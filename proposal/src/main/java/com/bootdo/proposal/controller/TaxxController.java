package com.bootdo.proposal.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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

import com.bootdo.proposal.domain.FhhzDO;
import com.bootdo.proposal.domain.GrwyDO;
import com.bootdo.proposal.domain.GzfwDO;
import com.bootdo.proposal.domain.LbyblsxDO;
import com.bootdo.proposal.domain.SswyhDO;
import com.bootdo.proposal.domain.TaxxDO;
import com.bootdo.proposal.domain.UtilDO;
import com.bootdo.proposal.domain.ZxjcDO;
import com.bootdo.proposal.service.FhhzService;
import com.bootdo.proposal.service.GrwyService;
import com.bootdo.proposal.service.LbyblsxService;
import com.bootdo.proposal.service.SswyhService;
import com.bootdo.proposal.service.TaxxService;
import com.bootdo.proposal.service.UtilService;
import com.bootdo.proposal.service.ZxjcService;
import com.bootdo.proposal.util.DeleteFileUtil;
import com.bootdo.proposal.util.FileToByte;
import com.bootdo.proposal.util.HtmlRemoveLabel;
import com.bootdo.proposal.util.StringUtil;
import com.bootdo.proposal.util.WordUtils;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;

/**
 * 提案信息
 * 
 * @author shipan
 * @email 
 * @date 2018-10-17 14:16:06
 */
 
