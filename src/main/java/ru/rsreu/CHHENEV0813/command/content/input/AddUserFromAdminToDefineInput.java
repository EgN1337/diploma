package ru.rsreu.CHHENEV0813.command.content.input;

import ru.rsreu.CHHENEV0813.models.UserName;
import ru.rsreu.CHHENEV0813.models.enums.Role;

public class AddUserFromAdminToDefineInput {
    private String surName;
    private String firstName;
    private String thirdName;
    private UserName user;
    private int userRole;
    private String login;
    private String password;

    public UserName getUser() {
        return user;
    }

    public void setUser(UserName user) {
        this.user = user;
    }
    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public void setUserRoleFromRequest(Object userRole) {
        this.userRole = Integer.parseInt(userRole.toString());
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
