<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="typeSingle" class="cn.com.shanda.aesop.blog.bean.ArticleTypeBean" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>修改类别</title>
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
    <p id="title">【修改类别信息】</p>
    <form action="/aesop/blog/ArticleAction" method="post">
      <input type="hidden" name="action" value="typeModify">
      <input type="hidden" name="type" value="doModify">
      <input type="hidden" name="typeId" value="<%=typeSingle.getId() %>">
      <p>文章ID：<%=typeSingle.getId() %></p>
 	  <p>类别名称：<input type="text" name="typeName" value="<%=typeSingle.getTypeName() %>" /></p>
      <p>类别描述：<input type="text" name="typeInfo" value="<%=typeSingle.getTypeInfo()%>" /></p>
      <p><input type="submit" value="修改" class="button_bg" />
         <input type="reset" value="重置" class="button_bg" />
      </p>    
    </form>
    </div>
    <div id="end">
		<address>All CopyRights&copy;2011 易索</address>
	</div>
  </div>
</body>
</html>