package com.bootdo.proposal.service;

import com.bootdo.proposal.domain.ZxjcDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-07 11:34:53
 */
public interface ZxjcService {
	
	ZxjcDO get(Integer id);
	
	List<ZxjcDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZxjcDO zxjc);
	
	int update(ZxjcDO zxjc);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<Map<String,Object>> selectAllSelect();
	
	ZxjcDO getNewest();
}
