<%@ page language="java" import="java.util.*, cn.com.shanda.aesop.pojo.*,cn.com.shanda.aesop.dao.impl.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
    
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>图片显示界面</title>
  <link rel="stylesheet" href="/aesop/css/list.css" />
  <script language="javascript" src="/aesop/js/Picasa.js"></script>
</head>
<body>
  <%List<ResourceItem> list = (List<ResourceItem>)request.getAttribute("list"); %>
<div id="main">
    <div id="head">
	<a href="/aesop/index.jsp"><img src="/aesop/images/logo_small.png" id="logo_small"></a>
	<form id="form" action="function/PictureAction" method="post" >
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
	<div id="image">
	  <div>${warning}</div>
	  <div id="prompt">${prompt}</div>
	<%for (int i = 0; i < list.size(); ++i) { %>
	<%ResourceItem item = list.get(i); %>
	<div class="picArea">
		<a href="javascript: void(0);" rel="Picasa[<%=item.getUrl()%>, <%=item.getTitle() %>]">
			<p><img class="img" src="<%=item.getUrl() %>" /></p>
			<p class="title"><%=item.getTitle() %></p>
		</a>
		<p class="info">&nbsp;<%=item.getDescription() %>&nbsp;</p>
		<p class="info"><a href="<%=item.getUrl()%>" target="_blank">点此下载</a></p>
	</div>
	<%}%>
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