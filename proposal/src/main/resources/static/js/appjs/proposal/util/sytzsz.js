$().ready(function() {
	validateRule();
	
	$('#text_sn').summernote({
		height : '340px',
		lang : 'zh-CN',
		callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile('czwt_sn',files);
            }
        }
	});
	$('#text_sn').summernote('code', $("#text").val());
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	var text_sn = $("#text_sn").summernote('code');
	$("#text").val(text_sn);
	
	$.ajax({
		cache : true,
		type : "POST",
		url : "/proposal/util/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
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
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
			}
		}
	})
}