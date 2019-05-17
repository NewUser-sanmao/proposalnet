package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.dao.DpDao;
import com.bootdo.proposal.domain.DpDO;
import com.bootdo.proposal.service.DpService;



@Service
public class DpServiceImpl implements DpService {
	@Autowired
	private DpDao dpDao;
	
	@Override
	public DpDO get(Integer id){
		return dpDao.get(id);
	}
	
	@Override
	public List<DpDO> list(Map<String, Object> map){
		return dpDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return dpDao.count(map);
	}
	
	@Override
	public int save(DpDO dp){
		dp.setCreateid(ShiroUtils.getUserIdInteger());
		dp.setCreatetime(new Date());
		dp.setUpdateid(ShiroUtils.getUserIdInteger());
		dp.setUpdatetime(new Date());
		dp.setState(1);
		return dpDao.save(dp);
	}
	
	@Override
	public int update(DpDO dp){
		return dpDao.update(dp);
	}
	
	@Override
	public int remove(Integer id){
		return dpDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return dpDao.batchRemove(ids);
	}

	@Override
	public List<Map<String, Object>> selectAllSelect() {
		// TODO Auto-generated method stub
		return dpDao.selectAllSelect();
	}
	
}
