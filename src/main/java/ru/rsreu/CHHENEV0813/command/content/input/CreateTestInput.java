package ru.rsreu.CHHENEV0813.command.content.input;

public class CreateTestInput {
    private String testName;
    private String description;
    private String task;
    private int teacherId;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Object teacherId) {
        this.teacherId = Integer.parseInt(teacherId.toString());
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
