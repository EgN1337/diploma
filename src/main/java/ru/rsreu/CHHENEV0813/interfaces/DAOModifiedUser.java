package ru.rsreu.CHHENEV0813.interfaces;

import ru.rsreu.CHHENEV0813.models.*;

public interface DAOModifiedUser {

    /**
     * Allows admin to create new user of system
     * @param userName  - first, second and third names
     * @param role      - user's role
     * @param login     - user's login
     * @param password  - user's password
     */
    void addUser(UserName userName, int role, String login, String password);

    /**
     * Allows admin to edit any user of system
     * @param userName  - first, second and third names
     * @param login     - user's role
     * @param role      - user's login
     * @param password  - user's password
     */
    void editUser(UserName userName, String login, int role, String password);

    /**
     * Allows admin to delete any user of system
     * @param userId    - user's ID
     * @param login     - user's login
     */
    void deleteUser( int userId, String login);

    /**
     * Allows admin to edit user blocked status
     * @param userId - user's ID
     */
    void editUserStatus(String userId);

    /**
     * Allows admin to edit user logged status
     * @param userId        - user's ID
     * @param loggedStatus  - new status
     */
    void updateLoggedStatus(int userId, boolean loggedStatus);
}
