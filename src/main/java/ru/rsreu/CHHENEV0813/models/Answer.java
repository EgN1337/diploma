package ru.rsreu.CHHENEV0813.models;

public class Answer {

    private int answerID;
    private String answerText;
    private boolean answerCondition;

    public Answer(){

    }

    public Answer(int answerID, String answerText, boolean answerCondition){
        this.answerID = answerID;
        this.answerText = answerText;
        this.answerCondition = answerCondition;
    }

    public int getAnswerID() {
        return answerID;
    }

    public String getAnswerText() {
        return answerText;
    }

    public boolean isAnswerCondition() {
        return answerCondition;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public void setAnswerCondition(boolean answerCondition) {
        this.answerCondition = answerCondition;
    }
}
