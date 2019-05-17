package com.bootdo.proposal.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class CqnewsHttpUtil {
	
	public static String sendPost(String url, List<NameValuePair> params) {
	   String response = null;
	   try {
	       CloseableHttpClient httpclient = null;
	       CloseableHttpResponse httpresponse = null;
	       try {
	           httpclient = HttpClients.createDefault();
	           HttpPost httppost = new HttpPost(url);
	           httppost.setEntity(new UrlEncodedFormEntity(params,Consts.UTF_8)); 
	           httpresponse = httpclient.execute(httppost);
	           response = EntityUtils.toString(httpresponse.getEntity());
	       } finally {
	           if (httpclient != null) {
	               httpclient.close();
	           }
	           if (httpresponse != null) {
	               httpresponse.close();
	           }
	       }
	   } catch (Exception e) {
	       e.printStackTrace();
	       return null;
	   }
	   return response;
	}
	
	public static void main(String[] args) {
		List<NameValuePair> params = new ArrayList<>();  
        params.add(new BasicNameValuePair("pageNum", "1"));
        params.add(new BasicNameValuePair("pageSize", "1000"));
		
		String s = sendPost("http://data.test.cqnews.net:9560/sd/sdproposalservice/session/listSessionDic", params);
		System.out.println(s);
		
		JSONObject json = JSONObject.parseObject(s);
		
	}
}
