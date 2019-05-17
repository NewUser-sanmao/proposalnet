package com.bootdo.proposal.controller;

import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.bootdo.common.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.proposal.domain.GrwyDO;
import com.bootdo.proposal.excel.ExcelException;
import com.bootdo.proposal.excel.ExcelUtil;
import com.bootdo.proposal.service.FhhzService;
import com.bootdo.proposal.service.GbsqCbdwService;
import com.bootdo.proposal.service.GbsqService;
import com.bootdo.proposal.service.PfbService;
import com.bootdo.proposal.service.PfbTaxxService;
import com.bootdo.proposal.service.TaxxCbdwService;
import com.bootdo.proposal.service.TaxxService;
import com.bootdo.proposal.service.ZxjcService;

/**
 * 提案信息
 *
 * @author shipan
 * @email
 * @date 2018-10-17 14:16:06
 */

@Controller
@RequestMapping("/proposal/tjfx")
public class TacxtjController {
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

    @GetMapping("/grtatj")
    //@RequiresPermissions("proposal:tjfx:grtatj")
    String grtatj(){
        return "proposal/tjfx/grtatj";
    }
    @GetMapping("/tacbdwhz")
    //@RequiresPermissions("proposal:tjfx:tacbdwhz")
    String tacbdwhz(){
        return "proposal/tjfx/tacbdwhz";
    }
    
	@GetMapping("/jttatj")
	String jttatj(){
		return "proposal/tjfx/jttatj";
	}
	
	@ResponseBody
	@GetMapping("/jttatjList")
	public PageUtils jttatjList(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("type", "2");
        List<Map<String, Object>> taxxList = taxxService.tatjb(params);
        int total = taxxService.tablCount(params);
        PageUtils pageUtils = new PageUtils(taxxList, total);
        return pageUtils;
	}
    
    @ResponseBody
    @RequestMapping("/downLoad")
    public void downLoad(String checkxx, @RequestParam Map<String, Object> params, HttpServletResponse response){
        params.put("type","2");
//        Query query = new Query(params);
        if("".equals(checkxx)){
            List<Map<String, Object>> taxxList = taxxService.tatjb(params);
            List<GrwyDO> resultValue = new ArrayList<>();
            taxxList.forEach(e->{
                GrwyDO day=new GrwyDO();
                day.setDwjc(e.get("dwmc").toString());
                day.setDwmc(e.get("count").toString());
                day.setYb(e.get("la").toString());
                resultValue.add(day);
            });
            LinkedHashMap<String, String> fieldMap=new LinkedHashMap<>();
            fieldMap.put("dwjc", "集体名称");
            fieldMap.put("dwmc", "提案数量");
            fieldMap.put("yb", "立案数量");
            String fieldName="集体提案统计";
            try {
                ExcelUtil.listToExcel(resultValue, fieldMap, fieldName, response);
            } catch (ExcelException e) {
                e.printStackTrace();
            }
        }else{
            params.put("zxjcId",checkxx);
            List<Map<String, Object>> taxxList = taxxService.tatjb(params);
            List<GrwyDO> resultValue = new ArrayList<>();
            taxxList.forEach(e->{
                GrwyDO day=new GrwyDO();
                day.setDwjc(e.get("dwmc").toString());
                day.setDwmc(e.get("count").toString());
                day.setYb(e.get("la").toString());
                resultValue.add(day);
            });
            LinkedHashMap<String, String> fieldMap=new LinkedHashMap<>();
            fieldMap.put("dwjc", "集体名称");
            fieldMap.put("dwmc", "提案数量");
            fieldMap.put("yb", "立案数量");
            String fieldName="集体提案统计";
            try {
                ExcelUtil.listToExcel(resultValue, fieldMap, fieldName, response);
            } catch (ExcelException e) {
                e.printStackTrace();
            }
        }
    }


    @ResponseBody
    @GetMapping("/grtatjList")
    public PageUtils grtatjList(@RequestParam Map<String, Object> params){
        //查询列表数据
        params.put("type", "1");
        List<Map<String, Object>> taxxList = taxxService.tatjb(params);
        int total = taxxService.tablCount(params);
        PageUtils pageUtils = new PageUtils(taxxList, total);
        return pageUtils;
    }

