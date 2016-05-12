package com.buterfleoge.whale.biz.account.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.account.AccountBiz;
import com.buterfleoge.whale.dao.AccountContactsRepository;
import com.buterfleoge.whale.dao.AccountInfoRepository;
import com.buterfleoge.whale.dao.AccountSettingRepository;
import com.buterfleoge.whale.type.entity.AccountContacts;
import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.type.entity.AccountSetting;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.account.DeleteContactsRequest;
import com.buterfleoge.whale.type.protocol.account.GetContactsRequest;
import com.buterfleoge.whale.type.protocol.account.GetContactsResponse;
import com.buterfleoge.whale.type.protocol.account.PostBasicInfoRequest;
import com.buterfleoge.whale.type.protocol.account.PostContactsRequest;

/**
 * 账户相关的操作
 *
 * @author xiezhenzong
 *
 */
@Service("accountBiz")
public class AccountBizImpl implements AccountBiz {

    private static final Logger LOG = LoggerFactory.getLogger(AccountBizImpl.class);

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    @Autowired
    private AccountSettingRepository accountSettingRepository;

    @Autowired
    private AccountContactsRepository accountContactsRepository;

    @Override
    public void updateBasicInfo(PostBasicInfoRequest request, Response response) throws Exception {
        AccountInfo accountInfo;
        AccountSetting accountSetting;
        Long accountid = request.getAccountid();
        try {
            if (accountid != null) {
                accountInfo = accountInfoRepository.findByAccountid(accountid);
                accountSetting = accountSettingRepository.findByAccountid(accountid);
                if (request.getEmail() != null)
                    accountInfo.setEmail(request.getEmail());
                if (request.getId() != null)
                    accountInfo.setId(request.getId());
                if (request.getIdType() != null)
                    accountInfo.setIdType(request.getIdType());
                if (request.getMobile() != null)
                    accountInfo.setMobile(request.getMobile());
                if (request.getName() != null)
                    accountInfo.setName(request.getName());
                if (request.getPassword() != null)
                    accountInfo.setPassword(request.getPassword());

                if (request.getAddress() != null)
                    accountSetting.setAddress(request.getAddress());
                if (request.getAvatarUrl() != null)
                    accountSetting.setAvatarUrl(request.getAvatarUrl());
                if (request.getBirthday() != null) {
                    accountSetting.setBirthday(request.getBirthday());
                }
                if (request.getGender() != null)
                    accountSetting.setGender(request.getGender());
                if (request.getNickname() != null)
                    accountSetting.setNickname(request.getNickname());
                if (request.getQqid() != null)
                    accountSetting.setQqid(request.getQqid());
                if (request.getQqname() != null)
                    accountSetting.setQqname(request.getQqname());
                if (request.getWbid() != null)
                    accountSetting.setWbid(request.getWbid());
                if (request.getWbname() != null)
                    accountSetting.setWbname(request.getWbname());
                if (request.getWxid() != null)
                    accountSetting.setWxid(request.getWxid());
                if (request.getWxname() != null)
                    accountSetting.setWxname(request.getWxname());

                accountInfo.setModTime(System.currentTimeMillis());
                accountSetting.setModTime(System.currentTimeMillis());

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
            } else {
                if (accountid != null) {
                    List<AccountContacts> contacts = null;
                    if (isDefault != null && isDefault) {
                        contacts = accountContactsRepository.findByAccountidAndValidTrue(accountid);
                    } else {
                        contacts =
                                accountContactsRepository.findByAccountidAndValidTrueAndIsDefaultFalse(accountid);
                    }
                    response.setContacts(contacts);
                } else {
                    response.setStatus(Status.PARAM_ERROR);
                }
            }

        } catch (Exception e) {
            LOG.error("find contacts failed", e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    @Override
    public void postContacts(PostContactsRequest request, Response response) throws Exception {
        Long contactid = request.getContactid();
        if (contactid == null) {
            insertContact(request, response);
        } else {
            AccountContacts contact = accountContactsRepository.findByContactidAndValidTrue(request.getContactid());
            if (contact == null) {
                insertContact(request, response);
            } else {
                updateContacts(request, response, contact);
            }
        }
    }

    private void insertContact(PostContactsRequest request, Response response) {
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
            contact.setIsDefault(false);
            contact.setMobile(request.getMobile());
            contact.setName(request.getName());
            contact.setAddTime(System.currentTimeMillis());
            contact.setModTime(System.currentTimeMillis());
            contact.setValid(true);
            accountContactsRepository.save(contact);
        } catch (Exception e) {
            LOG.error("post contacts failed", e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    private void updateContacts(PostContactsRequest request, Response response, AccountContacts contact) {
        try {
            if (request.getName() != null) {
                contact.setName(request.getName());
            }
            if (request.getId() != null) {
                contact.setId(request.getId());
                contact.setIdType(request.getIdType());
            }
            if (request.getGender() != null) {
                contact.setGender(request.getGender());
            }
            if (request.getBirthday() != null) {
                contact.setBirthday(request.getBirthday());
            }
            if (request.getMobile() != null) {
                contact.setMobile(request.getMobile());
            }
            if (request.getEmail() != null) {
                contact.setEmail(request.getEmail());
            }
            if (request.getAddress() != null) {
                contact.setAddress(request.getAddress());
            }
            if (request.getEmergencyContact() != null) {
                contact.setEmergencyContact(request.getEmergencyContact());
            }
            if (request.getEmergencyMobile() != null) {
                contact.setEmergencyMobile(request.getEmergencyMobile());
            }
            contact.setModTime(System.currentTimeMillis());
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
        } catch (Exception e) {
            LOG.error("delete contacts failed", e);
            response.setStatus(Status.DB_ERROR);
        }
    }

}
