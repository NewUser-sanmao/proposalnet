package com.bootdo.proposal.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 提案信息
 * 
 * @author shipan
 * @email 
 * @date 2018-10-17 14:16:06
 */
public class TaxxDO implements Serializable {
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
	//流水号
	private String lsh;
	//提案号
	private String tah;
	//政协界次
	private Integer zxjcid;
	//提案类型 1个人 2集体
	private Integer tanx;
	//提案题目
	private String tatm;
	//建议承办单位
	private Integer cbdwid;
	//提案类别
	private Integer lbyblsxid;
	//是否经过调研
	private String isjgdy;
	//是否本人撰写
	private String isbrzx;
	//是否转呈他人材料
	private String iszctrcl;
	//是否公开提案内容
	private String isgktanr;
	//提案者
	private String taz;
	//工作单位及职务
	private String gzdwjzw;
	//通讯地址
	private String txdz;
	//邮编
	private String yb;
	//联系电话
	private String lxdh;
	//所属专委
	private Integer sswyhid;
	//附议提案者
	private String fytaz;
	//提案内容
	private String tanr;
	//建议协商方式
	private String jyxsfsid;
	//提案状态
	private Integer tastate;//0已提交未审核 1预审通过 2复审通过 3终审通过 4已转交 
	//立案状态
	private Integer lastate;//0不立案 1立案 2不立案转意见 3不立案退回
	//单位名称
	private String dwmc;
	//执笔人
	private String zbr;
	//单位负责人
	private String dwfzr;
	//联系人
	private String lxr;
	//电子邮箱
	private String dzyx;
	//立案类型1独办 2分半 3会办
	private Integer latype;
	//是否并案
	private Integer isba;//是否并案为1,同时并案父id为null则说明是主并
	//并案父id
	private Integer baid;
	//是否是重点提案0不是重点 1是重点
	private Integer iszdta;
	//是否公开 0不公开 1公开
	private Integer isgk;
	//分类id 与提案类型关联的表相同
	private Integer flid;
	//领导意见
	private String ldyj;
	//退案理由
	private Integer taly;
	//是否公开(提案者选择) 0不公开 1公开
	private Integer isgkta;
	//基本情况
	private String jbqk;
	//存在问题
	private String czwt;
	//对策建议
	private String dcjy;
	//与转交申诉建议
	private String yzjssjy;
	//是否是优秀提案0不是优秀 1是优秀
	private Integer isYxta;
	//其它说明
	private String qtsm;
	//附加名称 每个用,隔开
	private String fjmc;
	//附件地址 每个用,隔开
	private String fjdz;
	//承办单位进度
	private Integer cbdwjd;

	public Integer getCbdwjd() {
		return cbdwjd;
	}

	public void setCbdwjd(Integer cbdwjd) {
		this.cbdwjd = cbdwjd;
	}


	
	
	//非数据库字段
	private String cbdw;
	private String fbdw;
	private String xbdw;
	private String mc;
	private String str1;
	private Integer xxCount;
	private String yjdz;//意见复函地址
	private String yjmc;//意见复函名称
	private String hzdz;//正式复函回执地址
	private String hzmc;//正式复函回执名称
	private Integer pfCount;//评分数量
	private Integer gbsqCount;//评分数量
	private String zxjcmc;//政协届次
	private Integer cbdwjd1;//承办单位进度接受字段

	public Integer getCbdwjd1() {
		return cbdwjd1;
	}

