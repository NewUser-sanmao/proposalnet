$().ready(function() {
	validateRule();
	
});

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
		url : "/proposal/tabl/saveFhhz",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.fhReLoad();
				
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	
	$("#signupForm").validate({
		rules : {
			bt : {
				required : true
			},
			nr : {
				required : true
			}
		},
		messages : {
			bt : {
				required : icon + "必填项"
			},
			nr : {
				required : icon + "必填项"
			}
		}
	});
}