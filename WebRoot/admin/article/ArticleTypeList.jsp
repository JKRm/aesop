<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.com.shanda.aesop.blog.bean.ArticleTypeBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>浏览文章类别</title>
	<link rel="stylesheet" href="/aesop/css/blogAdmin.css">
</head>
<body>
<div id="main">
	<div id="top">
		<p><a href="/aesop/blog/IndexAction">【前台首页】</a>
		   <a href="/aesop/blog/LogonAction?action=logout">【退出登录】</a>
		</p>
  	</div>
	<div id="left">
		<div class="block">
			<p class="type">文章管理</p>
			<p><a href="/aesop/admin/article/ArticleAdd.jsp">★发表文章</a></p>
			<p><a href="/aesop/admin/article/ArticleList.jsp">★浏览/修改/删除文章</a></p>
		</div>
		<div class="block">
			<p class="type">文章类别管理</p>
			<p><a href="/aesop/admin/article/ArticleTypeAdd.jsp">★添加类别</a></p>
			<p><a href="/aesop/blog/ArticleAction?action=typeSelect">★浏览/修改/删除类别</a></p>
		</div>
		<div class="block">
			<p class="type">友情连接管理</p>
			<p><a href="/aesop/admin/friend/FriendAdd.jsp">★添加友情链接</a></p>
			<p><a href="/aesop/blog/FriendAction?action=adminList">★浏览/修改/删除友情链接</a></p>
		</div>
		<div class="block">
			<p class="type">留言管理</p>
			<p><a href="/aesop/blog/WordAction?action=adminselect">★浏览/删除留言</a></p>
		</div>
	</div>
    <div id="center">
    <p id="title">【浏览文章类别】</p>
	<%
		ArrayList typelist=(ArrayList)request.getAttribute("typelist");
		if(typelist==null||typelist.size()==0){
	%>
	<p class="error">没有类别可显示！</p>
	<%} else {%> 
	<table id="table">
		<tr>
			<th>类别名称</th><th>类别描述</th><th>操作</th>
		</tr>
	<%
    	for(int i=0;i<typelist.size();i++){
    	ArticleTypeBean single=(ArticleTypeBean)typelist.get(i);    							
	%>
		<tr>
			<td><%=single.getTypeName()%></td>
			<td><%=single.getTypeInfo() %></td>
			<td><a href="/aesop/blog/ArticleAction?action=typeModify&typeId=<%=single.getId() %>" class="word_purple ">√修改</a>
				<!-- <a href="/aesop/bolg/ArticleAction?action=typeDelete&typeId=<%=single.getId() %>" class="word_purple ">×删除</a></p> -->
			</td>
		</tr>
	<%}%>
	</table>
	<%}%>
    </div>
    <div id="end">
		<address>All CopyRights&copy;2011 易索</address>
	</div>
</div>
</body>
</html>