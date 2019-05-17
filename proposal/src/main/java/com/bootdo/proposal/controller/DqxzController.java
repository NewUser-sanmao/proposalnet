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
import com.bootdo.proposal.domain.DqxzDO;
import com.bootdo.proposal.service.DqxzService;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-08 16:33:55
 */
 
@Controller
@RequestMapping("/proposal/dqxz")
public class DqxzController {
	@Autowired
	private DqxzService dqxzService;
	
	@GetMapping()
	@RequiresPermissions("proposal:dqxz:dqxz")
	String Dqxz(){
	    return "proposal/dqxz/dqxz";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:dqxz:dqxz")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DqxzDO> dqxzList = dqxzService.list(query);
		int total = dqxzService.count(query);
		PageUtils pageUtils = new PageUtils(dqxzList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:dqxz:add")
	String add(){
	    return "proposal/dqxz/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:dqxz:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		DqxzDO dqxz = dqxzService.get(id);
		model.addAttribute("dqxz", dqxz);
	    return "proposal/dqxz/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:dqxz:add")
	public R save( DqxzDO dqxz){
		if(dqxzService.save(dqxz)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:dqxz:edit")
	public R update( DqxzDO dqxz){
		dqxzService.update(dqxz);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:dqxz:remove")
	public R remove( Integer id){
		if(dqxzService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:dqxz:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		dqxzService.batchRemove(ids);
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
		List<Map<String,Object>> l = dqxzService.selectAllSelect();
		map.put("results", l);
		return map;
	}
	
}
