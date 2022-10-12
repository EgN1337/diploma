package ru.rsreu.CHHENEV0813.oracleDAO;

import javafx.util.Pair;
import ru.rsreu.CHHENEV0813.interfaces.DAOModifiedTest;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleModifiedTestDAO implements DAOModifiedTest {

    private Connection connection;

    public OracleModifiedTestDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public int addTest(String testName, String description, String task, int teacherId) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int testId = 0;
        List<Integer> students = new ArrayList<>();
        try{
            statement = connection.prepareStatement(MessageManager.getProperty("sql.addNewTest"));
            statement.setString(1, testName);
            statement.setString(2,description);
            statement.setString(3,task);
            statement.setInt(4,teacherId);
            statement.executeUpdate();

            statement = connection.prepareStatement(MessageManager.getProperty("sql.findTestId"));
            statement.setString(1, testName);
            statement.setString(2,description);
            statement.setInt(3,teacherId);
            resultSet = statement.executeQuery();
            testId = getTestId(resultSet);

            statement = connection.prepareStatement(MessageManager.getProperty("sql.getStudentsList"));
            resultSet = statement.executeQuery();
            students = getStudentsList(resultSet);

            for (int i = 0; i < students.size(); i++){
                statement = connection.prepareStatement(MessageManager.getProperty("sql.addNewTestToSolution"));
                statement.setInt(1,testId);
                statement.setString(2, "");
                statement.setInt(3, students.get(i));
                statement.executeUpdate();
            }

            for (int i = 0; i < students.size(); i++){
                statement = connection.prepareStatement(MessageManager.getProperty("sql.addNewTestToResults"));
                statement.setInt(1,students.get(i));
                statement.setInt(2, testId);
                statement.setInt(3, 6);
                statement.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(statement);
        }
        return testId;
    }

    private int getTestId(ResultSet resultSet) throws SQLException{
        List<Integer> testIds = new ArrayList<>();
        while(resultSet.next()){
            Integer testId = resultSet.getInt("test_id");
            testIds.add(testId);
        }
        return testIds.get(0);
    }

    private List<Integer> getStudentsList(ResultSet resultSet) throws SQLException{
        List<Integer> students = new ArrayList<>();
        while(resultSet.next()){
            Integer student = resultSet.getInt("user_id");
            students.add(student);
        }
        return students;
    }

    @Override
    public String getStudentSolution(int testId, int studentId) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String solution = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.getStudentSolution"));
            statement.setInt(1, testId);
            statement.setInt(2,studentId);
            resultSet = statement.executeQuery();
            solution = getStudentSolutionFromSolutions(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(statement);
        }
        return solution;
    }

    private String getStudentSolutionFromSolutions(ResultSet resultSet) throws SQLException{
        List<String> solutions = new ArrayList<>();
        while(resultSet.next()){
            String solution = resultSet.getString("test_solution");
            solutions.add(solution);
        }
        return solutions.get(0);
    }

    @Override
    public void checkTest(int testId, int studentId, int assessment) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(MessageManager.getProperty("sql.checkStudentSolution"));
            statement.setInt(1, assessment);
            statement.setInt(2, studentId);
            statement.setInt(3, testId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(statement);
        }
    }

    @Override
    public int addQuestion(String questionText, int testID, int questionType, int questionMaxPoint, int questionWeight) {
        PreparedStatement statement = null;
        //ResultSet resultSet = null;
        int questionId = 0;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.addNewQuestion"));
            statement.setString(1,questionText);
            statement.setInt(2, testID);
            statement.setInt(3, questionType);
            statement.setInt(4, questionMaxPoint);
            statement.setInt(5,questionWeight);
            statement.executeUpdate();
            questionId = getQuestionId(questionText, testID, questionType, questionMaxPoint, questionWeight);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(statement);
        }
        return questionId;
    }

    private int getQuestionId(String questionText, int testID, int questionType, int questionMaxPoint, int questionWeight) throws SQLException{
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int questionID = 0;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.findQuestionId"));
            statement.setString(1,questionText);
            statement.setInt(2, testID);
            statement.setInt(3, questionType);
            statement.setInt(4, questionMaxPoint);
            statement.setInt(5,questionWeight);
            resultSet = statement.executeQuery();
            questionID = getQuestionIdFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(statement);
        }

        return questionID;
    }

    private int getQuestionIdFromResultSet(ResultSet resultSet) throws SQLException {
        List<Integer> questionIds = new ArrayList<>();
        while(resultSet.next()){
            Integer questionId = resultSet.getInt("question_id");
            questionIds.add(questionId);
        }
        return questionIds.get(0);
    }

    @Override
    public void addAnswer(String answerText, int questionID, int answerCondition) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.addNewAnswer"));
            statement.setString(1, answerText);
            statement.setInt(2, questionID);
            statement.setInt(3, answerCondition);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(statement);
        }
    }

    @Override
    public List<Pair<Integer, List<Integer>>> getQuestionAnswersList(int testID) {

        List<Pair<Integer, List<Integer>>> listPair = new ArrayList<Pair<Integer, List<Integer>>>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.findQuestionWithTestId"));
            statement.setInt(1, testID);
            resultSet = statement.executeQuery();
            List<Integer> questions = getQuestionsList(resultSet);

            statement = connection.prepareStatement(MessageManager.getProperty("sql.findAnswerWithQuestionId"));
            for (int i = 0; i < questions.size(); i++){
                statement.setInt(1, questions.get(i));
                resultSet = statement.executeQuery();
                List<Integer> answers = getAnswersList(resultSet);
                listPair.add(new Pair<>(questions.get(i), answers));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(statement);
        }
        return listPair;
    }

    public List<Integer> getQuestionsList(ResultSet resultSet) throws SQLException {
        List<Integer> questions = new ArrayList<>();
        while(resultSet.next()){
            int question = resultSet.getInt("question_id");
            questions.add(question);
        }
        return questions;
    }

    public List<Integer> getAnswersList(ResultSet resultSet) throws SQLException {
        List<Integer> answers = new ArrayList<>();
        while(resultSet.next()){
            int answer = resultSet.getInt("answer_id");
            answers.add(answer);
        }
        return answers;
    }

    @Override
    public String getQuestionText(int questionID) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String questionText = null;
        try{
            statement = connection.prepareStatement(MessageManager.getProperty("sql.findQuestionText"));
            statement.setInt(1, questionID);
            resultSet = statement.executeQuery();
            questionText = getQuestionTextFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(statement);
        }
    return questionText;
    }

    private String getQuestionTextFromResultSet(ResultSet resultSet) throws SQLException {
        List<String> questionTexts = new ArrayList<>();
        while(resultSet.next()){
            String questionText = resultSet.getString("question_text");
            questionTexts.add(questionText);
        }
        return questionTexts.get(0);
    }

    @Override
    public String getAnswerText(int answerID) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String answerText = null;
        try{
            statement = connection.prepareStatement(MessageManager.getProperty("sql.findAnswerText"));
            statement.setInt(1, answerID);
            resultSet = statement.executeQuery();
            answerText = getAnswerTextFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(statement);
        }
        return answerText;
    }

    private String getAnswerTextFromResultSet(ResultSet resultSet) throws SQLException {
        List<String> answerTexts = new ArrayList<>();
        while(resultSet.next()){
            String questionText = resultSet.getString("answer_text");
            answerTexts.add(questionText);
        }
        return answerTexts.get(0);
    }

    private void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {}
        }
    }


}
