package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.proposal.dao.UtilDao;
import com.bootdo.proposal.domain.UtilDO;
import com.bootdo.proposal.service.UtilService;



@Service
public class UtilServiceImpl implements UtilService {
	@Autowired
	private UtilDao utilDao;
	
	@Override
	public UtilDO get(Integer id){
		return utilDao.get(id);
	}
	
	@Override
	public List<UtilDO> list(Map<String, Object> map){
		return utilDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return utilDao.count(map);
	}
	
	@Override
	public int save(UtilDO util){
		return utilDao.save(util);
	}
	
	@Override
	public int update(UtilDO util){
		return utilDao.update(util);
	}
	
	@Override
	public int remove(Integer id){
		return utilDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return utilDao.batchRemove(ids);
	}
	
}
