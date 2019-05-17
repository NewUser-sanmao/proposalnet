package com.bootdo.proposal.service;

import com.bootdo.proposal.domain.GrwyDO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-08-13 09:29:05
 */
public interface GrwyService {
	
	GrwyDO get(Integer id);
	
	List<GrwyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GrwyDO grwy);
	
	int update(GrwyDO grwy);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	GrwyDO getByUserId(Integer userId);
	
	List<GrwyDO> getListByDwmcs(Map<String,Object> map);
	/**
	 * 导入
	 * @param upFile
	 * @param type 1个人委员 2集体委员
	 * @return
	 * @throws IOException
	 */
	Map<String,Object> saveExcel(MultipartFile upFile, Integer type) throws IOException;
	
	GrwyDO getByDwmcId(Integer dwmcId);
	
	int resetPwd(Integer id);
}
