package repos;

import models.Reimbursement;
import models.Role;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class RoleDAOImpl implements RoleDAO{


    @Override
    public List<Role> findAllRoles() {
        Session session = HibernateUtil.getSession();

        List<Role> list = session.createQuery("FROM Role").list();

        HibernateUtil.closeSession();

        return list;
    }

    @Override
    public Role findByRoleId(int id) {
        Session session = HibernateUtil.getSession();

        Role role = session.get(Role.class, id);

        HibernateUtil.closeSession();

        return role;
    }

    @Override
    public boolean addRole(Role role) {
        try{

            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            HibernateUtil.closeSession();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateRole(Role role) {
        try{
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(role);
            transaction.commit();

            HibernateUtil.closeSession();
            return true;
        }   catch(HibernateException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRole(Role role) {
        try{

            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.delete(role);
            transaction.commit();
            HibernateUtil.closeSession();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
    }    
}
