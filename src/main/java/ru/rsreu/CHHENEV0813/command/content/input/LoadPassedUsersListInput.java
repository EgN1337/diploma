package ru.rsreu.CHHENEV0813.command.content.input;

public class LoadPassedUsersListInput {
    private int testTeacher;

    public int getTestTeacher() {
        return testTeacher;
    }

    public void setTestTeacher(Object testTeacher) {
        this.testTeacher = Integer.parseInt(testTeacher.toString());
    }
}
