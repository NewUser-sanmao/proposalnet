package com.bootdo.proposal.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-13 09:29:05
 */
public class GrwyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 个人委员 */
	public static Integer GRWY = 1;
	
	/** 集体委员 */
	public static Integer JTWY = 2;
	
	/** 承办单位 */
	public static Integer CBDW = 3;
	
	//个人委员
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
	//所属界次
	private Integer ssjcid;
	//所属界别
	private Integer ssjbid;
	//所属党派
	private Integer ssdpid;
	//所属专委会
	private Integer sswyhid;
	//所属地区
	private Integer ssdqid;
	//姓名
	private String xm;
	//性别
	private Integer xb;
	//生日_年
	private String srNian;
	//生日_月
	private String srYue;
	//
	private String jstx;
	//单位名称_职务
	private String dwmcZw;
	//通讯地址
	private String txdz;
	//邮编
	private String yb;
	//
	private String bglxdh;
	//手机号码
	private String sjhm;
	//数据类型
	private Integer sjlx;
	//对应登录Id
	private Integer userid;
	//类型 1个人委员 2集体委员
	private Integer type;
	
	//负责人
	private String fzr;
	//单位名称
	private String dwmc;
	//联系人
	private String lxr;
	//职务
	private String zw;
	//单位简称
	private String dwjc;
	//单位简称
	private Integer dwmcid;
	//身份证
	private String sfz;
	//职能职责
	private String znzz;
	
	//非数据库字段
	
	private String userName;
	
	private String pwd;
	
	private String parentName;
	
	/**
	 * 设置：个人委员
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：个人委员
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
	 * 设置：所属界次
	 */
	public void setSsjcid(Integer ssjcid) {
		this.ssjcid = ssjcid;
	}
	/**
	 * 获取：所属界次
	 */
	public Integer getSsjcid() {
		return ssjcid;
	}
	/**
	 * 设置：所属界别
	 */
	public void setSsjbid(Integer ssjbid) {
		this.ssjbid = ssjbid;
	}
	/**
	 * 获取：所属界别
	 */
	public Integer getSsjbid() {
		return ssjbid;
	}
	/**
	 * 设置：所属党派
	 */
	public void setSsdpid(Integer ssdpid) {
		this.ssdpid = ssdpid;
	}
	/**
	 * 获取：所属党派
	 */
	public Integer getSsdpid() {
		return ssdpid;
	}
	/**
	 * 设置：所属专委会
	 */
	public void setSswyhid(Integer sswyhid) {
		this.sswyhid = sswyhid;
	}
	/**
	 * 获取：所属专委会
	 */
	public Integer getSswyhid() {
		return sswyhid;
	}
	public void setSsdqid(Integer ssdqid) {
		this.ssdqid = ssdqid;
	}
	public Integer getSsdqid() {
		return ssdqid;
	}
	/**
	 * 设置：姓名
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * 获取：姓名
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * 设置：性别
	 */
	public void setXb(Integer xb) {
		this.xb = xb;
	}
	/**
	 * 获取：性别
	 */
	public Integer getXb() {
		return xb;
	}
	/**
	 * 设置：生日_年
	 */
	public void setSrNian(String srNian) {
		this.srNian = srNian;
	}
	/**
	 * 获取：生日_年
	 */
	public String getSrNian() {
		return srNian;
	}
	/**
	 * 设置：生日_月
	 */
	public void setSrYue(String srYue) {
		this.srYue = srYue;
	}
	/**
	 * 获取：生日_月
	 */
	public String getSrYue() {
		return srYue;
	}
	/**
	 * 设置：
	 */
	public void setJstx(String jstx) {
		this.jstx = jstx;
	}
	/**
	 * 获取：
	 */
	public String getJstx() {
		return jstx;
	}
	/**
	 * 设置：单位名称_职务
	 */
	public void setDwmcZw(String dwmcZw) {
		this.dwmcZw = dwmcZw;
	}
	/**
	 * 获取：单位名称_职务
	 */
	public String getDwmcZw() {
		return dwmcZw;
	}
	/**
	 * 设置：通讯地址
	 */
	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}
	/**
	 * 获取：通讯地址
	 */
	public String getTxdz() {
		return txdz;
	}
	/**
	 * 设置：邮编
	 */
	public void setYb(String yb) {
		this.yb = yb;
	}
	/**
	 * 获取：邮编
	 */
	public String getYb() {
		return yb;
	}
	/**
	 * 设置：
	 */
	public void setBglxdh(String bglxdh) {
		this.bglxdh = bglxdh;
	}
	/**
	 * 获取：
	 */
	public String getBglxdh() {
		return bglxdh;
	}
	/**
	 * 设置：手机号码
	 */
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	/**
	 * 获取：手机号码
	 */
	public String getSjhm() {
		return sjhm;
	}
	/**
	 * 设置：数据类型
	 */
	public void setSjlx(Integer sjlx) {
		this.sjlx = sjlx;
	}
	/**
	 * 获取：数据类型
	 */
	public Integer getSjlx() {
		return sjlx;
	}
	/**
	 * 设置：对应登录Id
	 */
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	/**
	 * 获取：对应登录Id
	 */
	public Integer getUserid() {
		return userid;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 设置：负责人
	 */
	public void setFzr(String fzr) {
		this.fzr = fzr;
	}
	/**
	 * 获取：负责人
	 */
	public String getFzr() {
		return fzr;
	}
	/**
	 * 设置：单位名称
	 */
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	/**
	 * 获取：单位名称
	 */
	public String getDwmc() {
		return dwmc;
	}
	/**
	 * 设置：联系人
	 */
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	/**
	 * 获取：联系人
	 */
	public String getLxr() {
		return lxr;
	}
	/**
	 * 设置：职务
	 */
	public void setZw(String zw) {
		this.zw = zw;
	}
	/**
	 * 获取：职务
	 */
	public String getZw() {
		return zw;
	}
	public String getDwjc() {
		return dwjc;
	}
	public void setDwjc(String dwjc) {
		this.dwjc = dwjc;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getDwmcid() {
		return dwmcid;
	}
	public void setDwmcid(Integer dwmcid) {
		this.dwmcid = dwmcid;
	}
	
	public String getSfz() {
		return sfz;
	}
	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	public String getZnzz() {
		return znzz;
	}
	public void setZnzz(String znzz) {
		this.znzz = znzz;
	}
	
	
}
