package myce.casia.db;

public class deleteExamationAndKnowledge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s="examationandknowledge";
		
		deleteAll(s);
		
	}

	public static void deleteById(int id){
		
		ExamationAndKnowledgeDao exdao= new ExamationAndKnowledgeDao();
		
			exdao.delete(id);
	
	}
	
	public static void deleteAll(String tablename){
		
		ExamationAndKnowledgeDao exdao= new ExamationAndKnowledgeDao();
		
		exdao.destroy(tablename);
		
	}

}
