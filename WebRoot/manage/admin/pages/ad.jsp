<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>广告设置窗口</title>
    
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
    <h3>请输入广告词及对应链接</h3>
    <form action="ad/AdAction.action" method="post">
    <p>
    广告词：&nbsp;<input id="adword" name="adword" type="text" /><br/>
    </p>
    <p>
    广告链接 ：<input id="adlink" name="adlink" type="text" /><br/>
    </p>
    <p>
    <input id="submit" name="submit" type="submit" value="确定">
    <input id="reset" name="reset" type="reset" value="清空">
    </p>
    </form>
  </body>
</html>
