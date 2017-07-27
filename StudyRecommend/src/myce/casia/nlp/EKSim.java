package myce.casia.nlp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import myce.casia.baidu.nlp.SimNet;
import myce.casia.db.ExamationAndKnowledge;
import myce.casia.db.ExamationAndKnowledgeDao;
import myce.casia.db.Knowledge;
import myce.casia.db.KnowledgeDao;

public class EKSim {

	
	public static FileWriter fw;
	public static BufferedWriter writer;
	
	static {
		try {
			fw=new FileWriter(new File("./data/知识点试题相似度.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer=new BufferedWriter(fw);
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String content="16公路上行驶的一辆汽车车牌为偶数的频率约是（　　A．50%  B．100%  C．由各车所在单位或个人定   D．无法确定";
		Eksim(content);
	}
	
	public static void Eksim(String content) throws IOException{
		
		KnowledgeDao kDao=new KnowledgeDao();
		
		List<Knowledge> knowledges=kDao.findAll();
		
		for(int i=0;i<knowledges.size();i++){
			
		double d=	SimNet.TextSim(content, knowledges.get(i).toString());
		System.out.println(knowledges.get(i).getName().toString()+" "+d);
		writer.write(knowledges.get(i).getName().toString()+" "+d+"\r\n");
		writer.flush();
		
		}
		
	}
	
	

}
