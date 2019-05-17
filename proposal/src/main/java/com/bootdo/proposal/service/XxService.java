package com.bootdo.proposal.service;

import com.bootdo.proposal.domain.XxDO;

import java.util.List;
import java.util.Map;

/**
 * 消息
 * 
 * @author shipan
 * @email 
 * @date 2018-10-31 14:06:43
 */
public interface XxService {
	
	XxDO get(Integer id);
	
	List<XxDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XxDO xx, Integer[] mbdw, Integer lx, Integer type);
	
	int saveAdminAdd(XxDO xx, Integer[] mbdw, Integer type);
	
	int update(XxDO xx);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<Map<String,Object>> listMap(Map<String,Object> map);
	
	int countMap(Map<String,Object> map);

	int findCountByName(Map<String,Object> map);
}
