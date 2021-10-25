package repos;

import models.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> findAllRoles();
    Role findByRoleId(int id);
    boolean addRole(Role role);
    boolean updateRole(Role role);
    boolean deleteRole(Role role);

}
