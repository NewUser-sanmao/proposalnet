package com.bootdo.proposal.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.proposal.domain.CbdwDO;
import com.bootdo.proposal.domain.TaxxDO;
import com.bootdo.proposal.domain.ZxjcDO;
import com.bootdo.proposal.service.CbdwService;
import com.bootdo.proposal.service.PfbService;
import com.bootdo.proposal.service.PfbTaxxService;
import com.bootdo.proposal.service.TaxxService;
import com.bootdo.proposal.service.ZxjcService;
import com.bootdo.proposal.util.FileToByte;
import com.bootdo.proposal.util.StringUtil;
import com.bootdo.proposal.util.WordUtils;

/**
 * 提案信息
 * 
 * @author shipan
 * @email 
 * @date 2018-10-17 14:16:06
 */
 
@Controller
@RequestMapping("/proposal/tjfx")
public class TjfxController {
	@Autowired
	private TaxxService taxxService;
	@Autowired
	private CbdwService cbdwService;
	@Autowired
	private ZxjcService zxjcService;
	@Autowired
	private PfbTaxxService pfbTaxxService;
	
	
	@GetMapping("/taml")
	@RequiresPermissions("proposal:tjfx:taml")
	String Taxx(){
	    return "proposal/tjfx/taml";
	}
	
	@ResponseBody
	@GetMapping("/tamlList")
	@RequiresPermissions("proposal:tjfx:taml")
	public PageUtils tamlList(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("lastate", 1);//提案
		params.put("state", 1);
//		params.put("dyTastate", 3);//转交
		
//		params.put("cbdwId", ShiroUtils.getUserIdInteger());//小于
		
//        Query query = new Query(params);
		List<Map<String,Object>> taxxList = taxxService.tamltj(params);
//		int total = taxxService.tablCount(query);
		PageUtils pageUtils = new PageUtils(taxxList, 0);
		return pageUtils;
	}
	
