package ru.rsreu.CHHENEV0813.command.content.input;

import ru.rsreu.CHHENEV0813.models.CheckingTest;

public class AddSolutionToTestInput {
    private CheckingTest checkingTest;
    private int studentId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(Object studentId) {
        this.studentId = Integer.parseInt(studentId.toString());
    }

    public CheckingTest getCheckingTest() {
        return checkingTest;
    }

    public void setCheckingTest(CheckingTest checkingTest) {
        this.checkingTest = checkingTest;
    }
}
