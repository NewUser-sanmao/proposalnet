package com.bootdo.proposal.service;

import com.bootdo.proposal.domain.FhhzDO;

import java.util.List;
import java.util.Map;

/**
 * 复函回执
 * 
 * @author shipan
 * @email 
 * @date 2018-10-24 15:13:09
 */
public interface FhhzService {
	
	FhhzDO get(Integer id);
	
	List<FhhzDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);

	int conuntByTaxxid(Map<String, Object> map);

	int save(FhhzDO fhhz);
	
	int update(FhhzDO fhhz);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<Map<String, Object>> listMap(Map<String,Object> map);
	
	int countMap(Map<String,Object> map);

	/**
	 *查询当前用户是否提交回复函1：已提交意见稿 2：已提交正式函 0：没有提交意见稿
	 * @param map
	 * @return
	 */
	Integer findByFhhz(Map<String,Object> map);

	/**
	 * 查询该提案已提交正式函的单位 0：为完成 1：已提交
	 * @param map
	 * @return
	 */
	Integer findByName(Map<String,Object> map);
}