    @ResponseBody
    @RequestMapping("/grtatjDownLoad")
    public void downLoadgrtatjDownLoad(String checkxx, @RequestParam Map<String, Object> params, HttpServletResponse response){
        params.put("type","1");
//        Query query = new Query(params);
        if("".equals(checkxx)){
            List<Map<String, Object>> taxxList = taxxService.tatjb(params);
            List<GrwyDO> resultValue = new ArrayList<>();
            taxxList.forEach(e->{
                GrwyDO day=new GrwyDO();
                day.setDwjc(e.get("xm").toString());
                day.setDwmc(e.get("count").toString());
                day.setYb(e.get("la").toString());
                resultValue.add(day);
            });
            LinkedHashMap<String, String> fieldMap=new LinkedHashMap<>();
            fieldMap.put("dwjc", "委员名称");
            fieldMap.put("dwmc", "提案数量");
            fieldMap.put("yb", "立案数量");
            String fieldName="集体提案统计";
            try {
                ExcelUtil.listToExcel(resultValue, fieldMap, fieldName, response);
            } catch (ExcelException e) {
                e.printStackTrace();
            }
        }else{
            params.put("zxjcId",checkxx);
            List<Map<String, Object>> taxxList = taxxService.tatjb(params);
            List<GrwyDO> resultValue = new ArrayList<>();
            taxxList.forEach(e->{
                GrwyDO day=new GrwyDO();
                day.setDwjc(e.get("xm").toString());
                day.setDwmc(e.get("count").toString());
                day.setYb(e.get("la").toString());
                resultValue.add(day);
            });
            LinkedHashMap<String, String> fieldMap=new LinkedHashMap<>();
            fieldMap.put("dwjc", "委员名称");
            fieldMap.put("dwmc", "提案数量");
            fieldMap.put("yb", "立案数量");
            String fieldName="委员提案统计";
            try {
                ExcelUtil.listToExcel(resultValue, fieldMap, fieldName, response);
            } catch (ExcelException e) {
                e.printStackTrace();
            }
        }
    }


    @ResponseBody
    @GetMapping("/tacbdwhzList")
    public PageUtils tacbdwhzList(@RequestParam Map<String, Object> params){
        //查询列表数据
        //params.put("type", "1");
//    	params.put("dyTastate", 3);
        List<Map<String, Object>> taxxList = taxxService.tacbdwhz(params);
        Map<String,String> zb = new HashMap<>();
        Map<String,String> xb = new HashMap<>();
        Map<String,String> fb = new HashMap<>();
        Map<String,String> yj = new HashMap<>();
        List<String> nameList = new ArrayList<>();
        Map<String,Object> nameId = new HashMap<>();
        
        if(taxxList!=null && taxxList.size()>0){
            for(Map<String, Object> e : taxxList){
                if(e.get("name") != null){
                    /*if("区人大办".equals(e.get("name").toString())){
                        System.out.println();
                    }*/
                    if(nameList.indexOf(e.get("name").toString()) == -1){
                        nameList.add(e.get("name").toString());
                        nameId.put(e.get("name").toString(), e.get("id"));
                    }

                    if(e.get("laState")!=null && "1".equals(e.get("laState").toString())){
                        if(e.get("type")!=null && "1".equals(e.get("type").toString())){//承办
                            String ls_str = zb.get(e.get("name").toString());
                            if(ls_str == null){
                                zb.put(e.get("name").toString(), e.get("tah").toString());
                            }else{
                                ls_str += ","+e.get("tah").toString();
                                zb.put(e.get("name").toString(), ls_str);
                            }
                        }else if(e.get("type")!=null && "2".equals(e.get("type").toString())){//分办
                            String ls_str = fb.get(e.get("name").toString());
                            if(ls_str == null){
                                fb.put(e.get("name").toString(), e.get("tah").toString());
                            }else{
                                ls_str += ","+e.get("tah").toString();
                                fb.put(e.get("name").toString(), ls_str);
                            }
                        }else if(e.get("type")!=null && "3".equals(e.get("type").toString())){//协办
                            String ls_str = xb.get(e.get("name").toString());
                            if(ls_str == null){
                                xb.put(e.get("name").toString(), e.get("tah").toString());
                            }else{
                                ls_str += ","+e.get("tah").toString();
                                xb.put(e.get("name").toString(), ls_str);
                            }
                        }
                    }else if(e.get("laState")!=null && "2".equals(e.get("laState").toString())){
                        String ls_str = yj.get(e.get("name").toString());
                        if(ls_str == null){
                            yj.put(e.get("name").toString(), e.get("lsh").toString());
                        }else{
                            ls_str += ","+e.get("lsh").toString();
                            yj.put(e.get("name").toString(), ls_str);
                        }
                    }
                }
            }
        }
        List<Map<String,Object>> retMap = new ArrayList<>();
        for(String name : nameList){
            Map<String,Object> ls_map = new HashMap<>();
            ls_map.put("name",name);
            ls_map.put("id",nameId.get(name));
            
            int sum=0;
            if(zb.get(name) == null){
                ls_map.put("zbjs",0);
                ls_map.put("zbtah","");
            }else{
                String[] lsArr = zb.get(name).split(",");
                sum += lsArr.length;
                ls_map.put("zbjs",lsArr.length);
                ls_map.put("zbtah",zb.get(name));
            }

            if(xb.get(name) == null){
                ls_map.put("xbjs",0);
                ls_map.put("xbtah","");
            }else{
                String[] lsArr = xb.get(name).split(",");
                sum += lsArr.length;
                ls_map.put("xbjs",lsArr.length);
                ls_map.put("xbtah",xb.get(name));
            }

            if(fb.get(name) == null){
                ls_map.put("fbjs",0);
                ls_map.put("fbtah","");
            }else{
                String[] lsArr = fb.get(name).split(",");
                sum += lsArr.length;
                ls_map.put("fbjs",lsArr.length);
                ls_map.put("fbtah",fb.get(name));
            }

            if(yj.get(name) == null){
                ls_map.put("yjjs",0);
                ls_map.put("yjtah","");
            }else{
                String[] lsArr = yj.get(name).split(",");
                sum += lsArr.length;
                ls_map.put("yjjs",lsArr.length);
                ls_map.put("yjtah",yj.get(name));
            }
            ls_map.put("ydfjs",sum);
            retMap.add(ls_map);
        }
        /*
        List<Map<String, Object>> list=new ArrayList<>();
        Map<String, Object> map= new HashMap<>();
        for(Map<String, Object> e:taxxList){
            int taState=Integer.parseInt(e.get("taState").toString());
            int laState=Integer.parseInt(e.get("laState").toString());
            if(laState==1&&taState>3){
                int i=0;
                map.put("name",e.get("name").toString());
                map.put("","");
            }else if(laState==2){
            }
        }
        int total = taxxService.tablCount(params);*/
        PageUtils pageUtils = new PageUtils(retMap, 0);
        return pageUtils;
    }



