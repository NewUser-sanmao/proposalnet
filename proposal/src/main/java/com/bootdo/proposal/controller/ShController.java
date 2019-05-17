package com.bootdo.proposal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bootdo.proposal.service.GrwyService;
import com.bootdo.proposal.service.LbyblsxService;
import com.bootdo.proposal.service.SswyhService;
import com.bootdo.proposal.service.TaxxService;
import com.bootdo.proposal.service.ZxjcService;

/**
 * 审核
 * 
 * @author shipan
 * @email 
 * @date 2018-10-17 14:16:06
 */
 
@Controller
@RequestMapping("/proposal/sh")
public class ShController {
	@Autowired
	private TaxxService taxxService;
	@Autowired
	private GrwyService grwyService;
	@Autowired
	private SswyhService sswyhService;
	@Autowired
	private ZxjcService zxjcService;
	@Autowired
	private LbyblsxService lbyblsxService;
	
	
	
}