	public void setCbdwjd1(Integer cbdwjd1) {
		this.cbdwjd1 = cbdwjd1;
	}


	
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
	 * 设置：流水号
	 */
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
	/**
	 * 获取：流水号
	 */
	public String getLsh() {
		return lsh;
	}
	/**
	 * 设置：提案号
	 */
	public void setTah(String tah) {
		this.tah = tah;
	}
	/**
	 * 获取：提案号
	 */
	public String getTah() {
		return tah;
	}
	/**
	 * 设置：政协界次
	 */
	public void setZxjcid(Integer zxjcid) {
		this.zxjcid = zxjcid;
	}
	/**
	 * 获取：政协界次
	 */
	public Integer getZxjcid() {
		return zxjcid;
	}
	/**
	 * 设置：提案类型
	 */
	public void setTanx(Integer tanx) {
		this.tanx = tanx;
	}
	/**
	 * 获取：提案类型
	 */
	public Integer getTanx() {
		return tanx;
	}
	/**
	 * 设置：提案题目
	 */
	public void setTatm(String tatm) {
		this.tatm = tatm;
	}
	/**
	 * 获取：提案题目
	 */
	public String getTatm() {
		return tatm;
	}
	/**
	 * 设置：建议承办单位
	 */
	public void setCbdwid(Integer cbdwid) {
		this.cbdwid = cbdwid;
	}
	/**
	 * 获取：建议承办单位
	 */
	public Integer getCbdwid() {
		return cbdwid;
	}
	/**
	 * 设置：提案类别
	 */
	public void setLbyblsxid(Integer lbyblsxid) {
		this.lbyblsxid = lbyblsxid;
	}
	/**
	 * 获取：提案类别
	 */
	public Integer getLbyblsxid() {
		return lbyblsxid;
	}
	/**
	 * 设置：是否经过调研
	 */
	public void setIsjgdy(String isjgdy) {
		this.isjgdy = isjgdy;
	}
	/**
	 * 获取：是否经过调研
	 */
	public String getIsjgdy() {
		return isjgdy;
	}
	/**
	 * 设置：是否本人撰写
	 */
	public void setIsbrzx(String isbrzx) {
		this.isbrzx = isbrzx;
	}
	/**
	 * 获取：是否本人撰写
	 */
	public String getIsbrzx() {
		return isbrzx;
	}
	/**
	 * 设置：是否转呈他人材料
	 */
	public void setIszctrcl(String iszctrcl) {
		this.iszctrcl = iszctrcl;
	}
	/**
	 * 获取：是否转呈他人材料
	 */
	public String getIszctrcl() {
		return iszctrcl;
	}
	/**
	 * 设置：是否公开提案内容
	 */
	public void setIsgktanr(String isgktanr) {
		this.isgktanr = isgktanr;
	}
	/**
	 * 获取：是否公开提案内容
	 */
	public String getIsgktanr() {
		return isgktanr;
	}
	/**
	 * 设置：提案者
	 */
	public void setTaz(String taz) {
		this.taz = taz;
	}
	/**
	 * 获取：提案者
	 */
	public String getTaz() {
		return taz;
	}
	/**
	 * 设置：工作单位及职务
	 */
	public void setGzdwjzw(String gzdwjzw) {
		this.gzdwjzw = gzdwjzw;
	}
	/**
	 * 获取：工作单位及职务
	 */
	public String getGzdwjzw() {
		return gzdwjzw;
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
	 * 设置：联系电话
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * 获取：联系电话
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * 设置：所属专委
	 */
	public void setSswyhid(Integer sswyhid) {
		this.sswyhid = sswyhid;
	}
	/**
	 * 获取：所属专委
	 */
	public Integer getSswyhid() {
		return sswyhid;
	}
	/**
	 * 设置：附议提案者
	 */
	public void setFytaz(String fytaz) {
		this.fytaz = fytaz;
	}
	/**
	 * 获取：附议提案者
	 */
	public String getFytaz() {
		return fytaz;
	}
	/**
	 * 设置：提案内容
	 */
	public void setTanr(String tanr) {
		this.tanr = tanr;
	}
	/**
	 * 获取：提案内容
	 */
	public String getTanr() {
		return tanr;
	}
	/**
	 * 设置：建议协商方式
	 */
	public void setJyxsfsid(String jyxsfsid) {
		this.jyxsfsid = jyxsfsid;
	}
	/**
	 * 获取：建议协商方式
	 */
	public String getJyxsfsid() {
		return jyxsfsid;
	}
	/**
	 * 设置：提案状态
	 */
	public void setTastate(Integer tastate) {
		this.tastate = tastate;
	}
	/**
	 * 获取：提案状态
	 */
	public Integer getTastate() {
		return tastate;
	}
	/**
	 * 设置：立案状态
	 */
	public void setLastate(Integer lastate) {
		this.lastate = lastate;
	}
	/**
	 * 获取：立案状态
	 */
	public Integer getLastate() {
		return lastate;
	}
	public String getDwmc() {
		return dwmc;
	}
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	public String getZbr() {
		return zbr;
	}
	public void setZbr(String zbr) {
		this.zbr = zbr;
	}
	public String getDwfzr() {
		return dwfzr;
	}
	public void setDwfzr(String dwfzr) {
		this.dwfzr = dwfzr;
	}
	public String getLxr() {
		return lxr;
	}
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	public String getDzyx() {
		return dzyx;
	}
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	
	/**
	 * 设置：立案类型1独办 2分半 3会办
	 */
	public void setLatype(Integer latype) {
		this.latype = latype;
	}
	/**
	 * 获取：立案类型1独办 2分半 3会办
	 */
	public Integer getLatype() {
		return latype;
	}
	/**
	 * 设置：是否并案
	 */
	public void setIsba(Integer isba) {
		this.isba = isba;
	}
	/**
	 * 获取：是否并案
	 */
	public Integer getIsba() {
		return isba;
	}
	/**
	 * 设置：并案父id
	 */
	public void setBaid(Integer baid) {
		this.baid = baid;
	}
	/**
	 * 获取：并案父id
	 */
	public Integer getBaid() {
		return baid;
	}
	/**
	 * 设置：是否是重点提案0不是重点 1是重点
	 */
	public void setIszdta(Integer iszdta) {
		this.iszdta = iszdta;
	}
	/**
	 * 获取：是否是重点提案0不是重点 1是重点
	 */
	public Integer getIszdta() {
		return iszdta;
	}
	/**
	 * 设置：是否公开 0不公开 1公开
	 */
	public void setIsgk(Integer isgk) {
		this.isgk = isgk;
	}
	/**
	 * 获取：是否公开 0不公开 1公开
	 */
	public Integer getIsgk() {
		return isgk;
	}
	/**
	 * 设置：分类id 与提案类型关联的表相同
	 */
	public void setFlid(Integer flid) {
		this.flid = flid;
	}
	/**
	 * 获取：分类id 与提案类型关联的表相同
	 */
	public Integer getFlid() {
		return flid;
	}
	/**
	 * 设置：领导意见
	 */
	public void setLdyj(String ldyj) {
		this.ldyj = ldyj;
	}
	/**
	 * 获取：领导意见
	 */
	public String getLdyj() {
		return ldyj;
	}
	public Integer getTaly() {
		return taly;
	}
	public void setTaly(Integer taly) {
		this.taly = taly;
	}
	
	public String getCbdw() {
		return cbdw;
	}
	public void setCbdw(String cbdw) {
		this.cbdw = cbdw;
	}
	public String getFbdw() {
		return fbdw;
	}
	public void setFbdw(String fbdw) {
		this.fbdw = fbdw;
	}
	public String getXbdw() {
		return xbdw;
	}
	public void setXbdw(String xbdw) {
		this.xbdw = xbdw;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public Integer getIsgkta() {
		return isgkta;
	}
	public void setIsgkta(Integer isgkta) {
		this.isgkta = isgkta;
	}
	public String getJbqk() {
		return jbqk;
	}
	public void setJbqk(String jbqk) {
		this.jbqk = jbqk;
	}
	public String getCzwt() {
		return czwt;
	}
	public void setCzwt(String czwt) {
		this.czwt = czwt;
	}
	public String getDcjy() {
		return dcjy;
	}
	public void setDcjy(String dcjy) {
		this.dcjy = dcjy;
	}
	public String getYzjssjy() {
		return yzjssjy;
	}
	public void setYzjssjy(String yzjssjy) {
		this.yzjssjy = yzjssjy;
	}
	public Integer getIsYxta() {
		return isYxta;
	}
	public void setIsYxta(Integer isYxta) {
		this.isYxta = isYxta;
	}
	public String getQtsm() {
		return qtsm;
	}
	public void setQtsm(String qtsm) {
		this.qtsm = qtsm;
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
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	public Integer getXxCount() {
		return xxCount;
	}
	public void setXxCount(Integer xxCount) {
		this.xxCount = xxCount;
	}
	public String getYjdz() {
		return yjdz;
	}
	public void setYjdz(String yjdz) {
		this.yjdz = yjdz;
	}
	public String getYjmc() {
		return yjmc;
	}
	public void setYjmc(String yjmc) {
		this.yjmc = yjmc;
	}
	public String getHzdz() {
		return hzdz;
	}
	public void setHzdz(String hzdz) {
		this.hzdz = hzdz;
	}
	public String getHzmc() {
		return hzmc;
	}
	public void setHzmc(String hzmc) {
		this.hzmc = hzmc;
	}
	public Integer getPfCount() {
		return pfCount;
	}
	public void setPfCount(Integer pfCount) {
		this.pfCount = pfCount;
	}
	public Integer getGbsqCount() {
		return gbsqCount;
	}
	public void setGbsqCount(Integer gbsqCount) {
		this.gbsqCount = gbsqCount;
	}
	public String getZxjcmc() {
		return zxjcmc;
	}
	public void setZxjcmc(String zxjcmc) {
		this.zxjcmc = zxjcmc;
	}
	
	
	
}
