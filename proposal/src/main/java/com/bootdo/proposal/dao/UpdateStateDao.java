package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.UpdateStateDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author shipan
 * @email 
 * @date 2018-12-02 19:30:28
 */
@Mapper
public interface UpdateStateDao {

	UpdateStateDO get(Integer id);
	
	List<UpdateStateDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UpdateStateDO updateState);
	
	int update(UpdateStateDO updateState);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
