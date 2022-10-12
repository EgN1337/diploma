package ru.rsreu.CHHENEV0813.command.content.input;

public class CreateQuestionInput {
    private String questionText;
    private int testID;
    private int questionType;
    private int questionMaxPoint;
    private int questionWeight;




    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(Object testID) {
        this.testID = Integer.parseInt(testID.toString());
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Object questionType) {
        this.questionType = Integer.parseInt(questionType.toString());
    }

    public int getQuestionMaxPoint() {
        return questionMaxPoint;
    }

    public void setQuestionMaxPoint(Object questionMaxPoint) {
        this.questionMaxPoint = Integer.parseInt(questionMaxPoint.toString());
    }

    public int getQuestionWeight() {
        return questionWeight;
    }

    public void setQuestionWeight(Object questionWeight) {
        this.questionWeight = Integer.parseInt(questionWeight.toString());
    }
}
