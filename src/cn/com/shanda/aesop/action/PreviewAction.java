package cn.com.shanda.aesop.action;

import java.rmi.Naming;
import java.text.DateFormat;
import java.util.Map;

import cn.com.shanda.aesop.dao.ResourceXMLParserDao;
import cn.com.shanda.aesop.dao.impl.ResourceXMLParserDaoImpl;
import cn.com.shanda.aesop.pojo.ResourceItem;
import cn.com.shanda.aesop.preview.GetFileName;
import cn.com.shanda.aesop.preview.LrcGet;
import cn.com.shanda.aesop.preview.PreviewPPT;
import cn.com.shanda.aesop.preview.PreviewWord;
import cn.com.shanda.aesop.preview.URLFileType;
import cn.com.shanda.aesop.server.IpXML;
import cn.com.shanda.aesop.server.Registered;
import cn.com.shanda.aesop.util.FileNameEncodingUtil;

import com.opensymphony.xwork2.ActionContext;

/*
 * 本类作用：得到 URL地址，根据对应文件的不同，执行不同操作
 */
public class PreviewAction {

	private String url;
	
	private String type;
	
	private ResourceXMLParserDao xmlParserDao = new ResourceXMLParserDaoImpl();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String execute() throws Exception {
		String ip = url.substring(url.lastIndexOf("//")+2, url.lastIndexOf(":"));
		System.out.println(ip);
		Registered registry =(Registered)Naming.lookup("rmi://" + ip + "/registryimpl");
		ResourceItem item = registry.getResource(url);
		Map request = (Map) ActionContext.getContext().get("request");
		
		if ("wenku".equals(type) || URLFileType.type(url).equals(".pdf")) { // 若为 pdf文件，应该？？
			// 添加PDF预览实现的代码
			String date = DateFormat.getDateTimeInstance().format(item.getDate());
			request.put("swfName", GetFileName.StringFilter(date));
			request.put("resourceItem", item);
			return "pdf";
		 } else if(URLFileType.type(url).equals(".doc")) {  // 若为word文档，则得到文档的部分文字
			PreviewWord word = new PreviewWord();
			String doc = word.getTextFromUrlWord(url);
			request.put("resourceItem", item);
			request.put("text", doc);
			return "word";
		} else if(URLFileType.type(url).equals(".ppt")) { // 若为PPT文件，则保存为本地图片并得到文件夹目录			
			String mark = GetFileName.StringFilter(url);  // 标记生成图片的名字
			GetFileName p = new GetFileName();
			String s1 = p.path();   // 得到本类WebRoot路径
			String path = s1 + "/" + mark;
			String inProject = PreviewPPT.urlPPTtoImage(url,path);   // 文件存放目录
			if(inProject == null) return "error";
			request.put("folder", inProject);
			request.put("mark", mark);
			request.put("resourceItem", item);
			return "ppt";
		} else if (URLFileType.type(url).equals(".mp4")
				|| URLFileType.type(url).equals(".avi")
				|| URLFileType.type(url).equals(".mpg")
				|| URLFileType.type(url).equals(".wmv")
				|| URLFileType.type(url).equals(".3gp")
				|| URLFileType.type(url).equals(".asf")
				|| URLFileType.type(url).equals(".flv")
				|| URLFileType.type(url).equals(".mov")
				|| URLFileType.type(url).equals(".asx")){
			
			String flvPlayer = url.substring(0, url.lastIndexOf("/"));
			flvPlayer = flvPlayer.substring(0, flvPlayer.lastIndexOf("/")+1)+"aesop/tools/Flvplayer.swf";
//			String str = url.substring(0, url.lastIndexOf("/") + 1);
//			String str1 = url.substring(url.lastIndexOf("/") + 1, url.length());
//			url = str + java.net.URLEncoder.encode(str1, "UTF-8");
//			url = url.substring(0, url.lastIndexOf(".") + 1) + "flv";
//			request.put("flvUrl", url);
			
			String date = DateFormat.getDateTimeInstance().format(item.getDate());
			String flvUrl = url.substring(0, url.lastIndexOf("/") + 1) + GetFileName.StringFilter(date) + ".flv";
			
			request.put("flvUrl", flvUrl);
			request.put("flvPlayer", flvPlayer);
			request.put("resourceItem", item);
			return "video";
		}else if (URLFileType.type(url).equals(".mpeg")
				|| URLFileType.type(url).equals(".rmvb")
				|| URLFileType.type(url).equals(".rm")
				|| URLFileType.type(url).equals(".qt")
				|| URLFileType.type(url).equals(".dat")
				|| URLFileType.type(url).equals(".swf")) {
			String str = url.substring(0, url.lastIndexOf("/")+1);
			String str1 = url.substring(url.lastIndexOf("/")+1, url.length());
//			url = str+ java.net.URLEncoder.encode(str1, "UTF-8");                 //test
			url = str+ FileNameEncodingUtil.encode(str1);
			
			request.put("url", url);
			request.put("resourceItem", item);
			return "othervideo";
		}else if (URLFileType.type(url).equals(".mp3")
				|| URLFileType.type(url).equals(".wma")
				|| URLFileType.type(url).equals(".wav")) {

			String str1 = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
			LrcGet.query(str1,new ResourceXMLParserDaoImpl().getResource(url).getAuthor());
			String str = url.substring(0, url.lastIndexOf("/") + 1);
			String str2 = url.substring(url.lastIndexOf("/") + 1, url.length());
			url = str + FileNameEncodingUtil.encode(str2);
			
			request.put("url", url);
			request.put("resourceItem", item);
			return "sound";
		} else
			return "error"; // 否则，错误提示
	}

}
