package com.buterfleoge.whale.biz.account;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.buterfleoge.whale.Constants.BizCode;
import com.buterfleoge.whale.Constants.ErrorMsg;
import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.Utils;
import com.buterfleoge.whale.Utils.ListFilter;
import com.buterfleoge.whale.biz.AccountBiz;
import com.buterfleoge.whale.dao.AccountContactsRepository;
import com.buterfleoge.whale.dao.AccountInfoRepository;
import com.buterfleoge.whale.dao.ActivityRepository;
import com.buterfleoge.whale.dao.CouponRepository;
import com.buterfleoge.whale.exception.WeixinException;
import com.buterfleoge.whale.service.WeixinCgibinService;
import com.buterfleoge.whale.service.weixin.protocol.WxCgibinAccessTokenResponse;
import com.buterfleoge.whale.service.weixin.protocol.WxGetTicketResponse;
import com.buterfleoge.whale.type.AccountStatus;
import com.buterfleoge.whale.type.CouponStatus;
import com.buterfleoge.whale.type.CouponType;
import com.buterfleoge.whale.type.entity.AccountContact;
import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.type.entity.Activity;
import com.buterfleoge.whale.type.entity.Coupon;
import com.buterfleoge.whale.type.protocol.Error;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.account.DeleteContactsRequest;
import com.buterfleoge.whale.type.protocol.account.GetContactsRequest;
import com.buterfleoge.whale.type.protocol.account.GetContactsResponse;
import com.buterfleoge.whale.type.protocol.account.GetCouponsRequest;
import com.buterfleoge.whale.type.protocol.account.GetCouponsResponse;
import com.buterfleoge.whale.type.protocol.account.GetWxShareConfigRequest;
import com.buterfleoge.whale.type.protocol.account.GetWxShareConfigResponse;
import com.buterfleoge.whale.type.protocol.account.PostBasicInfoRequest;
import com.buterfleoge.whale.type.protocol.account.PostContactsRequest;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeRequest;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeResponse;

/**
 * 账户相关的操作
 *
 * @author xiezhenzong
 *
 */
@Service("accountBiz")
public class AccountBizImpl implements AccountBiz {

    private static final Logger LOG = LoggerFactory.getLogger(AccountBizImpl.class);

