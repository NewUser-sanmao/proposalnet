
var prefix = "/proposal/tasc";
$(function() {
	var tastate = $("#tastate").val();
	if(tastate == '0'){
		s_ys_h = '';
	}else if(tastate == '1'){
		s_fs_h = '';
	}else if(tastate == '2'){
		s_zs_h = '';
	}else if(tastate == '3'){
		$("#zjButton").removeClass("hidden");
	}
	
	load();
	
	proposalUtil.setSelect('/proposal/zxjc/selectAllSelect',null,'zxjcid',null);
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/zhglList", // 服务器数据的加载地址
						cache : false,
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						pageList: [10, 25, 50, 100, 500, 2000],
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							if(params.sort=="lsh"){
								params.sort = "lsh+0";
							}else if(params.sort=="tah"){
								params.sort = "tah+0";
							}
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset: params.offset,
								order:params.order,//排序
								sort:params.sort,//排序字段
								state:1,
								tatm: ($("#searchName").val()==null ? "":"%"+$("#searchName").val()+"%"),
								tanx: ($("#tanx").val()==null ? "":$("#tanx").val()),
								zxjcid: ($("#zxjcid").val()==null ? "":$("#zxjcid").val()),
								lastate:($("#lastate").val()==null ? "":$("#lastate").val()),
								lsh:$.trim($('#lsh').val()),
								tah:$.trim($('#tah').val()),
								mc:$.trim($('#taz').val())==""?"":"%"+$.trim($('#taz').val())+"%",
								cbdw:$.trim($('#cbdw').val())==""?"":"%"+$.trim($('#cbdw').val())+"%",
								xbdw:$.trim($('#xbdw').val())==""?"":"%"+$.trim($('#xbdw').val())+"%",
								zb_bin:($("#zb_bin").val()==null ? "":$("#zb_bin").val())
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true
								},
																{
									field : 'id', 
									title : '',
									visible: false
								},
																{
									field : 'tanx', 
									title : '提案类型',
									formatter : function(value, row, index) {
										if(value == 1){
											return "个人提案";
										}
										return "集体提案";
									}
								},
																{
									field : 'lsh', 
									title : '流水号♦',
									sortable : true
								},
																{
									field : 'tah', 
									title : '提案号/意见号♦',
									sortable : true,
									formatter : function(value, row, index) {
										if(row.isBa == 1 && row.baId==null){
											return (value==null?"":value)+"<span style='color: #2de143;'>[主并]</span>";
										}else if(row.isBa == 1 && row.baId!=null){
											return (value==null?"":value)+"<span style='color: #ff0000;'>[被并]</span>";
										}
										return value;
									}
								},
																{
									field : 'zxjcmc', 
									title : '届次'
								},
																{
									field : 'tanx', 
									title : '提案者♦',
									sortable : true,
									formatter : function(value, row, index) {
										var str = row.dwmc;
										if(value == 1){
											str = row.xm;
										}
										if(str == undefined){
											str = "";
										}
										return '<a href="#" onclick="readTaxxCreateUserInfo('+row.id+')">'+str+'</a>';
									}
								},
																{
									field : 'tatm', 
									title : '提案题目♦' ,
									width : 200,
									sortable : true,
									formatter : function(value, row, index) {
										if(row.isZdta == 1){
											return "<a href='javascript:void(0);' onclick='chakan("+row.id+");'>"+value+"</a>"+"[重点]"
										}
										return "<a href='javascript:void(0);' onclick='chakan("+row.id+");'>"+value+"</a>";
									}
								},
																{
									field : 'talx', 
									title : '分类',
									formatter : function(value, row, index){
										var s = value.split("：");
										return '<span title="'+s[1]+'">'+s[0]+'</span>';
									}
								},
								{
									field : 'taState', 
									title : '提案状态',
									formatter : function(value, row, index) {
										if(value=="0"){
											return "已提交未审核";
										}else if(value=="1"){
											return "预审通过";
										}else if(value=="2"){
											return "复审通过";
										}else if(value=="3"){
											return "终审通过";
										}else if(value=="4"){
											if(row.gbsqCount > 0){
												return "<span style='color: #ff0000;'>已转交</span>";
											}else{
												return "已转交";
											}
										}else if(value=="5"){
											return "已提交复函征求意见稿";
										}else if(value=="6"){
											return "已提交正式复函";
										}
										return "";
									}
								},
								{
									field : 'laState', 
									title : '立案状态',
									formatter : function(value, row, index) {
										if(value=="0"){
											return "不立案";
										}else if(value=="1"){
											return "立案";
										}else if(value=="2"){
											return "不立案转意见";
										}else if(value=="3"){
											return "不立案退回";
										}
										
										return "";
									}
								},
																{
									field : 'cbdw', 
									title : '承办单位♦',
									sortable : true
								},
																{
									field : 'xbdw', 
									title : '协办单位♦',
									sortable : true
								}
								/*,
																{
									field : 'fbdw', 
									title : '分办单位' 
								}*/
								/*,
								{
									title : '操作',
									field : 'option',
									align : 'center',
									formatter : function(value, row, index) {
										var y = '<a class="btn btn-primary btn-sm '+s_thta_h+'" href="#" title="退回提案" mce_href="#" onclick="thta(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i>退回提案</a> ';
										var f = '<a class="btn btn-primary btn-sm '+s_zdta_h+'" href="#" title="重点提案" mce_href="#" onclick="zdta(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i>重点提案</a> ';
										var c = '<a class="btn btn-primary btn-sm '+s_ck_h+'" href="#" title="查看" mce_href="#" onclick="chakan(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i>查看</a> ';
										var dc = '<a class="btn btn-primary btn-sm" href="#" title="导出提案" mce_href="#" onclick="dc(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i>导出提案</a> ';
										
										return y + f + z + c +dc;
									}
								}*/ ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
	layer.close(search_index);
}
var search_index = null;
function showSearch() {
	search_index = layer.open({
		type : 1,
		title : '查询',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		shadeClose: true,
		content : $("#search") // iframe的url
	});
}
function empty(){
	$('#lsh').val("");
	$('#tah').val("");
	$('#taz').val("");
	$('#cbdw').val("");
	$('#xbdw').val("");
	//$('#tatm').val("");
}
function getIdImplementFun(fun){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length != 1) {
		layer.msg("请选择一条数据");
		return;
	}
	fun(rows[0].id);
}


