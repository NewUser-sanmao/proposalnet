$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		if($("#inputFile").val()!=null && $("#inputFile").val()!=""){
			save();
		}else{
			parent.layer.alert("请选择需要上传的文件!")
		}
	}
});
function save() {
	
	$("#signupForm").ajaxSubmit({
        type: 'post', // 提交方式 get/post
        url: '/proposal/cbdw/upExcel', // 需要提交的 url
        success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
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
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
		},
		messages : {
		}
	});
}