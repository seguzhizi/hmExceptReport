<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 -->
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

	<base href="<%=basePath%>">
    
    <title>修改密码</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <meta charset="utf-8" />
    
    <link href="<%=basePath%>/common/css/common.css" rel="stylesheet" media="screen">
    <link href="<%=basePath%>/common/css/index.css" rel="stylesheet" media="screen">
    <link href="<%=basePath%>/common/less/global.css" rel="stylesheet" media="screen">
</head>

<body>
<%--
    <div class="div_top">
        <div class="top_main">
            <div class="div_logo"></div>
            <div class="div_navigation"><img src="../images/userhead.jpg" style="margin-top:12px;" />  管理员登录  <a href="#">首页</a> <font style="color:#e7e7eb;font-size:16px;">|</font> <a href="#">退出</a></div>
        </div>
    </div>
 --%>
	<jsp:include page="../top.jsp"/>
	
    <div class="div_center">

        <div style="height:100px;"></div>
        <div class="div_main">
        
        <jsp:include page="../main.jsp"/>
        
            <div class="div_right">
            	 <div class="coreTitle">
            	 	<div class="title">个人信息</div>
                    <div class="titleChild" onclick="changeContent('peopleAction_listPeopleInfo.action',0);">个人资料</div>
                    <div class="titleChild" onclick="changeContent('peopleAction_initUpdatePwd.action',1);">修改个人密码</div>
                    <div class="titleChild" onclick="changeContent('peopleAction_initUpdPersonProfile.action',2);">修改个人信息</div>
                    <div class="titleLine"></div>
                </div>
                <div class="core">
                    <div class="moudule">
                        <div class="moudule_title">
                            <div class="moudule_title_icon"></div> 信息编辑 <span class="span_must"> （注：" * " 为必填项）</span>
                        </div>
                        <form action="" method="POST">
	                        <div class="div_condition" style="float:none;margin-left:90px;margin-top:20px;">
	                            <span class="span_must"> * </span>旧密码 <input type="password" id="old_p" name="" style="margin-left:25px;" />
	                        </div>
	                        <div class="div_condition" style="float:none;margin-top:20px;margin-left:90px;">
	                            <span class="span_must"> * </span>新密码 <input type="password" id="new_p1" name="" style="margin-left:25px;" />
	                        </div>
	                        <div class="div_condition" style="float:none;margin-top:20px;margin-left:90px;">
	                            <span class="span_must"> * </span>密码确认 <input type="password" id="new_p2" name="" style="margin-left:11px;" />
	                        </div>
	                        <div class="error_tips" style="display:none;">
	                            <div class="tip_icon"></div>
	                            <div>您输入的密码安全系数较低</div>
	                        </div>
	                        <div class="div_btns">
	                            <input type="reset" class="input_button" value="" />
	                            <input type="button" class="input_button" id="b_submit" value="" style="margin-left:50px;background-position-x:-742px;" />
	                        </div>
                        </form>
                    </div>
                    <div style="height:420px;"></div>
                </div>
            </div>
        </div>
        <!--底部-->
        <div class="div_bottom">
            <div class="div_copyright"></div>
        </div>
    </div>
	
    <script type="text/javascript" src="<%=basePath%>/common/js/jquery-1.9.0.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="<%=basePath%>/common/js/common.js" charset="UTF-8"></script>
    <script type="text/javascript">
   	
    $(document).ready(function(){
		var menuId = "<s:property value='menuId'/>";
    	changeBg(menuId)
	});
    
    function checkNull(){
    	var p1 = $(".o_p");
	    var p2 = $(".n_p1");
	    var p3 = $(".n_p2");
	    if(p1.length > 0){
	    	p1.remove();
	    }
	    if(p2.length > 0){
	    	p2.remove();
	    }
	    if(p3.length > 0){
	    	p3.remove();
	    }
    }
    
    $("#b_submit").click(function(){
    	
	    checkNull();
	    
	    var flag = true;
	    
	    var old_p = $.trim($("#old_p").val());
	    var new_p1 = $.trim($("#new_p1").val());
	    var new_p2 = $.trim($("#new_p2").val());
	    
	    if(old_p == "" ){
	    	var o_p = $("#old_p");
	    	o_p.after("<span class='o_p' style='color:#ff4f15'>&nbsp;旧密码不能为空</span>");
	    	flag = false;
	    } else if(new_p1 == "") {
	    	var n_p1 = $("#new_p1");
	    	n_p1.after("<span class='n_p1' style='color:#ff4f15'>&nbsp;新密码不能为空</span>");
	    	flag = false;
	    } else if(new_p2 == ""){
	    	var n_p2 = $("#new_p2");
	    	n_p2.after("<span class='n_p2' style='color:#ff4f15'>&nbsp;新密码确认不能为空</span>");
	    	flag = false;
	    } else {
	    	flag = checkPwdEquals();
	    }
		
	    if(flag){
	    	$.ajax({
    		   type: "POST",
    		   url: "peopleAction_checkPwdCorrect.action",
    		   data: {"oldPwd" : old_p, "newPwd" : new_p1},
    		   cache : false,
    		   success: function(data){
    			   //alert("Data Loaded: " + data);
    			   var result = $(".oldpwd");
    			   if(result.length > 0){
    				   result.remove();
    				}
    			   if(data == "ok"){
    				   alert("密码修改成功");
					} else if(data == "differ"){
						var o_p = $("#old_p");
						o_p.after("<span class='oldpwd' style='color:#ff4f15'>&nbsp;原密码输入错误</span>");
					} else {
						alert("密码修改出现异常");
					}
    		   }
	    	});
	    } 
    });
    
    
    
  //[密码]要素变化时检测
  /*
   	$("#old_p").bind({
   		change:function(){
   			checkNull();
   			var old_p = $.trim($("#old_p").val());
   			if(old_p != ""){
	   			//判断原密码是否正确
		    	$.get("peopleAction_checkPwdCorrect.action", { "oldPwd" : old_p},
	   				function(data){
	   				    //alert("Data Loaded: " + data);
	   				    var result = $(".oldpwd");
	   				    if(result.length > 0){
	   				    	result.remove();
	   				    }
	   					if(data == "ok"){
	   					} else {
	   						var o_p = $("#old_p");
	   						o_p.after("<span class='oldpwd' style='color:#ff4f15'>&nbsp;原密码输入错误</span>");
	   					}
	   			});
   			}
     	} 
    });
  
  */
  
  //[旧密码]要素变化时检测
   	$("#old_p").bind({
   		change:function(){
   			var p1 = $(".oldpwd");
   		    if(p1.length > 0){
   		    	p1.remove();
   		    }
   			var p2 = $(".o_p");
   		    if(p2.length > 0){
   		    	p2.remove();
   		    }
     	} 
    });
  
  //[密码]要素变化时检测
   	$("#new_p1").bind({
   		change:function(){
   			checkNull();
   			checkPwdEquals();
     	} 
    });
	
	//[密码确认]要素变化时检测
   	$("#new_p2").bind({
   		change:function(){
   			checkNull();
   			checkPwdEquals();
     	} 
    });
	
    //检测输入的密码是否一致
   	function checkPwdEquals(){
   		var new_p1 = $.trim($("#new_p1").val());
   		var new_p2 = $.trim($("#new_p2").val());
   		
   		var differ = $(".differ");
	    if(differ.length > 0){
	    	differ.remove();
	    }
	    
   		if(new_p1 == new_p2){
   			return true;
   		} else {
   			//alert("两次输入密码不一致");
   			var pwdCon = $("#new_p2");
   			pwdCon.after("<span class='differ' style='color:#ff4f15'>&nbsp;两次输入密码不一致</span>");
   			return false;
   		}
   	}
    
    </script>
    
</body>

</html>
