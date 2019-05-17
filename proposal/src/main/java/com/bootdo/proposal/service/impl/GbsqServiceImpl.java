package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.dao.GbsqCbdwDao;
import com.bootdo.proposal.dao.GbsqDao;
import com.bootdo.proposal.dao.TaxxCbdwDao;
import com.bootdo.proposal.dao.TaxxDao;
import com.bootdo.proposal.domain.GbsqCbdwDO;
import com.bootdo.proposal.domain.GbsqDO;
import com.bootdo.proposal.domain.TaxxCbdwDO;
import com.bootdo.proposal.domain.TaxxDO;
import com.bootdo.proposal.service.GbsqService;



@Service
public class GbsqServiceImpl implements GbsqService {
	@Autowired
	private GbsqDao gbsqDao;
	@Autowired
	private GbsqCbdwDao gbsqCbdwDao;
	@Autowired
	private TaxxDao taxxDao;
	@Autowired
	private TaxxCbdwDao taxxCbdwDao;
	
	
	@Override
	public GbsqDO get(Integer id){
		return gbsqDao.get(id);
	}
	
	@Override
	public List<GbsqDO> list(Map<String, Object> map){
		return gbsqDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return gbsqDao.count(map);
	}
	
	@Override
	public int save(GbsqDO gbsq,String[] cbdw,String[] fbdw,String[] xbdw){
		gbsq.setCreateid(ShiroUtils.getUserIdInteger());
		gbsq.setCreatetime(new Date());
		gbsq.setUpdateid(ShiroUtils.getUserIdInteger());
		gbsq.setUpdatetime(new Date());
		gbsq.setState(1);
		gbsq.setType(0);
		int gbsqId = 0;
		
		if(gbsq.getId()==null) {
			gbsqDao.save(gbsq);
			gbsqId = gbsq.getId();
		}else {
			gbsqId = gbsq.getId();
			gbsqDao.update(gbsq);
			
			Map<String,Object> map = new HashMap<>();
			map.put("taxxid", gbsqId);
			List<GbsqCbdwDO> taxxCbdwDOList = gbsqCbdwDao.list(map);
			if(taxxCbdwDOList!=null && taxxCbdwDOList.size()>0) {
				for(GbsqCbdwDO t : taxxCbdwDOList) {
					gbsqCbdwDao.remove(t.getId());
				}
			}
			
		}
		
		if(cbdw!=null && cbdw.length>0 && (gbsq.getLatype().intValue() == 1 || gbsq.getLatype().intValue() == 3)) {
			for(String cbdwId : cbdw){
				GbsqCbdwDO t = new GbsqCbdwDO();
				t.setCreateid(ShiroUtils.getUserIdInteger());
				t.setCreatetime(new Date());
				t.setUpdateid(ShiroUtils.getUserIdInteger());
				t.setUpdatetime(new Date());
				t.setState(1);
				t.setCbdwid(Integer.parseInt(cbdwId));
				t.setTaxxid(gbsqId);
				t.setType(1);
				gbsqCbdwDao.save(t);
			}
		}
		
		if(fbdw!=null && fbdw.length>0 && gbsq.getLatype().intValue() == 2) {
			for(String fbdwId : fbdw){
				GbsqCbdwDO t = new GbsqCbdwDO();
				t.setCreateid(ShiroUtils.getUserIdInteger());
				t.setCreatetime(new Date());
				t.setUpdateid(ShiroUtils.getUserIdInteger());
				t.setUpdatetime(new Date());
				t.setState(1);
				t.setCbdwid(Integer.parseInt(fbdwId));
				t.setTaxxid(gbsqId);
				t.setType(2);
				gbsqCbdwDao.save(t);
			}
		}
		
		if(xbdw!=null && xbdw.length>0 && gbsq.getLatype().intValue() == 3) {
			for(String xbdwId : xbdw){
				GbsqCbdwDO t = new GbsqCbdwDO();
				t.setCreateid(ShiroUtils.getUserIdInteger());
				t.setCreatetime(new Date());
				t.setUpdateid(ShiroUtils.getUserIdInteger());
				t.setUpdatetime(new Date());
				t.setState(1);
				t.setCbdwid(Integer.parseInt(xbdwId));
				t.setTaxxid(gbsqId);
				t.setType(3);
				gbsqCbdwDao.save(t);
			}
		}
		
		return gbsqId;
	}
	
	@Override
	public int update(GbsqDO gbsq){
		
		if(gbsq.getType().intValue() == 1) {//同意
			updateTaxxDO(gbsq);
		}
		
		return gbsqDao.update(gbsq);
	}
	
	private void updateTaxxDO(GbsqDO gbsq) {//修改原提案
		TaxxDO taxx = taxxDao.get(gbsq.getTaxxid());
		if(taxx.getLatype().intValue() != gbsq.getLatype().intValue()){//查看立案类型是否相同
			TaxxDO ls_t = new TaxxDO();
			ls_t.setId(taxx.getId());
			ls_t.setLatype(gbsq.getLatype());
			taxxDao.update(ls_t);
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("taxxid", gbsq.getTaxxid());
		List<TaxxCbdwDO> taxxCbdwDOList = taxxCbdwDao.list(map);
		if(taxxCbdwDOList!=null && taxxCbdwDOList.size()>0) {//删除之前的承办单位
			for(TaxxCbdwDO t : taxxCbdwDOList) {
				taxxCbdwDao.remove(t.getId());
			}
		}
		
		map.clear();
		map.put("taxxid", gbsq.getId());
		List<GbsqCbdwDO> gbsqCbdwDOList = gbsqCbdwDao.list(map);
		if(gbsqCbdwDOList!=null && gbsqCbdwDOList.size()>0) {//保存现在的承办单位
			for(GbsqCbdwDO t : gbsqCbdwDOList) {
				TaxxCbdwDO taxxCbdwDO = new TaxxCbdwDO();
				taxxCbdwDO.setCreateid(ShiroUtils.getUserIdInteger());
				taxxCbdwDO.setCreatetime(new Date());
				taxxCbdwDO.setUpdateid(ShiroUtils.getUserIdInteger());
				taxxCbdwDO.setUpdatetime(new Date());
				taxxCbdwDO.setState(1);
				
				taxxCbdwDO.setCbdwid(t.getCbdwid());
				taxxCbdwDO.setTaxxid(taxx.getId());
				taxxCbdwDO.setType(t.getType());
				taxxCbdwDao.save(taxxCbdwDO);
			}
		}
	}
	
	@Override
	public int remove(Integer id){
		Map<String,Object> map = new HashMap<>();
		map.put("taxxid", id);
		List<GbsqCbdwDO> taxxCbdwDOList = gbsqCbdwDao.list(map);
		if(taxxCbdwDOList!=null && taxxCbdwDOList.size()>0) {
			for(GbsqCbdwDO t : taxxCbdwDOList) {
				gbsqCbdwDao.remove(t.getId());
			}
		}
		
		return gbsqDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return gbsqDao.batchRemove(ids);
	}

	@Override
	public List<Map<String, Object>> tagbList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return gbsqDao.tagbList(map);
	}

	@Override
	public int tagbCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return gbsqDao.tagbCount(map);
	}
	
}
