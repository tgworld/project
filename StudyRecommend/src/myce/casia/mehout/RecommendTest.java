package myce.casia.mehout;

import java.util.List;
import java.util.Scanner;

import my.casia.rules.JenaReasonerByName;
import myce.casia.db.ExamationAndKnowledge;
import myce.casia.db.SelectExamation;
import myce.casia.nlp.ContentParsing;

public class RecommendTest {

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub\
		
		while(true){
			
			Scanner in=new Scanner(System.in);
			
			System.out.println("请输入某用户的答题记录的id");
			
			int uid=in.nextInt();
			
			
			List<Integer> idlist=CompsiteRecommend.itemid(uid);//系统根据基于用户的系统过滤来推荐试题
			
			for(int i=0;i<idlist.size();i++){
				ExamationAndKnowledge ekrecommend=SelectExamation.SelectExamation(idlist.get(i));
				System.out.println("推荐的试题编号与内容："+idlist.get(i)+"   "+ekrecommend.getExamation());
			}//根据user-based 的itemid查询数据库获得试题
			
			
			
	        ExamationAndKnowledge ekerror=SelectExamation.SelectExamation(459);//根据错题查询题库
	        
	        if(ekerror.getKnowledge()!=null){
	        	
	        	
	        	System.out.println("关联的知识点为：  "+ekerror.getKnowledge());//打印试题关联的知识点
	        	String[] ks=ContentParsing.printKnText(ekerror).split("\\s+");
	        	for (int i = 0; i < ks.length; i++) {
	        		System.out.println("知识点"+ks[i]+"的前序知识点（包含推理）为：");
		        	JenaReasonerByName.isSuccessorQuery(ks[i]);
		        	System.out.println("知识点"+ks[i]+"的属于的知识点（包含推理）为：");
		        	JenaReasonerByName.containQuery(ks[i]);
				}
	        	JenaReasonerByName.testExpert();
	        	
	        	System.out.println("根据前序与包含知识点查询题库，进行推荐的题目为：");
	        	System.out.println(SelectExamation.SelectExamation(1).getExamation());
	        	
	        }
	        else{
	        	
				ContentParsing.knowledgeattach(ekerror);//分析错题关联知识点更新试题知识点库
				
				
	        	
	        }
		
	        
			
			
		}
		
		

	
		
		
		
	}

}
