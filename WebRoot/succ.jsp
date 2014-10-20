<%--
网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2012, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>

<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="cn.com.shanda.aesop.server.GetHostIp" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>上传成功</title>
</head>
<body>
<%GetHostIp hostIp = new GetHostIp(); %>
<font color="red"><b><a id='div1'></a></b></font>秒后自动返回，继续添加   <a href="http://<%=hostIp.hostIp()%>:8080/aesop/manage/admin/pages/upload.jsp">立即返回</a>
	上传成功!<br/>
	文件标题:<s:property value=" + uploadFileName"/><br/>
	文件关键词:<s:property value=" + keywords"/><br/>
	文件类型:<s:property value=" + uploadContentType"/><br/>
	内容分类:<s:property value=" + TypeName"/><br/>
	文件描述:<s:property value=" + describe"/><br/>

</body>
</html>
<script type="text/javascript">

			var t = 10;

			function showTime()
			{
    			t -= 1;
    			document.getElementById('div1').innerHTML= t;
    
    		if(t==0)
    		{
        		location.href='http://<%=hostIp.hostIp()%>:8080/aesop/manage/admin/pages/upload.jsp';
    		}
    
    		setTimeout("showTime()",1000);
			}

			showTime();
</script>