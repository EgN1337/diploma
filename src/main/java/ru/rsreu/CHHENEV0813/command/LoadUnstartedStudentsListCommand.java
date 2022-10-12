package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.LoadUnstartedStudentsListInput;
import ru.rsreu.CHHENEV0813.command.content.output.LoadUnstartedStudentsListOutput;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.InvaluableSolutions;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoadUnstartedStudentsListCommand implements ActionCommand{
    private LoadUnstartedStudentsListInput inputContent4;
    private LoadUnstartedStudentsListOutput outputContent4;

    @Override
    public void getAttributes(HttpServletRequest request) {
        inputContent4 = new LoadUnstartedStudentsListInput();
        HttpSession session = request.getSession(true);
        inputContent4.setTestTeacher(session.getAttribute("user_id"));
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("jsp.teacher");
        outputContent4 = new LoadUnstartedStudentsListOutput();
        Integer testTeacher4 = this.inputContent4.getTestTeacher();

        List<InvaluableSolutions> invaluableSolutions = DAOFactory.getInstance(DBType.ORACLE).getTeacher().getUnsolvedStudents(testTeacher4);
        if (invaluableSolutions == null) {

        } else {
            outputContent4.setUnstartedSolutions(invaluableSolutions);
        }
        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        request.setAttribute("data_unstarted", outputContent4);
    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }
}
