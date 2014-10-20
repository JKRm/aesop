package cn.com.shanda.aesop.util;

import java.io.File;

public class FileDeleter {
    /** 
     * ɾ�������ļ� 
     * @param   sPath    ��ɾ���ļ����ļ��� 
     * @return �����ļ�ɾ���ɹ�����true�����򷵻�false 
     */  
    public static boolean deleteFile(String sPath) {  
        boolean flag = false;  
        File file = new File(sPath);  
        // ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
        if (file.isFile() && file.exists()) {  
            
        	file.delete();  
            flag = true;  
        }
        return flag;  
    }  
}
