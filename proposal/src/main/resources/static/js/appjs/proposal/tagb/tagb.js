
var prefix = "/proposal/tagb"
$(function() {
	load();
});

function load() {
	var queryParams = {};
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
							//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
							queryParams.limit = params.limit;
							queryParams.offset = params.offset;
							return queryParams;
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
									field : 'taxxId', 
									title : 'taxxId',
									visible: false
								},
																{
									field : 'dwmc', 
									title : '申请承办单位' 
								},
																{
									field : 'ly', 
									title : '申请改办原因' 
								},
																{
									field : 'lsh', 
									title : '流水号' 
								},
																{
									field : 'tah', 
									title : '提案号',
									formatter : function(value, row, index) {
										if(row.laState==2){
											return (value==null?"":value)+"<span style='color: #ffc107;'>[意见]</span>";
										}
										return value;
									}
								},
																{
									field : 'cbdw1', 
									title : '现承办单位' 
								},
																{
									field : 'xbdw1', 
									title : '现协办单位' 
								},
																{
									field : 'fbdw1', 
									title : '现分办单位' 
								},
																{
									field : 'cbdw2', 
									title : '建议承办单位' 
								},
																{
									field : 'xbdw2', 
									title : '建议协办单位' 
								},
																{
									field : 'fbdw2', 
									title : '建议分办单位' 
								},
																{
									field : 'type', 
									title : '状态',
									formatter : function(value, row, index) {
										if(value == 0){
											return "未处理";
										}else if(value == 1){
											return "同意申请";
										}else if(value == 2){
											return "不同意申请";
										}else if(value == 3){
											return "手动处理完成";
										}
										return "";
									}
								},
																{
									title : '操作',
									field : 'option',
									align : 'center',
									formatter : function(value, row, index) {
										var html = '';
										if(row.type == 0){
											var a = '<a class="btn btn-primary btn-sm '+s_ty_h+'" href="#" title="同意" mce_href="#" onclick="update(\''
													+ row.id + '\',\'1'
													+ '\')"><i class="fa fa-edit"></i>同意</a> ';
											var b = '<a class="btn btn-primary btn-sm '+s_bty_h+'" href="#" title="不同意" mce_href="#" onclick="update(\''
													+ row.id + '\',\'2'
													+ '\')"><i class="fa fa-edit"></i>不同意</a> ';
											html = html + a + b;
										}
										var c = '<a class="btn btn-primary btn-sm '+s_xgyta_h+'" href="#" title="修改原提案" mce_href="#" onclick="xgyta(\''
												+ row.taxxId + '\',\'' + row.id
												+ '\')"><i class="fa fa-edit"></i>修改承办单位</a> ';
										html += c;
										return html ;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function update(id,type) {
	layer.confirm("确认同意该申请吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		$.ajax({
			type : 'POST',
			data : {
				"id" : id,
				"type" : type
			},
			url : prefix + '/update',
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
function xgyta(taxxid,id) {
	layer.open({
		type : 2,
		title : '修改原提案',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/xgyta/' + taxxid +'/'+ id// iframe的url
	});
}

function resetPwd(id) {
}
