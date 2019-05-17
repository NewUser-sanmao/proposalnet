package com.bootdo.proposal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.R;
import com.bootdo.proposal.domain.CbdwDO;
import com.bootdo.proposal.domain.FhhzDO;
import com.bootdo.proposal.domain.GrwyDO;
import com.bootdo.proposal.domain.GzfwDO;
import com.bootdo.proposal.domain.LxEnumDO;
import com.bootdo.proposal.domain.LzFshzDO;
import com.bootdo.proposal.domain.OpenReturnDO;
import com.bootdo.proposal.domain.TaxxCbdwDO;
import com.bootdo.proposal.domain.TaxxDO;
import com.bootdo.proposal.domain.XxDO;
import com.bootdo.proposal.domain.ZxjcDO;
import com.bootdo.proposal.service.CbdwService;
import com.bootdo.proposal.service.FhhzService;
import com.bootdo.proposal.service.GrwyService;
import com.bootdo.proposal.service.GzfwService;
import com.bootdo.proposal.service.LbyblsxService;
import com.bootdo.proposal.service.LxEnumService;
import com.bootdo.proposal.service.LzFshzService;
import com.bootdo.proposal.service.TaxxCbdwService;
import com.bootdo.proposal.service.TaxxService;
import com.bootdo.proposal.service.XxService;
import com.bootdo.proposal.service.ZxjcService;
import com.bootdo.proposal.util.CqnewsHttpUtil;
import com.bootdo.proposal.util.LxEnumMapUtil;
import com.bootdo.proposal.util.StringUtil;
import com.bootdo.proposal.util.TokenUtil;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-10-19 14:22:10
 */
 
@Controller
@RequestMapping("/kaifang")
public class OpenController {
	@Autowired
	private LbyblsxService lbyblsxService;
	@Autowired
	private TaxxService taxxService;
	@Autowired
	private GrwyService grwyService;
	@Autowired
	private CbdwService cbdwService;
	@Autowired
	private LxEnumService lxEnumService;
	@Autowired
	private ZxjcService zxjcService;
	@Autowired
	private TaxxCbdwService taxxCbdwService;
	@Autowired
	private FhhzService fhhzService;
	@Autowired
	private XxService xxService;
	@Autowired
	private UserService userService;
	@Autowired
	private GzfwService gzfwService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private LzFshzService lzFshzService;
	/**
	 * 提案类型
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/talx")
	public Object talx(@RequestParam Map<String, Object> params){
		
		Map<String,Object> retMap = new HashMap<>();
		if(StringUtil.judgeObjectsIsNull(params.get("pageNum"),params.get("pageSize"))) {
			return new OpenReturnDO("1","pageNum|pageSize参数值为空");
		}
		
		int num = Integer.parseInt(params.get("pageNum").toString());
		int size = Integer.parseInt(params.get("pageSize").toString());
		
		params.put("offset", (num-1)*size);
		params.put("limit", size);
		
//		offset limit
		
		//查询列表数据
		List<Map<String,Object>> l = lbyblsxService.getListJK(params);
		retMap.put("resultList", l);
		
		return new OpenReturnDO(retMap);
	}
	
	/**
	 * 用户查询
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/yhcx")
	public Object yhcx(@RequestParam Map<String, Object> params){
		
		Map<String,Object> retMap = new HashMap<>();
		if(StringUtil.judgeObjectsIsNull(params.get("strUserName"),params.get("strPhone"))) {
			return new OpenReturnDO("1","strUserName|strPhone参数值为空");
		}
		
		params.put("xm", params.get("strUserName"));
		params.put("sjhm", params.get("strPhone"));
		List<GrwyDO> l1 = grwyService.list(params);
		if(l1!=null && l1.size()>0) {
			retMap.put("strProposalUserId", l1!=null && l1.size()>0 ? l1.get(0).getUserid() : null);
			return new OpenReturnDO(retMap);
		}
		
		params.put("xm", params.get("strUserName"));
		params.put("sfz", params.get("strUserIdCard"));
		List<GrwyDO> l2 = grwyService.list(params);
		if(l2!=null && l2.size()>0) {
			retMap.put("strProposalUserId", l2!=null && l2.size()>0 ? l2.get(0).getUserid() : null);
			return new OpenReturnDO(retMap);
		}
		
		params.put("sjhm", params.get("strUserName"));
		params.put("sfz", params.get("strUserIdCard"));
		List<GrwyDO> l3 = grwyService.list(params);
		if(l3!=null && l3.size()>0) {
			retMap.put("strProposalUserId", l3!=null && l3.size()>0 ? l3.get(0).getUserid() : null);
			return new OpenReturnDO(retMap);
		}
		
		return new OpenReturnDO(null);
	}
	
	/**
	 * 单位列表
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/dwlb")
	public Object dwlb(@RequestParam Map<String, Object> params){
		
		Map<String,Object> retMap = new HashMap<>();
//		if(StringUtil.judgeObjectsIsNull(params.get("pageNum"),params.get("pageSize"))) {
//			return new OpenReturnDO("参数值为空");
//		}
		
		/*int num = Integer.parseInt(params.get("pageNum").toString());
		int size = Integer.parseInt(params.get("pageSize").toString());
		
		params.put("offset", (num-1)*size);
		params.put("limit", size);*/
		
		if(params.get("keyword")!=null && !"".equals(params.get("keyword").toString().trim())) {
			params.put("name", "%"+params.get("keyword")+"%");
		}
		
		//查询列表数据
		List<CbdwDO> cbdwList = cbdwService.list(params);
		int intTotalRecord = cbdwService.count(params);
		LinkedHashMap<Integer, List<CbdwDO>> map = new LinkedHashMap<>();
		if(cbdwList!=null && cbdwList.size() > 0) {
			for(CbdwDO c : cbdwList) {
				List<CbdwDO> l = map.get(c.getParentId());
				if(l == null) {
					l = new ArrayList<>();
				}
				l.add(c);
				map.put(c.getParentId(), l);
			}
			List<Map<String,Object>> r = getTreeview(map, 0);
			
			retMap.put("resultList", r);
		}
//		int intTotalPage = 0;
//		if(intTotalRecord%size == 0) {
//			intTotalPage = intTotalRecord/size;
//		}else {
//			intTotalPage = (intTotalRecord/size)+1;
//		}
//		retMap.put("intCurrentPage", params.get("pageNum"));
//		retMap.put("intPageSize", params.get("pageSize"));
//		retMap.put("intTotalPage", intTotalPage);//总页数
//		retMap.put("intTotalRecord", intTotalRecord);//总条数
		
