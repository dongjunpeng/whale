package com.buterfleoge.whale.type.protocol.travel.imgtext;

import java.util.List;

/**
 * @author Brent24
 *
 */
public class Imgtext {
    private List<String> sliderImgs;
    private List<String> descriptions;
    private List<Day> days;
    private Notice notice;
    private Expense expense;

    public List<String> getSliderImgs() {
        return sliderImgs;
    }

    public void setSliderImgs(List<String> sliderImgs) {
        this.sliderImgs = sliderImgs;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

}
