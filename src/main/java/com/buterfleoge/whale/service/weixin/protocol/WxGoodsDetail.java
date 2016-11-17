package com.buterfleoge.whale.service.weixin.protocol;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author xiezhenzong
 *
 */
public class WxGoodsDetail {

    public static class WxGoodsDetailItem extends BaseObject {

        private String goods_id;

        private String wxpay_goods_id;

        private String goods_name;

        private Integer quantity;

        private Integer price;

        private String goods_category;

        private String body;

        public static WxGoodsDetailItem newInstance(Long groupid, String productName, int count, BigDecimal price, Long routeid) {
            WxGoodsDetailItem item = new WxGoodsDetailItem();
            item.setGoods_id(String.valueOf(groupid));
            item.setGoods_name(productName);
            item.setQuantity(count);
            item.setPrice(PriceConverter.yuanToFen(price).intValue());
            item.setGoods_category(String.valueOf(routeid));
            return item;
        }

        /**
         * @return the goods_id
         */
        public String getGoods_id() {
            return goods_id;
        }

        /**
         * @param goods_id
         *            the goods_id to set
         */
        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        /**
         * @return the wxpay_goods_id
         */
        public String getWxpay_goods_id() {
            return wxpay_goods_id;
        }

        /**
         * @param wxpay_goods_id
         *            the wxpay_goods_id to set
         */
        public void setWxpay_goods_id(String wxpay_goods_id) {
            this.wxpay_goods_id = wxpay_goods_id;
        }

        /**
         * @return the goods_name
         */
        public String getGoods_name() {
            return goods_name;
        }

        /**
         * @param goods_name
         *            the goods_name to set
         */
        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        /**
         * @return the quantity
         */
        public Integer getQuantity() {
            return quantity;
        }

        /**
         * @param quantity
         *            the quantity to set
         */
        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        /**
         * @return the price
         */
        public Integer getPrice() {
            return price;
        }

        /**
         * @param price
         *            the price to set
         */
        public void setPrice(Integer price) {
            this.price = price;
        }

        /**
         * @return the goods_category
         */
        public String getGoods_category() {
            return goods_category;
        }

        /**
         * @param goods_category
         *            the goods_category to set
         */
        public void setGoods_category(String goods_category) {
            this.goods_category = goods_category;
        }

        /**
         * @return the body
         */
        public String getBody() {
            return body;
        }

        /**
         * @param body
         *            the body to set
         */
        public void setBody(String body) {
            this.body = body;
        }

    }

    private List<WxGoodsDetailItem> goods_detail = new ArrayList<WxGoodsDetail.WxGoodsDetailItem>(3);

    public static WxGoodsDetail newInstance(Long groupid, String productName, int count, BigDecimal price, Long routeid) {
        WxGoodsDetail detail = new WxGoodsDetail();
        detail.goods_detail.add(WxGoodsDetailItem.newInstance(groupid, productName, count, price, routeid));
        return detail;
    }

    /**
     * @return the goods_detail
     */
    public List<WxGoodsDetailItem> getGoods_detail() {
        return goods_detail;
    }

    /**
     * @param goods_detail
     *            the goods_detail to set
     */
    public void setGoods_detail(List<WxGoodsDetailItem> goods_detail) {
        this.goods_detail = goods_detail;
    }

    /**
     * 获取json表示
     *
     * @return
     * @throws JsonProcessingException
     */
    public String toJsonString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

}
