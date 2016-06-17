<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

	<base href="<%=basePath%>">
    
    <title>操作日志查询</title>
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
                    <div class="title">操作日志管理</div>
                    <div class="titleChild" onclick="changeContent('sysLogAction_init.action',5);">操作日志管理</div>
                    <div class="titleLine"></div>
                </div>
                <div class="core">
                    <form action="sysLogAction_listAllLogByPage.action" method="POST">
	                    <div class="moudule">
	                        <div class="moudule_title">
	                            <div class="moudule_title_icon"></div> 查询条件
	                        </div>
	                        <div class="div_condition" style="margin-left:25px;">
	                            <p>操作账号 <input type="text" name="username" value="<s:property value='username'/>"/></p>
	                        </div>
	                        <div class="error_tips" style="display:none">
	                            <div class="tip_icon"></div>
	                            <div>xxx输入有误！</div>
	                        </div>
	                        <div class="buttons">
	                            <input type="reset" class="input_button" value="" id="res"/>
	                            <input type="submit" class="input_button" value="" style="margin-left:50px;background-position-x:-424px;" />
	                        </div>
                    	</div>
                   	</form>

                    <div class="moudule" style="height:700px;">
                        <div class="moudule_title">
                            <div class="moudule_title_icon"></div> 数据列表
                            <!-- 
                            <div class="div_export" id="exportExcel">
                            	<img src="images/d_icon.jpg" style="margin-right:-2px;" /><label style="margin-top:0;">导出数据</label>
                            </div>
                             -->
                        </div>
                        <div class="div_table">
                            <table class="tittab" cellpadding="0" cellspacing="0" style="width:800px;">
                                <tr class="thtr">
                                    <td width="20px" class="titth">序号</td>
                                    <td width="40px" class="titth">用户名</td>
                                    <td width="40px" class="titth">IP</td>
                                    <td width="120px" class="titth">日志描述</td>
                                    <td width="80px" class="titth">操作时间</td>
                                </tr>
	                            <s:iterator value="logList" var="exp" status="st">
	                            	<tr>
		                            	<td class="tittdSingle"><s:property value="#st.index + 1"/></td>
		                            	<td class="tittd"><s:property value="#exp.username"/></td>
		                            	<td class="tittdSingle"><s:property value="#exp.ip"/></td>
		                            	<td class="tittd"><s:property value="#exp.logMsg"/></td>
		                            	<td class="tittdSingle">
		                            	<!-- 
		                            		<s:property value="#exp.logTime"/>
		                            	 -->
		                            	 <s:property value="@com.hm.excp.util.DateUtil@changeTimestamp(#exp.logTime)"/>
		                            	</td>
	                            	</tr>
	                            </s:iterator>
                            </table>
                        </div>
                        <div class="div_page">
                            <div class="total_div"> 
                            	总计：<span class="span_must"><s:property value='pageParams.totalRecords'/></span> 条 
                            </div>
                            <div class="num_page">
                            	<s:property value='pageParams.curPage'/>/<s:property value='pageParams.totalPages'/>
                            </div>
                             <a style="text-decoration:none;" href="" onclick="queryByPage(<s:property value='1'/>);return false">
                             	<div class="first_page"></div>
                             </a>
                             <a style="text-decoration:none;" href="" onclick="queryByPage(<s:property value='pageParams.curPage'/> - 1);return false">
                             	<div class="pre_page"></div>
                             </a>
                             <a style="text-decoration:none;" href="" onclick="queryByPage(<s:property value='pageParams.curPage'/> + 1);return false">
                             	<div class="next_page"></div>
                             </a>
                             <a style="text-decoration:none;" href="" onclick="queryByPage(<s:property value='pageParams.totalPages'/>);return false">
                             	<div class="last_page"></div>
                             </a>
                           	
                            <input class="input_page" type="text" id="c_page" value="<s:property value='pageParams.curPage'/>" 
                            		onkeypress="return is_number(event);"/>
                            <div type="button" name="" class="btn_jump" onclick="changeByPage();"></div>
                        </div>
                    </div>
                    <div style="height:10px;"></div>
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
    <script type="text/javascript" src="<%=basePath%>/common/js/hm_list.js" charset="UTF-8"></script>
    <script type="text/javascript" src="<%=basePath%>/common/js/WdatePicker/WdatePicker.js" charset="UTF-8"></script>
	 <script type="text/javascript">
    
    $(document).ready(function(){
		var menuId = "<s:property value='menuId'/>";
    	changeBg(menuId)
	});
    
    //分页跳转
    function queryByPage(curPage){
    	location.href="sysLogAction_listAllLogByPage.action?pageParams.curPage="+curPage;
    }
    
    //输入数字时页面跳转
    function changeByPage(){
    	var curPage = $("#c_page").val();
    	if($.trim(curPage) != ""){
	    	queryByPage(curPage);
    	}
    }
    
    </script>
    
    
</body>

</html>

