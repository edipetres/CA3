package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import security.IUser;

@Entity
@Table(name = "User")
public class User implements IUser {

    @Id
    @Column(name = "username")
    private String userName;
    
    @Column(name = "password")
    private String password;  //Pleeeeease dont store me in plain text

    @ElementCollection
    @CollectionTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id")
    )
    @Column (name = "roles")
    List<String> roles = new ArrayList();

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    
    public User() {
        
    }

    public User(String userName, String password, List<String> roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public void addRole(String role) {
        roles.add(role);
    }

    @Override
    public List<String> getRolesAsStrings() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
