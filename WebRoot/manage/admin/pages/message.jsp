<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ page import="cn.com.shanda.aesop.server.GetHostIp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'message.jsp' starting page</title>
    
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
  <font color="red"><b><a id='div1'></a></b></font>秒后返回添加界面  <a href="http://<%=hostIp.hostIp()%>:8080/aesop/manage/admin/pages/ad.jsp">立即返回</a><br /><br />
   <center>
	<div style="width:auto;height:300; border:3">
		<div ><font size="3">${requestScope.message}</font></div>
	</div>
</center>
  </body>
</html>
<script type="text/javascript">

			var t = 10;

			function showTime()
			{
    			t -= 1;
    			document.getElementById('div1').innerHTML= t;
    
    		if(t==0)
    		{
        		location.href='http://<%=hostIp.hostIp()%>:8080/aesop/manage/admin/pages/ad.jsp';
    		}
    
    		setTimeout("showTime()",1000);
			}

			showTime();
</script>
