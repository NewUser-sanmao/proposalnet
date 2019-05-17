package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.dao.TajbDao;
import com.bootdo.proposal.domain.TajbDO;
import com.bootdo.proposal.service.TajbService;



@Service
public class TajbServiceImpl implements TajbService {
	@Autowired
	private TajbDao tajbDao;
	
	@Override
	public TajbDO get(Integer id){
		return tajbDao.get(id);
	}
	
	@Override
	public List<TajbDO> list(Map<String, Object> map){
		return tajbDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tajbDao.count(map);
	}
	
	@Override
	public int save(TajbDO tajb){
		tajb.setCreateid(ShiroUtils.getUserIdInteger());
		tajb.setCreatetime(new Date());
		tajb.setUpdateid(ShiroUtils.getUserIdInteger());
		tajb.setUpdatetime(new Date());
		tajb.setState(1);
		return tajbDao.save(tajb);
	}
	
	@Override
	public int update(TajbDO tajb){
		return tajbDao.update(tajb);
	}
	
	@Override
	public int remove(Integer id){
		return tajbDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return tajbDao.batchRemove(ids);
	}

	@Override
	public List<Map<String, Object>> selectAllSelect() {
		// TODO Auto-generated method stub
		return tajbDao.selectAllSelect();
	}
	
}
