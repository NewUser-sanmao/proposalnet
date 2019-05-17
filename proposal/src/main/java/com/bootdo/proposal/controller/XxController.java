package com.bootdo.proposal.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.proposal.domain.GrwyDO;
import com.bootdo.proposal.domain.TaxxCbdwDO;
import com.bootdo.proposal.domain.TaxxDO;
import com.bootdo.proposal.domain.XxDO;
import com.bootdo.proposal.service.GrwyService;
import com.bootdo.proposal.service.TaxxCbdwService;
import com.bootdo.proposal.service.TaxxService;
import com.bootdo.proposal.service.XxService;
import com.bootdo.proposal.util.StringUtil;
import com.bootdo.system.service.RoleService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;

/**
 * 消息
 * 
 * @author shipan
 * @email 
 * @date 2018-10-31 14:06:43
 */
 
@Controller
@RequestMapping("/proposal/xx")
public class XxController {
	@Autowired
	private XxService xxService;
	@Autowired
	private TaxxService taxxService;
	@Autowired
	private TaxxCbdwService taxxCbdwService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	@Autowired
	private GrwyService grwyService;
	
	
	@GetMapping("/{lx}")
	@RequiresPermissions("proposal:xx:xx")
	String Xx(@PathVariable("lx") Integer lx,Integer taxxid,Model model,Integer dealType){
		model.addAttribute("lx", lx);
		model.addAttribute("taxxid", taxxid);
		model.addAttribute("dealType", dealType);//处理提案角色类别  0：承办单位 1：协办单位 2：分办单位
		System.out.println("dealType22222 :dealType22222 "+dealType);
		return "proposal/xx/xx";
	}
	
