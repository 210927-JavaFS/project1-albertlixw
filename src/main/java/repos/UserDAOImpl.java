package repos;

import models.Role;
import models.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO{
    
//    public static void main(String[] args) {
//        UserDAO userDAO = new UserDAOImpl();
//
//        userDAO.addUser(new User("asd", "123".hashCode(), "123@123.com", "Steve", "Rogers", new Role(1, "employee")));
//    }

    //TODO: Delete this
    public User getByUsername(String username) {
        if(username.equals("asd")) {
            return new User("asd", "123".hashCode(), "123@123.com", "Steve", "Rogers", new Role(1, "employee"));

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
//        Criteria criteria = session.createCriteria(username.getClass());
//        Query query = session.createQuery("SELECT * from users u WHERE u.username = ?");
//        query.setParameter(1, username) ;
        //difference with line 44: am I referencing the User obj or users table
        TypedQuery<User> typed = session.createQuery("FROM User WHERE username = ?"); // getResultList() method is in here.
        typed.setParameter(1, username);
        return typed.getResultList().get(0);
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
