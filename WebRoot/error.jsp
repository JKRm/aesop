<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>Choose file to Preview.</title>
  </head>
  <!-- 找不到文件的错误提示界面 -->
  <body>
  	<p align="center">Sorry，文件不存在！</p>
  </body>

</html>
