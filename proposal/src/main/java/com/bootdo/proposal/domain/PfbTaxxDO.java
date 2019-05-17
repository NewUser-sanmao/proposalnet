package com.bootdo.proposal.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 提案信息对应的评分表
 * 
 * @author shipan
 * @email 
 * @date 2018-11-07 16:49:05
 */
public class PfbTaxxDO implements Serializable {
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
	//
	private Integer type;
	//
	private Integer pfbid;
	//提案id
	private Integer taxxid;
	//分值
	private Integer fz;

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
	 * 设置：
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setPfbid(Integer pfbid) {
		this.pfbid = pfbid;
	}
	/**
	 * 获取：
	 */
	public Integer getPfbid() {
		return pfbid;
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
	 * 设置：分值
	 */
	public void setFz(Integer fz) {
		this.fz = fz;
	}
	/**
	 * 获取：分值
	 */
	public Integer getFz() {
		return fz;
	}
}
