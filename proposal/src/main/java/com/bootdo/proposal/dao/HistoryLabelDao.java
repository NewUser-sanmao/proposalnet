package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.HistoryLabelDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author shipan
 * @email 
 * @date 2018-11-20 11:10:53
 */
@Mapper
public interface HistoryLabelDao {

	HistoryLabelDO get(Integer id);
	
	List<HistoryLabelDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(HistoryLabelDO historyLabel);
	
	int update(HistoryLabelDO historyLabel);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
