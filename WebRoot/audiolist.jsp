<%@ page language="java" import="java.util.*, cn.com.shanda.aesop.pojo.*,cn.com.shanda.aesop.dao.impl.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>音乐列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="/aesop/css/preview.css" />

  </head>
  
  <body>
    <div id="audiolist">
      <div id="title">
      </div>
      <div id="head">
      <a href="/aesop"><img src="/aesop/images/logo_small.png" id="logo_small"></a>
	<form id="form" action="function/AudioAction" method="post" >
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
      <div id="list">
      <%List<ResourceItem> list = (List<ResourceItem>)request.getAttribute("list"); %>
		<table>
		  <tr>
		    <th class="name">歌曲名称</th>
		    <th class="singer">演唱者</th>
		    <th class="listen">试听</th>
		    <th class="down">下载</th>
		  </tr>
		  <%for (int i = 0; i < list.size(); ++i) { %>
		  <%ResourceItem item = list.get(i); %>
		  <tr>
		  <%if (i%2 == 0) { %>
		  <tr class="even">
		  <%} else { %>
		  <tr class="odd">
		  <%} %>
		    <td><a href="/aesop/preview?url=<%=item.getUrl() %>" target="_blank"><%=item.getTitle() %></a></td>
		    <td><%=item.getAuthor() %></td>
		    <td class="center"><a href="/aesop/preview?url=<%=item.getUrl() %>"  target="_blank"><img src="/aesop/images/preview/play.png" /></a></td>
		    <td class="center"><a href="<%=item.getUrl() %>"><img src="/aesop/images/preview/down.png"></a></td>
		  </tr>
		  <%} %>
		</table>
      </div>
      <div id="pageBar" class="center">
	  ${pageBar}
	  </div>
	  <div id="foot">
		<address>&copy;2011 <a href="http://www.wh.sdu.edu.cn/">山东大学威海分校</a> “威致”开发团队</address>
	</div>
    </div>
</div>
</body>
</html>
