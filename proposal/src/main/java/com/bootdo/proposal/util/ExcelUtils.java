/**
 * Copyright (c) 2016 SIH, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * SIH, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with SIH.
 */
package com.bootdo.proposal.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * TODO
 * @ClassName: ExcelUtils.java
 * @author tuzm
 * @date 2016年8月30日 上午9:21:51
 *
 */
public class ExcelUtils {
	//判断excel版本
	public static Workbook openWorkbook(InputStream in,String filename)throws IOException
	{
		Workbook wb = null;
		if(filename.endsWith(".xlsx")){
			wb = new XSSFWorkbook(in);//Excel 2007
		} else {
			wb = new HSSFWorkbook(in);//Excel 2003
		}
		return wb;
	}
	public static void createNewExcel(String[] excelHead_statTypeCountMgrImpl,List<Map<String,Object>> data, OutputStream os,String shetName){
				// 创建excel工作表
				HSSFWorkbook wb = new HSSFWorkbook();
				// 创建sheet页
				HSSFSheet sheet = wb.createSheet();
				if(data==null||data.size()<1){
					try {
						wb.write(os);
						os.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					// 创建表头
					if(excelHead_statTypeCountMgrImpl==null){
						excelHead_statTypeCountMgrImpl = new String[data.get(0).size()];
						int index = 0;
						for(String key:data.get(0).keySet()){
							excelHead_statTypeCountMgrImpl[index] = key;
							index++;
						}
					}
					HSSFRow row = sheet.createRow((int) 1);
					row.setHeight((short)500);
					CellStyle style = wb.createCellStyle();
					style.setAlignment(CellStyle.ALIGN_LEFT); // 创建一个居中格式
					int dotCategoryvalueint=0;
					wb.setSheetName(0, shetName);  
					int ii = 0; // 新Excel中的行数
					for (int i = 0; i < data.size(); i++) {
						row = sheet.createRow(ii);
						HSSFCell cell1 = row.createCell((short) 0);
						cell1.setCellStyle(style);
						int row_index = 0;
						for(String str:excelHead_statTypeCountMgrImpl){
							initRow(row, row_index, data.get(i).get(str)==null?"":data.get(i).get(str).toString(), style);		
							row_index++;
						}
						ii++; 
					}
					// 将文件存到指定位置
					try {
						wb.write(os);
						os.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
	};
	private static void initRow(HSSFRow row,int i,String content,CellStyle style) {
		HSSFCell cell = row.createCell((short) i);
		cell.setCellStyle(style);
		if(content!=null){
			cell.setCellValue(content);
		}else{
			cell.setCellValue("");
		}
		row.setHeight((short)400); 
	}
	
	public static void ftModuleMatrix(OutputStream os,List<Map<String, Object>> head,List<Map<String, Object>> data){
		Map<Integer,String> names = new HashMap<Integer, String>();
		List<Integer> ccL = new ArrayList<Integer>();//记录粗体边框行
		//创建HSSFWorkbook对象(excel的文档对象)
	    HSSFWorkbook wb = new HSSFWorkbook();
	    
	    Font sFont = wb.createFont();   
        sFont.setColor(HSSFColor.BLUE.index);         // 将字体设置为“蓝色”  
        Font gFont = wb.createFont();   
        gFont.setColor(HSSFColor.GREEN.index);       // 将字体设置为“绿色”  
	    
	    CellStyle leftStyle = (CellStyle) wb .createCellStyle();
	    leftStyle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框  
	    leftStyle.setBorderLeft(CellStyle.BORDER_THICK);// 左边框  
	    leftStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框  
	    leftStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框  
	    leftStyle.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
	    leftStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP); //顶部
	    leftStyle.setWrapText(true);
	    
	    CellStyle leftStyle_s = (CellStyle) wb .createCellStyle();
	    leftStyle_s.setBorderBottom(CellStyle.BORDER_THIN); // 下边框  
	    leftStyle_s.setBorderLeft(CellStyle.BORDER_THICK);// 左边框  
	    leftStyle_s.setBorderTop(CellStyle.BORDER_THIN);// 上边框  
	    leftStyle_s.setBorderRight(CellStyle.BORDER_THIN);// 右边框  
	    leftStyle_s.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
	    leftStyle_s.setVerticalAlignment(CellStyle.VERTICAL_TOP); //顶部
	    leftStyle_s.setFont(sFont);
	    leftStyle_s.setWrapText(true);
	    
	    CellStyle leftStyle_g = (CellStyle) wb .createCellStyle();
	    leftStyle_g.setBorderBottom(CellStyle.BORDER_THIN); // 下边框  
	    leftStyle_g.setBorderLeft(CellStyle.BORDER_THICK);// 左边框  
	    leftStyle_g.setBorderTop(CellStyle.BORDER_THIN);// 上边框  
	    leftStyle_g.setBorderRight(CellStyle.BORDER_THIN);// 右边框  
	    leftStyle_g.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
	    leftStyle_g.setVerticalAlignment(CellStyle.VERTICAL_TOP); //顶部
	    leftStyle_g.setFont(gFont);
	    leftStyle_g.setWrapText(true);
	    
	    CellStyle allStyle = (CellStyle) wb .createCellStyle();
	    allStyle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框  
	    allStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框  
	    allStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框  
	    allStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框  
	    allStyle.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
	    allStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP); //顶部
	    allStyle.setWrapText(true);
	    
	    CellStyle allStyle_s = (CellStyle) wb .createCellStyle();
	    allStyle_s.setBorderBottom(CellStyle.BORDER_THIN); // 下边框  
	    allStyle_s.setBorderLeft(CellStyle.BORDER_THIN);// 左边框  
	    allStyle_s.setBorderTop(CellStyle.BORDER_THIN);// 上边框  
	    allStyle_s.setBorderRight(CellStyle.BORDER_THIN);// 右边框  
	    allStyle_s.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
	    allStyle_s.setVerticalAlignment(CellStyle.VERTICAL_TOP); //顶部
	    allStyle_s.setFont(sFont);
	    allStyle_s.setWrapText(true);
	    CellStyle allStyle_g = (CellStyle) wb .createCellStyle();
	    allStyle_g.setBorderBottom(CellStyle.BORDER_THIN); // 下边框  
	    allStyle_g.setBorderLeft(CellStyle.BORDER_THIN);// 左边框  
	    allStyle_g.setBorderTop(CellStyle.BORDER_THIN);// 上边框  
	    allStyle_g.setBorderRight(CellStyle.BORDER_THIN);// 右边框  
	    allStyle_g.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
	    allStyle_g.setVerticalAlignment(CellStyle.VERTICAL_TOP); //顶部
	    allStyle_g.setFont(gFont);
	    allStyle_g.setWrapText(true);
	    
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("ftmodulematrix");
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row0=sheet.createRow(0);
		HSSFRow row1=sheet.createRow(1);
		HSSFRow row2=sheet.createRow(2);    
		int index0 = 11;
		int index1 = 11;
		int index2 = 11;
			//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
			HSSFCell cell=row0.createCell(0);
		     //设置单元格内容
			cell.setCellValue("");
			cell.setCellStyle(leftStyle);
			//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
			sheet.addMergedRegion(new CellRangeAddress(0,1,0,index0));
			//在sheet里创建第二行
			List<Map<String, Object>> vals = null;
			HSSFCell cellh1 = null;
			HSSFCell cellh2 = null;
			HSSFCell cellh3 = null;
			for(Map<String, Object> map:head){
				cellh1 = row0.createCell(++index0);
				cellh1.setCellStyle(leftStyle);
				ccL.add(index0);
				cellh1.setCellValue(map.get("Charact_Code").toString());
				vals = (List<Map<String, Object>>) map.get("children");
				if(vals.size()>1){
					sheet.addMergedRegion(new CellRangeAddress(0,0,index0,index0+(vals.size()-1)));
					index0 = index0+(vals.size()-1);
				}
				for(Map<String, Object> val:vals){
					cellh2 = row1.createCell(++index1);
					sheet.setColumnWidth((short) index1, (short) 650);
					if(ccL.contains(index1))
						cellh2.setCellStyle(leftStyle);
					else
						cellh2.setCellStyle(allStyle);
					cellh2.setCellValue(formatStr(val.get("Value").toString()));
					cellh3 = row2.createCell(index1);
					if(ccL.contains(index1))
						cellh3.setCellStyle(leftStyle);
					else
						cellh3.setCellStyle(allStyle);
					cellh3.setCellValue(val.get("Value_status").toString());
					names.put(index1, val.get("Value").toString());
				}
			}
	      //创建单元格并设置单元格内容
	      row2.createCell(0).setCellValue("Part number");
	      names.put(0, "Partnumber");
	      sheet.setColumnWidth((short) 0, (short) 256*10+186);
	      row2.createCell(1).setCellValue("Sts");    
	      names.put(1, "Sts");
	      row2.createCell(2).setCellValue("ChFr");
	      names.put(2, "ChFr");
	      row2.createCell(3).setCellValue("In");   
	      sheet.setColumnWidth((short) 3, (short) 256*15+186);
	      names.put(3, "CIDIn");
	      row2.createCell(4).setCellValue("Out");    
	      sheet.setColumnWidth((short) 4, (short) 256*15+186);
	      names.put(4, "CIDOut");
	      row2.createCell(5).setCellValue("Num.Seq");  
	      sheet.setColumnWidth((short) 4, (short) 256*25+186);
	      names.put(5, "NumSeq");
	      row2.createCell(6).setCellValue("Qt.");    
	      names.put(6, "Qt");
	      row2.createCell(7).setCellValue("FT code");    
	      names.put(7, "FTcode");
	      row2.createCell(8).setCellValue("St.");    
	      names.put(8, "St");
	      row2.createCell(9).setCellValue("St.D");    
	      names.put(9, "StD");
	      row2.createCell(10).setCellValue("Ph.O");    
	      names.put(10, "PhO");
	      row2.createCell(11).setCellValue("Or.d");    
	      names.put(11, "Ord");
	      HSSFRow row = null;//sheet.createRow(2);
	      HSSFCell celln = null;
	      int mark = 3;
	      String[] cv = null;
	      for(Map<String,Object> r:data){
	    	  row = sheet.createRow(mark++);
	    	  for(Integer key:names.keySet()){
	    		  celln = row.createCell(key);
	    		  if(r.get(names.get(key).toString())!=null){
	    			  if(key>11){
	    				  cv = r.get(names.get(key).toString()).toString().split("=");
	    				  if(ccL.contains(key)){
	    					  if("S".equals(cv[0])){
	    						  celln.setCellStyle(leftStyle_s);
	    	    				  celln.setCellValue(cv[1]);
	    					  }else if("G".equals(cv[0])){
	    						  celln.setCellStyle(leftStyle_g);
	    	    				  celln.setCellValue(cv[1]);
	    					  }else{
	    						  celln.setCellStyle(leftStyle);
	    	    				  celln.setCellValue(cv[1]);
	    					  }
	    				  }else{
	    					  if("S".equals(cv[0])){
	    						  celln.setCellStyle(allStyle_s);
	    	    				  celln.setCellValue(cv[1]);
	    					  }else if("G".equals(cv[0])){
	    						  celln.setCellStyle(allStyle_g);
	    	    				  celln.setCellValue(cv[1]);
	    					  }else{
	    						  celln.setCellStyle(allStyle);
	    	    				  celln.setCellValue(cv[1]);
	    					  }  
	    				  }
	    			  }else{
	    				  if(ccL.contains(key)){
	    					  celln.setCellStyle(leftStyle);
	    					  celln.setCellValue(r.get(names.get(key).toString()).toString());
	    				  }else{
	    					  celln.setCellStyle(allStyle);
	    					  celln.setCellValue(r.get(names.get(key).toString()).toString());
	    				  }
	    				 
	    			  }
	    		  }else{
	    			  if(ccL.contains(key))
	    				  celln.setCellStyle(leftStyle);
	    			  else
	    				  celln.setCellStyle(allStyle);
	    			  celln.setCellValue("");
	    		  }
	    	  }
	      }
		      
		   // 将文件存到指定位置
			try {
				wb.write(os);
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
    }
	public static void ftModuleShowMatrix(OutputStream os,List<Map<String, Object>> head,List<Map<String, Object>> data){
		Map<Integer,String> names = new HashMap<Integer, String>();
		List<Integer> ccL = new ArrayList<Integer>();//记录粗体边框行
		//创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wb = new HSSFWorkbook();
		
		Font sFont = wb.createFont();   
		sFont.setColor(HSSFColor.BLUE.index);         // 将字体设置为“蓝色”  
		Font gFont = wb.createFont();   
		gFont.setColor(HSSFColor.GREEN.index);       // 将字体设置为“绿色”  
		
		CellStyle leftStyle = (CellStyle) wb .createCellStyle();
		leftStyle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框  
		leftStyle.setBorderLeft(CellStyle.BORDER_THICK);// 左边框  
		leftStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框  
		leftStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框  
		leftStyle.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
		leftStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP); //顶部
		leftStyle.setWrapText(true);
		
		CellStyle leftStyle_s = (CellStyle) wb .createCellStyle();
		leftStyle_s.setBorderBottom(CellStyle.BORDER_THIN); // 下边框  
		leftStyle_s.setBorderLeft(CellStyle.BORDER_THICK);// 左边框  
		leftStyle_s.setBorderTop(CellStyle.BORDER_THIN);// 上边框  
		leftStyle_s.setBorderRight(CellStyle.BORDER_THIN);// 右边框  
		leftStyle_s.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
		leftStyle_s.setVerticalAlignment(CellStyle.VERTICAL_TOP); //顶部
		leftStyle_s.setFont(sFont);
		leftStyle_s.setWrapText(true);
		
		CellStyle leftStyle_g = (CellStyle) wb .createCellStyle();
		leftStyle_g.setBorderBottom(CellStyle.BORDER_THIN); // 下边框  
		leftStyle_g.setBorderLeft(CellStyle.BORDER_THICK);// 左边框  
		leftStyle_g.setBorderTop(CellStyle.BORDER_THIN);// 上边框  
		leftStyle_g.setBorderRight(CellStyle.BORDER_THIN);// 右边框  
		leftStyle_g.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
		leftStyle_g.setVerticalAlignment(CellStyle.VERTICAL_TOP); //顶部
		leftStyle_g.setFont(gFont);
		leftStyle_g.setWrapText(true);
		
		CellStyle allStyle = (CellStyle) wb .createCellStyle();
		allStyle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框  
		allStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框  
		allStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框  
		allStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框  
		allStyle.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
		allStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP); //顶部
		allStyle.setWrapText(true);
		
		CellStyle allStyle_s = (CellStyle) wb .createCellStyle();
		allStyle_s.setBorderBottom(CellStyle.BORDER_THIN); // 下边框  
		allStyle_s.setBorderLeft(CellStyle.BORDER_THIN);// 左边框  
		allStyle_s.setBorderTop(CellStyle.BORDER_THIN);// 上边框  
		allStyle_s.setBorderRight(CellStyle.BORDER_THIN);// 右边框  
		allStyle_s.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
		allStyle_s.setVerticalAlignment(CellStyle.VERTICAL_TOP); //顶部
		allStyle_s.setFont(sFont);
		allStyle_s.setWrapText(true);
		CellStyle allStyle_g = (CellStyle) wb .createCellStyle();
		allStyle_g.setBorderBottom(CellStyle.BORDER_THIN); // 下边框  
		allStyle_g.setBorderLeft(CellStyle.BORDER_THIN);// 左边框  
		allStyle_g.setBorderTop(CellStyle.BORDER_THIN);// 上边框  
		allStyle_g.setBorderRight(CellStyle.BORDER_THIN);// 右边框  
		allStyle_g.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
		allStyle_g.setVerticalAlignment(CellStyle.VERTICAL_TOP); //顶部
		allStyle_g.setFont(gFont);
		allStyle_g.setWrapText(true);
		
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("ftModuleShowMatrix");
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row0=sheet.createRow(0);
		HSSFRow row1=sheet.createRow(1);
		HSSFRow row2=sheet.createRow(2);    
		int index0 = 0;
		int index1 = 0;
		int index2 = 0;
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell=row0.createCell(0);
		//设置单元格内容
		cell.setCellValue("");
		cell.setCellStyle(leftStyle);
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0,2,0,0));
		//在sheet里创建第二行
		List<Map<String, Object>> vals = null;
		HSSFCell cellh1 = null;
		HSSFCell cellh2 = null;
		HSSFCell cellh3 = null;
		for(Map<String, Object> map:head){
			cellh1 = row0.createCell(++index0);
			cellh1.setCellStyle(leftStyle);
			ccL.add(index0);
			cellh1.setCellValue(map.get("Charact_Code").toString());
			vals = (List<Map<String, Object>>) map.get("children");
			if(vals.size()>1){
				sheet.addMergedRegion(new CellRangeAddress(0,0,index0,index0+(vals.size()-1)));
				index0 = index0+(vals.size()-1);
			}
			for(Map<String, Object> val:vals){
				cellh2 = row1.createCell(++index1);
				sheet.setColumnWidth((short) index1, (short) 650);
				if(ccL.contains(index1))
					cellh2.setCellStyle(leftStyle);
				else
					cellh2.setCellStyle(allStyle);
				cellh2.setCellValue(formatStr(val.get("Value").toString()));
				cellh3 = row2.createCell(index1);
				if(ccL.contains(index1))
					cellh3.setCellStyle(leftStyle);
				else
					cellh3.setCellStyle(allStyle);
				cellh3.setCellValue(val.get("Value_status").toString());
				names.put(index1, val.get("Value").toString());
			}
		}
		//创建单元格并设置单元格内容
		row2.createCell(0).setCellValue("");
		sheet.setColumnWidth((short) 0, (short) 256*32+186);
		names.put(0, "PTAS");
		HSSFRow row = null;//sheet.createRow(2);
		HSSFCell celln = null;
		int mark = 3;
		String cv = null;
		for(Map<String,Object> r:data){
			row = sheet.createRow(mark++);
			for(Integer key:names.keySet()){
				celln = row.createCell(key);
				if(r.get(names.get(key).toString())!=null){
					if(key>=0){
						cv = r.get(names.get(key).toString()).toString();
						if(ccL.contains(key)){
							celln.setCellStyle(leftStyle);
							celln.setCellValue(cv);
						}else{
							celln.setCellStyle(allStyle);
							celln.setCellValue(cv);
						}
					}else{
						if(ccL.contains(key)){
							celln.setCellStyle(leftStyle);
							celln.setCellValue(r.get(names.get(key).toString()).toString());
						}else{
							celln.setCellStyle(allStyle);
							celln.setCellValue(r.get(names.get(key).toString()).toString());
						}
						
					}
				}else{
					if(ccL.contains(key))
						celln.setCellStyle(leftStyle);
					else
						celln.setCellStyle(allStyle);
					celln.setCellValue("");
				}
			}
		}
		
		// 将文件存到指定位置
		try {
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String formatStr(String str){
		char[] strs = str.toCharArray();
		String result = "";
		for(char c:strs){
			result += c+"\n"; 
		}
		return result;
	}
}
