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
    <title>资源列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">   
            function checkEvent(name, allCheckId) {   
                var allCk = document.getElementById(allCheckId);   
                if (allCk.checked == true) checkAll(name);   
                else checkAllNo(name);   
  
            }   
  
            //全选   
            function checkAll(name) {   
                var names = document.getElementsByName(name);   
                var len = names.length;   
                if (len > 0) {   
                    var i = 0;   
                    for (i = 0; i < len; i++)   
                    names[i].checked = true;   
  
                }   
            }   
  
            //全不选   
            function checkAllNo(name) {   
                var names = document.getElementsByName(name);   
                var len = names.length;   
                if (len > 0) {   
                    var i = 0;   
                    for (i = 0; i < len; i++)   
                    names[i].checked = false;   
                }   
            }   
  
            //反选   
            function reserveCheck(name) {   
                var names = document.getElementsByName(name);   
                var len = names.length;   
                if (len > 0) {   
                    var i = 0;   
                    for (i = 0; i < len; i++) {   
                        if (names[i].checked) names[i].checked = false;   
                        else names[i].checked = true;   
  
                    }   
                }   
  
            }   
        </script> 
		
  </head>
  
  <body>
  <div ><font size="3" color=red ><b>${requestScope.message}</b></font></div>
 <%GetHostIp hostIp = new GetHostIp(); %>
<a href="http://<%=hostIp.hostIp()%>:8080/aesop/manage/admin/pages/DelResource.jsp">立即刷新</a><br /><br />
资源列表：<br>
  </body>


<%@ page contentType="text/html; charset=utf-8" %>

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
<p>
<input type="checkbox" name="ra" id="ckall" onclick="checkEvent('checkbox','ckall')"/>全选
<input type="checkbox" name="ra" id="ckReserve" onclick="reserveCheck('checkbox','ckReserve')"/>反选  
</p>
<form action="del/DelResourceAction" method="post">
<p align="right">
<input id="submit" name="submit" type="submit" value="确定">
</p>
<table border="5">
<tr>
<th width=50 height=25><div align=center>是否删除</div></th>
<th width=50 height=25><div align=center>标题</div></th>
<th width=80 height=25><div align=center>关键字</div></th>
<th width=80 height=25><div align=center>种类</div></th>
<th width=80 height=30><div align=center>描述</div></th>
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
<td align="center"><input type="checkbox" name="checkbox" value=<%=url.replaceAll(" ", "%NULL%" )%> /></td>
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
<p align="justify">
<input id="submit" name="submit" type="submit" value="确定">
</p>
<%
out.print("服务器列表查询完毕！"); 
%>
</form>
<body><br><br><br></body>
</html>

