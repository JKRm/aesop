package cn.com.shanda.aesop.server;

import java.rmi.Naming;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.com.shanda.aesop.pojo.PreviewItem;


public class PreviewSearchRunnable implements Runnable{
	
	private String remoteip;
	
	public PreviewSearchRunnable(String path){
		this.remoteip = path;
	}

	@Override
	public void run() {
		try {
			
			Map<String, List<PreviewItem>> temporaryMap = new HashMap<String, List<PreviewItem>>();
			
			Registered registry=(Registered) Naming.lookup("rmi://" + remoteip + "/registryimpl");
			
			temporaryMap = registry.getPreviewItems();
			
			Iterator<String> it = temporaryMap.keySet().iterator();
			
			while(it.hasNext()) {
				String kind = it.next();
				if (WenKuTemporaryMap.map.containsKey(kind)) {
					WenKuTemporaryMap.map.get(kind).addAll(temporaryMap.get(kind));
				} else {
					WenKuTemporaryMap.map.put(kind, temporaryMap.get(kind));
				}
			}
			
			
		} catch (Exception e) {
			
			System.out.println("¼ìË÷"+remoteip+"Ê§°Ü,É¾³ýÏàÓ¦µÄµØÖ·");
			
			for(int i=0;i<IpList.list.size();i++){
				if(!(IpList.list.get(i).toString().equals(remoteip))){
					Warn remove=new Warn(IpList.list.get(i).toString(),remoteip);
					Thread thread=new Thread(remove);
					thread.start();
				}
			}
		}	
	}

}
