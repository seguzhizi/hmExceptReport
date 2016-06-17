/**
 * 功能通用js
 * @version 1.0
 * @author lingyh
 * @date 2013-8-6
 */
/**
 * 删除选中的记录
 * @param {Object} formId 
 */
function delChecked(formId){
	var b=	confirm("是否删除?");
	if(b){
	    $('#'+formId).submit();		
	 }
}
/**
 * 查询
 * @param {Object} formId
 */
function submitSearchForm(formId){
	if(validateSearchForm(formId)){
		$('#'+formId).submit();
	}
}
/***
 * form表单提交异步请求
 * @param {Object} formId 要提交的表单ID
 * @param {Object} jumpUrl 提交成功之后，跳转的url
 * @param {Object} successDesc 提交成功之后的描述
 * @param {Object} failDesc    提交失败之后的描述
 */
function submitFormData(formId,jumpUrl,successDesc,failDesc) {
	var data = $("#"+formId).serialize() ;
	var url = $("#"+formId).attr("action") ;
	$.ajax( {
				type : "post",
				url : url,
				dataType : 'json',
				data:data,
				success : function(data, textStatus) {
					displayMessageDiv(formId + "Res");//显示消息层
					if (data["responseCode"] == null || SUCCESS_CODE == data["responseCode"]) {
						var desc = "操作成功！";
						if(successDesc != null && successDesc != ''){
							desc = successDesc;		
						}
						if(jumpUrl != null){
							$("#" + formId + "Res").html(desc+"页面正在进行跳转，请勿操作！");
							document.getElementById(formId + "Res").style.display = "block";
							setTimeout(function(){//多少秒之后自动跳转
								window.location.href = jumpUrl;
							},2000);
						}else{
							$("#" + formId + "Res").html(desc);
						}
					} else {//错误提示
						if(jumpUrl != null){
						$("#" + formId + "Res").html(respResult(data["responseCode"])+"页面正在进行跳转，请勿操作！");
							document.getElementById(formId + "Res").style.display = "block";
							setTimeout(function(){//多少秒之后自动跳转
								window.location.href = jumpUrl;
							},2000);
						}else{
							$("#" + formId + "Res").html(respResult(data["responseCode"]));
						}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					displayMessageDiv(formId + "Res");//显示消息层
					$("#" + formId + "Res").html(errorText("操作失败，系统内部错误，请联系管理员！ "));
				}
			});
}

/**
 * 显示隐藏的父div
 * @param {Object} divId
 */
function displayMessageDiv(divId){
	if(typeof(document.getElementById(divId)) != 'undefined' && typeof(document.getElementById(divId).parentElement) != 'undefined'){
		document.getElementById(divId).parentElement.style.display = "block";
	}
}
/**
 * 隐藏的父div
 * @param {Object} divId
 */
function hiddenMessageDiv(divId){
	if(typeof(document.getElementById(divId)) != 'undefined' && document.getElementById(divId) != null  
				&& typeof(document.getElementById(divId).parentElement) != 'undefined' && document.getElementById(divId).parentElement != null){
		document.getElementById(divId).parentElement.style.display = "none";
	}
}
/**
 * 隐藏的父div
 * @param {Object} divId
 */
function hiddenResponseInfoDiv(){
	if(typeof($('.responseInfo')) != 'undefined' && $('.responseInfo') != null && $('.responseInfo')[0] != null 
				&& typeof($('.responseInfo')[0].parentElement) != 'undefined' && $('.responseInfo')[0].parentElement != null){
		$('.responseInfo')[0].parentElement.style.display = "none";
	}
}
/**
 * 开始时间
 * @return {TypeName} 
 */
function getStartDate() {
	return new WdatePicker({
		maxDate:new Date()	
	});
}
/**
 * 根据选定开始时间的初始化结束时间控件
 * @param {Object} startDateId
 * @return {TypeName} 
 */
function getEndDate(startDateId){
	if(startDateId != null ){
		var startDateStr = $('#'+startDateId).val();
		if(startDateStr != null && startDateStr != ''){
			return new WdatePicker({
				minDate:new Date(startDateStr)	
			});
		}
	}
}
/**
 * 两个时间比较
 * @param {Object} startDateId
 * @param {Object} endDateId
 */
function  compTwoDate(startDateId,endDateId){
	var startDateStr = $("#"+startDateId).val();
	var endDateStr = $("#"+endDateId).val();
	var startDate = null;
	var endDate = null;
	if(startDateStr != null && startDateStr != ''){
		startDate = new Date(startDateStr);
	}
	if(endDateStr != null && endDateStr != ''){
		endDate = new Date(endDateStr);
	}
	if(startDate != null && endDate != null &&  startDate > endDate){
		displayMessageDiv("responseInfoDiv");//显示消息层
		$("#responseInfoDiv").html(errorText("开始时间不能大于结束时间！"));
		document.getElementById(startDateId).focus();
	}else{		
		$("#responseInfoDiv").html("");
		hiddenMessageDiv("responseInfoDiv");//隐藏消息层
	}
	
}
/**
 * 查询验证函数
 * @param {Object} formId
 * @return {TypeName} 
 */
function validateSearchForm(formId){
	var form = $("#"+formId)[0];
	for(var i = 0 ; i < form.length ; i++){
		if(typeof($(form[i]).attr("startDate")) !='undefined'){
			var valStr = $(form[i]).attr("startDate");
			var endDate ;
			var msg = "";
			if(valStr.indexOf(",") > -1){
				var strArr = valStr.split(",");
				endDate = $("#"+strArr[0]).val();
				msg = strArr[1];
			}else{
				endDate = $("#"+valStr).val();
			}
			var startDate = form[i].value;
			if(startDate != null && startDate != "" && endDate != null && endDate != "" && startDate > endDate ){
				displayMessageDiv("responseInfoDiv");//显示消息层
				$("#responseInfoDiv").html(errorText(msg+"开始时间不能大于结束时间！"));
				return false;
			}
		}
		if(typeof($(form[i]).attr("requiredOnlyOne")) !='undefined'){//需要其中一个必填项的，所有可能需要必填的元素ID都作为requiredOnlyOne的值传入
			var valAttr = $(form[i]).attr("requiredOnlyOne");
			var msg = "";
			var valStr = "";
			if(valAttr != null && valAttr != ""){
				if(valAttr.indexOf("#") > -1){
					var jArr = valAttr.split("#");
					if(jArr[0] != null && jArr[0] != ""){
						valStr = jArr[0];
					}
					if(jArr[1] != null && jArr[1] != ""){
						msg = jArr[1];
					}
				}else{
					valStr = valAttr;
				}
				var isNotNull = false;
				if(valStr != "" && valStr.indexOf(",") > -1){
					var strArr = valStr.split(",");
					$.each(strArr,function(i,item){
						if(item != null && item != '' && typeof($('#'+item)) != 'undefined' && $('#'+item) != null){
							var value  = $('#'+item).val();
							if(value != null && value != ''){
								isNotNull = true;
								return;
							}else{
								if(msg == ""){
									msg = "带红色*号的项必须填写一个！";
								}
							}
						}
						});
				}else{
					var value  = $('#'+valStr).val();
					if(value != null && value != ''){
						isNotNull = true;
					}
					if(msg == ""){
						msg = "带红色*号的项必须填写！";
					}
				}
				if(!isNotNull){//全部为空
					displayMessageDiv("responseInfoDiv");//显示消息层
					$("#responseInfoDiv").html(errorText(msg));
					return false;
				}
			}
			
		}
	}
	
	hiddenMessageDiv("responseInfoDiv");//隐藏消息层
	return true;
}



