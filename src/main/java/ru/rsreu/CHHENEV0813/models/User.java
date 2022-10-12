package ru.rsreu.CHHENEV0813.models;

import ru.rsreu.CHHENEV0813.models.enums.Role;

/**
 * User class contains data about user: his ID, first name, surname, third name, role, blocked status, password and log
 * status. These definitions contain in session.
 */
public class User {
    private int userId;
    private UserName userName;
    private Role role;
    private int blocked;
    private String password;
    private int logged;

    public User(){

    }

    public User(int userId, UserName userName, Role role, int blocked, String password, int logged) {
        this.userId = userId;
        this.userName = userName;
        this.role = role;
        this.blocked = blocked;
        this.password = password;
        this.logged = logged;
    }

    public int getUserId() {
        return userId;
    }

    public UserName getUserName() {
        return userName;
    }

    public Role getRole() {
        return role;
    }

    public int getBlocked() {
        return blocked;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(UserName userName) {
        this.userName = userName;
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
