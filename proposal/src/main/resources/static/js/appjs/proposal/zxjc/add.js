$().ready(function() {
	validateRule();
	
	$('#qsrq').datepicker({
	    startView: 0,
	    todayBtn: "linked",
	    keyboardNavigation: false,
	    forceParse: false,
	    autoclose: true,
	    format: "yyyy-mm-dd",
	    clearBtn: true
	});
	
	$('#zjrq').datepicker({
	    startView: 0,
	    todayBtn: "linked",
	    keyboardNavigation: false,
	    forceParse: false,
	    autoclose: true,
	    format: "yyyy-mm-dd",
	    clearBtn: true
	});
	
	proposalUtil.setSelect2('/proposal/zxjc/selectAllDic',null,'jid',null);
	
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function save() {
	$("#jmc").val($("#jId option:selected").html());
	
	$.ajax({
		cache : true,
		type : "POST",
		url : "/proposal/zxjc/save",
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
	$("#signupForm").validate({
		rules : {
			zxjcmc : {
				required : true
			},
			zxjcqsh : {
				required : true
			},
			zjrq : {
				required : true
			},
			jid : {
				required : true
			},
			qsrq : {
				required : true
			}
		},
		messages : {
			zxjcmc : {
				required : icon + "必填项"
			},
			zxjcqsh : {
				required : icon + "必填项"
			},
			zjrq : {
				required : icon + "必填项"
			},
			jid : {
				required : icon + "必填项"
			},
			qsrq : {
				required : icon + "必填项"
			}
		}
	})
}