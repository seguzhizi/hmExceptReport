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
    
    <title>查询用户</title>
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
                    <div class="title">用户管理</div>
                    <div class="titleChild" onclick="changeContent('peopleManageAction_initAddPeople.action',3);">用户添加</div>
                    <div class="titleChild" onclick="changeContent('peopleManageAction_initListAllPeople.action',3);">用户查看</div>
                    <div class="titleLine"></div>
                </div>
                <div class="core">
                    <form action="peopleManageAction_listAllPeople.action" method="POST">
	                    <div class="moudule">
	                        <div class="moudule_title">
	                            <div class="moudule_title_icon"></div> 查询条件
	                        </div>
	                        <div class="div_condition" style="margin-left:25px;">
	                            <p>用户账号 <input type="text" name="queryPeopleBean.username" value="<s:property value='queryPeopleBean.username'/>"/></p>
	                        </div>
	                        <div class="div_condition" style="margin-left:25px;">
	                            <p>用户名称 <input type="text" name="queryPeopleBean.realName" value="<s:property value='queryPeopleBean.realName'/>"/></p>
	                        </div>
	                        <div class="div_condition" style="margin-left:25px;">
	                            <p>用户角色
								    <s:select name="queryPeopleBean.roleId" id="roleId"
								    	list="#{0:'请选择',1:'维护员',2:'管机员',3:'部门领导',4:'主管',5:'部门经理',6:'银行主管',7:'超级管理员'}"/>
	                            </p>
	                        </div>
	                        <div class="div_condition" style="margin-left:25px;">
	                            <p>用户状态
								    <s:select name="queryPeopleBean.status" id="staus"
								    	list="#{0:'请选择',1:'正常',2:'锁定'}"/>
	                            </p>
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
                             <div class="div_export">
                            	<img src="images/d_icon.jpg" style="margin-right:-2px;" /><label style="margin-top:0;">导出数据</label>
                            </div>
                             -->
                        </div>
                        <div class="div_table">
                            <table class="tittab" cellpadding="0" cellspacing="0">
                                <tr class="thtr">
                                    <td width="40px" class="titth">序号</td>
                                    <td width="200px" class="titth">操作</td>
                                    <td width="100px" class="titth">登陆账号</td>
                                    <td width="100px" class="titth">角色</td>
                                    <td width="50px" class="titth">状态</td>
                                    <td width="100px" class="titth">真实姓名</td>
                                    <td width="150px" class="titth">身份证号</td>
                                    <td width="120px" class="titth">联系电话</td>
                                    <td width="100px" class="titth">联系手机</td>
                                    <td width="150px" class="titth">联系邮箱</td>
                                </tr>
                            	<s:iterator value="xpeopleList" var="xp" status="s">
	                                <tr>
	                                    <td class="tittdSingle"><s:property value="#s.index + 1"/></td>
	                                    <td class="tittd">
	                                    	<div class="edit_div" style="margin-left:15px;"><div class="icon_div"></div>
	                                    		<a style="text-decoration:none;" href="" onclick="recoverPwd('<s:property value="#xp.sid"/>');return false">
	                                    			重置密码
	                                    		</a>
	                                    	</div>
	                                    	<div class="edit_div" style="margin-left:15px;"><div class="icon_div"></div>
	                                    		<a style="text-decoration:none;" href="" onclick="updatePeople('<s:property value="#xp.sid"/>');return false">
	                                    			编辑
	                                    		</a>
	                                    	</div>
	                                    	<s:if test="#xp.username != 'admin' ">
		                                    <div class="del_div" style="margin-left:15px;"><div class="icon_div" style="background-position-x:-19px;"></div>
		                                    	<a style="text-decoration:none;" href="" onclick="deletePeople('<s:property value="#xp.sid"/>');return false">
	                                    			删除
	                                    		</a>
	                                    	</s:if>
		                                    </div>
                                    	</td>
	                                    <td class="tittdSingle"><s:property value="#xp.username"/></td>
	                                    <td class="tittd">
	                                    	<s:iterator value="role_map.keySet()" id="id" >
												<s:if test="#id==#xp.roleId">
													<s:property value="role_map.get(#id)"/>
												</s:if>
											</s:iterator>
	                                    </td>
	                                    <td class="tittdSingle">
	                                    	<s:if test="#xp.status == 1 ">
		                            			正常
		                            		</s:if>
		                            		<s:else>
		                            			锁定
		                            		</s:else>
	                                    </td>
	                                    <td class="tittd"><s:property value="#xp.realName"/></td>
	                                    <td class="tittdSingle"><s:property value="#xp.idNo"/></td>
	                                    <td class="tittd"><s:property value="#xp.phone"/></td>
	                                    <td class="tittdSingle"><s:property value="#xp.celphone"/></td>
	                                    <td class="tittd"><s:property value="#xp.email"/></td>
	                                </tr>
	                                <!-- 
	                                <tr class="tr_statistics">
	                                    <td colspan="5" style="text-align:left;padding-left:15px;">合计：</td>
	                                    <td>0</td>
	                                    <td>0</td>
	                                    <td>0</td>
	                                    <td>0</td>
	                                    <td>0</td>
	                                    <td>0</td>
	                                    <td>0</td>
	                                    <td>0</td>
	                                </tr>
	                                 -->
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
    	location.href="peopleManageAction_listAllPeople.action?pageParams.curPage="+curPage;
    }
    
    //输入数字时页面跳转
    function changeByPage(){
    	var curPage = $("#c_page").val();
    	if($.trim(curPage) != ""){
	    	queryByPage(curPage);
    	}
    }
    
    function updatePeople(obj){
    	location.href="peopleAction_initUpdPersonProfile.action?sid="+obj;
    }
    
    function deletePeople(obj){
    	if(confirm("确认要删除该用户吗?")){
	    	location.href="peopleManageAction_deletePersonProfile.action?sid="+obj;
    	}
    }
    
    function recoverPwd(obj){
    	if(confirm("确认要重置该用户的密码吗?")){
    		$.ajax({
      		   type: "POST",
      		   url: "peopleManageAction_recoverPwd.action",
      		   data: {"sid" : obj},
      		   cache : false,
      		   success: function(data){
      			  // alert("Data Loaded: " + data);
      				if(data == "ok"){
      					alert("密码重置成功");
   					} else {
   						alert("密码重置出现异常");
   					}
      		   }
  	    	});
    	}
    }
    </script>
    
    
</body>

</html>

