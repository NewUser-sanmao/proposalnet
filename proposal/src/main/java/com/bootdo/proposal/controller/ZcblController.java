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

import com.bootdo.proposal.domain.ZcblDO;
import com.bootdo.proposal.service.ZcblService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 再次办理
 * 
 * @author shipan
 * @email 
 * @date 2018-12-28 14:53:24
 */
 
@Controller
@RequestMapping("/proposal/zcbl")
public class ZcblController {
	@Autowired
	private ZcblService zcblService;
	
	@GetMapping()
	@RequiresPermissions("proposal:zcbl:zcbl")
	String Zcbl(){
	    return "proposal/zcbl/zcbl";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:zcbl:zcbl")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ZcblDO> zcblList = zcblService.list(query);
		int total = zcblService.count(query);
		PageUtils pageUtils = new PageUtils(zcblList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:zcbl:add")
	String add(){
	    return "proposal/zcbl/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:zcbl:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ZcblDO zcbl = zcblService.get(id);
		model.addAttribute("zcbl", zcbl);
	    return "proposal/zcbl/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:zcbl:add")
	public R save( ZcblDO zcbl){
		if(zcblService.save(zcbl)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:zcbl:edit")
	public R update( ZcblDO zcbl){
		zcblService.update(zcbl);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:zcbl:remove")
	public R remove( Integer id){
		if(zcblService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:zcbl:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		zcblService.batchRemove(ids);
		return R.ok();
	}
	
}
