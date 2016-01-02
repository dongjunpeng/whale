package com.buterfleoge.whale;

public class StatusObject {
	private String status;
	private String content;
	private String detail;

	public StatusObject(String status) {
		this.status = status;
	}

	public StatusObject(String status, String content) {
		this.status = status;
		this.content = content;
	}

	public StatusObject(String status, String content, String detail) {
		this.status = status;
		this.content = content;
		this.detail = detail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
