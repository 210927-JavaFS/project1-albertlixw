package repos;

import models.Reimbursement;
import models.ReimbursementType;
import models.Role;
import models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO{

//    public static void main(String[] args) {
//        ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
//        ReimbursementType type1 = new ReimbursementType(1, "Lodging");
//        ReimbursementType type2 = new ReimbursementType(2, "Travel");
//        ReimbursementType type3 = new ReimbursementType(3, "Food");
//        ReimbursementType type4 = new ReimbursementType(4, "Other");
//        Role role1 = new Role (1, "Employee");
//        Role role2 = new Role (2, "Finance Manager");
//        User steve = new User("Alex", "123".hashCode(), "Alex@employee.com", "Alex", "Alex", role1);
//        User manager = new User("Manager2", "123".hashCode(), "1234@manager.com", "Manager", "Manager", role2);
//
//        reimbursementDAO.addReimbursement(new Reimbursement(400, "deniend", steve, type4));
//        reimbursementDAO.addReimbursement(new Reimbursement(300, "approved", steve, type3));
//        reimbursementDAO.addReimbursement(new Reimbursement(200, "pending", manager, type2));
//        reimbursementDAO.addReimbursement(new Reimbursement(100, "submitted", manager, type1));
//    }

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
