package com.bootdo.proposal.service;

import com.bootdo.proposal.domain.TajbDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-07 11:34:53
 */
public interface TajbService {
	
	TajbDO get(Integer id);
	
	List<TajbDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TajbDO tajb);
	
	int update(TajbDO tajb);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<Map<String,Object>> selectAllSelect();
}
