package repos;

import models.ReimbursementStatus;
import models.ReimbursementType;
import models.Role;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class ReimbursementStatusDAOImpl implements ReimbursementStatusDAO{


    @Override
    public List<ReimbursementStatus> findAllReimbursementStatus() {
        Session session = HibernateUtil.getSession();
        List<ReimbursementStatus> list = session.createQuery("FROM ReimbursementStatus").list();
        HibernateUtil.closeSession();

        return list;
    }

    @Override
    public ReimbursementStatus findById(int id) {
        Session session = HibernateUtil.getSession();
        ReimbursementStatus status = session.get(ReimbursementStatus.class, id);
        HibernateUtil.closeSession();


        return status;
    }

    @Override
    public boolean addReimbursementStatus(ReimbursementStatus reimbursementStatus) {
        try{

            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(reimbursementStatus);
            transaction.commit();
            HibernateUtil.closeSession();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }    }

    @Override
    public boolean updateReimbursementStatus(ReimbursementStatus reimbursementStatus) {
        try{
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(reimbursementStatus);
            transaction.commit();

            HibernateUtil.closeSession();
            return true;
        }   catch(HibernateException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteReimbursementStatus(ReimbursementStatus reimbursementStatus) {
        try{

            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.delete(reimbursementStatus);
            transaction.commit();
            HibernateUtil.closeSession();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
    }
}
