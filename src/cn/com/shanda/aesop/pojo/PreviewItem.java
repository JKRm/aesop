package cn.com.shanda.aesop.pojo;

import java.io.Serializable;

public class PreviewItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2763304653290172968L;

	private String name;
	
	private String previewUrl;
	
	public PreviewItem(String name, String previewUrl){
		
		this.name = name;
		this.previewUrl = previewUrl;
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}
	
	
}
