package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.proposal.dao.PfbDao;
import com.bootdo.proposal.domain.PfbDO;
import com.bootdo.proposal.service.PfbService;



@Service
public class PfbServiceImpl implements PfbService {
	@Autowired
	private PfbDao pfbDao;
	
	@Override
	public PfbDO get(Integer id){
		return pfbDao.get(id);
	}
	
	@Override
	public List<PfbDO> list(Map<String, Object> map){
		return pfbDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return pfbDao.count(map);
	}
	
	@Override
	public int save(PfbDO pfb){
		return pfbDao.save(pfb);
	}
	
	@Override
	public int update(PfbDO pfb){
		return pfbDao.update(pfb);
	}
	
	@Override
	public int remove(Integer id){
		return pfbDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return pfbDao.batchRemove(ids);
	}
	
}
