<%@ page contentType="text/html; charset=UTF-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String mess=(String)request.getAttribute("messages");
	if(mess==null||mess.equals("")) 
		mess="<li>欢迎登录！</li>";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>易索博客管理员登录</title>
		<link rel="stylesheet" href="../css/blogLogin.css" />
	</head>
	<body>	
	<div id="main">
	  <div id="logo">
	  </div>
	  <div id="form">	
	    <form action="../blog/LogonAction?action=logon" method="post">				
		<%=mess %>
		<p>用户名：<input type="text" name="userName" style="width:200"></p>
		<p>密&nbsp;&nbsp;码：&nbsp;<input type="password" name="userPass" style="width:200"></P>
		<p><input type="submit" class="button" value="登 录">&nbsp;&nbsp;
		   <input type="reset" class="button" value="重 置"></p>	
		</form>
		<a href="/aesop/blog/IndexAction">返回首页<span class="link"><%=basePath%></span></a>
	  </div>
	</div>
	</body>
</html>