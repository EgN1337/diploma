package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.LoadStudentUnpassedTestsInput;
import ru.rsreu.CHHENEV0813.command.content.output.LoadStudentUnpassedTestsOutput;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.PassedTest;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoadStudentUnpassedTestsCommand implements ActionCommand{
    private LoadStudentUnpassedTestsInput inputContent2;
    private LoadStudentUnpassedTestsOutput outputContent2;

    @Override
    public void getAttributes(HttpServletRequest request) {
        inputContent2 = new LoadStudentUnpassedTestsInput();
        HttpSession session = request.getSession(true);
        inputContent2.setUserId(session.getAttribute("user_id"));
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("jsp.student");
        outputContent2 = new LoadStudentUnpassedTestsOutput();
        Integer userId2 = this.inputContent2.getUserId();

        List<PassedTest> passedTestList2 = DAOFactory.getInstance(DBType.ORACLE).getStudent().getUnseccessfulTest(userId2);
        if (passedTestList2 == null) {

        } else {
            outputContent2.setPassedTestList(passedTestList2);
        }
        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        request.setAttribute("data_unpassed", outputContent2);
    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }
}
