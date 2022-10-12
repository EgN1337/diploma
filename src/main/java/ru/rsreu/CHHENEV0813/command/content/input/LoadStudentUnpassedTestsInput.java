package ru.rsreu.CHHENEV0813.command.content.input;

public class LoadStudentUnpassedTestsInput {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = Integer.parseInt(userId.toString());
    }
}
