package ru.rsreu.CHHENEV0813;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.rsreu.CHHENEV0813.command.ActionCommand;
import ru.rsreu.CHHENEV0813.exceptions.CommandException;
import ru.rsreu.CHHENEV0813.factory.ActionFactory;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

/**
 * All requests handler
 * Build response depends on request parameters
 * Main parameter - command
 */
public class FrontController extends HttpServlet {
    /**
     * Handle GET-requests
     * @param request - request object
     * @param response - response object
     * @throws ServletException - exception to servlet
     * @throws IOException - I/0 exception
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handle POST-requests
     * @param request - request object
     * @param response - response object
     * @throws ServletException - exception to servlet
     * @throws IOException - I/0 exception
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Define the result location of program "step"
     * Depends on command=.., where program takes location and gives the result one
     * @param request - request object
     * @param response - response object
     * @throws ServletException - exception to servlet
     * @throws IOException - I/0 exception
     */
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        String redirect = command.getRedirect();
        try {
            command.getAttributes(request);
            page = command.execute();
            command.setAttributes(request);
        }   catch (CommandException exception){
            exception.setErrorMessages(request);
            redirect = MessageManager.getProperty("redirect.forward");
            page = exception.getPage();
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
            redirect = MessageManager.getProperty("redirect.send");
            page = MessageManager.getProperty("show.new");
        }

        if (redirect.equals(MessageManager.getProperty("redirect.forward"))){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(page);
        }

    }
}