function showBjta(id) {
	layer.open({
		type : 2,
		title : '修改提案',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/xg/' + id // iframe的url
	});
}

function chakan(id) {
	layer.open({
		type : 2,
		title : '查看提案信息',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/chakan/' + id // iframe的url
	});
}

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}
function zdta(id) {
	layer.confirm("确认把该提案设置为重点提案吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.ajax({
			type : 'POST',
			data : {
				"id" : id
			},
			url : prefix + '/zdta',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}

function thta(id) {
	layer.confirm("确认退回该提案?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.ajax({
			type : 'POST',
			data : {
				"id" : id,
				"type" : $("input[name='thta_type']:checked").val()
			},
			url : prefix + '/thta',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
					layer.close(dcnr_index);
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}
function dc(id){
	layer.confirm('确认要导出吗？', {
		btn: ['确认','取消'] //按钮
	}, function(){
		//确认
		var action = "/proposal/taxx/exportWord";
	    var form = $("<form></form>");  
	    form.attr('action', action);  
	    form.attr('method', 'post');   
	    form.attr('target', '_blank');
	    
    	var input1 = $("<input type='hidden' name='id' />");  
    	input1.attr('value', id);  
    	form.append(input1);
	    
	    form.appendTo("body");  
	    form.css('display', 'none');  
	    form.submit();
		
	    layer.msg('正在导出...');
	}, function(){
		//取消
	});
}

function dcArr() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要导出的数据");
		return;
	}
	layer.confirm("确认要导出选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = "";
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids += row['id']+",";
		});
		if(ids != ""){
			ids = ids.substring(0,ids.length-1);
		}
		
		var action = "/proposal/taxx/exportWordArr";
	    var form = $("<form></form>");  
	    form.attr('action', action);  
	    form.attr('method', 'post');   
	    form.attr('target', '_blank');
	    
    	var input1 = $("<input type='hidden' name='idStr' />");  
    	input1.attr('value', ids);  
    	form.append(input1);
    	
    	var input2 = $("<input type='hidden' name='type' />");  
    	input2.attr('value', $("input[name='dcnr_type']:checked").val());  
    	form.append(input2);
	    
	    form.appendTo("body");  
	    form.css('display', 'none');  
	    form.submit();
		
	    layer.msg('正在导出...');
	    layer.close(dcnr_index);
	}, function() {

	});
}

function readTaxxCreateUserInfo(taxxId) {
	layer.open({
		type : 2,
		title : '查看委员信息',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/proposal/cbdw/readTaxxCreateUserInfo?id='+taxxId // iframe的url
	});
}

function dcArrFh() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要导出的数据");
		return;
	}
	layer.confirm("确认要导出选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = "";
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids += row['id']+",";
		});
		if(ids != ""){
			ids = ids.substring(0,ids.length-1);
		}
		
		var action = "/proposal/taxx/exportWordArrFh";
	    var form = $("<form></form>");  
	    form.attr('action', action);  
	    form.attr('method', 'post');   
	    form.attr('target', '_blank');
	    
    	var input1 = $("<input type='hidden' name='idStr' />");  
    	input1.attr('value', ids);  
    	form.append(input1);
	    
    	var input2 = $("<input type='hidden' name='type' />");  
    	input2.attr('value', $("input[name='fhdc_type']:checked").val());  
    	form.append(input2);
    	
	    form.appendTo("body");  
	    form.css('display', 'none');  
	    form.submit();
		
	    layer.msg('正在导出...');
	    layer.close(dcnr_index);
	}, function() {

	});
}

var dcnr_index = null;
function showDcnr() {
	dcnr_index = layer.open({
		type : 1,
		title : '导出提案内容',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		shadeClose: true,
		content : $("#dcnr") // iframe的url
	});
}
function showFhdc() {
	dcnr_index = layer.open({
		type : 1,
		title : '导出复函',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		shadeClose: true,
		content : $("#fhdc") // iframe的url
	});
}
function showThta() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length != 1) {
		layer.msg("请选择一条需要退回的数据");
		return;
	}
	dcnr_index = layer.open({
		type : 1,
		title : '退回提案',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '500px', '260px' ],
		shadeClose: true,
		content : $("#thta") // iframe的url
	});
}

function zcbl() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要再次办理的数据");
		return;
	}
	layer.confirm("确认要再次办理选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ls_bl = false;
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			if(row.taState!=6){
				ls_bl = true;
				return false;
			}
			if(row.isBa == 1 && row.baId!=null){//被并的不能操作
				//
			}else{
				ids[i] = row['id'];
			}
		});
		
		if(ls_bl){
			layer.msg("再次办理的数据必须是'已提交正式复函回执'的数据");
			return;
		}
		
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/zcbl',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}