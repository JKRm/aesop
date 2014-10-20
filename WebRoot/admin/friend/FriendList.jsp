<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.com.shanda.aesop.blog.bean.FriendBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>浏览所有友情网站</title>
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
    <p id="title">【浏览所有友情网站】</p>
	<%
		ArrayList friendList=(ArrayList)request.getAttribute("friendList");
		if(friendList==null||friendList.size()==0){
	%>
		<p class="error">没有好友可显示！</p>
	<%} else {%>
	   <table id="table">
	    <tr>
	      <th>友情网站名称</th><th>网址</th><th>操作</th>
	    </tr>
	<%
    	for(int i=0;i<friendList.size();i++){
    	FriendBean single=(FriendBean)friendList.get(i);    							
	%>
		<tr>
		  <td><a href="/aesop/blog/FriendAction?action=adminSingle&id=<%=single.getId() %>" class="word_purple "><%=single.getName()%></a></td>
		  <td><%=single.getBlog() %></td>
		  <td><a href="/aesop/blog/FriendAction?action=modify&id=<%=single.getId() %>" class="word_purple ">√修改</a>
		 	  <a href="/aesop/blog/FriendAction?action=delete&id=<%=single.getId() %>" class="word_purple ">×删除</a>							
		  <td>
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