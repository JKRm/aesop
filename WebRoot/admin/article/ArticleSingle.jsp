<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="cn.com.shanda.aesop.blog.bean.ArticleBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>易索资讯后台首页</title>
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
	<% 
      ArticleBean single=(ArticleBean)request.getAttribute("articleSingle");
      if(single==null) {
      	out.println("<p class=\"error\">阅读文章失败！</p>");
      	} else {
    %>
	    <div id="readArticle">
	    <p id="title"><%=single.getTitle()%></p>
	    <p class="info"><%=single.getCreate()%>：<%=single.getInfo() %>&nbsp;&nbsp;&nbsp;&nbsp;发表时间：<%=single.getSdTime() %></p>
	    <p class="info">评论：<%=single.getReview() %> 条&nbsp;&nbsp;&nbsp;&nbsp;
	                   阅读：<%=single.getCount() %> 次&nbsp;&nbsp;&nbsp;&nbsp;</p>
	    <%=single.getContent() %>
		<p class="back"><input type="button" value="返&nbsp;回" class="button_bg" onClick="javascript:window.history.go(-1)" /></p>
	    <%}%>
	    </div>
	</div>
	<div id="end">      
 		<address>All CopyRights&copy;2011 易索</address>
    </div>
</div> 
</body>
</html>