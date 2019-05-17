package com.bootdo.proposal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.proposal.domain.CbdwDO;
import com.bootdo.proposal.domain.DpDO;
import com.bootdo.proposal.domain.DqxzDO;
import com.bootdo.proposal.domain.GrwyDO;
import com.bootdo.proposal.domain.SswyhDO;
import com.bootdo.proposal.domain.TajbDO;
import com.bootdo.proposal.domain.TaxxDO;
import com.bootdo.proposal.domain.ZxjcDO;
import com.bootdo.proposal.service.CbdwService;
import com.bootdo.proposal.service.DpService;
import com.bootdo.proposal.service.DqxzService;
import com.bootdo.proposal.service.GrwyService;
import com.bootdo.proposal.service.SswyhService;
import com.bootdo.proposal.service.TajbService;
import com.bootdo.proposal.service.TaxxService;
import com.bootdo.proposal.service.ZxjcService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import com.bootdo.common.config.Constant;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;

/**
 * 
 * 
 * @author shipan
 * @email 
 * @date 2018-10-19 14:22:10
 */
 
@Controller
@RequestMapping("/proposal/cbdw")
public class CbdwController {
	@Autowired
	private CbdwService cbdwService;
	@Autowired
	private UserService userService;
	@Autowired
	private GrwyService grwyService;
	@Autowired
	private TaxxService taxxService;
	@Autowired
	private DqxzService dqxzService;
	@Autowired
	private SswyhService sswyhService;
	@Autowired
	private ZxjcService zxjcService;
	@Autowired
	private TajbService tajbService;
	@Autowired
	private DpService dpService;
	
	
	@GetMapping()
	@RequiresPermissions("proposal:cbdw:cbdw")
	String Cbdw(){
	    return "proposal/cbdw/cbdw";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:cbdw:cbdw")
	public List<CbdwDO> list(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<CbdwDO> cbdwList = cbdwService.list(params);
		return cbdwList;
	}
	
	@ResponseBody
	@GetMapping("/listCbdw")
	@RequiresPermissions("proposal:cbdw:cbdw")
	public PageUtils listCbdw(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        query.put("type", GrwyDO.CBDW);
        if(query.get("name") != null && !"".equals(query.get("name").toString())) {
        	query.put("name", "%"+query.get("name")+"%");
        }
        
		List<GrwyDO> grwyList = cbdwService.listCbdw(query);
		int total = cbdwService.listCbdwCount(query);
		PageUtils pageUtils = new PageUtils(grwyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add/{pId}")
	@RequiresPermissions("proposal:cbdw:add")
	String add(@PathVariable("pId") Integer pId, Model model){
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "无");
		} else {
			model.addAttribute("pName", cbdwService.get(pId).getName());
		}
	    return "proposal/cbdw/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("proposal:cbdw:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		GrwyDO grwy = grwyService.get(id);
		UserDO user = userService.get(grwy.getUserid().longValue());
		grwy.setUserName(user.getUsername());
		grwy.setPwd(user.getPassword());
		
		model.addAttribute("cbdw", grwy);
		
		CbdwDO cd = cbdwService.get(grwy.getDwmcid());
		model.addAttribute("cd", cd);
		
		CbdwDO parentCbdw = cbdwService.get(cd.getParentId());
		model.addAttribute("parentCbdw", parentCbdw);
		
	    return "proposal/cbdw/edit";
	}
	
	@GetMapping("/grEdit")
	@RequiresPermissions("proposal:cbdw:edit")
	String grEdit(Model model){
		GrwyDO grwy = grwyService.getByUserId(ShiroUtils.getUserIdInteger());
		UserDO user = userService.get(grwy.getUserid().longValue());
		grwy.setUserName(user.getUsername());
		grwy.setPwd(user.getPassword());
		
		model.addAttribute("cbdw", grwy);
		
		CbdwDO cd = cbdwService.get(grwy.getDwmcid());
		model.addAttribute("cd", cd);
		
		CbdwDO parentCbdw = cbdwService.get(cd.getParentId());
		model.addAttribute("parentCbdw", parentCbdw);
		
	    return "proposal/cbdw/grEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("proposal:cbdw:add")
	public R save( CbdwDO cbdw, GrwyDO grwy){
		grwy.setType(GrwyDO.CBDW);
		if(cbdwService.save(cbdw, grwy)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("proposal:cbdw:edit")
	public R update( CbdwDO cbdw, GrwyDO grwy){
		cbdwService.update(cbdw, grwy);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("proposal:cbdw:remove")
	public R remove( Integer id){
		if(cbdwService.remove(id)>0){
			return R.ok();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", id);
		if(cbdwService.count(map)>0) {
			return R.error(1, "包含下级部门,不允许修改");
		}
		
		if (cbdwService.remove(id) > 0) {
			return R.ok();
		}
		
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("proposal:cbdw:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		cbdwService.batchRemove(ids);
		return R.ok();
	}
	
	@ResponseBody
	@GetMapping("/getTreeData")
	public List<Map<String,Object>> getTreeData(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<CbdwDO> cbdwList = cbdwService.getTreeData(params);
		LinkedHashMap<Integer, List<CbdwDO>> map = new LinkedHashMap<>();
		if(cbdwList!=null && cbdwList.size() > 0) {
			for(CbdwDO c : cbdwList) {
				List<CbdwDO> l = map.get(c.getParentId());
				if(l == null) {
					l = new ArrayList<>();
				}
				l.add(c);
				map.put(c.getParentId(), l);
			}
			List<Map<String,Object>> r = getTreeview(map, 0);
			return r;
		}
		
		return null;
	}
	
	private List<Map<String,Object>> getTreeview(LinkedHashMap<Integer, List<CbdwDO>> map, Integer parentId){
		List<Map<String,Object>> ls_list = null;
		if(map.get(parentId) != null) {
			ls_list = new ArrayList<>();
			List<CbdwDO> l = map.get(parentId);
			for(CbdwDO c : l) {
				Map<String,Object> m = new HashMap<>();
				if(c.getLa() == null || c.getYcl() == null || c.getLa() == 0) {
					m.put("text", c.getName());
				}else if(c.getLa().intValue() == c.getYcl().intValue()) {
					m.put("text", "<span style='color: #2de143;'>"+c.getName()+"</span>");
				}else {
					m.put("text", "<span style='color: #ff0000;'>"+c.getName()+"</span>");
				}
				
				m.put("id", c.getId());
				if(map.get(c.getId()) != null) {
					List<Map<String, Object>> nodes = getTreeview(map, c.getId());
					m.put("nodes", nodes);
				}
				ls_list.add(m);
			}
		}
		return ls_list;
	}
	
	@ResponseBody
	@GetMapping("/selectAllSelect")
	public Map<String,Object> selectAllSelect(@RequestParam Map<String, Object> params){
		Map<String,Object> retDataMap = new HashMap<>();
		
		//查询列表数据
		List<CbdwDO> cbdwList = cbdwService.list(params);
		LinkedHashMap<Integer, List<CbdwDO>> map = new LinkedHashMap<>();
		if(cbdwList!=null && cbdwList.size() > 0) {
			for(CbdwDO c : cbdwList) {
				List<CbdwDO> l = map.get(c.getParentId());
				if(l == null) {
					l = new ArrayList<>();
				}
				l.add(c);
				map.put(c.getParentId(), l);
			}
			List<Map<String,Object>> retData = new ArrayList<>();
			getSelectData(map, 0, retData, "");
			retDataMap.put("results", retData);
		}
		
		return retDataMap;
	}
	
	private void getSelectData(LinkedHashMap<Integer, List<CbdwDO>> map, Integer parentId, List<Map<String,Object>> retData, String prefix){
		
		if(map.get(parentId) != null) {
			List<CbdwDO> l = map.get(parentId);
			for(CbdwDO c : l) {
				Map<String,Object> m = new HashMap<>();
				m.put("text", prefix+c.getName());
				m.put("id", c.getId());
				retData.add(m);
				if(map.get(c.getId()) != null) {
					getSelectData(map, c.getId(), retData, prefix+"......");
				}
			}
		}
	}
	
	/**
	 * 查询select下拉框所需要的数据
	 * @return
	 */
	@GetMapping( "/selectAllSelectByTaxxId")
	@ResponseBody
	public Map<String,Object> selectAllSelectByTaxxId(@RequestParam Map<String, Object> params){
		Map<String,Object> map = new HashMap<>();
		List<Map<String,Object>> l = cbdwService.selectAllSelectByTaxxId(params);
		map.put("results", l);
		return map;
	}
	
	/**
	 * 导入
	 * @return
	 */
	@GetMapping("/dr")
	@RequiresPermissions("proposal:cbdw:dr")
	String dr(){
	    return "proposal/cbdw/dr";
	}
	
	/**
     * 导入excel
     * @param upFile
     * @return
     */
	@ResponseBody
	@PostMapping(value="/upExcel")
	public R upExcel(@RequestParam("inputFile") MultipartFile upFile){
		R r = R.ok();
		try {
			if(upFile != null){
				String filename=upFile.getOriginalFilename();
				if(filename.lastIndexOf(".") != -1){
					String fLast = filename.substring(filename.lastIndexOf("."), filename.length());
					if(".xls".equals(fLast) || ".xlsx".equals(fLast)){
						//设计想法:1全量导入,先把所有的委员的账户都设置为停用.然后在开放存在的,新增的则新增.
						Map<String,Object> map = cbdwService.saveExcel(upFile);
						r.put("result", map);
						return r;
					}else{//后缀不对
						r = R.error(2,"后缀不对");
						return r;
					}
				}else{//后缀不对
					r = R.error(2,"后缀不对"); 
					return r;
				}
			}else{//文件为空
				r = R.error(3,"文件为空"); 
				return r;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	@GetMapping("/znzz")
	String znzz(){
	    return "proposal/cbdw/znzz";
	}
	
	/**
	 * 职能职责
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/listZnzz")
	public PageUtils listZnzz(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        query.put("type", GrwyDO.CBDW);
        if(query.get("name") != null && !"".equals(query.get("name").toString())) {
        	query.put("name", "%"+query.get("name")+"%");
        }
        
		List<GrwyDO> grwyList = cbdwService.listCbdw(query);
		int total = cbdwService.listCbdwCount(query);
		PageUtils pageUtils = new PageUtils(grwyList, total);
		return pageUtils;
	}
	
	/**
	 * 承办单位提案管理
	 * @return
	 */
	@GetMapping("/cbdwTagl")
	@RequiresPermissions("proposal:cbdw:cbdwTagl")
	String cbdwTagl(){
	    return "proposal/cbdw/cbdwTagl";
	}
	
	@ResponseBody
	@GetMapping("/cbdwTaglList")
	public PageUtils cbdwTaglList(@RequestParam Map<String, Object> params){
		//查询列表数据
		if(params.get("cbdwId2") == null || "".equals(params.get("cbdwId2").toString())) {
			return null;
		}
		
        Query query = new Query(params);
		List<TaxxDO> taxxList = taxxService.tablList(query);
		int total = taxxService.tablCount(query);
		PageUtils pageUtils = new PageUtils(taxxList, total);
		return pageUtils;
	}
	
	/**
	 * 查看提案创建人的信息
	 * @return
	 */
	@GetMapping("/readTaxxCreateUserInfo")
	String readTaxxCreateUserInfo(Integer id,Model model){
		
		TaxxDO taxx = taxxService.get(id);
		if(taxx == null) {
			return "proposal/grwy/read";
		}
		
		if(taxx.getTanx().intValue() == 1) {
			GrwyDO grwy = grwyService.getByUserId(taxx.getCreateid());
			UserDO user = userService.get(grwy.getUserid().longValue());
			grwy.setUserName(user.getUsername());
			grwy.setPwd(user.getPassword());
			model.addAttribute("grwy", grwy);
			
			DqxzDO dd = dqxzService.get(grwy.getSsdqid());
			model.addAttribute("ssdqName", dd==null ? "" : dd.getDqxz());
			
			SswyhDO sd = sswyhService.get(grwy.getSswyhid());
			model.addAttribute("sswyhName", sd==null ? "" : sd.getSswyhmc());
			
			ZxjcDO zd = zxjcService.get(grwy.getSsjcid());
			model.addAttribute("ssjcName", zd==null ? "" : zd.getZxjcmc());
			
			TajbDO td = tajbService.get(grwy.getSsjbid());
			model.addAttribute("ssjbName", td==null ? "" : td.getTajbmc());
			
			DpDO dp = dpService.get(grwy.getSsdpid());
			model.addAttribute("ssdpName", dp==null ? "" : dp.getDpmc());
			
		    return "proposal/grwy/read";
		}else if(taxx.getTanx().intValue() == 2) {
			GrwyDO jtwy = grwyService.getByUserId(taxx.getCreateid());
			UserDO user = userService.get(jtwy.getUserid().longValue());
			jtwy.setUserName(user.getUsername());
			jtwy.setPwd(user.getPassword());
			model.addAttribute("jtwy", jtwy);
			
			ZxjcDO zd = zxjcService.get(jtwy.getSsjcid());
			model.addAttribute("ssjcName", zd==null ? "" : zd.getZxjcmc());
			
		    return "proposal/jtwy/read";
		}
		
	    return "proposal/grwy/read";
	}
	
	@GetMapping("/readCbdw")
	String readCbdw(Integer id, Model model){
		GrwyDO grwy = grwyService.getByDwmcId(id);
		UserDO user = userService.get(grwy.getUserid().longValue());
		grwy.setUserName(user.getUsername());
		grwy.setPwd(user.getPassword());
		
		model.addAttribute("cbdw", grwy);
		
		CbdwDO cd = cbdwService.get(grwy.getDwmcid());
		model.addAttribute("cd", cd);
		
		CbdwDO parentCbdw = cbdwService.get(cd.getParentId());
		model.addAttribute("parentCbdw", parentCbdw);
		
	    return "proposal/cbdw/read";
	}
}
