package com.buterfleoge.whale.biz.account.impl;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.Constants.BizCode;
import com.buterfleoge.whale.Constants.DefaultValue;
import com.buterfleoge.whale.Constants.ErrorMsg;
import com.buterfleoge.whale.Constants.Key;
import com.buterfleoge.whale.Constants.Prefix;
import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.Utils;
import com.buterfleoge.whale.biz.account.AccountBiz;
import com.buterfleoge.whale.dao.AccountContactsRepository;
import com.buterfleoge.whale.dao.AccountInfoRepository;
import com.buterfleoge.whale.dao.AccountSettingRepository;
import com.buterfleoge.whale.service.MailService;
import com.buterfleoge.whale.service.mail.MailInfo;
import com.buterfleoge.whale.type.AccountStatus;
import com.buterfleoge.whale.type.entity.AccountContacts;
import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.type.entity.AccountSetting;
import com.buterfleoge.whale.type.protocol.Error;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.account.DeleteContactsRequest;
import com.buterfleoge.whale.type.protocol.account.EmailExistRequest;
import com.buterfleoge.whale.type.protocol.account.GetContactsRequest;
import com.buterfleoge.whale.type.protocol.account.GetContactsResponse;
import com.buterfleoge.whale.type.protocol.account.PostContactsRequest;
import com.buterfleoge.whale.type.protocol.account.PutContactsRequest;
import com.buterfleoge.whale.type.protocol.account.RegisterRequest;
import com.buterfleoge.whale.type.protocol.account.RegisterResponse;
import com.buterfleoge.whale.type.protocol.account.UpdateBasicInfoRequest;
import com.buterfleoge.whale.type.protocol.account.ValidateEmailRequest;

/**
 * 账户相关的操作
 *
 * @author xiezhenzong
 *
 */
@Service("accountBiz")
public class AccountBizImpl implements AccountBiz {

	private static final Logger LOG = LoggerFactory.getLogger(AccountBizImpl.class);
	private static final AccountStatus INIT_STATUS = AccountStatus.WAIT_ACTIVE;

	@Autowired
	private AccountInfoRepository accountInfoRepository;

	@Autowired
	private AccountSettingRepository accountSettingRepository;

	@Autowired
	private AccountContactsRepository accountContactsRepository;

	@Autowired
	private MailService mailService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public void isEmailExist(EmailExistRequest request, Response response) throws Exception {
		String email = request.getEmail();
		try {
			if (accountInfoRepository.countByEmail(email) == 0) {
				response.setStatus(Status.OK);
			} else {
				response.setStatus(Status.BIZ_ERROR);
				response.addError(new Error(BizCode.EMAIL_EXIST, ErrorMsg.EMAIL_EXIST));
			}
		} catch (Exception e) {
			LOG.error("Check email exist from db failed", e);
			response.setStatus(Status.DB_ERROR);
		}
	}

	@Override
	public void registerByEmail(RegisterRequest request, RegisterResponse response) throws Exception {
		EmailExistRequest emailExistRequest = createEmailExistRequestFromRegisterRequest(request);
		isEmailExist(emailExistRequest, response);
		if (response.hasError()) {
			return;
		}

		boolean isPasswordValid = Utils.isPasswordValid(request.getPassword());
		if (!isPasswordValid) {
			response.setStatus(Status.BIZ_ERROR);
			response.addError(new Error(BizCode.INVALID_PASSWORD, ErrorMsg.INVALID_PASSWORD));
			return;
		}

		AccountInfo info = new AccountInfo();
		info.setStatus(INIT_STATUS);
		info.setEmail(request.getEmail());
		info.setPassword(request.getPassword());
		info.setType(request.getType());
		info.setAddTime(System.currentTimeMillis());
		info.setModTime(info.getAddTime());

		try {
			AccountInfo accountInfo = accountInfoRepository.save(info);
			response.setAccountInfo(accountInfo);

			// TODO
			// 这个地方改了
			AccountSetting setting = new AccountSetting();
			setting.setAccountid(accountInfo.getAccountid());
			accountSettingRepository.save(setting);

			response.setStatus(Status.OK);

			InetAddress address = Utils.getAddress();
			String validKey = Utils
					.stringMD5(address.getHostName() + "?" + accountInfo.toString() + System.currentTimeMillis());
			String validCode = validKey.substring(0, 6);
			MailInfo mailInfo = new MailInfo();
			mailInfo.addInfo(Key.VALID_MAIL_VALID_CODE, validCode);
			boolean isAdd2Pool = mailService.sendMail(mailInfo);
			if (isAdd2Pool) {
				String key = Prefix.VALID_MAIL_CACHE_PREFIX + accountInfo.getEmail();
				stringRedisTemplate.opsForValue().set(key, validCode, DefaultValue.VALID_MAIL_INVALID_PERIOD,
						TimeUnit.MILLISECONDS);
			}
		} catch (Exception e) {
			LOG.error("Save account info to db failed", e);
			response.setStatus(Status.DB_ERROR);
		}
	}

