package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.output.LoadUserListForAdminContent;
import ru.rsreu.CHHENEV0813.command.content.output.LoadUserListFromModerOutput;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.UserStatus;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LoadUserListFromModerCommand implements ActionCommand{
    private LoadUserListFromModerOutput outputContent;

    @Override
    public void getAttributes(HttpServletRequest request) {

    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("jsp.moder");
        outputContent = new LoadUserListFromModerOutput();
        List<UserStatus> users = DAOFactory.getInstance(DBType.ORACLE).getUser().getAllUsersInsteadAdmin();
        outputContent.setUserStatusList(users);

        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        request.setAttribute("data", outputContent);
    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }
}
