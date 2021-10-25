package repos;

import models.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO{

    //TODO: Delete this
    public User getByUsername(String username) {
        if(username.equals("asd")) {
            return new User("asd", "123".hashCode(), "123@123.com", "Steve", "Rogers", 1);

        }
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        Session session = HibernateUtil.getSession();

        return session.createQuery("FROM User").list();
    }

    @Override
    public User findById(int id) {
        Session session = HibernateUtil.getSession();

        return session.get(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        Session session = HibernateUtil.getSession();
        //TODO: verify usefulness. 
//        Criteria criteria = session.createCriteria(username.getClass());
//        Query query = session.createQuery("SELECT * from users u WHERE u.username = ?");
//        query.setParameter(1, username) ;
        return session.get(User.class, username);
//        (User.class, username);
    }

    @Override
    public boolean addUser(User user) {
        try{

            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            HibernateUtil.closeSession();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try{
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            
            HibernateUtil.closeSession();
            return true;
        }   catch(HibernateException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(User user) {
        try{
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            HibernateUtil.closeSession();
            return true;
        }   catch(HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
}
