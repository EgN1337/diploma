package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.LoadInvaluableTestsListInput;
import ru.rsreu.CHHENEV0813.command.content.output.LoadInvaluableTestsListOutput;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.InvaluableSolutions;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoadInvaluableTestsListCommand implements ActionCommand{
    private LoadInvaluableTestsListInput inputContent5;
    private LoadInvaluableTestsListOutput outputContent5;

    @Override
    public void getAttributes(HttpServletRequest request) {
        inputContent5 = new LoadInvaluableTestsListInput();
        HttpSession session = request.getSession(true);
        inputContent5.setTestTeacher(session.getAttribute("user_id"));
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("jsp.teacher");
        outputContent5 = new LoadInvaluableTestsListOutput();
        Integer testTeacher5 = this.inputContent5.getTestTeacher();

        List<InvaluableSolutions> invaluableSolutions2 = DAOFactory.getInstance(DBType.ORACLE).getTeacher().getUnwatchedStudents(testTeacher5);
        if (invaluableSolutions2 == null) {

        } else {
            outputContent5.setInvaluableSolutions(invaluableSolutions2);
        }
        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        request.setAttribute("data_unwatched", outputContent5);
    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }
}
