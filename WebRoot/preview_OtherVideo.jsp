<%@ page language="java" import="java.util.*,cn.com.shanda.aesop.pojo.*,java.text.DateFormat" pageEncoding="GB2312"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>视频播放</title>
		<link rel="stylesheet" href="/aesop/css/preview.css" />
	</head>

	<body>
	<div id="main">
	<%ResourceItem item = (ResourceItem)request.getAttribute("resourceItem");%>
		<div id="player">
		<object id="MediaPlayer" height="400" width="500"
			classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6"
			type="application/x-oleobject">
			<param name="ShowPositionControls" value="0" />
			<param name="AutoStart" value="0" />
			<param name="EnableContextMenu" value="0">
			<param name="URL" value="${url}" />
			<embed id="MediaPlayer1"height="373" width="500" src="${url}" type="application/x-mplayer2" autostart="0" EnableContextMenu="0"></embed>
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
	</div>
	</body>
</html>
