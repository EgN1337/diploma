package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.LoadAllTestsInput;
import ru.rsreu.CHHENEV0813.command.content.output.LoadAllTestsOutput;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.TeachersTest;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoadAllTestsCommand implements ActionCommand{
    private LoadAllTestsInput inputContent3;
    private LoadAllTestsOutput outputContent3;
    @Override
    public void getAttributes(HttpServletRequest request) {
        inputContent3 = new LoadAllTestsInput();
        HttpSession session = request.getSession(true);
        inputContent3.setTestTeacher(session.getAttribute("user_id"));
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("jsp.teacher");
        outputContent3 = new LoadAllTestsOutput();
        Integer testTeacher = this.inputContent3.getTestTeacher();

        List<TeachersTest> test = DAOFactory.getInstance(DBType.ORACLE).getTeacher().getAllTests(testTeacher);
        if (test == null) {

        } else {
            outputContent3.setTeachersTests(test);
        }
        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("test_id", "");
        request.setAttribute("data", outputContent3);
    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }
}
