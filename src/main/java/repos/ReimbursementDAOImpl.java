package repos;

import controllers.ReimbursementController;
import models.*;
//import models.ReimbursementType;
//import models.Role;
//import models.User;
import org.hibernate.HibernateException;
import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateUtil;

import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO{
    private static UserDAO userDAO = new UserDAOImpl();
    private static final Logger log = LoggerFactory.getLogger(ReimbursementDAOImpl.class);
    @Override
    public List<Reimbursement> findByReimbStatus(int statusId) {
        try{

            Session session = HibernateUtil.getSession();


            List<Reimbursement> list = session.createQuery("FROM Reimbursement WHERE statusId = '" + statusId + "'").list();

            HibernateUtil.closeSession();

            return list.isEmpty()?null:list;
        }
        catch(HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reimbursement> findByReimbAuthorUsername(String username) {
        try{

            User user = userDAO.findByUsername(username);
            System.out.println(user);

//            findByUsername closed session, so have to reopen/open after the other DAO method call. 
            Session session = HibernateUtil.getSession();

            List<Reimbursement> list = session.createQuery("FROM Reimbursement WHERE author_userid = " + user.getUserId()).list();

            HibernateUtil.closeSession();

            return list.isEmpty()?null:list;
        }
        catch(HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reimbursement> findAllReimbursement() {
        Session session = HibernateUtil.getSession();
        List<Reimbursement> list = session.createQuery("FROM Reimbursement").list();
        HibernateUtil.closeSession();

        return list;
    }

    @Override
    public Reimbursement findByReimbId(int reimbId) {
        Session session = HibernateUtil.getSession();
        Reimbursement reimb = session.get(Reimbursement.class, reimbId);
        HibernateUtil.closeSession();

        return reimb;
    }

    @Override
    public Reimbursement findByAuthorId(int userid) {
        return null;
    }

    @Override
    public Reimbursement findByResolverId(int userid) {
        return null;
    }

    @Override
    public Reimbursement findByType(int typeid) {
        return null;
    }

    @Override
    public Reimbursement findByStatus(int statusid) {
        return null;
    }

    @Override
    public boolean addReimbursement(Reimbursement reimbursement) {
        try{
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(reimbursement);
            transaction.commit();
            HibernateUtil.closeSession();
            return true;
        }catch(PropertyValueException e){
            e.printStackTrace();
            log.error("User tried to insert null reimbursement. " + e.getMessage());
            return false;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateReimbursement(Reimbursement reimbursement) {
        try{
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(reimbursement);
            transaction.commit();

            HibernateUtil.closeSession();
            return true;
        }   catch(HibernateException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteReimbursement(Reimbursement reimbursement) {
        try{

            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.delete(reimbursement);
            transaction.commit();
            HibernateUtil.closeSession();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
    }
}
