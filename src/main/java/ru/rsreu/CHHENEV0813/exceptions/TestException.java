package ru.rsreu.CHHENEV0813.exceptions;

import ru.rsreu.CHHENEV0813.resource.MessageManager;

public class TestException extends CommandException{
    public TestException(String key, String message) {
        super(MessageManager.getProperty("show.student"), key, message);
    }
}
