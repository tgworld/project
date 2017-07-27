package myce.casia.db;

import java.io.IOException;
import java.util.List;

public class SelectExamation {
	
	
	public static void main(String[] args) throws IOException{
		
		//System.out.println(SelectExamation(25).getExamation());
		
		int[] kid={1,61};
		
		System.out.println(SelectExamationBykid(kid));
		
		
	}
	
	
	
	
	
	public static ExamationAndKnowledge SelectExamation(int id){
		
		ExamationAndKnowledgeDao ekdao=new ExamationAndKnowledgeDao();
	
		ExamationAndKnowledge ek=new ExamationAndKnowledge();
		
		
		ek=ekdao.getById(id);
		
		return ek;
		
	}
	public static ExamationAndKnowledge SelectExamationBykid(int[] kid){
		
		ExamationAndKnowledgeDao ekdao=new ExamationAndKnowledgeDao();
	
		ExamationAndKnowledge ek=new ExamationAndKnowledge();
		
	   List<ExamationAndKnowledge> eklist=ekdao.findAll();
	   
	  
	   for (int i = 0; i < eklist.size(); i++) {
		
	}
	   return null;
		
		
	}
}
