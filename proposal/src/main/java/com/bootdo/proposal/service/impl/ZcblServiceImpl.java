package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.proposal.dao.ZcblDao;
import com.bootdo.proposal.domain.ZcblDO;
import com.bootdo.proposal.service.ZcblService;



@Service
public class ZcblServiceImpl implements ZcblService {
	@Autowired
	private ZcblDao zcblDao;
	
	@Override
	public ZcblDO get(Integer id){
		return zcblDao.get(id);
	}
	
	@Override
	public List<ZcblDO> list(Map<String, Object> map){
		return zcblDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return zcblDao.count(map);
	}
	
	@Override
	public int save(ZcblDO zcbl){
		return zcblDao.save(zcbl);
	}
	
	@Override
	public int update(ZcblDO zcbl){
		return zcblDao.update(zcbl);
	}
	
	@Override
	public int remove(Integer id){
		return zcblDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return zcblDao.batchRemove(ids);
	}
	
}
