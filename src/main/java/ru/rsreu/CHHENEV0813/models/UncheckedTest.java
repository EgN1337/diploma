package ru.rsreu.CHHENEV0813.models;

/**
 * UncheckedTest class contains data about tests, which teacher didn 't have time to check or the student
 * didn't have time to solve it. Contains test ID and its name.
 */
public class UncheckedTest {
    private int testId;
    private String testName;

    public UncheckedTest (){
    }

    public UncheckedTest(int testId, String testName) {
        this.testId = testId;
        this.testName = testName;
    }

    public int getTestId() {
        return testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
