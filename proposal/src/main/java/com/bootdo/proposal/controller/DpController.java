package com.bootdo.proposal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.proposal.domain.DpDO;
import com.bootdo.proposal.service.DpService;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-08 15:05:29
 */
 
@Controller
@RequestMapping("/proposal/dp")
public class DpController {
	@Autowired
	private DpService dpService;
	
	@GetMapping()
	@RequiresPermissions("proposal:dp:dp")
	String Dp(){
	    return "proposal/dp/dp";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:dp:dp")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DpDO> dpList = dpService.list(query);
		int total = dpService.count(query);
		PageUtils pageUtils = new PageUtils(dpList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:dp:add")
	String add(){
	    return "proposal/dp/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:dp:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		DpDO dp = dpService.get(id);
		model.addAttribute("dp", dp);
	    return "proposal/dp/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:dp:add")
	public R save( DpDO dp){
		if(dpService.save(dp)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:dp:edit")
	public R update( DpDO dp){
		dpService.update(dp);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:dp:remove")
	public R remove( Integer id){
		if(dpService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:dp:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		dpService.batchRemove(ids);
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
		List<Map<String,Object>> l = dpService.selectAllSelect();
		map.put("results", l);
		return map;
	}
}
