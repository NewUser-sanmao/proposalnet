$().ready(function() {
	validateRule();
	
	$("#parentId").select2({
		ajax: {
			url: '/proposal/cbdw/selectAllSelect',
			dataType: 'json'
		}
	});
	
});

$.validator.setDefaults({
	submitHandler : function() {
		//update();
	}
});
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	
	jQuery.validator.addMethod("contrastPwd", function(value, element) {
		return value == $("#contrastPwd").val();   
	}, icon+"两次输入密码不一致");
	
	$("#signupForm").validate({
		rules : {
			txdz : {
				required : true
			},
			yb : {
				required : true
			},
			
			bglxdh : {
				required : true
			},
			sjhm : {
				required : true
			},
			userName : {
				required : true,
				minlength : 2,
				remote : {
					url : "/sys/user/exit", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "json", // 接受数据格式
					data : { // 要传递的数据
						exclude : function() {
							return $("#userName2").val();
						},
						username : function() {
							return $("#userName").val();
						}
					}
				}
			},
			pwd : {
				required : true,
				contrastPwd : true
			}
			
		},
		messages : {
			txdz : {
				required : icon + "必填项"
			},
			yb : {
				required : icon + "必填项"
			},
			
			bglxdh : {
				required : icon + "必填项"
			},
			sjhm : {
				required : icon + "必填项"
			},
			userName : {
				required : icon + "必填项",
				minlength : icon + "用户名必须两个字符以上",
				remote : icon + "用户名已经存在"
			},
			pwd : {
				required : icon + "必填项"
			}
		}
	});
}