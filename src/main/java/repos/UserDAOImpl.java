package repos;

import models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class UserDAOImpl implements UserDAO{
    
    @Override
    public List<User> findAllUsers() {
        Session session = HibernateUtil.getSession();

        List<User>list = session.createQuery("FROM User").list();
        HibernateUtil.closeSession();

        return list;
    }

    @Override
    public User findById(int id) {
        Session session = HibernateUtil.getSession();
        User user = session.get(User.class, id);
        HibernateUtil.closeSession();

        return user;
    }

    @Override
    public User findByUsername(String username) {
        try{

            Session session = HibernateUtil.getSession();

    
            List<User> list = session.createQuery("FROM User WHERE username = '" + username + "'").list();

            HibernateUtil.closeSession();

            return list.isEmpty()?null:list.get(0);
        }
        catch(HibernateException e){
            e.printStackTrace();
        }
        return null;
    }


//    //Fake login
//    public User getByUsername(String username) {
//        if(username.equals("asd")) {
//            return new User("asd", "123".hashCode(), "123@123.com", "Steve", "Rogers", new Role(1, "employee"));
//
//        }
//        return null;
//    }

    
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
