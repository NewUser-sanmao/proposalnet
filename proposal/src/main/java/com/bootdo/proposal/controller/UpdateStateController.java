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

import com.bootdo.proposal.domain.UpdateStateDO;
import com.bootdo.proposal.service.UpdateStateService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-12-02 19:30:28
 */
 
@Controller
@RequestMapping("/proposal/updateState")
public class UpdateStateController {
	@Autowired
	private UpdateStateService updateStateService;
	
	@GetMapping()
	@RequiresPermissions("proposal:updateState:updateState")
	String UpdateState(){
	    return "proposal/updateState/updateState";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:updateState:updateState")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UpdateStateDO> updateStateList = updateStateService.list(query);
		int total = updateStateService.count(query);
		PageUtils pageUtils = new PageUtils(updateStateList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:updateState:add")
	String add(){
	    return "proposal/updateState/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:updateState:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		UpdateStateDO updateState = updateStateService.get(id);
		model.addAttribute("updateState", updateState);
	    return "proposal/updateState/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:updateState:add")
	public R save( UpdateStateDO updateState){
		if(updateStateService.save(updateState)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:updateState:edit")
	public R update( UpdateStateDO updateState){
		updateStateService.update(updateState);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:updateState:remove")
	public R remove( Integer id){
		if(updateStateService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:updateState:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		updateStateService.batchRemove(ids);
		return R.ok();
	}
	
}
