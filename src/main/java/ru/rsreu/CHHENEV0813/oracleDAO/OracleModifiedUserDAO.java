package ru.rsreu.CHHENEV0813.oracleDAO;

import ru.rsreu.CHHENEV0813.interfaces.DAOModifiedUser;
import ru.rsreu.CHHENEV0813.models.UserName;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleModifiedUserDAO implements DAOModifiedUser {

    private final Connection connection;

    public OracleModifiedUserDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public void addUser(UserName userName, int role, String login, String password) {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.addNewUserDefine"));

            statement.setString(1, login);
            statement.setString(2, userName.getFirstName());
            statement.setString(3, userName.getSurName());
            statement.setString(4, userName.getThirdName());
            statement.executeUpdate();

            statement = connection.prepareStatement(MessageManager.getProperty("sql.addNewUser"));
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setInt(3, role);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(statement);
        }
    }

    @Override
    public void editUser( UserName userName, String login,  int role, String password) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.userDefineUpdate"));

            statement.setString(1,userName.getFirstName());
            statement.setString(2,userName.getSurName());
            statement.setString(3,userName.getThirdName());
            statement.setString(4,login);
            statement.executeUpdate();

            statement = connection.prepareStatement(MessageManager.getProperty("sql.userUpdate"));

            statement.setString(1, password);
            statement.setString(2, login);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        this.close(statement);}
    }

    @Override
    public void deleteUser(int userId, String login) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.findUserIdFromLogin"));
            statement.setString(1,login);
            resultSet = statement.executeQuery();
            userId = getUserId(resultSet);

            statement = connection.prepareStatement(MessageManager.getProperty("sql.deleteFromSolution"));

            statement.setInt(1,userId);
            statement.executeUpdate();

            statement = connection.prepareStatement(MessageManager.getProperty("sql.deleteFromResults"));
            statement.setInt(1,userId);
            statement.executeUpdate();

            statement = connection.prepareStatement(MessageManager.getProperty("sql.deleteFromKnowledgeTable"));
            statement.setInt(1,userId);
            statement.executeUpdate();

            statement = connection.prepareStatement(MessageManager.getProperty("sql.deleteFromUsers"));
            statement.setString(1,login);
            statement.executeUpdate();

            statement = connection.prepareStatement(MessageManager.getProperty("sql.deleteFromUserDefine"));
            statement.setString(1,login);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(statement);
        }
    }

    @Override
    public void editUserStatus(String userId) {
        PreparedStatement statement = null;
        int currentStatus = 0;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.findCurrentUserStatus"));
            statement.setString(1,userId);
            resultSet = statement.executeQuery();
            currentStatus = getStatus(resultSet);
            if (currentStatus == 0) {
                statement = connection.prepareStatement(MessageManager.getProperty("sql.blockUser"));
            } else {
                statement = connection.prepareStatement(MessageManager.getProperty("sql.unblockUser"));
            }
            statement.setString(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(statement);
        }
    }


    private int getUserId(ResultSet resultSet) throws SQLException{
        List<Integer> userIds = new ArrayList<>();
        while(resultSet.next()){
            int userId = resultSet.getInt("user_id");
            userIds.add(userId);
        }
        return userIds.get(0);
    }

    private int getStatus(ResultSet resultSet) throws SQLException{
        List<Integer> statuses = new ArrayList<>();
        while(resultSet.next()){
            int status = resultSet.getInt("blocked");
            statuses.add(status);
        }
        return statuses.get(0);
    }

    @Override
    public void updateLoggedStatus(int userId, boolean loggedStatus){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.findStatus"));
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();

            statement = connection.prepareStatement(MessageManager.getProperty("sql.loggedStatus"));
            if (getLoggedStatus(resultSet) == 0) {
                statement.setInt(1, loggedStatus ? 0 : 1);
            } else {
                statement.setInt(1, loggedStatus ? 1 : 0);
            }
            statement.setInt(2,userId);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            this.close(statement);
        }
    }

    private int getLoggedStatus(ResultSet resultSet) throws SQLException{
        List<Integer> loggedStatuses = new ArrayList<>();
        while(resultSet.next()){
            int logged = resultSet.getInt("logged");
            loggedStatuses.add(logged);
        }
        return loggedStatuses.get(0);
    }

    private void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {

            }
        }
    }
}
