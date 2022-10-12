package ru.rsreu.CHHENEV0813.interfaces;

import ru.rsreu.CHHENEV0813.models.*;

import java.util.List;


public interface DAOStudent {

    /**
     * Allows get list of student successful tests
     * @param studentId - student's ID
     * @return          - list of student successful tests
     */
    List<PassedTest> getSuccessfulTest(int studentId);

    /**
     * Allows get list of student unsuccessful tests
     * @param studentId - student's ID
     * @return          - list of student unsuccessful tests
     */
    List<PassedTest> getUnseccessfulTest(int studentId);

    /**
     * Allows get list of student unwatched tests
     * @param studentId - student's ID
     * @return          - list of student unwatched tests
     */
    List<UncheckedTest> getUnwatchedTest(int studentId);

    /**
     * Allows get list of student unsolved tests
     * @param studentId - student's ID
     * @return          - list of student unsolved tests
     */
    List<UncheckedTest> getUnsolvedTest(int studentId);

    /**
     * Allows student to get test task
     * @param testId    - test ID
     * @return          - task of the test
     */
    Test getTestTask(int testId);

    /**
     * Allows check if test is already exists in system
     * @param testId    - test ID
     * @return          - true/false
     */
    boolean isExistedTest(Integer testId);

    /**
     * Allows check if person is already exists in system
     * @param userId    - user ID
     * @return          - true/false
     */
    boolean isExistedUser(int userId);


}
