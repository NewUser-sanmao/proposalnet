package com.bootdo.proposal.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.bootdo.common.utils.MD5Utils;

public class TokenUtil {
	/**
	 * 生成签名
	 * @param map
	 * @return
	 */
	public static String getSign(Map<String, String> map) {
		if(map==null || map.size()==0) {
			return "";
		}
		
		String result = "";
		try {
			List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
			// 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
			Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
				public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
					return (o1.getKey()).toString().compareTo(o2.getKey());
				}
			});
			
			// 构造签名键值对的格式
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> item : infoIds) {
				if (item.getKey() != null || item.getKey() != "") {
					String key = item.getKey();
					if(judgeKey(key)) {
						String val = item.getValue();
						if (!(val == null || "".equals(val))) {
							sb.append(key + "=" + val + "&");
						}
					}
				}
 
			}
			result = sb.substring(0, sb.length()-1);
		} catch (Exception e) {
			return "";
		}
		return result;
	}
	
	/**
	 * 为true则可以拼接
	 * @param key
	 * @return
	 */
	private static boolean judgeKey(String key) {
		return !("token".equals(key));
	}
	
	public static String getToken(Map<String, String> map, String appKey) {
		String sign = getSign(map);
		return Sha256.getSHA256StrJava(sign+appKey);
	}
	
	/**
	 * true 通过验证 false没通过
	 * @param map
	 * @param appKey
	 * @return
	 */
	public static boolean verificationToken(Map map, String appKey) {
		try {
			if(map.get("token") == null || "".equals(map.get("token").toString())) {
				return false;
			}
			String t = map.get("token").toString();
			String sign = getSign(map);
			String token = Sha256.getSHA256StrJava(sign+appKey);
			return token.equals(t);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public static void main(String[] args) {
//		Map<String, String>  map = new HashMap<>();
//		map.put("strUserName", "段凤杰");
//		map.put("strUserIdCard", "112231231231X");
//		map.put("strPhone", "15025467198");
//		map.put("appId", "1011");
//		map.put("timestamp", "2018-11-13 10:58:54");
//		String s = getSign(map);
//		System.out.println(s);
//		System.out.println(Sha256.getSHA256StrJava(s+"4eMLOrX9fqepK_QtOyYcGth0dL1E"));
//		String s = MD5Utils.encrypt("段凤杰", "123456");
//		System.out.println(s);
		Map<String,String> map = new HashMap<>();
		map.put("timestamp", StringUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		map.put("appId", "1011");
		map.put("pageNum", "1");
		map.put("pageSize", "10");
		
		List<NameValuePair> parList = new ArrayList<>();  
		parList.add(new BasicNameValuePair("appId", map.get("appId")));
		parList.add(new BasicNameValuePair("timestamp", map.get("timestamp")));
		parList.add(new BasicNameValuePair("pageNum", map.get("pageNum")));
		parList.add(new BasicNameValuePair("pageSize", map.get("pageSize")));
		parList.add(new BasicNameValuePair("token", TokenUtil.getToken(map, "4eMLOrX9fqepK_QtOyYcGth0dL1E")));
		
		String jsonStr = CqnewsHttpUtil.sendPost("http://192.168.9.128:8751/gateway/sd/proposal/session/listSessionDic", parList);
		System.out.println(jsonStr);
	}
}
