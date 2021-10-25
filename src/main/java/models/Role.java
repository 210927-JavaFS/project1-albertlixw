package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Role {
    @Id
    private int roleId;
    private String role;  //varchar2(10)

    public Role(int roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }
    public Role() {
        super();
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return roleId == role1.roleId && role.equals(role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, role);
    }



    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
