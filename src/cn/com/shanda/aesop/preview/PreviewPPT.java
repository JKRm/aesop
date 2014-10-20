package cn.com.shanda.aesop.preview;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;
import cn.com.shanda.aesop.preview.GetFileName;

/*
 * 本类的作用：
 * 对URL地址对应的PPT文件进行图片转换，将前4页以图片形式保存到本地（不足4页全部保存），
 * 返回保存的文件夹的路径。
 * 在预览的 JSP界面中，根据文件夹路径读取图片显示出来即可。
 */

public class PreviewPPT {

	// 将PPT2003前几页转换为图片保存在本地
	public static String urlPPTtoImage(String urlPPT, String localPath) throws Exception {
		
		File folder = null;
		System.out.println(urlPPT);
		String file = urlPPT.substring(0, urlPPT.lastIndexOf("/") + 1) +
			URLEncoder.encode(urlPPT.substring(urlPPT.lastIndexOf("/") + 1, urlPPT.lastIndexOf(".")), "UTF-8") + ".ppt";
		System.out.println(file);
		
		try {
			URL url = new URL(file);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDefaultUseCaches(false);
			con.setUseCaches(false);
			// 指示应用程序要从 URL 连接读取数据
			con.setDoOutput(true);
			con.setDoInput(true);
			con.connect();
			InputStream in = con.getInputStream();
			if(in == null) return null;
			HSLFSlideShow hss = new HSLFSlideShow(in);
			// is 为文件的InputStream，建立SlideShow
			// 获得每一张幻灯片
			SlideShow ppt = new SlideShow(hss);
			in.close();
			Dimension pgsize = ppt.getPageSize();
			Slide[] slide = ppt.getSlides();
			for (int i = 0; i < slide.length; i++) {
				BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics = img.createGraphics();
				graphics.setPaint(Color.white);
				graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
				slide[i].draw(graphics);
				// 这里设置图片的存放路径和图片的格式(jpeg,png,bmp等等),注意生成文件路径
				folder = new File(localPath);
				folder.mkdir();
				if (i < 4) {
					FileOutputStream out = new FileOutputStream(folder.toString() + "\\"+ GetFileName.StringFilter(urlPPT) + "_"+ (i + 1) + ".jpg");
					javax.imageio.ImageIO.write(img, "jpg", out);
					out.close();
				}
			}
			return folder.toString();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		if(folder == null) return null;
		else return folder.toString();
	}
	
}
