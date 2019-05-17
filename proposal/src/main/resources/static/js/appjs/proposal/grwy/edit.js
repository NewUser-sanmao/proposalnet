$().ready(function() {
	validateRule();
	
	$("#ssjcid").select2({
		ajax: {
			url: '/proposal/zxjc/selectAllSelect',
			dataType: 'json'
		}
	});
	$("#ssjbid").select2({
		ajax: {
			url: '/proposal/tajb/selectAllSelect',
			dataType: 'json'
		}
	});
	$("#sswyhid").select2({
		ajax: {
			url: '/proposal/sswyh/selectAllSelect',
			dataType: 'json'
		}
	});
	$("#ssdpid").select2({
		ajax: {
			url: '/proposal/dp/selectAllSelect',
			dataType: 'json'
		}
	});
	$("#ssdqid").select2({
		ajax: {
			url: '/proposal/dqxz/selectAllSelect',
			dataType: 'json'
		}
	});
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/proposal/grwy/update",
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
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";

	jQuery.validator.addMethod("contrastPwd", function(value, element) {
		return value == $("#contrastPwd").val();   
	}, icon+"两次输入密码不一致");
	
	$("#signupForm").validate({
		rules : {
			ssjcid : {
				required : true
			},
			ssjbid : {
				required : true
			},
			ssdpid : {
				required : true
			},
			sswyhid : {
				required : true
			},
			ssdqid : {
				required : true
			},
			xm : {
				required : true,
				chinese : true
			},
			xb : {
				required : true
			},
			jstx : {
				required : true
			},
			dwmcZw : {
				required : true,
				noAllNumberAndLetter : true
			},
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
				required : true,
				legitimateSjhm : true
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
				intensityPwd : true,
				contrastPwd : true
			},
			sfz : {
				required : true,
				legitimateSfz : true
			}
		},
		messages : {
			ssjcid : {
				required : icon + "必填项"
			},
			ssjbid : {
				required : icon + "必填项"
			},
			ssdpid : {
				required : icon + "必填项"
			},
			sswyhid : {
				required : icon + "必填项"
			},
			ssdqid : {
				required : icon + "必填项"
			},
			xm : {
				required : icon + "必填项"
			},
			xb : {
				required : icon + "必填项"
			},
			jstx : {
				required : icon + "必填项"
			},
			dwmcZw : {
				required : icon + "必填项"
			},
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
			},
			sfz : {
				required : icon + "必填项"
			}
		}
	})
}