	/**
	 * @param request
	 * @return
	 */
	private EmailExistRequest createEmailExistRequestFromRegisterRequest(RegisterRequest request) {
		String email = request.getEmail();
		EmailExistRequest emailExistRequest = new EmailExistRequest();
		emailExistRequest.setEmail(email);
		return emailExistRequest;
	}

	@Override
	public void validateEmail(ValidateEmailRequest request, Response response) throws Exception {
		String email = request.getEmail();
		String validCode = request.getValidCode();

		String key = Prefix.VALID_MAIL_CACHE_PREFIX + email;
		String validCodeInCache = stringRedisTemplate.opsForValue().get(key);
		if (validCode.equals(validCodeInCache)) {
			try {
				AccountInfo accountInfo = accountInfoRepository.findByEmail(email);
				accountInfo.setStatus(AccountStatus.OK);
				accountInfoRepository.save(accountInfo);
				response.setStatus(Status.OK);
			} catch (Exception e) {
				LOG.error("validate email failed", e);
				response.setStatus(Status.DB_ERROR);
			}
		} else {
			response.setStatus(Status.BIZ_ERROR);
			response.addError(new Error(BizCode.INVALID_VALID_CODE, ErrorMsg.INVALID_VALID_CODE));
		}
	}

	@Override
	public void updateBasicInfo(UpdateBasicInfoRequest request, Response response) throws Exception {

		AccountInfo accountInfo;
		AccountSetting accountSetting;
		Date now;

		Long accountid = request.getAccountid();

		try {

			// 每次更新，必须同时提交么？还是可以只提交一个？
			if (request.getAccountid() != null) {

				accountInfo = accountInfoRepository.findByAccountid(accountid);
				accountSetting = accountSettingRepository.findByAccountid(accountid);

				// TODO

				accountInfo.setEmail(request.getEmail());
				accountInfo.setId(request.getId());
				accountInfo.setIdType(request.getIdType());
				accountInfo.setMobile(request.getMobile());
				accountInfo.setName(request.getName());
				accountInfo.setPassword(request.getPassword());// password是不是要单独搞？

				accountSetting.setAddress(request.getAddress());
				accountSetting.setAvatarUrl(request.getAvatarUrl());
				accountSetting.setBirthday(request.getBirthday());
				accountSetting.setGender(request.getGender());
				accountSetting.setNickname(request.getNickname());
				accountSetting.setQqid(request.getQqid());
				accountSetting.setQqname(request.getQqname());
				accountSetting.setWbid(request.getWbid());
				accountSetting.setWbname(request.getWbname());
				accountSetting.setWxid(request.getWxid());
				accountSetting.setWxname(request.getWxname());

				now = new Date();
				accountInfo.setModTime(now.getTime() / 1000);
				accountSetting.setModTime(now.getTime() / 1000);

				accountInfoRepository.save(accountInfo);
				accountSettingRepository.save(accountSetting);

				response.setStatus(Status.OK);
			} else {
				response.setStatus(Status.PARAM_ERROR);

			}
		} catch (Exception e) {
			LOG.error("update basicInfo failed", e);
			response.setStatus(Status.DB_ERROR);
		}

	}

