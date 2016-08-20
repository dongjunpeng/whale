package com.buterfleoge.whale.biz.account.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.account.AccountBiz;
import com.buterfleoge.whale.dao.AccountContactsRepository;
import com.buterfleoge.whale.dao.AccountInfoRepository;
import com.buterfleoge.whale.type.AccountStatus;
import com.buterfleoge.whale.type.entity.AccountContacts;
import com.buterfleoge.whale.type.entity.AccountInfo;
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
    private AccountContactsRepository accountContactsRepository;

    @Override
    public void updateBasicInfo(Long accountid, PostBasicInfoRequest request, Response response) throws Exception {
        String name = request.getName();
        Integer idType = request.getIdType();
        String id = request.getId();
        String email = request.getEmail();
        String mobile = request.getMobile();
        Integer gender = request.getGender();
        Date birthday = request.getBirthday();
        String address = request.getAddress();

        boolean isNeedSave = false;
        AccountInfo accountInfo = null;
        try {
            accountInfo = accountInfoRepository.findOne(accountid);
        } catch (Exception e) {
            LOG.error("find account info failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            return;
        }
        if (StringUtils.hasText(name) && !name.equals(accountInfo.getName())) {
            accountInfo.setName(name);
            isNeedSave = true;
        }
        if (StringUtils.hasText(id) && !id.equals(accountInfo.getId())) {
            accountInfo.setIdType(idType);
            accountInfo.setId(id);
            isNeedSave = true;
        }
        if (StringUtils.hasText(email) && !email.equals(accountInfo.getEmail())) {
            accountInfo.setEmail(email);
            isNeedSave = true;
        }
        if (StringUtils.hasText(mobile) && !mobile.equals(accountInfo.getMobile())) {
            accountInfo.setMobile(mobile);
            isNeedSave = true;
        }
        if (gender != null && !gender.equals(accountInfo.getGender())) {
            accountInfo.setGender(gender);
            isNeedSave = true;
        }
        if (birthday != null && !birthday.equals(accountInfo.getBirthday())) {
            accountInfo.setBirthday(birthday);
            isNeedSave = true;
        }
        if (StringUtils.hasText(address) && !address.equals(accountInfo.getAddress())) {
            accountInfo.setAddress(address);
            isNeedSave = true;
        }
        if (isNeedSave) {
            try {
                if (AccountStatus.WAIT_COMPLETE_INFO.value == accountInfo.getStatus()) {
                    accountInfo.setStatus(AccountStatus.OK.value);
                }
                accountInfo.setModTime(new Date());
                accountInfoRepository.save(accountInfo);
            } catch (Exception e) {
                LOG.error("save account info failed, reqid: " + request.getReqid(), e);
                response.setStatus(Status.DB_ERROR);
            }
        }
    }

    @Override
    public void getContacts(Long accountid, GetContactsRequest request, GetContactsResponse response) throws Exception {
        Long contactid = request.getContactid();
        try {
            if (contactid != null) {
                AccountContacts contact = accountContactsRepository.findByContactidAndValidTrue(contactid);
                if (contact != null) {
                    response.setContacts(Arrays.asList(contact));
                }
            } else {
                List<AccountContacts> contacts = accountContactsRepository.findByAccountidAndValidTrue(accountid);
                response.setContacts(contacts);
            }
        } catch (Exception e) {
            LOG.error("find contacts failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    @Override
    public void postContacts(Long accountid, PostContactsRequest request, Response response) throws Exception {
        Long contactid = request.getContactid();
        if (contactid == null) {
            insertContact(accountid, request, response);
        } else {
            AccountContacts contact = null;
            try {
                contact = accountContactsRepository.findByContactidAndValidTrue(contactid);
            } catch (Exception e) {
                LOG.error("find contact failed, reqid: " + request.getReqid(), e);
                response.setStatus(Status.DB_ERROR);
                return;
            }
            if (contact == null) {
                insertContact(accountid, request, response);
            } else {
                updateContacts(accountid, request, response, contact);
            }
        }
    }

    @Override
    public void deleteContacts(Long accountid, DeleteContactsRequest request, Response response) throws Exception {
        Long contactid = request.getContactid();
        try {
            AccountContacts contact = accountContactsRepository.findByContactidAndValidTrue(contactid);
            if (contact != null) {
                contact.setValid(false);
                accountContactsRepository.save(contact);
            }
        } catch (Exception e) {
            LOG.error("delete contacts failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    private void insertContact(Long accountid, PostContactsRequest request, Response response) throws Exception {
        AccountContacts contact = new AccountContacts();
        try {
            contact.setAccountid(accountid);
            contact.setAddress(request.getAddress());
            contact.setName(request.getName());
            contact.setEmail(request.getEmail());
            contact.setMobile(request.getMobile());
            contact.setId(request.getId());
            contact.setIdType(request.getIdType());
            contact.setBirthday(request.getBirthday());
            contact.setEmergencyContact(request.getEmergencyContact());
            contact.setEmergencyMobile(request.getEmergencyMobile());
            contact.setGender(request.getGender());
            contact.setAddTime(new Date());
            contact.setModTime(contact.getAddTime());
            contact.setValid(true);
            accountContactsRepository.save(contact);
        } catch (Exception e) {
            LOG.error("add contacts failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    private void updateContacts(Long accountid, PostContactsRequest request, Response response, AccountContacts contact)
            throws Exception {
        String name = request.getName();
        Integer idType = request.getIdType();
        String id = request.getId();
        String email = request.getEmail();
        String mobile = request.getMobile();
        Integer gender = request.getGender();
        Date birthday = request.getBirthday();
        String address = request.getAddress();
        String emergencyContact = request.getEmergencyContact();
        String emergencyMobile = request.getEmergencyMobile();
        boolean isNeedSave = false;
        if (StringUtils.hasText(name) && !name.equals(contact.getName())) {
            contact.setName(name);
            isNeedSave = true;
        }
        if (StringUtils.hasText(id) && !id.equals(contact.getId())) {
            contact.setIdType(idType);
            contact.setId(id);
            isNeedSave = true;
        }
        if (StringUtils.hasText(email) && !email.equals(contact.getEmail())) {
            contact.setEmail(email);
            isNeedSave = true;
        }
        if (StringUtils.hasText(mobile) && !mobile.equals(contact.getMobile())) {
            contact.setMobile(mobile);
            isNeedSave = true;
        }
        if (gender != null && !gender.equals(contact.getGender())) {
            contact.setGender(gender);
            isNeedSave = true;
        }
        if (birthday != null && !birthday.equals(contact.getBirthday())) {
            contact.setBirthday(birthday);
            isNeedSave = true;
        }
        if (StringUtils.hasText(address) && !address.equals(contact.getAddress())) {
            contact.setAddress(address);
            isNeedSave = true;
        }
        if (StringUtils.hasText(emergencyContact) && !emergencyContact.equals(contact.getEmergencyContact())) {
            contact.setEmergencyContact(emergencyContact);
            isNeedSave = true;
        }
        if (StringUtils.hasText(emergencyMobile) && !emergencyMobile.equals(contact.getEmergencyMobile())) {
            contact.setEmergencyMobile(emergencyMobile);
            isNeedSave = true;
        }
        if (isNeedSave) {
            try {
                contact.setModTime(new Date());
                accountContactsRepository.save(contact);
            } catch (Exception e) {
                LOG.error("post contacts failed, reqid: " + request.getReqid(), e);
                response.setStatus(Status.DB_ERROR);
            }
        }
    }

}
