package ru.rsreu.CHHENEV0813.filters;

import ru.rsreu.CHHENEV0813.command.CommandEnum;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IsBlockedFilter implements Filter{

    private List<CommandEnum> blockedCommands;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        blockedCommands = new ArrayList<>();
        blockedCommands.add(CommandEnum.LOGOUT);
        blockedCommands.add(CommandEnum.SHOW_BLOCKED_PAGE);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(true);
        if (session.getAttribute("blocked") == null) {
            chain.doFilter(request,response);
        } else {
            int status = (Integer) session.getAttribute("blocked");

            String action = req.getParameter("command");
            CommandEnum commandEnum = CommandEnum.SHOW_BLOCKED_PAGE;

            if (!(action == null || action.isEmpty())){
                try{
                    commandEnum = CommandEnum.valueOf(action.toUpperCase());
                } catch (IllegalArgumentException exception){
                    System.err.println(exception.getMessage());
                }
            }

            if (status == 0) {
                chain.doFilter(request, response);
            } else {
                if (!blockedCommands.contains(commandEnum)) {
                    ((HttpServletResponse) response).sendRedirect( MessageManager.getProperty("show.blocked"));
                } else {
                    chain.doFilter(request, response);
                }
            }
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
