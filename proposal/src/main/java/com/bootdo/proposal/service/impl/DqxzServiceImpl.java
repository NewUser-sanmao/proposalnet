package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.proposal.dao.DqxzDao;
import com.bootdo.proposal.domain.DqxzDO;
import com.bootdo.proposal.service.DqxzService;



@Service
public class DqxzServiceImpl implements DqxzService {
	@Autowired
	private DqxzDao dqxzDao;
	
	@Override
	public DqxzDO get(Integer id){
		return dqxzDao.get(id);
	}
	
	@Override
	public List<DqxzDO> list(Map<String, Object> map){
		return dqxzDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return dqxzDao.count(map);
	}
	
	@Override
	public int save(DqxzDO dqxz){
		return dqxzDao.save(dqxz);
	}
	
	@Override
	public int update(DqxzDO dqxz){
		return dqxzDao.update(dqxz);
	}
	
	@Override
	public int remove(Integer id){
		return dqxzDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return dqxzDao.batchRemove(ids);
	}

	@Override
	public List<Map<String, Object>> selectAllSelect() {
		// TODO Auto-generated method stub
		return dqxzDao.selectAllSelect();
	}
	
}
