package services;

import models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repos.*;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private static final UserService userService = new UserService();
    @BeforeEach
    void setUp() {

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
//
//        reimbursementDAO.addReimbursement(new Reimbursement(100, "Lodging", asd, type1));
//        reimbursementDAO.addReimbursement(new Reimbursement(200, "Travel", manager, type2));
//        reimbursementDAO.addReimbursement(new Reimbursement(300, "Food", steve, type3));
//        reimbursementDAO.addReimbursement(new Reimbursement(400, "Other", steve, type4));

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void login() {
        assertTrue(userService.login(new UserDTO("asd", "123")));
    }

    @Test
    void findByUsername() {
        assertEquals(userService.findByUsername("asd").getUsername(), "asd");
    }

    @Test
    void findById() {
        assertEquals(userService.findById(1).getUsername(), "asd");
    }

    @Test
    void findAllUsers() {
        assertEquals(userService.findAllUsers().size(), 3);
    }

    @Test
    void addUser() {
        User steve = new User("steve", "123".hashCode(), "steve@123.com", "Steve", "Rogers", new Role(2));
        userService.addUser(steve);
        assertEquals(steve, userService.findByUsername("steve"));
    }


    @Test
    void updateUser() {
        User steve = userService.findByUsername("Manager2");
        steve.setRole(new Role(1));
        userService.updateUser(steve);
        assertEquals(userService.findByUsername("Manager2").getRole().getRoleId(), 1);
    }


    @Test
    void deleteUser() {
        int id = userService.findByUsername("Manager2").getUserId();
        userService.deleteUser(id);
        assertNull(userService.findById(id));
    }
}