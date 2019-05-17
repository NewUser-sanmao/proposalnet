package com.bootdo.proposal.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bootdo.proposal.domain.LxEnumDO;
import com.bootdo.proposal.service.LxEnumService;

@Component
@Order(value = 1)
public class LxEnumMapUtil implements CommandLineRunner {
	
	/**
	 * 提案类型
	 */
	public static Map<Integer,String> talx = new HashMap<>();//1
	/**
	 * 提案状态
	 */
	public static Map<Integer,String> tazt = new HashMap<>();//2
	/**
	 * 提案立案状态
	 */
	public static Map<Integer,String> talazt = new HashMap<>();//3
	
	@Autowired
	private LxEnumService lxEnumService;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		List<LxEnumDO> list = lxEnumService.list(map);
		for(LxEnumDO l : list) {
			if(l.getType().intValue() == 1) {
				talx.put(l.getMark(), l.getName());
			}else if(l.getType().intValue() == 2) {
				tazt.put(l.getMark(), l.getName());
			}else if(l.getType().intValue() == 3) {
				talazt.put(l.getMark(), l.getName());
			}
		}
	}
	
	
	
}
