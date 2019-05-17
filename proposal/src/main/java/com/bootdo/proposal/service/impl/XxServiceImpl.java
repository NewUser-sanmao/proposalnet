package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.dao.GrwyDao;
import com.bootdo.proposal.dao.TaxxDao;
import com.bootdo.proposal.dao.XxDao;
import com.bootdo.proposal.domain.GrwyDO;
import com.bootdo.proposal.domain.TaxxDO;
import com.bootdo.proposal.domain.XxDO;
import com.bootdo.proposal.service.XxService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.SessionService;



@Service
public class XxServiceImpl implements XxService {
	@Autowired
	private XxDao xxDao;
	@Autowired
	private GrwyDao grwyDao;
	@Autowired
	private TaxxDao taxxDao;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SimpMessagingTemplate template;
	
	@Override
	public XxDO get(Integer id){
		return xxDao.get(id);
	}
	
	@Override
	public List<XxDO> list(Map<String, Object> map){
		return xxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xxDao.count(map);
	}
	
	@Override
	public int save(XxDO xx, Integer[] mbdw, Integer lx, Integer type){
		int i = 0;
		if(lx.intValue()==1) {
			i = wySave(xx, mbdw);
		}else if(lx.intValue() == 2){
			i = cbdwSave(xx, mbdw, type);
		}
		return i;
	}
	
	private int wySave(XxDO xx, Integer[] mbdw) {

		Map<String, Object> map = new HashMap<>();
		map.put("dwmcs", mbdw);
		List<GrwyDO> dws = grwyDao.getListByDwmcs(map);
		int i = 0;
		for(GrwyDO dw : dws) {
			XxDO xd = new XxDO();
			xd.setCreateid(xx.getFsr()==null ? ShiroUtils.getUserIdInteger() : xx.getFsr());
			xd.setCreatetime(new Date());
			xd.setUpdateid(xx.getFsr()==null ? ShiroUtils.getUserIdInteger() : xx.getFsr());
			xd.setUpdatetime(new Date());
			xd.setState(0);
			xd.setType(1);
			xd.setFsr(xx.getFsr()==null ? ShiroUtils.getUserIdInteger() : xx.getFsr());
			xd.setJsr(dw.getUserid());
			xd.setTaxxid(xx.getTaxxid());
			xd.setNr(xx.getNr());
			xd.setFjmc(xx.getFjmc());
			xd.setFjdz(xx.getFjdz());
			xd.setNr(xx.getNr());
			i += xxDao.save(xd);
		}
		
		String nr = xx.getNr();
		nr = nr.length()>20 ? nr.substring(0, 20)+"..." : nr;
		xx.setNr(nr);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(1,1,0, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
        executor.execute(new Runnable() {
            @Override
            public void run() {
            	for (GrwyDO dw : dws) {
            		for (UserDO userDO : sessionService.listOnlineUser()) {
                        if (userDO.getUserId().intValue() == dw.getUserid().intValue()) {
                            template.convertAndSendToUser(userDO.toString(), "/queue/notifications", ShiroUtils.getUser().getName()+"：" + xx.getNr());
                            break;
                        }
                    }
                }
            }
        });
        executor.shutdown();
        return i;
	}
	
	private int cbdwSave(XxDO xx, Integer[] mbdw, Integer type) {
		if(type.intValue() == 1) {
			TaxxDO taxx = taxxDao.get(xx.getTaxxid());
			xx.setCreateid(xx.getFsr()==null ? ShiroUtils.getUserIdInteger() : xx.getFsr());
			xx.setCreatetime(new Date());
			xx.setUpdateid(xx.getFsr()==null ? ShiroUtils.getUserIdInteger() : xx.getFsr());
			xx.setUpdatetime(new Date());
			xx.setState(0);
			xx.setType(1);
			xx.setFsr(xx.getFsr()==null ? ShiroUtils.getUserIdInteger() : xx.getFsr());
			xx.setJsr(taxx.getCreateid());
			xx.setTaxxid(xx.getTaxxid());
			xx.setNr(xx.getNr());
			
			xxDao.save(xx);
			
			String nr = xx.getNr();
			nr = nr.length()>20 ? nr.substring(0, 20)+"..." : nr;
			xx.setNr(nr);
			ThreadPoolExecutor executor = new ThreadPoolExecutor(1,1,0, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
	        executor.execute(new Runnable() {
	            @Override
	            public void run() {
	        		for (UserDO userDO : sessionService.listOnlineUser()) {
	                    if (userDO.getUserId().intValue() == taxx.getCreateid().intValue()) {
	                        template.convertAndSendToUser(userDO.toString(), "/queue/notifications", ShiroUtils.getUser().getName()+"：" + xx.getNr());
	                        break;
	                    }
	                }
	            }
	        });
	        executor.shutdown();
	        return 1;
		}else {
			return wySave(xx, mbdw);
		}
	}
	
	@Override
	public int update(XxDO xx){
		return xxDao.update(xx);
	}
	
	@Override
	public int remove(Integer id){
		return xxDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return xxDao.batchRemove(ids);
	}

	@Override
	public List<Map<String, Object>> listMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return xxDao.listMap(map);
	}

	@Override
	public int countMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return xxDao.countMap(map);
	}

	@Override
	public int saveAdminAdd(XxDO xx, Integer[] mbdw, Integer type) {
		// TODO Auto-generated method stub
		int i = 0;
		if(type.intValue()==0) {
			i += wySave(xx, mbdw);
			i += cbdwSave(xx, mbdw, 1);
		}else if(type.intValue()==1){
			i = cbdwSave(xx, mbdw, 1);
		}else if(type.intValue()==2){
			i = wySave(xx, mbdw);
		}
		
		return i;
	}

	public int findCountByName(Map<String,Object> map){
		return xxDao.findCountByName(map);
	};
}
