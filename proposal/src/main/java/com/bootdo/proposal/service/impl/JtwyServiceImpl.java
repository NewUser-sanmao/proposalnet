package com.bootdo.proposal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.dao.JtwyDao;
import com.bootdo.proposal.domain.JtwyDO;
import com.bootdo.proposal.service.JtwyService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;



@Service
public class JtwyServiceImpl implements JtwyService {
	@Autowired
	private JtwyDao jtwyDao;
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public JtwyDO get(Integer id){
		return jtwyDao.get(id);
	}
	
	@Override
	public List<JtwyDO> list(Map<String, Object> map){
		return jtwyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return jtwyDao.count(map);
	}
	
	@Override
	public int save(JtwyDO jtwy,String userName,String pwd){
		List<Long> ll = new ArrayList<Long>();
		ll.add(5l);
		UserDO user = new UserDO();
		user.setName(jtwy.getDwmc());
		user.setUsername(userName);
		user.setPassword(MD5Utils.encrypt(userName, pwd));
		user.setEmail("1@1.com");
		user.setStatus(1);
		user.setRoleIds(ll);
		user.setDeptId(19l);
		userService.save(user);
		
		jtwy.setUserid(user.getUserId().intValue());
		jtwy.setCreateid(ShiroUtils.getUserIdInteger());
		jtwy.setCreatetime(new Date());
		jtwy.setUpdateid(ShiroUtils.getUserIdInteger());
		jtwy.setUpdatetime(new Date());
		jtwy.setState(1);
		return jtwyDao.save(jtwy);
	}
	
	@Override
	public int update(JtwyDO jtwy){
		UserDO u = userService.get(jtwy.getUserid().longValue());
		if(!u.getUsername().equals(jtwy.getUserName()) || !u.getPassword().equals(jtwy.getPwd())) {
			u.setUsername(jtwy.getUserName());
			u.setPassword(MD5Utils.encrypt(jtwy.getUserName(), jtwy.getPwd()));
			userService.updatePersonal(u);
		}
		
		return jtwyDao.update(jtwy);
	}
	
	@Override
	public int remove(Integer id){
		JtwyDO jd = jtwyDao.get(id);
		UserDO userDO = new UserDO();
		userDO.setUserId(jd.getUserid().longValue());
		userDO.setStatus(0);
		userService.updatePersonal(userDO);
		
		JtwyDO jtwyDO = new JtwyDO();
		jtwyDO.setId(id);
		jtwyDO.setState(0);
		return jtwyDao.update(jtwyDO);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		for(Integer id : ids) {
			remove(id);
		}
		return ids.length;
	}
	
}
