package com.bootdo.proposal.controller;

import java.util.HashMap;
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

import com.bootdo.proposal.domain.TayyDO;
import com.bootdo.proposal.service.TayyService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-08 15:05:30
 */
 
@Controller
@RequestMapping("/proposal/tayy")
public class TayyController {
	@Autowired
	private TayyService tayyService;
	
	@GetMapping()
	@RequiresPermissions("proposal:tayy:tayy")
	String Tayy(){
	    return "proposal/tayy/tayy";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:tayy:tayy")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TayyDO> tayyList = tayyService.list(query);
		int total = tayyService.count(query);
		PageUtils pageUtils = new PageUtils(tayyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:tayy:add")
	String add(){
	    return "proposal/tayy/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:tayy:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TayyDO tayy = tayyService.get(id);
		model.addAttribute("tayy", tayy);
	    return "proposal/tayy/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:tayy:add")
	public R save( TayyDO tayy){
		if(tayyService.save(tayy)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:tayy:edit")
	public R update( TayyDO tayy){
		tayyService.update(tayy);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:tayy:remove")
	public R remove( Integer id){
		if(tayyService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:tayy:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		tayyService.batchRemove(ids);
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
		List<Map<String,Object>> l = tayyService.selectAllSelect();
		map.put("results", l);
		return map;
	}
}
