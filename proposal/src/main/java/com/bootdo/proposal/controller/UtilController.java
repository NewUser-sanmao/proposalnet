package com.bootdo.proposal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.proposal.domain.UtilDO;
import com.bootdo.proposal.service.UtilService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-11-20 17:14:57
 */
 
@Controller
@RequestMapping("/proposal/util")
public class UtilController {
	@Autowired
	private UtilService utilService;
	
	@GetMapping()
	@RequiresPermissions("proposal:util:util")
	String Util(){
	    return "proposal/util/util";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:util:util")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UtilDO> utilList = utilService.list(query);
		int total = utilService.count(query);
		PageUtils pageUtils = new PageUtils(utilList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:util:add")
	String add(){
	    return "proposal/util/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:util:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		UtilDO util = utilService.get(id);
		model.addAttribute("util", util);
	    return "proposal/util/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:util:add")
	public R save( UtilDO util){
		if(utilService.save(util)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( UtilDO util){
		utilService.update(util);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:util:remove")
	public R remove( Integer id){
		if(utilService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:util:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		utilService.batchRemove(ids);
		return R.ok();
	}
	
	@GetMapping("/sytzsz/{type}")
	@RequiresPermissions("proposal:util:sytzsz")
	String sytzsz(@PathVariable("type") Integer type,Model model){
		Map<String, Object> params = new HashMap<>();
		params.put("type", type);
		List<UtilDO> utilList = utilService.list(params);
		UtilDO util = utilList.get(0);
		model.addAttribute("util", util);
		
	    return "proposal/util/sytzsz";
	}
	
	@GetMapping("/tajzsj/{type}")
	@RequiresPermissions("proposal:util:tajzsj")
	String tajzsj(@PathVariable("type") Integer type,Model model){
		Map<String, Object> params = new HashMap<>();
		params.put("type", type);
		List<UtilDO> utilList = utilService.list(params);
		UtilDO util = utilList.get(0);
		model.addAttribute("util", util);
		
	    return "proposal/util/tajzsj";
	}
}
