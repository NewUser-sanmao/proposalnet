package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.dao.FhhzDao;
import com.bootdo.proposal.dao.GrwyDao;
import com.bootdo.proposal.dao.PfbTaxxDao;
import com.bootdo.proposal.dao.TaxxCbdwDao;
import com.bootdo.proposal.dao.TaxxDao;
import com.bootdo.proposal.dao.ZcblDao;
import com.bootdo.proposal.domain.FhhzDO;
import com.bootdo.proposal.domain.GrwyDO;
import com.bootdo.proposal.domain.PfbTaxxDO;
import com.bootdo.proposal.domain.TaxxCbdwDO;
import com.bootdo.proposal.domain.TaxxDO;
import com.bootdo.proposal.domain.UpdateStateDO;
import com.bootdo.proposal.domain.ZcblDO;
import com.bootdo.proposal.service.FsdxService;
import com.bootdo.proposal.service.TaxxService;
import com.bootdo.proposal.service.UpdateStateService;



@Service
public class TaxxServiceImpl implements TaxxService {
	@Autowired
	private TaxxDao taxxDao;
	@Autowired
	private GrwyDao grwyDao;
	@Autowired
	private TaxxCbdwDao taxxCbdwDao;
	@Autowired
	private UpdateStateService updateStateService;
	@Autowired
	private PfbTaxxDao pfbTaxxDao;
	@Autowired
	private ZcblDao zcblDao;
	@Autowired
	private FhhzDao fhhzDao;
	@Autowired
	private FsdxService fsdxService;
	
	
	@Override
	public TaxxDO get(Integer id){
		return taxxDao.get(id);
	}
	
	@Override
	public List<TaxxDO> list(Map<String, Object> map){
		return taxxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return taxxDao.count(map);
	}
	
	@Override
	public synchronized int save(TaxxDO taxx){
		Integer userId = ShiroUtils.getUserIdInteger();
		GrwyDO wy = grwyDao.getByUserId(userId);
		taxx.setCreateid(ShiroUtils.getUserIdInteger());
		taxx.setCreatetime(new Date());
		taxx.setUpdateid(ShiroUtils.getUserIdInteger());
		taxx.setUpdatetime(new Date());
		taxx.setState(1);
		taxx.setTanx(wy.getType());
		taxx.setTastate(0);
		taxx.setLastate(0);
		Map<String,Object> map = new HashMap<>();
		map.put("zxjcId", taxx.getZxjcid());
		int maxLsh = taxxDao.getMaxLsh(map);
		taxx.setLsh((maxLsh+1)+"");
		
		return taxxDao.save(taxx);
	}
	
	@Override
	public int update(TaxxDO taxx){
		return taxxDao.update(taxx);
	}
	
	@Override
	public int remove(Integer id){
		return taxxDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		/*for(Integer taxxid : ids) {
			Map<String,Object> map = new HashMap<>();
			map.put("taxxid", taxxid);
			List<TaxxCbdwDO> taxxCbdwDOList = taxxCbdwDao.list(map);
			if(taxxCbdwDOList!=null && taxxCbdwDOList.size()>0) {
				for(TaxxCbdwDO t : taxxCbdwDOList) {
					taxxCbdwDao.remove(t.getId());
				}
			}
		}*/
		
		return taxxDao.batchRemove(ids);
	}

