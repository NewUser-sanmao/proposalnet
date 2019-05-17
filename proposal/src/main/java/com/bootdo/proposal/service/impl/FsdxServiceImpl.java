package com.bootdo.proposal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.proposal.service.FsdxService;
import com.bootdo.proposal.util.CqnewsHttpUtil;
import com.bootdo.proposal.util.StringUtil;
import com.bootdo.proposal.util.TokenUtil;



@Service
public class FsdxServiceImpl implements FsdxService {
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@Override
	public boolean fs(String text, List<String> tels) {
		// TODO Auto-generated method stub
		try {
			StringBuffer sb = new StringBuffer();
			for(String s : tels) {
				sb.append(s);
				sb.append(",");
			}
			String telsStr = sb.substring(0, sb.length()-1);
			Map<String,String> map = new HashMap<>();
			map.put("timestamp", StringUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
			map.put("appId", bootdoConfig.getAppId());
			map.put("msgContent", text);
			map.put("telephone", telsStr);
			
			List<NameValuePair> parList = new ArrayList<>();  
			parList.add(new BasicNameValuePair("timestamp", map.get("timestamp")));
			parList.add(new BasicNameValuePair("appId", map.get("appId")));
			parList.add(new BasicNameValuePair("msgContent", map.get("msgContent")));
			for(String s : tels) {
				parList.add(new BasicNameValuePair("telephone", s));
			}
			
			parList.add(new BasicNameValuePair("token", TokenUtil.getToken(map, bootdoConfig.getAppKey())));
			
			String jsonStr = CqnewsHttpUtil.sendPost("http://192.168.10.125:8751/gateway/sd/proposal/notice/sendMsgNotice", parList);
			
			JSONObject json = JSONObject.parseObject(jsonStr);
			System.out.println(json.toJSONString());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		List<String> tels = new ArrayList<>();
		tels.add("18996923534");
		tels.add("18623285717");
		StringBuffer sb = new StringBuffer();
		for(String s : tels) {
			sb.append(s);
			sb.append(",");
		}
		String telsStr = sb.substring(0, sb.length()-1);
		
		Map<String,String> map = new HashMap<>();
		map.put("timestamp", StringUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		map.put("appId", "1011");
		map.put("telephone", telsStr);
		map.put("msgContent", "委员您好！根据十五届区政协三次会议日程安排，请您登录“渝北区政协委员履职平台”，及时提交大会提案。提案提交截止时间：2019年1月15日中午12：00.提案提交事宜咨询电话：67800244、13527347059、13648359551。祝您工作顺利，生活愉快！（渝北区政协提案委）");
		
		List<NameValuePair> parList = new ArrayList<>();  
		parList.add(new BasicNameValuePair("timestamp", map.get("timestamp")));
		parList.add(new BasicNameValuePair("appId", map.get("appId")));
		parList.add(new BasicNameValuePair("msgContent", map.get("msgContent")));
		for(String s : tels) {
			parList.add(new BasicNameValuePair("telephone", s));
		}
		parList.add(new BasicNameValuePair("token", TokenUtil.getToken(map, "4eMLOrX9fqepK_QtOyYcGth0dL1E")));
		
		String jsonStr = CqnewsHttpUtil.sendPost("http://192.168.10.125:8751/gateway/"+"sd/proposal/notice/sendMsgNotice", parList);
		
		JSONObject json = JSONObject.parseObject(jsonStr);
		System.out.println(json.toJSONString());
	}

	@Override
	public boolean fs(int type, Map<String, String> par, List<String> tels) {
		// TODO Auto-generated method stub
		try {
			if(type<1 || type>7) {
				return false;
			}else if(tels==null || tels.size()==0) {
				return true;
			}
			String text = "";
			//1立案,2不立案转意见,3不立案退回,4转交,5正式回执完成,6二次办理转交时,7二次办理正式回执完成
			switch(type) {
				case 1:
					text = "委员，您好！您提交的"+par.get("zxjcmc")+"提案——"+par.get("tatm")+"已立案，即将交有关单位办理，请予关注。祝您工作顺利，生活愉快！";
					break;
				case 2:
					text = "委员，您好！您提交的"+par.get("zxjcmc")+"提案——"+par.get("tatm")+"已不立案作意见转相关单位参考。谢谢您对区政协提案工作的支持，祝您工作顺利，生活愉快！";
					break;
				case 3:
					text = "委员，您好！您提交的"+par.get("zxjcmc")+"提案——"+par.get("tatm")+"已不立案退回。谢谢您对区政协提案工作的支持，祝您工作顺利，生活愉快！";
					break;
				case 4:
					text = "委员，您好！您提交的"+par.get("zxjcmc")+"第"+par.get("tah")+"号提案——"+par.get("tatm")+"已交"+par.get("cbdw")+"单位办理，请及时登录提案系统，保持手机通畅，与承办单位交流互动。祝您工作顺利，生活愉快！";
					break;
				case 5:
					text = "委员，您好！您提交的"+par.get("zxjcmc")+"第"+par.get("tah")+"号提案——"+par.get("tatm")+"正由"+par.get("cbdw")+"单位办理，请及时登录提案系统，填写提案办理评价。祝您工作顺利，生活愉快！";
					break;
				case 6:
					text = "委员，您好！您提交的"+par.get("zxjcmc")+"第"+par.get("tah")+"号提案——"+par.get("tatm")+"正由"+par.get("cbdw")+"单位进行二次办理，请及时登录提案系统，保持手机通畅，与承办单位交流互动。祝您工作顺利，生活愉快！";
					break;
				case 7:
					text = "委员，您好！您提交的"+par.get("zxjcmc")+"第"+par.get("tah")+"号提案——"+par.get("tatm")+"正由"+par.get("cbdw")+"单位进行二次办理，请及时登录提案系统，填写提案办理评价。祝您工作顺利，生活愉快！";
					break;
				default:
					return false;
			}
			fs(text, tels);
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
}
