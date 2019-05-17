package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.GrwyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author shipan
 * @email 
 * @date 2018-08-13 09:29:05
 */
@Mapper
public interface GrwyDao {

	GrwyDO get(Integer id);
	
	List<GrwyDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(GrwyDO grwy);
	
	int update(GrwyDO grwy);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	GrwyDO getByUserId(Integer userId);
	
	List<GrwyDO> getListByDwmcs(Map<String,Object> map);
	
	GrwyDO getByDwmcId(Integer dwmcId);
}
