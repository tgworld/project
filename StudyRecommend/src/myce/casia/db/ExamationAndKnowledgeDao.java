package myce.casia.db;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import arq.query;
 

 
/**
 * @author LinDL
 * 
 */
public class ExamationAndKnowledgeDao {
 
    public void save(ExamationAndKnowledge examationknowledge) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(examationknowledge);
            transaction.commit();
        } catch (RuntimeException e) {
            // TODO: handle exception
            transaction.rollback();
            throw e;
        } finally {
 
        }
        session.close();
    }
 
    public void update(ExamationAndKnowledge examationknowledge) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(examationknowledge);
            transaction.commit();
        } catch (RuntimeException e) {
            // TODO: handle exception
            transaction.rollback();
            throw e;
        } finally {
 
        }
        session.close();
    }
 
    public void delete(int id) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            ExamationAndKnowledge examationknowledge = (ExamationAndKnowledge) session.get(ExamationAndKnowledge.class, id);// 要先获取到实体对象
            session.delete(examationknowledge);// 删除的是实体对象
            transaction.commit();
        } catch (RuntimeException e) {
            // TODO: handle exception
            transaction.rollback();
            throw e;
        } finally {
 
        }
        session.close();
    }
    public void destroy(String tablename) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
           // ExamationAndKnowledge examationknowledge = (ExamationAndKnowledge) session.get(ExamationAndKnowledge.class, id);// 要先获取到实体对象
           String sql="truncate table "+tablename;
           session.createSQLQuery(sql).executeUpdate();
          
           // session.delete(examationknowledge);// 删除的是实体对象
            transaction.commit();
        } catch (RuntimeException e) {
            // TODO: handle exception
            transaction.rollback();
            throw e;
        } finally {
 
        }
        session.close();
    }
 
    /**
     * @param id
     * @return
     */
    public ExamationAndKnowledge getById(int id) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            ExamationAndKnowledge examationknowledge = (ExamationAndKnowledge) session.get(ExamationAndKnowledge.class, id);
            transaction.commit();
            return examationknowledge;
        } catch (RuntimeException e) {
            // TODO: handle exception
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    
    
    public ExamationAndKnowledge getByContent(String content) {
    	System.out.println(content);
        Session session = HibernateUtils.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
         //   ExamationAndKnowledge examationknowledge = (ExamationAndKnowledge) session.get(ExamationAndKnowledge.class, id);
           String sql="SELECT * FROM examationandknowledge WHERE examation = '"+content+"'";
           Query query=  session.createSQLQuery(sql).addEntity(ExamationAndKnowledge.class);
          
           ExamationAndKnowledge examationknowledge=(ExamationAndKnowledge) query.list().get(0);
          //System.out.println(examationknowledge.getExamation());
           transaction.commit();
            return examationknowledge;
        } catch (RuntimeException e) {
            // TODO: handle exception
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
   
 
    /**
     * @return
     */
    public List<ExamationAndKnowledge> findAll() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            // 使用HQL查询
            // List<user> all = session.createQuery(from User).list();
            // 使用面向对象的方式查询
            Criteria criteria = session.createCriteria(ExamationAndKnowledge.class);
            // criteria.add(Restrictions.eq(id, 5));// 添加限制条件，查询id等于5的记录
            // criteria.add(Restrictions.ge(id, 6));//
            // 查询id大于等于6的记录，lt表小于,le表小于等于
            // criteria.addOrder(Order.asc(id));// 添加排序条件
 
            List<ExamationAndKnowledge> all = criteria.list();
 
            transaction.commit();
            return all;
        } catch (RuntimeException e) {
            // TODO: handle exception
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
 
  
   
}
