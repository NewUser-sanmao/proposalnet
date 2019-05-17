package com.bootdo.proposal.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 履职分数汇总
 * 
 * @author shipan
 * @email 
 * @date 2018-12-08 19:18:11
 */
public class LzFshzDO implements Serializable {
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
	private String year;
	//
	private Integer userid;
	//个人提案数量
	private Integer personalnum;
	//集体/联名提案数量
	private Integer unionnum;
	//立案数量
	private Integer filingnum;
	//重点提案数量
	private Integer importantnum;
	//优秀提案数量
	private Integer perfectnum;
	//得到领导批示数量
	private Integer leaderinstructionnum;
	//附加分
	private Integer additionalpoints;

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
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * 获取：
	 */
	public String getYear() {
		return year;
	}
	/**
	 * 设置：
	 */
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	/**
	 * 获取：
	 */
	public Integer getUserid() {
		return userid;
	}
	/**
	 * 设置：个人提案数量
	 */
	public void setPersonalnum(Integer personalnum) {
		this.personalnum = personalnum;
	}
	/**
	 * 获取：个人提案数量
	 */
	public Integer getPersonalnum() {
		return personalnum;
	}
	/**
	 * 设置：集体/联名提案数量
	 */
	public void setUnionnum(Integer unionnum) {
		this.unionnum = unionnum;
	}
	/**
	 * 获取：集体/联名提案数量
	 */
	public Integer getUnionnum() {
		return unionnum;
	}
	/**
	 * 设置：立案数量
	 */
	public void setFilingnum(Integer filingnum) {
		this.filingnum = filingnum;
	}
	/**
	 * 获取：立案数量
	 */
	public Integer getFilingnum() {
		return filingnum;
	}
	/**
	 * 设置：重点提案数量
	 */
	public void setImportantnum(Integer importantnum) {
		this.importantnum = importantnum;
	}
	/**
	 * 获取：重点提案数量
	 */
	public Integer getImportantnum() {
		return importantnum;
	}
	/**
	 * 设置：优秀提案数量
	 */
	public void setPerfectnum(Integer perfectnum) {
		this.perfectnum = perfectnum;
	}
	/**
	 * 获取：优秀提案数量
	 */
	public Integer getPerfectnum() {
		return perfectnum;
	}
	/**
	 * 设置：得到领导批示数量
	 */
	public void setLeaderinstructionnum(Integer leaderinstructionnum) {
		this.leaderinstructionnum = leaderinstructionnum;
	}
	/**
	 * 获取：得到领导批示数量
	 */
	public Integer getLeaderinstructionnum() {
		return leaderinstructionnum;
	}
	/**
	 * 设置：附加分
	 */
	public void setAdditionalpoints(Integer additionalpoints) {
		this.additionalpoints = additionalpoints;
	}
	/**
	 * 获取：附加分
	 */
	public Integer getAdditionalpoints() {
		return additionalpoints;
	}
}
