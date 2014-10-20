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
    <title>资源列表</title>
    
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
  <a href="http://<%=hostIp.hostIp()%>:8080/aesop/manage/admin/pages/SourceList.jsp">立即刷新</a><br /><br />
资源列表：<br>
  </body>


<%@ page contentType="text/html; charset=gb2312" %>

<%@ page language="java" %>

<%@ page import="java.util.List" %>

<%@ page import="cn.com.shanda.aesop.server.*" %>

<%@ page import="cn.com.shanda.aesop.server.*" %>

<%@ page import="cn.com.shanda.aesop.dao.impl.*" %>

<%@ page import="cn.com.shanda.aesop.pojo.*" %>

<% 
	ResourceXMLParserDaoImpl xpdi = new ResourceXMLParserDaoImpl();
	
	ArrayList<ResourceItem> al = xpdi.getResources();
			
%>
<table border="5">
<tr>
<th width=50 height=25><div align=center>标题</div></th>
<th width=80 height=25><div align=center>关键字</div></th>
<th width=80 height=25><div align=center>种类</div></th>
<th width=80 height=25><div align=center>描述</div></th>
<th width=80 height=25><div align=center>日期</div></th>
<th width=80 height=25><div align=center>url</div></th>
<th width=80 height=25><div align=center>作者</div></th>
<th width=80 height=25><div align=center>出版社</div></th>
</tr>
<%
 for(int i=0;i<al.size();i++){
		String title = al.get(i).getTitle();
		String keywords = al.get(i).getKeywords();
		String kind = al.get(i).getKind();
		String description = al.get(i).getDescription();
		Date date = al.get(i).getDate();
		String url = al.get(i).getUrl();
		String author = al.get(i).getAuthor();
		String publisher = al.get(i).getPublisher();
		
%>
<tr>
<td><div align=center><%=title%></div></td>
<td><div align=center><%=keywords%></div></td>
<td><div align=center><%=kind%></div></td>
<td><div align=center><%=description%></div></td>
<td><div align=center><%=date%></div></td>
<td><div align=center><%=url%></div></td>
<td><div align=center><%=author%></div></td>
<td><div align=center><%=publisher%></div></td>
</tr>
<%
 }
%>
</table>
<%
out.print(" 资源列表查询完毕！"); 
%>

<body><br><br><br></body>
</html>