	@PostMapping("/tamlDc")
	public void tamlDc(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String, Object> params) {
    	
		params.put("lastate", 1);//立案状态
//		params.put("dyTastate", 3);//转交
		List<Map<String,Object>> taxxList = taxxService.tamltj(params);
    	try{
    		
    		HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
            HSSFSheet sheet = wb.createSheet("工作表格1");  
            sheet.setDefaultColumnWidth(18);
//            sheet.setDefaultRowHeightInPoints(30);  
            sheet.setColumnWidth(0, 5 * 256);//设置宽度
            sheet.setColumnWidth(1, 10 * 256);//设置宽度
            sheet.setColumnWidth(2, 50 * 256);//设置宽度
            sheet.setColumnWidth(3, 12 * 256);//设置宽度
            sheet.setColumnWidth(4, 12 * 256);//设置宽度
            sheet.setColumnWidth(5, 7 * 256);//设置宽度
            sheet.setColumnWidth(6, 7 * 256);//设置宽度
            sheet.setColumnWidth(7, 7 * 256);//设置宽度
            sheet.setColumnWidth(8, 4 * 256);//设置宽度
            sheet.setColumnWidth(9, 12 * 256);//设置宽度
            sheet.setColumnWidth(10, 11 * 256);//设置宽度
            sheet.setColumnWidth(11, 8 * 256);//设置宽度
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
            // 第四步，创建单元格，并设置值表头 设置表头居中  
            HSSFCellStyle style = wb.createCellStyle();  
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中 
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            style.setWrapText(true);//自动换行
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            HSSFRow row = null;
            HSSFCell cell = null;
            
            int rowIndex = 0;
            /*
            if(title!=null && !"".equals(title.trim())) {
            	sheet.addMergedRegion(new CellRangeAddress(     
            			rowIndex, //first row (0-based)  from 行     
            			rowIndex, //last row  (0-based)  to 行     
        		        0, //first column (0-based) from 列     
        		        th.size()-1  //last column  (0-based)  to 列     
        		));
            	row = sheet.createRow(rowIndex);
            	row.setHeightInPoints(20);
            	
            	cell = row.createCell(0);
            	cell.setCellValue(title);
                cell.setCellStyle(style);
            	
            	rowIndex++;
            }
            */
            
            sheet.addMergedRegion(new CellRangeAddress(     
        			rowIndex, //first row (0-based)  from 行     
        			rowIndex+1, //last row  (0-based)  to 行     
    		        0, //first column (0-based) from 列     
    		        0  //last column  (0-based)  to 列     
    		));
            
            sheet.addMergedRegion(new CellRangeAddress(     
        			rowIndex, //first row (0-based)  from 行     
        			rowIndex+1, //last row  (0-based)  to 行     
    		        1, //first column (0-based) from 列     
    		        1  //last column  (0-based)  to 列     
    		));
            
            sheet.addMergedRegion(new CellRangeAddress(     
        			rowIndex, //first row (0-based)  from 行     
        			rowIndex+1, //last row  (0-based)  to 行     
    		        2, //first column (0-based) from 列     
    		        2  //last column  (0-based)  to 列     
    		));
            
            sheet.addMergedRegion(new CellRangeAddress(     
        			rowIndex, //first row (0-based)  from 行     
        			rowIndex+1, //last row  (0-based)  to 行     
    		        3, //first column (0-based) from 列     
    		        3  //last column  (0-based)  to 列     
    		));
            
            sheet.addMergedRegion(new CellRangeAddress(     
        			rowIndex, //first row (0-based)  from 行     
        			rowIndex+1, //last row  (0-based)  to 行     
    		        4, //first column (0-based) from 列     
    		        4  //last column  (0-based)  to 列     
    		));
            
            sheet.addMergedRegion(new CellRangeAddress(     
        			rowIndex, //first row (0-based)  from 行     
        			rowIndex+1, //last row  (0-based)  to 行     
    		        5, //first column (0-based) from 列     
    		        5  //last column  (0-based)  to 列     
    		));
            
            sheet.addMergedRegion(new CellRangeAddress(     
        			rowIndex, //first row (0-based)  from 行     
        			rowIndex+1, //last row  (0-based)  to 行     
    		        6, //first column (0-based) from 列     
    		        6  //last column  (0-based)  to 列     
    		));
            
            sheet.addMergedRegion(new CellRangeAddress(     
        			rowIndex, //first row (0-based)  from 行     
        			rowIndex+1, //last row  (0-based)  to 行     
    		        7, //first column (0-based) from 列     
    		        7  //last column  (0-based)  to 列     
    		));
            
            sheet.addMergedRegion(new CellRangeAddress(
        			rowIndex, //first row (0-based)  from 行     
        			rowIndex, //last row  (0-based)  to 行     
    		        8, //first column (0-based) from 列     
    		        9  //last column  (0-based)  to 列     
    		));
            
            sheet.addMergedRegion(new CellRangeAddress(
        			rowIndex, //first row (0-based)  from 行     
        			rowIndex, //last row  (0-based)  to 行     
    		        10, //first column (0-based) from 列     
    		        11  //last column  (0-based)  to 列     
    		));
            
            
            row = sheet.createRow(rowIndex);
            row.setHeightInPoints(18);
            
            cell = row.createCell(0);
        	cell.setCellValue("提案");
            cell.setCellStyle(style);
            
            cell = row.createCell(1);
        	cell.setCellValue("姓名");
            cell.setCellStyle(style);
            
            cell = row.createCell(2);
        	cell.setCellValue("提案题目");
            cell.setCellStyle(style);
            
            cell = row.createCell(3);
        	cell.setCellValue("承办单位");
            cell.setCellStyle(style);
            
            cell = row.createCell(4);
        	cell.setCellValue("协办单位");
            cell.setCellStyle(style);
            
            cell = row.createCell(5);
        	cell.setCellValue("提案者回执");
            cell.setCellStyle(style);
            
            cell = row.createCell(6);
        	cell.setCellValue("办复日期");
            cell.setCellStyle(style);
            
            cell = row.createCell(7);
        	cell.setCellValue("类型ABC");
            cell.setCellStyle(style);
            
            cell = row.createCell(8);
        	cell.setCellValue("是否为重点提案");
            cell.setCellStyle(style);
            
            cell = row.createCell(10);
        	cell.setCellValue("报交办机关");
            cell.setCellStyle(style);
            
            rowIndex++;
            row = sheet.createRow(rowIndex);
            row.setHeightInPoints(18);
            
            for(int i=0;i<8;i++) {
            	cell = row.createCell(i);
                cell.setCellStyle(style);
            }
            
            cell = row.createCell(8);
        	cell.setCellValue("是");
            cell.setCellStyle(style);
            
            cell = row.createCell(9);
        	cell.setCellValue("区领导督办");
            cell.setCellStyle(style);
            
            cell = row.createCell(10);
        	cell.setCellValue("签字");
            cell.setCellStyle(style);
            
            cell = row.createCell(11);
        	cell.setCellValue("日期");
            cell.setCellStyle(style);
            
            if(taxxList!=null && taxxList.size()>0) {
        		for(Map<String, Object> m : taxxList) {
        			String cbdw = StringUtil.toString(m.get("cbdw"));
        			String xbdw = StringUtil.toString(m.get("xbdw"));
        			
        			int cbdwLength = cbdw.split(",").length;
        			int xbdwLength = xbdw.split(",").length;
        			
        			if(xbdwLength > cbdwLength) {
        				cbdwLength = xbdwLength;
        			}
        			
        			rowIndex++;
            		row = sheet.createRow(rowIndex);
            		row.setHeightInPoints(18*cbdwLength);
            		
            		String zbStr = "";
            		if(m.get("isba")!=null&&m.get("baid")==null) {//主并
            			zbStr = "[主并]";
            		}else if(m.get("isba")!=null&&m.get("baid")!=null){//被并
            			zbStr = "[被并]";
            		}
            		
            		cell = row.createCell(0);
    				cell.setCellValue(StringUtil.toString(m.get("tah"))+zbStr);
    				cell.setCellStyle(style);
            		
    				cell = row.createCell(1);
    				cell.setCellValue(StringUtil.toString(m.get("xm")));
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(2);
    				cell.setCellValue(StringUtil.toString(m.get("tatm")));
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(3);
    				cell.setCellValue(cbdw.replaceAll(",", "\r\n"));
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(4);
    				cell.setCellValue(xbdw.replaceAll(",", "\r\n"));
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(5);
    				cell.setCellValue("");
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(6);
    				cell.setCellValue("");
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(7);
    				cell.setCellValue("");
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(8);
    				cell.setCellValue("");
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(9);
    				cell.setCellValue("");
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(10);
    				cell.setCellValue("");
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(11);
    				cell.setCellValue("");
    				cell.setCellStyle(style);
            		
        		}
        	}
            
//    		HSSFWorkbook wb = ExportExcelUtil.get(null, th, body);
        	response.setContentType("application/msexcel;charset=UTF-8");
        	String fileName="提案目录统计"+StringUtil.getCurrentDate("yyyy-MM-dd")+".xls";
        	fileName = new String(fileName.getBytes("gb2312"),"iso8859-1");
        	response.setHeader("Content-Disposition", "attachment;filename="+fileName);
        	OutputStream os = response.getOutputStream();
        	
            wb.write(os);  
            os.close();
            os.close();
        }catch (Exception e){  
            e.printStackTrace();  
        }
    }
	
