package com.bootdo.proposal.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-07 11:34:53
 */
public class TajbDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer createid;
	//
	private Date createtime;
	//
	private Integer updateid;
	//
	private Date updatetime;
	//
	private Integer state;
	//提案界别
	private String tajbmc;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setCreateid(Integer createid) {
		this.createid = createid;
	}
	/**
	 * 获取：
	 */
	public Integer getCreateid() {
		return createid;
	}
	/**
	 * 设置：
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateid(Integer updateid) {
		this.updateid = updateid;
	}
	/**
	 * 获取：
	 */
	public Integer getUpdateid() {
		return updateid;
	}
	/**
	 * 设置：
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdatetime() {
		return updatetime;
	}
	/**
	 * 设置：
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * 设置：提案界别
	 */
	public void setTajbmc(String tajbmc) {
		this.tajbmc = tajbmc;
	}
	/**
	 * 获取：提案界别
	 */
	public String getTajbmc() {
		return tajbmc;
	}
}
