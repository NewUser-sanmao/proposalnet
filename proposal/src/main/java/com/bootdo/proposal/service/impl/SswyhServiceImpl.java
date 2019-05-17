package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.dao.SswyhDao;
import com.bootdo.proposal.domain.SswyhDO;
import com.bootdo.proposal.service.SswyhService;



@Service
public class SswyhServiceImpl implements SswyhService {
	@Autowired
	private SswyhDao sswyhDao;
	
	@Override
	public SswyhDO get(Integer id){
		return sswyhDao.get(id);
	}
	
	@Override
	public List<SswyhDO> list(Map<String, Object> map){
		return sswyhDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return sswyhDao.count(map);
	}
	
	@Override
	public int save(SswyhDO sswyh){
		sswyh.setCreateid(ShiroUtils.getUserIdInteger());
		sswyh.setCreatetime(new Date());
		sswyh.setUpdateid(ShiroUtils.getUserIdInteger());
		sswyh.setUpdatetime(new Date());
		sswyh.setState(1);
		return sswyhDao.save(sswyh);
	}
	
	@Override
	public int update(SswyhDO sswyh){
		return sswyhDao.update(sswyh);
	}
	
	@Override
	public int remove(Integer id){
		return sswyhDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return sswyhDao.batchRemove(ids);
	}

	@Override
	public List<Map<String, Object>> selectAllSelect() {
		// TODO Auto-generated method stub
		return sswyhDao.selectAllSelect();
	}
	
}
