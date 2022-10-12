package ru.rsreu.CHHENEV0813.command.content.input;

public class EditUserStatusInput {
    private String userId;
    private int currentStatus;

    public String getUserId() {
        return userId;
    }

    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setUserIdFromRequest(String userId) {
        this.userId = userId;
    }

    public void setUserStatusFromRequest(Object currentStatus) {
        this.currentStatus = Integer.parseInt(currentStatus.toString());
    }
}
