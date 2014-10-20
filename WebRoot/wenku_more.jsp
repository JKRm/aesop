<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="cn.com.shanda.aesop.dao.impl.PreviewXMLParserDaoImpl" %>
<%@ page import="cn.com.shanda.aesop.pojo.PreviewItem" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="cn.com.shanda.aesop.convert.GetTypeName" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文库</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="/aesop/css/preview.css" />
  </head>
  <body>
  <div id="main">
  <div id="wenkuHead">
  </div>
  <div id="head">
	<a href="/aesop/index.jsp"><img src="/aesop/images/logo_small.png" id="logo_small"></a>
	<form id="form" action="/aesop/function/DocumentAction" method="post" >
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
  <div id="wenkuBody">
	<%
 		PreviewXMLParserDaoImpl pxpdi = new PreviewXMLParserDaoImpl();
 		List<PreviewItem> list = new ArrayList<PreviewItem>();
 		GetTypeName gtn = new GetTypeName();
 		Map<String, List<PreviewItem>> map = new HashMap<String, List<PreviewItem>>();
 		map = pxpdi.getPreviewItems();
 		String filename;
 		String link;
 		String kind = request.getParameter("kind");
 		String typeName = gtn.GetType(kind);
 			if(map.get(kind)!=null){%>
 		<p class="wenkuTitle right"><%=typeName %></p>
 		<%
 				for(int i=0;i<map.get(kind).size();i++){
 					filename = map.get(kind).get(i).getName();
 					link = map.get(kind).get(i).getPreviewUrl();
	%>
	<p><a target="_blank" href = "http://localhost:8080/aesop/preview?url=<%=link %>&type=wenku"><%=filename %></a></p>
	<%} } else {%>
		<p class="center">暂无此类文章</p>
	<%}%>
	</div>
	</div>
</body>
</html>
