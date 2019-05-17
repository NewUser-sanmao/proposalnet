package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.dao.LbyblsxDao;
import com.bootdo.proposal.domain.LbyblsxDO;
import com.bootdo.proposal.service.LbyblsxService;



@Service
public class LbyblsxServiceImpl implements LbyblsxService {
	@Autowired
	private LbyblsxDao lbyblsxDao;
	
	@Override
	public LbyblsxDO get(Integer id){
		return lbyblsxDao.get(id);
	}
	
	@Override
	public List<LbyblsxDO> list(Map<String, Object> map){
		return lbyblsxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return lbyblsxDao.count(map);
	}
	
	@Override
	public int save(LbyblsxDO lbyblsx){
		lbyblsx.setCreateid(ShiroUtils.getUserIdInteger());
		lbyblsx.setCreatetime(new Date());
		lbyblsx.setUpdateid(ShiroUtils.getUserIdInteger());
		lbyblsx.setUpdatetime(new Date());
		lbyblsx.setState(1);
		return lbyblsxDao.save(lbyblsx);
	}
	
	@Override
	public int update(LbyblsxDO lbyblsx){
		return lbyblsxDao.update(lbyblsx);
	}
	
	@Override
	public int remove(Integer id){
		return lbyblsxDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return lbyblsxDao.batchRemove(ids);
	}

	@Override
	public List<Map<String, Object>> selectAllSelect() {
		// TODO Auto-generated method stub
		return lbyblsxDao.selectAllSelect();
	}

	@Override
	public List<Map<String, Object>> getListJK(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return lbyblsxDao.getListJK(map);
	}
	
}
