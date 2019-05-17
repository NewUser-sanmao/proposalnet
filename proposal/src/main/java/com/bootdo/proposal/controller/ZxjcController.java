package com.bootdo.proposal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.proposal.domain.ZxjcDO;
import com.bootdo.proposal.service.ZxjcService;
import com.bootdo.proposal.util.CqnewsHttpUtil;
import com.bootdo.proposal.util.StringUtil;
import com.bootdo.proposal.util.TokenUtil;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-07 11:34:53
 */
 
@Controller
@RequestMapping("/proposal/zxjc")
public class ZxjcController {
	@Autowired
	private ZxjcService zxjcService;
	@Autowired
	BootdoConfig bootdoConfig;
	
	
	@GetMapping()
	@RequiresPermissions("proposal:zxjc:zxjc")
	String Zxjc(){
	    return "proposal/zxjc/zxjc";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:zxjc:zxjc")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ZxjcDO> zxjcList = zxjcService.list(query);
		int total = zxjcService.count(query);
		PageUtils pageUtils = new PageUtils(zxjcList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:zxjc:add")
	String add(){
	    return "proposal/zxjc/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:zxjc:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ZxjcDO zxjc = zxjcService.get(id);
		model.addAttribute("zxjc", zxjc);
	    return "proposal/zxjc/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:zxjc:add")
	public R save( ZxjcDO zxjc){
		if(zxjcService.save(zxjc)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:zxjc:edit")
	public R update( ZxjcDO zxjc){
		zxjcService.update(zxjc);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:zxjc:remove")
	public R remove( Integer id){
		if(zxjcService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:zxjc:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		zxjcService.batchRemove(ids);
		return R.ok();
	}
	
	/**
	 * 查询select下拉框所需要的数据
	 * @return
	 */
	@GetMapping( "/selectAllSelect")
	@ResponseBody
	public Map<String,Object> selectAllSelect(){
		Map<String,Object> map = new HashMap<>();
		List<Map<String,Object>> l = zxjcService.selectAllSelect();
		map.put("results", l);
		return map;
	}
	
	/**
	 * 查询届,到华龙网哪里去查
	 * @return
	 */
	@GetMapping( "/selectAllDic")
	@ResponseBody
	public Map<String,Object> selectAllDic(){
		
		
		Map<String,String> mapKey = new HashMap<>();
		mapKey.put("timestamp", StringUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		mapKey.put("pageNum", "1");
		mapKey.put("pageSize", "1000");
		mapKey.put("appId", bootdoConfig.getAppId());
		
		List<NameValuePair> params = new ArrayList<>();  
        params.add(new BasicNameValuePair("pageNum", "1"));
        params.add(new BasicNameValuePair("pageSize", "1000"));
        params.add(new BasicNameValuePair("appId", mapKey.get("appId")));
        params.add(new BasicNameValuePair("timestamp", mapKey.get("timestamp")));
        params.add(new BasicNameValuePair("token", TokenUtil.getToken(mapKey, bootdoConfig.getAppKey())));
		
        String jsonStr = CqnewsHttpUtil.sendPost(bootdoConfig.getBaseCqnewsUrl()+"sd/proposal/session/listSessionDic", params);
        ////{"msg":"查询成功","code":1,"data":{"intTotalPage":1,"intPageSize":1000,"intCurrentPage":1,"intTotalRecord":6,"resultList":[{"strSessionId":"9dcbe16658554b7cb60731185e026b28","strSessionName":"67","strIsDelete":"1","strCreateTime":"2018-08-28 14:50:59","strUpdateTime":"2018-08-28 14:54:10","strOrder":"6"},{"strSessionId":"5","strSessionName":"5届","strIsDelete":"1","strOrder":"5"},{"strSessionId":"4","strSessionName":"4届","strIsDelete":"1","strOrder":"4"},{"strSessionId":"3","strSessionName":"3届","strIsDelete":"1","strOrder":"3"},{"strSessionId":"2","strSessionName":"2届","strIsDelete":"1","strOrder":"2"},{"strSessionId":"1","strSessionName":"1届","strIsDelete":"1","strOrder":"1"}]}}
        List<Map<String,Object>> l = new ArrayList<>();
        JSONObject json = JSONObject.parseObject(jsonStr);
        JSONObject data = json.getJSONObject("data");
        JSONArray resultList = data.getJSONArray("resultList");
        if(resultList!=null && resultList.size()>0) {
        	for(int i=0;i<resultList.size();i++) {
        		Map<String,Object> ls_map = new HashMap<>();
        		ls_map.put("id", resultList.getJSONObject(i).getString("strSessionId"));
        		ls_map.put("text", resultList.getJSONObject(i).getString("strSessionName"));
        		l.add(ls_map);
        	}
        }
		Map<String,Object> map = new HashMap<>();
		map.put("results", l);
		return map;
	}
	
}
