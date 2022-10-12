package ru.rsreu.CHHENEV0813.command.content.output;

import ru.rsreu.CHHENEV0813.models.PassedTest;

import java.util.List;

public class LoadStudentPassedTestsOutput {
    private List<PassedTest> passedTestList;

    public List<PassedTest> getPassedTestList() {
        return passedTestList;
    }

    public void setPassedTestList(List<PassedTest> passedTestList) {
        this.passedTestList = passedTestList;
    }
}
