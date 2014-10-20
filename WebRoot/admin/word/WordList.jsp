<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="cn.com.shanda.aesop.blog.bean.WordBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看所有留言</title>
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
  	<p id="title">【浏览留言】</p>
  	<% 
		List wordlist=(List)request.getAttribute("adminwordList"); 
        if(wordlist==null||wordlist.size()==0) {
			out.print("<p class=\"error\">暂无留言可显示！");
		} else {
         	int num=wordlist.size();     
            out.print("<p class=\"success\">【我的留言 共 " + num + " 条】</p>");                            
            int i=0;
            while(i<wordlist.size()){
            WordBean wordSingle=(WordBean)wordlist.get(i);            
    %>
	    <div class="wordList">
	 	<p>▲<b><%=wordSingle.getWordAuthor() %></b></p>
	 	<p>&nbsp;&nbsp;&nbsp;&nbsp;<%=wordSingle.getWordContent()%></p>	
		<p class="delete"><span class="info"><%=wordSingle.getWordTime()%></span>&nbsp;&nbsp;<a href="/aesop/blog/WordAction?action=delete&id=<%=wordSingle.getId() %>">[删除此条留言]</a></p>
	    </div>
	    <%i++;}}%>
	</div>
    <div id="end">
		<address>All CopyRights&copy;2011 易索</address>
	</div>
</div>
</body>
</html>