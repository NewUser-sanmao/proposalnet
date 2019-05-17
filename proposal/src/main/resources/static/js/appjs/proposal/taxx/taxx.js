
var prefix = "/proposal/taxx"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
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
							if(params.sort=="lsh"){
								params.sort = "lsh+0";
							}else if(params.sort=="tah"){
								params.sort = "tah+0";
							}
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								order:params.order,//排序
								sort:params.sort,//排序字段
								tatm:$('#searchName').val(),
								//state:1,
								pfType:2
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
									title : '流水号♦',
									sortable : true
								},
																{
									field : 'tah', 
									title : '提案号/意见号♦',
									sortable : true,
									formatter : function(value, row, index) {
										if(row.isba == 1 && row.baid==null){
											return (value==null?"":value)+"<span style='color: #2de143;'>[主并]</span>";
										}else if(row.isba == 1 && row.baid!=null){
											return (value==null?"":value)+"<span style='color: #ff0000;'>[被并]</span>";
										}
										return value;
									}
								},
																{
									field : 'tatm', 
									title : '提案题目♦',
									sortable : true
								},
																{
									field : 'createtime', 
									title : '创建时间♦',
									sortable : true
								},
																{
									field : 'tastate', 
									title : '提案状态',
									formatter : function(value, row, index) {
										if(row.state == 0){
											return "<span style='color: #ff0000;'>被删除</span>";
										}else{
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
												return "已提交复函征求意见稿";
											}else if(value=="6"){
												return "已提交正式复函";
											}
										}
										return "";
									}
								},
								{
									field : 'lastate', 
									title : '立案状态',
									formatter : function(value, row, index) {
										if(row.tastate=="0"){
											return "";
										}else{
											if(value=="0"){
												return "不立案";
											}else if(value=="1"){
												return "立案";
											}else if(value=="2"){
												return "不立案转意见";
											}else if(value=="3"){
												return "不立案退回";
											}
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
								},
								/*								{
									field : 'fbdw', 
									title : '分办单位' 
								},*/
								{
									field : 'yjdz', 
									title : '复函意见稿',
									formatter : function(value, row, index) {
										var ls_str = '';
										if(row.yjdz!=null && row.yjmc!=null){
											var dz = row.yjdz.split(',');
											var mc = row.yjmc.split(',');
											for(var i in dz){
												ls_str += '<a href="'+dz[i]+'" download="'+mc[i]+'">'+mc[i]+'</a>';
											}
										}
										return ls_str;
									}
								},
								{
									field : 'hzdz', 
									title : '正式复函',
									formatter : function(value, row, index) {
										var ls_str = '';
										if(row.hzdz!=null && row.hzmc!=null){
											var dz = row.hzdz.split(',');
											var mc = row.hzmc.split(',');
											for(var i in dz){
												ls_str += '<a href="'+dz[i]+'" download="'+mc[i]+'">'+mc[i]+'</a>';
											}
										}
										return ls_str;
									}
								},
																{
									title : '操作',
									field : 'option',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit">编辑</i></a> ';
										/*var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';*/
										var gt = '';
										if(row.tastate >= 4){
											var ls_str = 'btn-primary';
											if(row.xxCount > 0){
												ls_str = 'btn-danger';
											}
											gt = '<a class="btn '+ls_str+' btn-sm '+s_gt_h+'" href="#" mce_href="#" title="交流" onclick="gt(\''
											+ row.id
											+ '\')"><i class="fa fa-edit">交流</i></a> ';
										}
										var pingfen = '';
										
										if(row.tastate == 6){
											if(!(row.isba == 1 && row.baid!=null)){
												var ls_str = 'btn-primary';
												if(row.pfCount == 0){
													ls_str = 'btn-danger';
												}
												pingfen = '<a class="btn '+ls_str+' btn-sm '+s_pf_h+'" href="#" mce_href="#" title="评分" onclick="pf(\''
														+ row.id + '\',\'' + 2
														+ '\')"><i class="fa fa-edit">评分</i></a> ';
											}
										}
										return e + gt + pingfen;
									}
								} ]
					});
}
function reLoad(data) {
	if(data==null){
		$('#exampleTable').bootstrapTable('refresh');
	}else{
		var query = data;
		query.limit = 10;
		query.offset = 0;
		query.state = 1;
		$('#exampleTable').bootstrapTable('refresh',{
			query:query
		});
	}
	
}
function add() {
	$.ajax({
		url : prefix+"/judgeZxjcDate",//判断是否允许新增提案
		type : "post",
		data : {
		},
		success : function(r) {
			/*console.log(r);*/
			if (r.code==0) {
				layer.open({
					type : 2,
					title : '增加',
					maxmin : true,
					shadeClose : false, // 点击遮罩关闭层
					area : [ '800px', '520px' ],
					content : prefix + '/add' // iframe的url
				});
			}else{
				layer.alert(r.msg, {icon: 5});
				//layer.msg();
			}
		}
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function gt(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/proposal/xx/1?taxxid='+id // iframe的url
	});
}
function pf(id,type) {
	layer.open({
		type : 2,
		title : '委员评分',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/proposal/tabl/pf/' + id +'/'+type// iframe的url
	});
}

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

function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
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
			url : prefix + '/realRemove',
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