$().ready(function() {
	validateRule();
	
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
	/*$('#jbqk_sn').summernote({
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
	});*/
	
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
	$("#cbdwid").select2({
		ajax: {
			url: '/proposal/cbdw/selectAllSelect',
			dataType: 'json'
		}
	});
	
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
				/*if(defaultVal!=null){
					$("#cbdwid").val(defaultVal).trigger('change');
				}*/
			}
		}
	});
	//proposalUtil.setSelect2('/proposal/cbdw/selectAllSelect',null,'cbdwid',null);
	
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
		parent.layer.msg("提案内容必需填写.并且字数不能少于400字，不多余2000字。");
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
			$("#signupForm").ajaxSubmit({
		        type: 'post', // 提交方式 get/post
		        url: '/proposal/taxx/saveAndFile', // 需要提交的 url
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
		}, function(){
			//取消
		}
	);
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	
	$("#signupForm").validate({
		rules : {
			zxjcid : {
				required : true
			},
			tanx : {
				required : true
			},
			tatm : {
				required : true
			},
			lbyblsxid : {
				required : true
			},
			isjgdy : {
				required : true
			},
			isbrzx : {
				required : true
			},
			iszctrcl : {
				required : true
			},
			isgktanr : {
				required : true
			},
			taz : {
				required : true
			},
			gzdwjzw : {
				required : true
			},
			txdz : {
				required : true
			},
			yb : {
				required : true
			},
			lxdh : {
				required : true
			},
			sswyhid : {
				required : true
			},
			tanr : {
				required : true
			},
			jyxsfsid : {
				required : true
			},
			tastate : {
				required : true
			},
			lastate : {
				required : true
			},
			isgkta : {
				required : true
			}
			
		},
		messages : {
			zxjcid : {
				required : icon + "必填项"
			},
			tanx : {
				required : icon + "必填项"
			},
			tatm : {
				required : icon + "必填项"
			},
			lbyblsxid : {
				required : icon + "必填项"
			},
			isjgdy : {
				required : icon + "必填项"
			},
			isbrzx : {
				required : icon + "必填项"
			},
			iszctrcl : {
				required : icon + "必填项"
			},
			isgktanr : {
				required : icon + "必填项"
			},
			taz : {
				required : icon + "必填项"
			},
			gzdwjzw : {
				required : icon + "必填项"
			},
			txdz : {
				required : icon + "必填项"
			},
			yb : {
				required : icon + "必填项"
			},
			lxdh : {
				required : icon + "必填项"
			},
			sswyhid : {
				required : icon + "必填项"
			},
			tanr : {
				required : icon + "必填项"
			},
			jyxsfsid : {
				required : icon + "必填项"
			},
			tastate : {
				required : icon + "必填项"
			},
			lastate : {
				required : icon + "必填项"
			},
			isgkta : {
				required : icon + "必填项"
			}
			
		}
	});
}