	@GetMapping("/taflml")
	@RequiresPermissions("proposal:tjfx:taflml")
	String taflml(){
	    return "proposal/tjfx/taflml";
	}
	
	@ResponseBody
	@GetMapping("/taflmlList")
	@RequiresPermissions("proposal:tjfx:taflml")
	public PageUtils taflmlList(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<Map<String,Object>> list = taxxService.taflml(params);
		PageUtils pageUtils = new PageUtils(list, 0);
		return pageUtils;
	}
	
	@PostMapping("/taflmlDc")
	public void taflmlDc(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String, Object> params) {
		List<Map<String,Object>> list = taxxService.taflml(params);
    	try{
    		HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
            HSSFSheet sheet = wb.createSheet("工作表格1");  
            sheet.setDefaultColumnWidth(18);
//            sheet.setDefaultRowHeightInPoints(30);  
            sheet.setColumnWidth(0, 40 * 256);//设置宽度
            sheet.setColumnWidth(1, 5 * 256);//设置宽度
            sheet.setColumnWidth(2, 10 * 256);//设置宽度
            sheet.setColumnWidth(3, 40 * 256);//设置宽度
            sheet.setColumnWidth(4, 25 * 256);//设置宽度
            sheet.setColumnWidth(5, 15 * 256);//设置宽度
            sheet.setColumnWidth(6, 7 * 256);//设置宽度
            sheet.setColumnWidth(7, 7 * 256);//设置宽度
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
            // 第四步，创建单元格，并设置值表头 设置表头居中  
            HSSFCellStyle style = wb.createCellStyle();  
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中 
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            style.setWrapText(true);//自动换行
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            HSSFRow row = null;
            HSSFCell cell = null;
            
            int rowIndex = 0;
            
            row = sheet.createRow(rowIndex);
            row.setHeightInPoints(18);
            
            cell = row.createCell(0);
        	cell.setCellValue("分类");
            cell.setCellStyle(style);
            
            cell = row.createCell(1);
        	cell.setCellValue("提案号");
            cell.setCellStyle(style);
            
            cell = row.createCell(2);
        	cell.setCellValue("姓名");
            cell.setCellStyle(style);
            
            cell = row.createCell(3);
        	cell.setCellValue("通讯地址");
            cell.setCellStyle(style);
            
            cell = row.createCell(4);
        	cell.setCellValue("提案题目");
            cell.setCellStyle(style);
            
            cell = row.createCell(5);
        	cell.setCellValue("承办单位");
            cell.setCellStyle(style);
            
            cell = row.createCell(6);
        	cell.setCellValue("办复日期");
            cell.setCellStyle(style);
            
            cell = row.createCell(7);
        	cell.setCellValue("类型ABC");
            cell.setCellStyle(style);
            
            if(list!=null && list.size()>0) {
        		for(Map<String, Object> m : list) {
        			
        			rowIndex++;
            		row = sheet.createRow(rowIndex);
            		row.setHeightInPoints(18);
            		
            		cell = row.createCell(0);
    				cell.setCellValue(StringUtil.toString(m.get("talx")));
    				cell.setCellStyle(style);
            		
    				cell = row.createCell(1);
    				cell.setCellValue(StringUtil.toString(m.get("tah")));
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(2);
    				cell.setCellValue(StringUtil.toString(m.get("mc")));
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(3);
    				cell.setCellValue(StringUtil.toString(m.get("txdz")));
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(4);
    				cell.setCellValue(StringUtil.toString(m.get("tatm")));
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(5);
    				cell.setCellValue(StringUtil.toString(m.get("cbdw")));
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(6);
    				cell.setCellValue("");
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(7);
    				cell.setCellValue("");
    				cell.setCellStyle(style);
        		}
        	}
            
        	response.setContentType("application/msexcel;charset=UTF-8");
        	String fileName="提案分类目录"+StringUtil.getCurrentDate("yyyy-MM-dd")+".xls"; 
        	fileName = new String(fileName.getBytes("gb2312"),"iso8859-1");
        	response.setHeader("Content-Disposition", "attachment;filename="+fileName);
        	OutputStream os = response.getOutputStream();
        	
            wb.write(os);  
            os.close();
            os.close();
        }catch (Exception e){  
            e.printStackTrace();  
        }
    }
	
