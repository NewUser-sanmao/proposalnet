package com.bootdo.proposal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.proposal.domain.GbsqDO;
import com.bootdo.proposal.domain.GrwyDO;
import com.bootdo.proposal.domain.LbyblsxDO;
import com.bootdo.proposal.domain.SswyhDO;
import com.bootdo.proposal.domain.TajbDO;
import com.bootdo.proposal.domain.TaxxDO;
import com.bootdo.proposal.domain.ZxjcDO;
import com.bootdo.proposal.service.GbsqCbdwService;
import com.bootdo.proposal.service.GbsqService;
import com.bootdo.proposal.service.GrwyService;
import com.bootdo.proposal.service.LbyblsxService;
import com.bootdo.proposal.service.SswyhService;
import com.bootdo.proposal.service.TaxxCbdwService;
import com.bootdo.proposal.service.TaxxService;
import com.bootdo.proposal.service.ZxjcService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;

/**
 * 提案信息
 * 
 * @author shipan
 * @email 
 * @date 2018-10-17 14:16:06
 */
 
@Controller
@RequestMapping("/proposal/tagb")
public class TagbController {
	@Autowired
	private TaxxService taxxService;
	@Autowired
	private GbsqService gbsqService;
	@Autowired
	private TaxxCbdwService taxxCbdwService;
	@Autowired
	private ZxjcService zxjcService;
	
	
	@GetMapping("")
	@RequiresPermissions("proposal:tagb:tagb")
	String Taxx(Model model){
	    return "proposal/tagb/tagb";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:tagb:tagb")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		
        Query query = new Query(params);
		List<Map<String,Object>> tagbList = gbsqService.tagbList(query);
		int total = gbsqService.count(query);
		PageUtils pageUtils = new PageUtils(tagbList, total);
		return pageUtils;
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:tagb:tagb")
	public R update( GbsqDO gbsqDO){
		GbsqDO g = gbsqService.get(gbsqDO.getId());
		if(g.getType().intValue() != 0) {
			return R.error("该申请已处理");
		}
		g.setType(gbsqDO.getType());
		gbsqService.update(g);
		return R.ok();
	}
	
	/**
	 * 审核
	 * @param type
	 * @param model
	 * @return
	 */
	@GetMapping("/xgyta/{taxxid}/{gbsqid}")
	String xgyta(@PathVariable("taxxid") Integer taxxid,@PathVariable("gbsqid") Integer gbsqid, Model model){
		TaxxDO wy = taxxService.get(taxxid);
		model.addAttribute("wy", wy);
		
		model.addAttribute("gbsqid", gbsqid);
		model.addAttribute("tastate", wy.getTastate());
		
		Map<String,Object> map = new HashMap<>();
		map.put("taxxId", wy.getId());
		Map<String, Object> m = taxxCbdwService.getCbdw(map);
		
		if(m!=null && !"0".equals(m.get("cbdw"))) {
			model.addAttribute("cbdw", m.get("cbdw"));
		}
		if(m!=null && !"0".equals(m.get("fbdw"))) {
			model.addAttribute("fbdw", m.get("fbdw"));
		}
		if(m!=null && !"0".equals(m.get("xbdw"))) {
			model.addAttribute("xbdw", m.get("xbdw"));
		}
		
		ZxjcDO zxjc = zxjcService.get(wy.getZxjcid());
		model.addAttribute("zxjc", zxjc);
		
	    return "proposal/tagb/xgyta";
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/updateTasc")
	public R updateTasc( TaxxDO taxx,String[] cbdw,String[] fbdw,String[] xbdw, Integer gbsqid){
		GbsqDO g = gbsqService.get(gbsqid);
		if(g.getType().intValue() == 0) {//等于0才能修改
			GbsqDO gbsqDO = new GbsqDO();
			gbsqDO.setId(gbsqid);
			gbsqDO.setType(3);
			gbsqService.update(gbsqDO);
		}
		
		taxxService.updateTasc(taxx,cbdw,fbdw,xbdw);
		return R.ok();
	}
	
	/**
	 * 审核
	 * @param type
	 * @param model
	 * @return
	 */
	@GetMapping("/fh/{taxxid}/{type}")
	String fh(@PathVariable("taxxid") Integer taxxid,@PathVariable("type") Integer type, Model model){
		model.addAttribute("taxxid", taxxid);
		model.addAttribute("type", type);//1意见稿 2正式复函
		
	    return "proposal/tagb/xgyta";
	}
	
}
