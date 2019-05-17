$().ready(function() {
	if ($.validator) {
	    //fix: when several input elements shares the same name, but has different id-ies....
	    $.validator.prototype.elements = function () {
	        var validator = this,
	            rulesCache = {};
	        // select all valid inputs inside the form (no submit or reset buttons)
	        // workaround $Query([]).add until http://dev.jquery.com/ticket/2114 is solved
	        return $([]).add(this.currentForm.elements)
	        .filter(":input")
	        .not(":submit, :reset, :image, [disabled]")
	        .not(this.settings.ignore)
	        .filter(function () {
	            var elementIdentification = this.id || this.name;
	 
	            !elementIdentification && validator.settings.debug && window.console && console.error("%o has no id nor name assigned", this);
	 
	            // select only the first element for each name, and only those with rules specified
	            if (elementIdentification in rulesCache || !validator.objectLength($(this).rules()))
	                return false;
	 
	            rulesCache[elementIdentification] = true;
	            return true;
	        });
	    };
	}
	
	validateRule();
	
	//计算总分
	$("input[type='number']").keydown(function(){
		calculation();
	});
	
	$("input[type='number']").change(function(){
		calculation();
	});
	
});

function calculation(){
	var total = 0;
    $("input[name='pfInput']").each(function(){
    	var val = $(this).val();
    	if(val != null && val != ""){
    		total += parseInt(val);
    	}
    });
    $("#zf").val(total);
    var type = $("#type").val();
    if(type == 1){
    	$("#yxta").html("");
    	$("#hgta").html("");
    	$("#jxta").html("");
    	if(total >= 90){
    		$("#yxta").html("√");
    	}else if(total >= 70 && total < 90){
    		$("#hgta").html("√");
    	}else if(total < 70){
    		$("#jxta").html("√");
    	}
    }else{
    	$("#my").html("");
    	$("#jbmy").html("");
    	$("#bmy").html("");
    	if(total >= 85){
    		$("#my").html("√");
    	}else if(total >= 60 && total < 85){
    		$("#jbmy").html("√");
    	}else if(total < 60){
    		$("#bmy").html("√");
    	}
    }
}

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function save() {
	//debugger
	var data = {};
	var ids = new Array();
	var vals = new Array();
	$("input[name='pfInput']").each(function(){
		ids.push($(this).attr("pfbId"));
		vals.push($(this).val());
	});
	data.taxxid = $("#taxxid").val();
	data.type = $("#type").val();
	data.ids = ids.toString();
	data.vals = vals.toString();
	
	$.ajax({
		cache : true,
		type : "POST",
		url : "/proposal/tabl/savePf",
		data : data,// 你的formid
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
			pfInput : {
				required : true
			}
		},
		messages : {
			pfInput : {
				required : icon + "必填项"
			}
		}
	});
}

