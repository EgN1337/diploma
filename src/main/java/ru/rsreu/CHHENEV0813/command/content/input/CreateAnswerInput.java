package ru.rsreu.CHHENEV0813.command.content.input;

public class CreateAnswerInput {
    private String answerText;
    private int questionID;
    private int answerCondition;

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Object questionID) {
        this.questionID = Integer.parseInt(questionID.toString());
    }

    public int isAnswerCondition() {
        return answerCondition;
    }

    public void setAnswerCondition(Object answerCondition) {
        this.answerCondition = Integer.parseInt(answerCondition.toString());
    }
}
