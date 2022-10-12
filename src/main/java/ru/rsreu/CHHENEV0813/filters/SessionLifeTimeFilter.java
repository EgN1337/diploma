package ru.rsreu.CHHENEV0813.filters;

import ru.rsreu.CHHENEV0813.models.enums.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class SessionLifeTimeFilter implements Filter{
    private static final long TIMEOUT = 60*60*1000;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        if (session != null && session.getAttribute("date") != null) {
            long delta = new java.util.Date().getTime() - (Long.parseLong(session.getAttribute("date").toString()));
            if (delta > TIMEOUT) {
                session.setAttribute("user_role", Role.GUEST.toString());
            }
        }
        chain.doFilter(request, response);
        //session.invalidate();
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
