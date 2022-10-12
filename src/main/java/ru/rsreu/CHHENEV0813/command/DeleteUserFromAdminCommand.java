package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.DeleteUserFromAdminInput;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserFromAdminCommand implements ActionCommand{
    private DeleteUserFromAdminInput deleteUser;
    @Override
    public void getAttributes(HttpServletRequest request) {
        deleteUser = new DeleteUserFromAdminInput();

        //deleteUser.setUserIdFromRequest(request.getParameter("user_id"));
        deleteUser.setLogin(request.getParameter("user_id"));
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("show.admin");
        DAOFactory.getInstance(DBType.ORACLE).getModifiedUser().deleteUser(deleteUser.getUserId(), deleteUser.getLogin());
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
