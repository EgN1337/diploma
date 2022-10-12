package ru.rsreu.CHHENEV0813.oracleDAO;

import ru.rsreu.CHHENEV0813.interfaces.DAOModifiedStudent;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleModifiedStudentDAO implements DAOModifiedStudent {

    private Connection connection;

    public OracleModifiedStudentDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public void sendTestSolution(int studentId, int testId, String solution) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.addSolutionIntoSolutions"));
            statement.setString(1, solution);
            statement.setInt(2, testId);
            statement.setInt(3, studentId);
            statement.executeUpdate();
            statement = connection.prepareStatement(MessageManager.getProperty("sql.addSolutionIntoResults"));
            statement.setInt(2, studentId);
            statement.setInt(1,testId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close(statement);
        }
    }

    @Override
    public int getMaxPointPerQuestion(int questionID) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int maxPoint = 0;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.getMaxPointForQuestion"));
            statement.setInt(1, questionID);
            resultSet = statement.executeQuery();
            maxPoint = getMaxPointFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close(statement);
        }
        return maxPoint;
    }

    @Override
    public void addStudentPointPerQuestion(int questionID, int studentID, int studentPoint) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.studentPointsInsert"));
            statement.setInt(1, questionID);
            statement.setInt(2, studentID);
            statement.setInt(3, studentPoint);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close(statement);
        }
    }

    @Override
    public int getAnswerCondition(int answerID) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int answerCondition = 0;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.getAnswerCondition"));
            statement.setInt(1, answerID);
            resultSet = statement.executeQuery();
            answerCondition = getConditionFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close(statement);
        }

        return answerCondition;
    }

    @Override
    public int getQuestionType(int questionID) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int questionType = 0;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.getQuestionType"));
            statement.setInt(1, questionID);
            resultSet = statement.executeQuery();
            questionType = getTypeFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close(statement);
        }

        return questionType;
    }

    private int getTypeFromResultSet(ResultSet resultSet) throws SQLException {
        List<Integer> types = new ArrayList<>();
        while(resultSet.next()){
            Integer type = resultSet.getInt("question_type");
            types.add(type);
        }
        return types.get(0);
    }

    @Override
    public int getQuestionWeight(int questionID) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int questionWeight = 0;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.getQuestionWeight"));
            statement.setInt(1, questionID);
            resultSet = statement.executeQuery();
            questionWeight = getWeightFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close(statement);
        }

        return questionWeight;
    }

    @Override
    public List<Integer> getStudentsResult(int testID) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Integer> listOfQuestions = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.findQuestionWithTestId"));
            statement.setInt(1, testID);
            resultSet = statement.executeQuery();
            listOfQuestions = getListOfQuestions(resultSet);



        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close(statement);
        }
        //findQuestionWithTestId
        return listOfQuestions;
    }

    @Override
    public int getStudentsPoint(int questionID, int studentID) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int point = 0;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.getStudentsPoint"));
            statement.setInt(1, questionID);
            statement.setInt(2, studentID);
            resultSet = statement.executeQuery();
            point = getStudentPoint(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close(statement);
        }
        //findQuestionWithTestId
        return point;
    }

    private int getStudentPoint(ResultSet resultSet) throws SQLException{
        List<Integer> points = new ArrayList<>();
        while(resultSet.next()){
            Integer point = resultSet.getInt("student_point");
            points.add(point);
        }
        return points.get(0);
    }

    private List<Integer> getListOfQuestions(ResultSet resultSet) throws SQLException {
        List<Integer> questions = new ArrayList<>();
        while(resultSet.next()){
            Integer question = resultSet.getInt("question_id");
            questions.add(question);
        }
        return questions;
    }

    private int getWeightFromResultSet(ResultSet resultSet) throws SQLException {
        List<Integer> weights = new ArrayList<>();
        while(resultSet.next()){
            Integer weight = resultSet.getInt("question_weight");
            weights.add(weight);
        }
        return weights.get(0);
    }

    private int getMaxPointFromResultSet(ResultSet resultSet) throws SQLException {
        List<Integer> points = new ArrayList<>();
        while(resultSet.next()){
            Integer point = resultSet.getInt("question_max_point");
            points.add(point);
        }
        return points.get(0);
    }

    private int getConditionFromResultSet(ResultSet resultSet) throws SQLException {
        List<Integer> conditions = new ArrayList<>();
        while(resultSet.next()){
            Integer condition = resultSet.getInt("answer_condition");
            conditions.add(condition);
        }
        return conditions.get(0);
    }

    private void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {}
        }
    }
}
