package com.bootdo.proposal.controller;

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
import com.bootdo.proposal.domain.GrwyDO;
import com.bootdo.proposal.domain.TajbDO;
import com.bootdo.proposal.domain.ZxjcDO;
import com.bootdo.proposal.service.DqxzService;
import com.bootdo.proposal.service.GrwyService;
import com.bootdo.proposal.service.SswyhService;
import com.bootdo.proposal.service.TajbService;
import com.bootdo.proposal.service.ZxjcService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-09 11:50:51
 */
 
@Controller
@RequestMapping("/proposal/jtwy")
public class JtwyController {
	@Autowired
	private GrwyService grwyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DqxzService dqxzService;
	
	@Autowired
	private SswyhService sswyhService;
	
	@Autowired
	private ZxjcService zxjcService;
	
	@Autowired
	private TajbService tajbService;
	
	
	@GetMapping()
	@RequiresPermissions("proposal:jtwy:jtwy")
	String Jtwy(){
	    return "proposal/jtwy/jtwy";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:jtwy:jtwy")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        if(query.get("state") == null) {
        	query.put("state", 1);
		}
		List<GrwyDO> jtwyList = grwyService.list(query);
		int total = grwyService.count(query);
		PageUtils pageUtils = new PageUtils(jtwyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:jtwy:add")
	String add(){
	    return "proposal/jtwy/add";
	}
	
	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:jtwy:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		GrwyDO jtwy = grwyService.get(id);
		UserDO user = userService.get(jtwy.getUserid().longValue());
		jtwy.setUserName(user.getUsername());
		jtwy.setPwd(user.getPassword());
		model.addAttribute("jtwy", jtwy);
		
//		DqxzDO dd = dqxzService.get(jtwy.getSsdqid());
//		model.addAttribute("dqxzName", dd.getDqxz());
		
//		SswyhDO sd = sswyhService.get(jtwy.getSswyhid());
//		model.addAttribute("sswyhName", sd.getSswyhmc());
		
		ZxjcDO zd = zxjcService.get(jtwy.getSsjcid());
		model.addAttribute("ssjcName", zd.getZxjcmc());
		
//		TajbDO td = tajbService.get(jtwy.getSsjbid());
//		model.addAttribute("ssjbName", td.getTajbmc());
		
	    return "proposal/jtwy/edit";
	}
	
	@GetMapping("/grEdit/{id}")
	@RequiresPermissions("proposal:jtwy:edit")
	String grEdit(@PathVariable("id") Integer id,Model model){
		GrwyDO jtwy = grwyService.get(id);
		UserDO user = userService.get(jtwy.getUserid().longValue());
		jtwy.setUserName(user.getUsername());
		jtwy.setPwd(user.getPassword());
		model.addAttribute("jtwy", jtwy);
		
//		DqxzDO dd = dqxzService.get(jtwy.getSsdqid());
//		model.addAttribute("dqxzName", dd.getDqxz());
		
//		SswyhDO sd = sswyhService.get(jtwy.getSswyhid());
//		model.addAttribute("sswyhName", sd.getSswyhmc());
		
		ZxjcDO zd = zxjcService.get(jtwy.getSsjcid());
		model.addAttribute("ssjcName", zd.getZxjcmc());
		
//		TajbDO td = tajbService.get(jtwy.getSsjbid());
//		model.addAttribute("ssjbName", td.getTajbmc());
		
	    return "proposal/jtwy/grEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:jtwy:add")
	public R save(GrwyDO grwy){
		grwy.setType(GrwyDO.JTWY);
		if(grwyService.save(grwy)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:jtwy:edit")
	public R update( GrwyDO grwy){
		grwyService.update(grwy);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:jtwy:remove")
	public R remove( Integer id){
		if(grwyService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:jtwy:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		grwyService.batchRemove(ids);
		return R.ok();
	}
	
}
