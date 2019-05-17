package com.bootdo.proposal.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 改办申请
 * 
 * @author shipan
 * @email 
 * @date 2018-10-23 16:56:28
 */
public class GbsqDO implements Serializable {
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
	//改办理由
	private String ly;
	//0未处理 1同意 2不同意 3手动处理完成
	private Integer type;
	//立案类型
	private Integer latype;
	//立案状态
	private Integer lastate;
	
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
	 * 设置：改办理由
	 */
	public void setLy(String ly) {
		this.ly = ly;
	}
	/**
	 * 获取：改办理由
	 */
	public String getLy() {
		return ly;
	}
	/**
	 * 设置：0未处理 1处理
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：0未处理 1处理
	 */
	public Integer getType() {
		return type;
	}
	
	public Integer getLatype() {
		return latype;
	}
	
	public void setLatype(Integer latype) {
		this.latype = latype;
	}
	public Integer getLastate() {
		return lastate;
	}
	public void setLastate(Integer lastate) {
		this.lastate = lastate;
	}
	
	
}
