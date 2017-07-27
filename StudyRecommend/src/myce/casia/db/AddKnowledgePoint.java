package myce.casia.db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddKnowledgePoint {

	private static StringBuffer content;
	private static BufferedReader br;
	
	
	
	
	
	static {
		try {
			 br = new BufferedReader(new InputStreamReader(
					new FileInputStream("./data/初中数学知识点.txt"), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
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
			String s =br.readLine();
			while(s!=null){
				if(s.trim()!=null&&!s.contains("知识点名称")){
					
					
					System.out.println(s);
					Knowledge knowledge=new Knowledge();
					knowledge.setName(s);
					session.save(knowledge);
					
				}
				s=br.readLine();
				
			}
			
			
			
			//保存User对象
		
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
