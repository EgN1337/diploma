package ru.rsreu.CHHENEV0813.models;

public class Question {
    private int questionID;
    private String questionText;
    private String questionType;
    private int questionMaxPoint;
    private int questionWeight;

    public Question(){

    }

    public Question(int questionID, String questionText, String questionType, int questionMaxPoint, int questionWeight){
        this.questionID = questionID;
        this.questionText = questionText;
        this.questionType = questionType;
        this.questionMaxPoint = questionMaxPoint;
        this.questionWeight = questionWeight;
    }

    public int getQuestionID() {
        return questionID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getQuestionType() {
        return questionType;
    }

    public int getQuestionMaxPoint() {
        return questionMaxPoint;
    }

    public int getQuestionWeight() {
        return questionWeight;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public void setQuestionMaxPoint(int questionMaxPoint) {
        this.questionMaxPoint = questionMaxPoint;
    }

    public void setQuestionWeight(int questionWeight) {
        this.questionWeight = questionWeight;
    }
}
