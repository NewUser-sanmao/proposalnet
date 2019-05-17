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

import com.bootdo.proposal.domain.PfbDO;
import com.bootdo.proposal.service.PfbService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 评分题目表
 * 
 * @author shipan
 * @email 
 * @date 2018-11-07 16:49:05
 */
 
@Controller
@RequestMapping("/proposal/pfb")
public class PfbController {
	@Autowired
	private PfbService pfbService;
	
	@GetMapping()
	@RequiresPermissions("proposal:pfb:pfb")
	String Pfb(){
	    return "proposal/pfb/pfb";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:pfb:pfb")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PfbDO> pfbList = pfbService.list(query);
		int total = pfbService.count(query);
		PageUtils pageUtils = new PageUtils(pfbList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:pfb:add")
	String add(){
	    return "proposal/pfb/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:pfb:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PfbDO pfb = pfbService.get(id);
		model.addAttribute("pfb", pfb);
	    return "proposal/pfb/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:pfb:add")
	public R save( PfbDO pfb){
		if(pfbService.save(pfb)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:pfb:edit")
	public R update( PfbDO pfb){
		pfbService.update(pfb);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:pfb:remove")
	public R remove( Integer id){
		if(pfbService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:pfb:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		pfbService.batchRemove(ids);
		return R.ok();
	}
	
}
