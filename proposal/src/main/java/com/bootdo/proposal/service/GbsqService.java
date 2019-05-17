package com.bootdo.proposal.service;

import com.bootdo.proposal.domain.GbsqDO;

import java.util.List;
import java.util.Map;

/**
 * 改办申请
 * 
 * @author shipan
 * @email 
 * @date 2018-10-23 16:56:28
 */
public interface GbsqService {
	
	GbsqDO get(Integer id);
	
	List<GbsqDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GbsqDO gbsq,String[] cbdw,String[] fbdw,String[] xbdw);
	
	int update(GbsqDO gbsq);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<Map<String,Object>> tagbList(Map<String,Object> map);
	
	int tagbCount(Map<String,Object> map);
}