    @ResponseBody
    @RequestMapping("/tacbdwhzDownLoad")
    public void tacbdwhzDownLoad(String checkxx, @RequestParam Map<String, Object> params, HttpServletResponse response){
        //查询列表数据
        //params.put("type", "1");
        List<Map<String, Object>> taxxList = taxxService.tacbdwhz(params);
        Map<String,String> zb = new HashMap<>();
        Map<String,String> xb = new HashMap<>();
        Map<String,String> fb = new HashMap<>();
        Map<String,String> yj = new HashMap<>();
        List<String> nameList = new ArrayList<>();

        if(taxxList!=null && taxxList.size()>0){
            for(Map<String, Object> e : taxxList){
                if(e.get("name") != null){
                    /*if("区人大办".equals(e.get("name").toString())){
                        System.out.println();
                    }*/
                    if(nameList.indexOf(e.get("name").toString()) == -1){
                        nameList.add(e.get("name").toString());
                    }

                    if(e.get("laState")!=null && "1".equals(e.get("laState").toString())){
                        if(e.get("type")!=null && "1".equals(e.get("type").toString())){//承办
                            String ls_str = zb.get(e.get("name").toString());
                            if(ls_str == null){
                                zb.put(e.get("name").toString(), e.get("tah").toString());
                            }else{
                                ls_str += ","+e.get("tah").toString();
                                zb.put(e.get("name").toString(), ls_str);
                            }
                        }else if(e.get("type")!=null && "2".equals(e.get("type").toString())){//分办
                            String ls_str = fb.get(e.get("name").toString());
                            if(ls_str == null){
                                fb.put(e.get("name").toString(), e.get("tah").toString());
                            }else{
                                ls_str += ","+e.get("tah").toString();
                                fb.put(e.get("name").toString(), ls_str);
                            }
                        }else if(e.get("type")!=null && "3".equals(e.get("type").toString())){//协办
                            String ls_str = xb.get(e.get("name").toString());
                            if(ls_str == null){
                                xb.put(e.get("name").toString(), e.get("tah").toString());
                            }else{
                                ls_str += ","+e.get("tah").toString();
                                xb.put(e.get("name").toString(), ls_str);
                            }
                        }
                    }else if(e.get("laState")!=null && "2".equals(e.get("laState").toString())){
                        String ls_str = yj.get(e.get("name").toString());
                        if(ls_str == null){
                            yj.put(e.get("name").toString(), e.get("lsh").toString());
                        }else{
                            ls_str += ","+e.get("lsh").toString();
                            yj.put(e.get("name").toString(), ls_str);
                        }
                    }
                }
            }
        }
        List<Map<String,Object>> retMap = new ArrayList<>();
        for(String name : nameList){
            Map<String,Object> ls_map = new HashMap<>();
            ls_map.put("name",name);
            int sum=0;
            if(zb.get(name) == null){
                ls_map.put("zbjs",0);
                ls_map.put("zbtah","");
            }else{
                String[] lsArr = zb.get(name).split(",");
                sum += lsArr.length;
                ls_map.put("zbjs",lsArr.length);
                ls_map.put("zbtah",zb.get(name));
            }

            if(xb.get(name) == null){
                ls_map.put("xbjs",0);
                ls_map.put("xbtah","");
            }else{
                String[] lsArr = xb.get(name).split(",");
                sum += lsArr.length;
                ls_map.put("xbjs",lsArr.length);
                ls_map.put("xbtah",xb.get(name));
            }

            if(fb.get(name) == null){
                ls_map.put("fbjs",0);
                ls_map.put("fbtah","");
            }else{
                String[] lsArr = fb.get(name).split(",");
                sum += lsArr.length;
                ls_map.put("fbjs",lsArr.length);
                ls_map.put("fbtah",fb.get(name));
            }

            if(yj.get(name) == null){
                ls_map.put("yjjs",0);
                ls_map.put("yjtah","");
            }else{
                String[] lsArr = yj.get(name).split(",");
                sum += lsArr.length;
                ls_map.put("yjjs",lsArr.length);
                ls_map.put("yjtah",yj.get(name));
            }
            ls_map.put("ydfjs",sum);
            retMap.add(ls_map);
        }
            List<GrwyDO> resultValue = new ArrayList<>();
            retMap.forEach(e->{
                GrwyDO day=new GrwyDO();
              /*  if(e.get("name").toString().equals("区人大办")){
                    String a="";
                }*/
                day.setDwjc(e.get("name").toString());
                if("null".equals(String.valueOf(e.get("zbjs")))||"".equals(String.valueOf(e.get("zbjs")))){
                    day.setDwmc("0");
                }else{
                    day.setDwmc(e.get("zbjs").toString());
                }

                if("null".equals(String.valueOf(e.get("zbtah")))||"".equals(String.valueOf(e.get("zbtah")))){
                    day.setBglxdh("");
                }else{
                    day.setBglxdh(e.get("zbtah").toString());
                }
                //day.setBglxdh((String)e.get("zbtah"));
                //day.setDwmcZw();
                if("null".equals(String.valueOf(e.get("xbjs")))||"".equals(String.valueOf(e.get("xbjs")))){
                    day.setSjhm("0");
                }else{
                    day.setSjhm(e.get("xbjs").toString());
                }
                //day.setSjhm((String)e.get("xbjs"));
                if("null".equals(String.valueOf(e.get("xbtah")))||"".equals(String.valueOf(e.get("xbtah")))){
                    day.setFzr("");
                }else{
                    day.setFzr(e.get("xbtah").toString());
                }
                //day.setFzr((String)e.get("xbtah"));
                if("null".equals(String.valueOf(e.get("fbjs")))||"".equals(String.valueOf(e.get("fbjs")))){
                    day.setJstx("0");
                }else{
                    day.setJstx(e.get("xbtah").toString());
                }
                //day.setJstx((String)e.get("fbjs"));
                if("null".equals(String.valueOf(e.get("fbtah")))||"".equals(String.valueOf(e.get("fbtah")))){
                    day.setLxr("");
                }else{
                    day.setLxr(e.get("fbtah").toString());
                }
                //day.setLxr((String)e.get("fbtah"));
                if("null".equals(String.valueOf(e.get("yjjs")))||"".equals(String.valueOf(e.get("yjjs")))){
                    day.setTxdz("0");
                }else{
                    day.setTxdz(e.get("yjjs").toString());
                }
                //day.setTxdz((String)e.get("yjjs"));
                //day.setParentName();
                if("null".equals(String.valueOf(e.get("yjtah")))||"".equals(String.valueOf(e.get("yjtah")))){
                    day.setPwd("");
                }else{
                    day.setPwd(e.get("yjtah").toString());
                }
                //day.setPwd((String)e.get("yjtah"));
                if("null".equals(String.valueOf(e.get("ydfjs")))||"".equals(String.valueOf(e.get("ydfjs")))){
                    day.setSfz("");
                }else{
                    day.setSfz(e.get("yjtah").toString());
                }
                //day.setSfz((String)e.get("ydfjs"));

                resultValue.add(day);
            });
            LinkedHashMap<String, String> fieldMap=new LinkedHashMap<>();
            fieldMap.put("dwjc", "承办单位名称");
            fieldMap.put("dwmc", "主办件数");
            fieldMap.put("bglxdh", "主办提案号");
            fieldMap.put("sjhm", "协办件数");
            fieldMap.put("fzr", "协办提案号");
            fieldMap.put("jstx", "分办件数");
            fieldMap.put("lxr", "分办提案号");
            fieldMap.put("txdz", "意见数");
            fieldMap.put("pwd", "意见号");
            fieldMap.put("sfz", "应答复件数");



            String fieldName="提案承办单位汇总";
            try {
                ExcelUtil.listToExcel(resultValue, fieldMap, fieldName, response);
            } catch (ExcelException e) {
                e.printStackTrace();
            }

    }
}
