<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>密码修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div id="main">
    <div id="AdminLogin">
    </div>
    <s:actionmessage/>
    <div id="AdminmodifyForm">
    	<p><h2>密码修改</h2></p>	
	    <form action="modify/ModifyPasswordAction" method="post">				
		<p>当前密码：<input type="password" name="currentPassword" style="width:200"></p>
		<p>修改密码：<input type="password" name="motifiedPassword" style="width:200"></P>
		<p><input type="submit" class="button" value="修 改">&nbsp;&nbsp;
		   <input type="reset" class="button" value="重 置"></p>	
		</form>
	</div>
	</div>
  </body>
</html>