		return new OpenReturnDO(retMap);
	}
	
	private List<Map<String,Object>> getTreeview(LinkedHashMap<Integer, List<CbdwDO>> map, Integer parentId){
		List<Map<String,Object>> ls_list = null;
		if(map.get(parentId) != null) {
			ls_list = new ArrayList<>();
			List<CbdwDO> l = map.get(parentId);
			for(CbdwDO c : l) {
				Map<String,Object> m = new HashMap<>();
				m.put("id", c.getId());
				m.put("unitName", c.getName());
				m.put("fid", parentId);
				if(map.get(c.getId()) != null) {
					List<Map<String, Object>> childList = getTreeview(map, c.getId());
					m.put("childList", childList);
				}
				ls_list.add(m);
			}
		}
		return ls_list;
	}
	
	/**
	 * 提案类型列表
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/talxlb")
	public Object talxlb(@RequestParam Map<String, Object> params){
		
		Map<String,Object> retMap = new HashMap<>();
		//查询列表数据
		Map<String,Object> map = new HashMap<>();
		map.put("type", "1");
		List<LxEnumDO> list = lxEnumService.list(map);
		List<Map<String,Object>> lMap = new ArrayList<>();
		if(list!=null && list.size()>0) {
			for(LxEnumDO le : list){
				Map<String,Object> ls_map = new HashMap<>();
				ls_map.put("proposalType", le.getMark());
				ls_map.put("proposalTypeName", le.getName());
				lMap.add(ls_map);
			}
		}
		
		retMap.put("resultList", lMap);
		
		return new OpenReturnDO(retMap);
	}
	
	/**
	 * 提案状态列表
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/taztlb")
	public Object taztlb(@RequestParam Map<String, Object> params){
		
		Map<String,Object> retMap = new HashMap<>();
		//查询列表数据
		Map<String,Object> map = new HashMap<>();
		map.put("type", "2");
		List<LxEnumDO> list = lxEnumService.list(map);
		List<Map<String,Object>> lMap = new ArrayList<>();
		if(list!=null && list.size()>0) {
			for(LxEnumDO le : list){
				Map<String,Object> ls_map = new HashMap<>();
				ls_map.put("proposalStatus", le.getMark());
				ls_map.put("proposalStatusName", le.getName());
				lMap.add(ls_map);
			}
		}
		
		retMap.put("resultList", lMap);
		
		return new OpenReturnDO(retMap);
	}
	
	/**
	 * 提案立案状态列表
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/talaztlb")
	public Object talaztlb(@RequestParam Map<String, Object> params){
		
		Map<String,Object> retMap = new HashMap<>();
		//查询列表数据
		Map<String,Object> map = new HashMap<>();
		map.put("type", "3");
		List<LxEnumDO> list = lxEnumService.list(map);
		List<Map<String,Object>> lMap = new ArrayList<>();
		if(list!=null && list.size()>0) {
			for(LxEnumDO le : list){
				Map<String,Object> ls_map = new HashMap<>();
				ls_map.put("filingStatus", le.getMark());
				ls_map.put("filingStatusName", le.getName());
				lMap.add(ls_map);
			}
		}
		
		retMap.put("resultList", lMap);
		
		return new OpenReturnDO(retMap);
	}
	
	/**
	 * 提案会议届次列表
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/tahyjclb")
	public Object tahyjclb(@RequestParam Map<String, Object> params){
		
		
		Map<String,Object> retMap = new HashMap<>();
		if(StringUtil.judgeObjectsIsNull(params.get("pageNum"),params.get("pageSize"))) {
			return new OpenReturnDO("1","pageNum|pageSize参数值为空");
		}
		
		int num = Integer.parseInt(params.get("pageNum").toString());
		int size = Integer.parseInt(params.get("pageSize").toString());
		
		params.put("offset", (num-1)*size);
		params.put("limit", size);
		
//		if(params.get("isExpired")!=null && !"".equals(params.get("isExpired").toString().trim())) {
//			params.put("isExpired", params.get("isExpired"));
//		}
		
		List<ZxjcDO> list = zxjcService.list(params);
		int intTotalRecord = zxjcService.count(params);
		List<Map<String,Object>> lMap = new ArrayList<>();
		if(list!=null && list.size()>0) {
			for(ZxjcDO z : list) {
				Map<String,Object> map = new HashMap<>();
				map.put("strSessionId", z.getJid());
				map.put("strSessionName", z.getJmc());
				map.put("strSessionMeetId", z.getId());
				map.put("strSessionMeetName", z.getZxjcmc());
				map.put("startDateTime",StringUtil.getCurrentDate(z.getQsrq(), "yyyy-MM-dd HH:mm:ss"));
				map.put("endDateTime", StringUtil.getCurrentDate(z.getZjrq(), "yyyy-MM-dd HH:mm:ss"));
				lMap.add(map);
			}
		}
		
		int intTotalPage = 0;
		if(intTotalRecord%size == 0) {
			intTotalPage = intTotalRecord/size;
		}else {
			intTotalPage = (intTotalRecord/size)+1;
		}
		
		retMap.put("resultList", lMap);
		retMap.put("intCurrentPage", params.get("pageNum"));
		retMap.put("intPageSize", params.get("pageSize"));
		retMap.put("intTotalPage", intTotalPage);//总页数
		retMap.put("intTotalRecord", intTotalRecord);//总条数
		
		return new OpenReturnDO(retMap);
	}
	
	/**
	 * 履职档案详细接口
	 */
	@ResponseBody
	@RequestMapping("/lzdaxxjk")
	public Object lzdaxxjk(@RequestParam Map<String, Object> params){
		
		if(StringUtil.judgeObjectsIsNull(params.get("strProposalUserId"))) {
			return new OpenReturnDO("1","strProposalUserId参数值为空");
		}
		Map<String,Object> map = new HashMap<>();
		map.put("createid", params.get("strProposalUserId"));
		if(!StringUtil.judgeObjectsIsNull(params.get("strStartYear"))) {
			map.put("strStartYear", params.get("strStartYear"));
		}
		if(!StringUtil.judgeObjectsIsNull(params.get("strEndYear"))) {
			map.put("strEndYear", params.get("strEndYear"));
		}
		
		List<TaxxDO> list = taxxService.list(map);
		List<Map<String,Object>> lMap = new ArrayList<>();
		int writeNum=0,personalNum=0,unionNum=0,filingNum=0,importantNum=0,perfectNum=0,leaderInstructionNum=0;
		
		if(list!=null && list.size()>0) {
			writeNum = list.size();
			for(TaxxDO td : list) {
				Map<String,Object> ls_map = new HashMap<>();
				ls_map.put("id", td.getId());
				ls_map.put("title", td.getTatm());
				
				if(td.getTanx().intValue()==1) {
					personalNum++;
				}else if(td.getTanx().intValue()==2) {
					unionNum++;
				}
				ls_map.put("proposalType", td.getTanx());//提案类型
				ls_map.put("proposalTypeName", LxEnumMapUtil.talx.get(td.getTanx()));
				
				if(td.getLastate()!=null && td.getLastate().intValue() == 1) {
					filingNum++;
				}
				ls_map.put("isFiling", td.getLastate()==null ? 0 : td.getLastate());//是否立案
				ls_map.put("isFilingName", LxEnumMapUtil.talazt.get(td.getLastate()));
				
				if(td.getIsYxta()!=null && td.getIsYxta().intValue() == 1) {
					perfectNum++;
				}
				ls_map.put("isPerfect", td.getIsYxta()!=null ? td.getIsYxta() : 0);//是否优秀提案
				ls_map.put("isPerfectName", td.getIsYxta()!=null&&td.getIsYxta().intValue()==1 ? "是" : "否");
				
				if(td.getIszdta()!=null && td.getIszdta().intValue() == 1) {
					importantNum++;
				}
				ls_map.put("isImportant", td.getIszdta()!=null ? td.getIszdta() : 0);//是否重点提案
				ls_map.put("isImportantName", td.getIszdta()!=null&&td.getIszdta().intValue()==1 ? "是" : "否");
				
				if(td.getLdyj()!=null && !"".equals(td.getLdyj().trim())) {
					leaderInstructionNum++;
				}
				ls_map.put("isLeaderInstruction", td.getLdyj()!=null && !"".equals(td.getLdyj().trim()) ? 1 : 0);//领导是否批示
				ls_map.put("isLeaderInstructionName", td.getLdyj()!=null && !"".equals(td.getLdyj().trim()) ? "是" : "否");
				lMap.add(ls_map);
			}
		}
		
		map.clear();
		map.put("userid", params.get("strProposalUserId"));
		if(!StringUtil.judgeObjectsIsNull(params.get("strStartYear"))) {
			map.put("sDate", params.get("strStartYear"));
		}
		if(!StringUtil.judgeObjectsIsNull(params.get("strEndYear"))) {
			map.put("eDate", params.get("strEndYear"));
		}
		
		writeNum = 0;
		personalNum = 0;
		unionNum = 0;
		filingNum = 0;
		importantNum = 0;
		perfectNum = 0;
		leaderInstructionNum = 0;
		int totalBasicScore = 0, totaAdditionalScore = 0;
		
		List<LzFshzDO> listLf = lzFshzService.list(map);
		if(listLf!=null && listLf.size()>0) {
			for(LzFshzDO l : listLf) {
				writeNum += l.getPersonalnum();
				writeNum += l.getUnionnum();
				filingNum += l.getFilingnum();
				importantNum += l.getImportantnum();
				perfectNum += l.getPerfectnum();
				leaderInstructionNum += l.getLeaderinstructionnum();
				if(writeNum>0) {
					totalBasicScore += 15;
				}
				totaAdditionalScore += l.getAdditionalpoints();
			}
		}
		
		Map<String, Object> statistics = new HashMap<>();
		statistics.put("writeNum", writeNum);//撰写提案数
		statistics.put("personalNum", personalNum);//个人提案数
		statistics.put("unionNum", unionNum);//集体/联名提案数
		statistics.put("filingNum", filingNum);//立案数量
		statistics.put("importantNum", importantNum);//重点提案数量
		statistics.put("perfectNum", perfectNum);//优秀提案数量
		statistics.put("leaderInstructionNum", leaderInstructionNum);//得到领导批示数量
		statistics.put("totalBasicScore", totalBasicScore);//合计得分
		statistics.put("totaAdditionalScore", totaAdditionalScore);//合计加分
		Map<String,Object> retMap = new HashMap<>();
		retMap.put("resultList", lMap);
		retMap.put("statistics", statistics);
		
		return new OpenReturnDO(retMap);
	}
	
	/**
	 * 提案列表,根据条件查询
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/talb")
	public Object talb(@RequestParam Map<String, Object> params){
		
		if(StringUtil.judgeObjectsIsNull(params.get("pageNum"),params.get("pageSize"))) {
			return new OpenReturnDO("1","pageNum|pageSize参数值为空");
		}
		
		int num = Integer.parseInt(params.get("pageNum").toString());
		int size = Integer.parseInt(params.get("pageSize").toString());
		
		Map<String,Object> map = new HashMap<>();
		map.put("offset", (num-1)*size);
		map.put("limit", size);
		
		map.put("createid", params.get("strProposalUserId"));//提案人
		if(params.get("keyword")!=null && !"".equals(params.get("keyword").toString().trim())) {
			map.put("tatm", "%"+params.get("keyword")+"%");//提案题目
		}
		map.put("tanx", params.get("proposalType"));//提案类型
		map.put("tastate", params.get("proposalStatus"));//提案状态
		map.put("lastate", params.get("filingStatus"));//立案状态
		map.put("jid", params.get("strSessionId"));//届id
		map.put("zxjcid", params.get("strSessionMeetId"));//届次id
		
		List<TaxxDO> list = taxxService.list(map);
		int intTotalRecord = taxxService.count(map);
		List<Map<String,Object>> lMap = new ArrayList<>();
		for(TaxxDO t : list) {
			Map<String,Object> ls_map = new HashMap<>();
			ls_map.put("flowId", t.getLsh());
			ls_map.put("id", t.getId());
			ls_map.put("title", t.getTatm());
			ls_map.put("proposalType", t.getTanx());
			ls_map.put("proposalTypeName", LxEnumMapUtil.talx.get(t.getTanx()));
			ls_map.put("createTime", StringUtil.getCurrentDate(t.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
			ls_map.put("proposalStatus", t.getTastate());
			ls_map.put("proposalStatusName", LxEnumMapUtil.tazt.get(t.getTastate()));
			ls_map.put("filingStatus", t.getLastate());
			ls_map.put("filingStatusName", LxEnumMapUtil.talazt.get(t.getLastate()));
			ls_map.put("undertakeUnitName", t.getCbdw());
			ls_map.put("coUnitName", t.getXbdw());
			ls_map.put("supportingUnitName", t.getFbdw());
			lMap.add(ls_map);
		}
		Map<String,Object> retMap = new HashMap<>();
		
		int intTotalPage = 0;
		if(intTotalRecord%size == 0) {
			intTotalPage = intTotalRecord/size;
		}else {
			intTotalPage = (intTotalRecord/size)+1;
		}
		
		retMap.put("resultList", lMap);
		retMap.put("intCurrentPage", params.get("pageNum"));
		retMap.put("intPageSize", params.get("pageSize"));
		retMap.put("intTotalPage", intTotalPage);//总页数
		retMap.put("intTotalRecord", intTotalRecord);//总条数
		
		return new OpenReturnDO(retMap);
	}
	
	/**
	 * 提案详情查看
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/taxqck")
	public Object taxqck(@RequestParam Map<String, Object> params){
		
		if(StringUtil.judgeObjectsIsNull(params.get("id"))) {
			return new OpenReturnDO("1","id参数值为空");
		}
		
		TaxxDO taxx = taxxService.get(Integer.parseInt(params.get("id").toString()));
		GrwyDO gr = grwyService.getByUserId(taxx.getCreateid());
		Map<String,Object> retMap = new HashMap<>();
		retMap.put("id", taxx.getId());//提案id
		retMap.put("title", taxx.getTatm());//提案标题
		retMap.put("proposalType", taxx.getTanx());//提案类型
		retMap.put("proposalTypeName", LxEnumMapUtil.talx.get(taxx.getTanx()));//提案类型
		retMap.put("suggestUndertakeUnit", taxx.getCbdwid());//建议承办单位ID
//		retMap.put("suggestNegotiationType", value);//建议协商方式
		retMap.put("isResearch", taxx.getIsjgdy());//是否经过调研
		retMap.put("isSelfWrite", taxx.getIsbrzx());//是否本人撰写
		retMap.put("isTransfer", taxx.getIszctrcl());//是否转呈他人材料
		retMap.put("isPublic", taxx.getIsgk());//是否公开
		retMap.put("strProposalUserId", taxx.getCreateid());//提案用户ID
		retMap.put("strProposalUserUserName", gr.getXm());//提案用户姓名
//		retMap.put("strSeconderProposalUserId", value);//复议提案用户ID
		retMap.put("strSeconderProposalUserName", taxx.getFytaz());//复议提案用户姓名
		retMap.put("mainContent", taxx.getTanr() == null ? "" : taxx.getTanr());//问题现状
		retMap.put("causeAnalysisContent", "");//原因分析
		retMap.put("adviceContent", "");//建议措施
		retMap.put("attachedFileUrls", "/files/fj/6b8457f9-8dbc-42c7-9fb0-68c3c251a2b0.docx");//附件地址
		retMap.put("attachedFileNames", "aaaa.docx");//附件名称
		retMap.put("createTime", StringUtil.getCurrentDate(taxx.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));//提案时间
		retMap.put("proposalStatus", taxx.getTastate());//提案状态
		retMap.put("proposalStatusName", LxEnumMapUtil.tazt.get(taxx.getTastate()));//提案状态
		retMap.put("filingStatus", taxx.getLastate());//立案状态
		retMap.put("filingStatusName", LxEnumMapUtil.talazt.get(taxx.getLastate()));//立案状态
		
		Map<String,Object> map = new HashMap<>();
		map.put("taxxid", taxx.getId());
		List<Map<String, Object>> cbdwGroupConcat = taxxService.getCbdwGroupConcat(map);
		retMap.put("undertakeUnitId", null);//承办单位Id
		retMap.put("undertakeUnitName", null);//承办单位
		retMap.put("coUnitId", null);//协办单位Id
		retMap.put("coUnitName", null);//协办单位
		retMap.put("supportingUnitId", null);//分办单位Id
		retMap.put("supportingUnitName", null);//分办单位
		if(cbdwGroupConcat!=null && cbdwGroupConcat.size()>0) {
			for(Map<String, Object> m : cbdwGroupConcat) {
				if(m.get("type") != null && "1".equals(m.get("type").toString())) {//承办
					retMap.put("undertakeUnitId", m.get("cbdwId"));//承办单位Id
					retMap.put("undertakeUnitName", m.get("name"));//承办单位
				}else if(m.get("type") != null && "2".equals(m.get("type").toString())) {//分半
					retMap.put("coUnitId", m.get("cbdwId"));//协办单位Id
					retMap.put("coUnitName", m.get("name"));//协办单位
				}else if(m.get("type") != null && "3".equals(m.get("type").toString())) {//协办
					retMap.put("supportingUnitId", m.get("cbdwId"));//分办单位Id
					retMap.put("supportingUnitName", m.get("name"));//分办单位
				}
			}
		}
		
		//意见函列表 opinionList
		Map<String,Object> fhhzMap = new HashMap<>();
		fhhzMap.put("taxxid", taxx.getId());
		fhhzMap.put("type", 1);
		List<Map<String,Object>> list1 = fhhzService.listMap(fhhzMap);
		List<Map<String,Object>> opinionList = new ArrayList<>();
		if(list1!=null && list1.size()>0) {
			for(Map<String,Object> f : list1) {
				Map<String,Object> yjhlbMap = new HashMap<>();
				yjhlbMap.put("id", f.get("id"));//意见函Id
				yjhlbMap.put("createTime", f.get("createTime"));//发送时间
				yjhlbMap.put("type", 1);//类型(0发送/1接收)
				yjhlbMap.put("strUserName", f.get("jsrName"));//发送/接收人员姓名
				yjhlbMap.put("unitName", f.get("fsrName"));//发送/接收单位名称
//				yjhlbMap.put("opinionContent", value);//意见函内容
				yjhlbMap.put("opinionFileUrls", f.get("dz"));//附件地址
				yjhlbMap.put("opinionFileNames", f.get("mc"));//附件文件名称
				opinionList.add(yjhlbMap);
			}
		}
		retMap.put("opinionList", opinionList);
		
		//正式复函列表 officialReplyList
		fhhzMap.put("type", 2);
		List<Map<String,Object>> list2 = fhhzService.listMap(fhhzMap);
		List<Map<String,Object>> officialReplyList = new ArrayList<>();
		if(list2!=null && list2.size()>0) {
			for(Map<String,Object> f : list2) {
				Map<String,Object> yjhlbMap = new HashMap<>();
				yjhlbMap.put("id", f.get("id"));//意见函Id
				yjhlbMap.put("createTime", f.get("createTime"));//发送时间
				yjhlbMap.put("type", 1);//类型(0发送/1接收)
				yjhlbMap.put("strUserName", f.get("jsrName"));//发送/接收人员姓名
				yjhlbMap.put("unitName", f.get("fsrName"));//发送/接收单位名称
//				yjhlbMap.put("opinionContent", value);//意见函内容
				yjhlbMap.put("replyFileUrls", f.get("dz"));//附件地址
				yjhlbMap.put("replyFileNames", f.get("mc"));//附件文件名称
				officialReplyList.add(yjhlbMap);
			}
		}
		retMap.put("officialReplyList", officialReplyList);
		
		//并案提案列表 proposalUnionList
		List<Map<String,Object>> proposalUnionList = new ArrayList<>();
		if(taxx.getIsba()!=null && taxx.getIsba() == 1) {
			Map<String,Object> baMap = new HashMap<>();
			if(taxx.getBaid() == null) {
				baMap.put("baid", taxx.getId());
			}else {
				baMap.put("fid", taxx.getBaid());
			}
			List<Map<String,Object>> tList = taxxService.listMap(baMap);
			if(tList!=null && tList.size()>0) {
				for(Map<String,Object> t : tList){
					if(Integer.parseInt(t.get("id").toString()) != taxx.getId().intValue()) {//不于此条提案相等
						Map<String,Object> ls_map = new HashMap<>();
						ls_map.put("id", t.get("id"));//提案id
						ls_map.put("title", t.get("tatm"));//提案标题
						ls_map.put("proposalType", t.get("tanx"));//提案类型
						ls_map.put("fid", t.get("baId")==null ? 0 : t.get("baId"));//如果是主并案则为0
						ls_map.put("strProposalUserId", t.get("createId"));//提案用户ID
						ls_map.put("strProposalUserUserName", t.get("xm"));//提案用户姓名
						proposalUnionList.add(ls_map);
					}
				}
			}
		}
		
		retMap.put("proposalUnionList", proposalUnionList);
		
		return new OpenReturnDO(retMap);
	}
	
	/**
	 * 意见函查询
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/yjhcx")
	public Object yjhcx(@RequestParam Map<String, Object> params){
		
		if(StringUtil.judgeObjectsIsNull(params.get("pageNum"),params.get("pageSize"),params.get("strProposalUserId"))) {
			return new OpenReturnDO("1","pageNum|pageSize|strProposalUserId参数值为空");
		}
//		strProposalUserId 提案系统的用户ID
//		keyword 提案标题
		Integer jsr =  Integer.parseInt(params.get("strProposalUserId").toString());
		
		int num = Integer.parseInt(params.get("pageNum").toString());
		int size = Integer.parseInt(params.get("pageSize").toString());
		
//		Map<String,Object> map = new HashMap<>();
		Map<String,Object> fhhzMap = new HashMap<>();
		fhhzMap.put("offset", (num-1)*size);
		fhhzMap.put("limit", size);
		
		fhhzMap.put("jsr", jsr);
		fhhzMap.put("type", 1);
		
		
		if(!StringUtil.judgeObjectsIsNull(params.get("keyword"))) {
			fhhzMap.put("mc", "%"+params.get("keyword")+"%");
		}
		
		
		List<Map<String, Object>> list = fhhzService.listMap(fhhzMap);
		int intTotalRecord = fhhzService.countMap(fhhzMap);
		List<Map<String,Object>> lMap = new ArrayList<>();
		for(Map<String, Object> t : list) {
			Map<String,Object> ls_map = new HashMap<>();
			ls_map.put("id", t.get("id"));//意见函Id
			ls_map.put("proposalTitle", t.get("tatm"));//提案标题
			ls_map.put("createTime", t.get("createTime").toString());//发送时间
			ls_map.put("type", 0);
			ls_map.put("strUserName", t.get("fsrName"));//发送/接收人员姓名
			ls_map.put("unitId", t.get("jsr"));//发送/接收单位Id
			ls_map.put("unitName", t.get("jsrName"));//发送/接收单位名称
//			ls_map.put("opinionContent", t.getLastate());//意见函内容
			ls_map.put("opinionFileUrls", t.get("dz"));//附件地址
			ls_map.put("opinionFileNames", t.get("mc"));
			lMap.add(ls_map);
		}
		Map<String,Object> retMap = new HashMap<>();
		
		int intTotalPage = 0;
		if(intTotalRecord%size == 0) {
			intTotalPage = intTotalRecord/size;
		}else {
			intTotalPage = (intTotalRecord/size)+1;
		}
		
		retMap.put("resultList", lMap);
		retMap.put("intCurrentPage", params.get("pageNum"));
		retMap.put("intPageSize", params.get("pageSize"));
		retMap.put("intTotalPage", intTotalPage);//总页数
		retMap.put("intTotalRecord", intTotalRecord);//总条数
		
		return new OpenReturnDO(retMap);
	}
	
	/**
	 * 复函查询
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/fhch")
	public Object fhch(@RequestParam Map<String, Object> params){
		
		if(StringUtil.judgeObjectsIsNull(params.get("pageNum"),params.get("pageSize"),params.get("strProposalUserId"))) {
			return new OpenReturnDO("1","pageNum|pageSize|strProposalUserId参数值为空");
		}
//		strProposalUserId 提案系统的用户ID
//		keyword 提案标题
		Integer jsr = Integer.parseInt(params.get("strProposalUserId").toString());
		
		int num = Integer.parseInt(params.get("pageNum").toString());
		int size = Integer.parseInt(params.get("pageSize").toString());
		
		Map<String,Object> fhhzMap = new HashMap<>();
		fhhzMap.put("offset", (num-1)*size);
		fhhzMap.put("limit", size);
		
		fhhzMap.put("jsr", jsr);
		fhhzMap.put("type", 2);
		
		if(!StringUtil.judgeObjectsIsNull(params.get("keyword"))) {
			fhhzMap.put("mc", "%"+params.get("keyword")+"%");
		}
		
		List<Map<String, Object>> list = fhhzService.listMap(fhhzMap);
		int intTotalRecord = fhhzService.countMap(fhhzMap);
		List<Map<String,Object>> lMap = new ArrayList<>();
		for(Map<String, Object> t : list) {
			Map<String,Object> ls_map = new HashMap<>();
			ls_map.put("id", t.get("id"));//意见函Id
			ls_map.put("proposalTitle", t.get("tatm"));//提案标题
			ls_map.put("createTime", t.get("createTime").toString());//发送时间
			
			if("1".equals(t.get("fsrType").toString()) || "2".equals(t.get("fsrType").toString())) {
				if("1".equals(t.get("fsrType").toString())) {
					ls_map.put("strUserName", t.get("fsrXm"));//个人委员
				}else {
					ls_map.put("strUserName", t.get("fsrDwmc"));//集体委员
				}
//				ls_map.put("sendType", 0);//0发送/1接收
			}
			
			if("1".equals(t.get("jsrType").toString()) || "2".equals(t.get("jsrType").toString())) {
				if("1".equals(t.get("fsrType").toString())) {
					ls_map.put("strUserName", t.get("jsrXm"));//个人委员
				}else {
					ls_map.put("strUserName", t.get("jsrDwmc"));//集体委员
				}
//				ls_map.put("sendType", 1);//0发送/1接收
			}
			
			if("3".equals(t.get("fsrType").toString())) {
				ls_map.put("unitId", t.get("fsr"));//发送/接收单位Id
				ls_map.put("unitName", t.get("fsrDwmc"));//发送/接收单位名称
			}
			
			if("3".equals(t.get("jsrType").toString())) {
				ls_map.put("unitId", t.get("jsr"));//发送/接收单位Id
				ls_map.put("unitName", t.get("jsrDwmc"));//发送/接收单位名称
			}
			
//			ls_map.put("strUserName", t.get("fsrName"));//发送/接收人员姓名
//			ls_map.put("unitId", t.get("jsr"));//发送/接收单位Id
//			ls_map.put("unitName", t.get("jsrName"));//发送/接收单位名称
			
//			ls_map.put("opinionContent", t.getLastate());//意见函内容
			ls_map.put("replyFileUrls", t.get("dz"));//附件地址
			ls_map.put("replyFileNames", t.get("mc"));
			lMap.add(ls_map);
		}
		Map<String,Object> retMap = new HashMap<>();
		
		int intTotalPage = 0;
		if(intTotalRecord%size == 0) {
			intTotalPage = intTotalRecord/size;
		}else {
			intTotalPage = (intTotalRecord/size)+1;
		}
		
		retMap.put("resultList", lMap);
		retMap.put("intCurrentPage", params.get("pageNum"));
		retMap.put("intPageSize", params.get("pageSize"));
		retMap.put("intTotalPage", intTotalPage);//总页数
		retMap.put("intTotalRecord", intTotalRecord);//总条数
		
		return new OpenReturnDO(retMap);
	}
	
	/**
	 * 提案交流列表查询
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/tajllbcx")
	public Object tajllbcx(@RequestParam Map<String, Object> params){
		
		if(StringUtil.judgeObjectsIsNull(params.get("pageNum"),params.get("pageSize"),params.get("strProposalUserId"))) {
			return new OpenReturnDO("1","pageNum|pageSize|strProposalUserId参数值为空");
		}
		
//		strProposalUserId 提案系统的用户ID
//		keyword 提案标题
		Integer createId = null;
		if(params.get("strProposalUserId")!=null && !"".equals(params.get("strProposalUserId").toString())) {
			createId = Integer.parseInt(params.get("strProposalUserId").toString());
		}
		
		
		int num = Integer.parseInt(params.get("pageNum").toString());
		int size = Integer.parseInt(params.get("pageSize").toString());
		
		Map<String,Object> fhhzMap = new HashMap<>();
		fhhzMap.put("offset", (num-1)*size);
		fhhzMap.put("limit", size);
		fhhzMap.put("br", createId);
		if(params.get("keyword")!=null && !"".equals(params.get("keyword").toString())) {
			fhhzMap.put("keyword", "%"+params.get("keyword").toString()+"%");
		}
		if(params.get("proposalId")!=null && !"".equals(params.get("proposalId").toString())) {
			fhhzMap.put("proposalId", params.get("proposalId").toString());
		}
		
		List<Map<String, Object>> list = xxService.listMap(fhhzMap);
		int intTotalRecord = xxService.countMap(fhhzMap);
		
		List<Map<String,Object>> lMap = new ArrayList<>();
		for(Map<String, Object> t : list) {
			Map<String,Object> ls_map = new HashMap<>();
			ls_map.put("id", t.get("id"));//意见函Id
//			ls_map.put("infoTitle", null);//提案标题
			ls_map.put("createTime", t.get("createTime").toString());//发送时间
			ls_map.put("sendContent", t.get("nr").toString());
			ls_map.put("proposalId", t.get("taxxId"));
			ls_map.put("proposalTitle", t.get("tatm").toString());
			
			if("1".equals(t.get("fsrType").toString()) || "2".equals(t.get("fsrType").toString())) {
				if("1".equals(t.get("fsrType").toString())) {
					ls_map.put("strUserName", t.get("fsrXm"));//个人委员
				}else {
					ls_map.put("strUserName", t.get("fsrDwmc"));//集体委员
				}
				ls_map.put("sendType", 0);//0发送/1接收
			}
			
			if("1".equals(t.get("jsrType").toString()) || "2".equals(t.get("jsrType").toString())) {
				if("1".equals(t.get("fsrType").toString())) {
					ls_map.put("strUserName", t.get("jsrXm"));//个人委员
				}else {
					ls_map.put("strUserName", t.get("jsrDwmc"));//集体委员
				}
				ls_map.put("sendType", 1);//0发送/1接收
			}
			
			if("3".equals(t.get("fsrType").toString())) {
				ls_map.put("unitId", t.get("fsr"));//发送/接收单位Id
				ls_map.put("unitName", t.get("fsrDwmc"));//发送/接收单位名称
			}
			
			if("3".equals(t.get("jsrType").toString())) {
				ls_map.put("unitId", t.get("jsr"));//发送/接收单位Id
				ls_map.put("unitName", t.get("jsrDwmc"));//发送/接收单位名称
			}
			
			lMap.add(ls_map);
		}
		Map<String,Object> retMap = new HashMap<>();
		
		int intTotalPage = 0;
		if(intTotalRecord%size == 0) {
			intTotalPage = intTotalRecord/size;
		}else {
			intTotalPage = (intTotalRecord/size)+1;
		}
		
		retMap.put("resultList", lMap);
		retMap.put("intCurrentPage", params.get("pageNum"));
		retMap.put("intPageSize", params.get("pageSize"));
		retMap.put("intTotalPage", intTotalPage);//总页数
		retMap.put("intTotalRecord", intTotalRecord);//总条数
		
		return new OpenReturnDO(retMap);
	}
	
	/**
	 * 根据提案id查询,承办单位和协办单位
	 * 提案下承办单位列表
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/taxcbdwlb")
	public Object taxcbdwlb(@RequestParam Map<String, Object> params){
		
		if(StringUtil.judgeObjectsIsNull(params.get("proposalId"))) {
			return new OpenReturnDO("1","proposalId参数值为空");
		}
//		proposalId 提案id
		Integer taxxId = Integer.parseInt(params.get("proposalId").toString());
		Map<String,Object> taxxMap = new HashMap<>();
		taxxMap.put("taxxid", taxxId);
		List<Map<String, Object>> list = taxxCbdwService.listMap(taxxMap);
		List<Map<String,Object>> retMap = new ArrayList<>();
		if(list!=null && list.size()>0) {
			for(Map<String, Object> t : list) {
				Map<String,Object> map = new HashMap<>();
				map.put("id", t.get("userId"));
				map.put("unitName", t.get("name"));
				retMap.add(map);
			}
		}
		Map<String,Object> rMap = new HashMap<>();
		rMap.put("resultList", retMap);
		return new OpenReturnDO(rMap);
	}
	
	/**
	 * 提案交流录入
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/tajlxxlr")
	public Object tajlxxlr(@RequestParam Map<String, Object> params){
		
		if(StringUtil.judgeObjectsIsNull(params.get("proposalId"),params.get("unitId"),params.get("strProposalUserId"),params.get("opinionContent"))) {
			return new OpenReturnDO("1","proposalId|unitId|strProposalUserId|opinionContent参数值为空");
		}
		
//		discussFileUrls
//		discussFileNames
		
		String proposalId = params.get("proposalId").toString();
		String unitId = params.get("unitId").toString();
		Integer[] mbdw = new Integer[1];
		mbdw[0] = Integer.parseInt(unitId);
		String strProposalUserId = params.get("strProposalUserId").toString();
		String opinionContent = params.get("opinionContent").toString();
		
		TaxxDO t = taxxService.get(Integer.parseInt(proposalId));
		if(t==null) {
			return new OpenReturnDO("1","提案ID不存在");
		}else if(t.getTastate().intValue() < 4) {
			return new OpenReturnDO("1","该提案该状态不能交流");
		}
		
		UserDO d1 = userService.get(Long.parseLong(unitId));
		if(d1==null){
			return new OpenReturnDO("1","接受单位ID不存在");
		}
		
		UserDO d2 = userService.get(Long.parseLong(strProposalUserId));
		if(d2==null){
			return new OpenReturnDO("1","提案用户ID不存在");
		}
		
		XxDO xx = new XxDO();
		xx.setTaxxid(Integer.parseInt(proposalId));
		xx.setNr(opinionContent);
		xx.setFsr(Integer.parseInt(strProposalUserId));
		int i = xxService.save(xx, mbdw, 1, null);//委员保存
		if(i==0) {
			return new OpenReturnDO("1","保存不成功");
		}
		return new OpenReturnDO();
	}
	
	
	/**
	 * 工作服务列表查询
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/gzfwlbcx")
	public Object gzfwlbcx(@RequestParam Map<String, Object> params){
		
		if(StringUtil.judgeObjectsIsNull(params.get("pageNum"),params.get("pageSize"))) {
			return new OpenReturnDO("1","pageNum|pageSize参数值为空");
		}
		
//		strProposalUserId 提案系统的用户ID
//		keyword 提案标题
		
		int num = Integer.parseInt(params.get("pageNum").toString());
		int size = Integer.parseInt(params.get("pageSize").toString());
		
		Map<String,Object> fhhzMap = new HashMap<>();
		fhhzMap.put("offset", (num-1)*size);
		fhhzMap.put("limit", size);
//		fhhzMap.put("createId", createId);
		if(params.get("keyword")!=null && !"".equals(params.get("keyword").toString())) {
			fhhzMap.put("bt", "%"+params.get("keyword").toString()+"%");
		}
		List<GzfwDO> list = gzfwService.list(fhhzMap);
		int intTotalRecord = gzfwService.count(fhhzMap);
		
		List<Map<String,Object>> lMap = new ArrayList<>();
		for(GzfwDO t : list) {
			Map<String,Object> ls_map = new HashMap<>();
			ls_map.put("id", t.getId());//意见函Id
			ls_map.put("infoTitle", t.getBt());//标题
			ls_map.put("createTime", t.getBt());//发送时间
			ls_map.put("strUserName", t.getCreateName());//发送/接收人员姓名
			ls_map.put("unitId", "all");//回复单位Id
			ls_map.put("unitName", "所有");//发送/接收单位名称
			ls_map.put("sendContent", t.getNr());//内容
			ls_map.put("infoFileUrls", t.getFjdz());//附件地址
			ls_map.put("infoFileNames", t.getFjmc());//
			lMap.add(ls_map);
		}
		Map<String,Object> retMap = new HashMap<>();
		
		int intTotalPage = 0;
		if(intTotalRecord%size == 0) {
			intTotalPage = intTotalRecord/size;
		}else {
			intTotalPage = (intTotalRecord/size)+1;
		}
		
		retMap.put("resultList", lMap);
		retMap.put("intCurrentPage", params.get("pageNum"));
		retMap.put("intPageSize", params.get("pageSize"));
		retMap.put("intTotalPage", intTotalPage);//总页数
		retMap.put("intTotalRecord", intTotalRecord);//总条数
		
		return new OpenReturnDO(retMap);
	}
	
	/**
	 * 用户查询
	 * @param params
	 * @return
	 */
	@RequestMapping("/keyLogin")
	public Object keyLogin(@RequestParam Map<String, Object> params){
		
		if(StringUtil.judgeObjectsIsNull(params.get("ssoKey"))) {
			return "redirect:/login";
		}
		
		try {
			Map<String,String> map = new HashMap<>();
			map.put("timestamp", StringUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
			map.put("ssoKey", params.get("ssoKey").toString());
			map.put("appId", bootdoConfig.getAppId());
			
			List<NameValuePair> parList = new ArrayList<>();  
			parList.add(new BasicNameValuePair("ssoKey", map.get("ssoKey")));
			parList.add(new BasicNameValuePair("appId", map.get("appId")));
			parList.add(new BasicNameValuePair("timestamp", map.get("timestamp")));
			parList.add(new BasicNameValuePair("token", TokenUtil.getToken(map, bootdoConfig.getAppKey())));
			
			String jsonStr = CqnewsHttpUtil.sendPost(bootdoConfig.getBaseCqnewsUrl()+"sd/proposal/sso/auth", parList);
			
//			user	object			
//			strProposalUserId	String	是		提案系统的用户ID
//			strUserName	String	是		用户姓名
//			strPosition	String	是		单位职务
//			strUserAddr	String	是		联系地址
//			strPhone	String	是		联系电话
//			strSpecommitteeId	String	是		专委会ID
//			strSpecommitteeName	String	是		专委会名称
//			strEmail	String	是		邮箱地址
//			jieci	array< object>	是		委员届次
//			strSessionId	String	是		届次id
//			strSessionName	String	是		届次名称
			
			JSONObject json = JSONObject.parseObject(jsonStr);
			/*JSONObject user = json.getJSONObject("user");
			if(StringUtil.judgeObjectsIsNull(user)) {
				return "redirect:/login";
			}
			
			JSONObject uId = user.getJSONObject("strProposalUserId");
			if(StringUtil.judgeObjectsIsNull(uId.toString())) {
				return "redirect:/login";
			}*/
			
			JSONObject data = json.getJSONObject("data");
			if(StringUtil.judgeObjectsIsNull(data)) {
				return "redirect:/login";
			}
			
			JSONObject user = data.getJSONObject("user");
			if(StringUtil.judgeObjectsIsNull(user)) {
				return "redirect:/login";
			}
			
			String uId = user.getString("strProposalUserId");
			if(StringUtil.judgeObjectsIsNull(uId)) {
				return "redirect:/login";
			}
			
			UserDO userDo = userService.get(Long.parseLong(uId.toString()));
			UsernamePasswordToken token = new UsernamePasswordToken(userDo.getUsername(), userDo.getPassword());
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			return "redirect:/index";
		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/login";
		}
	}
	
	public static void main(String[] args) {
		JSONObject json = JSONObject.parseObject("{\"code\":1,\"data\":{\"jieci\":[{\"strSessionId\":\"4\",\"strSessionName\":\"4届\"},{\"strSessionId\":\"5\",\"strSessionName\":\"5届\"}],\"user\":{\"strUserAddr\":\"重庆沙坪坝\",\"strPosition\":\"委员\",\"strSpecommitteeName\":\"城乡建设环境保护委员会\",\"strUserName\":\"段凤杰\",\"strPhone\":\"15025467198\",\"strProposalUserId\":\"153\",\"strSpecommitteeId\":\"2\"}}}");
		
		JSONObject data = json.getJSONObject("data");
		if(StringUtil.judgeObjectsIsNull(data)) {
			System.out.println(1);
		}
		
		JSONObject user = data.getJSONObject("user");
		if(StringUtil.judgeObjectsIsNull(user)) {
			System.out.println(1);
		}
		
		String uId = user.getString("strProposalUserId");
		if(StringUtil.judgeObjectsIsNull(uId.toString())) {
			System.out.println(2);
		}
		System.out.println(uId);
	}
	
}
