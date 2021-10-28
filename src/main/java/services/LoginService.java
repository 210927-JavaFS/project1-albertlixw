package services;

import models.User;
import models.UserDTO;
import repos.UserDAO;
import repos.UserDAOImpl;

public class LoginService {
      private UserDAO userDAO = new UserDAOImpl();

      public boolean login(UserDTO userDTO){
          User user = userDAO.findByUsername(userDTO.username);
          if(user!=null && (userDTO.password.hashCode()==user.getPassword())){
             return true;
          }

          return false;
      }
    
}
