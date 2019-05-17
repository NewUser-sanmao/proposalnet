
var prefix = "/proposal/cbdw"
$(function() {
	proposalUtil.setSelect('/proposal/zxjc/selectAllSelect',null,'zxjcid',null);
	
	load();
	loadTreeview();
});

var queryParams = {};
function load() {
	$('#exampleTable')
	.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : "/proposal/cbdw/cbdwTaglList", // 服务器数据的加载地址
				cache : false,
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
					queryParams.lastate = 1;
					queryParams.zxjcid = $("#zxjcid").val();
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
							field : 'tanx', 
							title : '提案者',
							formatter : function(value, row, index) {
								var str = "";
								if(value == 1){
									str = row.taz;
								}else{
									str = row.dwmc;
								}
								return '<a href="#" onclick="readTaxxCreateUserInfo('+row.id+')">'+str+'</a>';
							}
						},
														{
							field : 'lsh', 
							title : '流水号' 
						},
														{
							field : 'tah', 
							title : '提案号',
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
							title : '提案题目',
							formatter : function(value, row, index) {
								///debugger
								if(row.tastate < 4){
									return "<span style='color: #ffc002;'>[预]</span>"+'<a href="#" onclick="readTaxx('+row.id+')">'+value+'</a>';
								}
								return '<a href="#" onclick="readTaxx('+row.id+')">'+value+'</a>';
							}
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
								}else if(value=="5"){
									return "已提交复函意见稿";
								}else if(value=="6"){
									return "已提交正式复函";
								}
								return "";
							}
						},
						{
							field : 'lastate', 
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
						}
						/*								{
							field : 'fbdw', 
							title : '分办单位' 
						},*/
						 ]
			});
	
}

function selectOnchange(){
	reLoad();
	loadTreeview();
}

function loadTreeview(){
	$.ajax({
		url : prefix+"/getTreeData?zxjcid="+($("#zxjcid").val()==null ? "" : $("#zxjcid").val()),
		type : "get",
		success : function(r) {
			$('#tree').treeview({
				height:600,
				data: r,
				onNodeSelected:function(event, node){
					//node.id;
					reLoad(node.id);
				}
			});
		}
	});
}
function reLoad(cbdwId) {
	var data = {};
	if(cbdwId!=null){
		data.cbdwId2 = cbdwId;
	}
	$('#exampleTable').bootstrapTable('refresh',{query:data});
}
function readTaxxCreateUserInfo(taxxId) {
	layer.open({
		type : 2,
		title : '查看委员信息',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/readTaxxCreateUserInfo?id='+taxxId // iframe的url
	});
}

function readTaxx(id) {
	layer.open({
		type : 2,
		title : '查看提案信息',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/proposal/tasc/chakan/' + id // iframe的url
	});
}