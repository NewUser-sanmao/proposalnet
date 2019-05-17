package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.dao.ZxjcDao;
import com.bootdo.proposal.domain.ZxjcDO;
import com.bootdo.proposal.service.ZxjcService;



@Service
public class ZxjcServiceImpl implements ZxjcService {
	@Autowired
	private ZxjcDao zxjcDao;
	
	@Override
	public ZxjcDO get(Integer id){
		return zxjcDao.get(id);
	}
	
	@Override
	public List<ZxjcDO> list(Map<String, Object> map){
		return zxjcDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return zxjcDao.count(map);
	}
	
	@Override
	public int save(ZxjcDO zxjc){
		zxjc.setCreateid(ShiroUtils.getUserIdInteger());
		zxjc.setCreatetime(new Date());
		zxjc.setUpdateid(ShiroUtils.getUserIdInteger());
		zxjc.setUpdatetime(new Date());
		zxjc.setState(1);
		return zxjcDao.save(zxjc);
	}
	
	@Override
	public int update(ZxjcDO zxjc){
		return zxjcDao.update(zxjc);
	}
	
	@Override
	public int remove(Integer id){
		return zxjcDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return zxjcDao.batchRemove(ids);
	}

	@Override
	public List<Map<String, Object>> selectAllSelect() {
		// TODO Auto-generated method stub
		return zxjcDao.selectAllSelect();
	}

	@Override
	public ZxjcDO getNewest() {
		// TODO Auto-generated method stub
		return zxjcDao.getNewest();
	}
	
}
