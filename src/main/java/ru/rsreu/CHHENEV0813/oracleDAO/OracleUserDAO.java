package ru.rsreu.CHHENEV0813.oracleDAO;

import ru.rsreu.CHHENEV0813.interfaces.DAOUser;
import ru.rsreu.CHHENEV0813.models.*;
import ru.rsreu.CHHENEV0813.models.enums.Role;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OracleUserDAO implements DAOUser {

    private Connection connection;


    public OracleUserDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public User getUserData(String login, String password) {
        User user = new User();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.authorization"));
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            user = getUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(resultSet, statement);
        }
        return user;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while(resultSet.next()){
            User user = new User(
                        resultSet.getInt("user_id"),
                        new UserName(resultSet.getString("first_name"),
                                    resultSet.getString("sur_name"),
                                    resultSet.getString("third_name")),
                        Role.getRoleFromInt(resultSet.getInt("user_role")),
                        resultSet.getInt("blocked"),
                        resultSet.getString("password"),
                        resultSet.getInt("logged"));
            users.add(user);
        }
        if (users.size() != 0 ){
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<UserStatus> getAllUsersInsteadAdmin() {
        List<UserStatus> userStatuses = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.usersTestInsteadAdmin"));
            resultSet = statement.executeQuery();
            userStatuses = getAllUsersInsteadAdministrator(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(resultSet, statement);
        }
        return userStatuses;
    }

    private List<UserStatus> getAllUsersInsteadAdministrator(ResultSet resultSet) throws SQLException {
        List<UserStatus> userStatuses = new ArrayList<>();
        while(resultSet.next()){
            UserStatus userStatus = new UserStatus(

                    new UserName(resultSet.getString("first_name"),
                            resultSet.getString("sur_name"),
                            resultSet.getString("third_name")),
                    resultSet.getString("login"),
                    Role.getRoleFromInt(resultSet.getInt("role_id")),
                    resultSet.getInt("blocked"),
                    resultSet.getString("password"),
                    resultSet.getInt("logged"));
            userStatuses.add(userStatus);
        }
        return userStatuses;
    }

    @Override
    public boolean isExistedUser(String login) {
        List<String> rows = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(MessageManager.getProperty("sql.isExist"));
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                String row = resultSet.getString("login");

                if (row != null || row.isEmpty()){
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
