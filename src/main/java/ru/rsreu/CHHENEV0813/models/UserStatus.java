package ru.rsreu.CHHENEV0813.models;

import ru.rsreu.CHHENEV0813.models.enums.Role;

/**
 * UserStatus class contains data about user referring to which helps with getting lists of tests.
 */
public class UserStatus {

    /*private int userId;*/
    private UserName userName;
    private String login;
    private Role role;
    private int blocked;
    private String password;
    private int logged;

    public UserStatus(){}

    public UserStatus(/*int userId,*/  UserName userName, String login, Role role, int blocked, String password, int logged) {
        /*this.userId = userId;*/
        this.userName = userName;
        this.login = login;
        this.role = role;
        this.blocked = blocked;
        this.password = password;
        this.logged = logged;
    }

    /*public int getUserId() {
        return userId;
    }*/

    public UserName getUserName() {
        return userName;
    }

    public String getLogin() {
        return login;
    }

    public Role getRole() {
        return role;
    }

    public int getBlocked() {
        return blocked;
    }

    /*public void setUserId(int userId) {
        this.userId = userId;
    }*/

    public void setUserName(UserName userName) {
        this.userName = userName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLogged() {
        return logged;
    }

    public void setLogged(int logged) {
        this.logged = logged;
    }
}
