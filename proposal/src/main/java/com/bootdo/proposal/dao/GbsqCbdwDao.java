package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.GbsqCbdwDO;
import com.bootdo.proposal.domain.TaxxCbdwDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author shipan
 * @email 
 * @date 2018-10-21 15:32:38
 */
@Mapper
public interface GbsqCbdwDao {

	GbsqCbdwDO get(Integer id);
	
	List<GbsqCbdwDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(GbsqCbdwDO taxxCbdw);
	
	int update(GbsqCbdwDO taxxCbdw);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	Map<String,Object> getCbdw(Map<String,Object> map);
}
