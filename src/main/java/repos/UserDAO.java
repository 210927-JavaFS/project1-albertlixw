package repos;

import models.User;

import java.util.List;

public interface UserDAO {
    List<User> findAllUsers();
    User findById(int id);
    User findByUsername(String username);
//    User getByUsername(String username);
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
}
