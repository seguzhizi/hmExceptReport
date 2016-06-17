
/**
 * 检测表单的元素是否符合要求
 * 当用户提交表单时，触发此函数，判断是否符合要求，进而决定表单是否提交
 * @author zjj
 * @returns boolean flag
 * 			<p>true  : 表示可以提交  flase : 表示不能提交
 * <p>使用步骤如下：
 * <p>1.在需要检测的表单form里面添加onsubmit事件
 * <p>比如：&lt;form action="peopleAction_updatePersonProfile.action" method="POST" onsubmit="return check();"&gt;
 * <p>2.在需要检测的元素上面添加属性
 * <p>比如要检测非空:
 * &lt;input type="text" name="updPeople.idNo" notnull="notnull" notnullinfo="身份证号码不能为空"/&gt;
 * <p>notnull属性是该元素被获取的标志,必须添加,notnullinfo是将要显示给用户看的错误提示信息
 * <p>检测只能输入数字：
 * &lt;input type="text" name="updPeople.idNo" number='number' numberinfo="身份证号码只能输入数字"/&gt;
 * <p>使用方式相同
 * 
 */
function check(){
	
	//标志位
	var flag = true;
	
	/*************************************1.检测是否为空************************************/
	
	//1.检测是否为空
	//获取错误的提示信息
	var remindInfo = $(".notnull_Info");
	$(remindInfo).each(function(j){
		//如果错误信息已经存在，先全部删除
		var ri = $(remindInfo);
		ri.remove();
	});
	
	//获取不能为空的元素(即有这个notnull='notnull'属性的元素,支持input元素)
	var notnull = $("input[notnull='notnull']");
	$(notnull).each(function(i){
		var nu = $(notnull[i]);
		//遍历这些元素,查看其值是否为空
		if($.trim(nu.val()) == ""){
			var info = nu.attr("notnullinfo");
			//如果为空,则在该元素后面添加如下的提示信息
			nu.after("<b class='notnull_Info' style='color:#ff4f15'>" + info + "</b>");
			//将标志位置为false,阻止表单的提交
			flag = false;
		}
	});
	
	//获取不能为空的元素(即有这个notnull='notnull'属性的元素,支持textarea元素)
	var notnull = $("textarea[notnull='notnull']");
	$(notnull).each(function(i){
		var nu = $(notnull[i]);
		//遍历这些元素,查看其值是否为空
		if($.trim(nu.val()) == ""){
			var info = nu.attr("notnullinfo");
			//如果为空,则在该元素后面添加如下的提示信息
			nu.after("<b class='notnull_Info' style='color:#ff4f15'>" + info + "</b>");
			//将标志位置为false,阻止表单的提交
			flag = false;
		}
	});
	
	/*************************************2.检测是否为数字************************************/
	
//	//2.检测是否为数字
//	//获取错误的提示信息
//	var remindInfo = $(".number_Info");
//	$(remindInfo).each(function(j){
//		//如果错误信息已经存在，先全部删除
//		var ri = $(remindInfo);
//		ri.remove();
//	});
//	
//	//获取只能输入数字的元素(即有这个number='number'属性的元素,目前只支持input元素)
//	var number = $("input[number='number']");
//	$(number).each(function(i){
//		var num = $(number[i]);
//		//遍历这些元素,如果不为空则继续判断
//		var numberVal = $.trim(num.val());
//		if(numberVal != ""){
//			var strP=/^\d+(\.\d+)?$/;
//			if(!strP.test(numberVal)){
//				var info = num.attr("numberinfo");
//				//如果为空,则在该元素后面添加如下的提示信息
//				num.after("<b class='number_Info' style='color:#ff4f15'>" + info + "</b>");
//				//将标志位置为false,阻止表单的提交
//				flag = false;
//			}
//		}
//	});
	
	
	//2.检测是否为数字
	//获取错误的提示信息
	var remindInfo = $(".number_Info");
	$(remindInfo).each(function(j){
		//如果错误信息已经存在，先全部删除
		var ri = $(remindInfo);
		ri.remove();
	});
	
	//获取只能输入数字的元素(即有这个number='number'属性的元素,目前只支持input元素)
	var number = $("input[number='number']");
	$(number).each(function(i){
		var num = $(number[i]);
		//遍历这些元素,如果不为空则继续判断
		var numberVal = $.trim(num.val());
		if(numberVal != ""){
			var strP=/^\d+(\.\d+)?$/;
			if(!strP.test(numberVal)){
				var info = num.attr("numberinfo");
				//如果为空,则在该元素后面添加如下的提示信息
				num.after("<b class='number_Info' style='color:#ff4f15'>" + info + "</b>");
				//将标志位置为false,阻止表单的提交
				flag = false;
			}
		}
	});
	
	/*************************************3.检测邮箱************************************/
	
	//3.检测邮箱
	var remindInfo = $(".email_Info");
	$(remindInfo).each(function(j){
		var ri = $(remindInfo);
		ri.remove();
	});
	
	var emails = $("input[email='email']");
	$(emails).each(function(i){
		var email = $(emails[i]);
		//遍历这些元素,如果不为空则继续判断
		var emailVal = $.trim(email.val());
		if(emailVal != ""){
		    var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;  
		    if (!pattern.test(emailVal)) {
				var info = email.attr("emailinfo");
				//如果为空,则在该元素后面添加如下的提示信息
				email.after("<b class='email_Info' style='color:#ff4f15'>" + info + "</b>");
				//将标志位置为false,阻止表单的提交
				flag = false;
		    }  
		}
	});
	
	//4.检测输入的长度是否符合规则
	
	
	return flag;
}



/*
$.fn.check = function() {
    //在这里面,this指的是用jQuery选中的元素
    this.css('color', 'red');
    this.each(function() {
        //对每个元素进行操作
        $(this).append(' ' + $(this).attr('href'));
    }))
}
*/
/*
var check = {
		alert("ssss")
	checkNull : function(){
		alert("ssss")
	},
	
  	checkNumber : function(){
  		alert("2222")
  	}
	
	return false;
};
*/