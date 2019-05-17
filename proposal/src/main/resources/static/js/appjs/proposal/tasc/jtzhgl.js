
var prefix = "/proposal/tasc"
$(function() {
	load();
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
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								tanx:2
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
									field : 'lsh', 
									title : '流水号' 
								},
																{
									field : 'tah', 
									title : '提案号' 
								},
																{
									field : 'zxjcmc', 
									title : '届次'
								},
																{
									field : 'tatm', 
									title : '提案题目',
									formatter : function(value, row, index) {
										if(row.isZdta == 1){
											return value+"[重点]"
										}
										return value;
									}
								},
																{
									field : 'dwmc', 
									title : '单位' 
								},
																{
									field : 'txdz', 
									title : '通讯地址' 
								},
																{
									field : 'yb', 
									title : '邮编' 
								},
																{
									field : 'fzr', 
									title : '负责人' 
								},
																{
									field : 'lxr', 
									title : '邮编' 
								},
																{
									field : 'dzyx', 
									title : '电子邮箱' 
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
											return "已转交";
										}else if(value=="5"){
											return "已提交复函意见稿";
										}else if(value=="6"){
											return "已提交正式复函回执";
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
									title : '承办单位' 
								},
																{
									field : 'xbdw', 
									title : '协办单位' 
								},
																{
									field : 'fbdw', 
									title : '分办单位' 
								}
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
										var z = '<a class="btn btn-primary btn-sm '+s_xgtah_h+'" href="#" title="修改提案号" mce_href="#" onclick="a(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i>修改提案号</a> ';
										var c = '<a class="btn btn-primary btn-sm '+s_ck_h+'" href="#" title="查看" mce_href="#" onclick="chakan(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i>查看</a> ';
								
								
								return y + f + z + c;
									}
								}*/]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function getIdImplementFun(fun){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length != 1) {
		layer.msg("请选择一条数据");
		return;
	}
	fun(rows[0].id);
}

function chakan(id,tastate) {
	layer.open({
		type : 2,
		title : '审核',
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
				"id" : id
			},
			url : prefix + '/thta',
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
	    
	    form.appendTo("body");  
	    form.css('display', 'none');  
	    form.submit();
		
	    layer.msg('正在导出...');
	}, function() {

	});
}