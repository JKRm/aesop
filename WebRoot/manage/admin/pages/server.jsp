<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.com.shanda.aesop.server.GetHostIp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript">
    	setInterval(function () {
    		window.location.reload();
    		}, 30000);
    </script>
    <title>服务器列表</title>
    
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
   <%GetHostIp hostIp = new GetHostIp(); %>
  <font color="red"><b><a id='div1'></a></b></font>秒后自动刷新该界面  <a href="http://<%=hostIp.hostIp()%>:8080/aesop/manage/admin/pages/server.jsp">立即刷新</a><br /><br />
服务器列表：<br>
  </body>


<%@ page contentType="text/html; charset=gb2312" %>

<%@ page language="java" %>

<%@ page import="java.util.List" %>

<%@ page import="cn.com.shanda.aesop.server.*" %>

<%@ page import="cn.com.shanda.aesop.server.*" %>

<%@ page import="org.dom4j.Element" %>

<%@ page import="org.dom4j.Attribute" %>

<% 
	List<String> getlist =IpList.list;
	
	int m=1;
	
	String isMain = "否";
	
	String mainURL = RemoteIp.remoteip;
			
%>
<table border="5">
<tr>
<th width=50 height=25><div align=center>服务器ID</div></th>
<th width=50 height=25><div align=center>主服务器</div></th>
<th width=80 height=25><div align=center>服务器URL</div></th>
</tr>
<%
 for(int i=0;i<getlist.size();i++){
		String url = getlist.get(i);
		if(url.equals(mainURL))
			isMain = "是";
		else isMain = "否";
%>
<tr>
<td><div align=center><%=m++%></div></td>
<td><div align=center><%=isMain%></div></td>
<td><div align=center><%=url%></div></td>
</tr>
<%
 }
%>
</table>
<%
out.print("服务器列表查询完毕！"); 
%>

<body><br><br><br></body>
</html>
<script type="text/javascript">

			var t = 10;

			function showTime()
			{
    			t -= 1;
    			document.getElementById('div1').innerHTML= t;
    
    		if(t==0)
    		{
        		location.href='http://<%=hostIp.hostIp()%>:8080/aesop/manage/admin/pages/server.jsp';
    		}
    
    		setTimeout("showTime()",1000);
			}

			showTime();
</script>
