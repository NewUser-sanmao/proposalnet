package com.bootdo.proposal.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-09 11:50:51
 */
public class JtwyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//集体委员管理
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
	//所属委员会
	private Integer sswyhid;
	//地区小组
	private Integer dqxzid;
	//负责人
	private String fzr;
	//单位名称
	private String dwmc;
	//联系人
	private String lxr;
	//通讯地址
	private String txdz;
	//邮编
	private String yb;
	//职务
	private String zw;
	//办公联系电话
	private String bglxdh;
	//手机号码
	private String sjhm;
	//数据类型
	private String sjlx;
	//对应的登录账号
	private Integer userid;
	
	private String userName;
	
	private String pwd;

	/**
	 * 设置：集体委员管理
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：集体委员管理
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
	 * 设置：所属委员会
	 */
	public void setSswyhid(Integer sswyhid) {
		this.sswyhid = sswyhid;
	}
	/**
	 * 获取：所属委员会
	 */
	public Integer getSswyhid() {
		return sswyhid;
	}
	/**
	 * 设置：地区小组
	 */
	public void setDqxzid(Integer dqxzid) {
		this.dqxzid = dqxzid;
	}
	/**
	 * 获取：地区小组
	 */
	public Integer getDqxzid() {
		return dqxzid;
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
	/**
	 * 设置：办公联系电话
	 */
	public void setBglxdh(String bglxdh) {
		this.bglxdh = bglxdh;
	}
	/**
	 * 获取：办公联系电话
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
	public void setSjlx(String sjlx) {
		this.sjlx = sjlx;
	}
	/**
	 * 获取：数据类型
	 */
	public String getSjlx() {
		return sjlx;
	}
	/**
	 * 设置：对应的登录账号
	 */
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	/**
	 * 获取：对应的登录账号
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
	
}
