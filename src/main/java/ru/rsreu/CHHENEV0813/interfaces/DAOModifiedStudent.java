package ru.rsreu.CHHENEV0813.interfaces;

import java.util.List;

public interface DAOModifiedStudent {
    /**
     * Allows student to send solution on define test
     * @param studentId - student ID
     * @param testId    - test ID
     * @param solution  - student solution on define test
     */
    void sendTestSolution(int studentId, int testId, String solution);

    int getMaxPointPerQuestion(int questionID);

    void addStudentPointPerQuestion(int questionID, int studentID, int studentPoint);

    int getAnswerCondition(int answerID);

    int getQuestionType(int questionID);

    int getQuestionWeight(int questionID);

    List<Integer> getStudentsResult(int testID);

    int getStudentsPoint(int questionID, int studentID);
}
