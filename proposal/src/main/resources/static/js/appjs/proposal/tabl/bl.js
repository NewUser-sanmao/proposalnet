$().ready(function() {
	$('#tanr_sn').summernote({
		height : '140px',
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
	/*$("#zxjcid").select2({
		ajax: {
			url: '/proposal/zxjc/selectAllSelect',
			dataType: 'json'
		}
	});*/
	/*proposalUtil.setSelect2('/proposal/zxjc/selectAllSelect',null,'zxjcid',$("#zxjcidVal").val());*/
	
	/*$("#sswyhid").select2({
		ajax: {
			url: '/proposal/sswyh/selectAllSelect',
			dataType: 'json'
		}
	});*/
	proposalUtil.setSelect2('/proposal/sswyh/selectAllSelect',null,'sswyhid',$("#sswyhidVal").val());
	
	/*$("#lbyblsxid").select2({
		ajax: {
			url: '/proposal/lbyblsx/selectAllSelect',
			dataType: 'json'
		}
	});*/
	proposalUtil.setSelect2('/proposal/lbyblsx/selectAllSelect',null,'lbyblsxid',$("#lbyblsxidVal").val());
	
	/*$("#flid").select2({
		ajax: {
			url: '/proposal/lbyblsx/selectAllSelect',
			dataType: 'json'
		}
	});*/
	proposalUtil.setSelect2('/proposal/lbyblsx/selectAllSelect',null,'flid',$("#flidVal").val());
	
	
	var cbdwVal = $("#cbdwVal").val();
	proposalUtil.setSelect2Checkbox('/proposal/cbdw/selectAllSelect',null,'cbdw',cbdwVal.split(","));
	
	var fbdwVal = $("#fbdwVal").val();
	proposalUtil.setSelect2Checkbox('/proposal/cbdw/selectAllSelect',null,'fbdw',fbdwVal.split(","));
	
	var xbdwVal = $("#xbdwVal").val();
	proposalUtil.setSelect2Checkbox('/proposal/cbdw/selectAllSelect',null,'xbdw',xbdwVal.split(","));
	
	/*
	$("#taly").select2({
		ajax: {
			url: '/proposal/tayy/selectAllSelect',
			dataType: 'json'
		}
	});
	*/
	proposalUtil.setSelect2('/proposal/tayy/selectAllSelect',null,'taly',$("#talyId").val());
	//$(".js-example").val(['0','2']).trigger('change')
	
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
	
	//debugger
	var fjmc = $("#fjmc").val();
	var fjdz = $("#fjdz").val();
	var fjStr = '';
	if(fjmc!=null && fjmc!=""){
		fjmcs = fjmc.split(",");
		fjdzs = fjdz.split(",");
		for(var index in fjmcs){
			fjStr += '<a href="'+fjdz[index]+'" download="'+fjmcs[index]+'">'+fjmcs[index]+'</a><br/>';
		}
		$("#fjDiv").html(fjStr);
	}
	
});
