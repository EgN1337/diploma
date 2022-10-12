package ru.rsreu.CHHENEV0813.command.content.output;

import ru.rsreu.CHHENEV0813.models.UserStatus;

import java.util.List;

public class LoadUserListFromModerOutput {
    private List<UserStatus> userStatusList;

    public List<UserStatus> getUserStatusList() {
        return userStatusList;
    }

    public void setUserStatusList(List<UserStatus> userStatusList) {
        this.userStatusList = userStatusList;
    }
}
