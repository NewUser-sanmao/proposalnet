package com.bootdo.proposal.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 复函回执
 * 
 * @author shipan
 * @email 
 * @date 2018-11-02 21:04:27
 */
public class FhhzDO implements Serializable {
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
	//1意见稿 2正式复函
	private Integer type;
	//提案id
	private Integer taxxid;
	//发送人
	private Integer fsr;
	//接收人
	private Integer jsr;
	//文件地址
	private String dz;
	//文件名称
	private String mc;

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
	 * 设置：1意见稿 2正式复函
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：1意见稿 2正式复函
	 */
	public Integer getType() {
		return type;
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
	 * 设置：发送人
	 */
	public void setFsr(Integer fsr) {
		this.fsr = fsr;
	}
	/**
	 * 获取：发送人
	 */
	public Integer getFsr() {
		return fsr;
	}
	/**
	 * 设置：接收人
	 */
	public void setJsr(Integer jsr) {
		this.jsr = jsr;
	}
	/**
	 * 获取：接收人
	 */
	public Integer getJsr() {
		return jsr;
	}
	/**
	 * 设置：文件地址
	 */
	public void setDz(String dz) {
		this.dz = dz;
	}
	/**
	 * 获取：文件地址
	 */
	public String getDz() {
		return dz;
	}
	/**
	 * 设置：文件名称
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}
	/**
	 * 获取：文件名称
	 */
	public String getMc() {
		return mc;
	}
}