	@Override
	public synchronized int updateTasc(TaxxDO taxx, String[] cbdw, String[] fbdw, String[] xbdw) {
		// TODO Auto-generated method stub
//		TaxxCbdwDO t = 
		TaxxDO td = taxxDao.get(taxx.getId());
		if(taxx.getTastate() < 3) {
			taxx.setTastate(taxx.getTastate()+1);
		}
		
		if(taxx.getLastate().intValue()==1) {
			taxx.setTaly(-1);
			/*if(td.getTah() == null) {
				Map<String, Object> getTahMap = new HashMap<>();
				getTahMap.put("zxjcId", taxx.getZxjcid());
				int tah = taxxDao.getMaxTah(getTahMap);
				taxx.setTah((tah+1)+"");
			}*/
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("taxxid", taxx.getId());
		List<TaxxCbdwDO> taxxCbdwDOList = taxxCbdwDao.list(map);
		if(taxxCbdwDOList!=null && taxxCbdwDOList.size()>0) {
			for(TaxxCbdwDO t : taxxCbdwDOList) {
				taxxCbdwDao.remove(t.getId());
			}
		}
		
		/*if(taxx.getLastate().intValue() != 2) {
			taxx.setTaly(null);
		}*/
		
		
		if(cbdw!=null && cbdw.length>0 && (taxx.getLatype().intValue() == 1 || taxx.getLatype().intValue() == 3) && taxx.getLastate().intValue() != 3) {
			for(String cbdwId : cbdw){
				TaxxCbdwDO t = new TaxxCbdwDO();
				t.setCreateid(ShiroUtils.getUserIdInteger());
				t.setCreatetime(new Date());
				t.setUpdateid(ShiroUtils.getUserIdInteger());
				t.setUpdatetime(new Date());
				t.setState(1);
				t.setCbdwid(Integer.parseInt(cbdwId));
				t.setTaxxid(taxx.getId());
				t.setType(1);
				taxxCbdwDao.save(t);
			}
		}
		
		if(fbdw!=null && fbdw.length>0 && taxx.getLatype().intValue() == 2 && taxx.getLastate().intValue()==1) {
			for(String fbdwId : fbdw){
				TaxxCbdwDO t = new TaxxCbdwDO();
				t.setCreateid(ShiroUtils.getUserIdInteger());
				t.setCreatetime(new Date());
				t.setUpdateid(ShiroUtils.getUserIdInteger());
				t.setUpdatetime(new Date());
				t.setState(1);
				t.setCbdwid(Integer.parseInt(fbdwId));
				t.setTaxxid(taxx.getId());
				t.setType(2);
				taxxCbdwDao.save(t);
			}
		}
		
		if(xbdw!=null && xbdw.length>0 && taxx.getLatype().intValue() == 3 && taxx.getLastate().intValue()==1) {
			for(String xbdwId : xbdw){
				TaxxCbdwDO t = new TaxxCbdwDO();
				t.setCreateid(ShiroUtils.getUserIdInteger());
				t.setCreatetime(new Date());
				t.setUpdateid(ShiroUtils.getUserIdInteger());
				t.setUpdatetime(new Date());
				t.setState(1);
				t.setCbdwid(Integer.parseInt(xbdwId));
				t.setTaxxid(taxx.getId());
				t.setType(3);
				taxxCbdwDao.save(t);
			}
		}
		
		return taxxDao.update(taxx);
	}

	@Override
	public int updateTastate(Integer[] ids, Integer tastate) {
		// TODO Auto-generated method stub
		int i = 0;
		List<TaxxDO> list = taxxDao.getListByIds(ids);
		for(TaxxDO td : list) {
			//保存修改日志
			UpdateStateDO us = new UpdateStateDO();
			us.setTaxxid(td.getId());
			us.setLastate(td.getLastate());
			us.setTastate(tastate);
			updateStateService.save(us);
			
			if(td.getIsba()!=null && td.getIsba().intValue()==1 && td.getBaid()!=null) {//被并案件不能单独转交
				continue;
			}
			TaxxDO taxx = new TaxxDO();
			taxx.setId(td.getId());
			taxx.setTastate(tastate);
			i+= taxxDao.updateTahAndSon(taxx);
			
			//发送短信4
			/*if(td.getLastate().intValue() == 1) {//立案
				GrwyDO grwy = grwyDao.getByUserId(td.getCreateid());
				List<String> tels = new ArrayList<>();
				tels.add(grwy.getSjhm());
				Map<String,String> par = new HashMap<>();
				par.put("tatm", td.getTatm());
				par.put("zxjcmc", td.getZxjcmc());
				par.put("tah", td.getTah());
				par.put("cbdw", td.getCbdw()+(td.getXbdw()==null||"".equals(td.getXbdw())?"":","+td.getXbdw()));
				fsdxService.fs(4, par, tels);
			}*/
		}
		
		return i;
	}

	@Override
	public List<TaxxDO> tablList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxDao.tablList(map);
	}

