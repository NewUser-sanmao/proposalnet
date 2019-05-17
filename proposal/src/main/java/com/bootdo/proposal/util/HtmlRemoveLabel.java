package com.bootdo.proposal.util;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 

public class HtmlRemoveLabel {
	
	public static String getWordString(String str,String baseUrl) {
		if(str==null || "".equals(str)) {
			return "";
		}
		
		str = str.replaceAll("&nbsp;", "");
//		System.out.println(str);
		
		str = str.replaceAll(" ", "");
//		System.out.println(str);
		
		str = str.replaceAll("<br>", "");
//		System.out.println(str);
		
		StringBuffer retSb = new StringBuffer();
		StringBuffer sb = new StringBuffer(str);
		int start = 0, end = 0, startEnd = 0; 
		int img = 0;
		while(sb.indexOf("<p") != -1) {
			start = sb.indexOf("<p");
			startEnd = sb.indexOf(">");
			
			end = sb.indexOf("</p>");
			if((startEnd+1) != end) {
				retSb.append("<w:p><w:pPr><w:keepNext w:val=\"0\"/><w:keepLines w:val=\"0\"/><w:pageBreakBefore w:val=\"0\"/><w:widowControl w:val=\"0\"/><w:kinsoku/><w:wordWrap/><w:overflowPunct/><w:topLinePunct w:val=\"0\"/><w:autoSpaceDE/><w:autoSpaceDN/><w:bidi w:val=\"0\"/><w:adjustRightInd/><w:snapToGrid/><w:spacing w:line=\"560\" w:lineRule=\"exact\"/><w:ind w:firstLine=\"640\" w:firstLineChars=\"200\"/><w:textAlignment w:val=\"auto\"/><w:outlineLvl w:val=\"9\"/><w:rPr><w:rFonts w:hint=\"eastAsia\" w:ascii=\"仿宋\" w:hAnsi=\"仿宋\" w:eastAsia=\"仿宋\" w:cs=\"仿宋\"/><w:sz w:val=\"32\"/><w:szCs w:val=\"32\"/><w:lang w:val=\"en-US\" w:eastAsia=\"zh-CN\"/></w:rPr></w:pPr><w:r><w:rPr><w:rFonts w:hint=\"eastAsia\" w:ascii=\"仿宋\" w:hAnsi=\"仿宋\" w:eastAsia=\"仿宋\" w:cs=\"仿宋\"/><w:sz w:val=\"32\"/><w:szCs w:val=\"32\"/><w:lang w:val=\"en-US\" w:eastAsia=\"zh-CN\"/></w:rPr><w:t>");
				String text = sb.substring(startEnd+1, end);
				if(text.indexOf("<imgsrc") != -1) {
					img = text.indexOf("<imgsrc");
					text = text.substring(img+9, text.length());
					img = text.indexOf("\"");
					text = text.substring(0, img);
					
					retSb.append("图片地址：");
					retSb.append(baseUrl);
					retSb.append(text);
				}else {
					retSb.append(sb.substring(startEnd+1, end));
				}
//				retSb.append("<w:br />");
				retSb.append("</w:t></w:r></w:p>");
			}
			
			sb.replace(start, startEnd+1, "");
			System.out.println(sb.toString());
			sb.replace(end-((startEnd-start)+1), end-((startEnd-start)+1)+4, "");
			System.out.println(sb.toString());
		}
		return retSb.toString();
	}
	
	public static String getWordString2(String str,String baseUrl) {
		if(str==null || "".equals(str)) {
			return "";
		}
		
		str = str.replaceAll("&nbsp;", "");
//		System.out.println(str);
		
//		str = str.replaceAll(" ", "");
//		System.out.println(str);
		
//		str = str.replaceAll("<br>", "");
//		System.out.println(str);
		StringBuffer retSb = new StringBuffer();
		StringBuffer sb = new StringBuffer(str);
		
		sb = replaceImg(sb, baseUrl);
		String s = stripHtml(sb.toString());
		s = s.replaceAll(" ", "");
		String[] ss = s.split("\r\n");
		for(String z : ss) {
			retSb.append("<w:p><w:pPr><w:keepNext w:val=\"0\"/><w:keepLines w:val=\"0\"/><w:pageBreakBefore w:val=\"0\"/><w:widowControl w:val=\"0\"/><w:kinsoku/><w:wordWrap/><w:overflowPunct/><w:topLinePunct w:val=\"0\"/><w:autoSpaceDE/><w:autoSpaceDN/><w:bidi w:val=\"0\"/><w:adjustRightInd/><w:snapToGrid/><w:spacing w:line=\"560\" w:lineRule=\"exact\"/><w:ind w:firstLine=\"640\" w:firstLineChars=\"200\"/><w:textAlignment w:val=\"auto\"/><w:outlineLvl w:val=\"9\"/><w:rPr><w:rFonts w:hint=\"eastAsia\" w:ascii=\"仿宋\" w:hAnsi=\"仿宋\" w:eastAsia=\"仿宋\" w:cs=\"仿宋\"/><w:sz w:val=\"32\"/><w:szCs w:val=\"32\"/><w:lang w:val=\"en-US\" w:eastAsia=\"zh-CN\"/></w:rPr></w:pPr><w:r><w:rPr><w:rFonts w:hint=\"eastAsia\" w:ascii=\"仿宋\" w:hAnsi=\"仿宋\" w:eastAsia=\"仿宋\" w:cs=\"仿宋\"/><w:sz w:val=\"32\"/><w:szCs w:val=\"32\"/><w:lang w:val=\"en-US\" w:eastAsia=\"zh-CN\"/></w:rPr><w:t>");
			retSb.append(z);
			retSb.append("</w:t></w:r></w:p>");
		}
		return retSb.toString();
	}
	
	public static StringBuffer replaceImg(StringBuffer text,String baseUrl) {
		int s1 = -1;
		while((s1=text.indexOf("<img src"))!=-1) {
//			s1 = text.indexOf("<img src");
			String str = text.substring(s1+10, text.length());
			int s2 = str.indexOf("\"");
			str = str.substring(0, s2);
			text.replace(s1+1, s1+8, "");
			text.insert(s1, baseUrl+str);
		}
		return text;
	}
	
	public static String stripHtml(String content) { 
		// <p>段落替换为换行 
		content = content.replaceAll("<p.*?>", "\r\n"); 
		// <br><br/>替换为换行 
		//content = content.replaceAll("<br\\s*/?>", "\r\n"); 
		// 去掉其它的<>之间的东西 
		content = content.replaceAll("\\<.*?>", ""); 
		// 还原HTML 
		// content = HTMLDecoder.decode(content); 
		return content; 
	}
	
	public static String delHTMLTag(String htmlStr){ 
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
        
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //过滤script标签 
         
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //过滤style标签 
         
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 

        return htmlStr.trim(); //返回文本字符串 
    }
	
	public static void main(String[] args) {
		String str = "<p>fas的发送到发送到发送到</p><p>ssss</p>";
//		String s = getWordString(str,"");
		StringBuffer sb= replaceImg(new StringBuffer(str),"");
		System.out.println(sb.toString());
		System.out.println(stripHtml(sb.toString()));
	}
}
