package ru.rsreu.CHHENEV0813.exceptions;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandException extends Exception{
    private final String page;
    private final Map<String, String> errorMessages;

    public CommandException(String page, String key, String message) {
        this.page = page;
        errorMessages = new HashMap<String, String>();
        errorMessages.put(key, message);
    }

    public void addError(String key, String message) {
        errorMessages.put(key, message);
    }

    public String getPage() {
        return page;
    }

    public void setErrorMessages(HttpServletRequest request) {
        for (String error : errorMessages.keySet()) {
            request.setAttribute(error, errorMessages.get(error));
        }
    }
}
