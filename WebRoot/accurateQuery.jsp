<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <title>高级查找</title>
  <link rel="stylesheet" href="css/accurate.css" />
  <script src="js/accurate.js" type="text/javascript"></script>
</head>
<body>
  <div id="main">
    <div id="logo">
    </div>
    <div id="form">
      <form action="accurateSearch" method="post">
	   <p><label for="queryText">搜索条件:</label><input type="text" id="queryText" name="queryText" /></p>
	   <p><label for="wholekey">包含完整关键字:</label><input type="text" id="wholekey" name="wholekey" /></p>
	   <p><label for="excludingText">不包含关键字:</label><input type="text" id="excludingText" name="excludingText" /></p>
	   <p><label for="field">检索字段:</label><select id="field" name="field" /><option value="all">全部</option><option value="title">标题</option><option value="keywords">关键字</option><option value="description">简介</option></select></p>
	   <p><label for="author">作者:</label><input type="text" id="author" name="author" /></p>
	   <p><label for="publisher">出版商:</label><input type="text" name="publisher" /></p>
	   <p class="checkbox"><label for="type">类型:</label><input type="checkbox" name="type" id="all" value="all"  />全部</p>
	   								  <p class="checkbox"><input type="checkbox" name="type" id="document" value="document" />文档</p>
	   								  <p class="checkbox"><input type="checkbox" name="type" id="audio" value="audio" />音频</p>
	   								  <p class="checkbox"><input type="checkbox" name="type" id="video" value="video" />视频</p>
	   								  <p class="checkbox"><input type="checkbox" name="type" id="other" value="other" />其他</p>
	   <p><input type="submit" id="search" name="search" value="搜索" /></p>
      </form>
    </div>
	<div id="copyright">
	<address>&copy;2011 <a href="http://www.wh.sdu.edu.cn/">山东大学威海分校</a> “威致”开发团队</address>
	</div>
  </div>
</body>
</html>
