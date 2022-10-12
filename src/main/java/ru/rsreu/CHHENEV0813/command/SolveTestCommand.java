package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.SolveTestInput;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SolveTestCommand implements ActionCommand{

    private SolveTestInput inputContent;
    private int value;
    private int amount;

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
        inputContent = new SolveTestInput();
        HttpSession session = request.getSession(true);
        inputContent.setStudentAnswer(request.getParameter("student_answer"));
        inputContent.setAnswerID(session.getAttribute("currentAnswer" + inputContent.getStudentAnswer() + "ID"));
        inputContent.setStudentID(session.getAttribute("user_id"));
        inputContent.setQuestionID(session.getAttribute("currentQuestionID"));
        value = Integer.parseInt(session.getAttribute("currentValue").toString());
        amount = Integer.parseInt(session.getAttribute("question_amount").toString());

    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("jsp.solve.test");

            int answerCondition = DAOFactory.getInstance(DBType.ORACLE).getModifiedStudent().getAnswerCondition(inputContent.getAnswerID());
            if (answerCondition == 1) {
                inputContent.setStudentPoint(DAOFactory.getInstance(DBType.ORACLE).getModifiedStudent().getMaxPointPerQuestion(
                        inputContent.getQuestionID()
                ));

                DAOFactory.getInstance(DBType.ORACLE).getModifiedStudent().addStudentPointPerQuestion(
                        inputContent.getQuestionID(),
                        inputContent.getStudentID(),
                        inputContent.getStudentPoint()
                );
            } else if (answerCondition == 0) {
                inputContent.setStudentPoint(-DAOFactory.getInstance(DBType.ORACLE).getModifiedStudent().getMaxPointPerQuestion(
                        inputContent.getQuestionID()
                ));
                DAOFactory.getInstance(DBType.ORACLE).getModifiedStudent().addStudentPointPerQuestion(
                        inputContent.getQuestionID(),
                        inputContent.getStudentID(),
                        inputContent.getStudentPoint()
                );
            }

        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        int currentValue = Integer.parseInt(session.getAttribute("currentValue").toString());
        session.setAttribute("currentValue",  currentValue + 1);

        //  ОСУЩЕСТВИТЬ ПЕРЕПРИСВАИВАНИЕ ВОПРОСОВ, ОТВЕТОВ, ID-ШНИКОВ
        if ( value <= amount ){
            session.setAttribute("currentQuestion",session.getAttribute("question" + session.getAttribute("currentValue")));
            session.setAttribute("currentQuestionID",session.getAttribute("question" + session.getAttribute("currentValue") +"ID"));
            session.setAttribute("currentAnswer1",session.getAttribute("answer" + session.getAttribute("currentValue") + "1"));
            session.setAttribute("currentAnswer2",session.getAttribute("answer" + session.getAttribute("currentValue") + "2"));
            session.setAttribute("currentAnswer3",session.getAttribute("answer" + session.getAttribute("currentValue") + "3"));
            session.setAttribute("currentAnswer4",session.getAttribute("answer" + session.getAttribute("currentValue") + "4"));
            session.setAttribute("currentAnswer1ID",session.getAttribute("answer" + session.getAttribute("currentValue") + "1ID"));
            session.setAttribute("currentAnswer2ID",session.getAttribute("answer" + session.getAttribute("currentValue") + "2ID"));
            session.setAttribute("currentAnswer3ID",session.getAttribute("answer" + session.getAttribute("currentValue") + "3ID"));
            session.setAttribute("currentAnswer4ID",session.getAttribute("answer" + session.getAttribute("currentValue") + "4ID"));
        }

        int studentPoint = inputContent.getStudentPoint();
        int questionWeight = DAOFactory.getInstance(DBType.ORACLE).getModifiedStudent().getQuestionWeight(inputContent.getQuestionID());
        int questionType = DAOFactory.getInstance(DBType.ORACLE).getModifiedStudent().getQuestionType(inputContent.getQuestionID());

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

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }
}
