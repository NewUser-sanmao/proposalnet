package com.bootdo.proposal.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 工作服务
 * 
 * @author shipan
 * @email 
 * @date 2018-10-25 10:42:22
 */
public class GzfwDO implements Serializable {
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
	//类型
	private String lx;
	//标题
	private String bt;
	//内容
	private String nr;
	//附加名称 每个用,隔开
	private String fjmc;
	//附件地址 每个用,隔开
	private String fjdz;
	
	private String createName;
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
	 * 设置：类型
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}
	/**
	 * 获取：类型
	 */
	public String getLx() {
		return lx;
	}
	/**
	 * 设置：标题
	 */
	public void setBt(String bt) {
		this.bt = bt;
	}
	/**
	 * 获取：标题
	 */
	public String getBt() {
		return bt;
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
	 * 设置：附加名称 每个用#,$隔开
	 */
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	/**
	 * 获取：附加名称 每个用#,$隔开
	 */
	public String getFjmc() {
		return fjmc;
	}
	/**
	 * 设置：附件地址 每个用#,$隔开
	 */
	public void setFjdz(String fjdz) {
		this.fjdz = fjdz;
	}
	/**
	 * 获取：附件地址 每个用#,$隔开
	 */
	public String getFjdz() {
		return fjdz;
	}
	
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
}
