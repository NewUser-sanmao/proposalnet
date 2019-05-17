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

import com.bootdo.proposal.domain.FhhzDO;
import com.bootdo.proposal.domain.GrwyDO;
import com.bootdo.proposal.domain.LbyblsxDO;
import com.bootdo.proposal.domain.PfbDO;
import com.bootdo.proposal.domain.PfbTaxxDO;
import com.bootdo.proposal.domain.SswyhDO;
import com.bootdo.proposal.domain.TaxxCbdwDO;
import com.bootdo.proposal.domain.TaxxDO;
import com.bootdo.proposal.domain.ZxjcDO;
import com.bootdo.proposal.service.FhhzService;
import com.bootdo.proposal.service.GbsqService;
import com.bootdo.proposal.service.GrwyService;
import com.bootdo.proposal.service.LbyblsxService;
import com.bootdo.proposal.service.PfbService;
import com.bootdo.proposal.service.PfbTaxxService;
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
@RequestMapping("/proposal/tasc")
public class TascController {
	@Autowired
	private TaxxService taxxService;
	@Autowired
	private ZxjcService zxjcService;
	@Autowired
	private TaxxCbdwService taxxCbdwService;
	@Autowired
	private FhhzService fhhzService;
	@Autowired
	private PfbService pfbService;
	@Autowired
	private PfbTaxxService pfbTaxxService;
	@Autowired
	private GbsqService gbsqService;
	
	@GetMapping("/{type}")
	@RequiresPermissions("proposal:tasc:tays")
	String Taxx(@PathVariable("type") Integer type,Model model){
		model.addAttribute("type", type);
	    return "proposal/tasc/tays";
	}
	
	@GetMapping("/grzhgl")
	@RequiresPermissions("proposal:tasc:grzhgl")
	String grzhgl(Model model){
	    return "proposal/tasc/grzhgl";
	}
	
