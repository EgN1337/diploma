package ru.rsreu.CHHENEV0813.command;
import javax.servlet.http.HttpServletRequest;
import ru.rsreu.CHHENEV0813.resource.ConfigurationManager;
public class EmptyCommand implements ActionCommand {

    @Override
    public void getAttributes(HttpServletRequest request) {

    }

    @Override
    public String execute() throws Exception {
        return null;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {

    }

    @Override
    public String getRedirect() {
        return null;
    }
}