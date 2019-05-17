package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.SswyhDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author shipan
 * @email 
 * @date 2018-08-07 11:34:53
 */
@Mapper
public interface SswyhDao {

	SswyhDO get(Integer id);
	
	List<SswyhDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SswyhDO sswyh);
	
	int update(SswyhDO sswyh);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<Map<String,Object>> selectAllSelect();
}
