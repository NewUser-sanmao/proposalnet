package com.bootdo.proposal.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-10-19 14:22:10
 */
public interface FsdxService {
	
	/**
	 * 发送短信
	 * @return
	 */
	boolean fs(String text, List<String> tels);
	
	/**
	 * 发送短信
	 * @param type 1立案,2不立案转意见,3不立案退回,4转交,5正式回执完成,6二次办理转交时,7二次办理正式回执完成
	 * @param par
	 * @param tels
	 * @return
	 */
	boolean fs(int type, Map<String,String> par, List<String> tels);
}
