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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.proposal.domain.DpDO;
import com.bootdo.proposal.domain.DqxzDO;
import com.bootdo.proposal.domain.GrwyDO;
import com.bootdo.proposal.domain.SswyhDO;
import com.bootdo.proposal.domain.TajbDO;
import com.bootdo.proposal.domain.ZxjcDO;
import com.bootdo.proposal.service.DpService;
import com.bootdo.proposal.service.DqxzService;
import com.bootdo.proposal.service.GrwyService;
import com.bootdo.proposal.service.SswyhService;
import com.bootdo.proposal.service.TajbService;
import com.bootdo.proposal.service.ZxjcService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-13 09:29:05
 */
 
@Controller
@RequestMapping("/proposal/grwy")
public class GrwyController {
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
	
	@Autowired
	private DpService dpService;
	
	@GetMapping()
	@RequiresPermissions("proposal:grwy:grwy")
	String Grwy(){
	    return "proposal/grwy/grwy";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:grwy:grwy")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        if(query.get("state") == null) {
        	query.put("state", 1);
		}
		List<GrwyDO> grwyList = grwyService.list(query);
		int total = grwyService.count(query);
		PageUtils pageUtils = new PageUtils(grwyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:grwy:add")
	String add(){
	    return "proposal/grwy/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:grwy:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		GrwyDO grwy = grwyService.get(id);
		UserDO user = userService.get(grwy.getUserid().longValue());
		grwy.setUserName(user.getUsername());
		grwy.setPwd(user.getPassword());
		model.addAttribute("grwy", grwy);
		
		DqxzDO dd = dqxzService.get(grwy.getSsdqid());
		model.addAttribute("ssdqName", dd==null ? "" : dd.getDqxz());
		
		SswyhDO sd = sswyhService.get(grwy.getSswyhid());
		model.addAttribute("sswyhName", sd==null ? "" : sd.getSswyhmc());
		
		ZxjcDO zd = zxjcService.get(grwy.getSsjcid());
		model.addAttribute("ssjcName", zd==null ? "" : zd.getZxjcmc());
		
		TajbDO td = tajbService.get(grwy.getSsjbid());
		model.addAttribute("ssjbName", td==null ? "" : td.getTajbmc());
		
		DpDO dp = dpService.get(grwy.getSsdpid());
		model.addAttribute("ssdpName", dp==null ? "" : dp.getDpmc());
		
	    return "proposal/grwy/edit";
	}
	
	@GetMapping("/wyEdit")
	@RequiresPermissions("proposal:grwy:wyEdit")
	String wyEdit(Model model){
		GrwyDO grwy = grwyService.getByUserId(ShiroUtils.getUserIdInteger());
		if(grwy.getType().intValue() == GrwyDO.GRWY) {
			UserDO user = userService.get(grwy.getUserid().longValue());
			grwy.setUserName(user.getUsername());
			grwy.setPwd(user.getPassword());
			model.addAttribute("grwy", grwy);
			
			DqxzDO dd = dqxzService.get(grwy.getSsdqid());
			model.addAttribute("ssdqName", dd==null ? "" : dd.getDqxz());
			
			SswyhDO sd = sswyhService.get(grwy.getSswyhid());
			model.addAttribute("sswyhName", sd==null ? "" : sd.getSswyhmc());
			
			ZxjcDO zd = zxjcService.get(grwy.getSsjcid());
			model.addAttribute("ssjcName", zd==null ? "" : zd.getZxjcmc());
			
			TajbDO td = tajbService.get(grwy.getSsjbid());
			model.addAttribute("ssjbName", td==null ? "" : td.getTajbmc());
			
			DpDO dp = dpService.get(grwy.getSsdpid());
			model.addAttribute("ssdpName", dp==null ? "" : dp.getDpmc());
			
			return "proposal/grwy/wyEdit";
		}else {
			UserDO user = userService.get(grwy.getUserid().longValue());
			grwy.setUserName(user.getUsername());
			grwy.setPwd(user.getPassword());
			model.addAttribute("jtwy", grwy);
			
			/*DqxzDO dd = dqxzService.get(grwy.getSsdqid());
			model.addAttribute("dqxzName", dd.getDqxz());
			
			SswyhDO sd = sswyhService.get(grwy.getSswyhid());
			model.addAttribute("sswyhName", sd.getSswyhmc());
			*/
			ZxjcDO zd = zxjcService.get(grwy.getSsjcid());
			model.addAttribute("ssjcName", zd.getZxjcmc());
			/*
			TajbDO td = tajbService.get(grwy.getSsjbid());
			model.addAttribute("ssjbName", td.getTajbmc());
			*/
		    return "proposal/jtwy/grEdit";
		}
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:grwy:add")
	public R save( GrwyDO grwy, String userName, String pwd){
		grwy.setType(GrwyDO.GRWY);
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
	@RequiresPermissions("proposal:grwy:edit")
	public R update( GrwyDO grwy){
		grwyService.update(grwy);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:grwy:remove")
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
	@RequiresPermissions("proposal:grwy:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		grwyService.batchRemove(ids);
		return R.ok();
	}
	
	@GetMapping("/dr")
	@RequiresPermissions("proposal:grwy:dr")
	String dr(){
	    return "proposal/grwy/dr";
	}
	
	/**
     * 导入excel
     * @param upFile
     * @return
     */
	@ResponseBody
	@PostMapping(value="/upExcel")
	public R upExcel(@RequestParam("inputFile") MultipartFile upFile){
		R r = R.ok();
		try {
			if(upFile != null){
				String filename=upFile.getOriginalFilename();
				if(filename.lastIndexOf(".") != -1){
					String fLast = filename.substring(filename.lastIndexOf("."), filename.length());
					if(".xls".equals(fLast) || ".xlsx".equals(fLast)){
						//设计想法:1全量导入,先把所有的委员的账户都设置为停用.然后在开放存在的,新增的则新增.
						Map<String,Object> map = grwyService.saveExcel(upFile, 1);
						r.put("result", map);
						return r;
					}else{//后缀不对
						r = R.error(2,"后缀不对");
						return r;
					}
				}else{//后缀不对
					r = R.error(2,"后缀不对"); 
					return r;
				}
			}else{//文件为空
				r = R.error(3,"文件为空"); 
				return r;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 重置密码
	 */
	@PostMapping( "/resetPwd")
	@ResponseBody
	public R resetPwd( Integer id){
		if(grwyService.resetPwd(id)>0){
		return R.ok();
		}
		return R.error();
	}
}
