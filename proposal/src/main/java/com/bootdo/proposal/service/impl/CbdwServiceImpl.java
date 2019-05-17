package com.bootdo.proposal.service.impl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.dao.CbdwDao;
import com.bootdo.proposal.dao.GrwyDao;
import com.bootdo.proposal.domain.CbdwDO;
import com.bootdo.proposal.domain.GrwyDO;
import com.bootdo.proposal.service.CbdwService;
import com.bootdo.proposal.util.ExcelUtils;
import com.bootdo.proposal.util.StringUtil;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;



@Service
public class CbdwServiceImpl implements CbdwService {
	@Autowired
	private CbdwDao cbdwDao;
	@Autowired
	private GrwyDao grwyDao;
	@Autowired
	private UserService userService;
	
	@Override
	public CbdwDO get(Integer id){
		return cbdwDao.get(id);
	}
	
	@Override
	public List<CbdwDO> list(Map<String, Object> map){
		return cbdwDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return cbdwDao.count(map);
	}
	
	@Override
	public int save(CbdwDO cbdw, GrwyDO grwy){
		cbdw.setCreateid(ShiroUtils.getUserIdInteger());
		cbdw.setCreatetime(new Date());
		cbdw.setUpdateid(ShiroUtils.getUserIdInteger());
		cbdw.setUpdatetime(new Date());
		cbdw.setState(1);
		cbdw.setDelFlag(1);
		if(cbdw.getParentId() == null) {
			cbdw.setParentId(0);
		}
		cbdwDao.save(cbdw);
		
		List<Long> ll = new ArrayList<Long>();
		ll.add(8l);
		UserDO user = new UserDO();
		user.setName(cbdw.getName());
		user.setUsername(grwy.getUserName());
		user.setPassword(MD5Utils.encrypt(grwy.getUserName(), grwy.getPwd()));
		user.setEmail("1@1.com");
		user.setStatus(1);
		user.setRoleIds(ll);
		user.setDeptId(23l);
		userService.save(user);
		
		grwy.setDwmc(cbdw.getName());
		grwy.setUserid(user.getUserId().intValue());
		grwy.setCreateid(ShiroUtils.getUserIdInteger());
		grwy.setCreatetime(new Date());
		grwy.setUpdateid(ShiroUtils.getUserIdInteger());
		grwy.setUpdatetime(new Date());
		grwy.setState(1);
		grwy.setDwmcid(cbdw.getId());
		grwyDao.save(grwy);
		
		return 1;
	}
	
	@Override
	public int update(CbdwDO cbdw, GrwyDO grwy){
		UserDO u = userService.get(grwy.getUserid().longValue());
		if(!u.getUsername().equals(grwy.getUserName()) || !u.getPassword().equals(grwy.getPwd())) {
			u.setUsername(grwy.getUserName());
			u.setPassword(MD5Utils.encrypt(grwy.getUserName(), grwy.getPwd()));
			userService.updatePersonal(u);
		}
		grwy.setDwmc(cbdw.getName());;
		grwyDao.update(grwy);
		GrwyDO g = grwyDao.get(grwy.getId());
		cbdw.setId(g.getDwmcid());
		return cbdwDao.update(cbdw);
	}
	
	@Override
	public int remove(Integer id){
		return cbdwDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		for(Integer grwyId : ids) {
			GrwyDO g = grwyDao.get(grwyId);
			cbdwDao.remove(g.getDwmcid());
			userService.remove(g.getUserid().longValue());
			grwyDao.remove(g.getId());
		}
		return ids.length;
	}

	@Override
	public List<GrwyDO> listCbdw(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cbdwDao.listCbdw(map);
	}

	@Override
	public int listCbdwCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cbdwDao.listCbdwCount(map);
	}

	@Override
	public List<Map<String, Object>> selectAllSelectByTaxxId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cbdwDao.selectAllSelectByTaxxId(map);
	}

	@Override
	public Map<String, Object> saveExcel(MultipartFile upFile) throws IOException {
		// TODO Auto-generated method stub
		InputStream in = upFile.getInputStream();
		String filename = upFile.getOriginalFilename();
		Workbook wbook = ExcelUtils.openWorkbook(in, filename);
		Sheet sheet = wbook.getSheetAt(0);
		Row row = null;
		
		int sucnum = 0;//成功数
		int startrow = 1;//开始行数
		
		List<Integer> errorIndex = new LinkedList<Integer>(); //错误的行数
		
		Map<String ,String> existenceUser = new HashMap<String, String>();
		for(int i=startrow ; i<sheet.getLastRowNum()+1 ; i++){
			row = sheet.getRow(i);
			if(row!=null && !StringUtil.RowIndexsIsNull(row, new int[] {0,1,2})){
				if(existenceUser.get(StringUtil.getCellString(row.getCell(0))) == null){
					try {
						//承办单位
						//承办单位名称	账号	密码
						CbdwDO cbdw = new CbdwDO();
						cbdw.setName(StringUtil.getCellString(row.getCell(0)));
						cbdw.setParentId(0);
						cbdw.setOrderNum(1);
						cbdw.setDelFlag(0);
						GrwyDO grwy = new GrwyDO();
						grwy.setType(GrwyDO.CBDW);
						grwy.setDwmc(cbdw.getName());
						grwy.setUserName(StringUtil.getCellString(row.getCell(1)));
						grwy.setPwd(StringUtil.getCellString(row.getCell(2)));
						save(cbdw, grwy);
						
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("解析导入运营商生效时间出错");
						errorIndex.add(i+1);
					}
				}else{//重复
					errorIndex.add(i+1);
				}
			}else{//参数错误
				errorIndex.add(i+1);
			}
		}
		
		Map<String,Object> mapInfo = new HashMap<String ,Object>();
		mapInfo.put("sucnum", sucnum);
		mapInfo.put("errorIndex", errorIndex);
		
		return mapInfo;
	}

	@Override
	public List<CbdwDO> getTreeData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cbdwDao.getTreeData(map);
	}
	
}
