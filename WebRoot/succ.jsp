<%--
��վ: <a href="http://www.crazyit.org">���Java����</a>
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
	<title>�ϴ��ɹ�</title>
</head>
<body>
<%GetHostIp hostIp = new GetHostIp(); %>
<font color="red"><b><a id='div1'></a></b></font>����Զ����أ��������   <a href="http://<%=hostIp.hostIp()%>:8080/aesop/manage/admin/pages/upload.jsp">��������</a>
	�ϴ��ɹ�!<br/>
	�ļ�����:<s:property value=" + uploadFileName"/><br/>
	�ļ��ؼ���:<s:property value=" + keywords"/><br/>
	�ļ�����:<s:property value=" + uploadContentType"/><br/>
	���ݷ���:<s:property value=" + TypeName"/><br/>
	�ļ�����:<s:property value=" + describe"/><br/>

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