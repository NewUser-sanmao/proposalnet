package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.GzfwDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 工作服务
 * @author shipan
 * @email 
 * @date 2018-10-25 10:42:22
 */
@Mapper
public interface GzfwDao {

	GzfwDO get(Integer id);
	
	List<GzfwDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(GzfwDO gzfw);
	
	int update(GzfwDO gzfw);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
