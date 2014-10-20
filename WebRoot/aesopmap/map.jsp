<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>aesop地图</title>

    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="/aesop/css/map.css" />
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
	

  </head>
  
  <body>
  <div id="map">
  <div id="left">
  	<div class="form">
    <h4>请输入需要查询的城市</h4>
    <form action="map/MapAction.action" method="post">
    <p class="center">
    	<input id="city" name="city" type="text" value="<%=request.getParameter("city")==null?"":request.getParameter("city")%>"/>
    	<input id="submit" name="submit" type="submit" value="确定">
    </p>
    </form>
	</div>
	<div class="form">
	<h4>请输入需要查询路线</h4>
	<p>(可在地图中拖拽更改始终点)</p>
    <form action="map/WayAction.action" method="post">
	    <p class="center">起点：<input id="from" name="from" type="text" value="<%=request.getParameter("from")==null?"":request.getParameter("from")%>"/></p>
	    <p class="center">终点：<input id="to" name="to" type="text" value="<%=request.getParameter("to")==null?"":request.getParameter("to")%>"/></p>
	    <input type="hidden" value="<%=request.getParameter("city")%>" name="city"/>
	    <p class="center"><input id="submit" name="submit" type="submit" value="确定"></p>
    </form>
    </div>
    <div id="results" style="font-size:20px;"></div>
  </div>
  <div id="content">
    <div id="container"></div>
    <div id="foot">
		<address>&copy;2011 <a href="http://www.wh.sdu.edu.cn/">山东大学威海分校</a> “威致”开发团队</address>
	</div>
	<script type="text/javascript">
		var map = new BMap.Map("container");  // 创建地图实例
		var city = "<%=request.getParameter("city")%>";
		if (city == null || city == "null") {
			city = "济南";
		}
		map.enableScrollWheelZoom();
		map.addControl(new BMap.NavigationControl());  
		map.addControl(new BMap.ScaleControl());  
		map.addControl(new BMap.OverviewMapControl());  
		map.addControl(new BMap.MapTypeControl());  
		map.centerAndZoom(city);                 // 初始化地图，设置中心点坐标和地图级别
		/*var local = new BMap.LocalSearch(map, {
  			renderOptions: {map: map, panel: "results"}
		});
		local.search("饭店");*/

		map.enableScrollWheelZoom();
		var transit = new BMap.DrivingRoute(map, {
		    renderOptions: {map: map,panel: "results"},            
		    onMarkersSet: function(pois){
		       var start = pois[0].marker, end = pois[1].marker;
		       start.enableDragging();//开启起点拖拽功能
		       end.enableDragging();//开启终点拖拽功能
		       start.addEventListener("dragend",function(e){                   
		       map.clearOverlays();
		       transit.search(e.point,end.getPosition());                   
		     });
		     end.addEventListener("dragend",function(e){                    
		        map.clearOverlays();                      
		        transit.search(start.getPosition(),e.point);                  
		     });
		 	}
    	});

    	transit.search("<%=request.getParameter("from")%>","<%=request.getParameter("to")%>");
	</script>
  </div>
  </div>
</body>
</html>
