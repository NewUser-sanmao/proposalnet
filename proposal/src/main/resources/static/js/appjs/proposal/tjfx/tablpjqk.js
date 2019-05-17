
var prefix = "/proposal/tjfx"
$(function() {
	load();
	proposalUtil.setSelect('/proposal/zxjc/selectAllSelect',null,'zxjcid',null);
});
var lastOrder = "asc";
var lastSort = "p1.tah";
var queryParams = {};
function load() {
	if($("#zxjcid").val() != ""){
		queryParams.zxjcid = $("#zxjcid").val();
	}
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/tablpjqkList", // 服务器数据的加载地址
						cache : false,
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : false, // 设置为true会在底部显示分页条
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
//							queryParams.limit = params.limit;
//							queryParams.offset = params.offset;
							queryParams.order=params.order;//排序
							queryParams.sort=params.sort;//排序字段
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
									field : 'name', 
									title : '办理单位'
								},
																{
									field : 'js', 
									title : '主分办件数'
								},
																{
									field : 'pjf', 
									title : '平均分'
								},
																{
									field : 'my', 
									title : '满意件数'
								},
																{
									field : 'jbmy', 
									title : '基本满意件数'
								},
																{
									field : 'bmy', 
									title : '不满意件数'
								} ]
					});
}
function reLoad() {
	if($("#zxjcid").val() != ""){
		queryParams.zxjcId = $("#zxjcid").val();
	}else{
		queryParams.zxjcId = null;
	}
	$('#exampleTable').bootstrapTable('refresh');
}

function dc(){
	//询问框
	layer.confirm('确认要导出吗？', {
		btn: ['确认','取消'] //按钮
	}, function(){
		//确认
		var action = "/proposal/tjfx/taflzhtjDc";
	    var form = $("<form></form>");  
	    form.attr('action', action);  
	    form.attr('method', 'post');   
	    form.attr('target', '_blank');
	    
	    if($("#zxjcid").val() != ""){
	    	var input1 = $("<input type='hidden' name='zxjcId' />");  
	    	input1.attr('value', $("#zxjcid").val());  
	    	form.append(input1);
	    }
	    
	    form.appendTo("body");  
	    form.css('display', 'none');  
	    form.submit();
		
	    layer.msg('正在导出...');
	}, function(){
		//取消
	});
}

