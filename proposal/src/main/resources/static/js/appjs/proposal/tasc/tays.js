
var prefix = "/proposal/tasc"
$(function() {
	var tastate = $("#tastate").val();
	if(tastate == '0'){
		s_ys_h = '';
		s_dc_h = '';
	}else if(tastate == '1'){
		s_fs_h = '';
		s_dc_h = '';
	}else if(tastate == '2'){
		s_zs_h = '';
		s_dc_h = '';
	}else if(tastate == '3'){
		$("#zjButton").removeClass("hidden");
		$("#bhButton").removeClass("hidden");
		$("#hbtaButton").removeClass("hidden");
		$("#cxbhButton").removeClass("hidden");
		$("#xyLaState").val("3");
		
		$("#lafsdxButton").removeClass("hidden");
		$("#zjfsdxButton").removeClass("hidden");
		$("#sdbhButton").removeClass("hidden");
		$("#qxbaButton").removeClass("hidden");
	}
	
	load();
	
	proposalUtil.setSelect('/proposal/zxjc/selectAllSelect',null,'zxjcid',null);
	proposalUtil.setSelect('/proposal/zxjc/selectAllSelect',null,'fadxDivZxjcid',null);
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
								offset:params.offset,
								order:params.order,//排序
								sort:params.sort,//排序字段
								tastate:$('#tastate').val(),
								tatm:"%"+$('#searchName').val()+"%",
								zxjcid:($("#zxjcid").val()==null ? "":$("#zxjcid").val()),
								tanx:($("#tanx").val()==null ? "":$("#tanx").val()),
								state:1,
								xyLaState:($("#xyLaState").val()==null ? "":$("#xyLaState").val()),
								lastate:$.trim($('#lastate').val()),
								lsh:$.trim($('#lsh').val()),
								tah:$.trim($('#tah').val()),
								mc:$.trim($('#taz').val())==""?"":"%"+$.trim($('#taz').val())+"%",
								cbdw:$.trim($('#cbdw').val())==""?"":"%"+$.trim($('#cbdw').val())+"%",
								xbdw:$.trim($('#xbdw').val())==""?"":"%"+$.trim($('#xbdw').val())+"%",
								zb_bin:($("#zb_bin").val()==null ? "":$("#zb_bin").val())
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
									field : 'mc', 
									title : '提案者♦',
									sortable : true
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
										return (value==null?"":value);
									}
								},
																{
									field : 'tatm', 
									title : '提案题目',
									width : 200
								},
																{
									field : 'createtime', 
									title : '创建时间' 
								},
								{
									field : 'tastate', 
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
																{
									title : '操作',
									field : 'option',
									align : 'center',
									formatter : function(value, row, index) {
										var btn_class = "btn-primary";
										if(row.gbsqCount!=null && row.gbsqCount>0){
											btn_class = "btn-danger";
										}
										
										var y = '<a class="btn btn-primary btn-sm '+s_ys_h+'" href="#" title="预审" mce_href="#" onclick="sh(\''
												+ row.id + '\',\'0'
												+ '\')"><i class="fa fa-edit"></i>预审</a> ';
										var f = '<a class="btn '+btn_class+' btn-sm '+s_fs_h+'" href="#" title="复审" mce_href="#" onclick="sh(\''
												+ row.id + '\',\'1'
												+ '\')"><i class="fa fa-edit"></i>复审</a> ';
										var z = '<a class="btn '+btn_class+' btn-sm '+s_zs_h+'" href="#" title="终审" mce_href="#" onclick="sh(\''
												+ row.id + '\',\'2'
												+ '\')"><i class="fa fa-edit"></i>终审</a> ';
										var dc = '<a class="btn btn-primary btn-sm '+s_dc_h+'" href="#" title="导出" mce_href="#" onclick="dc(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i>导出</a> ';
										
										return y + f + z + dc;
									}
								} ]
					});
}
function reLoad() {
	//$('#exampleTable').bootstrapTable('refresh');
	$('#exampleTable').bootstrapTable('refresh');
	layer.close(search_index);
	/*if(data==null){
	}else{
		var query = data;
		query.limit = 10;
		query.offset = 0;
		query.state = 1;
		$('#exampleTable').bootstrapTable('refresh',{
			query:query
		});
	}*/
}
function empty(){
	$("#lastate").val(" ");
	$('#lsh').val("");
	$('#tah').val("");
	$('#taz').val("");
	$('#cbdw').val("");
	$('#xbdw').val("");
	//$('#tatm').val("");
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
function showFadxDiv(type,str) {
	$("#fadxDivType").val(type);
	$("#fadxDivStr").val(str);
	search_index = layer.open({
		type : 1,
		title : '发送短信',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '500px', '270px' ],
		shadeClose: true,
		content : $("#fadxDiv") // iframe的url
	});
}
/*function search(){
	var data = $('#signupForm').serialize();
	var query = {};
	if(data!=null ){
		var a = data.split("&");
		for(var index in a){
			var b = a[index].split("=");
			if(b.length == 2 && b[1] != ""){
				query[b[0]] = "%"+b[1]+"%";
			}
		}
	}
	var zxjcid = $("#zxjcid").val();
	if(zxjcid!=null && zxjcid!=""){
		query.zxjcid = zxjcid;
	}
	
	var lastate = $("#lastate").val();
	if(lastate!=null && lastate!=""){
		query.lastate = lastate;
	}
	
	reLoad(query);
	
	layer.close(search_index);
}*/
function sh(id,tastate) {
	var shLayer = null;
	shLayer = layer.open({
		type : 2,
		title : '审核',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/sh/' + id + '/' + tastate// iframe的url
	});
	layer.full(shLayer);
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
function zjClick() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要转交的数据");
		return;
	}
	layer.confirm("确认要转交选中的'" + rows.length + "'条数据吗?", {
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
			url : prefix + '/zjta',
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

function bhClick() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要编号的数据");
		return;
	}
	layer.confirm("确认要对选中的'" + rows.length + "'条数据编号吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		//debugger
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
			url : prefix + '/bxtah',
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
//重新编号
function cxbhClick() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要编号的数据");
		return;
	}
	layer.confirm("确认要对选中的'" + rows.length + "'条数据进行重新编号吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		//debugger
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
			url : prefix + '/cxbxtah',
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
//手动编号
function sdbhClick(){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length != 1) {
		layer.msg("请选择一条要编号的数据");
		return;
	}
	layer.prompt({
		formType: 2,
		value: '',
		title: '请输入提案号'
	}, function(value, index, elem){
		if(isNaN(value)){
			layer.msg("请输入数字");
			return;
		}
		layer.close(index);
		//layer.alert(value);
		$.ajax({
			type : 'POST',
			data : {
				"id" : rows[0].id,
				"tah" : value
			},
			url : prefix + '/sdbhtah',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	});
	
}


function hbtaClick() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要合并的数据");
		return;
	}
	layer.confirm("确认要对选中的'" + rows.length + "'条数据进行合并操作吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		layer.closeAll('dialog');
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		
		$.ajax({//判断是否可以并案
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/contrastCbdw',
			success : function(r) {
				if (r.code == 0) {//可以并案
					layer.open({
						type : 2,
						title : '合并提案',
						maxmin : true,
						shadeClose : false, // 点击遮罩关闭层
						area : [ '800px', '520px' ],
						content : prefix + '/hbta/' + ids.toString() // iframe的url
					});
				} else {
					layer.msg(r.msg);
				}
			}
		});
		
	}, function() {

	});
}

