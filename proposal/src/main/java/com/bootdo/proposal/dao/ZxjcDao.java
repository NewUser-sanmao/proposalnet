package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.ZxjcDO;

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
public interface ZxjcDao {

	ZxjcDO get(Integer id);
	
	List<ZxjcDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ZxjcDO zxjc);
	
	int update(ZxjcDO zxjc);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<Map<String,Object>> selectAllSelect();
	
	ZxjcDO getNewest();
}