	@GetMapping()
	@RequiresPermissions("proposal:xx:xx")
	String Xx(Model model){
		GrwyDO g = grwyService.getByUserId(ShiroUtils.getUserIdInteger());
		if(g != null) {
			if(g.getType().intValue()==1 || g.getType().intValue()==2) {
				model.addAttribute("lx", 1);
			}else if(g.getType().intValue()==3){
				model.addAttribute("lx", 2);
			}
		}
	    return "proposal/xx/xx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:xx:xx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<Long> role = roleService.getRoleIds(ShiroUtils.getUserIdInteger().longValue());
		if(role.indexOf(1l)!=-1 || role.indexOf(4l)!=-1 || role.indexOf(6l)!=-1) {
			
		}else {
			params.put("br", ShiroUtils.getUserIdInteger());
		}
		
        Query query = new Query(params);
		List<Map<String,Object>> xxList = xxService.listMap(query);
		int total = xxService.countMap(query);
		PageUtils pageUtils = new PageUtils(xxList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/wrapperList")
	public PageUtils wrapperList(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("jsr", ShiroUtils.getUserIdInteger());
		params.put("offset", 0);
		params.put("limit", 3);
		params.put("state", "0");
		
		
        Query query = new Query(params);
		List<Map<String,Object>> xxList = xxService.listMap(query);
		for(Map<String,Object> map : xxList) {
			map.put("before", DateUtils.getTimeBefore(StringUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss", map.get("createTime").toString())));
		}
		
		int total = xxService.countMap(query);
		PageUtils pageUtils = new PageUtils(xxList, total);
		return pageUtils;
	}
	
	@GetMapping("/add/{lx}")
		@RequiresPermissions("proposal:xx:add")
		String add(@PathVariable("lx") Integer lx,Integer taxxid,Model model,Integer dealType){
			System.out.println(dealType);
			model.addAttribute("lx", lx);
			model.addAttribute("taxxid", taxxid);
			model.addAttribute("dealType", dealType);//处理提案角色类别  0：承办单位 1：协办单位 2：分办单位
		model.addAttribute("userId", ShiroUtils.getUserIdInteger());
	    return "proposal/xx/add";
	}

	@GetMapping("/edit/{id}/{lx}")
	@RequiresPermissions("proposal:xx:edit")
	String edit(@PathVariable("id") Integer id,@PathVariable("lx") Integer lx,Integer taxxid,Model model){
		XxDO xx = xxService.get(id);
		model.addAttribute("xx", xx);
		model.addAttribute("taxxid", xx.getTaxxid());
		if(xx.getFsr().intValue() == ShiroUtils.getUserIdInteger().intValue()) {
			model.addAttribute("lx", lx);
			GrwyDO grwy = grwyService.getByUserId(xx.getJsr());
			if(grwy != null) {
				model.addAttribute("jsr", grwy.getDwmcid());
			}
		}else if(xx.getJsr().intValue() == ShiroUtils.getUserIdInteger().intValue()) {
			model.addAttribute("lx", 2);
		}
		if(xx.getJsr().intValue() == ShiroUtils.getUserIdInteger().intValue()) {
			xx.setState(1);
			xxService.update(xx);
		}
	    return "proposal/xx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:xx:add")
	public R save( XxDO xx, Integer[] mbdw, Integer lx, Integer type,@RequestParam("inputFiles") MultipartFile[] files){
		if(files!=null && files.length>0) {
			String fjmc = "";
			String fjdz = "";
			for(MultipartFile file : files) {
				// 获取文件名
				String fileName = file.getOriginalFilename();
				fjmc += fileName + ",";
				// 获取文件的后缀名
				String suffixName = fileName.substring(fileName.lastIndexOf("."));
				// 文件上传后的路径
				String filePath = bootdoConfig.getUploadPath()+"/fj/";
				// 解决中文问题，liunx下中文路径，图片显示问题
				String fileUrl = UUID.randomUUID() + suffixName;
				fjdz += "/files/fj/"+fileUrl + ",";
				
				try {
					FileUtil.uploadFile(file.getBytes(), filePath, fileUrl);
					FileDO sysFile = new FileDO(FileType.fileType(fileUrl), "/files/fj/" + fileUrl, new Date());
					sysFileService.save(sysFile);
				} catch (Exception e) {
					return R.error();
				}
			}
			fjmc = fjmc.substring(0, fjmc.length()-1);
			fjdz = fjdz.substring(0, fjdz.length()-1);
			xx.setFjmc(fjmc);
			xx.setFjdz(fjdz);
		}
		if(xxService.save(xx, mbdw, lx, type)>0){
			return R.ok();
		}
		return R.error();
	}
	
	@GetMapping("/adminAdd")
	@RequiresPermissions("proposal:xx:add")
	String adminAdd(Integer taxxid,Model model){
		model.addAttribute("taxxid", taxxid);
	    return "proposal/xx/adminAdd";
	}
	
	/**
	 * 管理员保存
	 */
	@ResponseBody
	@PostMapping("/saveAdminAdd")
	public R saveAdminAdd(XxDO xx, Integer[] mbdw, Integer type, @RequestParam("inputFiles") MultipartFile[] files){
		if(files!=null && files.length>0) {
			String fjmc = "";
			String fjdz = "";
			for(MultipartFile file : files) {
				// 获取文件名
				String fileName = file.getOriginalFilename();
				fjmc += fileName + ",";
				// 获取文件的后缀名
				String suffixName = fileName.substring(fileName.lastIndexOf("."));
				// 文件上传后的路径
				String filePath = bootdoConfig.getUploadPath()+"/fj/";
				// 解决中文问题，liunx下中文路径，图片显示问题
				String fileUrl = UUID.randomUUID() + suffixName;
				fjdz += "/files/fj/"+fileUrl + ",";
				
				try {
					FileUtil.uploadFile(file.getBytes(), filePath, fileUrl);
					FileDO sysFile = new FileDO(FileType.fileType(fileUrl), "/files/fj/" + fileUrl, new Date());
					sysFileService.save(sysFile);
				} catch (Exception e) {
					return R.error();
				}
			}
			fjmc = fjmc.substring(0, fjmc.length()-1);
			fjdz = fjdz.substring(0, fjdz.length()-1);
			xx.setFjmc(fjmc);
			xx.setFjdz(fjdz);
		}
		if(xxService.saveAdminAdd(xx, mbdw, type)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:xx:edit")
	public R update( XxDO xx){
		xxService.update(xx);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:xx:remove")
	public R remove( Integer id){
		if(xxService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:xx:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		xxService.batchRemove(ids);
		return R.ok();
	}
	
}
