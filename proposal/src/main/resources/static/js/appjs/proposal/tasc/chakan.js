$().ready(function() {
	taxxLoad();
	xxLoad();

	$("input[name='lastate']").click(function(){
		var val = $(this).val();
		showOrhide(val);
	});
	
	$("input[name='latype']").click(function(){
		var val = $(this).val();
		laTypeClick(val);
	});
	
	showOrhide($("input[name='lastate']:checked").val());
	var cbdwVal = $("#cbdwVal").val();
	proposalUtil.setSelect2Checkbox('/proposal/cbdw/selectAllSelect',null,'cbdw',cbdwVal.split(","));
	
	var fbdwVal = $("#fbdwVal").val();
	proposalUtil.setSelect2Checkbox('/proposal/cbdw/selectAllSelect',null,'fbdw',fbdwVal.split(","));
	
	var xbdwVal = $("#xbdwVal").val();
	proposalUtil.setSelect2Checkbox('/proposal/cbdw/selectAllSelect',null,'xbdw',xbdwVal.split(","));
	
	//组合办理单位,用作查看单位详情
	setTimeout(function(){
		//debugger
		var zhHtml = "";
		if($("#cbdw").val() != null){
			var val = $("#cbdw").val()+"";
			var arr = val.split(",");
			for(var index in arr){
				zhHtml += "<a href='javascript:void(0);' onclick='readBldW("+arr[index]+");'>"+$("#cbdw option[value='"+arr[index]+"']").html()+"</a>&nbsp;&nbsp;";
			}
		}
		if($("#fbdw").val() != null){
			var val = $("#fbdw").val()+"";
			var arr = val.split(",");
			for(var index in arr){
				zhHtml += "<a href='javascript:void(0);' onclick='readBldW("+arr[index]+");'>"+$("#fbdw option[value='"+arr[index]+"']").html()+"</a>&nbsp;&nbsp;";
			}
		}
		if($("#xbdw").val() != null){
			var val = $("#xbdw").val()+"";
			var arr = val.split(",");
			for(var index in arr){
				zhHtml += "<a href='javascript:void(0);' onclick='readBldW("+arr[index]+");'>"+$("#xbdw option[value='"+arr[index]+"']").html()+"</a>&nbsp;&nbsp;";
			}
		}
		$("#ckdwxq").html(zhHtml);
		
	},500);
	
});
function taxxLoad(){
	//debugger
	$('#tanr_sn').summernote({
		height : '240px',
		lang : 'zh-CN',
		toolbar: [
		    // [groupName, [list of button]]
		    ['Insert', ['picture']],
		],
		callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile('tanr_sn',files);
            },
            onPaste: function(ne) {
            	var bufferText = ((ne.originalEvent || ne).clipboardData || window.clipboardData).getData('Text/plain');
                //    ne.preventDefault();  
                ne.preventDefault ? ne.preventDefault() : (ne.returnValue = false);
                // Firefox fix
                setTimeout(function () {
                    document.execCommand("insertText", false, bufferText);
                });
            }
        }
	});
	$('#tanr_sn').summernote('code', $("#tanr").val());
	
	
	proposalUtil.setSelect2('/proposal/sswyh/selectAllSelect',null,'sswyhid',$("#sswyhidVal").val());
	
	proposalUtil.setSelect2('/proposal/lbyblsx/selectAllSelect',null,'lbyblsxid',$("#lbyblsxidVal").val());
	
	proposalUtil.setSelect2('/proposal/lbyblsx/selectAllSelect',null,'flid',$("#flidVal").val());
	
	
	var cbdwVal = $("#cbdwVal").val();
	proposalUtil.setSelect2Checkbox('/proposal/cbdw/selectAllSelect',null,'cbdw',cbdwVal.split(","));
	
	var fbdwVal = $("#fbdwVal").val();
	proposalUtil.setSelect2Checkbox('/proposal/cbdw/selectAllSelect',null,'fbdw',fbdwVal.split(","));
	
	var xbdwVal = $("#xbdwVal").val();
	proposalUtil.setSelect2Checkbox('/proposal/cbdw/selectAllSelect',null,'xbdw',xbdwVal.split(","));
	
	proposalUtil.setSelect2('/proposal/tayy/selectAllSelect',null,'taly',$("#talyId").val());
	//$(".js-example").val(['0','2']).trigger('change')
	
	$.ajax({
		type : "get",
		url : '/proposal/cbdw/selectAllSelect',
		data : null,// 你的formid
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if(data!=null && data.results!=null){
				data.results.splice(0, 0, {id:-1,text:"请选择"});
				$("#cbdwid").select2({
			        data: data.results,
			        placeholder: "请选择"
			    });
				if($("#cbdwidVal").val()!=null){
					$("#cbdwid").val($("#cbdwidVal").val()).trigger('change');
				}
			}
		}
	});
	
	//debugger
	var fjmc = $("#fjmc").val();
	var fjdz = $("#fjdz").val();
	var fjStr = '';
	if(fjmc!=null && fjmc!=""){
		fjmcs = fjmc.split(",");
		fjdzs = fjdz.split(",");
		for(var index in fjmcs){
			fjStr += '<a href="'+fjdz[index]+'" download="'+fjmcs[index]+'">'+fjmcs[index]+'</a><br/>';
		}
		$("#fjDiv").html(fjStr);
	}
}


