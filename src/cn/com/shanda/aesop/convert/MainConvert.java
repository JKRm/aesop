package cn.com.shanda.aesop.convert;

import java.io.IOException;

//�����ļ�����ת������

public class MainConvert {
	/**
	 * @param args
	 * @throws Exception 
	 * @throws Exception 
	 */
	public static String DoConvert(String inputfilename, String date) throws Exception{
	     
	     Converter ct = new Converter();
	     
	     if(!(inputfilename.substring(inputfilename.lastIndexOf(".")+1).equals("pdf"))){
	    	 
	    	 Others2PDF.OTHERS2PDF(inputfilename);
	    
	     }
	     
	     ct.convertPDF2SWF(inputfilename, date);
	     
	     return ct.getFileName();
		
	}
	
	
}
