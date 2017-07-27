
package myce.casia.db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddExamationAndKnowledge {

	private static StringBuffer content;
	private static BufferedReader br;
	
	public static void main(String[] args){
		

		String content="1．（2017•成都）《九章算术》中注有“今两算得失相反，要令正负以名之”，意思是：今有两数若其意义相反，则分别叫做正数与负数，若气温为零上10℃记作+10℃，则-3℃表示气温为（　　）A．零上3℃ B．零下3℃ C．零上7℃ D．零下7℃难度：0.60真题：1组卷：902";
		
		updateExamationAndknowledge(content, "  ");
		
		
	
		
	}
	
	
	public static void AddExamationAndKnowledge(String content,String kid){
		
		ExamationAndKnowledgeDao ekdao=new ExamationAndKnowledgeDao();
		ExamationAndKnowledge ek=new ExamationAndKnowledge();
		ek.setExamation(content);
		ek.setKnowledge(kid);
		ekdao.save(ek);
		System.out.println("已存入数据库");
		
		
		
	}
	public static void updateExamationAndknowledge(String content,String kid){
		ExamationAndKnowledgeDao ekdao=new ExamationAndKnowledgeDao();
		ExamationAndKnowledge ek=ekdao.getByContent(content);
		ek.setExamation(content);
		ek.setKnowledge(kid);
		ekdao.update(ek);
		System.out.println("试题更新完毕");
		
	}
	
}