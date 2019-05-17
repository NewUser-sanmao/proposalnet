package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.PfbTaxxDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 提案信息对应的评分表
 * @author shipan
 * @email 
 * @date 2018-11-07 16:49:05
 */
@Mapper
public interface PfbTaxxDao {

	PfbTaxxDO get(Integer id);
	
	List<PfbTaxxDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PfbTaxxDO pfbTaxx);
	
	int update(PfbTaxxDO pfbTaxx);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	/**
	 * 区政协 届 次会议提案质量评价情况汇总统计表
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> listMap(Map<String,Object> map);
	/**
	 * 区政协 届 次会议提案质量评价情况汇总统计表
	 * @param map
	 * @return
	 */
	int listMapCount(Map<String,Object> map);
	
	/**
	 * 渝北区政协  届  次会议第  号提案办理工作评价表
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> tablpjqkMap(Map<String,Object> map);

	/**
	 * 根据名字查询该cbdw是否已评分
	 * @param map
	 * @return
	 */
	int findByNameCount(Map<String,Object> map);
	
}
