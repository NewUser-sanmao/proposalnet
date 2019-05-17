package com.bootdo.proposal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.proposal.domain.TaxxDO;
import com.bootdo.proposal.excel.ExcelException;
import com.bootdo.proposal.excel.ExcelUtil;
import com.bootdo.proposal.service.TaxxService;
import com.bootdo.common.utils.PageUtils;
/**
 * 提案信息
 * 
 * @author ZengLiang
 * @email 
 * @date 2018-10-17 14:16:06
 */
 
@Controller
@RequestMapping("/proposal/bltnyjtj")
public class BltnYjtjController {
	@Autowired
	private TaxxService taxxService;
	
	
	
	@GetMapping("/bltnyjtj")
	@RequiresPermissions("proposal:tjfx:bltnyjtj")
	String bltnYjtj(){
	    return "proposal/tjfx/bltnyjtj";
	}
	
	@GetMapping("/wyxxTaqk")
	@RequiresPermissions("proposal:tjfx:wyxxTaqk")
	String wyxxTaqk(){
	    return "proposal/tjfx/wyxxTaqk";
	}
	
	@GetMapping("/jtxxTaqk")
	@RequiresPermissions("proposal:tjfx:jtxxTaqk")
	String jtxxTaqk(){
	    return "proposal/tjfx/jtxxTaqk";
	}
	
	
	/**
	 * 办理提案、意见统计-查询
	 * 
	 * @author ZengLiang
	 */
	@ResponseBody
	@GetMapping("/bltnYjtjList")
	@RequiresPermissions("proposal:tjfx:bltnyjtj")
	public PageUtils bltnYjtjList(@RequestParam Map<String, Object> params){
		
		List<Map<String,Object>> taxxList = taxxService.bltayjtj(params);
		//int total = taxxService.tablCount(params);
		
		PageUtils pageUtils = new PageUtils(taxxList, 0);
		return pageUtils;
	
	}
	
	/**
	 * 办理提案、意见统计-导出
	 * 
	 * @author ZengLiang
	 */
	 @ResponseBody
	    @RequestMapping("/bltnyjtj_downLoad")
	    public void downLoad(@RequestParam Map<String, Object> params, HttpServletResponse response){
	       

	            List<Map<String, Object>> taxxList = taxxService.bltayjtj(params);
	            List<TaxxDO> vlist = new LinkedList<>();
	            taxxList.forEach(e ->
	             {
	            	 TaxxDO tzhl = new TaxxDO();
	            	    tzhl.setCbdw(null!=e.get("name")&&""!=e.get("name")?e.get("name").toString():"-");
	            	    tzhl.setTah(null!=e.get("tah")&&""!=e.get("tah")?e.get("tah").toString():"-");
	            	    tzhl.setCzwt(null!=e.get("tahCount")&&""!=e.get("tahCount")?e.get("tahCount").toString():"-");
	            	    tzhl.setDcjy(null!=e.get("yjh")&&""!=e.get("yjh")?e.get("yjh").toString():"-");
	            	    tzhl.setDwfzr(null!=e.get("yjhCount")&&""!=e.get("yjhCount")?e.get("yjhCount").toString():"-");
	            	    
	                 vlist.add(tzhl);
	            });
	            LinkedHashMap<String, String> fieldMap=new LinkedHashMap<>();
	             fieldMap.put("cbdw","单位名称");
	             fieldMap.put("tah","提案号");
	             fieldMap.put("czwt","提案数");
	             fieldMap.put("dcjy","意见号");
	             fieldMap.put("dwfzr","意见数");
	             String fieldName="办理提案、意见统计";
	            try {
	                ExcelUtil.listToExcel(vlist, fieldMap, fieldName, response);
	            } catch (ExcelException e) {
	                e.printStackTrace();
	            }
	        
	    }
	    
	
	
	 
		 /**
		 * 委员撰写提案情况-查询
		 * 
		 * @author ZengLiang
		 */
		@ResponseBody
		@GetMapping("/wyxxTaqkList")
		@RequiresPermissions("proposal:tjfx:wyxxTaqk")
		public PageUtils wyxxTaqkList(@RequestParam Map<String, Object> params){
			
			 Map<String,String> tah=new HashMap<>();
			 Map<String,String> yjh=new HashMap<>();
			 Map<String,String> ths=new HashMap<>();
			
			 List<String> mclist = new ArrayList<>();
			 
			 params.put("type", 1);
			 List<Map<String,Object>> taxxList = taxxService.zxtaqk(params);
			 
			if(taxxList!=null && taxxList.size()>0){
				 for (Map<String, Object> e : taxxList) {
					  if(e.get("mc") != null){
						  if(mclist.indexOf(e.get("mc").toString()) == -1){mclist.add(e.get("mc").toString());}
						 
						  if(e.get("laState")!=null && "1".equals(e.get("laState").toString())){
							    String ls_str = tah.get(e.get("mc").toString());
	                            if(ls_str == null){
	                            	tah.put(e.get("mc").toString(), e.get("tah").toString());
	                            }else{
	                                ls_str += ","+e.get("tah").toString();
	                                tah.put(e.get("mc").toString(), ls_str);
	                            }
						  }else if(e.get("laState")!=null && "2".equals(e.get("laState").toString())){
							    String ls_str = yjh.get(e.get("mc").toString());
	                            if(ls_str == null){
	                            	yjh.put(e.get("mc").toString(), e.get("lsh").toString());
	                            }else{
	                                ls_str += ","+e.get("lsh").toString();
	                                yjh.put(e.get("mc").toString(), ls_str);
	                            }
						  }else if(e.get("laState")!=null && "3".equals(e.get("laState").toString())){
							    String ls_str = ths.get(e.get("mc").toString());
	                            if(ls_str == null){
	                            	ths.put(e.get("mc").toString(), e.get("lsh").toString());
	                            }else{
	                                ls_str += ","+e.get("lsh").toString();
	                                ths.put(e.get("mc").toString(), ls_str);
	                            }
						  }
					 }
				 }
			}
			List<Map<String,Object>> retMap = new ArrayList<>();
	        for(String mc : mclist){
	            Map<String,Object> ls_map = new HashMap<>();
	            ls_map.put("mc",mc);
	            
	            if(tah.get(mc) == null){
	                ls_map.put("tas",0);
	                ls_map.put("tah","");
	            }else{
	                String[] lsArr = tah.get(mc).split(",");
	                ls_map.put("tas",lsArr.length);
	                ls_map.put("tah",tah.get(mc));
	            }
	            
	            if(yjh.get(mc) == null){
	                ls_map.put("yjs",0);
	                ls_map.put("yjh","");
	            }else{
	                String[] lsArr = yjh.get(mc).split(",");
	                ls_map.put("yjs",lsArr.length);
	                ls_map.put("yjh",yjh.get(mc));
	            }
	            
	            if(ths.get(mc) == null){
	                ls_map.put("ths",0);
	            }else{
	                String[] lsArr = ths.get(mc).split(",");
	                ls_map.put("ths",lsArr.length);
	            }
	            
	            retMap.add(ls_map);
		    }
			
			
			int total = taxxService.tablCount(params);
			
			PageUtils pageUtils = new PageUtils(retMap, total);
			return pageUtils;
		
		}
		
