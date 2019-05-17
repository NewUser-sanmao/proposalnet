package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.XxDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 消息
 * @author shipan
 * @email 
 * @date 2018-10-31 14:06:43
 */
@Mapper
public interface XxDao {

	XxDO get(Integer id);
	
	List<XxDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(XxDO xx);
	
	int update(XxDO xx);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<Map<String,Object>> listMap(Map<String,Object> map);
	
	int countMap(Map<String,Object> map);

	int findCountByName(Map<String,Object> map);
}