	/**
	 * 提案分类综合统计
	 * @return
	 */
	@GetMapping("/taflzhtj")
	@RequiresPermissions("proposal:tjfx:taflzhtj")
	String taflzhtj(){
	    return "proposal/tjfx/taflzhtj";
	}
	
	@ResponseBody
	@GetMapping("/taflzhtjList")
	@RequiresPermissions("proposal:tjfx:taflzhtj")
	public PageUtils taflzhtjList(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<Map<String,Object>> list = taxxService.taflzhtj(params);
		PageUtils pageUtils = new PageUtils(list, 0);
		return pageUtils;
	}
	
	@PostMapping("/taflzhtjDc")
	public void taflzhtjDc(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String, Object> params) {
		List<Map<String,Object>> list = taxxService.taflzhtj(params);
    	try{
    		HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
            HSSFSheet sheet = wb.createSheet("工作表格1");  
            sheet.setDefaultColumnWidth(18);
//            sheet.setDefaultRowHeightInPoints(30);  
            sheet.setColumnWidth(0, 40 * 256);//设置宽度
            sheet.setColumnWidth(1, 10 * 256);//设置宽度
            sheet.setColumnWidth(2, 10 * 256);//设置宽度
            sheet.setColumnWidth(3, 10 * 256);//设置宽度
            sheet.setColumnWidth(4, 10 * 256);//设置宽度
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
            // 第四步，创建单元格，并设置值表头 设置表头居中  
            HSSFCellStyle style = wb.createCellStyle();  
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中 
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            style.setWrapText(true);//自动换行
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            HSSFRow row = null;
            HSSFCell cell = null;
            
            int rowIndex = 0;
            
            row = sheet.createRow(rowIndex);
            row.setHeightInPoints(18);
            
            cell = row.createCell(0);
        	cell.setCellValue("分类");
            cell.setCellStyle(style);
            
            cell = row.createCell(1);
        	cell.setCellValue("立案");
            cell.setCellStyle(style);
            
            cell = row.createCell(2);
        	cell.setCellValue("不立案转意见");
            cell.setCellStyle(style);
            
            cell = row.createCell(3);
        	cell.setCellValue("不立案退回");
            cell.setCellStyle(style);
            
            cell = row.createCell(4);
        	cell.setCellValue("合计");
            cell.setCellStyle(style);
            
            
            if(list!=null && list.size()>0) {
        		for(Map<String, Object> m : list) {
        			
        			rowIndex++;
            		row = sheet.createRow(rowIndex);
            		row.setHeightInPoints(18);
            		
            		cell = row.createCell(0);
    				cell.setCellValue(StringUtil.toString(m.get("talx")));
    				cell.setCellStyle(style);
            		
    				cell = row.createCell(1);
    				cell.setCellValue(StringUtil.toString(m.get("la")));
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(2);
    				cell.setCellValue(StringUtil.toString(m.get("blazyj")));
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(3);
    				cell.setCellValue(StringUtil.toString(m.get("blath")));
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(4);
    				cell.setCellValue(StringUtil.toString(m.get("hj")));
    				cell.setCellStyle(style);
        		}
        	}
            
        	response.setContentType("application/msexcel;charset=UTF-8");
        	String fileName="提案分类综合统计"+StringUtil.getCurrentDate("yyyy-MM-dd")+".xls"; 
        	fileName = new String(fileName.getBytes("gb2312"),"iso8859-1");
        	response.setHeader("Content-Disposition", "attachment;filename="+fileName);
        	OutputStream os = response.getOutputStream();
        	
            wb.write(os);  
            os.close();
            os.close();
        }catch (Exception e){  
            e.printStackTrace();  
        }
    }
	
