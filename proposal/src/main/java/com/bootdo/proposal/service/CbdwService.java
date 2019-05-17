package com.bootdo.proposal.service;

import com.bootdo.proposal.domain.CbdwDO;
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
 * @date 2018-10-19 14:22:10
 */
public interface CbdwService {


	CbdwDO get(Integer id);
	
	List<CbdwDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CbdwDO cbdw, GrwyDO grwy);
	
	int update(CbdwDO cbdw, GrwyDO grwy);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<GrwyDO> listCbdw(Map<String, Object> map);
	
	int listCbdwCount(Map<String,Object> map);
	
	List<Map<String,Object>> selectAllSelectByTaxxId(Map<String,Object> map);
	
	/**
	 * 导入
	 * @param upFile
	 * @return
	 * @throws IOException
	 */
	Map<String,Object> saveExcel(MultipartFile upFile) throws IOException;
	
	List<CbdwDO> getTreeData(Map<String, Object> map);
}
