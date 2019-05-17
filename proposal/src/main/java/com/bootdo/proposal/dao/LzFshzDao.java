package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.LzFshzDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 履职分数汇总
 * @author shipan
 * @email 
 * @date 2018-12-08 19:18:11
 */
@Mapper
public interface LzFshzDao {

	LzFshzDO get(Integer id);
	
	List<LzFshzDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(LzFshzDO lzFshz);
	
	int update(LzFshzDO lzFshz);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
