package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.TayyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author shipan
 * @email 
 * @date 2018-08-08 15:05:30
 */
@Mapper
public interface TayyDao {

	TayyDO get(Integer id);
	
	List<TayyDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TayyDO tayy);
	
	int update(TayyDO tayy);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<Map<String,Object>> selectAllSelect();
}
