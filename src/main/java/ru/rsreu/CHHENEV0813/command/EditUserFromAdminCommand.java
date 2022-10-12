package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.EditUserFromAdminInput;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.UserName;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class EditUserFromAdminCommand implements ActionCommand{
    private EditUserFromAdminInput editUser;

    @Override
    public void getAttributes(HttpServletRequest request) {
        editUser = new EditUserFromAdminInput();
        editUser.setLogin(request.getParameter("user_id"));
        editUser.setSurName(request.getParameter("sur_name"));
        editUser.setFirstName(request.getParameter("first_name"));
        editUser.setThirdName(request.getParameter("third_name"));
        UserName user = new UserName(editUser.getFirstName(), editUser.getSurName(), editUser.getThirdName());
        editUser.setUser(user);
        editUser.setUserRoleFromRequest(request.getParameter("user_role"));
        editUser.setPassword(request.getParameter("password"));
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("show.admin");
        DAOFactory.getInstance(DBType.ORACLE).getModifiedUser().editUser(editUser.getUser(), editUser.getLogin(), editUser.getUserRole(), editUser.getPassword());
        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {

    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }
}
