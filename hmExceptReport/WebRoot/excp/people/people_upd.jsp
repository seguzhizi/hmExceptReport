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
            <div class="div_navigation">
            	<img src="<%=basePath%>images/userhead.jpg" style="margin-top:12px;" />
            		管理员登录 <a href="#">首页</a><font style="color:#e7e7eb;font-size:16px;">|</font><a href="#">退出</a></div>
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
                    <div class="title">修改个人信息</div>
                    <div class="titleChild" onclick="changeContent('peopleAction_listPeopleInfo.action',0);">个人资料</div>
                    <div class="titleChild" onclick="changeContent('peopleAction_initUpdatePwd.action',1);">修改个人密码</div>
                    <div class="titleChild" onclick="changeContent('peopleAction_initUpdPersonProfile.action',2);">修改个人信息</div>
                    <div class="titleLine"></div>
                </div>
                <div class="core">
                    <div class="moudule" style="height:630px;">
                        <div class="moudule_title">
                            <div class="moudule_title_icon"></div> 账户信息
                        </div>
                        
                        <form id="updPeopleForm" action="peopleAction_updPersonProfile.action" method="POST" onsubmit="return check();">
                        		
	                        <table class="info_tab" style="width:730px;margin-left:5px;" cellpadding="0" cellspacing="0">
	                            <tr>
	                                <td width="70px"><span class="span_must_1"> * </span>登录账户</td>
	                                <td class="info_td"><s:property value="updPeople.username"/></td>
	                            </tr>
	                            <tr>
	                                <td><span class="span_must"> * </span>状态</td>
	                                <td class ="info_td">
	                                	<select name="updPeople.status" id="status"> 
									        <option value="1">正常</option>   
									        <option value="2">锁定</option>   
									    </select>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td style=""><span class="span_must"> * </span>真实姓名 </td>
	                                <td class="info_td"><input type="text" id="realName" name="updPeople.realName"
	                                	value="<s:property value="updPeople.realName"/>"
		                                notnull="notnull" notnullinfo="真实姓名不能为空" placeholder="请输入真实姓名"/></td>
	                            </tr>
	                            <tr>
	                                <td><span class="span_must"> * </span>角色</td>
	                                <td class="info_td">
	                                	<s:select list="role_map" name="updPeople.roleId">
	                                	</s:select>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td><span class="span_must"> * </span>联系手机</td>
	                                <td class="info_td">
	                                	<input type="text" name="updPeople.celphone" value="<s:property value="updPeople.celphone"/>"
	                                	notnull="notnull" notnullinfo="联系手机不能为空" 
	                                	number='number' numberinfo="联系手机只能输入数字" placeholder="请输入11位手机号码" maxlength="11"/>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td><span class="span_must"> * </span>联系邮箱</td>
	                                <td class="info_td"><input type="text" name="updPeople.email" value="<s:property value="updPeople.email"/>" 
	                                	notnull="notnull" notnullinfo="联系邮箱不能为空" 
	                                	email='email' emailinfo="联系邮箱不符合规则" placeholder="请输入邮箱地址" />
	                                </td>
	                            </tr>
	                            <tr>
	                                <td>联系电话</td>
	                                <td class="info_td"><input type="text" name="updPeople.phone" value="<s:property value="updPeople.phone"/>" placeholder="请输入电话号码" /></td>
	                            </tr>
	                            <tr>
	                                <td>身份证号码</td>
	                                <td class="info_td"><input type="text" name="updPeople.idNo" value="<s:property value="updPeople.idNo"/>" placeholder="请输入身份证号码" /></td>
	                            </tr>
	                            <tr>
	                                <td>备注</td>
	                                <td class="info_td"><textarea type="text" name="updPeople.remark" class="mask_textarea"><s:property value="updPeople.remark"/></textarea></td>
	                            </tr>
	                        </table>
                        
	                        <div style="margin-top:50px;margin-left:250px;">
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
    
/*    
    //onkeypress="return is_number(event)"
    
    function is_number(e){  
    //IE 中 Event 对象使用 keyCode 获得键入字符的 Unicode 编码  
    //DOM 中 Event 对象 使用 charCode 获得键入字符的 Unicode 编码  
    var char_code = e.charCode ? e.charCode : e.keyCode;  
    //Unicode 编码中， 0~9 的编码的十进制 是 48~57 之间 ， 0为 48， 9 为57  
    if(char_code<48 || char_code >57){  
       // alert("只允许输入数字");     
        return false;  
    }  
    else{  
        return true;      
    }  
}
*/
    
    /*
    $("#b_submit").click(function(){
    	var real = $(".real");
    	if(real.length > 0){
    		real.remove();
    	}
    	var realName = $("#realName");
    	if($.trim(realName.val()) == ""){
    		realName.after("<span class='real' style='color:#ff4f15'>&nbsp;真实姓名不能为空</span>");
    	} else {
    		$("form").submit();
    	}
    });
    */
    
    //点击提交按钮，直接提交表单，但是表单有onsubmit的验证(check.js)
    $("#b_submit").click(function(){
    	$("form").submit();
    });
    
    
  //检测必输项是否有空
  /*
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
  */
    </script>
</body>

</html>

