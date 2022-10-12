package ru.rsreu.CHHENEV0813.exceptions;

import ru.rsreu.CHHENEV0813.resource.MessageManager;

public class UserDataException extends CommandException{

    public UserDataException( String key, String message) {
        super(MessageManager.getProperty("show.admin"), key, message);
    }
}
