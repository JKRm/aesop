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
 * ��������ã�
 * ��URL��ַ��Ӧ��PPT�ļ�����ͼƬת������ǰ4ҳ��ͼƬ��ʽ���浽���أ�����4ҳȫ�����棩��
 * ���ر�����ļ��е�·����
 * ��Ԥ���� JSP�����У������ļ���·����ȡͼƬ��ʾ�������ɡ�
 */

public class PreviewPPT {

	// ��PPT2003ǰ��ҳת��ΪͼƬ�����ڱ���
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
			// ָʾӦ�ó���Ҫ�� URL ���Ӷ�ȡ����
			con.setDoOutput(true);
			con.setDoInput(true);
			con.connect();
			InputStream in = con.getInputStream();
			if(in == null) return null;
			HSLFSlideShow hss = new HSLFSlideShow(in);
			// is Ϊ�ļ���InputStream������SlideShow
			// ���ÿһ�Żõ�Ƭ
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
				// ��������ͼƬ�Ĵ��·����ͼƬ�ĸ�ʽ(jpeg,png,bmp�ȵ�),ע�������ļ�·��
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
