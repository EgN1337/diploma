package ru.rsreu.CHHENEV0813.command;

import ru.rsreu.CHHENEV0813.command.content.input.CreateAnswerInput;
import ru.rsreu.CHHENEV0813.command.content.input.CreateQuestionInput;
import ru.rsreu.CHHENEV0813.exceptions.StudentTestException;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateQuestionCommand implements ActionCommand {
    private CreateQuestionInput questionInput;
    private CreateAnswerInput answerInput;
    private CreateAnswerInput answerInput2;
    private CreateAnswerInput answerInput3;
    private CreateAnswerInput answerInput4;
    private int questionID;

    @Override
    public void getAttributes(HttpServletRequest request) {
        questionInput = new CreateQuestionInput();
        answerInput = new CreateAnswerInput();
        answerInput2 = new CreateAnswerInput();
        answerInput3 = new CreateAnswerInput();
        answerInput4 = new CreateAnswerInput();

        HttpSession session = request.getSession();
        questionInput.setQuestionText(request.getParameter("question_text"));
        questionInput.setTestID(session.getAttribute("test_id"));
        questionInput.setQuestionType(request.getParameter("question_type"));
        questionInput.setQuestionMaxPoint(request.getParameter("question_max_point"));
        questionInput.setQuestionWeight(request.getParameter("question_weight"));
        answerInput.setAnswerText(request.getParameter("answer_text"));
        answerInput2.setAnswerText(request.getParameter("answer_text2"));
        answerInput3.setAnswerText(request.getParameter("answer_text3"));
        answerInput4.setAnswerText(request.getParameter("answer_text4"));
        answerInput.setAnswerCondition(request.getParameter("answer_condition"));
        answerInput2.setAnswerCondition(request.getParameter("answer_condition2"));
        answerInput3.setAnswerCondition(request.getParameter("answer_condition3"));
        answerInput4.setAnswerCondition(request.getParameter("answer_condition4"));
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("show.add.test");
        questionID = DAOFactory.getInstance(DBType.ORACLE).getModifiedTest().addQuestion(
            questionInput.getQuestionText().trim(),
            questionInput.getTestID(),
            questionInput.getQuestionType(),
            questionInput.getQuestionMaxPoint(),
            questionInput.getQuestionWeight()
        );
        DAOFactory.getInstance(DBType.ORACLE).getModifiedTest().addAnswer(
            answerInput.getAnswerText(),
            questionID,
            answerInput.isAnswerCondition()
        );
        DAOFactory.getInstance(DBType.ORACLE).getModifiedTest().addAnswer(
                answerInput2.getAnswerText(),
                questionID,
                answerInput2.isAnswerCondition()
        );
        DAOFactory.getInstance(DBType.ORACLE).getModifiedTest().addAnswer(
                answerInput3.getAnswerText(),
                questionID,
                answerInput3.isAnswerCondition()
        );
        DAOFactory.getInstance(DBType.ORACLE).getModifiedTest().addAnswer(
                answerInput4.getAnswerText(),
                questionID,
                answerInput4.isAnswerCondition()
        );
        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {

    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }
}
