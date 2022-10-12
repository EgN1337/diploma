package ru.rsreu.CHHENEV0813.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.CHHENEV0813.command.content.input.LoginInputContent;
import ru.rsreu.CHHENEV0813.command.content.output.LoginOutputContent;
import ru.rsreu.CHHENEV0813.exceptions.LoginException;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.User;
import ru.rsreu.CHHENEV0813.oracleDAO.OracleModifiedUserDAO;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

public class LoginCommand implements ActionCommand {
    private LoginInputContent inputContent;
    private LoginOutputContent outputContent;

    @Override
    public void getAttributes(HttpServletRequest request) {
        inputContent = new LoginInputContent();
        inputContent.setLogin(request.getParameter("login"));
        inputContent.setPassword(request.getParameter("password"));
        HttpSession session = request.getSession(true);
        session.setAttribute("instance", DAOFactory.getInstance(DBType.ORACLE).getModifiedUser());
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("show.new");
        outputContent = new LoginOutputContent();
        String login = this.inputContent.getLogin();
        String password = this.inputContent.getPassword();

        User user = DAOFactory.getInstance(DBType.ORACLE).getUser().getUserData(login, password);

        if (user == null) {
            throw new LoginException("wrongLogin", "Wrong login or password. Try to login again");
            /*page = MessageManager.getProperty("jsp.authorization");*/
        } else {
            outputContent.setUserId(user.getUserId());
            outputContent.setRole(user.getRole().getRoleId());
            outputContent.setSurName(user.getUserName().getSurName());
            outputContent.setFirstName(user.getUserName().getFirstName());
            outputContent.setThirdName(user.getUserName().getThirdName());
            outputContent.setBlocked(user.getBlocked());
        }
        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (outputContent == null){
            session.setAttribute("wrongLogin","Неверный логин или пароль");
        } else {
            session.setAttribute("user_id",outputContent.getUserId());
            session.setAttribute("user_role",outputContent.getRole());
            session.setAttribute("sur_name",outputContent.getSurName());
            session.setAttribute("first_name",outputContent.getFirstName());
            session.setAttribute("third_name",outputContent.getThirdName());
            session.setAttribute("blocked",outputContent.getBlocked());
            session.setAttribute("date", new java.util.Date().getTime());
        }

    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }
}