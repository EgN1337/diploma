package ru.rsreu.CHHENEV0813.command.content.input;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class LoginInputContent {

    private String login;
    private String password;

    public LoginInputContent() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
