package models;

public class UserDTO {
    public String username, password;

    public UserDTO() {
        super();
    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
