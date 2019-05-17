package com.bootdo.proposal.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.proposal.service.*;
import io.swagger.models.auth.In;
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

import com.bootdo.proposal.domain.FhhzDO;
import com.bootdo.proposal.domain.GbsqDO;
import com.bootdo.proposal.domain.GrwyDO;
import com.bootdo.proposal.domain.GzfwDO;
import com.bootdo.proposal.domain.LbyblsxDO;
import com.bootdo.proposal.domain.PfbDO;
import com.bootdo.proposal.domain.PfbTaxxDO;
import com.bootdo.proposal.domain.SswyhDO;
import com.bootdo.proposal.domain.TaxxDO;
import com.bootdo.proposal.domain.ZcblDO;
import com.bootdo.proposal.domain.ZxjcDO;
import com.bootdo.proposal.util.DeleteFileUtil;
import com.bootdo.common.config.BootdoConfig;
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
@RequestMapping("/proposal/tabl")
public class TablController {
	@Autowired
	private TaxxService taxxService;
	@Autowired
	private TaxxCbdwService taxxCbdwService;
	@Autowired
	private GbsqCbdwService gbsqCbdwService;
	@Autowired
	private GbsqService gbsqService;
	@Autowired
	private FhhzService fhhzService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private PfbService pfbService;
	@Autowired
	private PfbTaxxService pfbTaxxService;
	@Autowired
	private ZxjcService zxjcService;
	@Autowired
	private GrwyService grwyService;
	@Autowired
	private ZcblService zcblService;
	@Autowired
	private FsdxService fsdxService;
	@Autowired
	private XxService xxService;



	@GetMapping("/{type}")
	@RequiresPermissions("proposal:tabl:tabl")
	String Taxx(@PathVariable("type") Integer type,Model model){
		model.addAttribute("type", type);
	    return "proposal/tabl/tabl";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("proposal:tabl:tabl")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
//		params.put("lastate", 1);//提案

		params.put("xyLaState", 3);//可以查询立案和意见
//		params.put("tastate", 4);//转交
		//params.put("dyTastate", 3);//转交

		GrwyDO gr = grwyService.getByUserId(ShiroUtils.getUserIdInteger());
		if(gr == null) {
			return null;
		}
		params.put("cbdwId", gr.getDwmcid());//小于
		params.put("gbsqCreateId", ShiroUtils.getUserIdInteger());

		Query query = new Query(params);
		List<TaxxDO> taxxList = taxxService.tablList(query);

		for (int i = 0; i <taxxList.size() ; i++) {


			if(taxxList.get(i).getLatype()==2){
				//根据上传文件的数量改变状态
				Map map = new HashMap();
				map.put("taxxid",taxxList.get(i).getId());
				map.put("createid",ShiroUtils.getUserIdInteger());
				Integer count = fhhzService.findByFhhz(map);
				if(count==0){
					taxxList.get(i).setTastate(4);
				}else if(count==1){
					taxxList.get(i).setTastate(5);
				}else{
					taxxList.get(i).setTastate(6);
				}
				//根据是否查询到PF数据来改变评分状态
				Map mapPF = new HashMap();
				mapPF.put("taxxid",taxxList.get(i).getId());
				mapPF.put("createid",ShiroUtils.getUserIdInteger());
				int pfbCount = pfbTaxxService.count(mapPF);
				if(pfbCount == 0 ){
					taxxList.get(i).setPfCount(0);
				}


			}
			//查询本提案的cbdw完成情况 已完成字体改为绿色
			String cbdwName = taxxList.get(i).getCbdw();
			String name[] = cbdwName.split(",");
			StringBuilder b = new StringBuilder();
			for (int j = 0; j < name.length; j++) {
				Map mapName = new HashMap();
				mapName.put("name",name[j]);
				mapName.put("taxxid",taxxList.get(i).getId());

				if(pfbTaxxService.findByNameCount(mapName)!=0){
					name[j] = name[j]+"（已评分）";
				}
				if(fhhzService.findByName(mapName) !=0){
					b.append("<font color=\"green\">"+name[j]+"</font>"+",");
				}else{
					b.append("<font>"+name[j]+"</font>"+",");
				}

			}
			b.deleteCharAt(b.length()-1);
			taxxList.get(i).setCbdw(b.toString());
			//协办案件
			if(taxxList.get(i).getLatype()==3){
				String xbdw =  taxxList.get(i).getXbdw();
				String xbdwName[] = xbdw.split(",");
				//根据协办单位名称去查是否已发送消息
				StringBuilder s = new StringBuilder();
				for (int j = 0; j <xbdwName.length ; j++) {
					Map mapName = new HashMap();
					mapName.put("name",xbdwName[j]);
					mapName.put("taxxid",taxxList.get(i).getId());
					if(xxService.findCountByName(mapName) ==0 ){
						s.append("<font color=\"red\">"+xbdwName[j]+"</font>"+",");
					}else{
						s.append("<font>"+xbdwName[j]+"</font>"+",");
					}
				}
				s.deleteCharAt(s.length()-1);
				taxxList.get(i).setXbdw(s.toString());
			}


		}

		int total = taxxService.tablCount(query);
		PageUtils pageUtils = new PageUtils(taxxList, total);
		return pageUtils;
	}

