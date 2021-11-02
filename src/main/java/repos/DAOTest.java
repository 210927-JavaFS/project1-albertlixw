package repos;

import models.*;

public class DAOTest {

    public static UserDAO userDAO = new UserDAOImpl();
    public static ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();

    public static void main(String[] args) {
        ReimbursementStatus status1 = new ReimbursementStatus(1);
        ReimbursementStatus status2 = new ReimbursementStatus(2);
        ReimbursementStatus status3 = new ReimbursementStatus(3);

        ReimbursementStatusDAO reimbursementStatusDAO = new ReimbursementStatusDAOImpl();

        reimbursementStatusDAO.addReimbursementStatus(status1);
        reimbursementStatusDAO.addReimbursementStatus(status2);
        reimbursementStatusDAO.addReimbursementStatus(status3);

        ReimbursementType type1 = new ReimbursementType(1);
        ReimbursementType type2 = new ReimbursementType(2);
        ReimbursementType type3 = new ReimbursementType(3);
        ReimbursementType type4 = new ReimbursementType(4);

        Role role1 = new Role(1);
        Role role2 = new Role (2);

        RoleDAO roleDAO = new RoleDAOImpl();
        roleDAO.addRole(role1);
        roleDAO.addRole(role2);

        User asd = new User("asd", "123".hashCode(), "asd@123.com", "asd", "asd", role2);
//        User steve = new User("alex", "123".hashCode(), "alex@employee.com", "alex", "alex", role1);
//        User manager = new User("Manager2", "123".hashCode(), "1234@manager.com", "Manager", "Manager", role2);

//        UserDAO userDAO = new UserDAOImpl();
        userDAO.addUser(asd);
//        userDAO.addUser(manager);
//        userDAO.addUser(steve);

        User user = userDAO.findById(1);

//        ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
        reimbursementDAO.addReimbursement(new Reimbursement(100, "Lodging", asd, type1));
//        reimbursementDAO.addReimbursement(new Reimbursement(200, "Travel", manager, type2));
//        reimbursementDAO.addReimbursement(new Reimbursement(300, "Food", steve, type3));
//        reimbursementDAO.addReimbursement(new Reimbursement(400, "Other", steve, type4));


        reimbursementDAO.addReimbursement(new Reimbursement());
    }

}
