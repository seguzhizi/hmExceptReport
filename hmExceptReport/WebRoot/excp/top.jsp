<%@ page language="java" import="java.util.*,com.hm.excp.util.*,com.hm.excp.dao.pojo.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	<base href="<%=basePath%>">
<%--

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

    
    <title>角色管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <meta charset="utf-8" />
    
    <link href="<%=basePath%>/css/common.css" rel="stylesheet" media="screen">
    <link href="<%=basePath%>/css/index.css" rel="stylesheet" media="screen">
    <link href="<%=basePath%>/less/global.css" rel="stylesheet" media="screen">
</head>

<body>
	--%>
	
    <div class="div_top">
        <div class="top_main">
            <div class="div_logo"></div>
            <div class="div_navigation">
            	<img src="<%=basePath%>/common/images/userhead.jpg" style="margin-top:12px;" />
            	[<%=((People)session.getAttribute(ExceptConstant.XPEOPLE_INFO.XPEOPLE)).getUsername() %>]&nbsp;  
            	<a href="#" onclick="main();return false;">首页</a>
            	<font style="color:#e7e7eb;font-size:16px;">|</font>
            	<a href="#" onclick="logout();return false;">退出</a>
            </div>
        </div>
    </div>
    
	<%--
    <div class="div_center">

        <div style="height:100px;"></div>
        <div class="div_main">
        
          	<div class="menu">
                <div class="menuMain">
                    <div class="ico"></div>个人信息
                </div>
                <!-- 
                <div class="menuChild" onclick="javascript:alert('sssssss')">个人资料1</div>
                 -->
                <div class="menuChild" onclick="changeContent('peopleAction_peopleInfo.action',0);">个人资料</div>
                <div class="menuChild" onclick="changeContent('peopleAction_updatePwd.action',1);">修改个人密码</div>
                <div class="menuChild" onclick="changeContent('peopleAction_updatePersonProfile.action',2);">修改个人信息</div>
                <div class="menuMain">
                    <div class="ico" style="background-position-x: -66px;"></div>系统管理
                </div>
                <div class="menuChild" onclick="changeContent('peopleAction_initAddPeople.action',3);">用户管理</div>
                <div class="menuChild">角色管理</div>
                <div class="menuChild">操作日志管理</div>
                <div class="menuMain">
                    <div class="ico" style="background-position-x: -22px;"></div>查询统计
                </div>
                <div class="menuChild">审核结果统计</div>
                <div class="menuChild">设备信息统计</div>
                <div class="menuChild">终端监控</div>
				<!-- 
                <div class="menuMain">
                    <div class="ico" style="background-position-x: -44px;"></div>远程授权
                </div>
                <div class="menuChild">审核列表</div>
                <div class="menuChild">审核结果查看</div>
				 -->

                <!-- 
                <div class="menuChild">机构管理</div>
                <div class="menuChild">设备管理</div>
                <div class="menuChild">远程授权配置</div>
                 -->
				<!-- 
                <div class="menuMain">
                    <div class="ico" style="background-position-x: -88px;"></div>软件版本管理
                </div>
                <div class="menuChild">软件版本新增</div>
				 -->
            </div>
            --%>
            
            <%--
            <jsp:include page="updatePwd.jsp"/>
            <jsp:include page="index.jsp"/>
            <%@include file="updatePwd.jsp"%>
            <div class="div_right">
	            <iframe id="workFrame" name="workFrame" frameborder="0" scrol="yes"
					style="width:90%;height:400px;overflow-y:auto;margin:0px auto auto 0px;" src="" >
				</iframe>
            --%>
		 	<%--
                <div class="coreTitle">
                    <div class="title">个人信息</div>
                    <div class="titleChild">个人资料</div>
                    <div class="titleChild">修改个人密码</div>
                    <div class="titleChild">修改个人信息</div>
                    <div class="titleLine"></div>
                </div>
                <div class="core">
                    <div class="moudule">
                        <div class="moudule_title">
                            <div class="moudule_title_icon"></div> 信息编辑 <span class="span_must"> （注：" * " 为必填项）</span>
                        </div>
                        <div class="div_condition" style="float:none;margin-left:90px;margin-top:20px;">
                            <span class="span_must"> * </span>旧密码 <input type="password" name="" style="margin-left:25px;" />
                        </div>
                        <div class="div_condition" style="float:none;margin-top:20px;margin-left:90px;">
                            <span class="span_must"> * </span>新密码 <input type="password" name="" style="margin-left:25px;" />
                        </div>
                        <div class="div_condition" style="float:none;margin-top:20px;margin-left:90px;">
                            <span class="span_must"> * </span>密码确认 <input type="password" name="" style="margin-left:11px;" />
                        </div>
                        <div class="error_tips">
                            <div class="tip_icon"></div>
                            <div>您输入的密码安全系数较低</div>
                        </div>
                        <div class="div_btns">
                            <input type="button" class="input_button" value="" />
                            <input type="button" class="input_button" value="" style="margin-left:50px;background-position-x:-742px;" />
                        </div>
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

</body>

</html>

    <script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.0.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="<%=basePath%>/js/common.js" charset="UTF-8"></script>
 	<script type="text/javascript" src="js/location.js" charset="UTF-8"></script>
 	 --%>
 	<script type="text/javascript" src="<%=basePath%>/common/js/location.js" charset="UTF-8"></script>
    <script type="text/javascript">
	//function changeContent(locale){
//		alert("跳转至：" + locale)
//		location.href=locale;
//	}
	function main(){
		location.href="loginAction_login.action";
	}
	function logout(){
		location.href="logoutAction_logout.action";
	}
    
    </script>
