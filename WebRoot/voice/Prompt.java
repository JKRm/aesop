import java.io.BufferedReader;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

class Prompt {
    // native method that prints a prompt and reads a line
    
	private native String getLine(String returnString);
	
    static {
        System.loadLibrary("Prompt");  // �˴�"Prompt" �� dll������
    }
 
    public static void main(String args[]) throws Exception {
   	
     	Prompt p = new Prompt();
        String str=new String();
        String input = p.getLine(str);  // �˴��õ� C���򴫵������ַ���
        //input = new String(input.getBytes("ISO8859-1"), "GBK");    // �˴����ַ����ı���룬�Ա�֤���Ĳ�����  
        System.out.println(input);
        
    }
    
}
