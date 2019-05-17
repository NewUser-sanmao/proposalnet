$().ready(function() {
	
});

function hbta() {
	//debugger
	var zid = $("input[name='zta']:checked").val();
	if(zid == null){
		parent.layer.msg("请选择主提案");
		return;
	}
	var arr = new Array();
	$("input[name='zta']").each(function(){
		var str = $(this).val();
		if(zid != str){
			arr.push(str);
		}
	});
	
	$.ajax({
		cache : true,
		type : "POST",
		url : "/proposal/tasc/updateBa",
		data : {zid:zid,fids:arr.toString()},// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});
}