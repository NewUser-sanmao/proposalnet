$().ready(function() {
	/*
	$('#jbqk_sn').summernote({
		height : '140px',
		lang : 'zh-CN',
		toolbar: [
		    // [groupName, [list of button]]
		    ['Insert', ['picture']],
		],
		callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile('jbqk_sn',files);
            },
            onPaste: function(ne) {
            	var bufferText = ((ne.originalEvent || ne).clipboardData || window.clipboardData).getData('Text/plain');
                //    ne.preventDefault();  
                ne.preventDefault ? ne.preventDefault() : (ne.returnValue = false);
                // Firefox fix
                setTimeout(function () {
                    document.execCommand("insertText", false, bufferText);
                });
            }
		}
	});
	$('#jbqk_sn').summernote('code', $("#jbqk").val());
	
	$('#czwt_sn').summernote({
		height : '140px',
		lang : 'zh-CN',
		toolbar: [
		    ['Insert', ['picture']],
		],
		callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile('czwt_sn',files);
            },
            onPaste: function(ne) {
            	var bufferText = ((ne.originalEvent || ne).clipboardData || window.clipboardData).getData('Text/plain');
                //    ne.preventDefault();  
                ne.preventDefault ? ne.preventDefault() : (ne.returnValue = false);
                // Firefox fix
                setTimeout(function () {
                    document.execCommand("insertText", false, bufferText);
                });
            }
        }
	});
	$('#czwt_sn').summernote('code', $("#czwt").val());
	
	$('#dcjy_sn').summernote({
		height : '140px',
		lang : 'zh-CN',
		toolbar: [
		    ['Insert', ['picture']],
		],
		callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile('dcjy_sn',files);
            },
            onPaste: function(ne) {
            	var bufferText = ((ne.originalEvent || ne).clipboardData || window.clipboardData).getData('Text/plain');
                //    ne.preventDefault();  
                ne.preventDefault ? ne.preventDefault() : (ne.returnValue = false);
                // Firefox fix
                setTimeout(function () {
                    document.execCommand("insertText", false, bufferText);
                });
            }
        }
	});
	$('#dcjy_sn').summernote('code', $("#dcjy").val());
	
	$('#qtsm_sn').summernote({
		height : '140px',
		lang : 'zh-CN',
		toolbar: [
		    ['Insert', ['picture']],
		],
		callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile('qtsm_sn',files);
            },
            onPaste: function(ne) {
            	var bufferText = ((ne.originalEvent || ne).clipboardData || window.clipboardData).getData('Text/plain');
                //    ne.preventDefault();  
                ne.preventDefault ? ne.preventDefault() : (ne.returnValue = false);
                // Firefox fix
                setTimeout(function () {
                    document.execCommand("insertText", false, bufferText);
                });
            }
        }
	});
	$('#qtsm_sn').summernote('code', $("#qtsm").val());
	*/
	
	$('#tanr_sn').summernote({
		height : '340px',
		lang : 'zh-CN',
		toolbar: [
		    // [groupName, [list of button]]
		    ['Insert', ['picture']],
		],
		callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile('tanr_sn',files);
            },
            onPaste: function(ne) {
            	var bufferText = ((ne.originalEvent || ne).clipboardData || window.clipboardData).getData('Text/plain');
                //    ne.preventDefault();  
                ne.preventDefault ? ne.preventDefault() : (ne.returnValue = false);
                // Firefox fix
                setTimeout(function () {
                    document.execCommand("insertText", false, bufferText);
                });
            }
        }
	});
	$('#tanr_sn').summernote('code', $("#tanr").val());
	
	/*$("#zxjcid").select2({
		ajax: {
			url: '/proposal/zxjc/selectAllSelect',
			dataType: 'json'
		}
	});*/
	
	$("#sswyhid").select2({
		ajax: {
			url: '/proposal/sswyh/selectAllSelect',
			dataType: 'json'
		}
	});
	$("#lbyblsxid").select2({
		ajax: {
			url: '/proposal/lbyblsx/selectAllSelect',
			dataType: 'json'
		}
	});
	
	validateRule();
	
	$.ajax({
		type : "get",
		url : '/proposal/cbdw/selectAllSelect',
		data : null,// 你的formid
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if(data!=null && data.results!=null){
				data.results.splice(0, 0, {id:-1,text:"请选择"});
				$("#cbdwid").select2({
			        data: data.results,
			        placeholder: "请选择"
			    });
				if($("#cbdwidVal").val()!=null){
					$("#cbdwid").val($("#cbdwidVal").val()).trigger('change');
				}
			}
		}
	});
	//proposalUtil.setSelect2('/proposal/cbdw/selectAllSelect',null,'cbdwid',$("#cbdwidVal").val());
	
	//debugger
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
	    deleteUrl: "/proposal/taxx/removeFj",
	    deleteExtraData : function(){
	    	return {id:$("#id").val()};
	    },
	    overwriteInitial: false,
	    maxFileSize: 102400,
	    uploadAsync : true,
	    uploadUrl: "/proposal/taxx/uploadFj",
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
//	var content_sn = $("#tanr_sn").summernote('code');
//	$("#tanr").val(content_sn);
	

	/*var jbqk_sn = $("#jbqk_sn").summernote('code');
	$("#jbqk").val(jbqk_sn);
	
	var czwt_sn = $("#czwt_sn").summernote('code');
	$("#czwt").val(czwt_sn);
	
	var dcjy_sn = $("#dcjy_sn").summernote('code');
	$("#dcjy").val(dcjy_sn);
	
	var qtsm_sn = $("#qtsm_sn").summernote('code');
	$("#qtsm").val(qtsm_sn);*/
	var tanr_sn = $("#tanr_sn").summernote('code');
	$("#tanr").val(tanr_sn);
	
	//$('#content_sn').summernote('code', content);
	var ls_str1 = tanr_sn.replace(/[^\u4e00-\u9fa5]/g,"");
	if(ls_str1.length == 0){
		parent.layer.msg("提案内容必需填写.并且字数不能少于400字");
		return;
	}else if((ls_str1.length) < 400){
		parent.layer.msg("字数小于400不能提交");
		return;
	}else if(ls_str1.length > 2000){
		parent.layer.msg("字数大于2000不能提交");
		return;
	}else if(ls_str1.length > 1600){
		//parent.layer.msg("提案内容必需填写.并且字数不能少于400字");
		//parent.layer.alert("提案内容字数已经超过1600字，能再精简一下吗？", {icon: 6});
		layer.confirm('提案内容字数已经超过1600字，能再精简一下吗？', {
				btn: ['再精简一下','继续提交'] //按钮
			}, function(){
				//再精简一下
				layer.close(layer.index);
			}, function(){
				//继续提交
				qrtj();
			}
		);
	}else{
		qrtj();
	}
}
function qrtj(){
	layer.confirm('确认要提交吗？', {
			btn: ['确认','取消'] //按钮
		}, function(){
			//确认
			$.ajax({
				cache : true,
				type : "POST",
				url : "/proposal/taxx/update",
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
		}, function(){
			//取消
		}
	);
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