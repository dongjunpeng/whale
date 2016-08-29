/**
 *
 */
package com.buterfleoge.whale;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xiezhenzong
 *
 */
public class EnumObject extends BaseObject {

    public final int value;

    /**
     *
     */
    public EnumObject(int value) {
        this.value = value;
    }

    /**
     *
     * @author xiezhenzong
     *
     */
    public static class EnumObjectHelper<T extends EnumObject> {

        private List<T> values;
        private Map<Integer, T> valueMap = new HashMap<Integer, T>();

        /**
         * 创建一个helper
         *
         * @param values
         *            values, not null
         */
        public EnumObjectHelper(List<T> values) {
            this.values = values;
            for (T t : values) {
                T pre = valueMap.put(t.value, t);
                if (pre != null) {
                    throw new IllegalArgumentException("Duplicate value occur, value: " + pre);
                }
            }
        }

        /**
         * factory method
         *
         * @param values
         *            values
         * @return helper
         */
        @SuppressWarnings("unchecked")
        public static final <T extends EnumObject> EnumObjectHelper<T> create(T... values) {
            return new EnumObjectHelper<T>(Arrays.asList(values));
        }

        /**
         * 返回枚举集合
         *
         * @return enum object list, not null
         */
        public List<T> values() {
            return Collections.unmodifiableList(values);
        }

        /**
         * 返回枚举的int值集合
         * 
         * @return values
         */
        public Set<Integer> intValues() {
            return Collections.unmodifiableSet(valueMap.keySet());
        }

        /**
         * 获取value对应的枚举对象
         *
         * @param value
         *            value
         * @return enum object
         */
        public T valueOf(int value) {
            T enumObj = valueMap.get(value);
            if (enumObj == null) {
                throw new IllegalArgumentException("Can't find this enum, value: " + value);
            }
            return enumObj;
        }
    }

}
