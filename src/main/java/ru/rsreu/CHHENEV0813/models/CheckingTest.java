package ru.rsreu.CHHENEV0813.models;

/**
 * CheckingTest class contains all data about solution of the test at the point of teacher's view: student's ID, test's ID
 * and solution. IDs contain in them numbers of student and test appr. and solution gives full definition of student
 * solution on define teacher test.
 */
public class CheckingTest {

    private int userId;
    private int testId;
    private String solution;

    public CheckingTest(int userId, int testId, String solution) {
        this.userId = userId;
        this.testId = testId;
        this.solution = solution;
    }

    public int getUserId() {
        return userId;
    }

    public int getTestId() {
        return testId;
    }

    public String getSolution() {
        return solution;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

}
