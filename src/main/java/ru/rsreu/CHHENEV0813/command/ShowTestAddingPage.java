package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ShowTestAddingPage implements ActionCommand{

    @Override
    public void getAttributes(HttpServletRequest request) {

    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("jsp.add.test");
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
