package com.buterfleoge.whale.type.protocol;

import java.util.List;

import com.buterfleoge.whale.BaseObject;

/**
 * base response
 * 
 * @author xiezhenzong
 *
 */
public class Response<T> extends BaseObject {

	public static final int STATUS_OK = 100;
	public static final int STATUS_FAIL = 200;
	public static final int STATUS_PARAM_ERROR = 300;
	public static final int STATUS_AUTH_ERROR = 400;
	public static final int STATUS_SYS_ERROR = 500;
	public static final int STATUS_RESOURE_ERROR = 600;

	private int status;
	private String showMessage;
	private List<Error> errors;
	private List<T> data;

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the errors
	 */
	public List<Error> getErrors() {
		return errors;
	}

	/**
	 * @param errors
	 *            the errors to set
	 */
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	/**
	 * @return the data
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<T> data) {
		this.data = data;
	}

	public String getShowMessage() {
		return showMessage;
	}

	public void setShowMessage(String showMessage) {
		this.showMessage = showMessage;
	}

}
