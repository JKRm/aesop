<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>添加友情链接</title>
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
	<p id="title">【添加友情链接】</p>
	<form action="/aesop/blog/FriendAction" method="post">
	<input type="hidden" name="action" value="add">
	<p>友情网站名称：<input type="text" name="name" class="text" /></p>
	<p>友&nbsp;&nbsp;情&nbsp;&nbsp;网&nbsp;&nbsp;址：<input type="text" name="blog" class="text" /></p>
	<p><input type="submit" class="button_bg" value="保存">
	   <input type="reset" class="button_bg" value="重置">
	</p>
	</form>
	</div>
	<div id="end">      
 		<address>All CopyRights&copy;2011 易索</address>
    </div>
</div> 
</body>
</html>