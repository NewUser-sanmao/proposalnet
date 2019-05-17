$(function() {
	proposalUtil.setSelect('/proposal/zxjc/selectAllSelect',null,'zxjcid',null);
	proposalUtil.setSelect('/proposal/lbyblsx/selectAllSelect',null,'lbyblsxid',null);
});

function search(){
	var data = $('#signupForm').serialize();
	var query = {};
	if(data!=null ){
		var a = data.split("&");
		for(var index in a){
			var b = a[index].split("=");
			if(b.length == 2 && b[1] != ""){
				query[b[0]] = "%"+b[1]+"%";
			}
		}
	}
	var zxjcid = $("#zxjcid").val();
	if(zxjcid!=null && zxjcid!=""){
		query.zxjcid = zxjcid;
	}
	var lbyblsxid = $("#lbyblsxid").val();
	if(lbyblsxid!=null && lbyblsxid!=""){
		query.lbyblsxid = lbyblsxid;
	}
	
	reLoad(query);
	
	layer.close(search_index);
}