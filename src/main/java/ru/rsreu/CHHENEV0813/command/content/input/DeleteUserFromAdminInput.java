package ru.rsreu.CHHENEV0813.command.content.input;

public class DeleteUserFromAdminInput {
    private int userId;
    private String login;

    public void setUserIdFromRequest(Object userId) {
        this.userId = Integer.parseInt(userId.toString());
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
