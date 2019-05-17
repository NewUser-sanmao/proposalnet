package com.bootdo.proposal.dao;

import com.bootdo.proposal.domain.TaxxDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 提案信息
 * @author shipan
 * @email 
 * @date 2018-10-17 14:16:06
 */
@Mapper
public interface TaxxDao {

	TaxxDO get(Integer id);
	
	List<TaxxDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TaxxDO taxx);
	
	int update(TaxxDO taxx);
	
	/**
	 * 退回预审
	 * @param taxx
	 * @return
	 */
	int updateIsNull(TaxxDO taxx);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	int realRemove(Integer[] ids);
	
	/**
	 * 恢复提案
	 * @param ids
	 * @return
	 */
	int batchRecovery(Integer[] ids);
	
	List<TaxxDO> tablList(Map<String,Object> map);
	
	int tablCount(Map<String,Object> map);
	
	List<Map<String,Object>> listMap(Map<String,Object> map);
	
	int listMapCount(Map<String,Object> map);
	
	int getMaxLsh(Map<String,Object> map);
	
	int getMaxTah(Map<String,Object> map);
	
	/**
	 * 根据id获取这个提案届次的最大的提案号
	 * @param id
	 * @return
	 */
	int getMaxTahById(Integer id);
	
	/**
	 * 根据id获取这个提案届次的最大的意见号
	 * @param id
	 * @return
	 */
	int getMaxYjhById(Integer id);
	
	List<TaxxDO> getListByIds(Integer[] ids);
	
	/**
	 * 清除提案号
	 * @param ids
	 * @return
	 */
	int updateTahClear(Integer[] ids);
	
	int updateBaZ(Map<String,Object> map);
	
	int updateBaF(Map<String,Object> map);
	
	/**
	 * 修改提案号,同时修改此提案号的辅提案
	 * @param taxx
	 * @return
	 */
	int updateTahAndSon(TaxxDO taxx);
	
	/**
	 * 获取提案的承办单位
	 * @return
	 */
	List<Map<String,Object>> getCbdwGroupConcat(Map<String,Object> map);
	
	/**
	 * 办理提案,意见统计
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> bltayjtj(Map<String,Object> map);
	
	/**
	 * 提案统计表
	 * @param map type:1个人提案 2集体提案,zxjcId:政协届次
	 * @return
	 */
	List<Map<String,Object>> tatjb(Map<String,Object> map);
	
	/**
	 * 集体提案目录统计
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> tamltj(Map<String,Object> map);
	
	/**
	 * 提案承办单位汇总
	 * @param map zxjcId:政协届次id
	 * @return
	 */
	List<Map<String,Object>> tacbdwhz(Map<String,Object> map);
	
	/**
	 * 个人提案统计表
	 * @param map type:1个人提案 2集体提案,zxjcId:政协届次
	 * @return
	 */
	List<Map<String,Object>> grtatjb(Map<String,Object> map);
	
	/**
	 * 委员\集体撰写提案情况
	 * @param map type:1委员 2集体,zxjcId:政协届次
	 * @return
	 */
	List<Map<String,Object>> zxtaqk(Map<String,Object> map);
	
	/**
	 * 提案分类目录
	 * @param map zxjcId:政协届次
	 * @return
	 */
	List<Map<String,Object>> taflml(Map<String,Object> map);
	
	/**
	 * 提案分类综合统计
	 * @param map zxjcId:政协届次
	 * @return
	 */
	List<Map<String,Object>> taflzhtj(Map<String,Object> map);
	
	/**
	 * 转意见目录统计
	 * @param map zxjcId:政协届次
	 * @return
	 */
	List<Map<String,Object>> zyjmltj(Map<String,Object> map);
	
	/**
	 * 承办单位首页需要的数据
	 * @param map
	 * @return
	 */
	Map<String,Object> cbdwInfoDate(Map<String,Object> map);
	
	/**
	 * 管理员首页需要的数据
	 * @param map
	 * @return
	 */
	Map<String,Object> glyInfoDate(Map<String,Object> map);
	
	/**
	 * 导出多个Word文档需要的
	 * @param ids
	 * @return
	 */
	List<Map<String,Object>> exportWordArrData(Integer[] ids);
	
	/**
	 * 导出多复函
	 * @param ids
	 * @return
	 */
	List<Map<String,Object>> exportWordArrFhData(Integer[] ids);
	
	/**
	 * 导出单个Word文档需要的
	 * @param id
	 * @return
	 */
	Map<String,Object> exportWordData(Integer id);
	
	/**
	 * 取消并案
	 * @param id
	 * @return
	 */
	int updateQxba(Integer id);

	/**
	 * 修改分办承办单位进度
	 * @param map
	 * @return
	 */
	int updateCbdwjd(Map<String,Object> map);

	/**
	 * 查询分办的承办单位进度
	 * @param map
	 * @return
	 */
	Integer findCbdwjd(Map<String,Object> map);

	/**
	 * 根据登录人查询承办单位id
	 * @param name
	 * @return
	 */
	Integer findUserId(String name);

	/**
	 * 根据提案查询所有协办单位的name
	 * @param taxxid
	 * @return
	 */
	String findCountByName(Integer taxxid);
}
