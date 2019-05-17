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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.proposal.domain.TaxxCbdwDO;
import com.bootdo.proposal.service.TaxxCbdwService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-10-21 15:32:38
 */
 
@Controller
@RequestMapping("/proposal/taxxCbdw")
public class TaxxCbdwController {
	@Autowired
	private TaxxCbdwService taxxCbdwService;
	
	@GetMapping()
	@RequiresPermissions("proposal:taxxCbdw:taxxCbdw")
	String TaxxCbdw(){
	    return "proposal/taxxCbdw/taxxCbdw";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:taxxCbdw:taxxCbdw")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TaxxCbdwDO> taxxCbdwList = taxxCbdwService.list(query);
		int total = taxxCbdwService.count(query);
		PageUtils pageUtils = new PageUtils(taxxCbdwList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:taxxCbdw:add")
	String add(){
	    return "proposal/taxxCbdw/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:taxxCbdw:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TaxxCbdwDO taxxCbdw = taxxCbdwService.get(id);
		model.addAttribute("taxxCbdw", taxxCbdw);
	    return "proposal/taxxCbdw/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:taxxCbdw:add")
	public R save( TaxxCbdwDO taxxCbdw){
		if(taxxCbdwService.save(taxxCbdw)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:taxxCbdw:edit")
	public R update( TaxxCbdwDO taxxCbdw){
		taxxCbdwService.update(taxxCbdw);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:taxxCbdw:remove")
	public R remove( Integer id){
		if(taxxCbdwService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:taxxCbdw:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		taxxCbdwService.batchRemove(ids);
		return R.ok();
	}
	
}
