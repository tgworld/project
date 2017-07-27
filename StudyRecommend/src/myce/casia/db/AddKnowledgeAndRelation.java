package myce.casia.db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddKnowledgeAndRelation {

	private static StringBuffer content;
	private static BufferedReader br;
	
	public static void main(String[] args){
		
      AddKnowledgeRelation("二元一次方程（组）及解的概念","运用一元二次方程分析和解决实际问题", "应用");
		
	}
	
	
	public static void AddKnowledgeRelation(String k1,String k2,String r)
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
					//创建对象，保存到数据库
		
				//保存User对象
			KnowledgeRelation kr=new KnowledgeRelation();
			
			
			kr.setKnowledgepairs(k1+" "+k2);
			kr.setRelation(r);
			session.save(kr);
				//提交事务
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