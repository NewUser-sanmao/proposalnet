
var prefix = "/proposal/tabl"
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
	
});

function load() {
	var queryParams = {};
	var type = $("#type").val();

	if(type==0){
		queryParams.taxxCbdwType = "1";
		queryParams.cbdwIsNotNull = "1";
	}else if(type==1){
		queryParams.taxxCbdwType = "3";
		queryParams.xbdwIsNotNull = "3";
	}else if(type==2){
		queryParams.taxxCbdwType = "2";
		queryParams.fbdwIsNotNull = "2";
	}
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
							queryParams.pfType = 1;
							queryParams.state = 1;
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
									title : '提案号/意见号',
									formatter : function(value, row, index) {
										if(row.isba == 1 && row.baid==null){
											return (value==null?"":value)+"<span style='color: #2de143;'>[主并]</span>";
										}else if(row.isba == 1 && row.baid!=null){
											return (value==null?"":value)+"<span style='color: #ff0000;'>[被并]</span>";
										}else if(row.lastate==2){
											return (value==null?"":value)+"<span style='color: #ffc107;'>[意见]</span>";
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
											return "<span style='color: #ffc002;'>[预]</span>"+value;
										}
										return value;
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
										}else if(value=="5" || row.cbdwjd == "5"){
											return "已提交复函征求意见稿";
										}else if(value=="6" || row.cddwjd == "6"){
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
									title : '承办单位' ,
									formatter : function(value,row,index){
									return value;
									}
								},
																{
									field : 'xbdw', 
									title : '协办单位' 
								},
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
										console.log(row);
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
								/*								{
									field : 'fbdw', 
									title : '分办单位' 
								},*/
																{
									field : 'str1', 
									title : '是否改办',
									formatter : function(value, row, index) {
										//0未处理 1同意 2不同意 3手动处理完成
										if(value == 0){
											return "正在处理中";
										}else if(value == 1 || value == 3){
											return "申请失败";
										}else if(value == 2){
											return "不同意申请";
										}
										return "否";
									}
								},
																{
									title : '操作',
									field : 'option',
									align : 'center',
									formatter : function(value, row, index) {
										var ls_str = 'btn-primary';
										if(row.xxCount > 0){
											ls_str = 'btn-danger';
										}


										var gt = '<a class="btn '+ls_str+' btn-sm '+s_gt_h+'" href="#" mce_href="#" title="交流" onclick="gt(\''
											+ row.id + '\',\''+type
											+'\')"><i class="fa fa-edit">交流</i></a> ';
										var b = '<a class="btn btn-primary btn-sm '+s_bl_h+'" href="#" title="查看" mce_href="#" onclick="bl(\''
											+ row.id
											+ '\')"><i class="fa fa-edit"></i>查看</a> ';
										var s = '';
										if(row.str1 == null || row.str1==""){
											s = '<a class="btn btn-primary btn-sm '+s_sqgb_h+'" href="#" title="申请改办" mce_href="#" onclick="sqgb(\''
											+ row.id
											+ '\')"><i class="fa fa-edit"></i>申请改办</a> ';
										}
										b += s;

										//2019-04-03 新增限制 协办单位直接通过交流与承办单位进行沟通，不直接复函给提案者

										if(row.lastate == 1 && type != 1 ){
											var fhyjg = '';


											if(row.tastate == 4 ){
												fhyjg = '<a class="btn btn-primary btn-sm '+s_fhyjg_h+'" href="#" mce_href="#" title="复函意见稿" onclick="fhyjg(\''
												+ row.id + '\',\'' + 1
												+ '\')"><i class="fa fa-edit">复函意见稿</i></a> ';
											}

											var zsfhhz = '';
											if(row.tastate == 5 ){//只有提交完复函意见稿的时候可以修改
												zsfhhz = '<a class="btn btn-primary btn-sm '+s_zsfhhz_h+'" href="#" mce_href="#" title="正式复函" onclick="fhyjg(\''
												+ row.id + '\',\'' + 2
												+ '\')"><i class="fa fa-edit">正式复函</i></a> ';
											}

											var pingfen = '';
											if(row.tastate == 6){
												var pf_str = 'btn-primary';
												if(row.pfCount == 0){
													pf_str = 'btn-danger';
												}

												pingfen = '<a class="btn '+pf_str+' btn-sm '+s_pf_h+'" href="#" mce_href="#" title="评分" onclick="pf(\''
												+ row.id + '\',\'' + 1
												+ '\')"><i class="fa fa-edit">评分</i></a> ';
											}
											
											var dc = '<a class="btn btn-primary btn-sm" href="#" title="导出提案" mce_href="#" onclick="dc(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i>导出提案</a> ';
											
											return b + gt + fhyjg + zsfhhz + pingfen + dc;
										}else{
											/*var b = '<a class="btn btn-primary btn-sm '+s_bl_h+'" href="#" title="查看" mce_href="#" onclick="bl(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i>查看</a> ';*/
											var dc = '<a class="btn btn-primary btn-sm" href="#" title="导出提案" mce_href="#" onclick="dc(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i>导出提案</a> ';
											return b + gt + dc;
										}
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function bl(id) {
	layer.open({
		type : 2,
		title : '查看',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/bl/' + id + '/' + $("#type").val()// iframe的url
	});
}
function sqgb(id) {
	layer.open({
		type : 2,
		title : '申请改办',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/sqgb/' + id + '/' + $("#type").val()// iframe的url
	});
}

function fh(id) {
	layer.open({
		id : "fhLayerId",
		type : 2,
		title : '复函',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/fh/' + id// iframe的url
	});
}

function gt(id,dealType) {
	layer.open({
		type : 2,
		title : '交流',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/proposal/xx/2?taxxid='+id +'&dealType='+dealType// iframe的url
	});
}

function fhyjg(id,type) {
	layer.open({
		type : 2,
		title : '复函意见稿',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/fhyjg/' + id +'/'+type// iframe的url
	});
}

function pf(id,type) {
	layer.open({
		type : 2,
		title : '承办单位评分',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/pf/' + id +'/'+type// iframe的url
	});
}

function resetPwd(id) {
}

function fhReLoad(){
	var frameId = document.getElementById('fhLayerId').getElementsByTagName("iframe")[0].id;
    $('#'+frameId)[0].contentWindow.reLoad();
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
