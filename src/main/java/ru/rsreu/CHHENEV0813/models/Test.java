package ru.rsreu.CHHENEV0813.models;

/**
 * Test class contains data about test. It contains test description and test task. Student can look at this info on his page,
 * in the "Get task of the test" gap
 */
public class Test {
    private String description;
    private String task;

    public Test( String description, String task) {

        this.description = description;
        this.task = task;
    }

    public Test() {

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public String getTask() {
        return task;
    }
}
