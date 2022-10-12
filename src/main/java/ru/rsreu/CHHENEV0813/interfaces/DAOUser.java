package ru.rsreu.CHHENEV0813.interfaces;

import ru.rsreu.CHHENEV0813.models.*;

import java.sql.SQLException;
import java.util.List;

public interface DAOUser {

    /**
     * Allows get user data
     * @param login     - user login
     * @param password  - user password
     * @return          - user data contains login and password
     * @throws SQLException
     */
    User getUserData(String login, String password) throws SQLException;

    /**
     * Allows get list of all users instead administrator and moderator
     * @return - list of all users instead administrator and moderator
     */
    List<UserStatus> getAllUsersInsteadAdmin();

    /**
     * Allows check if person is already exists in system
     * @param login     - user login
     * @return          - true/false
     */
    boolean isExistedUser(String login);
}
