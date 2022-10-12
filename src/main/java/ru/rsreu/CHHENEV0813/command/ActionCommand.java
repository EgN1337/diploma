package ru.rsreu.CHHENEV0813.command;
import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    void getAttributes(HttpServletRequest request);
    String execute() throws Exception;
    void setAttributes(HttpServletRequest request);
    String getRedirect();
}

