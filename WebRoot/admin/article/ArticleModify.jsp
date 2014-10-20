<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.com.shanda.aesop.blog.bean.ArticleTypeBean" %>
<% ArrayList typelist=(ArrayList)session.getAttribute("artTypeList"); %>
<jsp:useBean id="modifySingle" class="cn.com.shanda.aesop.blog.bean.ArticleBean" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>修改文章</title>
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
	<p id="title">【修改文章】</p>
	<form action="/aesop/blog/ArticleAction" method="post" id="articleModify">
    <input type="hidden" name="action" value="modify" />
    <input type="hidden" name="type" value="doModify" />
    <input type="hidden" name="id" value="<%=modifySingle.getId() %>" />
    <p>文章ID：<%=modifySingle.getId() %></p>
    <p>文章类别：<select name="typeId">
                <option value=""></option>
                <% 
                	if(typelist!=null&&typelist.size()!=0){
                	for(int i=0;i<typelist.size();i++){
                	ArticleTypeBean typeBean=(ArticleTypeBean)typelist.get(i);
                	if(typeBean.getId()==modifySingle.getTypeId()){
                %>
                <option value="<%=typeBean.getId()%>" selected><%=typeBean.getTypeName() %></option>
                <%} else {%>
                <option value="<%=typeBean.getId()%>"><%=typeBean.getTypeName() %></option>
                <%}}}
                %>
                </select>
                &nbsp;&nbsp;&nbsp;&nbsp;文章来源： <select name="create">
            	<option value=""/>
            <%	if(modifySingle.getCreate().equals("原创")){ %>
            	<option value="原创" selected>原创</option> 
            	<option value="摘自">摘自</option>
            <%}
	            if(modifySingle.getCreate().equals("摘自")){
	        %>
	        	<option value="原创">原创</option>
            	<option value="摘自" selected>摘自</option>		
	        <%}%>
            	</select>
    </p>
    <p>文章标题：<input type="text" name="title" class="text" value="<%=modifySingle.getTitle() %>" /></p>
    <p>文章描述：<input type="text" name="info" class="text" value="<%=modifySingle.getInfo()%>" /></p>
    <p>文章内容：</p>
    <textarea name="content" class="textArea"><%=modifySingle.getContent() %></textarea></p>
 	<p><input type="submit" value="保存" class="button_bg">
  	   <input type="reset" value="重置" class="button_bg">
    </p>
    </form>
	</div>
	<div id="end">      
 		<address>All CopyRights&copy;2011 易索</address>
    </div>
</div> 
</body>
</html>