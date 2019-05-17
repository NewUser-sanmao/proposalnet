$().ready(function() {
	load();
});

function load() {
	var queryParams = {};
	queryParams.taxxid = $("#taxxid").val();
	$('#fhTable') .bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : "/proposal/tabl/fhhzList", // 服务器数据的加载地址
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
									field : 'lsh', 
									title : '流水号' 
								},
																{
									field : 'tah', 
									title : '提案号' 
								},
																{
									field : 'bt', 
									title : '复函标题' 
								},
																{
									field : 'nr', 
									title : '复函内容',
									formatter: function(value, row, index){
										
										return value;
									}
								},
																{
									field : 'type', 
									title : '复函类型',
									formatter: function(value, row, index){
										if(value == 1){
											return "意见稿";
										}else if(value == 2){
											return "正式回执";
										}
										return "";
									}
								} ]
					});
}

function reLoad() {
	$('#fhTable').bootstrapTable('refresh');
}

function addFh(id) {
	parent.layer.open({
		type : 2,
		title : '查看',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/proposal/tabl/addFh/' + $("#taxxid").val()// iframe的url
	});
}