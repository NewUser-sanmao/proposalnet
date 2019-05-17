package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.dao.GzfwDao;
import com.bootdo.proposal.domain.GzfwDO;
import com.bootdo.proposal.service.GzfwService;



@Service
public class GzfwServiceImpl implements GzfwService {
	@Autowired
	private GzfwDao gzfwDao;
	
	@Override
	public GzfwDO get(Integer id){
		return gzfwDao.get(id);
	}
	
	@Override
	public List<GzfwDO> list(Map<String, Object> map){
		return gzfwDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return gzfwDao.count(map);
	}
	
	@Override
	public int save(GzfwDO gzfw){
		gzfw.setCreateid(ShiroUtils.getUserIdInteger());
		gzfw.setCreatetime(new Date());
		gzfw.setUpdateid(ShiroUtils.getUserIdInteger());
		gzfw.setUpdatetime(new Date());
		gzfw.setState(1);
		
		return gzfwDao.save(gzfw);
	}
	
	@Override
	public int update(GzfwDO gzfw){
		return gzfwDao.update(gzfw);
	}
	
	@Override
	public int remove(Integer id){
		return gzfwDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return gzfwDao.batchRemove(ids);
	}
	
}
