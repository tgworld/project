package myce.casia.db;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
 

 
/**
 * @author LinDL
 * 
 */
public class KnowledgeDao {
 
    public void save(Knowledge knowledge) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(knowledge);
            transaction.commit();
        } catch (RuntimeException e) {
            // TODO: handle exception
            transaction.rollback();
            throw e;
        } finally {
 
        }
        session.close();
    }
 
    public void update(Knowledge knowledge) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(knowledge);
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
            Knowledge knowledge= (Knowledge) session.get(Knowledge.class, id);// 要先获取到实体对象
            session.delete(knowledge);// 删除的是实体对象
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
    public Knowledge  getById(int id) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Knowledge knowledge= (Knowledge) session.get(Knowledge.class, id);
            transaction.commit();
            return knowledge;
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
    public List<Knowledge> findAll() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            // 使用HQL查询
            // List<user> all = session.createQuery(from User).list();
            // 使用面向对象的方式查询
            Criteria criteria = session.createCriteria(Knowledge.class);
            // criteria.add(Restrictions.eq(id, 5));// 添加限制条件，查询id等于5的记录
            // criteria.add(Restrictions.ge(id, 6));//
            // 查询id大于等于6的记录，lt表小于,le表小于等于
            // criteria.addOrder(Order.asc(id));// 添加排序条件
 
            List<Knowledge> all = criteria.list();
 
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
