package com.bootdo.proposal.service;

import com.bootdo.proposal.domain.LxEnumDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-11-02 10:07:14
 */
public interface LxEnumService {
	
	LxEnumDO get(Integer id);
	
	List<LxEnumDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LxEnumDO lxEnum);
	
	int update(LxEnumDO lxEnum);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
