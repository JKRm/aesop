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
	<base href="<%=basePath%>">

  <title>结果显示界面</title>
  <meta http-equiv="pragma" content="no-cache">  
  <meta http-equiv="cache-control" content="no-cache">  
  <meta http-equiv="expires" content="0">  
  <link rel="stylesheet" href="css/style_result.css" />
  <script src="js/result.js" type="text/javascript"></script>
  <script src="js/suggest.js" type="text/javascript"></script>
</head>
<body>
  <div id="main">
    <div id="title">
	<a href="/aesop/index.jsp"><img src="images/logo_small.png" id="logo_small"></a>
	<form id="form" action="search" method="post" name="Form1">
	<%String type = (String)session.getAttribute("type");%>
        <ul>
		  <li id="all"><input type="radio" name="type" value="all" <% if ("all".equals(type)||type==null) {%> checked="checked" <%}%>/>全部</li>
		  <li id="file"><input type="radio" name="type" value="document" <% if ("document".equals(type)) {%> checked="checked" <%}%>/><a href="/aesop/function/WenkuAction?queryText=">文档</a></li>
		  <li id="mp3"><input type="radio" name="type" value="audio" <% if ("audio".equals(type)) {%> checked="checked" <%}%>/><a href="/aesop/function/AudioAction?queryText=">音频</a></li>
		  <li id="video"><input type="radio" name="type" value="video" <% if ("video".equals(type)) {%> checked="checked" <%}%>/><a href="/aesop/function/VideoAction?queryText=">视频</a></li>
		  <li id="picture"><input type="radio" name="type" value="picture" <% if ("picture".equals(type)) {%> checked="checked" <%}%>/><a href="/aesop/function/PictureAction?queryText=">图片</a></li>
		  <li id="other"><input type="radio" name="type" value="other" <% if ("other".equals(type)) {%> checked="checked" <%}%>/>其他</li>
	   <ul>
	   <input type="text" id="queryText" name="queryText" value="<%=session.getAttribute("queryText")==null?"":session.getAttribute("queryText") %>"/>
	   <input type="submit" id="search" name="search" value="搜索" /><a href="/aesop/accurateQuery.jsp">高级查找</a>
	   <span id="spanOutput"></span>
      </form>
      <hr />
	</div>
	<div id="result">
	  <div>${warning}</div>
	  <div id="prompt">${prompt}</div>
	  <s:iterator id="item" value="list" status="stuts"> 
	    <div class="content">
	      <a class="title" target="_blank" href="preview?url=${url}">${title}</a>
	      <p>
	        <span class="keywords">关键字：${keywords}</span>
	        <span class="kind ${kind}">类型：${kind}</span>
	        <span class="download"><a href="${url}">点此下载</a></span>
	      </p>
	      <p class="description">简介：${description}</p>
	      <p>
	        <span class="author">作者：${author}</span>
	        <span class="publisher">出版单位：${publisher}</span>
	        <span class="date">日期：${date}</span>
	      </p>
	    </div>
	  </s:iterator>
	</div>
	<div id="pageBar">
	  ${pageBar}
	</div>
	<div id="copyright">
		<address>&copy;2011 <a href="http://www.wh.sdu.edu.cn/">山东大学威海分校</a> “威致”开发团队</address>
	</div>
  </div>
  <div id="ads">
    <div id="empty">
    </div>
  <%ArrayList adlist = new AdvertisementDaoImpl().getAdList(); 
  	for (int i=0; i<adlist.size(); ++i) {
  		Advertisement ad = (Advertisement)adlist.get(i);
  %>
    <div class="ad">
      <a href="<%=ad.getAdlink() %>"><%=ad.getAdword() %></a>
    </div>
  <%
  	}
  %>
  </div>
</body>