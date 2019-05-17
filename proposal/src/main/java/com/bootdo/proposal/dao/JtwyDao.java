package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.JtwyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author shipan
 * @email 
 * @date 2018-08-09 11:50:51
 */
@Mapper
public interface JtwyDao {

	JtwyDO get(Integer id);
	
	List<JtwyDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(JtwyDO jtwy);
	
	int update(JtwyDO jtwy);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
