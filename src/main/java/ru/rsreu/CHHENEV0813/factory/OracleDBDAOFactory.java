package ru.rsreu.CHHENEV0813.factory;
import oracle.jdbc.driver.OracleDriver;
import ru.rsreu.CHHENEV0813.interfaces.*;
import ru.rsreu.CHHENEV0813.oracleDAO.*;
import ru.rsreu.CHHENEV0813.resource.ConfigurationManager;

import java.sql.*;
import java.util.Locale;
import java.util.Properties;

public class OracleDBDAOFactory extends DAOFactory {
    private static volatile OracleDBDAOFactory instance;
    private Connection connection;

    private OracleDBDAOFactory() {
    }

    public static OracleDBDAOFactory getInstance() throws ClassNotFoundException, SQLException {
        OracleDBDAOFactory factory = instance;
        if (instance == null) {
            synchronized (OracleDBDAOFactory.class) {
                instance = factory = new OracleDBDAOFactory();
                factory.connected();
            }
        }
        return factory;
    }

    private void connected() throws ClassNotFoundException, SQLException {
        String url = "jdbc:oracle:thin:@DESKTOP-5GKUCGQ:1521:xe";
        String user = "SYSTEM";
        String password = "0508";

        try {
            DriverManager.registerDriver(new OracleDriver());
            Properties properties = new Properties();
            properties.put("user", user);
            properties.put("password", password);
            properties.put("charSet", "chcp1251");
            connection = DriverManager.getConnection(url, properties);
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }

        System.out.println("Connected to Oracle DB!");


    }


    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public DAOModifiedStudent getModifiedStudent() {
        return new OracleModifiedStudentDAO(this.connection);
    }

    @Override
    public DAOModifiedTest getModifiedTest() {
        return new OracleModifiedTestDAO(this.connection);
    }

    @Override
    public DAOModifiedUser getModifiedUser() {
        return new OracleModifiedUserDAO(this.connection) ;
    }

    @Override
    public DAOStudent getStudent() {
        return new OracleStudentDAO(this.connection);
    }

    @Override
    public DAOUser getUser() {
        return new OracleUserDAO(this.connection);
    }

    @Override
    public DAOTeacher getTeacher() {
        return new OracleTeacherDAO(this.connection);
    }
}

