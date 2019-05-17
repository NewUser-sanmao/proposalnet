package com.bootdo.proposal.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.proposal.util.TokenUtil;
/*
@Component
@WebFilter(urlPatterns = "/kaifang/*",filterName = "cqnewsFilter")
public class CqnewsFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        Map map = request.getParameterMap();
        String uri = request.getRequestURI();
        if(uri.indexOf("keyLogin") != -1) {
        	filterChain.doFilter(request, response);
        	return;
        }
        
        Map<String,String> writeAbleMap = new HashMap<>();  
        Enumeration enuma = request.getParameterNames();  
        while (enuma.hasMoreElements()) {  
	        String paramName = (String) enuma.nextElement();  
	        String paramValue = request.getParameter(paramName);  
	        //形成键值对应的map  
	        writeAbleMap.put(paramName, paramValue);  
        }
        
        
        boolean s = TokenUtil.verificationToken(writeAbleMap, "4eMLOrX9fqepK_QtOyYcGth0dL1E");
        if(s) {
        	filterChain.doFilter(request, response);
        }else {
        	response.setHeader("Content-type", "text/html;charset=UTF-8");
        	JSONObject obj = new JSONObject();
        	obj.put("code", 1);
        	obj.put("msg", "签名认证未通过");
        	obj.put("data", null);
        	
        	PrintWriter out = null;  
            try {  
                out = response.getWriter();  
                out.append(obj.toString());  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
                if (out != null) {  
                    out.close();  
                }  
            } 
        }
        
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
*/