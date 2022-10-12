package ru.rsreu.CHHENEV0813.factory;

import ru.rsreu.CHHENEV0813.interfaces.*;

import java.sql.SQLException;

public abstract class DAOFactory {
    public static DAOFactory getInstance(DBType dbType) {
        DAOFactory result = dbType.getDAOFactory();
        return result;
    }


    public abstract void closeConnection() throws SQLException;
    public abstract DAOModifiedStudent getModifiedStudent();
    public abstract DAOModifiedTest getModifiedTest();
    public abstract DAOModifiedUser getModifiedUser();
    public abstract DAOStudent getStudent();
    public abstract DAOUser getUser();
    public abstract DAOTeacher getTeacher();

}

