<%@ page language="java" import="java.util.*, cn.com.shanda.aesop.pojo.*,cn.com.shanda.aesop.dao.impl.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>视频搜索结果显示界面</title>
  <link rel="stylesheet" href="/aesop/css/list.css" />
  <script src="js/result.js" type="text/javascript"></script>
</head>
<body>
<div id="main">
  	<%List<ResourceItem> list = (List<ResourceItem>)request.getAttribute("list"); %>
    <div id="head">
	<a href="/aesop"><img src="/aesop/images/logo_small.png" id="logo_small"></a>
	<form id="form" action="function/VideoAction" method="post" >
	<%String type = (String)session.getAttribute("type");%>
        <ul>
		  <li id="all"><a href="/aesop">全部</a></li>
		  <li id="file"><a href="/aesop/function/WenkuAction?queryText=">文档</a></li>
		  <li id="mp3"><a href="/aesop/function/AudioAction?queryText=">音频</a></li>
		  <li id="video"><a href="/aesop/function/VideoAction?queryText=">视频</a></li>
		  <li id="picture"><a href="/aesop/function/PictureAction?queryText=">图片</a></li>
		  <li id="other">其他</li>
	   <ul>
	   <input type="text" id="queryText" name="queryText" value="<%=session.getAttribute("queryText")==null?"":session.getAttribute("queryText") %>"/>
	   <input type="submit" id="search" name="search" value="易索" /><a href="/aesop/accurateQuery.jsp">高级查找</a>
      </form>
      <hr />
	</div>
	<div>${warning}</div>
	<div id="prompt">${prompt}</div>
	<div id="image">
		<%for (int i = 0; i < list.size(); ++i) { %>
		<%ResourceItem item = list.get(i); %>
		<%String url = item.getUrl().substring(0, item.getUrl().lastIndexOf(".") + 1) + "jpg"; %>
		<%url = url.substring(0, url.lastIndexOf("/") + 1) + "img" + url.substring(url.lastIndexOf("/"), url.length()); %>
		<div class="picArea">
			<a target="_blank" href="/aesop/preview?url=<%=item.getUrl() %>" style="text-decoration: none; border: 0;">
				<div style="background: #000;"><img class="img" src="<%=url %>" /></div>
				<div style="margin-top: -100px; margin-left:70px;"><img src="/aesop/images/preview/player.png" /></div>
				<div style="margin-top: 60px;"><p class="title"><%=item.getTitle()+"."+item.getKind() %></p></div>
			</a>
			<p class="info">关键字:&nbsp;&nbsp;</font><%=item.getKeywords() %></p>
		</div>
		<%} %>
	</div>
	<div id="ads">
		<div id="empty"></div>
	<%ArrayList adlist = new AdvertisementDaoImpl().getAdList(); 
  		for (int i=0; i<adlist.size(); ++i) {
  		Advertisement ad = (Advertisement)adlist.get(i);
 	%>
 		<div class="ad">
 			<a href="<%=ad.getAdlink() %>" target="_blank"><%=ad.getAdword() %></a>
		</div>
	<%}%>
	</div>
	<div id="pageBar">
	  ${pageBar}
	</div>
	<div id="foot">
		<address>&copy;2011 <a href="http://www.wh.sdu.edu.cn/">山东大学威海分校</a> “威致”开发团队</address>
	</div>
</div>
</body>