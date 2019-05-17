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
import com.bootdo.proposal.domain.SswyhDO;
import com.bootdo.proposal.service.SswyhService;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-07 11:34:53
 */
 
@Controller
@RequestMapping("/proposal/sswyh")
public class SswyhController {
	@Autowired
	private SswyhService sswyhService;
	
	@GetMapping()
	@RequiresPermissions("proposal:sswyh:sswyh")
	String Sswyh(){
	    return "proposal/sswyh/sswyh";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:sswyh:sswyh")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SswyhDO> sswyhList = sswyhService.list(query);
		int total = sswyhService.count(query);
		PageUtils pageUtils = new PageUtils(sswyhList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:sswyh:add")
	String add(){
	    return "proposal/sswyh/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:sswyh:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SswyhDO sswyh = sswyhService.get(id);
		model.addAttribute("sswyh", sswyh);
	    return "proposal/sswyh/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:sswyh:add")
	public R save( SswyhDO sswyh){
		if(sswyhService.save(sswyh)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:sswyh:edit")
	public R update( SswyhDO sswyh){
		sswyhService.update(sswyh);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:sswyh:remove")
	public R remove( Integer id){
		if(sswyhService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:sswyh:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		sswyhService.batchRemove(ids);
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
		List<Map<String,Object>> l = sswyhService.selectAllSelect();
		map.put("results", l);
		return map;
	}
	
}
