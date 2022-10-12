package ru.rsreu.CHHENEV0813.models;

/**
 * PassedTest class contains all data about tests which student has already passed.
 * This info student might check on his page. Contains test name and assessment.
 */
public class PassedTest {

    private String testName;
    private String assessment;

    public PassedTest(){
    }

    public PassedTest(String testName, String assessment) {
        this.testName = testName;
        this.assessment = assessment;
    }

    public String getTestName() {
        return testName;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }
}
