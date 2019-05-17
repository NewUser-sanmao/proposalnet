package com.bootdo.proposal.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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

import com.bootdo.proposal.domain.GzfwDO;
import com.bootdo.proposal.service.GzfwService;
import com.bootdo.proposal.util.DeleteFileUtil;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 工作服务
 * 
 * @author shipan
 * @email 
 * @date 2018-10-25 10:42:22
 */
 
@Controller
@RequestMapping("/proposal/gzfw")
public class GzfwController {
	@Autowired
	private GzfwService gzfwService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	
	@GetMapping()
	@RequiresPermissions("proposal:gzfw:gzfw")
	String Gzfw(){
	    return "proposal/gzfw/gzfw";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:gzfw:gzfw")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<GzfwDO> gzfwList = gzfwService.list(query);
		int total = gzfwService.count(query);
		PageUtils pageUtils = new PageUtils(gzfwList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:gzfw:add")
	String add(){
	    return "proposal/gzfw/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:gzfw:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		GzfwDO gzfw = gzfwService.get(id);
		model.addAttribute("gzfw", gzfw);
	    return "proposal/gzfw/edit";
	}
	
	@GetMapping("/read/{id}")
	@RequiresPermissions("proposal:gzfw:read")
	String read(@PathVariable("id") Integer id,Model model){
		GzfwDO gzfw = gzfwService.get(id);
		model.addAttribute("gzfw", gzfw);
	    return "proposal/gzfw/read";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:gzfw:add")
	public R save( GzfwDO gzfw, @RequestParam("inputFiles") MultipartFile[] files){
		
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
			gzfw.setFjmc(fjmc);
			gzfw.setFjdz(fjdz);
		}
		
		if(gzfwService.save(gzfw)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:gzfw:edit")
	public R update( GzfwDO gzfw){
		gzfwService.update(gzfw);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:gzfw:remove")
	public R remove( Integer id){
		if(gzfwService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:gzfw:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		gzfwService.batchRemove(ids);
		return R.ok();
	}
	
	/**
	 * 删除附件
	 */
	@PostMapping( "/removeFj")
	@ResponseBody
	public R remove(@RequestParam Map<String, Object> params){
		Integer id = Integer.parseInt(params.get("id").toString());
		String dz = params.get("key").toString();
		GzfwDO gd = gzfwService.get(id);
		String[] fjdzs = gd.getFjdz() != null ? gd.getFjdz().split(",") : null;
		String[] fjmcs = gd.getFjmc() != null ? gd.getFjmc().split(",") : null;
		int index = -1;
		for(int i=0;i<fjdzs.length;i++) {
			if(dz.equals(fjdzs[i])) {
				String delDz = dz.substring(6, dz.length());
				DeleteFileUtil.delete(bootdoConfig.getUploadPath()+delDz);
				index = i;
				break;
			}
		}
		
		if(index != -1) {
			String fjdz = "";
			String fjmc = "";
			for(int i=0;i<fjdzs.length;i++) {
				if(i != index) {
					fjdz += fjdzs[i] + ",";
					fjmc += fjmcs[i] + ",";
				}
			}
			if(fjdz.length() > 0) {
				fjdz = fjdz.substring(0, fjdz.length()-1);
				fjmc = fjmc.substring(0, fjmc.length()-1);
			}
			GzfwDO g = new GzfwDO();
			g.setId(id);
			g.setFjdz(fjdz);
			g.setFjmc(fjmc);
			gzfwService.update(g);
			
			return R.ok();
		}
		
		return R.error();
	}
	
	/**
	 * 上传
	 */
	@PostMapping( "/uploadFj")
	@ResponseBody
	public R uploadFj(@RequestParam("id") Integer id,@RequestParam("inputFiles") MultipartFile[] files){
		GzfwDO gd = gzfwService.get(id);
		if(gd!=null) {
			if(files!=null && files.length>0) {
				String fjmc = gd.getFjmc()!=null && !gd.getFjmc().equals("") ? gd.getFjmc()+"," : "";
				String fjdz = gd.getFjdz()!=null && !gd.getFjdz().equals("") ? gd.getFjdz()+"," : "";
				for(MultipartFile file : files) {
					// 获取文件名
					String fileName = file.getOriginalFilename();
					fjmc += fileName + ",";
					// 获取文件的后缀名
					String suffixName = fileName.substring(fileName.lastIndexOf("."));
					// 文件上传后的路径
					String filePath = bootdoConfig.getUploadPath();
					// 解决中文问题，liunx下中文路径，图片显示问题
					String fileUrl = "/fj/"+ UUID.randomUUID() + suffixName;
					fjdz += "/files"+fileUrl + ",";
					
					File dest = new File(filePath + fileUrl);
					// 检测是否存在目录
					if (!dest.getParentFile().exists()) {
						dest.getParentFile().mkdirs();
					}
					
					try {
						file.transferTo(dest);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				fjmc = fjmc.substring(0, fjmc.length()-1);
				fjdz = fjdz.substring(0, fjdz.length()-1);
				gd.setFjmc(fjmc);
				gd.setFjdz(fjdz);
				gzfwService.update(gd);
				return R.ok();
			}
		}
		
		
		return R.error();
	}
	
	
	
	public static void main(String[] args) {
		// 获取文件名
		String fileName = "啊啊啊啊.啊啊.xml";
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		System.out.println(suffixName);
	}
}
