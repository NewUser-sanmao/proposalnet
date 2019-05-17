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
	
	$('#summernote_nr').summernote('code', $("#nr").val());
	
	var fjmc = $("#fjmc").val();
	var fjdz = $("#fjdz").val();
	var fjmcs = [], fjdzs = [];
	var initialPreviewConfig = new Array();
	if(fjmc!=null && fjmc!=""){
		fjmcs = fjmc.split(",");
		fjdzs = fjdz.split(",");
		for(var index in fjmcs){
			initialPreviewConfig.push({
				caption: fjmcs[index], downloadUrl: fjdzs[index], width: "120px", key: fjdzs[index]
			});
		}
	}
	
	$("#inputFiles").fileinput({
		language: 'zh',
	    initialPreview: fjdzs,
	    initialPreviewAsData: true,
	    initialPreviewConfig: initialPreviewConfig,
	    deleteUrl: "/proposal/gzfw/removeFj",
	    deleteExtraData : function(){
	    	return {id:$("#id").val()};
	    },
	    overwriteInitial: false,
	    maxFileSize: 102400,
	    uploadAsync : true,
	    uploadUrl: "/proposal/gzfw/uploadFj",
	    uploadExtraData : function(){
	    	return {id:$("#id").val()};
	    }
	}).on('filebatchselected', function (event, files) {//选中文件事件
        $(this).fileinput("upload");
    });
	
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	var summernote_nr = $("#summernote_nr").summernote('code');
	$("#nr").val(summernote_nr);
	
	$.ajax({
		cache : true,
		type : "POST",
		url : "/proposal/gzfw/update",
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