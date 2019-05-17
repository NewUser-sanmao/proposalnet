package com.bootdo.proposal.domain;

public class OpenReturnDO {
	private String code;
	private String msg;
	private Object data;
	
	public OpenReturnDO() {
		this.code = "0";
		this.msg = "成功";
	}
	
	public OpenReturnDO(Object data) {
		this.code = "0";
		this.data = data;
	}
	
	public OpenReturnDO(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
