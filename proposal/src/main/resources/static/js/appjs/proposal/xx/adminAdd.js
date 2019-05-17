$().ready(function() {
	validateRule();
	var data = {};
	data.taxxid = $("#taxxid").val();
	//debugger
	$.ajax({
		type : "get",
		url : '/proposal/cbdw/selectAllSelectByTaxxId',
		data : data,// 你的formid
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if(data!=null && data.results!=null){
				$("#mbdw").select2({
					multiple:true,
			        data: data.results,
			        placeholder: "请选择"
			    });
			}
		}
	});
	
	$("input[name='type']").click(function(){
		var val = $(this).val();
		if(val == 2){
			$("#mbdwDiv").removeClass("hidden");
		}else{
			$("#mbdwDiv").addClass("hidden");
		}
	});
	
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	if($("input[name='type']:checked").val() == 0){
		var arr = new Array();
		$("#mbdw option").each(function(){
			var val = $(this).attr("value");
			arr.push(val);
		});
		$("#mbdw").val(arr).trigger('change');
	}
	$("#signupForm").ajaxSubmit({
        type: 'post', // 提交方式 get/post
        url: '/proposal/xx/saveAdminAdd', // 需要提交的 url
        success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
        	if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.xxReLoad();
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
			nr : {
				required : true
			}
		},
		messages : {
			nr : {
				required : icon + "必填项"
			}
		}
	})
}