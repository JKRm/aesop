<%@ page language="java" contentType="text/html; charset=UTF-8"%> 
<%@ page import="java.util.List" %>
<%@ page import="cn.com.shanda.aesop.blog.bean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>给我留言   联系我们</title>
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
	List wordList=(List)request.getAttribute("wordList"); 
    if(wordList==null||wordList.size()==0)
		out.print("<p class=\"prompt\">暂无留言</p>");
    else{%>
    <div class="reviewList">
    <%
        int num=wordList.size();     
        out.print("<p class=\"success\">【易索的留言 共 "+num+"条】</p>");                            
        int i=0; 
        while(i<wordList.size()){
            WordBean wordSingle=(WordBean)wordList.get(i);             
%>
	<div class="reviewItem">
		<p class="floor right">++第<%=num-i%>条++</p>
 		<p>▲ <b><%=wordSingle.getWordAuthor() %>:[</b><%=wordSingle.getWordTitle(15)%>]</p>
		<p class="reviewWord"><%=wordSingle.getWordContent()%></p>
		<p class="info right"><%=wordSingle.getWordTime()%></p>
	</div>
<%i++;}%>
	</div>
<%}%>
      ${requestScope.pageBar.pageLink}
     <form action="/aesop/blog/WordAction" method="post">
     <input type="hidden" name="action" value="add">
	<p class="left">留&nbsp;&nbsp;言&nbsp;&nbsp;者：<input type="text" name="wordAuthor" class="text" value="匿名好友" /></p>
	<p class="left">留言标题：<input type="text" name="wordTitle" class="text" /></p>
	<p class="left">留言内容：</p><textarea name="wordContent" class="textArea"></textarea>
	<p class="center"><input type="submit" value="提交" />
	   <input type="reset" value="重置" />
	</p>
    </form>
    </div>
    <div id="end">
		<address>All CopyRights&copy;2011 易索</address>
	</div>
</div>
</body>
</html>