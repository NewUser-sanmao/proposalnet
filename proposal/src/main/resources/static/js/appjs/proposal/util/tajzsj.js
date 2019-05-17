$().ready(function() {
	laydate({
        elem : '#birth'
    });
});

function update() {
	
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
