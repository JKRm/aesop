package cn.com.shanda.aesop.convert;

//该类用于获取上传文件页面所选的文件类型

public class GetTypeName {
	
	private String TypeName = "未分类";
	
	public String GetType(String kind){
		
		if(kind.equals("1")){
			
			TypeName = "学术教育";
			
		}
		
		else if(kind.equals("2")){
			
			TypeName = "外语学习";
		}
		
		else if(kind.equals("3")){  
			
			TypeName = "资格考试";
		}
		
		else if(kind.equals("4")){
	
			TypeName = "专业文献";
		}
		
		else if(kind.equals("5")){
	
			TypeName = "应用文书";
		}
		
		else if(kind.equals("6")){
	
			TypeName = "文学作品";
		}
		
		else if(kind.equals("7")){
			
			TypeName = "生活娱乐";
		}
		
		return TypeName;
	}

}
