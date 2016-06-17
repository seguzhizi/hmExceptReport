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
    
    <title>故障处理</title>
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
                    <div class="title">故障管理</div>
                    <div class="titleChild" onclick="changeContent('exceptAction_init.action',5);">故障处理</div>
                    <div class="titleChild" onclick="changeContent('exceptQueryAction_init.action',6);">故障处理结果查看</div>
                    <div class="titleLine"></div>
                </div>
                <div class="core">
                    <div class="moudule" style="height:630px;">
                        <div class="moudule_title">
                            <div class="moudule_title_icon"></div> 故障信息
                        </div>
                        
                        <form action="exceptAction_updExcept.action" method="POST" onsubmit="return check();">
	                        <table class="info_tab" style="width:730px;margin-left:5px;" cellpadding="0" cellspacing="0">
	                            <tr>
	                                <td width="100px">终端编号</td>
	                                <td class="info_td"><s:property value="except.terminalId"/></td>
	                            </tr>
	                            <tr>
	                                <td>设备类型</td>
	                                <td class ="info_td"><input type="text" name="except.type" value="<s:property value="except.type"/>"/></td>
	                            </tr>
	                            <tr>
	                                <td style="">设备运行状态 </td>
	                                <td class="info_td"><input type="text" name="except.runState" value="<s:property value="except.runStatus"/>"/></td>
	                            </tr>
	                            <tr>
	                                <td>模块状态</td>
	                                <td class="info_td"><input type="text" name="except.mkzt" value="<s:property value="except.mkzt"/>"/></td>
	                            </tr>
	                            <tr>
	                                <td><label class="span_must" style="margin-top:2px;margin-right:5px;"> * </label>故障处理人</td>
	                                <td class="info_td">
	                                	<input type="text" id="dealPerson" name="except.dealPerson" 
	                                		notnull="notnull" notnullinfo="故障处理人不能为空"
	                                		value="<s:property value="except.dealPerson"/>"/>
		                                <span>存在多个故障处理人时,请使用","分割</span>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td>故障等级</td>
	                                <td class="info_td"><s:property value="except.level"/>&nbsp;级</td>
	                            </tr>
	                            <tr>
	                                <td><label class="span_must" style="margin-top:2px;margin-right:5px;"> * </label>故障处理说明</td>
	                                <td class="info_td"><textarea type="text" name="except.remark" class="mask_textarea" 
	                                		notnull="notnull" notnullinfo="故障处理说明不能为空"
	                                		id="remark" style="height:80px;width:400px;"></textarea></td>
	                            </tr>
	                        </table>
                        
                        <div style="margin-top:50px;margin-left:200px;">
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
    $("#dealPerson").change(function(){
    	var notnull = $(".notnull");
    	if(notnull.length > 0){
    		notnull.remove();
    	}
    	var dp = $("#dealPerson");
    	if($.trim(dp.val()) == ""){
    		dp.after("<span class='notnull' style='color:#ff4f15'>&nbsp;故障处理人不能为空</span>");
    	}
    });
    
    $("#remark").change(function(){
    	var notnull2 = $(".notnull2");
    	if(notnull2.length > 0){
    		notnull2.remove();
    	}
    	var remark = $("#remark");
    	if($.trim(remark.val()) == ""){
    		remark.after("<span class='notnull2' style='color:#ff4f15'>&nbsp;故障处理说明不能为空</span>");
    	}
    });
    
    $("#b_submit").click(function(){
    	var notnull = $(".notnull");
    	if(notnull.length > 0){
    		notnull.remove();
    	}
    	var dp = $("#dealPerson");
    	if($.trim(dp.val()) == ""){
    		dp.after("<span class='notnull' style='color:#ff4f15'>&nbsp;故障处理人不能为空</span>");
    	} else {
    		var notnull2 = $(".notnull2");
        	if(notnull2.length > 0){
        		notnull2.remove();
        	}
        	var remark = $("#remark");
        	if($.trim(remark.val()) == ""){
        		remark.after("<span class='notnull2' style='color:#ff4f15'>&nbsp;故障处理说明不能为空</span>");
        	} else {
        		alert("sss");
        		//$("form").submit();
        	}
    	}
    });
    */
    
    $("#b_submit").click(function(){
    	$("form").submit();
    });
    
    </script>
</body>

</html>

