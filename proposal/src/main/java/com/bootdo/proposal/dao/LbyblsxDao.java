package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.LbyblsxDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author shipan
 * @email 
 * @date 2018-08-08 15:05:29
 */
@Mapper
public interface LbyblsxDao {

	LbyblsxDO get(Integer id);
	
	List<LbyblsxDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(LbyblsxDO lbyblsx);
	
	int update(LbyblsxDO lbyblsx);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<Map<String,Object>> selectAllSelect();
	
	List<Map<String,Object>> getListJK(Map<String,Object> map);
}
