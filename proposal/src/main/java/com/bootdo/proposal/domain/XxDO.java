package com.bootdo.proposal.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 消息
 * 
 * @author shipan
 * @email 
 * @date 2018-10-31 14:06:43
 */
public class XxDO implements Serializable {
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
	//1 提案交流信息
	private Integer type;
	//内容
	private String nr;
	//发送人
	private Integer fsr;
	//接受人
	private Integer jsr;
	//承办单位id
	private Integer cbdwid;
	//委员id
	private Integer wyid;
	//提案信息id
	private Integer taxxid;
	//附件名称
	private String fjmc;
	//附件地址
	private String fjdz;
	

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
	 * 设置：1 提案交流信息
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：1 提案交流信息
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：内容
	 */
	public void setNr(String nr) {
		this.nr = nr;
	}
	/**
	 * 获取：内容
	 */
	public String getNr() {
		return nr;
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
	 * 设置：接受人
	 */
	public void setJsr(Integer jsr) {
		this.jsr = jsr;
	}
	/**
	 * 获取：接受人
	 */
	public Integer getJsr() {
		return jsr;
	}
	/**
	 * 设置：承办单位id
	 */
	public void setCbdwid(Integer cbdwid) {
		this.cbdwid = cbdwid;
	}
	/**
	 * 获取：承办单位id
	 */
	public Integer getCbdwid() {
		return cbdwid;
	}
	/**
	 * 设置：委员id
	 */
	public void setWyid(Integer wyid) {
		this.wyid = wyid;
	}
	/**
	 * 获取：委员id
	 */
	public Integer getWyid() {
		return wyid;
	}
	/**
	 * 设置：提案信息id
	 */
	public void setTaxxid(Integer taxxid) {
		this.taxxid = taxxid;
	}
	/**
	 * 获取：提案信息id
	 */
	public Integer getTaxxid() {
		return taxxid;
	}
	
	public String getFjmc() {
		return fjmc;
	}
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	public String getFjdz() {
		return fjdz;
	}
	public void setFjdz(String fjdz) {
		this.fjdz = fjdz;
	}
	
}
