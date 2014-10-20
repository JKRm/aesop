<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>利用拦截器实现文件过滤</title>
	<s:head/>
</head>
<body>
<s:form action="uploadAction"  namespace="/upload" enctype="multipart/form-data"  method="post">
	<s:textfield name="author" label="作    者  "/><br />
	<s:textfield name="publisher" label="出版社  "/><br />
	<s:textfield name="keywords" label="关键词   "/><br />
	<s:textarea name="describe" label="文件描述" style="height:5em;width:13em;resize:none"/><br />
	<s:checkbox name="checked" label="是否上传至易索文库(限制格式ppt,pptx,doc,docx)"></s:checkbox><br />
	<s:select list="#{1:'学术教育',2:'外语学习',3:'资格考试',4:'专业文献',5:'应用文书 ',6:'文学作品',7:'生活娱乐'}" label="文件分类" name="doctype"></s:select>
	<s:file name="upload" label="选择文件"/><br />
	<s:submit value="上传"/>
</s:form>
</body>
</html>
