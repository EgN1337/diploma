package ru.rsreu.CHHENEV0813.models;

/**
 * PassedStudent class contains all data about students which have already complete their tests and have their marks on them.
 * Contains students' info (first, sur and third names), test name and assessment.
 */
public class PassedStudent {

    private UserName name;
    private String testName;
    private String assessment;

    public PassedStudent(UserName name, String testName, String assessment) {
        this.name = name;
        this.testName = testName;
        this.assessment = assessment;
    }


    public UserName getName() {
        return name;
    }

    public String getTestName() {
        return testName;
    }

    public String getAssessment() {
        return assessment;
    }



    public void setName(UserName name) {
        this.name = name;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

}
