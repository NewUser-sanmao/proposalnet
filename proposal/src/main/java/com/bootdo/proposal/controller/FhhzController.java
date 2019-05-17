package com.bootdo.proposal.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.proposal.domain.FhhzDO;
import com.bootdo.proposal.service.FhhzService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;

/**
 * 复函回执
 * 
 * @author shipan
 * @email 
 * @date 2018-10-24 15:13:09
 */
 
@Controller
@RequestMapping("/proposal/fhhz")
public class FhhzController {
	@Autowired
	private FhhzService fhhzService;
	
	@GetMapping("/{type}")
	@RequiresPermissions("proposal:fhhz:fhhz")
	String Fhhz(@PathVariable("type") Integer type,Model model){
		model.addAttribute("type", type);
		
	    return "proposal/fhhz/fhhz";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:fhhz:fhhz")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FhhzDO> fhhzList = fhhzService.list(query);
		int total = fhhzService.count(query);
		PageUtils pageUtils = new PageUtils(fhhzList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/listMap")
	public PageUtils listMap(@RequestParam Map<String, Object> params){
		//查询列表数据
		if(params.get("isBr")!=null && "1".equals(params.get("isBr").toString())) {
			params.put("jsr", ShiroUtils.getUserIdInteger());
		}
        Query query = new Query(params);
		List<Map<String,Object>> fhhzList = fhhzService.listMap(query);
		int total = fhhzService.countMap(query);
		PageUtils pageUtils = new PageUtils(fhhzList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:fhhz:add")
	String add(){
	    return "proposal/fhhz/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:fhhz:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		FhhzDO fhhz = fhhzService.get(id);
		model.addAttribute("fhhz", fhhz);
	    return "proposal/fhhz/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:fhhz:add")
	public R save( FhhzDO fhhz){
		if(fhhzService.save(fhhz)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:fhhz:edit")
	public R update( FhhzDO fhhz){
		fhhzService.update(fhhz);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:fhhz:remove")
	public R remove( Integer id){
		if(fhhzService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:fhhz:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		fhhzService.batchRemove(ids);
		return R.ok();
	}
	
}
