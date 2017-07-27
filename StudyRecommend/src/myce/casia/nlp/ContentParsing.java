package myce.casia.nlp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import myce.casia.db.ExamationAndKnowledge;
import myce.casia.db.ExamationAndKnowledgeDao;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;

public class ContentParsing {

	private static StringBuffer content;
	private static BufferedReader br;
	private static String[] knowledge=new String[500];
	private static String s;
	private static Integer count=0;
	static {
	
			 try {
				br = new BufferedReader(new InputStreamReader(
						new FileInputStream("./data/初中数学知识点.txt"), "utf-8"));
			} catch (UnsupportedEncodingException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               
				
				try {
					s = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while(s!=null){
				//	System.out.println(HanLP.segment(s));
				//	System.out.println(s);
					knowledge[count]=s;
					try {
						s=br.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count++;
				}
	}
	
	public static void main(String[] args) throws IOException{
		
		//System.out.println(knowledge[61]);
		System.out.println(getKnowByText("概率初步"));
	
	}
	
	public static void dictionaryload(){
        
		//CustomDictionary.add("偶数", "nz 3");
		
		CustomDictionary.add("频率", "nz 3");
		
		CustomDictionary.add("概率", "nz 3");
	}
	
	
	public static void knowledgeattach(ExamationAndKnowledge ekerrorf) {
		// TODO Auto-generated method stub

		dictionaryload();
		
		List<Term>  examationTerms=HanLP.segment(ekerrorf.getExamation());
		
		System.out.println(examationTerms);
		
		String kid="";
		
		for(int i=0;i<examationTerms.size();i++){
			
			if(examationTerms.get(i).nature.toString().contains("nz")){
				System.out.println(examationTerms.get(i).word);
				for(int j=0;j<count;j++){
					
					if(knowledge[j].contains(examationTerms.get(i).word.toString())){
						System.out.println(j+"  "+knowledge[j]);
						kid=kid+j+" ";
					}
					   
				}
			}
		}
		ExamationAndKnowledgeDao ekDao=new ExamationAndKnowledgeDao();
		
		
	//	ekerrorf.setKnowledge(ekerrorf.getKnowledge());
		
		ekerrorf.setKnowledge(kid);
		
		ekDao.update(ekerrorf);
		
		System.out.println("关联知识点更新成功");
		
		
		
	}
	public static String printKnText(ExamationAndKnowledge ekerror){
		
		System.out.println("知识点序号为"+ekerror.getKnowledge());
		
		String[] text=ekerror.getKnowledge().split("\\s+");
		
		System.out.println(text[0]+"  "+text[1]);
		
		String ks="";
		
	
		for(int i=0;i<text.length;i++){
					System.out.println("关联的知识点为： "+knowledge[Integer.parseInt(text[i])]);
					ks=knowledge[Integer.parseInt(text[i])]+" "+ks;
				
		}
		return ks;
		
	}
	public static int getKnowByText(String s){
		
		int a = 0;
		
		for (int i = 0; i < count; i++) {
			System.out.println(knowledge[i]);
			if (knowledge[i].equals("概率初步")) {
				a=i;
			}
			
		}
		
		
		
		return a;
		
	}
	

}
