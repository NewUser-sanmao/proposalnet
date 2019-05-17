package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.dao.TayyDao;
import com.bootdo.proposal.domain.TayyDO;
import com.bootdo.proposal.service.TayyService;



@Service
public class TayyServiceImpl implements TayyService {
	@Autowired
	private TayyDao tayyDao;
	
	@Override
	public TayyDO get(Integer id){
		return tayyDao.get(id);
	}
	
	@Override
	public List<TayyDO> list(Map<String, Object> map){
		return tayyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tayyDao.count(map);
	}
	
	@Override
	public int save(TayyDO tayy){
		tayy.setCreateid(ShiroUtils.getUserIdInteger());
		tayy.setCreatetime(new Date());
		tayy.setUpdateid(ShiroUtils.getUserIdInteger());
		tayy.setUpdatetime(new Date());
		tayy.setState(1);
		return tayyDao.save(tayy);
	}
	
	@Override
	public int update(TayyDO tayy){
		return tayyDao.update(tayy);
	}
	
	@Override
	public int remove(Integer id){
		return tayyDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return tayyDao.batchRemove(ids);
	}

	@Override
	public List<Map<String, Object>> selectAllSelect() {
		// TODO Auto-generated method stub
		return tayyDao.selectAllSelect();
	}
	
}
