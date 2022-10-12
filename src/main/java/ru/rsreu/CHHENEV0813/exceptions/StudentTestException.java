package ru.rsreu.CHHENEV0813.exceptions;

import ru.rsreu.CHHENEV0813.resource.MessageManager;

public class StudentTestException extends CommandException{
    public StudentTestException( String key, String message) {
        super(MessageManager.getProperty("show.teacher"), key, message);
    }
}
