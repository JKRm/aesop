<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*" %> 
<%@ page import="cn.com.shanda.aesop.blog.bean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>易索新闻</title> 
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
  	<p id="title">【易索文章】</p>
	<%
    ArrayList typelist=(ArrayList)session.getAttribute("artTypeList");
    if(typelist==null||typelist.size()==0){
%>
    <p class="prompt">对不起，没有文章类别！</p>
<%} else {%>
    <div id="type">
<%
    	for(int i=0;i<typelist.size();i++){
            ArticleTypeBean single=(ArticleTypeBean)typelist.get(i);
%>
	<a class="articleType" href="/aesop/blog/ArticleAction?action=select&typeId=<%=single.getId() %>"><%=single.getTypeName() %></a>
<%}%></div>
<% }
	ArrayList articlelist=(ArrayList)request.getAttribute("articleList"); 
    if(articlelist==null||articlelist.size()==0) {
		out.print("<p class=\"prompt\">对不起，您选择的文章类别中没有任何文章！</p>");
    } else {            
        ArticleBean articleSingle=(ArticleBean)articlelist.get(0);
        int books=articlelist.size();
        String typeName="最新公告"; 
        if(articleSingle.getTypeId()==1) { typeName="最新公告"; } 
        else if(articleSingle.getTypeId()==2) { typeName="资讯论坛"; } 
        else if(articleSingle.getTypeId()==3) {	typeName="寻求资源"; }
        else if(articleSingle.getTypeId()==4) { typeName="灌水无忌"; }
        out.print("<p class=\"success\">【"+typeName+" 共 "+books+" 篇】</>");                            
        int i=0;
        while(i<articlelist.size()){
        articleSingle=(ArticleBean)articlelist.get(i);            
%>
	<div class="article">
 		<p>▲<a href="/aesop/blog/ArticleAction?action=read&id=<%=articleSingle.getId()%>">
 			<b><%=articleSingle.getTitle() %></b>
			</a>[<%=articleSingle.getCreate()%>]
		</p>
		<p class="read right"><a href="/aesop/blog/ArticleAction?action=read&id=<%=articleSingle.getId()%>">阅读全文</a></p>
 		<%=articleSingle.getContent(100) %>
 		<p class="info">发表时间：<%=articleSingle.getSdTime() %> |&nbsp;评论：<%=articleSingle.getReview() %> |&nbsp;阅读：<%=articleSingle.getCount() %> 次</p>
 	</div>
<%i++;}}%>
    </div>
    <div id="end">
		<address>All CopyRights&copy;2011 易索</address>
	</div>
</div>
</body>
</html>