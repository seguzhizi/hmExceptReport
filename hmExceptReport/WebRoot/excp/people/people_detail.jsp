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
	
    <title>个人资料</title>
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
                    <div class="title">个人信息</div>
                    <div class="titleChild" onclick="changeContent('peopleAction_listPeopleInfo.action',0);">个人资料</div>
                    <div class="titleChild" onclick="changeContent('peopleAction_initUpdatePwd.action',1);">修改个人密码</div>
                    <div class="titleChild" onclick="changeContent('peopleAction_initUpdPersonProfile.action',2);">修改个人信息</div>
                    <div class="titleLine"></div>
                </div>
                <div class="core">
                    <div class="moudule" style="height:625px;">
                        <div class="moudule_title">
                            <div class="moudule_title_icon"></div> 账户信息
                        </div>
                        <table class="info_tab" style="width:730px;" cellpadding="0" cellspacing="0">
                            <tr class="odd">
                                <td width="70px">用户角色</td>
                                <td class="info_td">
                                	<s:iterator value="role_map.keySet()" id="id" >
										<s:if test="#id==xpeople.roleId">
											<s:property value="role_map.get(#id)"/>
										</s:if>
									</s:iterator>
                                </td>
                            </tr>
                            <tr>
                                <td>登录账户</td>
                                <td class="info_td"><s:property value="xpeople.username"/></td>
                            </tr>
                            <tr class="odd">
                                <td>真实姓名</td>
                                <td class="info_td"><s:property value="xpeople.realName"/></td>
                            </tr>
                            <tr>
                                <td>状态</td>
                                <td class="info_td">
                                	<s:if test="xpeople.status == 1">正常</s:if>
                                	<s:else>锁定</s:else>
                                </td>
                            </tr>
                            <tr class="odd">
                                <td>身份证号码</td>
                                <td class="info_td"><s:property value="xpeople.idNo"/></td>
                            </tr>
                            <tr>
                                <td>联系电话</td>
                                <td class="info_td"><s:property value="xpeople.phone"/></td>
                            </tr>
                            <tr class="odd">
                                <td>联系手机</td>
                                <td class="info_td"><s:property value="xpeople.celphone"/></td>
                            </tr>
                            <tr>
                                <td>联系邮箱</td>
                                <td class="info_td"><s:property value="xpeople.email"/></td>
                            </tr>
                            <tr class="odd">
                                <td>注册时间</td>
                                <td class="info_td">
    	                            <s:property value="@com.hm.excp.util.DateUtil@changeTimestamp(xpeople.enableTime)"/>
                                </td>
                            </tr>
                            <tr>
                                <td>备注</td>
                                <td class="info_td">
                                		<s:property value="xpeople.remark"/>
                                		<!-- 
                                	<textarea type="text" name="updPeople.remark" class="mask_textarea">
                                	</textarea>
                                		 -->
                                </td>
                            </tr>
                        </table>
                        
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
	<script type="text/javascript">
    
    	$(document).ready(function(){
    		var menuId = "<s:property value='menuId'/>";
    		changeBg(menuId)
    	});
    	
    </script>
</body>

</html>

