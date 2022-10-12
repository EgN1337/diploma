package ru.rsreu.CHHENEV0813;

import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.enums.Role;
import ru.rsreu.CHHENEV0813.oracleDAO.OracleModifiedUserDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionListener implements HttpSessionAttributeListener {

    private static final String ATTRIBUTE = "user_role";
    private static final int GUEST_ID = -1;

    public void attributeRemoved(HttpSessionBindingEvent ev) {
        setLoggedStatus(ev);
    }

    public void attributeAdded(HttpSessionBindingEvent ev) {
        setLoggedStatus(ev);
    }
    public void attributeReplaced(HttpSessionBindingEvent ev) {
        setLoggedStatus(ev);
    }

    public void setLoggedStatus(HttpSessionBindingEvent ev){


        if (ev.getName().equals(ATTRIBUTE)) {
            HttpSession session = ev.getSession();

            int userId = Integer.parseInt(session.getAttribute("user_id").toString());

            if (userId != GUEST_ID) {
                OracleModifiedUserDAO instance = (OracleModifiedUserDAO) ev.getSession().getAttribute("instance");
                instance.updateLoggedStatus(
                        userId,
                        ev.getValue().equals(Role.GUEST.toString())
                );
            }
        }
    }
}
