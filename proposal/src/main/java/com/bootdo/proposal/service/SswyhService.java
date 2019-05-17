package com.bootdo.proposal.service;

import com.bootdo.proposal.domain.SswyhDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-07 11:34:53
 */
public interface SswyhService {
	
	SswyhDO get(Integer id);
	
	List<SswyhDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SswyhDO sswyh);
	
	int update(SswyhDO sswyh);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<Map<String,Object>> selectAllSelect();
}
