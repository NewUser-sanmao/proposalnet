$().ready(function() {
	validateRule();
	//proposalUtil.setSelect2Checkbox('/proposal/cbdw/selectAllSelectByTaxxId',{taxxid:$("#taxxid").val()},'mbdw',null);
	var data = {};
	data.taxxid = $("#taxxid").val();
	if($("#lx").val() == 2){//排除掉自己的
		data.notUserId = $("#userId").val();
	}
	//debugger
	$.ajax({
		type : "get",
		url : '/proposal/cbdw/selectAllSelectByTaxxId',
		data : data,// 你的formid
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if(data!=null && data.results!=null){
				$("#mbdw").select2({
					multiple:true,
			        data: data.results,
			        placeholder: "请选择"
			    });
			}
            $("input[name='type'][value='2']").removeAttr("disabled");
			if(data.results.length<=0){
                $("input[name='type'][value='2']").attr("checked",false);
                $("input[name='type'][value='2']").attr("disabled","disabled");
                $("input[name='type'][value='1']").attr("checked",true);
                $("#mbdwDiv").addClass("hidden");
			}
		}
	});
	
	if($("#lx").val() == 1){
		$("#mbdwDiv").removeClass("hidden");
	}

	$("input[name='type']").click(function(){
		var val = $(this).val();
		if(val == 2){
			$("#mbdwDiv").removeClass("hidden");
		}else{
			$("#mbdwDiv").addClass("hidden");
		}
	});

	//2019-04-03 协办单位只能提交交流意见给承办单位	0：承办单位 1：协办单位 2：分办单位
    var dealType = $("#dealType").val();
    if(dealType == 1){
        $("input[name='type'][value='2']").attr("checked",true);
        $("input[name='type'][value='1']").attr("checked",false);

        $("#mbdwDiv").removeClass("hidden");
	}

	
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	var nr = $("#nr").val();
    // var dealType = $("#dealType").val();//0：承办单位 1：协办单位 2：分办单位
	var type  = $('input[name="type"]:checked').val();
    if(type==2){
		var mbdw = $("#mbdw").val();
        if(mbdw==null||mbdw==""||mbdw==undefined){
            parent.layer.msg("请填选择目标单位");
            return;
        }
	}
	if(nr==null||nr==""||nr==undefined){
        parent.layer.msg("请填写内容");
        return;
	}

	$("#signupForm").ajaxSubmit({
        type: 'post', // 提交方式 get/post
        url: '/proposal/xx/save', // 需要提交的 url
        success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
        	if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg);
			}
        }
    });
	
	/*$.ajax({
		cache : true,
		type : "POST",
		url : "/proposal/xx/save",
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