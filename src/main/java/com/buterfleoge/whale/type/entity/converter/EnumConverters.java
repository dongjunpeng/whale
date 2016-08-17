/**
 *
 */
package com.buterfleoge.whale.type.entity.converter;

import javax.persistence.AttributeConverter;

import com.buterfleoge.whale.type.AccountStatus;
import com.buterfleoge.whale.type.AccountType;
import com.buterfleoge.whale.type.DiscountCodeStatus;
import com.buterfleoge.whale.type.DiscountType;
import com.buterfleoge.whale.type.Gender;
import com.buterfleoge.whale.type.GroupStatus;
import com.buterfleoge.whale.type.IdType;
import com.buterfleoge.whale.type.OrderStaffStatus;
import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.PayType;
import com.buterfleoge.whale.type.RefoundStatus;
import com.buterfleoge.whale.type.RefoundType;
import com.buterfleoge.whale.type.TravelArea;
import com.buterfleoge.whale.type.TravelType;

/**
 * 枚举对象的转化器
 *
 * @author xiezhenzong
 *
 */
public class EnumConverters {

    public static class AccountStatusConverter implements AttributeConverter<AccountStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(AccountStatus attribute) {
            return attribute.getStatus();
        }

        @Override
        public AccountStatus convertToEntityAttribute(Integer dbData) {
            return AccountStatus.valueOf(dbData);
        }

    }

    public static class AccountTypeConverter implements AttributeConverter<AccountType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(AccountType attribute) {
            return attribute.getType();
        }

        @Override
        public AccountType convertToEntityAttribute(Integer dbData) {
            return AccountType.valueOf(dbData);
        }

    }

    public static class DiscountCodeStatusConverter implements AttributeConverter<DiscountCodeStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(DiscountCodeStatus attribute) {
            return attribute.getStatus();
        }

        @Override
        public DiscountCodeStatus convertToEntityAttribute(Integer dbData) {
            return DiscountCodeStatus.valueOf(dbData);
        }

    }

    public static class DiscountTypeConverter implements AttributeConverter<DiscountType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(DiscountType attribute) {
            return attribute.getType();
        }

        @Override
        public DiscountType convertToEntityAttribute(Integer dbData) {
            return DiscountType.valueOf(dbData);
        }

    }

    public static class GenderConverter implements AttributeConverter<Gender, Integer> {

        @Override
        public Integer convertToDatabaseColumn(Gender attribute) {
            return attribute.getGender();
        }

        @Override
        public Gender convertToEntityAttribute(Integer dbData) {
            return Gender.valueOf(dbData);
        }

    }

    public static class GroupStatusConverter implements AttributeConverter<GroupStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(GroupStatus attribute) {
            return attribute.getStatus();
        }

        @Override
        public GroupStatus convertToEntityAttribute(Integer dbData) {
            return GroupStatus.valueOf(dbData);
        }

    }

    public static class IdTypeConverter implements AttributeConverter<IdType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(IdType attribute) {
            return attribute.getType();
        }

        @Override
        public IdType convertToEntityAttribute(Integer dbData) {
            return IdType.valueOf(dbData);
        }

    }

    public static class OrderStaffStatusConverter implements AttributeConverter<OrderStaffStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(OrderStaffStatus attribute) {
            return attribute.getStatus();
        }

        @Override
        public OrderStaffStatus convertToEntityAttribute(Integer dbData) {
            return OrderStaffStatus.valueOf(dbData);
        }

    }

    public static class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(OrderStatus attribute) {
            return attribute.getStatus();
        }

        @Override
        public OrderStatus convertToEntityAttribute(Integer dbData) {
            return OrderStatus.valueOf(dbData);
        }

    }

    public static class PayTypeConverter implements AttributeConverter<PayType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(PayType attribute) {
            return attribute.getType();
        }

        @Override
        public PayType convertToEntityAttribute(Integer dbData) {
            return PayType.valueOf(dbData);
        }

    }

    public static class RefoundStatusConverter implements AttributeConverter<RefoundStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(RefoundStatus attribute) {
            return attribute.getStatus();
        }

        @Override
        public RefoundStatus convertToEntityAttribute(Integer dbData) {
            return RefoundStatus.valueOf(dbData);
        }

    }

    public static class RefoundTypeConverter implements AttributeConverter<RefoundType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(RefoundType attribute) {
            return attribute.getType();
        }

        @Override
        public RefoundType convertToEntityAttribute(Integer dbData) {
            return RefoundType.valueOf(dbData);
        }

    }

    public static class TravelAreaConverter implements AttributeConverter<TravelArea, Integer> {

        @Override
        public Integer convertToDatabaseColumn(TravelArea attribute) {
            return attribute.getScope();
        }

        @Override
        public TravelArea convertToEntityAttribute(Integer dbData) {
            return TravelArea.valueOf(dbData);
        }

    }

    public static class TravelTypeConverter implements AttributeConverter<TravelType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(TravelType attribute) {
            return attribute.getType();
        }

        @Override
        public TravelType convertToEntityAttribute(Integer dbData) {
            return TravelType.valueOf(dbData);
        }

    }

}
