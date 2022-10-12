package ru.rsreu.CHHENEV0813.interfaces;

import javafx.util.Pair;

import java.util.List;

public interface DAOModifiedTest {
    /**
     * Allows add test into tests' list on teacher page
     * @param testName      - name of the test
     * @param description   - test description
     * @param task          - test task
     * @param teacherId     - ID of teacher which wants to add test
     * @return
     */
    int addTest(String testName, String description, String task, int teacherId);

    /**
     * Allows teacher to get student solution on his test
     * @param testId    - test ID
     * @param studentId - student ID
     * @return          - student solution on this test
     */
    String getStudentSolution(int testId, int studentId);

    /**
     * Allows teacher to assess student by student's solution
     * @param testId     - test ID
     * @param studentId  - student ID
     * @param assessment - assessment which teacher wants to mark
     */
    void checkTest(int testId, int studentId, int assessment);

    int addQuestion(String questionText,int testID, int questionType, int questionMaxPoint, int questionWeight);

    void addAnswer(String answerText, int questionID, int answerCondition);

    List<Pair<Integer, List<Integer>>> getQuestionAnswersList(int testID);

    String getQuestionText(int questionID);

    String getAnswerText(int answerID);

}
