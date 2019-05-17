package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.DqxzDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author shipan
 * @email 
 * @date 2018-08-08 16:33:55
 */
@Mapper
public interface DqxzDao {

	DqxzDO get(Integer id);
	
	List<DqxzDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DqxzDO dqxz);
	
	int update(DqxzDO dqxz);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<Map<String,Object>> selectAllSelect();
}
