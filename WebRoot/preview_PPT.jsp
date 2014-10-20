<%@ page language="java" import="java.util.*,cn.com.shanda.aesop.preview.*,cn.com.shanda.aesop.pojo.*,java.text.DateFormat" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Preview PPT</title>
    <link rel="stylesheet" href="/aesop/css/preview.css" />
  </head>
  <body>
	<%
	String folder=(String)request.getAttribute("folder");
	String mark=(String)request.getAttribute("mark");
	ResourceItem item = (ResourceItem)request.getAttribute("resourceItem");
	%>
	<div id="main">
   	<div id="head">
   	  <div class="fileInfo">
	   	    <b class="b1"></b><b class="b2 d1"></b><b class="b3 d1"></b><b class="b4 d1"></b>
			<div class="b d1 k <%=item.getKind() %>">
				<p>文档名称：<span><%=item.getTitle() %></span></p>
	   			<p>关键字：<span><%=item.getKeywords() %></span></p>
	   			<p>简介：<span><%=item.getDescription() %></span></p>
	   			<p>作者：<span><%=item.getAuthor() %></span>&nbsp;&nbsp;&nbsp;&nbsp;出版单位：<span><%=item.getPublisher() %></span></p>
	   			<p>上传日期：<span><%=DateFormat.getDateTimeInstance().format(item.getDate()) %></span></p>
			</div>
			<b class="b4b d1"></b><b class="b3b d1"></b><b class="b2b d1"></b><b class="b1b"></b>
	  </div>
	  <div class="download">
	  	<a href="<%=item.getUrl() %>"><img src="/aesop/images/preview/download.png"></img></a>
	  </div>
   	</div> 
    <div id="body">
    	<br>
      <div class="showPPT">
		<% for(int i = 1;i<=4;i++){ %>
		<div class="pic">
		<img src="<%=mark%>/<%=mark%>_<%=i%>.jpg" /></div><%}%>
		</div>
      </div>
    <div id="foot">
    	<p><address>&copy;山东大学威海分校"威致"团队</address></p>
    </div>
  </div>
  </body>
</html>
