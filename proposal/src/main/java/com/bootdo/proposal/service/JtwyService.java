package com.bootdo.proposal.service;

import com.bootdo.proposal.domain.JtwyDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-09 11:50:51
 */
public interface JtwyService {
	
	JtwyDO get(Integer id);
	
	List<JtwyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(JtwyDO jtwy,String userName,String pwd);
	
	int update(JtwyDO jtwy);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
