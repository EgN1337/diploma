package ru.rsreu.CHHENEV0813.models;

import ru.rsreu.CHHENEV0813.models.enums.Role;

/**
 * UserInfo class contains data about user: his ID, first name, surname, third name, role, login, password. Is used for
 * login procedure.
 */
public class UserInfo {
    private String userId;
    private String userName;
    private String login;
    private Role role;
    private String password;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getLogin() {
        return login;
    }

    public Role getRole() {
        return role;
    }

    public String getBlocked() {
        return password;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setBlocked(String password) {
        this.password = password;
    }
}
