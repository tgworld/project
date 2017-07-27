package myce.casia.db;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtils {
 
    private static SessionFactory sessionFactory;
 
    static {
    
        sessionFactory = new Configuration()//
                .configure()//
                .buildSessionFactory();
    }
 
    /*
     * 获得一个全局唯一的SessionFactory
     * 
     * @return
     */
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    /*
     * 从全局SessionFactory中打开一个Session
     */
    public static Session openSession() {
        return sessionFactory.openSession();
    }
 
    public static void setSessionFactory(SessionFactory sessionFactory) {
        HibernateUtils.sessionFactory = sessionFactory;
    }
 
}