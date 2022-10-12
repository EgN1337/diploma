package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.ActionCommand;
import ru.rsreu.CHHENEV0813.command.content.input.AddUserFromAdminToDefineInput;
import ru.rsreu.CHHENEV0813.exceptions.UserDataException;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.UserName;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public class AddUserFromAdminCommand implements ActionCommand {
    private AddUserFromAdminToDefineInput inputUser;
    private Connection connection;

    @Override
    public void getAttributes(HttpServletRequest request) {
        inputUser = new AddUserFromAdminToDefineInput();

        inputUser.setLogin(request.getParameter("user_id"));
        inputUser.setSurName(request.getParameter("sur_name"));
        inputUser.setFirstName(request.getParameter("first_name"));
        inputUser.setThirdName(request.getParameter("third_name"));
        UserName user = new UserName(inputUser.getFirstName(), inputUser.getSurName(), inputUser.getThirdName());
        inputUser.setUser(user);

        inputUser.setUserRoleFromRequest(request.getParameter("user_role"));
        inputUser.setPassword(request.getParameter("password"));

    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("show.admin");
        boolean isExistedUser = DAOFactory.getInstance(DBType.ORACLE).getUser().isExistedUser(inputUser.getLogin());
        if (!validateInputData(inputUser)) {
            throw new UserDataException("wrongInputData", "Wrong values of input data.");
        } else if (isExistedUser) {
            throw new UserDataException("wrongInputData", "User with the same login is already exist.");
        } else {
        DAOFactory.getInstance(DBType.ORACLE).getModifiedUser().addUser(
                inputUser.getUser(),
                inputUser.getUserRole(),
                inputUser. getLogin().trim(),
                inputUser.getPassword().trim());}
        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        request.setAttribute("data2", inputUser);
    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }

    private boolean validateInputData(AddUserFromAdminToDefineInput inputUser) {
        return !(inputUser.getFirstName().trim().isEmpty()
                || inputUser.getSurName().trim().isEmpty()
                || inputUser.getLogin().trim().isEmpty()
                || inputUser.getPassword().trim().isEmpty()
                || inputUser.getThirdName().trim().isEmpty());
    }
}
