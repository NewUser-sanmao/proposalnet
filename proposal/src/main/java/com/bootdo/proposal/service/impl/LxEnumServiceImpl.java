package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.proposal.dao.LxEnumDao;
import com.bootdo.proposal.domain.LxEnumDO;
import com.bootdo.proposal.service.LxEnumService;



@Service
public class LxEnumServiceImpl implements LxEnumService {
	@Autowired
	private LxEnumDao lxEnumDao;
	
	@Override
	public LxEnumDO get(Integer id){
		return lxEnumDao.get(id);
	}
	
	@Override
	public List<LxEnumDO> list(Map<String, Object> map){
		return lxEnumDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return lxEnumDao.count(map);
	}
	
	@Override
	public int save(LxEnumDO lxEnum){
		return lxEnumDao.save(lxEnum);
	}
	
	@Override
	public int update(LxEnumDO lxEnum){
		return lxEnumDao.update(lxEnum);
	}
	
	@Override
	public int remove(Integer id){
		return lxEnumDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return lxEnumDao.batchRemove(ids);
	}
	
}
