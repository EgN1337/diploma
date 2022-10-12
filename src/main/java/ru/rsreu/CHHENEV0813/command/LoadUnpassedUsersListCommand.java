package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.LoadUnpassedUsersListInput;
import ru.rsreu.CHHENEV0813.command.content.output.LoadUnpassedUsersListOutput;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.PassedStudent;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoadUnpassedUsersListCommand implements ActionCommand{
    private LoadUnpassedUsersListInput inputContent;
    private LoadUnpassedUsersListOutput outputContent;

    @Override
    public void getAttributes(HttpServletRequest request) {
        inputContent = new LoadUnpassedUsersListInput();
        HttpSession session = request.getSession(true);
        inputContent.setTestTeacher(session.getAttribute("user_id"));
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("jsp.teacher");
        outputContent = new LoadUnpassedUsersListOutput();
        Integer testTeacher = this.inputContent.getTestTeacher();

        List<PassedStudent> passedStudents = DAOFactory.getInstance(DBType.ORACLE).getTeacher().getUnpassedStudents(testTeacher);
        if (passedStudents == null) {

        } else {
            outputContent.setPassedStudent(passedStudents);
        }
        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        request.setAttribute("data_unpassed", outputContent);
    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }
}
