package ru.rsreu.CHHENEV0813.command.content.input;

public class LoadTestTaskInput {
    private int testId;

    public int getTestId() {
        return testId;
    }

    public void setTestId(Object testId) {
        this.testId = Integer.parseInt(testId.toString());
    }
}
