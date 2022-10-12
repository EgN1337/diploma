package ru.rsreu.CHHENEV0813.command.content.input;

import ru.rsreu.CHHENEV0813.models.CheckingTest;

public class AssessTestInput {
    private int userId;
    private int testId;
    private int assessment;

    public void setUserId(Object userId) {
        this.userId = Integer.parseInt(userId.toString());
    }

    public void setTestId(Object testId) {
        this.testId = Integer.parseInt(testId.toString());
    }

    public void setAssessment(Object assessment) {
        this.assessment = Integer.parseInt(assessment.toString());
    }

    public int getUserId() {
        return userId;
    }

    public int getTestId() {
        return testId;
    }

    public int getAssessment() {
        return assessment;
    }


}
