package ru.rsreu.CHHENEV0813.command.content.input;

public class RedirectInput {
    private boolean isHasRole;
    private int userId;
    private int role;

    public boolean isHasRole() {
        return isHasRole;
    }

    public void setHasRole(boolean hasRole) {
        isHasRole = hasRole;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
