package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.CheckTestInput;
import ru.rsreu.CHHENEV0813.command.content.input.LoadTestTaskInput;
import ru.rsreu.CHHENEV0813.command.content.output.CheckTestOutput;
import ru.rsreu.CHHENEV0813.exceptions.StudentTestException;
import ru.rsreu.CHHENEV0813.exceptions.TestException;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CheckTestCommand implements ActionCommand{
    private CheckTestInput inputContent;
    private CheckTestOutput outputContent;

    public static int mouthMaxPoints;
    public static int mouthStudentPoints;
    public static int noseMaxPoints;
    public static int noseStudentPoints;
    public static int eyeMaxPoints;
    public static int eyeStudentPoints;
    public static int eyeBrowsMaxPoints;
    public static int eyeBrowsStudentPoints;

    @Override
    public void getAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        inputContent = new CheckTestInput();
        inputContent.setTestId(request.getParameter("test_id"));
        inputContent.setStudentId(request.getParameter("student_id"));

        session.setAttribute("mouthValue", 50);
        session.setAttribute("eyeValue", 50);
        session.setAttribute("eyeBrowsValue", 50);
        session.setAttribute("noseValue", 50);
        session.setAttribute("hairValue", 50);
        session.setAttribute("headValue", 50);
    }

    @Override
    public String execute() throws Exception {
        outputContent = new CheckTestOutput();
        String page = MessageManager.getProperty("show.teacher");
        boolean isExistedTest = DAOFactory.getInstance(DBType.ORACLE).getStudent().isExistedTest(inputContent.getTestId());
        boolean isExistedUser = DAOFactory.getInstance(DBType.ORACLE).getStudent().isExistedUser(inputContent.getStudentId());
        if (!validateInputData(inputContent)) {
            throw new StudentTestException("wrongStudentTest", "Wrong values of input data.");
        } else if (!isExistedTest) {
            throw new StudentTestException("wrongStudentTest", "Test with this ID does not exist.");
        } else if (!isExistedUser){
            throw new StudentTestException("wrongStudentTest", "Student with this ID does not exist");
        } else {
            outputContent.setTestSolution(DAOFactory.getInstance(DBType.ORACLE).getModifiedTest().getStudentSolution(
                    inputContent.getTestId(),
                    inputContent.getStudentId()));
        }

        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("test_solution", outputContent.getTestSolution());
        session.setAttribute("test_id", inputContent.getTestId());
        session.setAttribute("student_id", inputContent.getStudentId());

        List<Integer> list = DAOFactory.getInstance(DBType.ORACLE).getModifiedStudent().getStudentsResult(inputContent.getTestId());
        for (int i = 0; i < list.size(); i++) {
            int studentPoint = DAOFactory.getInstance(DBType.ORACLE).getModifiedStudent().getStudentsPoint(list.get(i), inputContent.getStudentId());
            int questionWeight = DAOFactory.getInstance(DBType.ORACLE).getModifiedStudent().getQuestionWeight(list.get(i));
            int questionType = DAOFactory.getInstance(DBType.ORACLE).getModifiedStudent().getQuestionType(list.get(i));

            int differValue = questionWeight * studentPoint / 10;

            int mouthValue = Integer.parseInt(session.getAttribute("mouthValue").toString());
            int eyeBrowsValue = Integer.parseInt(session.getAttribute("eyeBrowsValue").toString());
            int noseValue = Integer.parseInt(session.getAttribute("noseValue").toString());
            int eyeValue = Integer.parseInt(session.getAttribute("eyeValue").toString());
            int hairValue = (mouthValue + eyeBrowsValue + noseValue + eyeValue + differValue * 2) / 4;
            int headValue = (mouthValue + eyeBrowsValue + noseValue + eyeValue + differValue * 2) / 4;


            if (questionType == 1) {
                session.setAttribute("mouthValue", (mouthValue + differValue));
                mouthMaxPoints++;
                if (studentPoint > 0) {
                    mouthStudentPoints++;
                } else {

                }
            } else
            if (questionType == 2) {
                session.setAttribute("eyeBrowsValue", (eyeBrowsValue + differValue * 2));
                eyeBrowsMaxPoints++;
                if (studentPoint > 0) {
                    eyeBrowsStudentPoints++;
                } else {

                }
            } else
            if (questionType == 3) {
                session.setAttribute("noseValue", (noseValue + differValue * 2));
                noseMaxPoints++;
                if (studentPoint > 0) {
                    noseStudentPoints++;
                } else {

                }
            } else
            if (questionType == 4) {
                session.setAttribute("eyeValue", (eyeValue + differValue * 2));
                eyeMaxPoints++;
                if (studentPoint > 0) {
                    eyeStudentPoints++;
                } else {

                }
            }
            session.setAttribute("hairValue", hairValue);
            session.setAttribute("headValue", headValue);
        }

    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }

    private boolean validateInputData (CheckTestInput inputContent){
        return !(inputContent.getTestId() == 0
              || inputContent.getStudentId() == 0);
    }
}
