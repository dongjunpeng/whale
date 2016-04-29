package com.buterfleoge.whale.type.protocol.travel.imgtext;

import java.util.List;

/**
 * @author Brent24
 *
 */
public class Imgtext {

	private List<Day> days;
	private Notice notice;
	private Expense expense;

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