	@GetMapping("/jtzhgl")
	@RequiresPermissions("proposal:tasc:jtzhgl")
	String jtzhgl(Model model){
	    return "proposal/tasc/jtzhgl";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:tasc:tays")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		//params.put("xyLaState", 2);//小于
		
        Query query = new Query(params);
		List<TaxxDO> taxxList = taxxService.list(query);
		int total = taxxService.count(query);
		PageUtils pageUtils = new PageUtils(taxxList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/zhglList")
	public PageUtils zhglList(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<Map<String,Object>> taxxList = taxxService.listMap(query);
		int total = taxxService.listMapCount(query);
		PageUtils pageUtils = new PageUtils(taxxList, total);
		return pageUtils;
	}
	
	/**
	 * 审核
	 * @param type
	 * @param model
	 * @return
	 */
	@GetMapping("/sh/{id}/{tastate}")
	String sh(@PathVariable("id") Integer id, @PathVariable("tastate") Integer tastate, Model model){
		TaxxDO wy = taxxService.get(id);
		model.addAttribute("wy", wy);
		
		/*if(wy.getSswyhid()!=null) {
			SswyhDO sd = sswyhService.get(wy.getSswyhid());
			model.addAttribute("sswyhName", sd.getSswyhmc());
		}
		
		if(wy.getZxjcid()!=null) {
			ZxjcDO zd = zxjcService.get(wy.getZxjcid());
			model.addAttribute("zxjcName", zd.getZxjcmc());
		}
		
		if(wy.getLbyblsxid()!=null) {
			LbyblsxDO ld = lbyblsxService.get(wy.getLbyblsxid());
			model.addAttribute("lbyblsxName", ld.getTalx());
		}
		
		if(wy.getFlid()!=null) {
			LbyblsxDO ld = lbyblsxService.get(wy.getFlid());
			model.addAttribute("flidName", ld.getTalx());
		}*/
		
		model.addAttribute("tastate", tastate);
		
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
		
		//改办申请
		
		map.clear();
		map.put("taxxid", wy.getId());
		List<Map<String, Object>> gbsq = gbsqService.tagbList(map);
		if(gbsq != null && gbsq.size()>0) {
			Map<String, Object> gb = gbsq.get(0);
//			Integer latype = gb.get("latype")!=null ? Integer.parseInt(gb.get("latype").toString()) : 0;
//			model.addAttribute("latype", latype==1 ? "立案" : latype==2 ? "不立案转意见" : latype==3 ? "不立案退回" : "");
			model.addAttribute("ly", gb.get("ly")!=null ? gb.get("ly").toString() : "ly");
			String jydw = "";
			if(gb.get("cbdw2")!=null) {
				jydw += gb.get("cbdw2")+",";
			}
			if(gb.get("fbdw2")!=null) {
				jydw += gb.get("fbdw2")+",";
			}
			if(gb.get("xbdw2")!=null) {
				jydw += gb.get("xbdw2")+",";
			}
			
			if(!"".equals(jydw)) {
				jydw = jydw.substring(0, jydw.length()-1);
			}
			
			model.addAttribute("jydw", jydw);
		}
		
		
	    return "proposal/tasc/sh";
	}
	
	/**
	 * 修改
	 * @param type
	 * @param model
	 * @return
	 */
	@GetMapping("/xg/{id}")
	String sh(@PathVariable("id") Integer id, Model model){
		TaxxDO wy = taxxService.get(id);
		model.addAttribute("wy", wy);
		
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
		
		//改办申请
		
		map.clear();
		map.put("taxxid", wy.getId());
		List<Map<String, Object>> gbsq = gbsqService.tagbList(map);
		if(gbsq != null && gbsq.size()>0) {
			Map<String, Object> gb = gbsq.get(0);
			model.addAttribute("ly", gb.get("ly")!=null ? gb.get("ly").toString() : "ly");
			String jydw = "";
			if(gb.get("cbdw2")!=null) {
				jydw += gb.get("cbdw2")+",";
			}
			if(gb.get("fbdw2")!=null) {
				jydw += gb.get("fbdw2")+",";
			}
			if(gb.get("xbdw2")!=null) {
				jydw += gb.get("xbdw2")+",";
			}
			
			if(!"".equals(jydw)) {
				jydw = jydw.substring(0, jydw.length()-1);
			}
			
			model.addAttribute("jydw", jydw);
		}
		
	    return "proposal/tasc/xg";
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/updateTasc")
	public R updateTasc( TaxxDO taxx,String[] cbdw,String[] fbdw,String[] xbdw){
		taxxService.updateTasc(taxx,cbdw,fbdw,xbdw);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/updateTascNoTastate")
	public R updateTascNoTastate( TaxxDO taxx,String[] cbdw,String[] fbdw,String[] xbdw){
		if(taxx.getLastate().intValue()==1) {
			taxx.setTaly(null);
		}
		taxxService.update(taxx);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/updateTasc2")
	public R updateTasc2(TaxxDO taxx,String[] cbdw,String[] fbdw,String[] xbdw){
		if(taxx.getTastate() < 3) {
			taxx.setTastate(taxx.getTastate()-1);
		}
		taxxService.updateTasc(taxx,cbdw,fbdw,xbdw);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:tasc:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		taxxService.batchRemove(ids);
		return R.ok();
	}
	
	/**
	 * 转交提案
	 */
	@PostMapping( "/zjta")
	@ResponseBody
	@RequiresPermissions("proposal:tasc:zj")
	public R zjta(@RequestParam("ids[]") Integer[] ids){
		List<TaxxDO> ds = taxxService.getListByIds(ids);
		if(ds!=null && ds.size()>0) {
			for(TaxxDO t : ds) {
				if(t.getTah()==null) {
					return R.error(t.getTatm()+",该提案没有提案号,不能转交");
				}
			}
		}
		taxxService.updateTastate(ids, 4);
		return R.ok();
	}
	
	/**
	 * 修改为重点提案
	 */
	@ResponseBody
	@RequestMapping("/zdta")
	public R zdta(@RequestParam("id") Integer id){
		TaxxDO t = taxxService.get(id);
		if(t!=null) {
			if(t.getIszdta()==null || t.getIszdta().intValue()==0) {
				TaxxDO ta = new TaxxDO();
				ta.setId(id);
				ta.setIszdta(1);
				taxxService.update(ta);	
			}
		}else {
			return R.error("数据错误");
		}
		return R.ok();
	}
	
	
	/**
	 * 退回提案状态
	 */
	@ResponseBody
	@RequestMapping("/thta")
	public R thta(@RequestParam("id") Integer id, @RequestParam("type") Integer type){
		TaxxDO t = taxxService.get(id);
		if(t!=null) {
			if(t.getTastate().intValue()>0 && t.getTastate().intValue()<5) {
				if(type == 1) {//退回到上个版本
					TaxxDO ta = new TaxxDO();
					ta.setId(id);
					ta.setTastate(t.getTastate()-1);
					taxxService.update(ta);	
				}else {//退回到预审
					if(t.getTastate().intValue()>0 && t.getTastate().intValue()<4) {
						Map<String,Object> map = new HashMap<>();
						map.put("taxxid", id);
						
						//删除复函回执
						List<FhhzDO> fhhzList = fhhzService.list(map);
						if(fhhzList!=null && fhhzList.size()>0) {//删除以前的
							Integer[] removeIds = new Integer[fhhzList.size()];
							for(int i=0;i<fhhzList.size();i++) {
								removeIds[i] = fhhzList.get(i).getId();
							}
							fhhzService.batchRemove(removeIds);
						}
						
						//删除评论
						List<PfbTaxxDO> list = pfbTaxxService.list(map);
						if(list!=null && list.size()>0) {//删除以前的
							Integer[] removeIds = new Integer[list.size()];
							for(int i=0;i<list.size();i++) {
								removeIds[i] = list.get(i).getId();
							}
							pfbService.batchRemove(removeIds);
						}
						
						//删除办理单位
						List<TaxxCbdwDO> cbdwList = taxxCbdwService.list(map);
						if(cbdwList!=null && cbdwList.size()>0) {//删除以前的
							Integer[] removeIds = new Integer[cbdwList.size()];
							for(int i=0;i<cbdwList.size();i++) {
								removeIds[i] = cbdwList.get(i).getId();
							}
							taxxCbdwService.batchRemove(removeIds);
						}
						
						//修改
						TaxxDO ta = new TaxxDO();
						ta.setId(id);
						ta.setTastate(0);
						ta.setLastate(0);
						taxxService.updateIsNull(ta);
					}else {
						return R.error("已转交提案不能退回提案");
					}
				}
			}else {
				return R.error("该提案状态的提案不能退回");
			}
		}else {
			return R.error("数据错误");
		}
		return R.ok();
	}
	
	/**
	 * 查看
	 * @param model
	 * @return
	 */
	@GetMapping("/chakan/{id}")
	String chakan(@PathVariable("id") Integer id, Model model){
		TaxxDO taxx = taxxService.get(id);
		model.addAttribute("wy", taxx);
		
		Map<String,Object> map = new HashMap<>();
		map.put("taxxId", taxx.getId());
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
		
		ZxjcDO zxjc = zxjcService.get(taxx.getZxjcid());
		model.addAttribute("zxjc", zxjc);
		
		Map<String,Object> fhMap = new HashMap<>();
		fhMap.put("taxxid", taxx.getId());
		fhMap.put("type", 1);//意见函
		List<FhhzDO> fhList = fhhzService.list(fhMap);
		model.addAttribute("fhList", fhList);
		
		fhMap.put("type", 2);//正式回执函
		List<FhhzDO> zsList = fhhzService.list(fhMap);
		model.addAttribute("zsList", zsList);
		
		//1 办理单位评分选项 2委员
		map.clear();
		map.put("type", 1);
		List<PfbDO> pfbs1 = pfbService.list(map);
		model.addAttribute("pfbs1", pfbs1);
		
		map.clear();
		map.put("taxxid", taxx.getId());
		map.put("type", 1);
		List<PfbTaxxDO> pts1 = pfbTaxxService.list(map);
		Map<Integer,Object> ptMap1 = new HashMap<>();
		int total1 = 0;
		if(pts1!=null && pts1.size()>0) {
			for(PfbTaxxDO p : pts1) {
				ptMap1.put(p.getPfbid(), p.getFz());
				total1 += p.getFz();
			}
		}
		model.addAttribute("ptMap1", ptMap1);
		model.addAttribute("total1", total1);
		
		map.clear();
		map.put("type", 2);
		List<PfbDO> pfbs2 = pfbService.list(map);
		model.addAttribute("pfbs2", pfbs2);
		
		map.clear();
		map.put("taxxid", taxx.getId());
		map.put("type", 2);
		List<PfbTaxxDO> pts2 = pfbTaxxService.list(map);
		int total2 = 0;
		Map<Integer,Object> ptMap2 = new HashMap<>();
		if(pts2!=null && pts2.size()>0) {
			for(PfbTaxxDO p : pts2) {
				ptMap2.put(p.getPfbid(), p.getFz());
				total2 += p.getFz();
			}
		}
		model.addAttribute("ptMap2", ptMap2);
		model.addAttribute("total2", total2);
		
	    return "proposal/tasc/chakan";
	}
	
	/**
	 * 编写提案号
	 */
	@PostMapping( "/bxtah")
	@ResponseBody
	@RequiresPermissions("proposal:tasc:bh")
	public R bxtah(@RequestParam("ids[]") Integer[] ids){
		taxxService.updateBxtah(ids);
		return R.ok();
	}
	
	/**
	 * 重新编写提案号
	 */
	@PostMapping( "/cxbxtah")
	@ResponseBody
	@RequiresPermissions("proposal:tasc:cxbh")
	public R cxbxtah(@RequestParam("ids[]") Integer[] ids){
		taxxService.updateCxbxtah(ids);
		return R.ok();
	}
	
	@PostMapping( "/sdbhtah")
	@ResponseBody
	@RequiresPermissions("proposal:tasc:sdbh")
	public R sdbhtah(@RequestParam("id") Integer id,@RequestParam("tah") String tah){
		TaxxDO td = taxxService.get(id);
		if(td!=null) {
			Map<String,Object> map = new HashMap<>();
			map.put("state", 1);
			map.put("tah", tah);
			map.put("zxjcid", td.getZxjcid());
			map.put("lastate", td.getLastate());
			int s = taxxService.count(map);
			if(s==0) {
				taxxService.updateSdbhtah(id, tah);
			}else {
				return R.error("该提案号已经存在");
			}
		}else {
			return R.error("数据变动,请稍后再试");
		}
		return R.ok();
	}
	
	
	/**
	 * 合并提案
	 * @param ids
	 * @param model
	 * @return
	 */
	@GetMapping("/hbta/{ids}")
	String hbta(@PathVariable("ids") Integer[] ids, Model model){
		List<TaxxDO> l = taxxService.getListByIds(ids);
		model.addAttribute("taxxs", l);
	    return "proposal/tasc/hbta";
	}
	
	/**
	 * 并案操作
	 * @param ids
	 * @return
	 */
	@PostMapping( "/updateBa")
	@ResponseBody
	@RequiresPermissions("proposal:tasc:bh")
	public R updateBa(@RequestParam("zid") Integer zid, @RequestParam("fids") Integer[] fids){
		List<TaxxDO> ds = taxxService.getListByIds(fids);
		if(ds!=null && ds.size()>0) {
			for(TaxxDO t : ds) {
				if(t.getTah()!=null) {
					//return R.error(t.getTatm()+",已编写提案号不能并案");
				}else if(t.getIsba()!=null && t.getIsba().intValue()==1 && t.getBaid()==null){//是否并案为1,同时并案父id为null则说明是主并
					return R.error(t.getTatm()+",主并的数据不能进行并案操作");
				}
			}
			taxxService.updateBa(zid, fids);
		}
		return R.ok();
	}
	
	/**
	 * 对比提案的单位是否一致
	 * @param ids
	 * @return
	 */
	@PostMapping( "/contrastCbdw")
	@ResponseBody
	public R contrastCbdw(@RequestParam("ids[]") Integer[] ids){
//		ids
		Map<Integer,Integer> cbdwNum = new HashMap<>();
		Map<String,Object> params = new HashMap<>();
		for(Integer id : ids) {
			params.put("taxxid", id);
			params.put("types", "1,2");
			List<TaxxCbdwDO> t = taxxCbdwService.list(params);
			if(t!=null && t.size()>0) {//只要办理单位一样,不管位置是否相同(例如:承办单位A协办单位B  承办单位B协办单位A.是允许的)
				for(TaxxCbdwDO tc : t) {
					if(cbdwNum.get(tc.getCbdwid()) == null) {
						cbdwNum.put(tc.getCbdwid(), 1);
					}else {
						cbdwNum.put(tc.getCbdwid(), cbdwNum.get(tc.getCbdwid())+1);
					}
				}
			}
		}
		for(Integer key : cbdwNum.keySet()) {
			if(cbdwNum.get(key).intValue() != ids.length) {
				return R.error("办理单位不相同，不能并案！");
			}
		}
		
		return R.ok();
	}
	
	/**
	 * 取消并案
	 * @param id
	 * @return
	 */
	@ResponseBody
	@PostMapping( "/qxba")
	public R qxba(@RequestParam("id") Integer id){
		TaxxDO t = taxxService.get(id);
		if(t.getIsba()!=null && t.getBaid()==null) {//主并
			taxxService.updateQxba(id);
			Map<String, Object> map = new HashMap<>();
			map.put("zxjcid", t.getZxjcid());
			map.put("baid", t.getId());
			List<TaxxDO> taxx = taxxService.list(map);
			if(taxx!=null && taxx.size()>0) {
				for(TaxxDO ta : taxx) {
					taxxService.updateQxba(ta.getId());
				}
			}
		}else if(t.getIsba()!=null){//被并
			Map<String, Object> map = new HashMap<>();
			map.put("zxjcid", t.getZxjcid());
			map.put("baid", t.getBaid());//父id
			List<TaxxDO> taxx = taxxService.list(map);
			if(taxx!=null && taxx.size()==1) {//如果父id下只有一个被并案的,则把父id一起取消并案
				taxxService.updateQxba(t.getBaid());//父id
			}
			taxxService.updateQxba(id);
		}else {
			return R.error("该提案不是并案类型");
		}
		return R.ok();
	}
	
	@GetMapping("/bscta")
	@RequiresPermissions("proposal:tasc:bscta")
	String bscta(Model model){
	    return "proposal/tasc/bscta";
	}
	
	/**
	 * 恢复
	 */
	@PostMapping( "/batchRecovery")
	@ResponseBody
	public R batchRecovery(@RequestParam("ids[]") Integer[] ids){
		taxxService.batchRecovery(ids);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/zcbl")
	@ResponseBody
	@RequiresPermissions("proposal:tasc:zcbl")
	public R zcbl(@RequestParam("ids[]") Integer[] ids){
		taxxService.zcbl(ids);
		return R.ok();
	}
}
