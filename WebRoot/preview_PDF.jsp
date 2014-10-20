<%@ page language="java" import="java.util.*,cn.com.shanda.aesop.pojo.*,java.text.DateFormat" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">	
    <head> 
        <title>pdf预览</title>         
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
        <link rel="stylesheet" href="/aesop/css/preview.css" />
		<script type="text/javascript" src="js/flexpaper_flash.js"></script>
    </head> 
    <body>
    <div id="main">
    <%ResourceItem item = (ResourceItem)request.getAttribute("resourceItem");%>
    	<div id="pdf">
    	<div id="head">
	   	  <div class="fileInfo">
		   	    <b class="b1"></b><b class="b2 d1"></b><b class="b3 d1"></b><b class="b4 d1"></b>
				 <div class="b d1 k ">
					<p>文档名称：<span><%=item.getTitle()%></span></p>
		   			<p>关键字：<span><%=item.getKeywords()%></span></p>
		   			<p>简介：<span><%=item.getDescription()%></span></p>
		   			<p>作者：<span><%=item.getAuthor()%></span>&nbsp;&nbsp;&nbsp;&nbsp;出版单位：<span><%=item.getPublisher()%></span></p>
		   			<p>上传日期：<span><%=DateFormat.getDateTimeInstance().format(item.getDate())%></span></p>
				</div>
				<b class="b4b d1"></b><b class="b3b d1"></b><b class="b2b d1"></b><b class="b1b"></b>
		  </div>
		</div>
		<div class="download">
		  	<a href="<%=item.getUrl() %>"><img src="/aesop/images/preview/download.png"></img></a>
		</div>
		<div>
	        <a id="viewerPlaceHolder"></a>
	        <script type="text/javascript"> 
				var fp = new FlexPaperViewer(	
						 'FlexPaperViewer',
						 'viewerPlaceHolder', { config : {
						 SwfFile : "../resources/swf/"+<%=(String)request.getAttribute("swfName")%>+".swf",
						 Scale : 0.6, 
						 ZoomTransition : 'easeOut',
						 ZoomTime : 0.5,
						 ZoomInterval : 0.2,
						 FitPageOnLoad : true,
						 FitWidthOnLoad : false,
						 PrintEnabled : true,
						 FullScreenAsMaxWindow : false,
						 ProgressiveLoading : false,
						 MinZoomSize : 0.2,
						 MaxZoomSize : 5,
						 SearchMatchAll : false,
						 InitViewMode : 'Portrait',
						 
						 ViewModeToolsVisible : true,
						 ZoomToolsVisible : true,
						 NavToolsVisible : true,
						 CursorToolsVisible : true,
						 SearchToolsVisible : true,
  						 localeChain: 'zh_CN'
  						 //localeChain: 'en_US'
						 }});
	        </script>
	    </div>
        </div>
        <div id="foot" style="clear: both;">
    	<p><address>&copy;山东大学威海分校"威致"团队</address></p>
    	</div>
    </div>
   </body> 
</html> 