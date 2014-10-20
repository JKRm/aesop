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
    
    <title>拼音索引列表</title>
    
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
  <font color="red"><b><a id='div1'></a></b></font>秒后自动刷新   <a href="http://<%=hostIp.hostIp()%>:8080/aesop/manage/admin/pages/pinyinTransform.jsp">立即刷新</a><br /><br />
拼音索引列表：<br>

  </body>


<%@ page contentType="text/html; charset=gb2312" %>

<%@ page language="java" %>

<%@ page import = "java.sql.Connection" %>

<%@ page import="java.sql.*" %>

<%@ page import="cn.com.shanda.aesop.util.*" %>

<%
String sql="SELECT * FROM pinyinTransform";
Connection conn = DB.createConn();
PreparedStatement ps = DB.createStmt(conn,sql);
ResultSet rs = ps.executeQuery(); 
%>
<table border="5" align="center">
<tr>
<th width=50 height=25><div align=center>中文</div></th>
<th width=80 height=25><div align=center>拼音</div></th>
</tr>
<%
 while(rs.next()){
%>

<tr>
<td><div align=center><%=rs.getString(1)%></div></td>
<td><div align=center><%=rs.getString(2)%></div></td>
</tr>
<%
 }
%>
</table>
<%
out.print("拼音列表查询完毕！"); 
DB.close(rs);
DB.close(ps);
DB.close(conn);
%>

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
        		location.href='http://<%=hostIp.hostIp()%>:8080/aesop/manage/admin/pages/pinyinTransform.jsp';
    		}
    
    		setTimeout("showTime()",1000);
			}

			showTime();
</script>