package ru.rsreu.CHHENEV0813.oracleDAO;

import ru.rsreu.CHHENEV0813.interfaces.DAOTeacher;
import ru.rsreu.CHHENEV0813.models.*;
import ru.rsreu.CHHENEV0813.models.enums.Role;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleTeacherDAO implements DAOTeacher {

    private Connection connection;

    public OracleTeacherDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<TeachersTest> getAllTests(int userId) {
        List<TeachersTest> teachersTests = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.getAllTeachersTests"));
            statement.setInt(1,userId);
            resultSet = statement.executeQuery();
            teachersTests = getTeachersTests(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(resultSet, statement);
        }
        return teachersTests;
    }

    private List<TeachersTest> getTeachersTests(ResultSet resultSet) throws SQLException{
        List<TeachersTest> teachersTests = new ArrayList<>();
        while(resultSet.next()){
            TeachersTest teachersTest = new TeachersTest(
                resultSet.getString("test_name"),
                resultSet.getString("test_description")
            );
            teachersTests.add(teachersTest);
        }
        return teachersTests;
    }

    @Override
    public List<PassedStudent> getPassedStudents(int teacherId) {
        List<PassedStudent> passedStudents = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.getPassedStudents"));
            statement.setInt(1,teacherId);

            resultSet = statement.executeQuery();
            passedStudents = getPassedStudents(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(resultSet, statement);
        }
        return passedStudents;
    }

    @Override
    public List<PassedStudent> getUnpassedStudents(int teacherId) {
        List<PassedStudent> unpassedStudents = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.getUnpassedStudents"));
            statement.setInt(1,teacherId);

            resultSet = statement.executeQuery();
            unpassedStudents = getPassedStudents(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(resultSet, statement);
        }
        return unpassedStudents;
    }

    private List<PassedStudent> getPassedStudents(ResultSet resultSet) throws SQLException{
        List<PassedStudent> passedStudents = new ArrayList<>();
        while(resultSet.next()){
            PassedStudent passedStudent = new PassedStudent(
                    new UserName(resultSet.getString("first_name"),
                            resultSet.getString("sur_name"),
                            resultSet.getString("third_name")),
                    resultSet.getString("test_name"),
                    resultSet.getString("assessment_description")
            );
            passedStudents.add(passedStudent);
        }
        return passedStudents;
    }

    @Override
    public List<InvaluableSolutions> getUnwatchedStudents(int teacherId) {
        List<InvaluableSolutions> invaluableSolutions = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.getInvaluableSolutions"));
            statement.setInt(1,teacherId);

            resultSet = statement.executeQuery();
            invaluableSolutions = getInvaluableSolutions(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(resultSet, statement);
        }
        return invaluableSolutions;
    }

    @Override
    public List<InvaluableSolutions> getUnsolvedStudents(int teacherId) {
        List<InvaluableSolutions> invaluableSolutions = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.getUnstartedStudents"));
            statement.setInt(1,teacherId);

            resultSet = statement.executeQuery();
            invaluableSolutions = getInvaluableSolutions(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(resultSet, statement);
        }
        return invaluableSolutions;
    }

    private List<InvaluableSolutions> getInvaluableSolutions(ResultSet resultSet) throws SQLException {
        List<InvaluableSolutions> invaluableSolutions = new ArrayList<>();
        while(resultSet.next()){
            InvaluableSolutions invaluableSolution = new InvaluableSolutions(
                    resultSet.getInt("student_id"),
                    new UserName(resultSet.getString("first_name"),
                            resultSet.getString("sur_name"),
                            resultSet.getString("third_name")),

                    resultSet.getString("test_name"),
                    resultSet.getInt("test_id")
            );
            invaluableSolutions.add(invaluableSolution);
        }
        return invaluableSolutions;
    }

    private void close(ResultSet resultSet, Statement statement) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {

            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {

            }
        }
    }

}
