package ru.rsreu.CHHENEV0813.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.CHHENEV0813.models.enums.Role;
import ru.rsreu.CHHENEV0813.resource.ConfigurationManager;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

public class LogoutCommand implements ActionCommand {

    @Override
    public void getAttributes(HttpServletRequest request) {
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("jsp.index");
        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        request.getSession(true).setAttribute("user_role", Role.GUEST.getRoleId());
        request.getSession(true).invalidate();
    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }
}
