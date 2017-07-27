package myce.casia.db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FindKnowledgeAndRelation {

	
	public static void main(String[] args){
		
      FindKnowledgeRelation();
    String relation=FindRelation("运用一元二次方程分析和解决实际问题","二元一次方程（组）及解的概念");
		System.out.println(relation);
		FindKR();
		System.out.println(FindR("运用一元二次方程分析和解决实际问题","二元一次方程（组）及解的概念"));
		
	}
	

	public static String  FindRelation(String k1,String k2)
	{
		String relation = null;
		//默认读取hibernate.cfg.xml文件
				Configuration cfg=new Configuration().configure();
				//建立SessionFactory,镜像
				SessionFactory factory=cfg.buildSessionFactory();
				
				//取得session
				Session session=null;
				try {
					session=factory.openSession();
					//开启事务
					session.beginTransaction();
		Query query=session.createQuery("from KnowledgeRelation");
		List<KnowledgeRelation> list=query.list();
	
		for(int i=0;i<list.size();i++){
			//System.out.println(list.get(i).getId()+" "+list.get(i).getKnowledgepairs()+" "+list.get(i).getRelation());
		
			if(list.get(i).getKnowledgepairs().contains(k1)&&list.get(i).getKnowledgepairs().contains(k2)){
				relation=list.get(i).getRelation();
				//System.out.println(list.get(i).getRelation());
			}
			
		
		}
		
		
				session.getTransaction().commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				//回滚
				session.getTransaction().rollback();
				
			}finally{
				if(session!=null)
					if (session.isOpen()) {
						//关闭session
						session.close();
					}
			}
				return relation;
	
	
	
	}
	public static void FindKR(){
		Session session=HibernateUtils.openSession();
		session.beginTransaction();
		KnowledgeAndRelationDao krdao=new KnowledgeAndRelationDao();
		List<KnowledgeRelation> krlist=krdao.findAll();
		for(KnowledgeRelation kr:krlist){
			System.out.println(kr.getKnowledgepairs()+" "+kr.getRelation());
		}
		
	}
	public static String FindR(String k1,String k2){
		Session session=HibernateUtils.openSession();
		session.beginTransaction();
		KnowledgeAndRelationDao krdao=new KnowledgeAndRelationDao();
		List<KnowledgeRelation> krlist=krdao.findAll();
		String r=null;
		for(KnowledgeRelation kr:krlist){
			if(kr.getKnowledgepairs().contains(k1)&&kr.getKnowledgepairs().contains(k2)){
				r=kr.getRelation();
				//System.out.println(kr.getKnowledgepairs()+" "+kr.getRelation());
			}
			
		}
		return r;
		
	}
	
	public static void FindKnowledgeRelation()
	{
		
		//默认读取hibernate.cfg.xml文件
				Configuration cfg=new Configuration().configure();
				//建立SessionFactory,镜像
				SessionFactory factory=cfg.buildSessionFactory();
				
				//取得session
				Session session=null;
				try {
					session=factory.openSession();
					//开启事务
					session.beginTransaction();
		Query query=session.createQuery("from KnowledgeRelation");
		List<KnowledgeRelation> list=query.list();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getId()+" "+list.get(i).getKnowledgepairs()+" "+list.get(i).getRelation());
		}
				session.getTransaction().commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				//回滚
				session.getTransaction().rollback();
				
			}finally{
				if(session!=null)
					if (session.isOpen()) {
						//关闭session
						session.close();
					}
			}
	
	
	
	}
}