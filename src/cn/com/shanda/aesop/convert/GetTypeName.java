package cn.com.shanda.aesop.convert;

//�������ڻ�ȡ�ϴ��ļ�ҳ����ѡ���ļ�����

public class GetTypeName {
	
	private String TypeName = "δ����";
	
	public String GetType(String kind){
		
		if(kind.equals("1")){
			
			TypeName = "ѧ������";
			
		}
		
		else if(kind.equals("2")){
			
			TypeName = "����ѧϰ";
		}
		
		else if(kind.equals("3")){  
			
			TypeName = "�ʸ���";
		}
		
		else if(kind.equals("4")){
	
			TypeName = "רҵ����";
		}
		
		else if(kind.equals("5")){
	
			TypeName = "Ӧ������";
		}
		
		else if(kind.equals("6")){
	
			TypeName = "��ѧ��Ʒ";
		}
		
		else if(kind.equals("7")){
			
			TypeName = "��������";
		}
		
		return TypeName;
	}

}
