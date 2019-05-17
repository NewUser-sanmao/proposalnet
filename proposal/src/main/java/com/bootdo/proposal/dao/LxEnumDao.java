package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.LxEnumDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author shipan
 * @email 
 * @date 2018-11-02 10:07:14
 */
@Mapper
public interface LxEnumDao {

	LxEnumDO get(Integer id);
	
	List<LxEnumDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(LxEnumDO lxEnum);
	
	int update(LxEnumDO lxEnum);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
