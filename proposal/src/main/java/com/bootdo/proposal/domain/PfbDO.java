package com.bootdo.proposal.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 评分题目表
 * 
 * @author shipan
 * @email 
 * @date 2018-11-07 16:49:05
 */
public class PfbDO implements Serializable {
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
	//1 办理单位评分选项 2委员
	private Integer type;
	//序号
	private Integer xh;
	//内容
	private String nr;
	//基准分
	private Integer jzf;

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
	 * 设置：1 办理单位评分选项 2委员
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：1 办理单位评分选项 2委员
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：序号
	 */
	public void setXh(Integer xh) {
		this.xh = xh;
	}
	/**
	 * 获取：序号
	 */
	public Integer getXh() {
		return xh;
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
	 * 设置：基准分
	 */
	public void setJzf(Integer jzf) {
		this.jzf = jzf;
	}
	/**
	 * 获取：基准分
	 */
	public Integer getJzf() {
		return jzf;
	}
}
