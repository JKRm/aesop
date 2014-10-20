<%@ page language="java" import="java.util.*,cn.com.shanda.aesop.pojo.*,java.text.DateFormat" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>视频预览</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="/aesop/css/preview.css">

	</head>

	<body>
	<div id="main">
	<%ResourceItem item = (ResourceItem)request.getAttribute("resourceItem");%>
		<div id="player">
			<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="500" height="400">
		    <param name="movie" value="${flvPlayer}" />
		    <param name="quality" value="high" />
		    <param name="allowFullScreen" value="true" />
		    <param name="FlashVars" value="vcastr_file=${flvUrl}&LogoText=aesop&BufferTime=3" />
		    <embed src="${flvPlayer}" allowfullscreen="true" flashvars="vcastr_file=${flvUrl}&LogoText=aesop" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="500" height="375"></embed>
			</object>
		  </div>
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
	   	<div style="clear: both;"></div>
		<div id="foot">
	    	<p><address>&copy;山东大学威海分校"威致"团队</address></p>
	    </div>
	</div>
	</body>
</html>
