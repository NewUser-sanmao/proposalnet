package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.dao.UpdateStateDao;
import com.bootdo.proposal.domain.UpdateStateDO;
import com.bootdo.proposal.service.UpdateStateService;



@Service
public class UpdateStateServiceImpl implements UpdateStateService {
	@Autowired
	private UpdateStateDao updateStateDao;
	
	@Override
	public UpdateStateDO get(Integer id){
		return updateStateDao.get(id);
	}
	
	@Override
	public List<UpdateStateDO> list(Map<String, Object> map){
		return updateStateDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return updateStateDao.count(map);
	}
	
	@Override
	public int save(UpdateStateDO updateState){
		updateState.setCreateid(ShiroUtils.getUserIdInteger());
		updateState.setCreatetime(new Date());
		updateState.setUpdateid(ShiroUtils.getUserIdInteger());
		updateState.setUpdatetime(new Date());
		updateState.setState(1);
		return updateStateDao.save(updateState);
	}
	
	@Override
	public int update(UpdateStateDO updateState){
		return updateStateDao.update(updateState);
	}
	
	@Override
	public int remove(Integer id){
		return updateStateDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return updateStateDao.batchRemove(ids);
	}
	
}
