<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <title>易索主页</title>
  <meta http-equiv="pragma" content="no-cache">  
  <meta http-equiv="cache-control" content="no-cache">  
  <meta http-equiv="expires" content="0">  
  <link rel="stylesheet" href="css/style.css" />
  <script src="js/suggest.js" type="text/javascript"></script>
  <script src="js/voice.js" type="text/javascript"></script>
  <script src="js/index.js" type="text/javascript"></script>
</head>
<body>
  <div id="main">
    <div id="logo">
    </div>
    <div id="form">
      <form action="search" method="post" name="Form1">
        <ul>
		  <li id="all"><input type="radio" name="type" value="all" checked="checked" />全部</li>
		  <li id="file"><input type="radio" name="type" value="document" /><a href="/aesop/function/WenkuAction?queryText=">文档</a></li>
		  <li id="mp3"><input type="radio" name="type" value="audio"/><a href="/aesop/function/AudioAction?queryText=">音频</a></li>
		  <li id="video"><input type="radio" name="type" value="video"/><a href="/aesop/function/VideoAction?queryText=">视频</a></li>
		  <li id="picture"><input type="radio" name="type" value="picture"/><a href="/aesop/function/PictureAction?queryText=">图片</a></li>
		  <li id="other"><input type="radio" name="type" value="other" />其他</li>
	   <ul>
	   <input type="text" id="queryText" name="queryText" />
	   <input type="submit" id="search" name="search" value="易索" />&nbsp;
	   <span id="spanOutput"></span>
	   <input type="button" id="voice" value="语音体验"/><br/>
	   <img id="wait" src="images/blank.gif" height="40px" width="40px"/>
      </form>
    </div>
    <div id="jump">
      <p><a href="/aesop/accurateQuery.jsp">高级查找</a>&nbsp;|&nbsp;
         <a href="/aesop/blog/IndexAction">易索博客</a>&nbsp;|&nbsp;
         <a target="_blank" href="/aesop/aesopmap/map.jsp">易索地图</a>&nbsp;|&nbsp;
         <a target="_blank" href="/aesop/rss.jsp">RSS<img src="/aesop/images/rss.png"></a>
      </p>
    </div>
	<div id="copyright">
	<address>&copy;2011 <a href="http://www.wh.sdu.edu.cn/">山东大学威海分校</a> “威致”开发团队</address>
	</div>
  </div>
</body>
</html>
