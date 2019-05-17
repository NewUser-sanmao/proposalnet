package com.bootdo.proposal.service;

import com.bootdo.proposal.domain.LzFshzDO;

import java.util.List;
import java.util.Map;

/**
 * 履职分数汇总
 * 
 * @author shipan
 * @email 
 * @date 2018-12-08 19:18:11
 */
public interface LzFshzService {
	
	LzFshzDO get(Integer id);
	
	List<LzFshzDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LzFshzDO lzFshz);
	
	int update(LzFshzDO lzFshz);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
