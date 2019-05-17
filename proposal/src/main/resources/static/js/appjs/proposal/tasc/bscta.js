
var prefix = "/proposal/tasc"
$(function() {
	/*var tastate = $("#tastate").val();
	if(tastate == '0'){
		s_ys_h = '';
	}else if(tastate == '1'){
		s_fs_h = '';
	}else if(tastate == '2'){
		s_zs_h = '';
	}else if(tastate == '3'){
		$("#zjButton").removeClass("hidden");
	}*/
	
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
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset: params.offset,
								state:0,
								tanx: ($("#tanx").val()==null ? "":$("#tanx").val()),
								zxjcid: ($("#zxjcid").val()==null ? "":$("#zxjcid").val()),
								lastate:($("#lastate").val()==null ? "":$("#lastate").val())
								
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
									title : '流水号' 
								},
																{
									field : 'tah', 
									title : '提案号/意见号',
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
									title : '提案者',
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
									title : '提案题目' ,
									formatter : function(value, row, index) {
										if(row.isZdta == 1){
											return "<a href='javascript:void(0);' onclick='chakan("+row.id+");'>"+value+"</a>"+"[重点]"
										}
										return "<a href='javascript:void(0);' onclick='chakan("+row.id+");'>"+value+"</a>";
									}
								},
																{
									field : 'talx', 
									title : '分类'
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
								}]
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
function batchRecovery() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要恢复的数据");
		return;
	}
	layer.confirm("确认要恢复选中的'" + rows.length + "'条数据吗?", {
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
			url : prefix + '/batchRecovery',
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

