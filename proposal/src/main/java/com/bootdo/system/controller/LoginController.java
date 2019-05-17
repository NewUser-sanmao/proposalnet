package com.bootdo.system.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.proposal.domain.GrwyDO;
import com.bootdo.proposal.domain.TaxxDO;
import com.bootdo.proposal.domain.UpdateStateDO;
import com.bootdo.proposal.domain.UtilDO;
import com.bootdo.proposal.domain.ZxjcDO;
import com.bootdo.proposal.service.GrwyService;
import com.bootdo.proposal.service.TaxxService;
import com.bootdo.proposal.service.UpdateStateService;
import com.bootdo.proposal.service.UtilService;
import com.bootdo.proposal.service.ZxjcService;
import com.bootdo.proposal.util.StringUtil;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.MenuService;
import com.bootdo.system.service.UserService;

@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;
	@Autowired
	FileService fileService;
	@Autowired
	UtilService utilService;
	@Autowired
	UserService userService;
	@Autowired
	ZxjcService zxjcService;
	@Autowired
	TaxxService taxxService;
	@Autowired
	GrwyService grwyService;
	@Autowired
	UpdateStateService updateStateService;
	
	
	@GetMapping({ "/", "" })
	String welcome(Model model) {

		return "redirect:/login";
	}

	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model) {
		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());
		FileDO fileDO = fileService.get(getUser().getPicId());
		if(fileDO!=null&&fileDO.getUrl()!=null){
			if(fileService.isExist(fileDO.getUrl())){
				model.addAttribute("picUrl",fileDO.getUrl());
			}else {
				model.addAttribute("picUrl","/img/photo_s.png");
			}
		}else {
			model.addAttribute("picUrl","/img/photo_s.png");
		}
		model.addAttribute("username", getUser().getUsername());
		return "index_v1";
	}

	@GetMapping("/login")
	String login() {
		return "login";
	}

	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password) {

		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}

	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}
	public static void main(String[] args) {
		System.out.println(MD5Utils.encrypt("薛山", "123456"));
	}

	@GetMapping("/main")
	String main(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", 1);
		List<UtilDO> u = utilService.list(map);
		if(u.size() > 0) {
			model.addAttribute("text", u.get(0).getText());
		}
		return "main";
	}
	
	@GetMapping("/info")
	String info(Model model) {
		UserDO u = userService.get(getUserId());
		ZxjcDO zxjc = zxjcService.getNewest();
		Map<String,Object> map = new HashMap<>();
		map.put("zxjcid", zxjc.getId());
		map.put("state", 1);
		int allNumber = taxxService.count(map);
		if(u.getRoleIds().indexOf(8l) != -1){//承办单位
			model.addAttribute("type", "cbdw");
			//显示本次会议,总多少件,需要办理多少件,已办多少件
			map.clear();
			map.put("userId", u.getUserId());
			map.put("zxjcId", zxjc.getId());
			Map<String, Object> m = taxxService.cbdwInfoDate(map);
			model.addAttribute("allNumber", allNumber);
			if(m == null) {
				m = new HashMap<>();
				m.put("z", 0);
				m.put("ybl", 0);
			}
			model.addAttribute("data", m);
			
			GrwyDO gd = grwyService.getByUserId(u.getUserId().intValue());
			map.clear();
			map.put("cbdwId2", gd.getDwmcid());
			map.put("dyTastate", 3);
			List<TaxxDO> listTaxx = taxxService.tablList(map);
			if(listTaxx!=null && listTaxx.size()>0) {
				map.clear();
				map.put("taxxid", listTaxx.get(0).getId());
				map.put("tastate", 4);
				List<UpdateStateDO> listUs = updateStateService.list(map);
				if(listUs!=null && listUs.size()>0) {
					int s = StringUtil.differentDaysByMillisecond(new Date(), StringUtil.plusMonths(listUs.get(0).getCreatetime(), 3));
					if(s >= 0) {
						model.addAttribute("str1", "距复函交办时间还有<span style=\"color: #f00;\">"+s+"</span>天");
					}else {
						model.addAttribute("str1", "复函交办时间已过。");
					}
				}
			}
			
			
		}else if(u.getRoleIds().indexOf(5l) != -1) {//委员
			model.addAttribute("type", "wy");
			Map<String, Object> params = new HashMap<>();
			params.put("type", 2);
			List<UtilDO> utilList = utilService.list(params);
			UtilDO util = utilList.get(0);
			Date d = StringUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss", util.getText());
			
			int s = StringUtil.differentDaysByMillisecond(new Date(), d);
			if(s >= 0) {
				model.addAttribute("str1", "距提交提案时间还有<span style=\"color: #f00;\">"+s+"</span>天");
			}else {
				model.addAttribute("str1", "提交提案的时间已过。");
			}
		}else {//提案委或管理员或超级管理员
			map.clear();
			map.put("zxjcId", zxjc.getId());
			Map<String, Object> m = taxxService.glyInfoDate(map);
			model.addAttribute("data", m);
			model.addAttribute("type", "gly");
		}

		Map<String, Object> mapNotice = new HashMap<>();
		mapNotice.put("id", 1);
		List<UtilDO> utilDOList = utilService.list(mapNotice);
		if(utilDOList.size() > 0) {
			model.addAttribute("noticeText", utilDOList.get(0).getText());
		}

		return "info";
	}
}
