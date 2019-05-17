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

import com.bootdo.proposal.domain.LxEnumDO;
import com.bootdo.proposal.service.LxEnumService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-11-02 10:07:14
 */
 
@Controller
@RequestMapping("/proposal/lxEnum")
public class LxEnumController {
	@Autowired
	private LxEnumService lxEnumService;
	
	@GetMapping()
	@RequiresPermissions("proposal:lxEnum:lxEnum")
	String LxEnum(){
	    return "proposal/lxEnum/lxEnum";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:lxEnum:lxEnum")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LxEnumDO> lxEnumList = lxEnumService.list(query);
		int total = lxEnumService.count(query);
		PageUtils pageUtils = new PageUtils(lxEnumList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:lxEnum:add")
	String add(){
	    return "proposal/lxEnum/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:lxEnum:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		LxEnumDO lxEnum = lxEnumService.get(id);
		model.addAttribute("lxEnum", lxEnum);
	    return "proposal/lxEnum/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:lxEnum:add")
	public R save( LxEnumDO lxEnum){
		if(lxEnumService.save(lxEnum)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:lxEnum:edit")
	public R update( LxEnumDO lxEnum){
		lxEnumService.update(lxEnum);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:lxEnum:remove")
	public R remove( Integer id){
		if(lxEnumService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:lxEnum:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		lxEnumService.batchRemove(ids);
		return R.ok();
	}
	
}
