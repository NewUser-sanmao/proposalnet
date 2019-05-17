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

import com.bootdo.proposal.domain.LzFshzDO;
import com.bootdo.proposal.service.LzFshzService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 履职分数汇总
 * 
 * @author shipan
 * @email 
 * @date 2018-12-08 19:18:11
 */
 
@Controller
@RequestMapping("/proposal/lzFshz")
public class LzFshzController {
	@Autowired
	private LzFshzService lzFshzService;
	
	@GetMapping()
	@RequiresPermissions("proposal:lzFshz:lzFshz")
	String LzFshz(){
	    return "proposal/lzFshz/lzFshz";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:lzFshz:lzFshz")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LzFshzDO> lzFshzList = lzFshzService.list(query);
		int total = lzFshzService.count(query);
		PageUtils pageUtils = new PageUtils(lzFshzList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:lzFshz:add")
	String add(){
	    return "proposal/lzFshz/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:lzFshz:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		LzFshzDO lzFshz = lzFshzService.get(id);
		model.addAttribute("lzFshz", lzFshz);
	    return "proposal/lzFshz/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:lzFshz:add")
	public R save( LzFshzDO lzFshz){
		if(lzFshzService.save(lzFshz)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:lzFshz:edit")
	public R update( LzFshzDO lzFshz){
		lzFshzService.update(lzFshz);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:lzFshz:remove")
	public R remove( Integer id){
		if(lzFshzService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:lzFshz:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		lzFshzService.batchRemove(ids);
		return R.ok();
	}
	
}
