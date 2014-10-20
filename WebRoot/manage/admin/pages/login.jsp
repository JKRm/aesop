<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="/aesop/css/admin.css" />
  </head>
  
  <body>
    <div id="main">
    <div id="AdminLogin">
    </div>
    <div id="AdminLoginForm">
    	<p><h2>管理员登录</h2></p>	
	    <form action="manage/adminAction" method="post">				
		<p>用户名：<input type="text" name="username" style="width:200"></p>
		<p>密&nbsp;&nbsp;码：&nbsp;<input type="password" name="password" style="width:200"></P>
		<p><input type="submit" class="button" value="登 录">&nbsp;&nbsp;
		   <input type="reset" class="button" value="重 置"></p>	
		</form>
		<s:actionerror/>
	</div>
	</div>
  </body>
</html>
