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

                    <div class="moudule" style="height:700px;">
                        <div class="moudule_title">
                            <div class="moudule_title_icon"></div> 数据列表
                            <div class="div_export">
                            <!-- 
                            	<img src="images/d_icon.jpg" style="margin-right:-2px;"/>
                             -->
                            	<label style="margin-top:0;font-size:15px;" id="refresh">更&nbsp;新</label>
                            </div>
                        </div>
                        <!-- 
                        <div class="buttons_1">
	                            <input type="submit" class="input_button" value="" 
	                            	style="margin-left:0px;margin-top:0px;background-position-x:-424px;" />
	                    </div>
                         -->
                        <div class="div_table">
                            <table class="tittab" cellpadding="0" cellspacing="0">
                                <tr class="thtr">
                                    <td width="40px" class="titth">序号</td>
                                    <td width="80px" class="titth">操作</td>
                                    <td width="100px" class="titth">终端编号</td>
                                    <td width="70px" class="titth">设备类型</td>
                                    <td width="80px" class="titth">设备运行状态</td>
                                    <td width="250px" class="titth">模块状态</td>
                                    <td width="70px" class="titth">故障等级</td>
                                    <td width="100px" class="titth">故障处理状态</td>
                                    <td width="80px" class="titth">故障处理人</td>
                                    <!-- 
                                    <td width="120px" class="titth">故障完成时间</td>
                                    <td width="250px" class="titth">故障处理说明</td>
                                     -->
                                </tr>
                            	<s:iterator value="exceptList" var="xp" status="s">
	                                <tr>
	                                    <td class="tittdSingle"><s:property value="#s.index + 1"/></td>
	                                    <td class="tittd">
	                                    	<div class="edit_div" style="margin-left:15px;"><div class="icon_div"></div>
	                                    		<a style="text-decoration:none;" href="" onclick="dealExcept('<s:property value="#xp.exceptId"/>');return false">
	                                    			处理
	                                    		</a>
	                                    	</div>
                                    	</td>
	                                    <td class="tittdSingle"><s:property value="#xp.terminalId"/></td>
	                                    <td class="tittd"><s:property value="#xp.type"/></td>
	                                    <td class="tittdSingle">
	                                    	<s:if test="#xp.runStatus == 'S'">
		                            			成功
		                            		</s:if>
		                            		<s:else>
		                            			失败
		                            		</s:else>
	                                    </td>
	                                    <td class="tittd"><s:property value="#xp.mkzt"/></td>
		                            	<td class="tittdSingle"><s:property value="#xp.level"/></td>
		                            	
		                            	<td class="tittd">
		                            		<s:if test="#xp.processState == 1 ">
		                            			已分发
		                            		</s:if>
		                            		<s:else>
		                            			完成处理
		                            		</s:else>
		                            	</td>
		                            	<td class="tittdSingle"><s:property value="#xp.dealPerson"/></td>
		                            	<!-- 
		                            	<td class="tittd"><s:property value="#xp.dealTime"/></td>
		                            	<td class="tittdSingle"><s:property value="#xp.remark"/></td>
		                            	 -->
	                                </tr>
                            	</s:iterator>
                            </table>
                        </div>
                        <div class="div_page">
                        <!-- 
                        <input type="text" id="clock" size="35" />
                         -->
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
    
    $("#refresh").click(function(){
    	location.href="exceptAction_listAllExcept.action?pageParams.curPage=1";
    });
    
    //分页跳转
    function queryByPage(curPage){
    	location.href="exceptAction_listAllExcept.action?pageParams.curPage="+curPage;
    }
    
    //输入数字时页面跳转
    function changeByPage(){
    	var curPage = $("#c_page").val();
    	if($.trim(curPage) != ""){
	    	queryByPage(curPage);
    	}
    }
    
    //每隔一分钟，无限循环获取最新的故障信息
    var int=self.setInterval("getExceptList()",60000)
    function getExceptList() {
    	//var t = new Date()
    	//document.getElementById("clock").value = t
    	location.href="exceptAction_listAllExcept.action?pageParams.curPage=1";
    }
    
    function dealExcept(obj){
    	location.href="exceptAction_initUpdExcept.action?exceptId=" + obj;
    }
    
    </script>
    
    
</body>

</html>