	@Override
	public void getContacts(GetContactsRequest request, GetContactsResponse response) throws Exception {
		Long contactid = request.getContactid();
		Long accountid = request.getAccountid();
		Boolean isDefault = request.getIsDefault();

		try {
			if (contactid != null) {
				AccountContacts contact = accountContactsRepository.findByContactidAndValidTrue(contactid);
				response.setContacts(Arrays.asList(contact));
				response.setStatus(Status.OK);
			} else {
				if (accountid != null) {
					if (isDefault) {
						AccountContacts contact = accountContactsRepository
								.findByAccountidAndValidTrueAndIsDefaultTrue(accountid);
						response.setContacts(Arrays.asList(contact));
						response.setStatus(Status.OK);
					} else {
						List<AccountContacts> contacts = accountContactsRepository
								.findByAccountidAndValidTrue(accountid);
						response.setContacts(contacts);
						response.setStatus(Status.OK);
					}
				} else {
					response.setStatus(Status.PARAM_ERROR);
				}
			}

		} catch (Exception e) {
			LOG.error("find contacts failed", e);
			response.setStatus(Status.DB_ERROR);
		}
	}

	// @Override
	// public void postContacts(PostContactsRequest request,
	// PostContactsResponse response) throws Exception {
	// Long accountid = request.getAccountid();
	// List<AccountContacts> contacts = request.getContacts();
	// List<AccountContacts> insertContacts = findInsertContacts(contacts);
	// Map<Long, AccountContacts> updateContactsMap =
	// findUpdateContacts(contacts);
	//
	// List<AccountContacts> ret = new
	// ArrayList<AccountContacts>(contacts.size());
	// if (insertContacts.size() != 0) {
	// try {
	// Iterable<AccountContacts> iterable =
	// accountContactsRepository.save(insertContacts);
	// for (AccountContacts accountContacts : iterable) {
	// ret.add(accountContacts);
	// }
	// } catch (Exception e) {
	// LOG.error("insert contacts failed", e);
	// response.setStatus(Status.DB_ERROR);
	// return;
	// }
	// }
	//
	// if (updateContactsMap.size() != 0) {
	// try {
	// Iterable<AccountContacts> iterable = accountContactsRepository
	// .findByContactidInAndAccountidAndValidTrue(updateContactsMap.keySet(),
	// accountid);
	// for (AccountContacts target : iterable) {
	// AccountContacts from = updateContactsMap.get(target.getContactid());
	//
	// if (from.getName() != null) {
	// target.setName(from.getName());
	// }
	//
	// if (from.getId() != null) {
	// target.setId(from.getId());
	// target.setIdType(from.getIdType());
	// }
	//
	// if (from.getGender() != null) {
	// target.setGender(from.getGender());
	// }
	//
	// if (from.getBirthday() != null) {
	// target.setBirthday(from.getBirthday());
	// }
	//
	// if (from.getMobile() != null) {
	// target.setMobile(from.getMobile());
	// }
	//
	// if (from.getEmail() != null) {
	// target.setEmail(from.getEmail());
	// }
	//
	// target.setModTime(System.currentTimeMillis());
	// updateContactsMap.remove(target.getContactid());
	// }
	//
	// iterable = accountContactsRepository.save(iterable);
	// for (AccountContacts accountContacts : iterable) {
	// ret.add(accountContacts);
	// }
	//
	// if (updateContactsMap.size() != 0) {
	// throw new Exception("存在没有更新的");
	// }
	//
	// } catch (Exception e) {
	// LOG.error("update contacts failed", e);
	// response.setStatus(Status.DB_ERROR);
	// return;
	// }
	// }
	// response.setContacts(ret);
	// response.setStatus(Status.OK);
	// }
	@Override
	public void postContacts(PostContactsRequest request, Response response) throws Exception {
		AccountContacts contact = new AccountContacts();
		try {
			contact.setAccountid(request.getAccountid());
			contact.setAddress(request.getAddress());
			contact.setBirthday(request.getBirthday());
			contact.setEmail(request.getEmail());
			contact.setEmergencyContact(request.getEmergencyContact());
			contact.setEmergencyMobile(request.getEmergencyMobile());
			contact.setGender(request.getGender());
			contact.setId(request.getId());
			contact.setIdType(request.getIdType());
			contact.setIsDefault(request.getIsDefault());
			contact.setMobile(request.getMobile());
			contact.setName(request.getName());
			contact.setAddTime(System.currentTimeMillis() / 1000);
			contact.setModTime(System.currentTimeMillis() / 1000);
			contact.setValid(true);
			accountContactsRepository.save(contact);
		} catch (Exception e) {
			LOG.error("post contacts failed", e);
			response.setStatus(Status.DB_ERROR);
		}
	}

