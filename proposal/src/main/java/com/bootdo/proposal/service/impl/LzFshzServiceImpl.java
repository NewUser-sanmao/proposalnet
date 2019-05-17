package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.proposal.dao.LzFshzDao;
import com.bootdo.proposal.domain.LzFshzDO;
import com.bootdo.proposal.service.LzFshzService;



@Service
public class LzFshzServiceImpl implements LzFshzService {
	@Autowired
	private LzFshzDao lzFshzDao;
	
	@Override
	public LzFshzDO get(Integer id){
		return lzFshzDao.get(id);
	}
	
	@Override
	public List<LzFshzDO> list(Map<String, Object> map){
		return lzFshzDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return lzFshzDao.count(map);
	}
	
	@Override
	public int save(LzFshzDO lzFshz){
		return lzFshzDao.save(lzFshz);
	}
	
	@Override
	public int update(LzFshzDO lzFshz){
		return lzFshzDao.update(lzFshz);
	}
	
	@Override
	public int remove(Integer id){
		return lzFshzDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return lzFshzDao.batchRemove(ids);
	}
	
}
