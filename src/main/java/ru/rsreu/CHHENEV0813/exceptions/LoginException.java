package ru.rsreu.CHHENEV0813.exceptions;

import ru.rsreu.CHHENEV0813.resource.MessageManager;

public class LoginException extends CommandException{
    public LoginException(String key, String message) {
        super(MessageManager.getProperty("jsp.authorization"), key, message);
    }
}
