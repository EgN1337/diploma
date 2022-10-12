package ru.rsreu.CHHENEV0813.factory;
import javax.servlet.http.HttpServletRequest;
import ru.rsreu.CHHENEV0813.command.ActionCommand;
import ru.rsreu.CHHENEV0813.command.EmptyCommand;
import ru.rsreu.CHHENEV0813.command.CommandEnum;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();

        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {

            return current;
        }

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action);
        }
        return current;
    }
}