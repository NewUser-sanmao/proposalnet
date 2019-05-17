package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.proposal.dao.TaxxCbdwDao;
import com.bootdo.proposal.domain.TaxxCbdwDO;
import com.bootdo.proposal.service.TaxxCbdwService;



@Service
public class TaxxCbdwServiceImpl implements TaxxCbdwService {
	@Autowired
	private TaxxCbdwDao taxxCbdwDao;
	
	@Override
	public TaxxCbdwDO get(Integer id){
		return taxxCbdwDao.get(id);
	}
	
	@Override
	public List<TaxxCbdwDO> list(Map<String, Object> map){
		return taxxCbdwDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return taxxCbdwDao.count(map);
	}
	
	@Override
	public int save(TaxxCbdwDO taxxCbdw){
		return taxxCbdwDao.save(taxxCbdw);
	}
	
	@Override
	public int update(TaxxCbdwDO taxxCbdw){
		return taxxCbdwDao.update(taxxCbdw);
	}
	
	@Override
	public int remove(Integer id){
		return taxxCbdwDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return taxxCbdwDao.batchRemove(ids);
	}

	@Override
	public Map<String, Object> getCbdw(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxCbdwDao.getCbdw(map);
	}

	@Override
	public List<Map<String, Object>> listMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxCbdwDao.listMap(map);
	}

	@Override
	public int countMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxCbdwDao.countMap(map);
	}
	
}