	/**
	 * 提案分类综合统计
	 * @return
	 */
	@GetMapping("/zyjmltj")
	@RequiresPermissions("proposal:tjfx:zyjmltj")
	String zyjmltj(){
	    return "proposal/tjfx/zyjmltj";
	}
	
	@ResponseBody
	@GetMapping("/zyjmltjList")
	@RequiresPermissions("proposal:tjfx:zyjmltj")
	public PageUtils zyjmltjList(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<Map<String,Object>> list = taxxService.zyjmltj(params);
		PageUtils pageUtils = new PageUtils(list, 0);
		return pageUtils;
	}
	
	@PostMapping("/zyjmltjDc")
	public void zyjmltjDc(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String, Object> params) {
		List<Map<String,Object>> list = taxxService.zyjmltj(params);
    	try{
    		HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
            HSSFSheet sheet = wb.createSheet("工作表格1");  
            sheet.setDefaultColumnWidth(18);
//            sheet.setDefaultRowHeightInPoints(30);  
            sheet.setColumnWidth(0, 5 * 256);//设置宽度
            sheet.setColumnWidth(1, 10 * 256);//设置宽度
            sheet.setColumnWidth(2, 40 * 256);//设置宽度
            sheet.setColumnWidth(3, 40 * 256);//设置宽度
            sheet.setColumnWidth(4, 20 * 256);//设置宽度
            sheet.setColumnWidth(5, 10 * 256);//设置宽度
            sheet.setColumnWidth(6, 10 * 256);//设置宽度
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
            // 第四步，创建单元格，并设置值表头 设置表头居中  
            HSSFCellStyle style = wb.createCellStyle();  
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中 
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            style.setWrapText(true);//自动换行
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            HSSFRow row = null;
            HSSFCell cell = null;
            
            int rowIndex = 0;
            
            row = sheet.createRow(rowIndex);
            row.setHeightInPoints(18);
            
            cell = row.createCell(0);
        	cell.setCellValue("提案号");
            cell.setCellStyle(style);
            
            cell = row.createCell(1);
        	cell.setCellValue("姓名");
            cell.setCellStyle(style);
            
            cell = row.createCell(2);
        	cell.setCellValue("通讯地址");
            cell.setCellStyle(style);
            
            cell = row.createCell(3);
        	cell.setCellValue("提案题目");
            cell.setCellStyle(style);
            
            cell = row.createCell(4);
        	cell.setCellValue("承办单位");
            cell.setCellStyle(style);
            
            cell = row.createCell(5);
        	cell.setCellValue("办复日期");
            cell.setCellStyle(style);
            
            cell = row.createCell(6);
        	cell.setCellValue("办结类别(ABC)");
            cell.setCellStyle(style);
            
            if(list!=null && list.size()>0) {
        		for(Map<String, Object> m : list) {
        			
        			rowIndex++;
            		row = sheet.createRow(rowIndex);
            		row.setHeightInPoints(18);
            		
            		cell = row.createCell(0);
    				cell.setCellValue(StringUtil.toString(m.get("tah")));
    				cell.setCellStyle(style);
            		
    				cell = row.createCell(1);
    				cell.setCellValue(StringUtil.toString(m.get("mc")));
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(2);
    				cell.setCellValue(StringUtil.toString(m.get("txdz")));
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(3);
    				cell.setCellValue(StringUtil.toString(m.get("tatm")));
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(4);
    				cell.setCellValue(StringUtil.toString(m.get("cbdw")));
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(5);
    				cell.setCellValue("");
    				cell.setCellStyle(style);
    				
    				cell = row.createCell(6);
    				cell.setCellValue("");
    				cell.setCellStyle(style);
        		}
        	}
            
        	response.setContentType("application/msexcel;charset=UTF-8");
        	String fileName="转意见目录统计"+StringUtil.getCurrentDate("yyyy-MM-dd")+".xls"; 
        	fileName = new String(fileName.getBytes("gb2312"),"iso8859-1");
        	response.setHeader("Content-Disposition", "attachment;filename="+fileName);
        	OutputStream os = response.getOutputStream();
        	
            wb.write(os);  
            os.close();
            os.close();
        }catch (Exception e){  
            e.printStackTrace();  
        }
    }
	
