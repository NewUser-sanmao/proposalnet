package com.bootdo.proposal.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 再次办理
 * 
 * @author shipan
 * @email 
 * @date 2018-12-28 14:53:24
 */
public class ZcblDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//创建人
	private Integer createid;
	//创建时间
	private Date createtime;
	//修改人
	private Integer updateid;
	//修改时间
	private Date updatetime;
	//数据状态
	private Integer state;
	//提案id
	private Integer taxxid;
	//次数
	private Integer cs;

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
	 * 设置：创建人
	 */
	public void setCreateid(Integer createid) {
		this.createid = createid;
	}
	/**
	 * 获取：创建人
	 */
	public Integer getCreateid() {
		return createid;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：修改人
	 */
	public void setUpdateid(Integer updateid) {
		this.updateid = updateid;
	}
	/**
	 * 获取：修改人
	 */
	public Integer getUpdateid() {
		return updateid;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdatetime() {
		return updatetime;
	}
	/**
	 * 设置：数据状态
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：数据状态
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
	 * 设置：次数
	 */
	public void setCs(Integer cs) {
		this.cs = cs;
	}
	/**
	 * 获取：次数
	 */
	public Integer getCs() {
		return cs;
	}
}
