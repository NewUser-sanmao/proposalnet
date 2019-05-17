package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.DpDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author shipan
 * @email 
 * @date 2018-08-08 15:05:29
 */
@Mapper
public interface DpDao {

	DpDO get(Integer id);
	
	List<DpDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DpDO dp);
	
	int update(DpDO dp);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<Map<String,Object>> selectAllSelect();
}
