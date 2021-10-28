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
//    public static void main(String[] args) {
//        ReimbursementStatus status1 = new ReimbursementStatus(1, "Pending");
//        ReimbursementStatus status2 = new ReimbursementStatus(2, "Approved");
//        ReimbursementStatus status3 = new ReimbursementStatus(3, "Denied");
//
//        ReimbursementStatusDAO reimbursementStatusDAO = new ReimbursementStatusDAOImpl();
//        reimbursementStatusDAO.addReimbursementStatus(status1);
//        reimbursementStatusDAO.addReimbursementStatus(status2);
//        reimbursementStatusDAO.addReimbursementStatus(status3);
//    }

    @Override
    public List<ReimbursementStatus> findAllReimbursementStatus() {
        Session session = HibernateUtil.getSession();

        return session.createQuery("FROM ReimbursementStatus").list();
    }

    @Override
    public ReimbursementStatus findById(int id) {
        Session session = HibernateUtil.getSession();

        return session.get(ReimbursementStatus.class, id);    }

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
