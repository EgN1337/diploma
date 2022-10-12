package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.LoadStudentUncheckedTestsInput;
import ru.rsreu.CHHENEV0813.command.content.output.LoadStudentUncheckedTestsOutput;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.UncheckedTest;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoadStudentUncheckedTestsCommand implements ActionCommand{
    private LoadStudentUncheckedTestsInput inputContent3;
    private LoadStudentUncheckedTestsOutput outputContent3;

    @Override
    public void getAttributes(HttpServletRequest request) {
        inputContent3 = new LoadStudentUncheckedTestsInput();
        HttpSession session = request.getSession(true);
        inputContent3.setUserId(session.getAttribute("user_id"));
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("jsp.student");
        outputContent3 = new LoadStudentUncheckedTestsOutput();
        Integer userId3 = this.inputContent3.getUserId();

        List<UncheckedTest> uncheckedTestList3 = DAOFactory.getInstance(DBType.ORACLE).getStudent().getUnwatchedTest(userId3);
        if (uncheckedTestList3 == null) {

        } else {
            outputContent3.setUncheckedTestList(uncheckedTestList3);
        }
        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        request.setAttribute("data_unchecked", outputContent3);
    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }
}
