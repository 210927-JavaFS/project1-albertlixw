package services;

import controllers.ReimbursementController;
import models.User;
import models.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repos.UserDAO;
import repos.UserDAOImpl;

import java.util.List;

public class UserService {
    
    private UserDAO userDao = new UserDAOImpl();
    private static final Logger log = LoggerFactory.getLogger(ReimbursementController.class);

//    public User getByUserName(String username){
//        return userDao.getByUsername(username);
//    }

    public boolean login(UserDTO userDTO){
        User user = findByUsername(userDTO.username);

        if(user!=null && (userDTO.password.hashCode()==user.getPassword())){
            log.info("User logged in. Username: "+ userDTO.username);
            return true;
        }

        return false;
    }

    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }

    public User findById(int id){
        return userDao.findById(id);
    }

    public List<User> findAllUsers(){
     return userDao.findAllUsers();
 }


    public boolean addUser(User user){
        log.info("new user has been added. "+ user);
        return userDao.addUser(user);
 }

    public boolean updateUser(User user){
        log.info("user info has been updated: "+ user);
        return userDao.updateUser(user);
 }

    public boolean deleteUser(int userId){

        log.info("user deleted. UserId: " + userId);
        return userDao.deleteUser(findById(userId));
     }




}
