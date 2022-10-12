package ru.rsreu.CHHENEV0813.oracleDAO;

import ru.rsreu.CHHENEV0813.interfaces.DAOStudent;
import ru.rsreu.CHHENEV0813.models.*;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleStudentDAO implements DAOStudent {

    private Connection connection;

    public OracleStudentDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<PassedTest> getSuccessfulTest(int studentId) {
        List<PassedTest> passedTests = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.studentSuccessTests"));
            statement.setInt(1, studentId);
            resultSet = statement.executeQuery();
            passedTests = getCheckedTests(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(resultSet, statement);
        }
        return passedTests;
    }

    @Override
    public List<PassedTest> getUnseccessfulTest(int studentId) {
        List<PassedTest> passedTests = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.studentUnsuccessfulTests"));
            statement.setInt(1, studentId);
            resultSet = statement.executeQuery();
            passedTests = getCheckedTests(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(resultSet, statement);
        }
        return passedTests;
    }

    private List<PassedTest> getCheckedTests(ResultSet resultSet) throws SQLException {
        List<PassedTest> passedTests = new ArrayList<>();
        while(resultSet.next()){
            PassedTest passedTest = new PassedTest(
                    resultSet.getString("test_name"),
                    resultSet.getString("assessment_description"));
            passedTests.add(passedTest);
        }
        return passedTests;
    }

    @Override
    public List<UncheckedTest> getUnwatchedTest(int studentId) {
        List<UncheckedTest> uncheckedTests = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.studentUnwatchedTests"));
            statement.setInt(1, studentId);
            resultSet = statement.executeQuery();
            uncheckedTests = getUncheckedTests(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(resultSet, statement);
        }
        return uncheckedTests;
    }

    @Override
    public List<UncheckedTest> getUnsolvedTest(int studentId) {
        List<UncheckedTest> uncheckedTests = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.studentUnsolvedTests"));
            statement.setInt(1, studentId);
            resultSet = statement.executeQuery();
            uncheckedTests = getUncheckedTests(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(resultSet, statement);
        }
        return uncheckedTests;
    }

    public List<UncheckedTest> getUncheckedTests(ResultSet resultSet) throws SQLException {
        List<UncheckedTest> uncheckedTests = new ArrayList<>();
        while(resultSet.next()){
            UncheckedTest uncheckedTest = new UncheckedTest(
                    resultSet.getInt("test_id"),
                    resultSet.getString("test_name"));
            uncheckedTests.add(uncheckedTest);
        }
        return uncheckedTests;
    }

    @Override
    public Test getTestTask(int testId){
        Test test = new Test();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.getTestTask"));
            statement.setInt(1, testId);
            resultSet = statement.executeQuery();
            test = getTestsTask(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(resultSet, statement);
        }
        return test;
    }

    public Test getTestsTask(ResultSet resultSet) throws SQLException{
        List<Test> tests = new ArrayList<>();
        while(resultSet.next()){
            Test test = new Test(
                    resultSet.getString("test_description"),
                    resultSet.getString("test_task"));
            tests.add(test);
        }
        if (tests.size() != 0 ){
            return tests.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean isExistedTest(Integer testId) {
        List<Integer> rows = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.isExistTest"));
            statement.setInt(1, testId);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int row = resultSet.getInt("test_id");

                if (row != 0 ){
                    rows.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(resultSet, statement);
        }

        if (rows.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isExistedUser(int userId) {
        List<Integer> rows = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.isExistTestAndStudent"));
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int row = resultSet.getInt("student_id");

                if (row != 0){
                    rows.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(resultSet, statement);
        }

        if (rows.size() == 0) {
            return false;
        } else {
            return true;
        }
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
