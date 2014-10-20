<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<html>

<head>
<title>tomcat</title>
<link rel="stylesheet" href="chrome://browser/skin/feeds/subscribe.css" type="text/css" media="all"></link>
<link rel="stylesheet" href="chrome://browser/content/feeds/subscribe.css" type="text/css" media="all"></link>
<script type="application/javascript" src="chrome://browser/content/feeds/subscribe.js"></script>
</head>

<body onload="SubscribeHandler.writeContent();" onunload="SubscribeHandler.uninit();">
<div id="feedHeaderContainer">
<div id="feedHeader" class="feedBackground" dir="ltr">
<div id="feedIntroText">
<p id="feedSubscriptionInfo1"></p>
<p id="feedSubscriptionInfo2"></p>
</div>
<div id="feedSubscribeLine"></div>
</div>
</div>
<script type="application/javascript">
SubscribeHandler.init();
</script>
<a id="feedTitleLink">
</a>
<%

java.util.Properties systemSettings = System.getProperties();

systemSettings.put("http.proxyHost", "mywebcache.com");

systemSettings.put("http.proxyPort", "8080");

System.setProperties(systemSettings);

String filePath = System.getenv("CATALINA_HOME") + "/webapps/aesop/rss/test.xml";

//String urlStr = "http://localhost:8080/aesop/rss/test.xml";

//java.net.URLConnection feedUrl = new java.net.URL(urlStr).openConnection();

//feedUrl.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

com.sun.syndication.io.SyndFeedInput input = new com.sun.syndication.io.SyndFeedInput();

//com.sun.syndication.feed.synd.SyndFeed feed = input.build(new com.sun.syndication.io.XmlReader(feedUrl));

com.sun.syndication.feed.synd.SyndFeed feed = input.build(new java.io.File(filePath));
%>

<div align="center">

<h1>易索订阅</h1>

<table border=1 cellpadding=3 width="700">

<tr>

<th>Number</th>

<th>Title</th>

<th>Time</th>

</tr>

<%

java.util.List list = feed.getEntries();

for (int i = list.size()-1; i >= 0; i--) {

com.sun.syndication.feed.synd.SyndEntry entry = (com.sun.syndication.feed.synd.SyndEntry)list.get(i);

%>

<tr>

<td><%=list.size()- i%></td>

<td><a href="<%=entry.getLink()%>"><%=entry.getTitle()%></a></td>

<td><%=entry.getPublishedDate()%></td>

</tr>

<%}%>

</table>

</div>

<br>
<li>
<a href="http://mail.qq.com/cgi-bin/feed?u=feed://localhost:8080/aesop/rss/test.xml">
<img border="0" src="http://img.feedsky.com/images/icon_subshot02_qq.gif" alt="订阅到QQ邮箱">
</a>
</li>
</body>

</html>