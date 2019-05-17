package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.CbdwDO;
import com.bootdo.proposal.domain.GrwyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author shipan
 * @email 
 * @date 2018-10-19 14:22:10
 */
@Mapper
public interface CbdwDao {

	CbdwDO get(Integer id);
	
	List<CbdwDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CbdwDO cbdw);
	
	int update(CbdwDO cbdw);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<GrwyDO> listCbdw(Map<String, Object> map);
	
	int listCbdwCount(Map<String,Object> map);
	
	List<Map<String,Object>> selectAllSelectByTaxxId(Map<String,Object> map);
	
	List<CbdwDO> getTreeData(Map<String, Object> map);
}