    @Value("${wx.cgi-bin.appid}")
    private String appid;

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    @Autowired
    private AccountContactsRepository accountContactsRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private WeixinCgibinService weixinCgibinService;

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public AccountInfo updateBasicInfo(Long accountid, PostBasicInfoRequest request, Response response) throws Exception {
        String name = request.getName();
        Integer idType = request.getIdType();
        String id = request.getId();
        String email = request.getEmail();
        String mobile = request.getMobile();
        Integer gender = request.getGender();
        Date birthday = request.getBirthday();
        String area = request.getArea();
        String address = request.getAddress();

        boolean isNeedSave = false;
        AccountInfo accountInfo = null;
        try {
            accountInfo = accountInfoRepository.findOne(accountid);
        } catch (Exception e) {
            LOG.error("find account info failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            return null;
        }
        if (StringUtils.hasText(name) && !name.equals(accountInfo.getName())) {
            accountInfo.setName(name);
            isNeedSave = true;
        }
        if (StringUtils.hasText(id) && !id.equals(accountInfo.getId())) {
            accountInfo.setId(id);
            isNeedSave = true;
        }
        if (idType != null && !idType.equals(accountInfo.getIdType())) {
            accountInfo.setIdType(idType);
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
        if (StringUtils.hasText(area) && !area.equals(accountInfo.getArea())) {
            accountInfo.setArea(area);
            isNeedSave = true;
        }
        if (StringUtils.hasText(address) && !address.equals(accountInfo.getAddress())) {
            accountInfo.setAddress(address);
            isNeedSave = true;
        }
        if (!isNeedSave) {
            return null;
        }
        try {
            Date now = new Date();
            boolean isNew = AccountStatus.WAIT_COMPLETE_INFO.value == accountInfo.getStatus();
            if (isNew) {
                accountInfo.setStatus(AccountStatus.OK.value);
            }
            accountInfo.setModTime(now);
            AccountInfo newAccountInfo = accountInfoRepository.save(accountInfo);
            try {
                if (isNew && isNewActivityOpen()) {
                    List<Coupon> coupons = couponRepository.findByAccountidAndTypeAndStatusAndVerify(accountid, CouponType.NEW.value,
                            CouponStatus.CREATED.value, Coupon.VERIFY);
                    if (!CollectionUtils.isEmpty(coupons)) {
                        Coupon coupon = coupons.get(0);
                        if (coupon.getUpdateCount() == 0) {
                            BigDecimal value = coupon.getValue().add(BigDecimal.valueOf(50));
                            coupon.addDesc("完善信息后升级为" + Utils.formatPrice(value) + "元");
                            coupon.setUpdateCount(1);
                            coupon.setValue(value);
                            coupon.setModTime(now);
                            couponRepository.save(coupon);
                        }
                    }
                }
            } catch (Exception e) {
                LOG.error("update new coupon failed, reqid: " + request.getReqid(), e);
            }
            return newAccountInfo;
        } catch (Exception e) {
            LOG.error("save account info failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            return accountInfo;
        }
    }

    @Override
    public void getContacts(Long accountid, GetContactsRequest request, GetContactsResponse response) throws Exception {
        Long contactid = request.getContactid();
        try {
            if (contactid != null) {
                AccountContact contact = accountContactsRepository.findByContactidAndAccountidAndValidTrue(contactid, accountid);
                if (contact != null) {
                    response.setContacts(Arrays.asList(contact));
                }
            } else {
                List<AccountContact> contacts = accountContactsRepository.findByAccountidAndValidTrue(accountid);
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
            AccountContact contact = null;
            try {
                contact = accountContactsRepository.findByContactidAndAccountidAndValidTrue(contactid, accountid);
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
            AccountContact contact = accountContactsRepository.findByContactidAndAccountidAndValidTrue(contactid, accountid);
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
        AccountContact contact = new AccountContact();
        try {
            contact.setAccountid(accountid);
            contact.setArea(request.getArea());
            contact.setAddress(request.getAddress());
            contact.setName(request.getName());
            contact.setEmail(request.getEmail());
            contact.setMobile(request.getMobile());
            contact.setId(request.getId());
            contact.setIdType(request.getIdType());
            contact.setBirthday(request.getBirthday());
            contact.setEmergency(request.getEmergency());
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

    private void updateContacts(Long accountid, PostContactsRequest request, Response response, AccountContact contact) throws Exception {
        String name = request.getName();
        Integer idType = request.getIdType();
        String id = request.getId();
        String email = request.getEmail();
        String mobile = request.getMobile();
        Integer gender = request.getGender();
        Date birthday = request.getBirthday();
        String area = request.getArea();
        String address = request.getAddress();
        Boolean emergency = request.getEmergency();
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
        if (StringUtils.hasText(area) && !area.equals(contact.getArea())) {
            contact.setArea(area);
            isNeedSave = true;
        }
        if (StringUtils.hasText(address) && !address.equals(contact.getAddress())) {
            contact.setAddress(address);
            isNeedSave = true;
        }
        if (emergency != null && !emergency.equals(contact.getEmergency())) {
            contact.setEmergency(emergency);
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

    @Override
    public void getCoupons(Long accountid, GetCouponsRequest request, GetCouponsResponse response) throws Exception {
        try {
            Integer type = request.getType();
            List<Coupon> coupons = null;
            if (type == null) {
                coupons = couponRepository.findByAccountidAndVerify(accountid, Coupon.VERIFY);
            } else {
                coupons = couponRepository.findByAccountidAndTypeAndVerify(accountid, type, Coupon.VERIFY);
            }
            if (request.isUsable()) {
                coupons = Utils.filter(coupons, new ListFilter<Coupon>() {
                    @Override
                    public boolean filter(Coupon target) {
                        long now = System.currentTimeMillis();
                        return target.getStatus() == CouponStatus.CREATED.value && target.getStartTime().getTime() <= now
                                && now <= target.getEndTime().getTime();
                    }
                });
            }
            response.setCount(coupons.size());
            if (request.isOnlyCount()) {
                response.setCoupons(Collections.<Coupon> emptyList());
            } else {
                response.setCoupons(coupons);
            }
        } catch (Exception e) {
            LOG.error("find discount code failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            response.setCoupons(Collections.<Coupon> emptyList());
        }
    }

    @Override
    public void validateDiscountCode(Long accountid, ValidateCodeRequest request, ValidateCodeResponse response) throws Exception {
        Coupon discountCode = couponRepository.findByDiscountCode(request.getCode());
        if (discountCode == null || (discountCode.getAccountid() != null && !discountCode.getAccountid().equals(accountid))) {
            response.setStatus(Status.BIZ_ERROR);
            response.addError(new Error(BizCode.DISCOUNT_CODE_NOT_EXIST, ErrorMsg.DISCOUNT_CODE_NOT_EXIST));
            return;
        }
        Date now = new Date();
        if (discountCode.getEndTime().getTime() < now.getTime()) {
            response.setStatus(Status.BIZ_ERROR);
            response.addError(new Error(Coupon.ERRMSG.get(CouponStatus.TIMEOUT.value)));
            return;
        }
        if (discountCode.getStatus() == CouponStatus.CREATED.value) {
            if (Coupon.UNVERIFY.equals(discountCode.getVerify())) {
                discountCode.setAccountid(accountid);
                discountCode.setVerify(Coupon.VERIFY);
                discountCode.setValideTime(now);
                discountCode.setModTime(now);
                couponRepository.save(discountCode);
                response.setValue(discountCode.getValue());
            } else {
                response.setStatus(Status.BIZ_ERROR);
                response.addError(new Error("优惠码已经兑换!"));
            }
        } else {
            response.setStatus(Status.BIZ_ERROR);
            response.addError(new Error(Coupon.ERRMSG.get(discountCode.getStatus())));
        }
    }

    @Override
    public void getWxShareConfig(Long accountid, GetWxShareConfigRequest request, GetWxShareConfigResponse response) throws Exception {
        WxCgibinAccessTokenResponse accessToken = weixinCgibinService.getCgibinAccessToken();
        if (accessToken == null) {
            throw new WeixinException("获取accessToken失败");
        }
        WxGetTicketResponse ticket = weixinCgibinService.getTicket(accessToken.getAccess_token(), "jsapi");
        if (ticket == null) {
            throw new WeixinException("获取jsapiTicket失败");
        }

        String nonceStr = Utils.createNonceStr();
        String jsapiTicket = ticket.getTicket();
        long timeStamp = System.currentTimeMillis();
        String url = request.getUrl();
        String signature = createSignatur(nonceStr, jsapiTicket, timeStamp, url);

        response.setAppid(appid);
        response.setTimestamp(timeStamp);
        response.setNonceStr(nonceStr);
        response.setSignature(signature);
    }

    private String createSignatur(String nonceStr, String jsapiTicket, long timeStamp, String url) {
        Map<String, Object> paramToEncry = new HashMap<String, Object>();
        paramToEncry.put("noncestr", nonceStr);
        paramToEncry.put("jsapi_ticket", jsapiTicket);
        paramToEncry.put("timestamp", timeStamp);
        paramToEncry.put("url", url);
        String link = Utils.createLinkString(paramToEncry);
        return DigestUtils.sha1Hex(link);
    }

    private boolean isNewActivityOpen() {
        try {
            Activity activity = activityRepository.findOne(Activity.ACTIVITY_NEW);
            return activity != null && activity.isOpen();
        } catch (Exception e) {
            LOG.error("find activity failed", e);
            return false;
        }
    }

}
