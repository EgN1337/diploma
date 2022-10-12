package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.CreateTestInput;
import ru.rsreu.CHHENEV0813.exceptions.StudentTestException;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateTestCommand implements ActionCommand{
    private CreateTestInput testInput;
    private int testID;

    @Override
    public void getAttributes(HttpServletRequest request) {
        testInput = new CreateTestInput();
        HttpSession session = request.getSession(true);
        testInput.setTeacherId(session.getAttribute("user_id"));
        testInput.setTestName(request.getParameter("test_name"));
        testInput.setDescription(request.getParameter("test_description"));
        testInput.setTask(request.getParameter("test_task"));
        testID = 0;
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("show.add.test");
        if (validateInputData(testInput)) {
            throw new StudentTestException("wrongTestData", "Wrong values of input data.");
        } else {
        testID = DAOFactory.getInstance(DBType.ORACLE).getModifiedTest().addTest(
                testInput.getTestName().trim(),
                testInput.getDescription().trim(),
                testInput. getTask().trim(),
                testInput.getTeacherId());}
        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("test_id", testID);
        session.setAttribute("loh_id", 2);
    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }

    private boolean validateInputData (CreateTestInput testInput){
        return (testInput.getTask().trim() == ""
                || testInput.getDescription().trim() == ""
                || testInput.getTestName().trim() == "");
    }
}
