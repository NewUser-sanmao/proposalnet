package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.dao.HistoryLabelDao;
import com.bootdo.proposal.domain.HistoryLabelDO;
import com.bootdo.proposal.service.HistoryLabelService;



@Service
public class HistoryLabelServiceImpl implements HistoryLabelService {
	@Autowired
	private HistoryLabelDao historyLabelDao;
	
	@Override
	public HistoryLabelDO get(Integer id){
		return historyLabelDao.get(id);
	}
	
	@Override
	public List<HistoryLabelDO> list(Map<String, Object> map){
		return historyLabelDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return historyLabelDao.count(map);
	}
	
	@Override
	public int save(HistoryLabelDO historyLabel){
		Map<String, Object> map = new HashMap<>();
		map.put("grwyid", historyLabel.getGrwyid());
		map.put("zxjcid", historyLabel.getZxjcid());
		
		if(count(map) != 0) {
			return -1;
		}
		
		historyLabel.setCreateid(ShiroUtils.getUserIdInteger());
		historyLabel.setCreatetime(new Date());
		historyLabel.setUpdateid(ShiroUtils.getUserIdInteger());
		historyLabel.setUpdatetime(new Date());
		historyLabel.setState(1);
		return historyLabelDao.save(historyLabel);
	}
	
	@Override
	public int update(HistoryLabelDO historyLabel){
		return historyLabelDao.update(historyLabel);
	}
	
	@Override
	public int remove(Integer id){
		return historyLabelDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return historyLabelDao.batchRemove(ids);
	}
	
}
