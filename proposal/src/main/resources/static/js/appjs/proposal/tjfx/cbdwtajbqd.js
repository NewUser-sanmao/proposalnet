
var prefix = "/proposal/tjfx"
$(function() {
	proposalUtil.setSelect('/proposal/zxjc/selectAllSelect',null,'zxjcid',null);
});

function dc(){
	if($("#zxjcid").val() == null || $("#zxjcid").val()==""){
		layer.msg('请选择届次');
		return;
	}
	
	layer.confirm('确认要导出吗？', {
		btn: ['确认','取消'] //按钮
	}, function(){
		//确认
		var action = "/proposal/taxx/exportJbqd";
	    var form = $("<form></form>");  
	    form.attr('action', action);  
	    form.attr('method', 'post');   
	    form.attr('target', '_blank');
	    
    	var input1 = $("<input type='hidden' name='zxjcId' />");  
    	input1.attr('value', $("#zxjcid").val());  
    	form.append(input1);
	    
	    form.appendTo("body");  
	    form.css('display', 'none');  
	    form.submit();
		
	    layer.msg('正在导出...');
	}, function(){
		//取消
	});
}

function dc_dg(){
	if($("#zxjcid").val() == null || $("#zxjcid").val()==""){
		layer.msg('请选择届次');
		return;
	}
	
	layer.confirm('确认要导出单个吗？', {
		btn: ['确认','取消'] //按钮
	}, function(){
		//确认
		var action = "/proposal/taxx/exportJbqdDg";
	    var form = $("<form></form>");  
	    form.attr('action', action);  
	    form.attr('method', 'post');   
	    form.attr('target', '_blank');
	    
    	var input1 = $("<input type='hidden' name='zxjcId' />");  
    	input1.attr('value', $("#zxjcid").val());  
    	form.append(input1);
	    
	    form.appendTo("body");  
	    form.css('display', 'none');  
	    form.submit();
		
	    layer.msg('正在导出...');
	}, function(){
		//取消
	});
}
