<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'serverSetting.jsp' starting page</title>
    
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
服务器设置：<br>
  </body>
  
  
<%@ page contentType="text/html; charset=gb2312" %>

<%@ page language="java" %>

<%@ page import="java.util.List" %>

<%@ page import="cn.com.shanda.aesop.server.*" %>
  
<%
	String mainURL = RemoteIp.remoteip;
	
	out.print("当前主服务器为："+mainURL); 
			
%> 
  
  
</html>