		/**
		 * 委员撰写提案情况-导出
		 * 
		 * @author ZengLiang
		 */
		 @ResponseBody
		    @RequestMapping("/wyxxTaqk_downLoad")
		    public void wyxxTaqkDownLoad(@RequestParam Map<String, Object> params, HttpServletResponse response){
		       
			        params.put("type", 1);
		            List<Map<String, Object>> taxxList = taxxService.zxtaqk(params);
		            
		            List<TaxxDO> vlist = new LinkedList<>();
		            taxxList.forEach(e ->
		             {
		            	 TaxxDO tzhl = new TaxxDO();
		            	    tzhl.setCbdw(null!=e.get("mc")&&""!=e.get("mc")?e.get("mc").toString():"-");
		            	    tzhl.setCzwt(null!=e.get("taxxId")&&""!=e.get("taxxId")?e.get("taxxId").toString():"-");
		            	    tzhl.setTah(null!=e.get("tah")&&""!=e.get("tah")?e.get("tah").toString():"-");
		            	    tzhl.setLsh(null!=e.get("lsh")&&""!=e.get("lsh")?e.get("lsh").toString():"-");
		            	    tzhl.setLastate(null!=e.get("laState")&&""!=e.get("laState")?(Integer) e.get("laState"):0);
		            	    tzhl.setTastate(null!=e.get("taState")&&""!=e.get("taState")?(Integer) e.get("taState"):0);
		            	    
		                 vlist.add(tzhl);
		            });
		            LinkedHashMap<String, String> fieldMap=new LinkedHashMap<>();
		             fieldMap.put("cbdw","委员名称");
		             fieldMap.put("czwt","提案号");
		             fieldMap.put("tah","提案数");
		             fieldMap.put("lsh","意见号");
		             fieldMap.put("lastate","意见数");
		             fieldMap.put("tastate","不立案退回件数");
		             String fieldName="委员撰写提案情况";
		            try {
		                ExcelUtil.listToExcel(vlist, fieldMap, fieldName, response);
		            } catch (ExcelException e) {
		                e.printStackTrace();
		            }
		        
		    }
		    
		
		
		
		
		
		
