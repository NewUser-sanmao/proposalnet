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

import com.bootdo.proposal.domain.PfbTaxxDO;
import com.bootdo.proposal.service.PfbTaxxService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 提案信息对应的评分表
 * 
 * @author shipan
 * @email 
 * @date 2018-11-07 16:49:05
 */
 
@Controller
@RequestMapping("/proposal/pfbTaxx")
public class PfbTaxxController {
	@Autowired
	private PfbTaxxService pfbTaxxService;
	
	@GetMapping()
	@RequiresPermissions("proposal:pfbTaxx:pfbTaxx")
	String PfbTaxx(){
	    return "proposal/pfbTaxx/pfbTaxx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:pfbTaxx:pfbTaxx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PfbTaxxDO> pfbTaxxList = pfbTaxxService.list(query);
		int total = pfbTaxxService.count(query);
		PageUtils pageUtils = new PageUtils(pfbTaxxList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:pfbTaxx:add")
	String add(){
	    return "proposal/pfbTaxx/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:pfbTaxx:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PfbTaxxDO pfbTaxx = pfbTaxxService.get(id);
		model.addAttribute("pfbTaxx", pfbTaxx);
	    return "proposal/pfbTaxx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:pfbTaxx:add")
	public R save( PfbTaxxDO pfbTaxx){
		if(pfbTaxxService.save(pfbTaxx)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:pfbTaxx:edit")
	public R update( PfbTaxxDO pfbTaxx){
		pfbTaxxService.update(pfbTaxx);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:pfbTaxx:remove")
	public R remove( Integer id){
		if(pfbTaxxService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:pfbTaxx:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		pfbTaxxService.batchRemove(ids);
		return R.ok();
	}
	
}
