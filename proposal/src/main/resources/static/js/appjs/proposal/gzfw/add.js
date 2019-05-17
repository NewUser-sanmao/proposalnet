$().ready(function() {
	validateRule();
	
	$('#summernote_nr').summernote({
		height : '220px',
		lang : 'zh-CN',
		callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile(files);
            }
        }
	});
	
	$("#inputFiles").fileinput({
		language: 'zh',
        showUpload: false,
        layoutTemplates: {
            main1: "{preview}\n" +
            "<div class=\'input-group {class}\'>\n" +
            "   <div class=\'input-group-btn\ input-group-prepend'>\n" +
            "       {browse}\n" +
            "       {upload}\n" +
            "       {remove}\n" +
            "   </div>\n" +
            "   {caption}\n" +
            "</div>"
        }
    });
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	/*$.ajax({
		cache : true,
		type : "POST",
		url : "/proposal/gzfw/save",
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
	});*/
	
	var summernote_nr = $("#summernote_nr").summernote('code');
	$("#nr").val(summernote_nr);
	
	$("#signupForm").ajaxSubmit({
        type: 'post', // 提交方式 get/post
        url: '/proposal/gzfw/save', // 需要提交的 url
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
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}