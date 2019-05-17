package com.bootdo.proposal.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class StringUtil {
	
	/**
	 * 只要其中一个是null或""就返回true
	 * @param pars
	 * @return str==null || "".equals(str.toString().trim())
	 */
	public static boolean judgeObjectsIsNull(Object... pars) {
		for(Object str : pars) {
			if(str==null || "".equals(str.toString().trim())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断IP地址是否合法
	 * @param text
	 * @return
	 */
	public static boolean judgeIpLegitimate(String text) {
		String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."+
                "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."+
                "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."+
                "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		if(text.matches(regex)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 返回加上月份之后的1号
	 * @param month
	 * @return
	 */
	public static Date plusMonthTheFirstDay(Integer month) {
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime ldt2 = localDateTime.plusMonths(month).withHour(0).withMinute(0).withSecond(0).withNano(0).withDayOfMonth(1);
		ZonedDateTime zdt = ldt2.atZone(zoneId);
		Date date = Date.from(zdt.toInstant());
		return date;
	}
	
	/**
	 * 获取当月1号,或增加month
	 * @return
	 */
	public static String nowMonthToDayOne(int month) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");
		LocalDateTime localDateTime = LocalDateTime.now();
		return df.format(localDateTime.plusMonths(month).withDayOfMonth(1));
	}
	
	/**
	 * 增加month
	 * @return
	 */
	public static Date plusMonths(Date date, int month) {
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
		localDateTime = localDateTime.plusMonths(month);
		ZonedDateTime zdt = localDateTime.atZone(zoneId);
		return Date.from(zdt.toInstant());
	}
	
	/**
	 * 当前时间增加N年
	 * @return
	 */
	public static Date nowPlusYears(int year) {
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime ldt2 = localDateTime.plusYears(year).withHour(0).withMinute(0).withSecond(0).withNano(0);
		ZonedDateTime zdt = ldt2.atZone(zoneId);
		Date date = Date.from(zdt.toInstant());
		return date;
	}
	
	/**
	 * 当前时间增加N天
	 * yyyy-MM-dd 00:00:00
	 * @return
	 */
	public static Date nowPlusDays(int days) {
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime ldt2 = localDateTime.plusDays(days).withHour(0).withMinute(0).withSecond(0).withNano(0);
		ZonedDateTime zdt = ldt2.atZone(zoneId);
		Date date = Date.from(zdt.toInstant());
		return date;
	}
	
	 /**
     * 当前日期
     * @param par 填格式。例：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentDate(Date date, String par){  
    	DateFormat sdf = new SimpleDateFormat(par);
    	return sdf.format(date);
    }
	
    /**
     * 当前日期
     * @param par 填格式。例：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentDate(String par){  
    	Date d = new Date();
    	DateFormat sdf = new SimpleDateFormat(par);
    	return sdf.format(d);
    }
    
    /**
     * 时间转换为日期
     * @param par 填格式。例：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date getCurrentDate(String par, String date){  
    	DateFormat sdf = new SimpleDateFormat(par);
    	try {
			return sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 比较之间的月数
     * @param date1 被减数
     * @param date2 减数
     * @return
     */
    public static int compareWithNow(Date date1,Date date2) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//        String afferentYearMonth = DateUtil.getStringSim(dateStr, "yyyyMMddHHmmss", "yyyy-MM");
//        String nowYearMonth = YearMonth.now().toString();
    	Calendar now = Calendar.getInstance();
        Calendar afferent = Calendar.getInstance();
        now.setTime(date1);
        afferent.setTime(date2);
        int year = (now.get(Calendar.YEAR) - afferent.get(Calendar.YEAR)) * 12;
        int month = now.get(Calendar.MONTH) - afferent.get(Calendar.MONTH);
        return Math.abs(year + month);
    }
    
    public static void main(String[] args) {
//    	System.out.println(compareWithNow(new Date(),plusMonthTheFirstDay(-112)));
    	String[] ss = {"0","1","2","3","4","5","6","7","8","9",
				   "A","B","C","D","E","F","G","H","I","J",
				   "K","L","M","N","O","P","Q","R","S","T",
				   "U","V","W","X","Y","Z"};
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<32;i++){
			int a = (int)(Math.random()*36);
			sb.append(ss[a]);
		}
    	System.out.println(sb.toString());
	}
    
    /**
     * 年月日+毫秒值+N位随机数
     * @param n
     * @return
     */
    public static String getRandomStr(int number){  
    	String[] ss = {"0","1","2","3","4","5","6","7","8","9",
    				   "A","B","C","D","E","F","G","H","I","J",
    				   "K","L","M","N","O","P","Q","R","S","T",
    				   "U","V","W","X","Y","Z"};
    	StringBuffer sb = new StringBuffer();
    	sb.append(getCurrentDate("yyyyMMddHHmmssSSS"));
    	for(int i=0;i<number;i++){
    		int a = (int)(Math.random()*36);
    		sb.append(ss[a]);
    	}
    	
    	return sb.toString();
    }
    
    /**
     * 毫秒值+N位随机数
     * @param n
     * @return
     */
    public static String getMilliRandomStr(int number){  
    	String[] ss = {"0","1","2","3","4","5","6","7","8","9",
    				   "A","B","C","D","E","F","G","H","I","J",
    				   "K","L","M","N","O","P","Q","R","S","T",
    				   "U","V","W","X","Y","Z"};
    	StringBuffer sb = new StringBuffer();
    	sb.append(System.currentTimeMillis());
    	for(int i=0;i<number;i++){
    		int a = (int)(Math.random()*36);
    		sb.append(ss[a]);
    	}
    	
    	return sb.toString();
    }
    
    /**
     * 字符串分割,如果失败返回null
     * @param str
     * @return
     */
    public static String[] split(String str,String regex) {
    	String[] arr = null;
    	try {
    		arr = str.split(regex);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("字符串分割失败!!!");
		}
    	
    	return arr;
    }
    
    /**
     * 调用对象的toString方法,如果为null则返回""
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
    	if(obj==null) {
    		return "";
    	}
    	return obj.toString();
    }
    
    /**
     * 把Cell的类型设置为文本类型，在取值
     * @param cell cell值为null，返回""
     * @return
     */
    public static String getCellString(Cell  cell){  
    	String ret = "";
    	if(cell != null){
    		cell.setCellType(Cell.CELL_TYPE_STRING);
    		ret = cell.getStringCellValue().trim();//.replace(" ", "");
    	}
        return ret;     
    }
    
    /**
     * 把Cell的类型设置为文本类型，在取值
     * @param cell
     * @return 若cell的值为""则返回null , 并且去掉了所以的空格
     */
    public static String getCellStringAndNull(Cell  cell){  
    	String ret = null;
    	if(cell != null){
    		cell.setCellType(Cell.CELL_TYPE_STRING);
    		ret = cell.getStringCellValue().replaceAll(" ", "");//.replace(" ", "");
    	}
        return ret;     
    }
    
    /**
     * 判断这行中的这些下标里面是否有值
     * @param row
     * @param indexs
     * @return 如果有null或值为""则返回true
     */
    public static boolean RowIndexsIsNull(Row row,int[] indexs){  
    	if(row == null){
    		return true;
    	}
    	if(indexs!=null && indexs.length>0){
    		for(int i=0;i<indexs.length;i++){
    			if(row.getCell(indexs[i]) == null || "".equals(getCellString(row.getCell(indexs[i])))){
    				return true;
    			}
    		}
    	}
        return false;     
    }
    
    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2) {
    	int days = 0;
    	try {
    		days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        return days;
    }
}