	@Override
	public int tablCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxDao.tablCount(map);
	}

	@Override
	public List<Map<String, Object>> listMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxDao.listMap(map);
	}

	@Override
	public int listMapCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxDao.listMapCount(map);
	}

	@Override
	public int getMaxLsh(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxDao.getMaxLsh(map);
	}

	@Override
	public int getMaxTah(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxDao.getMaxTah(map);
	}

	@Override
	public int updateBxtah(Integer[] ids) {
		// TODO Auto-generated method stub
		if(ids==null || ids.length==0) {
			return 0;
		}
		List<TaxxDO> list = taxxDao.getListByIds(ids);
		Map<Integer, TaxxDO> m = new HashMap<>();
		for(TaxxDO t : list) {
			m.put(t.getId(), t);
		}
		for(Integer id : ids) {
			TaxxDO td = m.get(id);
			if(td.getTah()!=null) {
				continue;
			}else if(td.getIsba()!=null && td.getIsba().intValue()==1 && td.getBaid()!=null) {//被并案件不能编写提案号
				continue;
			}
			
			if(td.getLastate().intValue() == 1) {
				int tah = taxxDao.getMaxTahById(td.getId());
				TaxxDO taxx = new TaxxDO();
				taxx.setId(td.getId());
				taxx.setTah((tah+1)+"");
				taxxDao.updateTahAndSon(taxx);
			}else if(td.getLastate().intValue() == 2){
				int yjh = taxxDao.getMaxYjhById(td.getId());
				TaxxDO taxx = new TaxxDO();
				taxx.setId(td.getId());
				taxx.setTah((yjh+1)+"");
				taxxDao.updateTahAndSon(taxx);
			}
		}
		
		return ids.length;
	}

	@Override
	public List<TaxxDO> getListByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		return taxxDao.getListByIds(ids);
	}

	@Override
	public int updateBa(Integer zid, Integer[] fids) {
		// TODO Auto-generated method stub
		TaxxDO taxx = taxxDao.get(zid);
		if(taxx==null) {
			return 0;
		}
		
		int i = 0;
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", zid);
		map.put("isba", 1);
		i += taxxDao.updateBaZ(map);
		map.clear();
		
		map.put("fids", fids);
		map.put("isba", 1);
		map.put("baid", zid);
		if(taxx.getTah()!=null) {
			map.put("tah", taxx.getTah());
		}
		i += taxxDao.updateBaF(map);
		
		return i;
	}

	@Override
	public List<Map<String, Object>> getCbdwGroupConcat(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxDao.getCbdwGroupConcat(map);
	}

	@Override
	public int updateTahAndSon(TaxxDO taxx) {
		// TODO Auto-generated method stub
		return taxxDao.updateTahAndSon(taxx);
	}

	@Override
	public List<Map<String, Object>> bltayjtj(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxDao.bltayjtj(map);
	}

	@Override
	public List<Map<String, Object>> tatjb(Map<String, Object> map) {
		// TODO Auto-generated method stub
		if(map.get("type")!=null && "1".equals(map.get("type").toString())) {
			return taxxDao.grtatjb(map);
		}else if(map.get("type")!=null && "2".equals(map.get("type").toString())) {
			return taxxDao.tatjb(map);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> tamltj(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxDao.tamltj(map);
	}

	@Override
	public List<Map<String, Object>> tacbdwhz(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxDao.tacbdwhz(map);
	}

	@Override
	public List<Map<String, Object>> zxtaqk(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxDao.zxtaqk(map);
	}

	@Override
	public List<Map<String, Object>> taflml(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxDao.taflml(map);
	}

	@Override
	public List<Map<String, Object>> taflzhtj(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxDao.taflzhtj(map);
	}

	@Override
	public List<Map<String, Object>> zyjmltj(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxDao.zyjmltj(map);
	}

	@Override
	public int updateCxbxtah(Integer[] ids) {
		// TODO Auto-generated method stub
		if(ids==null || ids.length==0) {
			return 0;
		}
		
		taxxDao.updateTahClear(ids);
		
		List<TaxxDO> list = taxxDao.getListByIds(ids);
		Map<Integer, TaxxDO> m = new HashMap<>();
		for(TaxxDO t : list) {
			m.put(t.getId(), t);
		}
		for(Integer id : ids) {
			TaxxDO td = m.get(id);
			if(td.getIsba()!=null && td.getIsba().intValue()==1 && td.getBaid()!=null) {//被并案件不能编写提案号
				continue;
			}
			
			if(td.getLastate().intValue() == 1) {
				int tah = taxxDao.getMaxTahById(td.getId());
				TaxxDO taxx = new TaxxDO();
				taxx.setId(td.getId());
				taxx.setTah((tah+1)+"");
				taxxDao.updateTahAndSon(taxx);
			}else if(td.getLastate().intValue() == 2){
				int yjh = taxxDao.getMaxYjhById(td.getId());
				TaxxDO taxx = new TaxxDO();
				taxx.setId(td.getId());
				taxx.setTah((yjh+1)+"");
				taxxDao.updateTahAndSon(taxx);
			}
			/*int tah = taxxDao.getMaxTahById(td.getId());
			TaxxDO taxx = new TaxxDO();
			taxx.setId(td.getId());
			taxx.setTah((tah+1)+"");
			taxxDao.updateTahAndSon(taxx);*/
		}
		
		return ids.length;
	}

	@Override
	public Map<String, Object> cbdwInfoDate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxDao.cbdwInfoDate(map);
	}

	@Override
	public Map<String, Object> glyInfoDate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taxxDao.glyInfoDate(map);
	}

	@Override
	public List<Map<String, Object>> exportWordArrData(Integer[] ids) {
		// TODO Auto-generated method stub
		return taxxDao.exportWordArrData(ids);
	}

	@Override
	public List<Map<String, Object>> exportWordArrFhData(Integer[] ids) {
		// TODO Auto-generated method stub
		return taxxDao.exportWordArrFhData(ids);
	}

	@Override
	public int batchRecovery(Integer[] ids) {
		// TODO Auto-generated method stub
		return taxxDao.batchRecovery(ids);
	}

	@Override
	public Map<String, Object> exportWordData(Integer id) {
		// TODO Auto-generated method stub
		return taxxDao.exportWordData(id);
	}

	@Override
	public int zcbl(Integer[] ids) {
		// TODO Auto-generated method stub
		int num = 0;
		if(ids!=null && ids.length>0) {
			for(Integer id : ids) {
				Map<String,Object> map = new HashMap<>();
				map.put("taxxid", id);
				//删除复函回执
				List<FhhzDO> fhhzList = fhhzDao.list(map);
				if(fhhzList!=null && fhhzList.size()>0) {//删除以前的
					Integer[] removeIds = new Integer[fhhzList.size()];
					for(int i=0;i<fhhzList.size();i++) {
						removeIds[i] = fhhzList.get(i).getId();
					}
					fhhzDao.batchRemove(removeIds);
				}
				
				//删除评论
				List<PfbTaxxDO> list = pfbTaxxDao.list(map);
				if(list!=null && list.size()>0) {//删除以前的
					Integer[] removeIds = new Integer[list.size()];
					for(int i=0;i<list.size();i++) {
						removeIds[i] = list.get(i).getId();
					}
					pfbTaxxDao.batchRemove(removeIds);
				}
				
				TaxxDO t = new TaxxDO();
				t.setId(id);
				t.setTastate(4);//改为已转交
				updateTahAndSon(t);
				
				//保存进再次办理记录表
				int count = zcblDao.count(map);
				ZcblDO z = new ZcblDO();
				z.setCreateid(ShiroUtils.getUserIdInteger());
				z.setCreatetime(new Date());
				z.setUpdateid(ShiroUtils.getUserIdInteger());
				z.setUpdatetime(new Date());
				z.setState(1);
				z.setTaxxid(id);
				z.setCs(count+2);
				zcblDao.save(z);
				
				//发送短信6
				List<TaxxDO> tList = taxxDao.getListByIds(new Integer[] {id});
				if(tList!=null && tList.size()>0) {
					TaxxDO td = tList.get(0);
					GrwyDO grwy = grwyDao.getByUserId(td.getCreateid());
					List<String> tels = new ArrayList<>();
					tels.add(grwy.getSjhm());
					Map<String,String> par = new HashMap<>();
					par.put("tatm", td.getTatm());
					par.put("zxjcmc", td.getZxjcmc());
					par.put("tah", td.getTah());
					par.put("cbdw", td.getCbdw()+(td.getXbdw()==null||"".equals(td.getXbdw())?"":","+td.getXbdw()));
					fsdxService.fs(6, par, tels);
				}
				
				num++;
			}
		}
		
		return num;
	}

	@Override
	public int updateIsNull(TaxxDO taxx) {
		// TODO Auto-generated method stub
		return taxxDao.updateIsNull(taxx);
	}

	@Override
	public int realRemove(Integer[] ids) {
		// TODO Auto-generated method stub
		return taxxDao.realRemove(ids);
	}

	@Override
	public int updateSdbhtah(Integer id, String tah) {
		// TODO Auto-generated method stub
		TaxxDO taxx = new TaxxDO();
		taxx.setId(id);
		taxx.setTah(tah);
		return taxxDao.updateTahAndSon(taxx);
	}

	@Override
	public int updateQxba(Integer id) {
		// TODO Auto-generated method stub
		return taxxDao.updateQxba(id);
	}

	public int updateCbdwjd(Map<String,Object> map){
		return taxxDao.updateCbdwjd(map);
	}

	public Integer findCbdwjd(Map<String,Object> map){
		return taxxDao.findCbdwjd(map);
	};

	/**
	 * 根据登录人查询承办单位id
	 * @param name
	 * @return
	 */
	public Integer findUserId(String name){
		return taxxDao.findUserId(name);
	};

	public String findCountByName(Integer taxxid){
		return taxxDao.findCountByName(taxxid);
	};
}
