<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
	<base href="<%=basePath%>">
    <meta charset="utf-8" />
    <title>终端故障处理管理系统</title>
    <link href="<%=basePath%>/common/css/common.css" rel="stylesheet" media="screen">
    <link href="<%=basePath%>/common/css/login.css" rel="stylesheet" media="screen">
</head>
<body>
    <div class="div_top">
    </div>
    <div class="div_center">
        <div class="div_content">
            <div class="div_logo"></div>
            <div class="div_text"></div>
            <div class="div_login">
            	<form action="loginAction_login.action" method="POST">
	                <input type="text" class="login_input" name="xpeople.username" value="zjj" style="margin-top:62px;"><br />
	                <input type="password" class="login_input" name="xpeople.password" value="123456" style="margin-top:5px;">
	                <div class="login_hint"><s:actionerror/></div>
	                <input type="submit" class="btn_login" value="" id="login"/>
            	</form>
            </div>
        </div>
    </div>
    <div class="div_bottom">
        <div class="div_copyright"></div>
    </div>

    <script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.0.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="<%=basePath%>/js/common.js" charset="UTF-8"></script>
    <script type="text/javascript">
    
    </script>
</body>
</html>