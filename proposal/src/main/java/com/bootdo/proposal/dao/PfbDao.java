package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.PfbDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 评分题目表
 * @author shipan
 * @email 
 * @date 2018-11-07 16:49:05
 */
@Mapper
public interface PfbDao {

	PfbDO get(Integer id);
	
	List<PfbDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PfbDO pfb);
	
	int update(PfbDO pfb);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
