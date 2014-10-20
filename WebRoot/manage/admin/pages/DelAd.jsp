<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.com.shanda.aesop.server.GetHostIp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>广告删除</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">   
	
			function check (name) {
				var names = document.getElementsByName(name);
                var len = names.length;   
                if (len > 0) {   
                    var i = 0;   
                    for (i = 0; i < len; i++)   
                    	if (names[i].checked == true) {
                    		return true;
                    	}   
                }   
                
                return false;
			};
			
            function checkEvent(name, allCheckId) {   
                var allCk = document.getElementById(allCheckId);   
                if (allCk.checked == true) checkAll(name);   
                else checkAllNo(name);   
  
            }   
  
            //全选   
            function checkAll(name) {   
                var names = document.getElementsByName(name);   
                var len = names.length;   
                if (len > 0) {   
                    var i = 0;   
                    for (i = 0; i < len; i++)   
                    names[i].checked = true;   
  
                }   
            }   
  
            //全不选   
            function checkAllNo(name) {   
                var names = document.getElementsByName(name);   
                var len = names.length;   
                if (len > 0) {   
                    var i = 0;   
                    for (i = 0; i < len; i++)   
                    names[i].checked = false;   
                }   
            }   
  
            //反选   
            function reserveCheck(name) {   
                var names = document.getElementsByName(name);   
                var len = names.length;   
                if (len > 0) {   
                    var i = 0;   
                    for (i = 0; i < len; i++) {   
                        if (names[i].checked) names[i].checked = false;   
                        else names[i].checked = true;   
  
                    }   
                }   
  
            }   
        </script> 
		
  </head>
  
  <body>
  <div >
  <%GetHostIp hostIp = new GetHostIp(); %>
  <a href="http://<%=hostIp.hostIp()%>:8080/aesop/manage/admin/pages/DelAd.jsp">立即刷新</a><br /><br />
  <font size="3" color=red ><b>${requestScope.message}</b></font></div>
现有广告列表：<br>
  </body>


<%@ page contentType="text/html; charset=utf-8" %>

<%@ page language="java" %>

<%@ page import="java.util.List" %>

<%@ page import="cn.com.shanda.aesop.dao.impl.*" %>

<%@ page import="cn.com.shanda.aesop.pojo.*" %>

<% 
	AdvertisementDaoImpl adi = new AdvertisementDaoImpl();
	
	ArrayList<Advertisement> al = adi.getAdList();
			
%>
<input type="checkbox" name="ra" id="ckall" onclick="checkEvent('checkbox','ckall')"/>全选
<input type="checkbox" name="ra" id="ckReserve" onclick="reserveCheck('checkbox','ckReserve')"/>反选  
<form action="del/DelAdAction" method="post" onsubmit="return check('checkbox');">
<table border="5">
<tr>
<th width=80 height=25><div align=center>是否删除</div></th>
<th width=200 height=25><div align=center>广告词</div></th>
<th width=80 height=25><div align=center>广告链接</div></th>
</tr>
<%
 for(int i=0;i<al.size();i++){
		String adwords = al.get(i).getAdword();
		String adlinks = al.get(i).getAdlink();
%>
<tr>
<td align="center"><input type="checkbox" name="checkbox" value=<%=adwords%> /></td>
<td><div align=center><%=adwords%></div></td>
<td><div align=center><%=adlinks%></div></td>
</tr>
<%
 }
%>
</table>
<p align="justify">
<input id="submit" name="submit" type="submit" value="确定">
</p>
<%
out.print("广告列表查询完毕！"); 
%>
</form>
<body><br><br><br></body>
</html>

