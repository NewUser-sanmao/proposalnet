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

import com.bootdo.proposal.domain.HistoryLabelDO;
import com.bootdo.proposal.service.HistoryLabelService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-11-20 11:10:53
 */
 
@Controller
@RequestMapping("/proposal/historyLabel")
public class HistoryLabelController {
	@Autowired
	private HistoryLabelService historyLabelService;
	
	@GetMapping()
	@RequiresPermissions("proposal:historyLabel:historyLabel")
	String HistoryLabel(){
	    return "proposal/historyLabel/historyLabel";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:historyLabel:historyLabel")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<HistoryLabelDO> historyLabelList = historyLabelService.list(query);
		int total = historyLabelService.count(query);
		PageUtils pageUtils = new PageUtils(historyLabelList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:historyLabel:add")
	String add(){
	    return "proposal/historyLabel/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:historyLabel:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		HistoryLabelDO historyLabel = historyLabelService.get(id);
		model.addAttribute("historyLabel", historyLabel);
	    return "proposal/historyLabel/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:historyLabel:add")
	public R save( HistoryLabelDO historyLabel){
		if(historyLabelService.save(historyLabel)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:historyLabel:edit")
	public R update( HistoryLabelDO historyLabel){
		historyLabelService.update(historyLabel);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:historyLabel:remove")
	public R remove( Integer id){
		if(historyLabelService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:historyLabel:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		historyLabelService.batchRemove(ids);
		return R.ok();
	}
	
}
