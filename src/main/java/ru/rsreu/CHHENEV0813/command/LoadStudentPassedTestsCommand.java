package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.LoadStudentPassedTestsInput;
import ru.rsreu.CHHENEV0813.command.content.input.LoadStudentUncheckedTestsInput;
import ru.rsreu.CHHENEV0813.command.content.input.LoadStudentUnpassedTestsInput;
import ru.rsreu.CHHENEV0813.command.content.input.LoadStudentUnsolvedTestsInput;
import ru.rsreu.CHHENEV0813.command.content.output.*;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.InvaluableSolutions;
import ru.rsreu.CHHENEV0813.models.PassedStudent;
import ru.rsreu.CHHENEV0813.models.PassedTest;
import ru.rsreu.CHHENEV0813.models.UncheckedTest;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoadStudentPassedTestsCommand implements ActionCommand{
    private LoadStudentPassedTestsInput inputContent;
    private LoadStudentPassedTestsOutput outputContent;

    private LoadStudentUnpassedTestsInput inputContent2;
    private LoadStudentUnpassedTestsOutput outputContent2;

    private LoadStudentUncheckedTestsInput inputContent3;
    private LoadStudentUncheckedTestsOutput outputContent3;

    private LoadStudentUnsolvedTestsInput inputContent4;
    private LoadStudentUnsolvedTestsOutput outputContent4;

    @Override
    public void getAttributes(HttpServletRequest request) {
        inputContent = new LoadStudentPassedTestsInput();
        inputContent2 = new LoadStudentUnpassedTestsInput();
        inputContent3 = new LoadStudentUncheckedTestsInput();
        inputContent4 = new LoadStudentUnsolvedTestsInput();
        HttpSession session = request.getSession(true);
        inputContent.setUserId(session.getAttribute("user_id"));
        inputContent2.setUserId(session.getAttribute("user_id"));
        inputContent3.setUserId(session.getAttribute("user_id"));
        inputContent4.setUserId(session.getAttribute("user_id"));
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("jsp.student");
        outputContent = new LoadStudentPassedTestsOutput();
        Integer userId = this.inputContent.getUserId();

        List<PassedTest> passedTestList = DAOFactory.getInstance(DBType.ORACLE).getStudent().getSuccessfulTest(userId);
        if (passedTestList == null) {

        } else {
            outputContent.setPassedTestList(passedTestList);
        }

        outputContent2 = new LoadStudentUnpassedTestsOutput();
        Integer userId2 = this.inputContent2.getUserId();

        List<PassedTest> passedTestList2 = DAOFactory.getInstance(DBType.ORACLE).getStudent().getUnseccessfulTest(userId2);
        if (passedTestList2 == null) {

        } else {
            outputContent2.setPassedTestList(passedTestList2);
        }

        outputContent3 = new LoadStudentUncheckedTestsOutput();
        Integer userId3 = this.inputContent3.getUserId();

        List<UncheckedTest> uncheckedTestList3 = DAOFactory.getInstance(DBType.ORACLE).getStudent().getUnwatchedTest(userId3);
        if (uncheckedTestList3 == null) {

        } else {
            outputContent3.setUncheckedTestList(uncheckedTestList3);
        }

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
        request.setAttribute("data_passed", outputContent);
        request.setAttribute("data_unpassed", outputContent2);
        request.setAttribute("data_unchecked", outputContent3);
        request.setAttribute("data_unstarted", outputContent4);
    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }
}