	/**
	 * 审核
	 * @param type
	 * @param model
	 * @return
	 */
	@GetMapping("/bl/{id}/{type}")
	String sh(@PathVariable("id") Integer id, @PathVariable("type") Integer type, Model model){
		TaxxDO wy = taxxService.get(id);
		model.addAttribute("wy", wy);

		model.addAttribute("type", type);

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

	    return "proposal/tabl/bl";
	}


	/**
	 * 申请改办
	 * @param type
	 * @param model
	 * @return
	 */
	@GetMapping("/sqgb/{taxxid}/{type}")
	String sqgb(@PathVariable("taxxid") Integer taxxid, @PathVariable("type") Integer type, Model model){

		model.addAttribute("type", type);

		model.addAttribute("taxxid", taxxid);

		Map<String,Object> gbsqMap = new HashMap<>();
		gbsqMap.put("taxxid", taxxid);
		gbsqMap.put("createid", ShiroUtils.getUserIdInteger());

		List<GbsqDO> list = gbsqService.list(gbsqMap);
		if(list!=null && list.size()>0) {
			GbsqDO gbsqDO = list.get(0);

			model.addAttribute("id", gbsqDO.getId());
			model.addAttribute("latype", gbsqDO.getLatype());
			model.addAttribute("ly", gbsqDO.getLy());
			model.addAttribute("gbsqType", gbsqDO.getType());

			Map<String,Object> map = new HashMap<>();
			map.put("taxxId", gbsqDO.getId());
			Map<String, Object> m = gbsqCbdwService.getCbdw(map);

			if(m!=null && !"0".equals(m.get("cbdw"))) {
				model.addAttribute("cbdw", m.get("cbdw"));
			}
			if(m!=null && !"0".equals(m.get("fbdw"))) {
				model.addAttribute("fbdw", m.get("fbdw"));
			}
			if(m!=null && !"0".equals(m.get("xbdw"))) {
				model.addAttribute("xbdw", m.get("xbdw"));
			}
		}

		TaxxDO t = taxxService.get(taxxid);
		if(t == null) {
			return "";
		}else if(t.getLastate()==1){//立案
			model.addAttribute("lastate", 1);
			return "proposal/tabl/sqgb";
		}else if(t.getLastate()==2){//意见
			model.addAttribute("lastate", 2);
			return "proposal/tabl/sqgbYj";
		}

	    return "";
	}