function qxbaClick(){
	var rows = $('#exampleTable').bootstrapTable('getSelections');
	if (rows.length != 1) {
		layer.msg("请选择一条数据");
		return;
	}
	layer.confirm("确认要取消并案吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		layer.closeAll('dialog');
		$.ajax({//判断是否可以并案
			type : 'POST',
			data : {
				"id" : rows[0].id
			},
			url : prefix + '/qxba',
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

function fsdx() {
	var str = $("#fadxDivStr").val();
	var zxjcmc = "["+$("#fadxDivZxjcid option:selected").html()+"]";
	layer.confirm("确认要发送"+zxjcmc+str+"的短信吗1?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		//debugger
		layer.confirm("确认要发送"+zxjcmc+str+"的短信吗2?", {
			btn : [ '取消', '确定' ]
		// 按钮
		}, function() {
			//debugger
			layer.close(layer.index);
		}, function() {
			layer.confirm("确认要发送"+zxjcmc+str+"的短信吗3?", {
				btn : [ '确定', '取消' ]
			// 按钮
			}, function() {
				//debugger
				$.ajax({
					type : 'POST',
					data : {
						"zxjcid" : $("#fadxDivZxjcid").val(),
						"type" : $("#fadxDivType").val()
					},
					url : '/proposal/xtgj/fsdx',
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
		});
	}, function() {

	});
}