package cn.com.shanda.aesop.convert;

import java.io.File;    
import com.artofsolving.jodconverter.DocumentConverter;    	  
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;    	  
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;    	  
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter; 

//运用OpenOffice.Org将上传的doc docx ppt pptx类型的文件自动转化为PDF

public class Others2PDF {  
	
	
	public static void OTHERS2PDF(String filename) throws Exception{
		
		String TomCatpath = System.getenv("CATALINA_HOME");    
		  
	    String Path = TomCatpath+ "\\" + "webapps"+"\\"+"resources";
		
		String input = Path + "\\" + filename ;
		
		String convertedName = filename.substring(0,filename.lastIndexOf(".")) + ".pdf";
		
		String output = Path + "\\" + "swf" + "\\" + "pdf" + "\\" + convertedName;

	    File inputFile = new File(input); 
	    
	    File outputFile = new File(output);  
	    
	    OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100); 
	    
	    try {    
	          connection.connect();
	          
	          DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
	          
	          converter.convert(inputFile, outputFile);    
	          
	        } 
	    catch(Exception e) {
	    	
//	            e.printStackTrace();  
	            
	        }
	    finally {  
	    	
	            try{ 
	            	if(connection != null){
	            		
	            		connection.disconnect();
	            		
	            		connection = null;
	            		
	            		}
	            	}
	            catch(Exception e){}    
	        }    
	    }
}