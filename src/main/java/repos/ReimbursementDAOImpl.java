package repos;

import models.*;
//import models.ReimbursementType;
//import models.Role;
//import models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO{

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
