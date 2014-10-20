<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="cn.com.shanda.aesop.blog.bean.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>易索文章</title> 
	<link rel="stylesheet" href="/aesop/css/blogFront.css" />
</head>
<body>
<div id="main">
  <div id="top">
    <p id="link">
	<a href="/aesop/blog/IndexAction" class="topA">资讯首页</a>&nbsp;|
	<a href="/aesop/front/article/ArticleIndex.jsp" class="topA">易索文章</a>&nbsp;|
	<a href="/aesop/blog/WordAction?action=select" class="topA">给我留言</a>&nbsp;|
	<a href="/aesop/blog/LogonAction?action=islogon" class="topA">资讯管理</a>
	</p>
  </div>
  <div id="left">
	<div class="block">
	<p class="type">易索推荐</p>
<%
    List artTJlist=(List)session.getAttribute("artTJList");
    if(artTJlist==null||artTJlist.size()==0) {
    	out.print("<p class=\"center\">没有推荐文章可显示！</p>");
    } else {
        int i=0;
        while(i<artTJlist.size()){
        ArticleBean articleSingle=(ArticleBean)artTJlist.get(i);
%>
	<p class="center"><a href="/aesop/blog/ArticleAction?action=read&id=<%=articleSingle.getId()%>"><%=articleSingle.getTitle(15) %></a></p>
<%i++;}}%>
	</div>
	<div class="block">
	<p class="type">友情链接</p>
<%
    List friendlist=(List)session.getAttribute("friendList");
    if(friendlist==null||friendlist.size()==0) {
    	out.print("<p class=\"center\">目前还未添加任何连接！</p>");
    } else {
        int i=0;
        while(i<friendlist.size()){
        FriendBean friendSingle=(FriendBean)friendlist.get(i);
%>
	<p class="left friend"><a href="<%=friendSingle.getBlog() %>"><%=friendSingle.getName() %></a></p>
<%i++;}}%>
	</div>
	<div class="block">
	<p class="type">最新留言</p>
<%
    List wordlist=(List)session.getAttribute("wordList");
  	if(wordlist==null||wordlist.size()==0) {
		out.print("<p class=\"center\">没有留言可显示！</p>");
    } else {
		int i=0;
        while(i<wordlist.size()){
        WordBean wordSingle=(WordBean)wordlist.get(i);
%>
	<p class="center reviewico"><a href="/aesop/blog/WordAction?action=select"><%=wordSingle.getWordTitle(15)%></a></p>
<%i++;}}%>
	</div>
	<div class="block">
  	<p class="type">易索信息</p>
	<p class="left">官网：</p><p class="center small"><%=((MasterBean)session.getAttribute("master")).getMasterName() %></p>
	<p class="left">邮箱：</p><p class="center small"><%=((MasterBean)session.getAttribute("master")).getMasterSex()%></p>
	<p class="left">QQ群：<p class="center"><%=((MasterBean)session.getAttribute("master")).getMasterOicq()%></p>
	</div>
  </div>
  <div id="center">
  	<% 
    ArticleBean single=(ArticleBean)session.getAttribute("readSingle");
    if(single==null) {
    	out.println("<p class=\"prompt\">阅读文章失败了，刷新试试呢！</p>"); 
    } else {
%>
	<p id="title"><%=single.getTitle()%></p>
	<p class="info center"><%=single.getCreate()%>：<%=single.getInfo() %> 发表时间：<%=single.getSdTime() %>&nbsp;&nbsp;评论：<%=single.getReview() %>条&nbsp;&nbsp;阅读：<%=single.getCount() %>次</p>
	<div class="wordBG">
		<%=single.getContent() %>
	</div>
	<p class="center"><a href="javascript:window.history.go(-1)">返回</a></p>
<% 
    List reviewlist=(ArrayList)session.getAttribute("reviewlist");
	if(reviewlist==null||reviewlist.size()==0){
    	out.print("<p class=\"prompt\">该文章目前没有任何评论，快来抢沙发哦</p>");
} else {%>
	<div class="reviewList">
<%      int num=reviewlist.size(); 
        out.print("<P class=\"success\">【文章评论 共 " + num + " 条】</p>");                            
        int i=reviewlist.size();
	    while(i>0){
    	    ReviewBean reviewSingle=(ReviewBean)reviewlist.get(i-1);            
%>
	<div class="reviewItem">
	<p class="floor right">#<%=num-i+1 %>楼</p>
	<p>▲ <%=reviewSingle.getReAuthor() %>:</p>
    <p class="reviewWord">————<%=reviewSingle.getReContent()%></p> 
    <p class="info right"><%=reviewSingle.getReSdTime()%></p>	
    </div>				
<%i--;}%>
	</div>
<%}}%>
		<div class="word">
		<p><b>发表评论</b></p>
	    <form action="/aesop/blog/ArticleAction" method="post">
	    	<input type="hidden" name="action" value="followAdd">
	    	<% ArticleBean singleword=(ArticleBean)session.getAttribute("readSingle");%>
	    	<input type="hidden" name="articleId" value="<%=single.getId() %>">
	 	<p>评 论 者：<input class="text" type="text" name="reAuthor" size="40" value="匿名易索者"></p>
	    <p>评论内容：</p>
		<textarea class="textArea" name="reContent" rows="10" cols="50"></textarea>
	    <p class="center"><input type="submit" value="提交" style="width:50">
	    				  <input type="reset" value="重置" style="width:50">
	    </p>
	  	</form>
	  	</div>
    </div>
    <div id="end">
		<address>All CopyRights&copy;2011 易索</address>
	</div>
</div>
</body>
</html>