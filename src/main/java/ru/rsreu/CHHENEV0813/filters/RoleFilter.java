package ru.rsreu.CHHENEV0813.filters;

import jdk.nashorn.internal.runtime.ListAdapter;
import ru.rsreu.CHHENEV0813.command.CommandEnum;
import ru.rsreu.CHHENEV0813.models.enums.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


public class RoleFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(true);
        if (session.getAttribute("user_role") == null) {
            session.setAttribute("user_id", -1);
            session.setAttribute("user_role", Role.GUEST.getRoleId());
        }
        Role role = Role.getRoleFromInt(Integer.parseInt(session.getAttribute("user_role").toString()));
        String action = httpServletRequest.getParameter("command");
        CommandEnum commandEnum = CommandEnum.LOGIN;
        if (!(action == null || action.isEmpty())) {
            try {
                commandEnum = CommandEnum.valueOf(action.toUpperCase());
            } catch (IllegalArgumentException exception) {
                System.err.println(exception.getMessage());
            }
        }

        EnumSet<CommandEnum> roleCommand = Role.getRoleCommands(role.getRoleId());

        if (roleCommand.contains(commandEnum)) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/controller?command=guest_redirect");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
