package ru.rsreu.CHHENEV0813.models;


/**
 * InvaluableSolutions class contains all data about invaluable solutions of students.
 * It contains: student ID, students' first name, surname and third name, also test name, and it's ID.
 */
public class InvaluableSolutions {

    private int userId;
    private UserName name;
    private String testName;
    private int testId;

    public InvaluableSolutions(int userId, UserName name, String testName, int testId) {
        this.userId = userId;
        this.name = name;
        this.testName = testName;
        this.testId = testId;
    }

    public int getUserId() {
        return userId;
    }

    public UserName getName() {
        return name;
    }

    public String getTestName() {
        return testName;
    }

    public int getTestId() {
        return testId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(UserName name) {
        this.name = name;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }
}