		/**
		 * 集体撰写提案情况-查询
		 * 
		 * @author ZengLiang
		 */
		@ResponseBody
		@GetMapping("/jtxxTaqkList")
		@RequiresPermissions("proposal:tjfx:jtxxTaqk")
		public PageUtils jtxxTaqkList(@RequestParam Map<String, Object> params){
			
			Map<String,String> tah=new HashMap<>();
			Map<String,String> yjh=new HashMap<>();
			Map<String,String> ths=new HashMap<>();
			
			List<String> mclist = new ArrayList<>();
			
			params.put("type", 2);
			
			List<Map<String,Object>> taxxList = taxxService.zxtaqk(params);
			
			if(taxxList!=null && taxxList.size()>0){
				 for (Map<String, Object> e : taxxList) {
					  if(e.get("mc") != null){
						  if(mclist.indexOf(e.get("mc").toString()) == -1){mclist.add(e.get("mc").toString());}
						 
						  if(e.get("laState")!=null && "1".equals(e.get("laState").toString()) && Integer.parseInt(e.get("taState").toString())>3){
							    String ls_str = tah.get(e.get("mc").toString());
	                            if(ls_str == null){
	                            	tah.put(e.get("mc").toString(), e.get("tah").toString());
	                            }else{
	                                ls_str += ","+e.get("tah").toString();
	                                tah.put(e.get("mc").toString(), ls_str);
	                            }
						  }else if(e.get("laState")!=null && "2".equals(e.get("laState").toString())){
							    String ls_str = yjh.get(e.get("mc").toString());
	                            if(ls_str == null){
	                            	yjh.put(e.get("mc").toString(), e.get("lsh").toString());
	                            }else{
	                                ls_str += ","+e.get("lsh").toString();
	                                yjh.put(e.get("mc").toString(), ls_str);
	                            }
						  }else if(e.get("laState")!=null && "3".equals(e.get("laState").toString())){
							    String ls_str = ths.get(e.get("mc").toString());
	                            if(ls_str == null){
	                            	ths.put(e.get("mc").toString(), e.get("lsh").toString());
	                            }else{
	                                ls_str += ","+e.get("lsh").toString();
	                                ths.put(e.get("mc").toString(), ls_str);
	                            }
						  }
					 }
				 }
			}
			List<Map<String,Object>> retMap = new ArrayList<>();
	        for(String mc : mclist){
	            Map<String,Object> ls_map = new HashMap<>();
	            ls_map.put("mc",mc);
	            
	            if(tah.get(mc) == null){
	                ls_map.put("tas",0);
	                ls_map.put("tah","");
	            }else{
	                String[] lsArr = tah.get(mc).split(",");
	                ls_map.put("tas",lsArr.length);
	                ls_map.put("tah",tah.get(mc));
	            }
	            
	            if(yjh.get(mc) == null){
	                ls_map.put("yjs",0);
	                ls_map.put("yjh","");
	            }else{
	                String[] lsArr = yjh.get(mc).split(",");
	                ls_map.put("yjs",lsArr.length);
	                ls_map.put("yjh",yjh.get(mc));
	            }
	            
	            if(ths.get(mc) == null){
	                ls_map.put("ths",0);
	            }else{
	                String[] lsArr = ths.get(mc).split(",");
	                ls_map.put("ths",lsArr.length);
	            }
	            
	            retMap.add(ls_map);
		    }
			
			
			int total = taxxService.tablCount(params);
			
			PageUtils pageUtils = new PageUtils(retMap, total);
			return pageUtils;
		
		}
		
		
		
		/**
		 * 集体撰写提案情况-导出
		 * 
		 * @author ZengLiang
		 */
		 @ResponseBody
		    @RequestMapping("/jtxxTaqk_downLoad")
		    public void jtxxTaqkDownLoad(@RequestParam Map<String, Object> params, HttpServletResponse response){
		       
			        params.put("type", 2);
		            List<Map<String, Object>> taxxList = taxxService.zxtaqk(params);
		            
		            List<TaxxDO> vlist = new LinkedList<>();
		            taxxList.forEach(e ->
		             {
		            	 TaxxDO tzhl = new TaxxDO();
		            	    tzhl.setCbdw(null!=e.get("mc")&&""!=e.get("mc")?e.get("mc").toString():"-");
		            	    tzhl.setCzwt(null!=e.get("taxxId")&&""!=e.get("taxxId")?e.get("taxxId").toString():"-");
		            	    tzhl.setTah(null!=e.get("tah")&&""!=e.get("tah")?e.get("tah").toString():"-");
		            	    tzhl.setLsh(null!=e.get("lsh")&&""!=e.get("lsh")?e.get("lsh").toString():"-");
		            	    tzhl.setLastate(null!=e.get("laState")&&""!=e.get("laState")?(Integer) e.get("laState"):0);
		            	    tzhl.setTastate(null!=e.get("taState")&&""!=e.get("taState")?(Integer) e.get("taState"):0);
		            	    
		                 vlist.add(tzhl);
		            });
		            LinkedHashMap<String, String> fieldMap=new LinkedHashMap<>();
		             fieldMap.put("cbdw","集体名称");
		             fieldMap.put("czwt","提案号");
		             fieldMap.put("tah","提案数");
		             fieldMap.put("lsh","意见号");
		             fieldMap.put("lastate","意见数");
		             fieldMap.put("tastate","不立案退回件数");
		             String fieldName="集体撰写提案情况";
		            try {
		                ExcelUtil.listToExcel(vlist, fieldMap, fieldName, response);
		            } catch (ExcelException e) {
		                e.printStackTrace();
		            }
		        
		    }
		    
		
		
		
		

}
