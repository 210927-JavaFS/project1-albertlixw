package services;

import models.User;
import models.UserDTO;
import repos.UserDAO;
import repos.UserDAOImpl;

import java.util.List;

public class UserService {
    
    private UserDAO userDao = new UserDAOImpl();

//    public User getByUserName(String username){
//        return userDao.getByUsername(username);
//    }

    public boolean login(UserDTO userDTO){
        User user = findByUsername(userDTO.username);

        if(user!=null && (userDTO.password.hashCode()==user.getPassword())){
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
     return userDao.addUser(user);
 }

    public boolean updateUser(User user){
    return userDao.updateUser(user);
 }

    public boolean deleteUser(int userId){
        return userDao.deleteUser(findById(userId));
     }




}
