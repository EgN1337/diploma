package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.AssessTestInput;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.CheckingTest;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AssessTestCommand implements ActionCommand{
    private AssessTestInput testInput;
    @Override
    public void getAttributes(HttpServletRequest request) {
        testInput = new AssessTestInput();
        HttpSession session = request.getSession(true);
        testInput.setUserId(session.getAttribute("student_id"));
        testInput.setTestId(session.getAttribute("test_id"));
        testInput.setAssessment(request.getParameter("assessment_id"));
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("show.teacher");
        DAOFactory.getInstance(DBType.ORACLE).getModifiedTest().checkTest(
                testInput.getTestId(),
                testInput.getUserId(),
                testInput.getAssessment()
        );
        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("test_solution", "");
        session.setAttribute("test_id", "");
        session.setAttribute("student_id", null);
    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }
}
