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

import com.bootdo.proposal.domain.GbsqDO;
import com.bootdo.proposal.service.GbsqService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 改办申请
 * 
 * @author shipan
 * @email 
 * @date 2018-10-23 16:56:28
 */
 
@Controller
@RequestMapping("/proposal/gbsq")
public class GbsqController {
	@Autowired
	private GbsqService gbsqService;
	
	@GetMapping()
	@RequiresPermissions("proposal:gbsq:gbsq")
	String Gbsq(){
	    return "proposal/gbsq/gbsq";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:gbsq:gbsq")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<GbsqDO> gbsqList = gbsqService.list(query);
		int total = gbsqService.count(query);
		PageUtils pageUtils = new PageUtils(gbsqList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:gbsq:add")
	String add(){
	    return "proposal/gbsq/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:gbsq:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		GbsqDO gbsq = gbsqService.get(id);
		model.addAttribute("gbsq", gbsq);
	    return "proposal/gbsq/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:gbsq:add")
	public R save( GbsqDO gbsq, String[] cbdw,String[] fbdw,String[] xbdw){
		if(gbsqService.save(gbsq,cbdw,fbdw,xbdw)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:gbsq:edit")
	public R update( GbsqDO gbsq){
		gbsqService.update(gbsq);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:gbsq:remove")
	public R remove( Integer id){
		if(gbsqService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:gbsq:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		gbsqService.batchRemove(ids);
		return R.ok();
	}
	
}
