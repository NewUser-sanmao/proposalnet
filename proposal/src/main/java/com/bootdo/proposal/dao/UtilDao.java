package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.UtilDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author shipan
 * @email 
 * @date 2018-11-20 17:14:57
 */
@Mapper
public interface UtilDao {

	UtilDO get(Integer id);
	
	List<UtilDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UtilDO util);
	
	int update(UtilDO util);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
