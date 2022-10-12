package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.EditUserStatusInput;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class EditUserStatusCommand implements ActionCommand{
    private EditUserStatusInput editUserStatus;
    @Override
    public void getAttributes(HttpServletRequest request) {
        editUserStatus = new EditUserStatusInput();

        editUserStatus.setUserIdFromRequest(request.getParameter("login"));
        //editUserStatus.setUserStatusFromRequest(request.getParameter("blocked"));

    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("show.moder");
        DAOFactory.getInstance(DBType.ORACLE).getModifiedUser().editUserStatus(editUserStatus.getUserId());
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