@Controller
@RequestMapping("/proposal/taxx")
public class TaxxController {
	@Autowired
	private TaxxService taxxService;
	@Autowired
	private GrwyService grwyService;
	@Autowired
	private SswyhService sswyhService;
	@Autowired
	private ZxjcService zxjcService;
	@Autowired
	private LbyblsxService lbyblsxService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	@Autowired
	private FhhzService fhhzService;
	@Autowired
	private UtilService utilService;
	
	
	@GetMapping()
	@RequiresPermissions("proposal:taxx:taxx")
	String Taxx(){
	    return "proposal/taxx/taxx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:taxx:taxx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		
		if(params.get("tatm")!=null && !"".equals(params.get("tatm").toString())) {
			params.put("tatm", "%"+params.get("tatm")+"%");
		}
		
		params.put("createid", ShiroUtils.getUserIdInteger());

         Query query = new Query(params);
		List<TaxxDO> taxxList = taxxService.list(query);
		int total = taxxService.count(query);
		PageUtils pageUtils = new PageUtils(taxxList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("proposal:taxx:add")
	String add(Model model){
		Integer userId = ShiroUtils.getUserIdInteger();
		GrwyDO wy = grwyService.getByUserId(userId);
		model.addAttribute("wy", wy);
		
		SswyhDO sd = sswyhService.get(wy.getSswyhid());
		if(sd != null) {
			model.addAttribute("sswyhName", sd.getSswyhmc());
		}
		
		ZxjcDO zxjc = zxjcService.getNewest();
		model.addAttribute("zxjc", zxjc);
		
	    return "proposal/taxx/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:taxx:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TaxxDO wy = taxxService.get(id);
		model.addAttribute("wy", wy);
		
		if(wy.getSswyhid()!=null) {
			SswyhDO sd = sswyhService.get(wy.getSswyhid());
			model.addAttribute("sswyhName", sd.getSswyhmc());
		}
		
		if(wy.getZxjcid()!=null) {
			ZxjcDO zd = zxjcService.get(wy.getZxjcid());
			model.addAttribute("zxjcName", zd.getZxjcmc());
		}
		
		if(wy.getLbyblsxid()!=null) {
			LbyblsxDO ld = lbyblsxService.get(wy.getLbyblsxid());
			model.addAttribute("lbyblsxName", ld.getTalx());
		}
		
		ZxjcDO zxjc = zxjcService.get(wy.getZxjcid());
		model.addAttribute("zxjc", zxjc);
		
	    return "proposal/taxx/edit";
	}
	
	/**
	 * 判断是否可以提交提案
	 */
	@ResponseBody
	@PostMapping("/judgeZxjcDate")
	public R judgeZxjcDate(){
		
		//1判断个人信息
		GrwyDO gr = grwyService.getByUserId(ShiroUtils.getUserIdInteger());
		if(gr == null) {
			return R.error("个人信息错误");
		}
		if(gr.getType().intValue() == GrwyDO.GRWY) {
			//电话号码,专委会,界别,地区小组,党派,单位,职务,通讯地址
			if(gr.getSjhm()==null || gr.getSswyhid()==null || gr.getSsjbid()==null || gr.getSsdqid()==null || gr.getSsdpid()==null
					|| gr.getSsdpid()==null || gr.getDwmcZw()==null || gr.getTxdz()==null) {
				return R.error("请补充个人信息!");
			}
		}
		
		//2判断截止时间
		/*
		ZxjcDO zd = zxjcService.getNewest();
		Date zdDate = zd.getZjrq();*/
		
		Map<String, Object> params = new HashMap<>();
		params.put("type", 2);
		List<UtilDO> utilList = utilService.list(params);
		UtilDO util = utilList.get(0);
		Date d = StringUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss", util.getText());
		
		if(d!=null && d.getTime() < System.currentTimeMillis()) {//超过界次时间
			return R.error("对不起本次大会已"+StringUtil.getCurrentDate(d,"yyyy-MM-dd HH:mm:ss")+"截止提交提案");
		}
		return R.ok();
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:taxx:add")
	public R save( TaxxDO taxx){
		
		ZxjcDO zd = zxjcService.getNewest();
		Date zdDate = zd.getZjrq();
		
		/*if(zdDate.getTime() < System.currentTimeMillis()) {//超过界次时间
			return R.error("对不起本次大会已"+StringUtil.getCurrentDate(zdDate,"yyyy-MM-dd HH:mm:ss")+"截止提交提案");
		}*/
		
		taxx.setZxjcid(zd.getId());
		
		if(taxx.getCbdwid() == -1) {
			taxx.setCbdwid(null);
		}
		
		if(taxxService.save(taxx)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 保存有文件
	 */
	@ResponseBody
	@PostMapping("/saveAndFile")
	@RequiresPermissions("proposal:taxx:add")
	public R save(TaxxDO taxx, @RequestParam("inputFiles") MultipartFile[] files){
		
		ZxjcDO zd = zxjcService.getNewest();
		Date zdDate = zd.getZjrq();
		
		if(zdDate.getTime() < System.currentTimeMillis()) {//超过界次时间
			return R.error("对不起本次大会已"+StringUtil.getCurrentDate(zdDate,"yyyy-MM-dd HH:mm:ss")+"截止提交提案");
		}
		
		taxx.setZxjcid(zd.getId());
		
		if(taxx.getCbdwid() == -1) {
			taxx.setCbdwid(null);
		}
		
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
			taxx.setFjmc(fjmc);
			taxx.setFjdz(fjdz);
		}
		
		if(taxxService.save(taxx)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除附件
	 */
	@PostMapping( "/removeFj")
	@ResponseBody
	public R remove(@RequestParam Map<String, Object> params){
		Integer id = Integer.parseInt(params.get("id").toString());
		String dz = params.get("key").toString();
		TaxxDO ta = taxxService.get(id);
		String[] fjdzs = ta.getFjdz() != null ? ta.getFjdz().split(",") : null;
		String[] fjmcs = ta.getFjmc() != null ? ta.getFjmc().split(",") : null;
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
			TaxxDO t = new TaxxDO();
			t.setId(id);
			t.setFjdz(fjdz);
			t.setFjmc(fjmc);
			taxxService.update(t);
			
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
		TaxxDO ta = taxxService.get(id);
		if(ta!=null) {
			if(files!=null && files.length>0) {
				String fjmc = ta.getFjmc()!=null && !ta.getFjmc().equals("") ? ta.getFjmc()+"," : "";
				String fjdz = ta.getFjdz()!=null && !ta.getFjdz().equals("") ? ta.getFjdz()+"," : "";
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
				
				TaxxDO t = new TaxxDO();
				t.setId(id);
				t.setFjdz(fjdz);
				t.setFjmc(fjmc);
				taxxService.update(t);
//				gd.setFjmc(fjmc);
//				gd.setFjdz(fjdz);
//				gzfwService.update(gd);
				return R.ok();
			}
		}
		
		
		return R.error();
	}
	
	
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:taxx:edit")
	public R update( TaxxDO taxx){
		taxxService.update(taxx);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	/*@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:taxx:remove")
	public R remove( Integer id){
		if(taxxService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}*/
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:taxx:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		
		String str = "";
		for(Integer id : ids) {
			TaxxDO t = taxxService.get(id);
			if(t.getTastate().intValue() > 0) {
				str += t.getTatm()+",";
			}
		}
		
		if(!"".equals(str)) {
			str = str.substring(0,str.length()-1);
			return R.error("删除失败,不能删除已审核数据["+str+"]");
		}
		
		taxxService.batchRemove(ids);
		return R.ok();
	}
	
	/**
	 * 真`删除
	 */
	@PostMapping( "/realRemove")
	@ResponseBody
	public R realRemove(@RequestParam("ids[]") Integer[] ids){
		
		String str = "";
		for(Integer id : ids) {
			TaxxDO t = taxxService.get(id);
			if(t.getTastate().intValue() > 0) {
				str += t.getTatm()+",";
			}
		}
		
		if(!"".equals(str)) {
			str = str.substring(0,str.length()-1);
			return R.error("删除失败,不能删除已审核数据["+str+"]");
		}
		
		taxxService.realRemove(ids);
		return R.ok();
	}
	
	
	@GetMapping("/search")
	String search(Model model){
	    return "proposal/taxx/search";
	}
	
	@RequestMapping("/exportWord")
    public @ResponseBody void exportWord(HttpServletRequest request, HttpServletResponse response,Integer id){
		String baseUri = request.getRequestURI();
		String baseUrl = request.getRequestURL().toString();
		baseUrl = baseUrl.substring(0, baseUrl.indexOf(baseUri));
		
		Map<String, Object> map = taxxService.exportWordData(id);
		if(map == null || map.size() == 0) {
			return;
		}
//		ZxjcDO zxjc = zxjcService.get(taxx.getZxjcid());
        //获得数据  
        map.put("tanr", HtmlRemoveLabel.getWordString2(map.get("tanr")!=null ? map.get("tanr").toString() : null, baseUrl));
        assemble(map);
        
        try {
//        	String s = request.getSession().getServletContext().getRealPath("image");
        	URL url = getClass().getClassLoader().getResource("templates/proposal/template");
        	String urlStr = url.toString();
        	urlStr = urlStr.substring(6, urlStr.length());
        	if(urlStr.substring(0, 3).equals("usr")) {
        		urlStr = "/"+urlStr;
    		}
        	System.out.println("urlStr:"+urlStr);
        	
            WordUtils.exportMillCertificateWord(request, response, map, map.get("tah")+"-"+map.get("tatm")+map.get("taYj"), urlStr, map.get("ftlFile").toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }    
    }
	
	private Map<String,Object> setEmptyString(Map<String,Object> map,String[] strs){
		if(strs!=null) {
			for(String str : strs) {
				if(map.get(str) == null){
					map.put(str, "");
				}
			}
		}
		return map;
	}
	
	private Map<String,Object> assemble(Map<String,Object> map){

        if(map.get("tanx")!=null && "1".equals(map.get("tanx").toString())) {
        	//个人委员
        	map.put("ftlFile", "grta.ftl");
        }else {//集体委员
        	map.put("ftlFile", "jtta.ftl");
        }
        
        if(map.get("isJgdy")!=null && "1".equals(map.get("isJgdy").toString())) {
        	//是否经过调研
        	map.put("jgdy", "是");
        }else {
        	map.put("jgdy", "否");
        }
        if(map.get("isBrzx")!=null && "1".equals(map.get("isBrzx").toString())) {
        	//是否本人撰写
        	map.put("brzx", "是");
        }else {
        	map.put("brzx", "否");
        }
        if(map.get("isZctrcl")!=null && "1".equals(map.get("isZctrcl").toString())) {
        	//是否他人资料
        	map.put("trcl", "是");
        }else {
        	map.put("trcl", "否");
        }
        if(map.get("isGktanr")!=null && "1".equals(map.get("isGktanr").toString())) {
        	//是否公开提案内容
        	map.put("gklr", "是");
        }else {
        	map.put("gklr", "否");
        }
        
        if(map.get("laState")!=null && "1".equals(map.get("laState").toString())) {
        	//是否立案
        	map.put("la", "是");
        	map.put("taYj", "提案");
        }else if(map.get("laState")!=null && "2".equals(map.get("laState").toString())){
        	map.put("la", "否");
        	map.put("taYj", "意见");
        }else {
        	map.put("la", "否");
        	map.put("taYj", "提案");
        }
        
        if(map.get("isgk")!=null && "1".equals(map.get("isgk").toString())) {
        	//是否公开
        	map.put("gk", "是");
        }else {
        	map.put("gk", "否");
        }
        
        if(map.get("isBa")!=null) {
        	if(map.get("baId")==null) {//主并
        		map.put("zbb", "   主并");
        	}else {//被并
        		map.put("zbb", "   被并");
        	}
        }else {
        	map.put("zbb", "");
        }
        
        map = setEmptyString(map, new String[] {"zxjcmc", "tah", "tatm", "jgdy", "brzx", "trcl", "gklr", "mc", "dwmc_zw", "txdz",
        		"dpmc", "sjhm", "sswyhmc", "dqxz", "tajbmc", "fytaz", "la", "gk", "cbdw", "xbdw", "fbdw", "talx", "ldyj",
        		"yb", "fzr", "zbr", "lxr"});
        
		return map;
	}
	
	@RequestMapping("/exportWordArr")
    public @ResponseBody void exportWordArr(HttpServletRequest request, HttpServletResponse response,String idStr,Integer type){
		String baseUri = request.getRequestURI();
		String baseUrl = request.getRequestURL().toString();
		baseUrl = baseUrl.substring(0, baseUrl.indexOf(baseUri));
        try {
        	String[] idStrArr = idStr.split(",");
        	Integer[] ids = new Integer[idStrArr.length];
        	for(int i=0;i<idStrArr.length;i++) {
        		ids[i] = Integer.parseInt(idStrArr[i]);
        	}
        	
        	List<Map<String, Object>> taxxList = taxxService.exportWordArrData(ids);
        	if(taxxList == null) {
        		return;
        	}
        	
        	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    		ZipOutputStream zip = new ZipOutputStream(outputStream);
    		Map<String,Integer> tatmMap = new HashMap<>();
    		
    		if(type!=null && type.intValue() == 1) {//按办理单位
    			for(Map<String, Object> m : taxxList) {
    				//获得数据  
    				/*Map<String, Object> map = new HashMap<String, Object>(); 
    				map.put("tatm", m.get("tatm"));
    				map.put("tanr", HtmlRemoveLabel.getWordString2(m.get("tanr")!=null ? m.get("tanr").toString() : null, baseUrl));*/
    				Map<String, Object> map = taxxService.exportWordData(Integer.parseInt(m.get("taxxId").toString()));
    				map.put("tanr", HtmlRemoveLabel.getWordString2(map.get("tanr")!=null ? map.get("tanr").toString() : null, baseUrl));
    				assemble(map);
    				
    				URL url = getClass().getClassLoader().getResource("templates/proposal/template");
    				String urlStr = url.toString();
    				urlStr = urlStr.substring(6, urlStr.length());
    				if(urlStr.substring(0, 3).equals("usr")) {
    					urlStr = "/"+urlStr;
    				}
    				
    				byte[] bt = WordUtils.exportMillCertificateWord(map, urlStr, map.get("ftlFile").toString());
    				String tatm = m.get("name")+"/"+m.get("tah")+"-"+m.get("tatm")+map.get("taYj");
    				if(tatmMap.get(tatm)==null) {
    					tatmMap.put(tatm, 1);
    				}else {
    					tatmMap.put(tatm, tatmMap.get(tatm)+1);
    					tatm += tatmMap.get(tatm);
    				}
    				zip.putNextEntry(new ZipEntry(tatm+".doc"));//.substring(config.getString("package").lastIndexOf(".") + 1)
    				zip.write(bt);
    				zip.closeEntry();
    			}
    		}else {//按提案号
    			Map<String,String> ls_mm = new HashMap<>();
    			for(Map<String, Object> m : taxxList) {
    				String fileName = (m.get("tah")==null?"":m.get("tah"))+"-"+m.get("tatm")+".doc";
    				if(ls_mm.get(fileName) == null) {//不存在,保存
    					ls_mm.put(fileName, "");
    				}else {//存在,跳过
    					continue;
    				}
    				
    				//获得数据  
    				/*Map<String, Object> map = new HashMap<String, Object>(); 
    				map.put("tatm", m.get("tatm"));
    				map.put("tanr", HtmlRemoveLabel.getWordString2(m.get("tanr")!=null ? m.get("tanr").toString() : null, baseUrl));*/
    				Map<String, Object> map = taxxService.exportWordData(Integer.parseInt(m.get("taxxId").toString()));
    				map.put("tanr", HtmlRemoveLabel.getWordString2(map.get("tanr")!=null ? map.get("tanr").toString() : null, baseUrl));
    				assemble(map);
    				
    				URL url = getClass().getClassLoader().getResource("templates/proposal/template");
    				String urlStr = url.toString();
    				urlStr = urlStr.substring(6, urlStr.length());
    				if(urlStr.substring(0, 3).equals("usr")) {
    					urlStr = "/"+urlStr;
    				}
    				
    				byte[] bt = WordUtils.exportMillCertificateWord(map, urlStr, map.get("ftlFile").toString());
    				
    				String tatm = m.get("tah")+"-"+m.get("tatm")+map.get("taYj");
    				if(tatmMap.get(tatm)==null) {
    					tatmMap.put(tatm, 1);
    				}else {
    					tatmMap.put(tatm, tatmMap.get(tatm)+1);
    					tatm += tatmMap.get(tatm);
    				}
    				
    				zip.putNextEntry(new ZipEntry(tatm+".doc"));//.substring(config.getString("package").lastIndexOf(".") + 1)
    				zip.write(bt);
    				zip.closeEntry();
    			}
    		}
    		
    		IOUtils.closeQuietly(zip);
    		
    		response.reset();
    		response.setCharacterEncoding("utf-8");  
//            response.setContentType("application/msword");
            response.setContentType("application/octet-stream; charset=UTF-8");
            // 设置浏览器以下载的方式处理该文件名  
            String fileName = "提案文档.zip";  
            response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
    		
    		IOUtils.write(outputStream.toByteArray(), response.getOutputStream());
        	
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }    
    }
	
	@RequestMapping("/exportWordArrFh")
    public @ResponseBody void exportWordArrFh(HttpServletRequest request, HttpServletResponse response, String idStr,Integer type){
        try {
        	String[] idStrArr = idStr.split(",");
        	Integer[] ids = new Integer[idStrArr.length];
        	for(int i=0;i<idStrArr.length;i++) {
        		ids[i] = Integer.parseInt(idStrArr[i]);
        	}
        	
        	List<Map<String, Object>> taxxList = taxxService.exportWordArrFhData(ids);
        	if(taxxList == null) {
        		return;
        	}
        	
        	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    		ZipOutputStream zip = new ZipOutputStream(outputStream);
    		if(type!=null && type.intValue()==1) {
    			for(Map<String, Object> m : taxxList) {
            		//获得数据 
        			if(m.get("dz") == null) {
        				continue;
        			}
        			String typeStr = m.get("type").toString();
        			typeStr = "1".equals(typeStr) ? "复函意见稿" : "复函正式回执";
        			String dz = m.get("dz").toString();
        			dz = dz.substring(7,dz.length());
//        			File file = new File(bootdoConfig.getUploadPath()+dz);
        			dz = dz.replace("/",File.separator);
//        			dz.replace("\\\\","\\");
					System.out.println(dz);
					byte[] bt = FileToByte.File2byte((bootdoConfig.getUploadPath()+dz).replace("/",File.separator));
            		
            		zip.putNextEntry(new ZipEntry(typeStr+File.separator+m.get("name")+File.separator+m.get("mc")));//.substring(config.getString("package").lastIndexOf(".") + 1)
            		zip.write(bt);
                    zip.closeEntry();
            	}
    		}else {
    			Map<String,String> ls_mm = new HashMap<>();
    			for(Map<String, Object> m : taxxList) {
    				String fileName = (m.get("tah")==null?"":m.get("tah"))+"号提案复函-"+m.get("mc");
    				if(ls_mm.get(fileName) == null) {//不存在,保存ta
    					ls_mm.put(fileName, "");
    				}else {//存在,跳过
    					continue;
    				}
            		//获得数据 
        			if(m.get("dz") == null) {
        				continue;
        			}
        			String typeStr = m.get("type").toString();
        			typeStr = "1".equals(typeStr) ? "复函意见稿" : "复函正式回执";
        			String dz = m.get("dz").toString();
        			dz = dz.substring(7,dz.length());
//        			File file = new File(bootdoConfig.getUploadPath()+dz);
        			
            		byte[] bt = FileToByte.File2byte(bootdoConfig.getUploadPath()+dz);
            		
            		zip.putNextEntry(new ZipEntry(fileName));//.substring(config.getString("package").lastIndexOf(".") + 1)
            		zip.write(bt);
                    zip.closeEntry();
            	}
    		}
    		
    		
    		IOUtils.closeQuietly(zip);
    		
    		response.reset();
    		response.setCharacterEncoding("utf-8");  
//            response.setContentType("application/msword");
            response.setContentType("application/octet-stream; charset=UTF-8");
            // 设置浏览器以下载的方式处理该文件名  
            String fileName = "复函压缩包.zip";  
            response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
    		
    		IOUtils.write(outputStream.toByteArray(), response.getOutputStream());
        	
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }    
    }
	
	/**
	 * 导出交办清单
	 * @param request
	 * @param response
	 * @param zxjcId
	 */
	@RequestMapping("/exportJbqd")
    public @ResponseBody void exportJbqd(HttpServletRequest request, HttpServletResponse response,Integer zxjcId){
		String baseUri = request.getRequestURI();
		String baseUrl = request.getRequestURL().toString();
		baseUrl = baseUrl.substring(0, baseUrl.indexOf(baseUri));
		
		Map<String,Object> par = new HashMap<>();
		par.put("zxjcId", zxjcId);
		par.put("lastate", "1");
		List<Map<String,Object>> taxxList = taxxService.tacbdwhz(par);
		if(taxxList == null) {
			return;
		}
		
        Map<String,String> zb = new HashMap<>();
        Map<String,String> xb = new HashMap<>();
        Map<String,String> fb = new HashMap<>();
        Map<String,List<String>> zbList = new HashMap<>();
        Map<String,List<String>> fbList = new HashMap<>();
        Map<String,List<String>> xbList = new HashMap<>();
        List<String> nameList = new ArrayList<>();

        if(taxxList!=null && taxxList.size()>0){
            for(Map<String, Object> e : taxxList){
                if(e.get("name") != null){
                    if(nameList.indexOf(e.get("name").toString()) == -1){
                        nameList.add(e.get("name").toString());
                    }

                    if(e.get("laState")!=null && "1".equals(e.get("laState").toString())){//&& Integer.parseInt(e.get("taState").toString())>3
                        if(e.get("type")!=null && "1".equals(e.get("type").toString())){//承办
                            String ls_str = zb.get(e.get("name").toString());
                            if(ls_str == null){
                                zb.put(e.get("name").toString(), e.get("tah").toString());
                                
                                List<String> l = new ArrayList<>();
                                l.add(e.get("tah").toString());
                                zbList.put(e.get("name").toString(), l);
                            }else{
                            	if(zbList.get(e.get("name").toString()).indexOf(e.get("tah").toString()) == -1) {
                            		ls_str += ","+e.get("tah").toString();
                            		zb.put(e.get("name").toString(), ls_str);
                            		zbList.get(e.get("name").toString()).add(e.get("tah").toString());
                            	}
                            }
                        }else if(e.get("type")!=null && "2".equals(e.get("type").toString())){//分办
                            String ls_str = fb.get(e.get("name").toString());
                            if(ls_str == null){
                                fb.put(e.get("name").toString(), e.get("tah").toString());

                                List<String> l = new ArrayList<>();
                                l.add(e.get("tah").toString());
                                fbList.put(e.get("name").toString(), l);
                            }else{
                            	if(fbList.get(e.get("name").toString()).indexOf(e.get("tah").toString()) == -1) {
                            		ls_str += ","+e.get("tah").toString();
                            		fb.put(e.get("name").toString(), ls_str);
                            		fbList.get(e.get("name").toString()).add(e.get("tah").toString());
                            	}
                            }
                        }else if(e.get("type")!=null && "3".equals(e.get("type").toString())){//协办
                            String ls_str = xb.get(e.get("name").toString());
                            if(ls_str == null){
                                xb.put(e.get("name").toString(), e.get("tah").toString());
                                
                                List<String> l = new ArrayList<>();
                                l.add(e.get("tah").toString());
                                xbList.put(e.get("name").toString(), l);
                            }else{
                            	if(xbList.get(e.get("name").toString()).indexOf(e.get("tah").toString()) == -1) {
                            		ls_str += ","+e.get("tah").toString();
                            		xb.put(e.get("name").toString(), ls_str);
                            		xbList.get(e.get("name").toString()).add(e.get("tah").toString());
                            	}
                            }
                        }
                    }
                }
            }
        }
		
//		ZxjcDO zxjc = zxjcService.get(taxx.getZxjcid());
        //获得数据  
        List<Map<String,Object>> lMap = new ArrayList<>();
        for(String name : nameList) {
        	Map<String,Object> m = new HashMap<>();
        	m.put("name", name);
        	m.put("zbs", zbList.get(name)== null ? 0 : zbList.get(name).size());
        	m.put("zbh", zb.get(name) == null ? "" : zb.get(name));
        	m.put("fbs", fbList.get(name)== null ? 0 : fbList.get(name).size());
        	m.put("fbh", fb.get(name) == null ? "" : fb.get(name));
        	m.put("xbs", xbList.get(name)== null ? 0 : xbList.get(name).size());
        	m.put("xbh", xb.get(name) == null ? "" : xb.get(name));
        	lMap.add(m);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("nian", StringUtil.getCurrentDate("yyyy"));
        map.put("lMap", lMap);
        
        try {
//        	String s = request.getSession().getServletContext().getRealPath("image");
        	URL url = getClass().getClassLoader().getResource("templates/proposal/template");
        	String urlStr = url.toString();
        	urlStr = urlStr.substring(6, urlStr.length());
        	if(urlStr.substring(0, 3).equals("usr")) {
        		urlStr = "/"+urlStr;
    		}
        	System.out.println("urlStr:"+urlStr);
        	
            WordUtils.exportMillCertificateWord(request, response, map, StringUtil.getCurrentDate("yyyy")+"交办清单", urlStr, "jbqd.ftl");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }    
    }
	/**
	 * 导出交办清单,单个
	 * @param request
	 * @param response
	 * @param zxjcId
	 */
	@RequestMapping("/exportJbqdDg")
    public @ResponseBody void exportJbqdDg(HttpServletRequest request, HttpServletResponse response,Integer zxjcId){
		String baseUri = request.getRequestURI();
		String baseUrl = request.getRequestURL().toString();
		baseUrl = baseUrl.substring(0, baseUrl.indexOf(baseUri));
		
		Map<String,Object> par = new HashMap<>();
		par.put("zxjcId", zxjcId);
		par.put("lastate", "1");
		List<Map<String,Object>> taxxList = taxxService.tacbdwhz(par);
		if(taxxList == null) {
			return;
		}
		
        Map<String,String> zb = new HashMap<>();
        Map<String,String> xb = new HashMap<>();
        Map<String,String> fb = new HashMap<>();
        Map<String,List<String>> zbList = new HashMap<>();
        Map<String,List<String>> fbList = new HashMap<>();
        Map<String,List<String>> xbList = new HashMap<>();
        List<String> nameList = new ArrayList<>();

        if(taxxList!=null && taxxList.size()>0){
            for(Map<String, Object> e : taxxList){
                if(e.get("name") != null){
                    if(nameList.indexOf(e.get("name").toString()) == -1){
                        nameList.add(e.get("name").toString());
                    }

                    if(e.get("laState")!=null && "1".equals(e.get("laState").toString())){//&& Integer.parseInt(e.get("taState").toString())>3
                        if(e.get("type")!=null && "1".equals(e.get("type").toString())){//承办
                            String ls_str = zb.get(e.get("name").toString());
                            if(ls_str == null){
                                zb.put(e.get("name").toString(), e.get("tah").toString());
                                
                                List<String> l = new ArrayList<>();
                                l.add(e.get("tah").toString());
                                zbList.put(e.get("name").toString(), l);
                            }else{
                            	if(zbList.get(e.get("name").toString()).indexOf(e.get("tah").toString()) == -1) {
                            		ls_str += ","+e.get("tah").toString();
                            		zb.put(e.get("name").toString(), ls_str);
                            		zbList.get(e.get("name").toString()).add(e.get("tah").toString());
                            	}
                            }
                        }else if(e.get("type")!=null && "2".equals(e.get("type").toString())){//分办
                            String ls_str = fb.get(e.get("name").toString());
                            if(ls_str == null){
                                fb.put(e.get("name").toString(), e.get("tah").toString());

                                List<String> l = new ArrayList<>();
                                l.add(e.get("tah").toString());
                                fbList.put(e.get("name").toString(), l);
                            }else{
                            	if(fbList.get(e.get("name").toString()).indexOf(e.get("tah").toString()) == -1) {
                            		ls_str += ","+e.get("tah").toString();
                            		fb.put(e.get("name").toString(), ls_str);
                            		fbList.get(e.get("name").toString()).add(e.get("tah").toString());
                            	}
                            }
                        }else if(e.get("type")!=null && "3".equals(e.get("type").toString())){//协办
                            String ls_str = xb.get(e.get("name").toString());
                            if(ls_str == null){
                                xb.put(e.get("name").toString(), e.get("tah").toString());
                                
                                List<String> l = new ArrayList<>();
                                l.add(e.get("tah").toString());
                                xbList.put(e.get("name").toString(), l);
                            }else{
                            	if(xbList.get(e.get("name").toString()).indexOf(e.get("tah").toString()) == -1) {
                            		ls_str += ","+e.get("tah").toString();
                            		xb.put(e.get("name").toString(), ls_str);
                            		xbList.get(e.get("name").toString()).add(e.get("tah").toString());
                            	}
                            }
                        }
                    }
                }
            }
        }
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
        
        try {

        	URL url = getClass().getClassLoader().getResource("templates/proposal/template");
        	String urlStr = url.toString();
        	urlStr = urlStr.substring(6, urlStr.length());
        	if(urlStr.substring(0, 3).equals("usr")) {
        		urlStr = "/"+urlStr;
    		}
        	System.out.println("urlStr:"+urlStr);
        	
        	//获得数据  
	        List<Map<String,Object>> lMap = new ArrayList<>();
	        Map<String,Object> map = new HashMap<>();
	        
	        for(String name : nameList) {
	        	Map<String,Object> m = new HashMap<>();
	        	m.put("name", name);
	        	m.put("zbs", zbList.get(name)== null ? 0 : zbList.get(name).size());
	        	m.put("zbh", zb.get(name) == null ? "" : zb.get(name));
	        	m.put("fbs", fbList.get(name)== null ? 0 : fbList.get(name).size());
	        	m.put("fbh", fb.get(name) == null ? "" : fb.get(name));
	        	m.put("xbs", xbList.get(name)== null ? 0 : xbList.get(name).size());
	        	m.put("xbh", xb.get(name) == null ? "" : xb.get(name));
	        	lMap.add(m);
	        	
	            map.put("nian", StringUtil.getCurrentDate("yyyy"));
	            map.put("lMap", lMap);
	        	
	        	byte[] bt = WordUtils.exportMillCertificateWord(map, urlStr, "jbqd.ftl");
				
				zip.putNextEntry(new ZipEntry(m.get("name")+"-交办清单.doc"));//.substring(config.getString("package").lastIndexOf(".") + 1)
				zip.write(bt);
				zip.closeEntry();
				
				lMap.clear();
				map.clear();
	        }
	        
	        IOUtils.closeQuietly(zip);
    		
    		response.reset();
    		response.setCharacterEncoding("utf-8");  
            response.setContentType("application/octet-stream; charset=UTF-8");
            // 设置浏览器以下载的方式处理该文件名  
            String fileName = "提案文档.zip";  
            response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
    		
    		IOUtils.write(outputStream.toByteArray(), response.getOutputStream());
        	
//            WordUtils.exportMillCertificateWord(request, response, map, StringUtil.getCurrentDate("yyyy")+"交办清单", urlStr, "jbqd.ftl");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }    
    }
	
	@GetMapping("/qtta")
	@RequiresPermissions("proposal:taxx:qtta")
	String qtta(){
	    return "proposal/taxx/qtta";
	}
	
	@ResponseBody
	@GetMapping("/qtList")
	@RequiresPermissions("proposal:taxx:qtta")
	public PageUtils qtList(@RequestParam Map<String, Object> params){
		//查询列表数据
		
		if(params.get("tatm")!=null && !"".equals(params.get("tatm").toString())) {
			params.put("tatm", "%"+params.get("tatm")+"%");
		}
		
//		params.put("createid", ShiroUtils.getUserIdInteger());
        Query query = new Query(params);
		List<TaxxDO> taxxList = taxxService.list(query);
		int total = taxxService.count(query);
		PageUtils pageUtils = new PageUtils(taxxList, total);
		return pageUtils;
	}
}
