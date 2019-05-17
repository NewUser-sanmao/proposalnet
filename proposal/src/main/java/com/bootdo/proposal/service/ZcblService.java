package com.bootdo.proposal.service;

import com.bootdo.proposal.domain.ZcblDO;

import java.util.List;
import java.util.Map;

/**
 * 再次办理
 * 
 * @author shipan
 * @email 
 * @date 2018-12-28 14:53:24
 */
public interface ZcblService {
	
	ZcblDO get(Integer id);
	
	List<ZcblDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZcblDO zcbl);
	
	int update(ZcblDO zcbl);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