	/**
	 * 保存改办申请
	 */
	@ResponseBody
	@RequestMapping("/saveGbsq")
	public R updateTasc( GbsqDO gbsqDO,String[] cbdw,String[] fbdw,String[] xbdw){
		gbsqService.save(gbsqDO,cbdw,fbdw,xbdw);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/removeGbsq")
	@ResponseBody
	public R removeGbsq( Integer id){
		GbsqDO g = gbsqService.get(id);
		if(g.getType().intValue() == 1) {
			return R.error("该申请已处理不能撤销！");
		}

		if(gbsqService.remove(id)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 复函
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/fh/{id}")
	String fh(@PathVariable("id") Integer id, Model model){
		model.addAttribute("id", id);

	    return "proposal/tabl/fh";
	}

	@ResponseBody
	@GetMapping("/fhhzList")
	public PageUtils fhhzList(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FhhzDO> fhhzList = fhhzService.list(query);
		int total = fhhzService.count(query);
		PageUtils pageUtils = new PageUtils(fhhzList, total);
		return pageUtils;
	}

	/**
	 * 复函
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/addFh/{taxxid}")
	String addFh(@PathVariable("taxxid") Integer taxxid, Model model){
		model.addAttribute("taxxid", taxxid);

	    return "proposal/tabl/addFh";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/saveFhhz")
	public R saveFhhz( FhhzDO fhhz){

		TaxxDO t = taxxService.get(fhhz.getTaxxid());
		if(t==null) {
			return R.error("提案数据改变,请稍后重试");
		}

//		fhhz.setUserid(t.getCreateid());

		if(fhhzService.save(fhhz)>0){
			return R.ok();
		}

		return R.error();
	}

	/**
	 * 复函意见稿
	 * @param taxxid
	 * @param model
	 * @return
	 */
	@GetMapping("/fhyjg/{taxxid}/{type}")
	String fhyjg(@PathVariable("taxxid") Integer taxxid, @PathVariable("type") Integer type, Integer chakan,  Model model){
		model.addAttribute("taxxid", taxxid);
		model.addAttribute("type", type);
		model.addAttribute("chakan", chakan);

		TaxxDO taxx = taxxService.get(taxxid);
		model.addAttribute("taxx", taxx);
		Map<String,Object> map = new HashMap<>();
		map.put("taxxid", taxxid);
		map.put("type", type);
		List<FhhzDO> list = fhhzService.list(map);
		String ids = "";
		String dzs = "";
		String mcs = "";
		if(list!=null && list.size()>0){
			for(FhhzDO f : list) {
				ids += f.getId() + ",";
				dzs += f.getDz() + ",";
				mcs += f.getMc() + ",";
			}
			ids = ids.substring(0,ids.length()-1);
			dzs = dzs.substring(0,dzs.length()-1);
			mcs = mcs.substring(0,mcs.length()-1);
		}
		model.addAttribute("ids", ids);
		model.addAttribute("dzs", dzs);
		model.addAttribute("mcs", mcs);

	    return "proposal/tabl/fhyjg";
	}

	/**
	 * 复函意见稿
	 * @param taxxid
	 * @param model
	 * @return
	 */
	@GetMapping("/pf/{taxxid}/{type}")
	String pf(@PathVariable("taxxid") Integer taxxid, @PathVariable("type") Integer type, Model model){
		model.addAttribute("taxxid", taxxid);
		model.addAttribute("type", type);

		Map<String,Object> map = new HashMap<>();
		map.put("type", type);
		List<PfbDO> pfbs = pfbService.list(map);
		model.addAttribute("pfbs", pfbs);

		map.clear();
		map.put("taxxid", taxxid);
		map.put("type", type);
		List<PfbTaxxDO> pts = pfbTaxxService.list(map);
		Map<Integer,Object> ptMap = new HashMap<>();
		int total = 0;
		if(pts!=null && pts.size()>0) {
			for(PfbTaxxDO p : pts) {
				ptMap.put(p.getPfbid(), p.getFz());
				total += p.getFz();
			}
		}
		model.addAttribute("ptMap", ptMap);
		model.addAttribute("total", total);

	    return "proposal/tabl/pf";
	}

	/**
	 * 上传
	 */
	@PostMapping( "/uploadFj")
	@ResponseBody
	public R uploadFj(@RequestParam("taxxid") Integer taxxid,@RequestParam("type") Integer type,@RequestParam("inputFiles") MultipartFile[] files){
		TaxxDO taxx = taxxService.get(taxxid);
		String name =  taxxService.findCountByName(taxxid);
		String xbdwName[] =  name.split(",");
		for (int i = 0; i <xbdwName.length ; i++) {
			Map map = new HashMap();
			map.put("name",xbdwName[i]);
			map.put("taxxid",taxx.getId());
			if (xxService.findCountByName(map) == 0){
				return R.error("请与所有协办单位沟通后在进行提交");
			}
		}


		if(taxx != null) {
			if(files!=null && files.length>0) {
				for(MultipartFile file : files) {
					// 获取文件名
					String fileName = file.getOriginalFilename();
					// 获取文件的后缀名
					String suffixName = fileName.substring(fileName.lastIndexOf("."));
					// 文件上传后的路径
					String filePath = bootdoConfig.getUploadPath();
					// 解决中文问题，liunx下中文路径，图片显示问题
					String fileUrl = "/fj/"+ UUID.randomUUID() + suffixName;

					File dest = new File(filePath + fileUrl);
					// 检测是否存在目录
					if (!dest.getParentFile().exists()) {
						dest.getParentFile().mkdirs();
					}

					try {
						file.transferTo(dest);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					FhhzDO fhhz = new FhhzDO();
					fhhz.setCreateid(ShiroUtils.getUserIdInteger());
					fhhz.setCreatetime(new Date());
					fhhz.setUpdateid(ShiroUtils.getUserIdInteger());
					fhhz.setUpdatetime(new Date());
					fhhz.setState(1);
					fhhz.setTaxxid(taxxid);
					fhhz.setType(type);
					fhhz.setFsr(ShiroUtils.getUserIdInteger());
					fhhz.setJsr(taxx.getCreateid());
					fhhz.setDz("/files"+fileUrl);
					fhhz.setMc(fileName);
					fhhzService.save(fhhz);

					if(taxx.getTastate().intValue() == 4 || taxx.getTastate().intValue() == 5 ) {//修改提案状态
						if(taxx.getIsba()!=null && taxx.getIsba().intValue()==1 && taxx.getBaid()!=null) {//被并案件不能单独转交
							continue;
						}
						Integer cbdwId = taxxService.findUserId(ShiroUtils.getUser().getUsername());
						TaxxDO t = new TaxxDO();
						t.setId(taxxid);
						if(type.intValue() == 1 && taxx.getTastate().intValue() == 4) {
							Map map = new HashMap();
							map.put("taxxid",taxxid);

							Map mapCbdw = new HashMap();
							mapCbdw.put("taxxid",taxxid);
							mapCbdw.put("cbdwid",cbdwId);
							mapCbdw.put("cbdwjd",5);
							int a = taxxService.updateCbdwjd(mapCbdw);

							map.put("typeid",1); //1:意见稿 2：正式稿
								t.setTastate(5);//已提交复函意见稿

							taxxService.updateTahAndSon(t);

						}else if(type.intValue() == 2 && taxx.getTastate().intValue() == 5){

							Map mapCbdw = new HashMap();
							mapCbdw.put("taxxid",taxxid);
							mapCbdw.put("cbdwid",cbdwId);
							mapCbdw.put("cbdwjd",6);
							int b = taxxService.updateCbdwjd(mapCbdw);//该承办单位已提交正式复函

							Map mapX = new HashMap();
							mapX.put("taxxid",taxxid);

							mapX.put("typeid",2); //1:意见稿 2：正式稿
							/*//查询此提案已提交的意见稿数单位数量
							int countFhhz =  fhhzService.conuntByTaxxid(mapX);*/

								t.setTastate(6);//已提交正式复函回执

							taxxService.updateTahAndSon(t);


							//发送短信5 7
							Map<String,Object> map = new HashMap<>();
							map.put("state", 1);
							map.put("taxxid", taxxid);
							List<ZcblDO> zc = zcblService.list(map);

							List<TaxxDO> tList = taxxService.getListByIds(new Integer[] {taxxid});
							if(tList!=null && tList.size()>0) {
								TaxxDO td = tList.get(0);
								GrwyDO grwy = grwyService.getByUserId(td.getCreateid());
								List<String> tels = new ArrayList<>();
								tels.add(grwy.getSjhm());
								Map<String,String> par = new HashMap<>();
								par.put("tatm", td.getTatm());
								par.put("zxjcmc", td.getZxjcmc());
								par.put("tah", td.getTah());
								par.put("cbdw", td.getCbdw()+(td.getXbdw()==null||"".equals(td.getXbdw())?"":","+td.getXbdw()));
								if(zc!=null && zc.size()>0) {//再次办理,7
									fsdxService.fs(7, par, tels);
								}else {//5
									fsdxService.fs(5, par, tels);
								}
							}

						}
					}
				}

				return R.ok();
			}
		}

		return R.error();
	}

	/**
	 * 删除附件
	 */
	@PostMapping( "/removeFj")
	@ResponseBody
	public R removeFj(@RequestParam("key") Integer id){
		FhhzDO f = fhhzService.get(id);
		if(f!=null) {
			String delDz = f.getDz().substring(6, f.getDz().length());
			DeleteFileUtil.delete(bootdoConfig.getUploadPath()+delDz);
			if(fhhzService.remove(id) > 0) {
				return R.ok();
			}
		}else {
			return R.error("没有找到此附件");
		}
		return R.error();
	}

	/**
	 * 保存评分
	 * @param ids
	 * @param vals
	 * @param pfbid
	 * @param taxxid
	 * @param type
	 * @return
	 */
	@ResponseBody
	@PostMapping("/savePf")
	public R savePf(Integer[] ids, Integer[] vals, Integer taxxid, Integer type){

		if(pfbTaxxService.saveList(ids,vals,taxxid,type)>0){
			return R.ok();
		}

		return R.error();
	}
}
