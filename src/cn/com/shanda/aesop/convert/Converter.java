package cn.com.shanda.aesop.convert;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.com.shanda.aesop.util.FileDeleter;

//运用工具SWFTools执行将PDF文件向swf文件的转化

public class Converter {
	
	String fileName = "";
	
	String sourcePath = System.getenv("CATALINA_HOME") + "\\webapps\\resources\\swf\\pdf\\";
	
	String destPath = System.getenv("CATALINA_HOME") + "\\webapps\\resources\\swf";
	
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	
	//更改时间：9.9 19：27， 添加了一个参数
	 public int convertPDF2SWF(String filename, String date) throws IOException { 
		 
		 	String convertedName = filename.substring(0,filename.lastIndexOf(".")) + ".pdf";
		 	
		 	sourcePath = sourcePath + convertedName;
			 
		    fileName = date + ".swf";
		 
	        File dest = new File(destPath); 
	        
	        if (!dest.exists()) dest.mkdirs();   
	           
	        //源文件不存在则返回   
	        File source = new File(sourcePath);
	        
	        if (!source.exists()){
	        	
	        	return 0;   
	        }
	           
	        //调用pdf2swf命令进行转换   
	        
	        String command = System.getenv("CATALINA_HOME") + "\\webapps\\aesop\\tools\\pdf2swf.exe" +
	        				" -o \"" + destPath + "\\" + fileName + "\" -s flashversion=9 \""  + sourcePath + "\"";
	        
	        Process pro = Runtime.getRuntime().exec(command);   
	        
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));   
	        
	        while (bufferedReader.readLine() != null);    
	           
	        try {   
	            pro.waitFor();   
	            
	        } catch (InterruptedException e) {   
	     
//	            e.printStackTrace();  
	            
	        } 
	           
	        return pro.exitValue();
	        
	        
	           
	    } 
	 
	 public String getFileName() {
	 		
	 		return fileName;
	 }
	 		
	       

}
