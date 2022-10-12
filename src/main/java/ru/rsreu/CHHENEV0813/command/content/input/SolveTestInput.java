package ru.rsreu.CHHENEV0813.command.content.input;

public class SolveTestInput {
    private int questionID;
    private int studentID;
    private int studentPoint;
    private int answerID;
    private int studentAnswer;

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Object questionID) {
        this.questionID = Integer.parseInt(questionID.toString());
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(Object studentID) {
        this.studentID = Integer.parseInt(studentID.toString());
    }

    public int getStudentPoint() {
        return studentPoint;
    }

    public void setStudentPoint(Object studentPoint) {
        this.studentPoint = Integer.parseInt(studentPoint.toString());
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(Object answerID) {
        this.answerID = Integer.parseInt(answerID.toString());
    }

    public int getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(Object studentAnswer) {
        this.studentAnswer = Integer.parseInt(studentAnswer.toString());
    }
}
