package com.bootdo.proposal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.proposal.domain.DpDO;
import com.bootdo.proposal.domain.GrwyDO;
import com.bootdo.proposal.domain.TaxxDO;
import com.bootdo.proposal.service.DpService;
import com.bootdo.proposal.service.FsdxService;
import com.bootdo.proposal.service.GrwyService;
import com.bootdo.proposal.service.TaxxService;
import com.bootdo.proposal.util.CqnewsHttpUtil;
import com.bootdo.proposal.util.StringUtil;
import com.bootdo.proposal.util.TokenUtil;
import com.bootdo.system.domain.UserDO;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-08 15:05:29
 */
 
@Controller
@RequestMapping("/proposal/xtgj")
public class XtgjController {
	@Autowired
	private FsdxService fsdxService;
	@Autowired
	private GrwyService grwyService;
	@Autowired
	private TaxxService taxxService;
	
	@GetMapping("/fsdx")
	@RequiresPermissions("proposal:xtgj:fsdx")
	String Dp(){
	    return "proposal/xtgj/fsdx";
	}
	
	/**
	 * 发送短信
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/fs")
	public Object fs(@RequestParam Map<String, Object> params){
		
		if(StringUtil.judgeObjectsIsNull(params.get("type"), params.get("text"))) {
			return R.error("数据不能为空");
		}
		
		try {
			Map<String,Object> map = new HashMap<>();
			map.put("state", 1);
			map.put("types", params.get("type"));
			List<GrwyDO> gList = grwyService.list(map);
			List<String> tels = new ArrayList<>();
			for(GrwyDO g : gList) {
				if(tels.indexOf(g.getSjhm()) == -1) {
					tels.add(g.getSjhm());
				}
			}
			fsdxService.fs(params.get("text").toString(), tels);
			return R.ok();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return R.error();
		}
	}
	
	/**
	 * 发送短信
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/fsdx")
	public Object xtgj(@RequestParam Map<String, Object> params){
		
		if(StringUtil.judgeObjectsIsNull(params.get("type"),params.get("zxjcid"))) {//type=1:立案时(1立案,2不立案转意见,3不立案退回),=2:交办时;
			return R.error("数据不能为空");
		}
		
		try {
			Map<String,Object> map = new HashMap<>();
			map.put("zxjcid", params.get("zxjcid"));
			map.put("state", 1);
			List<TaxxDO> list = taxxService.list(map);
			if("1".equals(params.get("type").toString())) {
				for(TaxxDO t : list) {
					if(t.getTastate().intValue() > 2) {//终审完成及以上
						GrwyDO grwy = grwyService.getByUserId(t.getCreateid());
						List<String> tels = new ArrayList<>();
						tels.add(grwy.getSjhm());
						Map<String,String> par = new HashMap<>();
						par.put("tatm", t.getTatm());
						par.put("zxjcmc", t.getZxjcmc());
						if(t.getLastate().intValue()==1) {//立案
							fsdxService.fs(1, par, tels);
						}else if(t.getLastate().intValue()==2) {//转意见
							fsdxService.fs(2, par, tels);
						}else if(t.getLastate().intValue()==3) {//退回
							fsdxService.fs(3, par, tels);
						}
					}
				}
			}else if("2".equals(params.get("type").toString())) {
				for(TaxxDO t : list) {
					if(t.getTastate().intValue() > 2) {//终审完成及以上
						if(t.getLastate().intValue() == 1) {//立案
							GrwyDO grwy = grwyService.getByUserId(t.getCreateid());
							List<String> tels = new ArrayList<>();
							tels.add(grwy.getSjhm());
							Map<String,String> par = new HashMap<>();
							par.put("tatm", t.getTatm());
							par.put("zxjcmc", t.getZxjcmc());
							par.put("tah", t.getTah());
							par.put("cbdw", t.getCbdw()+(t.getXbdw()==null||"".equals(t.getXbdw())?"":","+t.getXbdw()));
							fsdxService.fs(4, par, tels);
						}
					}
				}
			}
			return R.ok();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return R.error();
		}
	}
}
