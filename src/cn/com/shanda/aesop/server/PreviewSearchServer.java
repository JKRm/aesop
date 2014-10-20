package cn.com.shanda.aesop.server;

public class PreviewSearchServer {
	
	public void previewSearch() {
		
		for(int i=0;i<IpList.list.size();i++){
			
			PreviewSearchRunnable preview = new PreviewSearchRunnable(IpList.list.get(i).toString());
			
			Thread thread = new Thread(preview);
			
			thread.start();
			
		}
	}
}
