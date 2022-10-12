package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.LoadStudentUnsolvedTestsInput;
import ru.rsreu.CHHENEV0813.command.content.output.LoadStudentUnsolvedTestsOutput;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.UncheckedTest;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoadStudentUnsolvedTestsCommand implements ActionCommand{
    private LoadStudentUnsolvedTestsInput inputContent4;
    private LoadStudentUnsolvedTestsOutput outputContent4;

    @Override
    public void getAttributes(HttpServletRequest request) {
        inputContent4 = new LoadStudentUnsolvedTestsInput();
        HttpSession session = request.getSession(true);
        inputContent4.setUserId(session.getAttribute("user_id"));
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("jsp.student");
        outputContent4 = new LoadStudentUnsolvedTestsOutput();
        Integer userId4 = this.inputContent4.getUserId();

        List<UncheckedTest> uncheckedTestList4 = DAOFactory.getInstance(DBType.ORACLE).getStudent().getUnsolvedTest(userId4);
        if (uncheckedTestList4 == null) {

        } else {
            outputContent4.setUncheckedTestList(uncheckedTestList4);
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
