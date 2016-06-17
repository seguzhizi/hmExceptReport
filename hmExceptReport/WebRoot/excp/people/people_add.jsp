<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

	<base href="<%=basePath%>">
    
    <title>添加用户</title>
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
 	<jsp:include page="../top.jsp"/>

    <div class="div_center">
    	
        <div style="height:100px;"></div>
        <div class="div_main">
        <jsp:include page="../main.jsp"/>
        
            <div class="div_right">
                <div class="coreTitle">
                    <div class="title">用户管理</div>
                    <div class="titleChild" onclick="changeContent('peopleManageAction_initAddPeople.action',3);">用户添加</div>
                    <div class="titleChild" onclick="changeContent('peopleManageAction_initListAllPeople.action',3);">用户查看</div>
                    <!-- 
                    <div class="titleChild" onclick="changeContent('peopleAction_updatePersonProfile.action');">修改个人信息</div>
                     -->
                    <div class="titleLine"></div>
                </div>
                <div class="core">
                    <div class="moudule" style="height:625px;">
                        <div class="moudule_title">
                            <div class="moudule_title_icon"></div> 用户添加
                        </div>
                        
                        <form action="peopleManageAction_addPeople.action" method="POST">
	                        <table class="info_tab" style="width:730px;" cellpadding="0" cellspacing="0">
	                            <tr class="odd">
	                                <td width="70px"><span class="span_must"> * </span>用户角色</td>
	                                <td class="info_td">
	                                	<select name="newPeople.roleId" id="roleId"> 
									        <option value="1" selected="selected">维护员</option>   
									        <option value="2">管机员</option>   
									        <option value="3">部门领导</option>
									        <option value="4">主管</option>   
									        <option value="5">部门经理</option>   
									        <option value="6">银行主管</option>
									        <option value="7">超级管理员</option>   
									    </select>
									 </td>
	                            </tr>
	                            <tr>
	                                <td><span class="span_must"> * </span>登录账号</td>
	                                <td class="info_td"><input type="text" id="username" name="newPeople.username" notnull="notnull" notnullinfo="登陆账号不能为空" /></td>
	                            </tr>
	                            <tr class="odd">
	                                <td><span class="span_must"> * </span>登录密码</td>
	                                <td class="info_td"><input type="password" name="newPeople.password" id="pwd" notnull="notnull" notnullinfo="登陆密码不能为空" /></td>
	                            </tr>
	                            <tr>
	                                <td><span class="span_must"> * </span>密码确认</td>
	                                <td class="info_td"><input type="password" id="pwdConfirm" notnull="notnull" notnullinfo="密码确认不能为空" /></td>
	                            </tr>
	                            <tr class="odd">
	                                <td><span class="span_must"> * </span>真实姓名</td>
	                                <td class="info_td"><input type="text" name="newPeople.realName" notnull="notnull" notnullinfo="真实姓名不能为空" /></td>
	                            </tr>
	                            <tr>
	                                <td><span class="span_must"> * </span>状态</td>
	                                <td class="info_td">
	                                	<select name="newPeople.status" id="status"> 
									        <option value="1" selected="selected">正常</option>   
									        <option value="2">锁定</option>   
									    </select>
	                                </td>
	                            </tr>
	                            <tr class="odd">
	                                <td><span class="span_must"> * </span>联系邮箱</td>
	                                <td class="info_td">
	                                	<input type="text" name="newPeople.email" notnull="notnull" notnullinfo="联系邮箱不能为空" 
	                                		email='email' emailinfo="联系邮箱不符合规则" />
	                                </td>
	                            </tr>
	                            <tr>
	                                <td><span class="span_must"> * </span>联系手机</td>
	                                <td class="info_td">
	                                	<input type="text" name="newPeople.celphone" notnull="notnull" notnullinfo="联系手机不能为空" 
	                                	number='number' numberinfo="联系手机只能输入数字" />
	                                </td>
	                            </tr>
	                            <tr class="odd">
	                                <td>联系电话</td>
	                                <td class="info_td"><input type="text" name="newPeople.phone"/></td>
	                            </tr>
	                            <tr>
	                                <td>身份证号码</td>
	                                <td class="info_td"><input type="text" name="newPeople.idNo"/></td>
	                            </tr>
	                            <tr class="odd">
	                                <td>备注</td>
	                                <!-- 
	                                <td class="info_td"><input type="text" name="newPeople.remark"/></td>
	                                 -->
	                                <td class="info_td"><textarea type="text" name="newPeople.remark" class="mask_textarea" 
	                                		id="remark" style="height:60px;width:300px;"></textarea></td>
	                            </tr>
	                        </table>
	                            <div class="div_btns">
		                            <input type="reset" class="input_button" value="" />
		                            <input type="button" class="input_button" id="b_submit" value="" style="margin-left:50px;background-position-x:-742px;" />
		                        </div>
                        </form>
                    </div>
                    <div style="height:70px;"></div>
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
    <script type="text/javascript" src="<%=basePath%>/common/js/check.js" charset="UTF-8"></script>
	<script type="text/javascript">
	
	 $(document).ready(function(){
		var menuId = "<s:property value='menuId'/>";
	   	changeBg(menuId)
	});
	 
	$("#b_submit").click(function(){
		var flag = false;
		if(check()){
			if(checkPwdEquals()){
				var result = $(".hasReg");
				//alert(result.length);
			    if(result.length > 0){
			    } else {
			    	flag = true;
			    }
			}
		}
		if(flag){
			$("form").submit();
		}
		
	});
	
	//[密码]要素变化时检测
   	$("#pwd").bind({
   		change:function(){
   			checkPwdEquals();
     	} 
    });
	
	//[密码确认]要素变化时检测
   	$("#pwdConfirm").bind({
   		change:function(){
   			checkPwdEquals();
     	} 
    });
   	
    //检测输入的密码是否一致
   	function checkPwdEquals(){
   		var pwd = $.trim($("#pwd").val());
   		var pwd2 = $.trim($("#pwdConfirm").val());
   		
   		var differ = $(".differ");
	    if(differ.length > 0){
	    	differ.remove();
	    }
	    
   		if(pwd == pwd2){
   			return true;
   		} else {
   			//alert("两次输入密码不一致");
   			var pwdCon = $("#pwdConfirm");
   			pwdCon.after("<span class='differ' style='color:#ff4f15'>&nbsp;两次输入密码不一致</span>");
   			return false;
   		}
   	}
   	
    //检测用户名是否已被占用
   	$("#username").bind({
   	  change:function(){
   		  var input = $(this);
   		  var uname = $.trim($(this).val());
   		  if(uname != ""){
   			$.ajax({
     		   type: "POST",
     		   url: "peopleManageAction_checkUsernameRegistered.action",
     		   data: {"username" : uname},
     		   cache : false,
     		   success: function(data){
     			//alert("Data Loaded: " + data);
  				    var result = $(".hasReg");
  				    if(result.length > 0){
  				    	result.remove();
  				    }
  					if(data == "yes"){
  					} else {
  						input.after("<span class='hasReg' style='color:#ff4f15'>&nbsp;该用户名已被占用</span>");
  					}
     		   }
 	    	});
   			  /*
   			$.get("peopleAction_checkUsernameRegistered.action", { "username" : uname},
   				function(data){
   				    //alert("Data Loaded: " + data);
   				    var result = $(".hasReg");
   				    if(result.length > 0){
   				    	result.remove();
   				    }
   					if(data == "yes"){
   					} else {
   						input.after("<span class='hasReg' style='color:#ff4f15'>&nbsp;该用户名已被占用</span>");
   					}
   				});
   			  */
   		  }
   	  } 
   	});
   	
    //检测必输项是否有空
    function checkNotNull(){
   		var must = $(".span_must");
   		var flag = false;
   		
   		$(must).each(function(i){
   			var span = must[i];
   			var td = $(span).parent()[0];
    		var td2 = $(td).siblings("td");
    		var inputs = $(td2).children();
    		var input = $(inputs)[0];
    		var text = $(input).val();
	    		
    		if(text == null || $.trim(text) == ""){
	    		//alert( i +"---" + $(input).val());
	    		var title = $(td).html();
	    		alert(title.substring(title.indexOf("</span>") + 7) + "不能为空");
	    		flag = false;
	    		return false;
    		} else {
	    		flag = true;
    		}
   		});
   		return flag;
   	}
   	
    </script>
    
</body>

</html>

