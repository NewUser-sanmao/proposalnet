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
import com.bootdo.proposal.domain.TajbDO;
import com.bootdo.proposal.service.TajbService;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-07 11:34:53
 */
 
@Controller
@RequestMapping("/proposal/tajb")
public class TajbController {
	@Autowired
	private TajbService tajbService;
	
	@GetMapping()
	@RequiresPermissions("proposal:tajb:tajb")
	String Tajb(){
	    return "proposal/tajb/tajb";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:tajb:tajb")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TajbDO> tajbList = tajbService.list(query);
		int total = tajbService.count(query);
		PageUtils pageUtils = new PageUtils(tajbList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:tajb:add")
	String add(){
	    return "proposal/tajb/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:tajb:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TajbDO tajb = tajbService.get(id);
		model.addAttribute("tajb", tajb);
	    return "proposal/tajb/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:tajb:add")
	public R save( TajbDO tajb){
		if(tajbService.save(tajb)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:tajb:edit")
	public R update( TajbDO tajb){
		tajbService.update(tajb);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:tajb:remove")
	public R remove( Integer id){
		if(tajbService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:tajb:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		tajbService.batchRemove(ids);
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
		List<Map<String,Object>> l = tajbService.selectAllSelect();
		map.put("results", l);
		return map;
	}
}
