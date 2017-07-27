package myce.casia.spider.apg;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import myce.casia.db.AddExamationAndKnowledge;
import myce.casia.db.ExamationAndKnowledgeDao;
import myce.casia.db.deleteExamationAndKnowledge;

public class DealEx2mutliRe {
	
	public static Map<String, String> ex2mutliRe=new HashMap<String, String>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		deleteExamationAndKnowledge.main(args);
		
	    String path="./testlog/";
		
		List<String>  list=FileUtils.getFiles(path);
		
		for (int i = 0; i < list.size(); i++) {
			
			System.out.println(list.get(i));
			
		BufferedReader br=new BufferedReader(new InputStreamReader(
				new FileInputStream("./testlog/"+list.get(i)), "utf-8"));
		StringBuffer sb=new StringBuffer();
		String s=br.readLine();
		while (s!=null) {
			
		
			if(ex2mutliRe.containsKey(s)){
				
				ex2mutliRe.put(s, ex2mutliRe.get(s)+" "+list.get(i).split("\\.")[0].split("\\：")[1].trim());
				
				AddExamationAndKnowledge.updateExamationAndknowledge(s, ex2mutliRe.get(s));
				
			    System.out.println("*****************");
			}else{
				
				ex2mutliRe.put(s, list.get(i).split("\\.")[0].split("\\：")[1].trim());
				
				AddExamationAndKnowledge.AddExamationAndKnowledge(s,ex2mutliRe.get(s));
				
				
			}
		
			s=br.readLine();
			
		}
		}
		
		
		
		
		
		

	}

}
