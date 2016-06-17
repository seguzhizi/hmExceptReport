<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
	function exp(){
		if(confirm("确定要导出清单？")){
	    	var url="JasperAction.action";
	        window.open(url,'exportReport','height=5,width=5,status=yes,toolbar=no,menubar=no,location=no');
	        //location.href="JasperAction.action";
		}
    }
	</script>
	
  </head>
  
  <body>
    This is my JSP page.  welcome<br>
    404<br/>
    Ttest jasperreport<br>
    	<a href="#" onclick="exp();">PDF</a>
  </body>
</html>
