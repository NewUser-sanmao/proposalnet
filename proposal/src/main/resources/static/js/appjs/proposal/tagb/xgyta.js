$().ready(function() {
	validateRule();
	
	$('#tanr_sn').summernote({
		height : '140px',
		lang : 'zh-CN',
		toolbar: [
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
	
	proposalUtil.setSelect2('/proposal/sswyh/selectAllSelect',null,'sswyhid',$("#sswyhidVal").val());
	
	proposalUtil.setSelect2('/proposal/lbyblsx/selectAllSelect',null,'lbyblsxid',$("#lbyblsxidVal").val());
	
	proposalUtil.setSelect2('/proposal/lbyblsx/selectAllSelect',null,'flid',$("#flidVal").val());
	
	$("input[name='lastate']").click(function(){
		var val = $(this).val();
		showOrhide(val);
	});
	
	$("input[name='latype']").click(function(){
		var val = $(this).val();
		laTypeClick(val);
	});
	
	showOrhide($("input[name='lastate']:checked").val());
	var cbdwVal = $("#cbdwVal").val();
	proposalUtil.setSelect2Checkbox('/proposal/cbdw/selectAllSelect',null,'cbdw',cbdwVal.split(","));
	
	var fbdwVal = $("#fbdwVal").val();
	proposalUtil.setSelect2Checkbox('/proposal/cbdw/selectAllSelect',null,'fbdw',fbdwVal.split(","));
	
	var xbdwVal = $("#xbdwVal").val();
	proposalUtil.setSelect2Checkbox('/proposal/cbdw/selectAllSelect',null,'xbdw',xbdwVal.split(","));
	
	proposalUtil.setSelect2('/proposal/tayy/selectAllSelect',null,'taly',$("#talyId").val());
	
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

function showOrhide(val){
	if(val=='1'){
		$("#laTypeDiv").removeClass("hidden");
		laTypeClick($("input[name='latype']:checked").val());
	}else if(val=='2'){
		addClass("laTypeDiv","hidden");
		addClass("fbdwDiv","hidden");
		addClass("xbdwDiv","hidden");
		
		$("#talyDiv").removeClass("hidden");
		$("#cbdwDiv").removeClass("hidden");
	}else if(val=='3'){
		addClass("laTypeDiv","hidden");
		addClass("cbdwDiv","hidden");
		addClass("fbdwDiv","hidden");
		addClass("xbdwDiv","hidden");
		
		$("#talyDiv").removeClass("hidden");
	}
}

function laTypeClick(val){
	if(val=='1'){
		$("#cbdwDiv").removeClass("hidden");
		addClass("talyDiv","hidden");
		addClass("fbdwDiv","hidden");
		addClass("xbdwDiv","hidden");
	}else if(val=='2'){
		$("#fbdwDiv").removeClass("hidden");
		addClass("talyDiv","hidden");
		addClass("cbdwDiv","hidden");
		addClass("xbdwDiv","hidden");
	}else if(val=='3'){
		$("#cbdwDiv").removeClass("hidden");
		$("#xbdwDiv").removeClass("hidden");
		addClass("talyDiv","hidden");
		addClass("fbdwDiv","hidden");
	}
}

function addClass(id,className){
	if($("#"+id).hasClass(className)==false){
		$("#"+id).addClass(className);
	}
}

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	var content_sn = $("#tanr_sn").summernote('code');
	$("#tanr").val(content_sn);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/proposal/tagb/updateTasc",
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
			flid : {
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
			flid : {
				required : icon + "必填项"
			}
			
		}
	});
}