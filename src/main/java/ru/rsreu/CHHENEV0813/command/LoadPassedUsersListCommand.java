package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.*;
import ru.rsreu.CHHENEV0813.command.content.output.*;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.InvaluableSolutions;
import ru.rsreu.CHHENEV0813.models.PassedStudent;
import ru.rsreu.CHHENEV0813.models.TeachersTest;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoadPassedUsersListCommand implements ActionCommand{
    private LoadPassedUsersListInput inputContent;
    private LoadPassedUsersListOutput outputContent;

    private LoadUnpassedUsersListInput inputContent2;
    private LoadUnpassedUsersListOutput outputContent2;

    private LoadAllTestsInput inputContent3;
    private LoadAllTestsOutput outputContent3;

    private LoadUnstartedStudentsListInput inputContent4;
    private LoadUnstartedStudentsListOutput outputContent4;

    private LoadInvaluableTestsListInput inputContent5;
    private LoadInvaluableTestsListOutput outputContent5;

    @Override
    public void getAttributes(HttpServletRequest request) {
        inputContent = new LoadPassedUsersListInput();
        inputContent2 = new LoadUnpassedUsersListInput();
        inputContent3 = new LoadAllTestsInput();
        inputContent4 = new LoadUnstartedStudentsListInput();
        inputContent5 = new LoadInvaluableTestsListInput();
        HttpSession session = request.getSession(true);
        inputContent.setTestTeacher(session.getAttribute("user_id"));
        inputContent2.setTestTeacher(session.getAttribute("user_id"));
        inputContent3.setTestTeacher(session.getAttribute("user_id"));
        inputContent4.setTestTeacher(session.getAttribute("user_id"));
        inputContent5.setTestTeacher(session.getAttribute("user_id"));
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("jsp.teacher");
        outputContent = new LoadPassedUsersListOutput();
        Integer testTeacher = this.inputContent.getTestTeacher();

        outputContent2 = new LoadUnpassedUsersListOutput();
        Integer testTeacher2 = this.inputContent2.getTestTeacher();

        outputContent3 = new LoadAllTestsOutput();
        Integer testTeacher3 = this.inputContent3.getTestTeacher();

        outputContent4 = new LoadUnstartedStudentsListOutput();
        Integer testTeacher4 = this.inputContent4.getTestTeacher();

        outputContent5 = new LoadInvaluableTestsListOutput();
        Integer testTeacher5 = this.inputContent5.getTestTeacher();

        List<PassedStudent> passedStudents = DAOFactory.getInstance(DBType.ORACLE).getTeacher().getPassedStudents(testTeacher);
        if (passedStudents == null) {

        } else {
            outputContent.setPassedStudent(passedStudents);
        }

        List<PassedStudent> passedStudents2 = DAOFactory.getInstance(DBType.ORACLE).getTeacher().getUnpassedStudents(testTeacher2);
        if (passedStudents == null) {

        } else {
            outputContent2.setPassedStudent(passedStudents2);
        }

        List<TeachersTest> test = DAOFactory.getInstance(DBType.ORACLE).getTeacher().getAllTests(testTeacher);
        if (test == null) {

        } else {
            outputContent3.setTeachersTests(test);
        }

        List<InvaluableSolutions> invaluableSolutions = DAOFactory.getInstance(DBType.ORACLE).getTeacher().getUnsolvedStudents(testTeacher4);
        if (invaluableSolutions == null) {

        } else {
            outputContent4.setUnstartedSolutions(invaluableSolutions);
        }

        List<InvaluableSolutions> invaluableSolutions2 = DAOFactory.getInstance(DBType.ORACLE).getTeacher().getUnwatchedStudents(testTeacher5);
        if (invaluableSolutions2 == null) {

        } else {
            outputContent5.setInvaluableSolutions(invaluableSolutions2);
        }

        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        request.setAttribute("data_passed", outputContent);
        request.setAttribute("data_unpassed", outputContent2);
        request.setAttribute("data", outputContent3);
        request.setAttribute("data_unstarted", outputContent4);
        request.setAttribute("data_unwatched", outputContent5);
    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }
}
