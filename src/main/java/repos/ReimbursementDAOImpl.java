package repos;

import models.Reimbursement;
import models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO{

    @Override
    public List<Reimbursement> findAllReimbursement() {
        Session session = HibernateUtil.getSession();

        return session.createQuery("FROM Reimbursement").list();
    }

    @Override
    public Reimbursement findByReimbId(int reimbId) {
        Session session = HibernateUtil.getSession();

        return session.get(Reimbursement.class, reimbId);    }

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
