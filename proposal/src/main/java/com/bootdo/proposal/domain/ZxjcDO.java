package com.bootdo.proposal.domain;

import java.io.Serializable;
import java.util.Date;

import com.bootdo.common.utils.DateUtils;



/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-07 11:34:53
 */
public class ZxjcDO implements Serializable {
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
	//届id
	private String jid;
	//届名称
	private String jmc;
	//名称
	private String zxjcmc;
	//起始号
	private String zxjcqsh;
	//起始日期
	private Date qsrq;
	//截止日期
	private Date zjrq;
	
	
	private String qsrqString;
	
	private String zjrqString;

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
	 * 设置：届id
	 */
	public void setJid(String jid) {
		this.jid = jid;
	}
	/**
	 * 获取：届id
	 */
	public String getJid() {
		return jid;
	}
	/**
	 * 设置：届名称
	 */
	public void setJmc(String jmc) {
		this.jmc = jmc;
	}
	/**
	 * 获取：届名称
	 */
	public String getJmc() {
		return jmc;
	}
	/**
	 * 设置：名称
	 */
	public void setZxjcmc(String zxjcmc) {
		this.zxjcmc = zxjcmc;
	}
	/**
	 * 获取：名称
	 */
	public String getZxjcmc() {
		return zxjcmc;
	}
	/**
	 * 设置：起始号
	 */
	public void setZxjcqsh(String zxjcqsh) {
		this.zxjcqsh = zxjcqsh;
	}
	/**
	 * 获取：起始号
	 */
	public String getZxjcqsh() {
		return zxjcqsh;
	}
	/**
	 * 设置：起始日期
	 */
	public void setQsrq(Date qsrq) {
		this.qsrq = qsrq;
	}
	/**
	 * 获取：起始日期
	 */
	public Date getQsrq() {
		return qsrq;
	}
	/**
	 * 设置：截止日期
	 */
	public void setZjrq(Date zjrq) {
		this.zjrq = zjrq;
	}
	/**
	 * 获取：截止日期
	 */
	public Date getZjrq() {
		return zjrq;
	}
	
	public String getQsrqString() {
		if(qsrqString == null) {
			return DateUtils.format(qsrq);
		}
		return qsrqString;
	}
	public void setQsrqString(String qsrqString) {
		this.qsrqString = qsrqString;
	}
	public String getZjrqString() {
		if(zjrqString == null) {
			return DateUtils.format(zjrq);
		}
		return zjrqString;
	}
	public void setZjrqString(String zjrqString) {
		this.zjrqString = zjrqString;
	}
	
	
}
