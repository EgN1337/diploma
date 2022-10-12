package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.AddSolutionToTestInput;
import ru.rsreu.CHHENEV0813.command.content.input.LoadTestTaskInput;
import ru.rsreu.CHHENEV0813.command.content.output.LoadTestTaskOutput;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.CheckingTest;
import ru.rsreu.CHHENEV0813.models.Test;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddSolutionToTestCommand implements ActionCommand{
    private AddSolutionToTestInput inputContent;
    @Override
    public void getAttributes(HttpServletRequest request) {
        inputContent = new AddSolutionToTestInput();
        HttpSession session = request.getSession(true);



        CheckingTest checkingTest = new CheckingTest(
                Integer.parseInt(session.getAttribute("user_id").toString()),
                Integer.parseInt(session.getAttribute("test_id2").toString()),
                request.getParameter("studentHadAnswered").toString() +
                        SolveTestCommand.mouthStudentPoints + request.getParameter("from").toString() + SolveTestCommand.mouthMaxPoints
                        + request.getParameter("firstSection").toString() +
                        SolveTestCommand.eyeBrowsStudentPoints + request.getParameter("from").toString() + SolveTestCommand.eyeBrowsMaxPoints
                        + request.getParameter("secondSection").toString() +
                        SolveTestCommand.noseStudentPoints + request.getParameter("from").toString() + SolveTestCommand.noseMaxPoints
                        + request.getParameter("thirdSection").toString() +
                        SolveTestCommand.eyeStudentPoints + request.getParameter("from").toString() + SolveTestCommand.eyeMaxPoints
                        + request.getParameter("fourthSection").toString());
        inputContent.setCheckingTest(checkingTest);

        inputContent.setStudentId(session.getAttribute("user_id"));
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("show.student");
        DAOFactory.getInstance(DBType.ORACLE).getModifiedStudent().sendTestSolution(
                inputContent.getStudentId(),
                inputContent.getCheckingTest().getTestId(),
                inputContent.getCheckingTest().getSolution()
                );
        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("test_description2", null);
        session.setAttribute("test_id2", null);
        session.setAttribute("test_task2", null);

    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }
}
