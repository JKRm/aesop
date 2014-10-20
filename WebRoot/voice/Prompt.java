import java.io.BufferedReader;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

class Prompt {
    // native method that prints a prompt and reads a line
    
	private native String getLine(String returnString);
	
    static {
        System.loadLibrary("Prompt");  // 此处"Prompt" 是 dll的名称
    }
 
    public static void main(String args[]) throws Exception {
   	
     	Prompt p = new Prompt();
        String str=new String();
        String input = p.getLine(str);  // 此处得到 C程序传递来的字符串
        //input = new String(input.getBytes("ISO8859-1"), "GBK");    // 此处对字符串改变编码，以保证中文不乱码  
        System.out.println(input);
        
    }
    
}