function xxLoad() {
	$('#xxTable').bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : "/proposal/xx/list", // 服务器数据的加载地址
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
								taxxid:$("#id").val()
					           // name:$('#searchName').val(),
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
									field : 'nr', 
									title : '内容' 
								},
																{
									field : 'fsrMc', 
									title : '发送人'
								},
																{
									field : 'jsrMc', 
									title : '接收人'
								},
																{
									field : 'tah', 
									title : '提案号' 
								},
																{
									field : 'tatm', 
									title : '提案题目' 
								},
																{
									field : 'createTime', 
									title : '创建时间' 
								},
																{
									field : 'state', 
									title : '数据状态',
									formatter : function(value, row, index) {
										if(row.state==1){
											return "已查看";
										}else if(row.state==0){
											return "未查看";
										}
										return "";
									}
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="xxEdit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										return e;
									}
								} ]
					});
}

function xxReLoad() {
	$('#xxTable').bootstrapTable('refresh');
}

function xxEdit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '500px', '320px' ],
		content : '/proposal/xx/edit/' + id + '/2?taxxid='+$("#id").val() // iframe的url
	});
}


function showOrhide(val){
	if(val=='1'){
		$("#laTypeDiv").removeClass("hidden");
		laTypeClick($("input[name='latype']:checked").val());
	}else if(val=='2'){
		addClass("laTypeDiv","hidden");
		addClass("fbdwDiv","hidden");
		addClass("xbdwDiv","hidden");
		
		$("#talyDiv").removeClass("hidden");
		$("#cbdwDiv").removeClass("hidden");
	}else if(val=='3'){
		addClass("laTypeDiv","hidden");
		addClass("cbdwDiv","hidden");
		addClass("fbdwDiv","hidden");
		addClass("xbdwDiv","hidden");
		
		$("#talyDiv").removeClass("hidden");
	}
}

function laTypeClick(val){
	if(val=='1'){
		$("#cbdwDiv").removeClass("hidden");
		addClass("talyDiv","hidden");
		addClass("fbdwDiv","hidden");
		addClass("xbdwDiv","hidden");
	}else if(val=='2'){
		$("#fbdwDiv").removeClass("hidden");
		addClass("talyDiv","hidden");
		addClass("cbdwDiv","hidden");
		addClass("xbdwDiv","hidden");
	}else if(val=='3'){
		$("#cbdwDiv").removeClass("hidden");
		$("#xbdwDiv").removeClass("hidden");
		addClass("talyDiv","hidden");
		addClass("fbdwDiv","hidden");
	}
}

function addClass(id,className){
	if($("#"+id).hasClass(className)==false){
		$("#"+id).addClass(className);
	}
}

function grwyXs(){
	if($("#gzdwjzwDiv").hasClass("hidden")){//存在
		$("#gzdwjzwDiv").removeClass("hidden");
		$("#txdzDiv").removeClass("hidden");
		$("#ybDiv").removeClass("hidden");
		$("#lxdhDiv").removeClass("hidden");
		$("#sswyhidDiv").removeClass("hidden");
		$("#fytazDiv").removeClass("hidden");
	}else{
		addClass("gzdwjzwDiv","hidden");
		addClass("txdzDiv","hidden");
		addClass("ybDiv","hidden");
		addClass("lxdhDiv","hidden");
		addClass("sswyhidDiv","hidden");
		addClass("fytazDiv","hidden");
	}
}

function jtwyXs(){
	if($("#zbrDiv").hasClass("hidden")){//存在
		$("#zbrDiv").removeClass("hidden");
		$("#dwfzrDiv").removeClass("hidden");
		$("#lxrDiv").removeClass("hidden");
		$("#dzyxDiv").removeClass("hidden");
		$("#txdzDiv").removeClass("hidden");
		$("#ybDiv").removeClass("hidden");
		$("#lxdhDiv").removeClass("hidden");
	}else{
		addClass("zbrDiv","hidden");
		addClass("dwfzrDiv","hidden");
		addClass("lxrDiv","hidden");
		addClass("dzyxDiv","hidden");
		addClass("txdzDiv","hidden");
		addClass("ybDiv","hidden");
		addClass("lxdhDiv","hidden");
	}
}

function readBldW(dwmcId) {
	parent.layer.open({
		type : 2,
		title : '查看单位信息',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/proposal/cbdw/readCbdw?id='+dwmcId // iframe的url
	});
}

function adminAddXx() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '500px', '320px' ],
		content : '/proposal/xx/adminAdd/?taxxid='+$("#id").val() // iframe的url
	});
}