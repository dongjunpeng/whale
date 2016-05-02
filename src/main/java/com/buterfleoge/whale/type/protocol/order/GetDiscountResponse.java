package com.buterfleoge.whale.type.protocol.order;

import java.util.List;

import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.object.DiscountObject;

/**
 * @author Brent24
 *
 */
public class GetDiscountResponse extends Response {

    private List<DiscountObject> list;

    private Long studentDiscount;

    private Long index;

    public List<DiscountObject> getList() {
        return list;
    }

    public void setList(List<DiscountObject> list) {
        this.list = list;
    }

    public Long getStudentDiscount() {
        return studentDiscount;
    }

    public void setStudentDiscount(Long studentDiscount) {
        this.studentDiscount = studentDiscount;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

}
