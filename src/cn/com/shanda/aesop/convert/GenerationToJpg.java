package cn.com.shanda.aesop.convert;

public class GenerationToJpg {

	@SuppressWarnings("unused")
	public void processJPG(String oldfilepath) {
		String type = oldfilepath.substring(oldfilepath.lastIndexOf(".") + 1,
				oldfilepath.length()).toLowerCase();
		if (type.equals("avi")||type.equals("mpg")||type.equals("wmv")||type.equals("3gp")
				||type.equals("mov")||type.equals("mp4")||type.equals("asf")||type.equals("asx")
				||type.equals("flv")||type.equals("mepg")||type.equals("rmvb")||type.equals("rm")
				||type.equals("qt")||type.equals("dat")||type.equals("swf")) {
			String videoname = oldfilepath
			.substring(oldfilepath.lastIndexOf("\\") + 1,
					oldfilepath.lastIndexOf("."));
			String ffme = "\"" + System.getenv("CATALINA_HOME")
			+ "\\webapps\\aesop\\tools\\ffmpeg.exe\"";
			String pic = "\"" + System.getenv("CATALINA_HOME")
			+ "\\webapps\\resources\\img\\";
			try {

				Runtime runtime = Runtime.getRuntime();
				Process proce = null;
				String cmd = "";
				String cut = "     "
					+ ffme
					+ "   -i   "
					+ "\""+oldfilepath+"\""
					+ "   -y   -f   image2   -ss   8   -t   0.001   -s   600x500   "
					+ pic + videoname + ".jpg\"";
				String cutCmd = cmd + cut;
				proce = runtime.exec(cutCmd);
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
	}
}
