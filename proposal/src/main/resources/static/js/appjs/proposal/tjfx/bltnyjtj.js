
var prefix = "/proposal/bltnyjtj"
$(function() {
	load();
});


var queryParams = {};
function load() {
	
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/bltnYjtjList", // 服务器数据的加载地址
						cache : false,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : false, // 设置为true会在底部显示分页条
						singleSelect : false, // 设置为true将禁止多选
						//pageSize : 10, // 如果设置了分页，每页数据条数
						//pageNumber : 1, // 如果设置了分布，首页页码
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
							queryParams.limit = params.limit;
							queryParams.offset = params.offset;
							
							return queryParams;
						},
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
									title : '单位名称' 
								},
																{
									field : 'tah', 
									title : '提案号' 
								},
																{
									field : 'tahCount', 
									title : '提案数' 
								},
																{
									field : 'yjh', 
									title : '意见号' 
								},
																{
									field : 'yjhCount', 
									title : '意见数' 
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



$(function() {
	proposalUtil.setSelect('/proposal/zxjc/selectAllSelect',null,'zxjcid',null);
});




function bltnYjtj_dow(){
	//询问框
	layer.confirm('确认要导出吗？', {
		btn: ['确认','取消'] //按钮
	}, function(){
		//确认
		var action = "/proposal/bltnyjtj/bltnyjtj_downLoad";
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





