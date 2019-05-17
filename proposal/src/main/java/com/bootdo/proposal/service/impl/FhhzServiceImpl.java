package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.dao.FhhzDao;
import com.bootdo.proposal.domain.FhhzDO;
import com.bootdo.proposal.service.FhhzService;



@Service
public class FhhzServiceImpl implements FhhzService {
	@Autowired
	private FhhzDao fhhzDao;
	
	@Override
	public FhhzDO get(Integer id){
		return fhhzDao.get(id);
	}
	
	@Override
	public List<FhhzDO> list(Map<String, Object> map){
		return fhhzDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return fhhzDao.count(map);
	}

	@Override
	public int conuntByTaxxid(Map<String, Object> map){
		return fhhzDao.conuntByTaxxid(map);
	}

	@Override
	public int save(FhhzDO fhhz){
		fhhz.setCreateid(ShiroUtils.getUserIdInteger());
		fhhz.setCreatetime(new Date());
		fhhz.setUpdateid(ShiroUtils.getUserIdInteger());
		fhhz.setUpdatetime(new Date());
		fhhz.setState(1);
		return fhhzDao.save(fhhz);
	}
	
	@Override
	public int update(FhhzDO fhhz){
		return fhhzDao.update(fhhz);
	}
	
	@Override
	public int remove(Integer id){
		return fhhzDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return fhhzDao.batchRemove(ids);
	}

	@Override
	public List<Map<String, Object>> listMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return fhhzDao.listMap(map);
	}

	@Override
	public int countMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return fhhzDao.countMap(map);
	}

	/**
	 *查询当前用户是否提交回复函1：已提交意见稿 2：已提交正式函 0：没有提交意见稿
	 * @param map
	 * @return
	 */
	public Integer findByFhhz(Map<String,Object> map){
		return fhhzDao.findByFhhz(map);
	};

	public Integer findByName(Map<String,Object> map){
		return fhhzDao.findByName(map);
	};
}
