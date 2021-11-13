package services;

import models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repos.*;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementServiceTest {

    ReimbursementService reimbService = new ReimbursementService();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        
    }

    @BeforeAll
    static void beforeAll() {
        ReimbursementStatus status1 = new ReimbursementStatus(1);
        ReimbursementStatus status2 = new ReimbursementStatus(2);
        ReimbursementStatus status3 = new ReimbursementStatus(3);

        ReimbursementStatusDAO reimbursementStatusDAO = new ReimbursementStatusDAOImpl();

        reimbursementStatusDAO.addReimbursementStatus(status1);
        reimbursementStatusDAO.addReimbursementStatus(status2);
        reimbursementStatusDAO.addReimbursementStatus(status3);

        ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();

        ReimbursementType type1 = new ReimbursementType(1);
        ReimbursementType type2 = new ReimbursementType(2);
        ReimbursementType type3 = new ReimbursementType(3);
        ReimbursementType type4 = new ReimbursementType(4);
        ReimbursementTypeDAO reimbursementTypeDAO = new ReimbursementTypeDAOImpl();

        reimbursementTypeDAO.addReimbursementType(type1);
        reimbursementTypeDAO.addReimbursementType(type2);
        reimbursementTypeDAO.addReimbursementType(type3);
        reimbursementTypeDAO.addReimbursementType(type4);

        Role role1 = new Role(1);
        Role role2 = new Role (2);

        RoleDAO roleDAO = new RoleDAOImpl();
        roleDAO.addRole(role1);
        roleDAO.addRole(role2);

        User asd = new User("asd", "123".hashCode(), "asd@123.com", "asd", "asd", role2);
        User steve = new User("alex", "123".hashCode(), "alex@employee.com", "alex", "alex", role1);
        User manager = new User("Manager2", "123".hashCode(), "1234@manager.com", "Manager", "Manager", role2);

        UserDAO userDAO = new UserDAOImpl();
        userDAO.addUser(asd);
        userDAO.addUser(manager);
        userDAO.addUser(steve);

        reimbursementDAO.addReimbursement(new Reimbursement(100, "Lodging", asd, type1));
        reimbursementDAO.addReimbursement(new Reimbursement(200, "Travel", manager, type2));
        reimbursementDAO.addReimbursement(new Reimbursement(300, "Food", steve, type3));
        reimbursementDAO.addReimbursement(new Reimbursement(400, "Other", steve, type4));
    }

    @Test
    void findAllReimbursement() {
        assertEquals(4, reimbService.findAllReimbursement().size());
    }

    @Test
    void findByReimbStatus() {
        assertEquals(4, reimbService.findByReimbStatus(1).size());
    }

    @Test
    void findByReimbAuthorUsername() {
        assertEquals(1, reimbService.findByReimbAuthorUsername("Manager2").size());
    }

    @Test
    void findByReimbId() {
        assertEquals(reimbService.findByReimbId(1).getAmount(), 100);
    }

    @Test
    void updateReimbursement() {
        Reimbursement reimb = reimbService.findByReimbId(1);
        reimb.setAmount(99999);
        reimbService.updateReimbursement(reimb);
        assertEquals(reimbService.findByReimbId(1).getAmount(), 99999);
    }

    @Test
    void resolveTicket() {
        Reimbursement reimb = reimbService.findByReimbId(1);
        reimb.setResolver(reimb.getAuthor());
        reimb.setStatus(new ReimbursementStatus(2));
        reimbService.resolveTicket(reimb);
        reimb = reimbService.findByReimbId(1);
        assertTrue((reimb.getResolved()!=null)&&(reimb.getStatus().getStatusId()==2));
    }

    @Test
    void addReimbursement() {
        Reimbursement reimb = reimbService.findByReimbId(1);
        reimbService.addReimbursement(new Reimbursement(1000, "Lodging", reimb.getAuthor(), reimb.getType()));
        assertNotNull(reimbService.findByReimbId(5));
    }

    @Test
    void deleteReimbursement() {
        reimbService.deleteReimbursement(4);
        assertNull(reimbService.findByReimbId(4));
    }
}