	/**
	 * 承办单位提案交办清单
	 * @return
	 */
	@GetMapping("/cbdwtajbqd")
	public String cbdwtajbqd(){
	    return "proposal/tjfx/cbdwtajbqd";
	}
	
	/**
	 * 导出“承办提案目录”
	 * @param request
	 * @param response
	 * @param zxjcId
	 */
	@RequestMapping("/exportCbtaml")
    public @ResponseBody void exportCbtaml(HttpServletRequest request, HttpServletResponse response,Integer zxjcId,String cbdwIds){
		String baseUri = request.getRequestURI();
		String baseUrl = request.getRequestURL().toString();
		baseUrl = baseUrl.substring(0, baseUrl.indexOf(baseUri));
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		
		try {
		String[] cIds = cbdwIds.split(",");
			for(String cId : cIds) {
				Map<String,Object> par = new HashMap<>();
				par.put("zxjcid", zxjcId);
				par.put("cbdwId3", cId);
				par.put("cbdwIsNotNull", cId);
				par.put("lastate", "1");
				
				par.put("sort", "tah+0");
				par.put("order", "asc");
				
	//			par.put("ids", cbdwId);
				
				List<TaxxDO> taxxList = taxxService.tablList(par);
				if(taxxList == null || taxxList.size()==0) {
					continue;
				}
				ZxjcDO zxjc = zxjcService.get(zxjcId);
				String nian = StringUtil.getCurrentDate(zxjc.getZjrq(), "yyyy");
				
				CbdwDO cbdw = cbdwService.get(Integer.parseInt(cId));
				
				String laTypeStr = "";
				if(taxxList.get(0).getLatype()!=null && taxxList.get(0).getLatype().intValue()==2) {//2分半
					laTypeStr = "[分办]";
				}else if(taxxList.get(0).getXbdw()!=null && taxxList.get(0).getXbdw().indexOf(cbdw.getName()) != -1){//协办
					laTypeStr = "[协办]";
				}
				
				String baStr = "";
				if(taxxList.get(0).getIsba()!=null && taxxList.get(0).getBaid()==null) {//主并
					baStr = "[主并]";
				}else if(taxxList.get(0).getIsba()!=null && taxxList.get(0).getBaid()!=null){//被并
					baStr = "[被并]";
				}
				
				Map<String,Object> map = new HashMap<>();
				map.put("dyhCbdw", cbdw.getName());
				map.put("dyhXh", taxxList.get(0).getTah()==null ? "" : taxxList.get(0).getTah()+laTypeStr);
				map.put("dyhTatm", taxxList.get(0).getTatm()==null ? "" : taxxList.get(0).getTatm()+baStr);
				if(taxxList.get(0).getTanx()==null) {
					map.put("dyhTaz", "");
				}else if(taxxList.get(0).getTanx().intValue()==1){//个人
					map.put("dyhTaz", taxxList.get(0).getTaz()==null ? "" : taxxList.get(0).getTaz());
				}else if(taxxList.get(0).getTanx().intValue()==2){//集体
					map.put("dyhTaz", taxxList.get(0).getDwmc()==null ? "" : taxxList.get(0).getDwmc());
				}
				map.put("dyhXbdw", taxxList.get(0).getXbdw()==null ? "" : taxxList.get(0).getXbdw());
				
				//获得数据  
				List<Map<String,Object>> lMap = new ArrayList<>();
				for(int i=1;i<taxxList.size();i++) {
					laTypeStr = "";
					if(taxxList.get(i).getLatype()!=null && taxxList.get(i).getLatype().intValue()==2) {//2分半
						laTypeStr = "[分办]";
					}else if(taxxList.get(i).getXbdw()!=null && taxxList.get(i).getXbdw().indexOf(cbdw.getName()) != -1){//协办
						laTypeStr = "[协办]";
					}
					
					baStr = "";
					if(taxxList.get(i).getIsba()!=null && taxxList.get(i).getBaid()==null) {//主并
						baStr = "[主并]";
					}else if(taxxList.get(i).getIsba()!=null && taxxList.get(i).getBaid()!=null){//被并
						baStr = "[被并]";
					}
					
					Map<String,Object> m = new HashMap<>();
					m.put("dyhXh", taxxList.get(i).getTah()==null ? "" : taxxList.get(i).getTah()+laTypeStr);
					m.put("dyhTatm", taxxList.get(i).getTatm()==null ? "" : taxxList.get(i).getTatm()+baStr);
					if(taxxList.get(i).getTanx()==null) {
						m.put("dyhTaz", "");
					}else if(taxxList.get(i).getTanx().intValue()==1){//个人
						m.put("dyhTaz", taxxList.get(i).getTaz()==null ? "" : taxxList.get(i).getTaz());
					}else if(taxxList.get(i).getTanx().intValue()==2){//集体
						m.put("dyhTaz", taxxList.get(i).getDwmc()==null ? "" : taxxList.get(i).getDwmc());
					}
					m.put("dyhXbdw", taxxList.get(i).getXbdw()==null ? "" : taxxList.get(i).getXbdw());
					lMap.add(m);
				}
				
				map.put("nian", nian);
				map.put("lMap", lMap);
				
				URL url = getClass().getClassLoader().getResource("templates/proposal/template");
				String urlStr = url.toString();
				urlStr = urlStr.substring(6, urlStr.length());
				if(urlStr.substring(0, 3).equals("usr")) {
					urlStr = "/"+urlStr;
				}
				System.out.println("urlStr:"+urlStr);
				
				byte[] bt = WordUtils.exportMillCertificateWord(map, urlStr, "taml.ftl");
				
				zip.putNextEntry(new ZipEntry(nian+"年"+cbdw.getName()+"承办提案目录.doc"));//.substring(config.getString("package").lastIndexOf(".") + 1)
				zip.write(bt);
				zip.closeEntry();
//				WordUtils.exportMillCertificateWord(request, response, map, nian+"年"+cbdw.getName()+"承办提案目录", urlStr, "taml.ftl");
			}
	        
			IOUtils.closeQuietly(zip);
			
			response.reset();
			response.setCharacterEncoding("utf-8");  
	//        response.setContentType("application/msword");
	        response.setContentType("application/octet-stream; charset=UTF-8");
	        // 设置浏览器以下载的方式处理该文件名  
	        String fileName = "承办提案目录.zip";  
	        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
			
			IOUtils.write(outputStream.toByteArray(), response.getOutputStream());
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	
	/**
	 * 区政协届次会议提案办理评价情况汇总统计表
	 * 提案办理评价情况
	 * @return
	 */
	@GetMapping("/tablpjqk")
	@RequiresPermissions("proposal:tjfx:tablpjqk")
	public String tablpjqk(){
	    return "proposal/tjfx/tablpjqk";
	}
	
	/**
	 * 区政协届次会议提案办理评价情况汇总统计表
	 * 提案办理评价情况
	 * @return
	 */
	@ResponseBody
	@GetMapping("/tablpjqkList")
	@RequiresPermissions("proposal:tjfx:tablpjqk")
	public PageUtils tablpjqkList(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("type", 2);//提案
		
		List<Map<String,Object>> list = pfbTaxxService.tablpjqkMap(params);
		List<Map<String,Object>> retList = new ArrayList<>();
		
		Map<String,Map<String,Object>> mm = new HashMap<>();
		if(list!=null) {
			List<String> arr = new ArrayList<>();
			for(Map<String,Object> m : list) {
				if(arr.indexOf(m.get("name").toString())==-1) {
					arr.add(m.get("name").toString());
				}
				Map<String,Object> objMap = mm.get(m.get("name").toString());
				if(objMap == null) {
					objMap = new HashMap<>();
					objMap.put("name", m.get("name"));
					
					if(m.get("taxxId") != null) {
						objMap.put("js", 1);//主分办件数
					}
					
					if(m.get("sum") != null) {
						objMap.put("pjf", m.get("sum"));//平均分
					}
					
					int fs = m.get("sum")!=null ? Integer.parseInt(m.get("sum").toString()) : 0;
					if(m.get("sum")!=null) {
						if(fs>=85) {
							objMap.put("my", 1);//满意
						}else {
							objMap.put("my", 0);//满意
						}
						
						if(fs>=60 && fs<85) {
							objMap.put("jbmy", 1);//满意
						}else {
							objMap.put("jbmy", 0);//满意
						}
						
						if(fs<60) {
							objMap.put("bmy", 1);//满意
						}else {
							objMap.put("bmy", 0);//满意
						}
					}else {
						objMap.put("my", 0);//满意
						objMap.put("jbmy", 0);//基本满意
						objMap.put("bmy", 0);//不满意
					}
				}else {
					if(m.get("taxxId") != null) {
						objMap.put("js", Integer.parseInt(objMap.get("js").toString())+1);
					}
					
					if(m.get("sum")!=null) {
						if(objMap.get("pjf")!=null) {
							double pjf = Double.parseDouble(objMap.get("pjf").toString()) + Double.parseDouble(m.get("sum").toString());
							objMap.put("pjf", pjf/2);
						}else {
							objMap.put("pjf",m.get("sum").toString());
						}
					}
					
					int fs = m.get("sum")!=null ? Integer.parseInt(m.get("sum").toString()) : 0;
					if(m.get("sum")!=null) {
						if(fs>=85) {
							objMap.put("my", Integer.parseInt(objMap.get("my").toString()) + 1);//满意
						}
						
						if(fs>=60 && fs<85) {
							objMap.put("jbmy", Integer.parseInt(objMap.get("jbmy").toString()) + 1);//满意
						}
						
						if(fs<60) {
							objMap.put("bmy", Integer.parseInt(objMap.get("bmy").toString()) + 1);//满意
						}
					}
				}
				mm.put(m.get("name").toString(), objMap);
			}
			for(String name : arr) {
				retList.add(mm.get(name));
			}
		}
		
		PageUtils pageUtils = new PageUtils(retList, 0);
		return pageUtils;
	}
	
	/**
	 * 区政协届次会议提案质量评价情况汇总统计表
	 * 提案质量评价情况
	 * @return
	 */
	@GetMapping("/tazlpjqk")
	@RequiresPermissions("proposal:tjfx:tazlpjqk")
	public String tazlpjqk(){
	    return "proposal/tjfx/tazlpjqk";
	}
	
	/**
	 * 区政协届次会议提案质量评价情况汇总统计表
	 * 提案质量评价情况
	 * @return
	 */
	@ResponseBody
	@GetMapping("/tazlpjqkList")
	@RequiresPermissions("proposal:tjfx:tazlpjqk")
	public PageUtils tazlpjqkList(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("type", 1);//提案
		
//        Query query = new Query(params);
		List<Map<String,Object>> list = pfbTaxxService.listMap(params);
//		int total = taxxService.tablCount(query);
		PageUtils pageUtils = new PageUtils(list, 0);
		return pageUtils;
	}
}
