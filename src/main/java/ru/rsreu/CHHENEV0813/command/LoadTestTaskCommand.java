package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.AddUserFromAdminToDefineInput;
import ru.rsreu.CHHENEV0813.command.content.input.LoadStudentUncheckedTestsInput;
import ru.rsreu.CHHENEV0813.command.content.input.LoadTestTaskInput;
import ru.rsreu.CHHENEV0813.command.content.output.LoadStudentUncheckedTestsOutput;
import ru.rsreu.CHHENEV0813.command.content.output.LoadTestTaskOutput;
import ru.rsreu.CHHENEV0813.exceptions.TestException;
import ru.rsreu.CHHENEV0813.exceptions.UserDataException;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.Test;
import ru.rsreu.CHHENEV0813.models.UncheckedTest;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoadTestTaskCommand implements ActionCommand{
    private LoadTestTaskInput inputContent;
    private LoadTestTaskOutput outputContent;

    @Override
    public void getAttributes(HttpServletRequest request) {
        inputContent = new LoadTestTaskInput();
        inputContent.setTestId(request.getParameter("test_id"));
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("show.student");
        outputContent = new LoadTestTaskOutput();
        Integer testId = this.inputContent.getTestId();
        boolean isExistedTest = DAOFactory.getInstance(DBType.ORACLE).getStudent().isExistedTest(inputContent.getTestId());
        if (!validateInputData(inputContent)) {
            throw new TestException("wrongTest", "Wrong values of input data.");
        } else if (!isExistedTest) {
            throw new TestException("wrongTest", "Test with this id does not exist.");
        } else {
            Test test = DAOFactory.getInstance(DBType.ORACLE).getStudent().getTestTask(testId);
            if (test == null) {
                throw new TestException("wrongTest", "Test with this id does not exist.");
            } else {
                outputContent.setTest(test);
            }
            return page;
        }
    }

    @Override
    public void setAttributes (HttpServletRequest request){
         HttpSession session = request.getSession(true);
         session.setAttribute("test_task2", outputContent.getTest().getTask());
         session.setAttribute("test_description2", outputContent.getTest().getDescription());
         session.setAttribute("test_id2", inputContent.getTestId());
    }

    @Override
    public String getRedirect () {
        return MessageManager.getProperty("redirect.forward");
    }

    private boolean validateInputData (LoadTestTaskInput inputContent){
        return !(inputContent.getTestId() == 0);
    }

}
