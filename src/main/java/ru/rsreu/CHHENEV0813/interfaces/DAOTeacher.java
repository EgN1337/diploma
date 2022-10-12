package ru.rsreu.CHHENEV0813.interfaces;

import ru.rsreu.CHHENEV0813.models.*;

import java.util.List;

public interface DAOTeacher {

    /**
     * Allows teacher to get list of all tests
     * @param userId - teacher's ID
     * @return       - list of all tests
     */
    List<TeachersTest> getAllTests(int userId);

    /**
     * Allows teacher to get list of students who got mark over 2
     * @param teacherId - teacher's ID
     * @return          - list of students who got mark over 2
     */
    List<PassedStudent> getPassedStudents(int teacherId);

    /**
     * Allows teacher to get list of students who got mark equal 2
     * @param teacherId - teacher's ID
     * @return          - list of students who got mark equal 2
     */
    List<PassedStudent> getUnpassedStudents(int teacherId);

    /**
     * Allows teacher to get list of students who are not already assessed
     * @param teacherId - teacher's ID
     * @return          - list of students who are not already assessed
     */
    List<InvaluableSolutions> getUnwatchedStudents(int teacherId);

    /**
     * Allows teacher to get list of students who are not solved yet
     * @param teacherId - teacher's ID
     * @return          - list of students who are not solved yet
     */
    List<InvaluableSolutions> getUnsolvedStudents(int teacherId);
}
