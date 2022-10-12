package ru.rsreu.CHHENEV0813.command.content.output;

import ru.rsreu.CHHENEV0813.models.UserStatus;

import java.util.List;

public class LoadUserListForAdminContent {
    private List<UserStatus> allUsersInsteadAdmin;

    public List<UserStatus> getAllUsersInsteadAdmin() {
        return allUsersInsteadAdmin;
    }

    public void setAllUsersInsteadAdmin(List<UserStatus> allUsersInsteadAdmin) {
        this.allUsersInsteadAdmin = allUsersInsteadAdmin;
    }
}