	@Override
	public void putContacts(PutContactsRequest request, Response response) throws Exception {
		try {
			AccountContacts contact = accountContactsRepository.findByContactidAndValidTrue(request.getContactid());
			contact.setAddress(request.getAddress());
			contact.setBirthday(request.getBirthday());
			contact.setEmail(request.getEmail());
			contact.setEmergencyContact(request.getEmergencyContact());
			contact.setEmergencyMobile(request.getEmergencyMobile());
			contact.setGender(request.getGender());
			contact.setId(request.getId());
			contact.setIdType(request.getIdType());
			contact.setIsDefault(request.getIsDefault());
			contact.setMobile(request.getMobile());
			contact.setName(request.getName());
			contact.setModTime(System.currentTimeMillis() / 1000);
			accountContactsRepository.save(contact);
		} catch (Exception e) {
			LOG.error("post contacts failed", e);
			response.setStatus(Status.DB_ERROR);
		}

	}

	@Override
	public void deleteContacts(DeleteContactsRequest request, Response response) throws Exception {
		Long contactid = request.getContactid();
		try {
			AccountContacts contact = accountContactsRepository.findByContactidAndValidTrue(contactid);
			contact.setValid(false);
			accountContactsRepository.save(contact);
			response.setStatus(Status.OK);
		} catch (Exception e) {
			LOG.error("delete contacts failed", e);
			response.setStatus(Status.DB_ERROR);
		}
	}

	// private Map<Long, AccountContacts>
	// findUpdateContacts(List<AccountContacts> contacts) {
	// Map<Long, AccountContacts> updateContacts = new HashMap<Long,
	// AccountContacts>(contacts.size());
	// for (AccountContacts accountContacts : contacts) {
	// if (accountContacts.getContactid() != 0) {
	// updateContacts.put(accountContacts.getContactid(), accountContacts);
	// }
	// }
	// return updateContacts;
	// }

	// private List<AccountContacts> findInsertContacts(List<AccountContacts>
	// contacts) {
	// List<AccountContacts> insertContacts = new
	// ArrayList<AccountContacts>(contacts.size());
	// for (AccountContacts accountContacts : contacts) {
	// if (accountContacts.getContactid() == 0) {
	// insertContacts.add(accountContacts);
	// }
	// }
	// return insertContacts;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.buterfleoge.whale.biz.account.AccountBiz#deleteContacts(com.
	 * buterfleoge.whale.type.protocol.account.DeleteContactsRequest,
	 * com.buterfleoge.whale.type.protocol.Response)
	 */

	// @Override
	// public void loginByEmail(Request<LoginRequest> request,
	// Response<AccountInfo> response) throws Exception {
	// LoginRequest requestItem = request.getFirstDataItem();
	//
	// try {
	// AccountInfo info =
	// accountInfoRepository.findByEmail(requestItem.getEmail());
	// response.setStatus(Status.OK);
	// if (info == null) {
	// response.addError(new Error(BizCode.EMAIL_NO_EXIST,
	// ErrorMsg.EMAIL_NO_EXIST));
	// return;
	// }
	//
	// if (!requestItem.getPassword().equals(info.getPassword())) {
	// response.addError(new Error(BizCode.WRONG_PASSWORD,
	// ErrorMsg.WRONG_PASSWORD));
	// return;
	// }
	//
	// if (!requestItem.getType().equals(info.getType())) {
	// response.addError(new Error(BizCode.WRONG_ACCOUNT_TYPE,
	// ErrorMsg.WRONG_ACCOUNT_TYPE));
	// return;
	// }
	// info.setPassword(null);
	// response.addData(info);
	// } catch (Exception e) {
	// LOG.error("Find account info from db failed", e);
	// response.setStatus(Status.DB_ERROR);
	// }
	// }

}
