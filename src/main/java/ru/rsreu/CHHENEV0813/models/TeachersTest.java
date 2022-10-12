package ru.rsreu.CHHENEV0813.models;

/**
 * TeachersTest class contains data about test. It contains test name and its description.
 */
public class TeachersTest {

    private String testName;
    private String description;

    public TeachersTest(){

    }

    public TeachersTest(String testName, String description) {

        this.testName = testName;
        this.description = description;
    }

    public String getTestName() {
        return testName;
    }

    public String getDescription() {
        return description;
    }



    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
