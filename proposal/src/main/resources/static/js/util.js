var proposalUtil = new Object();

proposalUtil.setSelect = function(url,data,id,defaultVal){
	$.ajax({
		type : "get",
		url : url,
		data : data,// 你的formid
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if(data!=null && data.results!=null){
				var html = "<option value=''>请选择</option>";
				for(var index in data.results){
					var sel = "";
					if(defaultVal!=null && defaultVal==data.results[index].id){
						sel = "selected='selected'";
					}
					html += "<option value='"+data.results[index].id+"' "+sel+">"+data.results[index].text+"</option>";
				}
				$("#"+id).html(html);
			}
		}
	});
};

proposalUtil.setSelect2 = function(url,data,id,defaultVal){
	$.ajax({
		type : "get",
		url : url,
		data : data,// 你的formid
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if(data!=null && data.results!=null){
				$("#"+id).select2({
			        data: data.results,
			        placeholder: "请选择"
			    });
				if(defaultVal!=null){
					$("#"+id).val(defaultVal).trigger('change');
				}
			}
		}
	});
};

proposalUtil.setSelect2PleaseChoose = function(url,data,id,defaultVal){
	$.ajax({
		type : "get",
		url : url,
		data : data,// 你的formid
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if(data!=null && data.results!=null){
				var sels = new Array();
				sels.push({"id":-1,"text":"请选择"});
				
				for(var i in data.results){
					sels.push(data.results[i]);
				}
				$("#"+id).select2({
			        data: sels,
			        placeholder: "请选择"
			    });
				if(defaultVal!=null){
					$("#"+id).val(defaultVal).trigger('change');
				}
			}
		}
	});
};

proposalUtil.setSelect2Checkbox = function(url,data,id,defaultVal){
	$.ajax({
		type : "get",
		url : url,
		data : data,// 你的formid
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if(data!=null && data.results!=null){
				$("#"+id).select2({
					multiple:true,
			        data: data.results,
			        placeholder: "请选择"
			    });
				if(defaultVal!=null){
					$("#"+id).val(defaultVal).trigger('change');
				}
			}
		}
	});
};

/**
 * 判断是不是图片
 * true:是,false:不是
 */
proposalUtil.isImg = function(fileName){
	var suffixIndex=fileName.lastIndexOf(".");  
	var suffix=fileName.substring(suffixIndex+1).toUpperCase();  
	if(suffix!="BMP"&&suffix!="JPG"&&suffix!="JPEG"&&suffix!="PNG"&&suffix!="GIF"){  
		return false;
	}
	return true;
};

//身份证验证
jQuery.validator.addMethod("legitimateSfz", function(value, element) {
	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
	if(reg.test(value) === false){ 
		return false;
	} 
	return true;   
}, "身份证输入不合法");
//手机号验证
jQuery.validator.addMethod("legitimateSjhm", function(value, element) {
	var reg = /(^\d{11}$)/;
	if(reg.test(value) === false){ 
		return false;
	} 
	return true;   
}, "手机号码输入不合法");
//密码强度验证
jQuery.validator.addMethod("intensityPwd", function(value, element) {
	if(value.length == 32){
		return true;
	}
	var reg = /^.*(?=.{6,})(?=.*\d)(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/;
	if(reg.test(value) === false){
		return false;
	}
	return true;
}, "密码强度不够。密码至少6位，需要同时有数字、字母、和特殊符号（!@#$%^&*?）");
//汉字验证
jQuery.validator.addMethod("chinese", function(value, element) {
	var reg = /^[\u4e00-\u9fa5]+$/;
	if(reg.test(value) === false){
		return false;
	}
	return true;   
}, "只能填写汉字");
//不能全是数字和字母
jQuery.validator.addMethod("noAllNumberAndLetter", function(value, element) {
	var reg = /^[a-zA-Z\d]+$/;
	if(reg.test(value) === true){
		return false;
	}
	return true;   
}, "不能全是数字和字母");