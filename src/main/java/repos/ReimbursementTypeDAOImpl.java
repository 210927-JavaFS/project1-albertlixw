package repos;

import models.ReimbursementType;
import models.Role;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class ReimbursementTypeDAOImpl implements ReimbursementTypeDAO{
    @Override
    public List<ReimbursementType> findAllReimbursementType() {
        Session session = HibernateUtil.getSession();

        return session.createQuery("FROM ReimbursementType").list();
    }

    @Override
    public ReimbursementType findById(int id) {
        Session session = HibernateUtil.getSession();

        return session.get(ReimbursementType.class, id);
    }

    @Override
    public boolean addReimbursementType(ReimbursementType reimbursementType) {
        try{

            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(reimbursementType);
            transaction.commit();
            HibernateUtil.closeSession();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateReimbursementType(ReimbursementType reimbursementType) {
        try{
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(reimbursementType);
            transaction.commit();

            HibernateUtil.closeSession();
            return true;
        }   catch(HibernateException e){
            e.printStackTrace();
            return false;
        }    }

    @Override
    public boolean deleteReimbursementType(ReimbursementType reimbursementType) {
        try{

            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.delete(reimbursementType);
            transaction.commit();
            HibernateUtil.closeSession();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
    }
}
