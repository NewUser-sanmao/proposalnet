package com.bootdo.proposal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.proposal.dao.GbsqCbdwDao;
import com.bootdo.proposal.domain.GbsqCbdwDO;
import com.bootdo.proposal.service.GbsqCbdwService;



@Service
public class GbsqCbdwServiceImpl implements GbsqCbdwService {
	@Autowired
	private GbsqCbdwDao gbsqCbdwDao;
	
	@Override
	public GbsqCbdwDO get(Integer id){
		return gbsqCbdwDao.get(id);
	}
	
	@Override
	public List<GbsqCbdwDO> list(Map<String, Object> map){
		return gbsqCbdwDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return gbsqCbdwDao.count(map);
	}
	
	@Override
	public int save(GbsqCbdwDO taxxCbdw){
		return gbsqCbdwDao.save(taxxCbdw);
	}
	
	@Override
	public int update(GbsqCbdwDO taxxCbdw){
		return gbsqCbdwDao.update(taxxCbdw);
	}
	
	@Override
	public int remove(Integer id){
		return gbsqCbdwDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return gbsqCbdwDao.batchRemove(ids);
	}

	@Override
	public Map<String, Object> getCbdw(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return gbsqCbdwDao.getCbdw(map);
	}
	
}
