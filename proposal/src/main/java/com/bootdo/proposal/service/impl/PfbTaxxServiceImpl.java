package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.dao.PfbTaxxDao;
import com.bootdo.proposal.domain.PfbTaxxDO;
import com.bootdo.proposal.service.PfbTaxxService;



@Service
public class PfbTaxxServiceImpl implements PfbTaxxService {
	@Autowired
	private PfbTaxxDao pfbTaxxDao;
	
	@Override
	public PfbTaxxDO get(Integer id){
		return pfbTaxxDao.get(id);
	}
	
	@Override
	public List<PfbTaxxDO> list(Map<String, Object> map){
		return pfbTaxxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return pfbTaxxDao.count(map);
	}
	
	@Override
	public int save(PfbTaxxDO pfbTaxx){
		return pfbTaxxDao.save(pfbTaxx);
	}
	
	@Override
	public int update(PfbTaxxDO pfbTaxx){
		return pfbTaxxDao.update(pfbTaxx);
	}
	
	@Override
	public int remove(Integer id){
		return pfbTaxxDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return pfbTaxxDao.batchRemove(ids);
	}

	@Override
	public int saveList(Integer[] ids, Integer[] vals, Integer taxxid, Integer type) {
		// TODO Auto-generated method stub
		if(ids==null || ids.length==0 || vals==null || vals.length==0) {
			return 0;
		}
		Map<String,Object> map = new HashMap<>();
		map.put("taxxid", taxxid);
		map.put("type", type);
		map.put("createid",ShiroUtils.getUserIdInteger());
		List<PfbTaxxDO> list = pfbTaxxDao.list(map);
		if(list!=null && list.size()>0) {
			Integer[] removeIds = new Integer[list.size()];
			for(int i=0;i<list.size();i++) {
				removeIds[i] = list.get(i).getId();
			}
			pfbTaxxDao.batchRemove(removeIds);
		}
		
		int sum = 0;
		for(int i=0;i<ids.length;i++) {
			PfbTaxxDO p = new PfbTaxxDO();
			p.setCreateid(ShiroUtils.getUserIdInteger());
			p.setCreatetime(new Date());
			p.setUpdateid(ShiroUtils.getUserIdInteger());
			p.setUpdatetime(new Date());
			p.setState(1);
			p.setPfbid(ids[i]);
			p.setFz(vals[i]);
			p.setType(type);
			p.setTaxxid(taxxid);
			sum += pfbTaxxDao.save(p);
		}



		return sum;
	}

	@Override
	public List<Map<String, Object>> listMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return pfbTaxxDao.listMap(map);
	}

	@Override
	public int listMapCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return pfbTaxxDao.listMapCount(map);
	}

	@Override
	public List<Map<String, Object>> tablpjqkMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return pfbTaxxDao.tablpjqkMap(map);
	}

	public int findByNameCount(Map<String,Object> map){
		return pfbTaxxDao.findByNameCount(map);
	};
}
