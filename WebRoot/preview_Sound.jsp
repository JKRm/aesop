<%@ page language="java" import="java.util.*,cn.com.shanda.aesop.pojo.*,java.text.DateFormat" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>音乐播放</title>
    <link rel="stylesheet" href="/aesop/css/preview.css" />
    <script src="/aesop/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		window.onload = function(e) {
		if (e) return setTimeout(arguments.callee, 500);
		var url = "<%=request.getAttribute("url")%>";
		WMPlayer.play(url);
		WMPlayer.addEventListener("PositionChange", function(oldPosition, newPosition) {
			//alert(this == player.wmp);
		});
		var lrc = new LRCPlayer(
			  WMPlayer.wmp
			, document.getElementById('txt').value
			, document.getElementById('lrc')
			, 230, 300
			, 'C2D5F0', '00FF00'
		);
		//setTimeout(function() { lrc.remove(); }, 1000 * 30);
	};
	</script>
</head>
<body>
<div id="main">
  <%ResourceItem item = (ResourceItem)request.getAttribute("resourceItem");%>
  <div id="audioPlayer">
  	<div style="height: 60px;"></div>
  	<div id="audioTitle">
  	<p><%=item.getTitle() %></p>
  	</div>
	  <div id="audio">
	  <script src="/aesop/js/player.js" type="text/javascript"></script>
	    <script src="/aesop/js/lrc.js" type="text/javascript"></script>
		<textarea id="txt" style="display:none;">
		${lrc}
		</textarea>
		<div id="lrc" style="float:left; background:#49648A ;"></div>
	  </div>
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
   	<div id="foot">
    	<p><address>&copy;山东大学威海分校"威致"团队</address></p>
    </div>
</div>
</body>
</html>