package cn.com.shanda.aesop.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinTransformer {
	/** 
     * ��ȡƴ�� 
     *  
     * @param chinese 
     * @return 
     * @throws BadHanyuPinyinOutputFormatCombination 
     */  
    public static String getPinYin(String chinese) throws BadHanyuPinyinOutputFormatCombination {  
  
        String pinyin = "";  
        
        char[] words = chinese.toCharArray();  
  
        for (int i = 0; i < words.length; i++) {
        	
            String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(words[i],  
                    getDefaultOutputFormat());  
            
            // ��ת�����������ַ�ʱ,����null  
            if (pinyins != null) {  
                pinyin += pinyins[0];  
            } else {  
                pinyin += words[i];  
            }  
        }  
  
        return pinyin;  
    }  
  
    /** 
     * Default Format Ĭ�������ʽ 
     *  
     * @return 
     */  
    public static HanyuPinyinOutputFormat getDefaultOutputFormat() {
    	
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);                            // Сд  
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);                         // û����������  
        format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);                   // u��ʾ  
  
        return format;  
    }  
  
}  

