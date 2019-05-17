package com.bootdo.proposal.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.dao.GrwyDao;
import com.bootdo.proposal.domain.GrwyDO;
import com.bootdo.proposal.domain.HistoryLabelDO;
import com.bootdo.proposal.service.GrwyService;
import com.bootdo.proposal.service.HistoryLabelService;
import com.bootdo.proposal.util.ExcelUtils;
import com.bootdo.proposal.util.StringUtil;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;



@Service
public class GrwyServiceImpl implements GrwyService {
	@Autowired
	private GrwyDao grwyDao;
	@Autowired
	private UserService userService;
	@Autowired
	private HistoryLabelService historyLabelService;
	
	@Override
	public GrwyDO get(Integer id){
		return grwyDao.get(id);
	}
	
	@Override
	public List<GrwyDO> list(Map<String, Object> map){
		return grwyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return grwyDao.count(map);
	}
	
	@Override
	public int save(GrwyDO grwy){
		List<Long> ll = new ArrayList<Long>();
		ll.add(5l);
		UserDO user = new UserDO();
		user.setName(grwy.getXm());
		user.setUsername(grwy.getUserName());
		user.setPassword(MD5Utils.encrypt(grwy.getUserName(), grwy.getPwd()));
		user.setEmail("1@1.com");
		user.setStatus(1);
		user.setRoleIds(ll);
		user.setDeptId(19l);//委员
		userService.save(user);//保存进登录用户表
		
		grwy.setUserid(user.getUserId().intValue());
		grwy.setCreateid(ShiroUtils.getUserIdInteger());
		grwy.setCreatetime(new Date());
		grwy.setUpdateid(ShiroUtils.getUserIdInteger());
		grwy.setUpdatetime(new Date());
		grwy.setState(1);
		grwyDao.save(grwy);//委员表
		
		HistoryLabelDO historyLabel = new HistoryLabelDO();
		historyLabel.setGrwyid(grwy.getId());
		historyLabel.setZxjcid(grwy.getSsjcid());
		historyLabelService.save(historyLabel);//记录届次
		
		return 1;
	}
	
	@Override
	public int update(GrwyDO grwy){
		UserDO u = userService.get(grwy.getUserid().longValue());
		if(!u.getUsername().equals(grwy.getUserName()) || !u.getPassword().equals(grwy.getPwd())) {
			u.setUsername(grwy.getUserName());
			u.setPassword(MD5Utils.encrypt(grwy.getUserName(), grwy.getPwd()));
			userService.updatePersonal(u);
		}
		return grwyDao.update(grwy);
	}
	
	@Override
	public int remove(Integer id){
		GrwyDO gd = grwyDao.get(id);
		UserDO userDO = new UserDO();
		userDO.setUserId(gd.getUserid().longValue());
		userDO.setStatus(0);
		userService.updatePersonal(userDO);
		
		GrwyDO grwyDO = new GrwyDO();
		grwyDO.setId(id);
		grwyDO.setState(0);
		return grwyDao.update(grwyDO);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		for(Integer id : ids) {
			remove(id);
		}
		return ids.length;
	}
	
	@Override
	public GrwyDO getByUserId(Integer userId){
		return grwyDao.getByUserId(userId);
	}

	@Override
	public List<GrwyDO> getListByDwmcs(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return grwyDao.getListByDwmcs(map);
	}

	@Override
	public Map<String, Object> saveExcel(MultipartFile upFile, Integer type) throws IOException {
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
			if(row!=null && !StringUtil.RowIndexsIsNull(row, new int[] {0,3,4,5})){
				if(existenceUser.get(StringUtil.getCellString(row.getCell(0))) == null){
					try {
						//个人委员
						//姓名 身份证 手机号	登录账号	登录密码	所属界次	所属界别	所属党派	所属专委会	地区小组	性别	即时通讯	单位名称_职务	通讯地址	邮编	办公联系电话
						GrwyDO gr = new GrwyDO();
						gr.setType(type);//委员类型
						gr.setXm(StringUtil.getCellString(row.getCell(0)).replaceAll(" ", ""));
						gr.setUserName(StringUtil.getCellString(row.getCell(3)).replaceAll(" ", ""));
						if(StringUtil.getCellStringAndNull(row.getCell(4)) == null) {
							gr.setPwd("123456");
						}else {
							gr.setPwd(StringUtil.getCellString(row.getCell(4)));
						}
						
						gr.setSfz(StringUtil.getCellStringAndNull(row.getCell(1)));
						gr.setSjhm(StringUtil.getCellStringAndNull(row.getCell(2)));
						
						if(row.getCell(5) != null) {
							gr.setSsjcid(Integer.parseInt(StringUtil.getCellString(row.getCell(5))));
						}
						if(row.getCell(6) != null) {
							gr.setSsjbid(Integer.parseInt(StringUtil.getCellString(row.getCell(6))));
						}
						if(row.getCell(7) != null) {
							gr.setSsdpid(Integer.parseInt(StringUtil.getCellString(row.getCell(7))));
						}
						if(row.getCell(8) != null) {
							gr.setSswyhid(Integer.parseInt(StringUtil.getCellString(row.getCell(8))));
						}
						if(row.getCell(9) != null) {
							gr.setSsdqid(Integer.parseInt(StringUtil.getCellString(row.getCell(9))));
						}
						if(row.getCell(10) != null) {
							gr.setXb(Integer.parseInt(StringUtil.getCellString(row.getCell(10))));
						}
						gr.setJstx(StringUtil.getCellStringAndNull(row.getCell(11)));
						gr.setDwmcZw(StringUtil.getCellStringAndNull(row.getCell(12)));
						gr.setTxdz(StringUtil.getCellStringAndNull(row.getCell(13)));
						gr.setYb(StringUtil.getCellStringAndNull(row.getCell(14)));
						gr.setBglxdh(StringUtil.getCellStringAndNull(row.getCell(15)));
						
						save(gr);//保存个人信息
						
						existenceUser.put(gr.getUserName(), "1");
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
	public GrwyDO getByDwmcId(Integer dwmcId) {
		// TODO Auto-generated method stub
		return grwyDao.getByDwmcId(dwmcId);
	}

	@Override
	public int resetPwd(Integer id) {
		// TODO Auto-generated method stub
		GrwyDO g = grwyDao.get(id);
		UserDO u = userService.get(g.getUserid().longValue());
		String password = MD5Utils.encrypt(u.getUsername(), "123456");
		UserDO ud = new UserDO();
		ud.setUserId(g.getUserid().longValue());
		ud.setPassword(password);
		return userService.updatePersonal(ud);
	}
}
