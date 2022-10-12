package ru.rsreu.CHHENEV0813.command.content.input;

public class CheckTestInput {
    private int testId;
    private int studentId;


    public int getTestId() {
        return testId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setTestId(Object testId) {
        this.testId = Integer.parseInt(testId.toString());
    }

    public void setStudentId(Object studentId) {
        this.studentId = Integer.parseInt(studentId.toString());
    }
}
