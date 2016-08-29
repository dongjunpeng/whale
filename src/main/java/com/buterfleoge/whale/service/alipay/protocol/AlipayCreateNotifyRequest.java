package com.buterfleoge.whale.service.alipay.protocol;

import java.util.Date;

import com.buterfleoge.whale.type.AlipayRefundStatus;

/**
 * @author xiezhenzong
 *
 */
public class AlipayCreateNotifyRequest extends AlipayCallbackRequest {

    private Date gmt_create;

    private Date gmt_payment;

    private Date gmt_close;

    private AlipayRefundStatus refund_status;

    private Date gmt_refund;

    /**
     * @return the gmt_create
     */
    public Date getGmt_create() {
        return gmt_create;
    }

    /**
     * @param gmt_create
     *            the gmt_create to set
     */
    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    /**
     * @return the gmt_payment
     */
    public Date getGmt_payment() {
        return gmt_payment;
    }

    /**
     * @param gmt_payment
     *            the gmt_payment to set
     */
    public void setGmt_payment(Date gmt_payment) {
        this.gmt_payment = gmt_payment;
    }

    /**
     * @return the gmt_close
     */
    public Date getGmt_close() {
        return gmt_close;
    }

    /**
     * @param gmt_close
     *            the gmt_close to set
     */
    public void setGmt_close(Date gmt_close) {
        this.gmt_close = gmt_close;
    }

    /**
     * @return the refund_status
     */
    public AlipayRefundStatus getRefund_status() {
        return refund_status;
    }

    /**
     * @param refund_status
     *            the refund_status to set
     */
    public void setRefund_status(AlipayRefundStatus refund_status) {
        this.refund_status = refund_status;
    }

    /**
     * @return the gmt_refund
     */
    public Date getGmt_refund() {
        return gmt_refund;
    }

    /**
     * @param gmt_refund
     *            the gmt_refund to set
     */
    public void setGmt_refund(Date gmt_refund) {
        this.gmt_refund = gmt_refund;
    }

}
