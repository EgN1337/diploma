package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.RedirectInput;
import ru.rsreu.CHHENEV0813.models.enums.Role;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RedirectCommand implements ActionCommand{
    private RedirectInput input;

    @Override
    public void getAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        input = new RedirectInput();

        Object role = session.getAttribute("user_role");
        Object userId = session.getAttribute("user_id");

        input.setRole(role == null ? 0 : Integer.parseInt(role.toString()));
        input.setUserId(userId == null ? -1 : Integer.parseInt(userId.toString()));
        input.setHasRole(role != null);
    }

    @Override
    public String execute() throws Exception {
        Role userRole = Role.GUEST;

        if (input.isHasRole()) {
            userRole = Role.getRoleFromInt(input.getRole());
        }

        return userRole.getMainPage();
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (!input.isHasRole()) {
            session.setAttribute("user_id", -1);
            session.setAttribute("user_role", Role.GUEST.toString());
        }
    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.send");
    }
}
