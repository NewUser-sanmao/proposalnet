$().ready(function() {
	validateRule();
	
	$("input[name='latype']").click(function(){
		var val = $(this).val();
		laTypeClick(val);
	});
	
	showOrhide(1);
	var cbdwVal = $("#cbdwVal").val();
	proposalUtil.setSelect2Checkbox('/proposal/cbdw/selectAllSelect',null,'cbdw',cbdwVal.split(","));
	
	var fbdwVal = $("#fbdwVal").val();
	proposalUtil.setSelect2Checkbox('/proposal/cbdw/selectAllSelect',null,'fbdw',fbdwVal.split(","));
	
	var xbdwVal = $("#xbdwVal").val();
	proposalUtil.setSelect2Checkbox('/proposal/cbdw/selectAllSelect',null,'xbdw',xbdwVal.split(","));
	
	proposalUtil.setSelect2('/proposal/tayy/selectAllSelect',null,'taly',$("#talyId").val());
	//$(".js-example").val(['0','2']).trigger('change')
	
});

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

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function tijiao(obj){
	if(!$(obj).hasClass("disabled")){
		$("#signupForm").submit();
	}
}

function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/proposal/tabl/saveGbsq",
		data : $('#signupForm').serialize(),// 你的formid
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

function remove() {
	layer.confirm('确定要撤销改办？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : "/proposal/tabl/removeGbsq",
			type : "post",
			data : {
				'id' : $("#id").val()
			},
			success : function(r) {
				if (r.code==0) {
					parent.layer.msg("操作成功");
					parent.reLoad();
					var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
					parent.layer.close(index);
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	
	$("#signupForm").validate({
		rules : {
			
		},
		messages : {
			
		}
	});
}