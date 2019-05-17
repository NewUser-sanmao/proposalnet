package com.bootdo.proposal.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-10-21 15:32:38
 */
public class GbsqCbdwDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//所属委员会
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
	//提案id
	private Integer taxxid;
	//承办单位Id
	private Integer cbdwid;
	//类型 1承办,2分办,3协办
	private Integer type;

	/**
	 * 设置：所属委员会
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：所属委员会
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
	 * 设置：提案id
	 */
	public void setTaxxid(Integer taxxid) {
		this.taxxid = taxxid;
	}
	/**
	 * 获取：提案id
	 */
	public Integer getTaxxid() {
		return taxxid;
	}
	/**
	 * 设置：承办单位Id
	 */
	public void setCbdwid(Integer cbdwid) {
		this.cbdwid = cbdwid;
	}
	/**
	 * 获取：承办单位Id
	 */
	public Integer getCbdwid() {
		return cbdwid;
	}
	/**
	 * 设置：类型 1承办,2分办,3协办
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型 1承办,2分办,3协办
	 */
	public Integer getType() {
		return type;
	}
}
