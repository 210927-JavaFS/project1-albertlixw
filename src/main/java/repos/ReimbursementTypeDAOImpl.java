package repos;

import models.ReimbursementStatus;
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
        List<ReimbursementType> list = session.createQuery("FROM ReimbursementType").list();
        HibernateUtil.closeSession();
        return list;
    }

    @Override
    public ReimbursementType findById(int id) {
        Session session = HibernateUtil.getSession();
        ReimbursementType type = session.get(ReimbursementType.class, id);
        HibernateUtil.closeSession();

        return type;
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
