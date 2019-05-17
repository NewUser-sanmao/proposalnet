$().ready(function() {
	//debugger
	var ids = $("#ids").val();
	var dzs = $("#dzs").val();
	var mcs = $("#mcs").val();
	var idsArr = [], dzsArr = [], mcsArr = [];
	var initialPreviewConfig = new Array();
	if(ids!=null && ids!=""){
		idsArr = ids.split(",");
		dzsArr = dzs.split(",");
		mcsArr = mcs.split(",");
		for(var index in idsArr){
			initialPreviewConfig.push({
				caption: mcsArr[index], downloadUrl: dzsArr[index], width: "120px", key: idsArr[index]
			});
		}
	}
	
	var ck = $("#chakan").val();
	var layoutTemplates = {};
	var showRemove = true;
	if(ck==1){//查看操作
		showRemove = false;
		layoutTemplates = {
	    	actionDelete:''
	    };
	}
	
	$("#inputFiles").fileinput({
		language: 'zh',
		showRemove : showRemove,
	    initialPreview: dzsArr,
	    initialPreviewAsData: true,
	    initialPreviewConfig: initialPreviewConfig,
	    deleteUrl: "/proposal/tabl/removeFj",
	    deleteExtraData : function(){
	    	return {id:$("#id").val()};
	    },
	    overwriteInitial: false,
	    maxFileSize: 102400,
	    uploadAsync : true,
	    uploadUrl: "/proposal/tabl/uploadFj",
	    uploadExtraData : function(){
	    	return {taxxid:$("#taxxid").val(), type:$("#type").val()};
	    },
	    layoutTemplates:layoutTemplates
	}).on('filebatchselected', function (event, files) {//选中文件事件
        //$(this).fileinput("upload");
    }).on('filesuccessremove', function (event, previewId, extra) {
    	//alert(1);
  　　　　　　//在移除事件里取出所需数据，并执行相应的删除指令
        //delete(($('#' + previewId).attr('fileid'));
    });;
	
	
});
new_element=document.createElement("script");
new_element.setAttribute("type","text/javascript");
new_element.setAttribute("src","tabl.js");
document.body.appendChild(new_element);
function tijiao(){
	layer.confirm("确认要提交吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function(r) {
		console.log(r);
		$("#inputFiles").fileinput("upload");
		parent.layer.msg("操作成功");
		parent.reLoad();
		var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
		parent.layer.close(index);
    }, function() {
        reLoad();
	});
}
