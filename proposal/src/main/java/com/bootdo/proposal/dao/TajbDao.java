package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.TajbDO;

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
public interface TajbDao {

	TajbDO get(Integer id);
	
	List<TajbDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TajbDO tajb);
	
	int update(TajbDO tajb);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<Map<String,Object>> selectAllSelect();
}
