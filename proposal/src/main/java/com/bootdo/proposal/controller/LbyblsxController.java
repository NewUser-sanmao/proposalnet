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

import com.bootdo.proposal.domain.LbyblsxDO;
import com.bootdo.proposal.service.LbyblsxService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-08 15:05:29
 */
 
@Controller
@RequestMapping("/proposal/lbyblsx")
public class LbyblsxController {
	@Autowired
	private LbyblsxService lbyblsxService;
	
	@GetMapping()
	@RequiresPermissions("proposal:lbyblsx:lbyblsx")
	String Lbyblsx(){
	    return "proposal/lbyblsx/lbyblsx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:lbyblsx:lbyblsx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LbyblsxDO> lbyblsxList = lbyblsxService.list(query);
		int total = lbyblsxService.count(query);
		PageUtils pageUtils = new PageUtils(lbyblsxList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:lbyblsx:add")
	String add(){
	    return "proposal/lbyblsx/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:lbyblsx:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		LbyblsxDO lbyblsx = lbyblsxService.get(id);
		model.addAttribute("lbyblsx", lbyblsx);
	    return "proposal/lbyblsx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:lbyblsx:add")
	public R save( LbyblsxDO lbyblsx){
		if(lbyblsxService.save(lbyblsx)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:lbyblsx:edit")
	public R update( LbyblsxDO lbyblsx){
		lbyblsxService.update(lbyblsx);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:lbyblsx:remove")
	public R remove( Integer id){
		if(lbyblsxService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:lbyblsx:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		lbyblsxService.batchRemove(ids);
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
		List<Map<String,Object>> l = lbyblsxService.selectAllSelect();
		map.put("results", l);
		return map;
	}
	
}
