package ru.rsreu.CHHENEV0813.command.content.input;

import ru.rsreu.CHHENEV0813.models.PassedTest;

import java.util.List;

public class LoadStudentPassedTestsInput {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = Integer.parseInt(userId.toString());
    }
}
