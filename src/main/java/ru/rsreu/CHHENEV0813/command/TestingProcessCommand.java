package ru.rsreu.CHHENEV0813.command;

import javafx.util.Pair;
import ru.rsreu.CHHENEV0813.command.content.input.LoadTestTaskInput;
import ru.rsreu.CHHENEV0813.command.content.input.TestingProcessInput;
import ru.rsreu.CHHENEV0813.command.content.output.LoadTestTaskOutput;
import ru.rsreu.CHHENEV0813.command.content.output.TestingProcessOutput;
import ru.rsreu.CHHENEV0813.exceptions.TestException;
import ru.rsreu.CHHENEV0813.factory.DAOFactory;
import ru.rsreu.CHHENEV0813.factory.DBType;
import ru.rsreu.CHHENEV0813.models.Test;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class TestingProcessCommand implements ActionCommand{
    /*private TestingProcessInput testingProcessInput;
    private TestingProcessOutput testingProcessOutput;

    List<Pair<Integer, List<Integer>>> listOfQuestions;*/

    @Override
    public void getAttributes(HttpServletRequest request) {
        /*HttpSession session = request.getSession(true);
        testingProcessInput = new TestingProcessInput();
        testingProcessInput.setTestID(session.getAttribute("test_id2"));*/
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("jsp.solve.test");

        /*testingProcessOutput = new TestingProcessOutput();
        Integer testID = this.testingProcessInput.getTestID();
        listOfQuestions = DAOFactory.getInstance(DBType.ORACLE).getModifiedTest().getQuestionAnswersList(testID);
        if (listOfQuestions == null) {
            throw new TestException("wrongTest", "Test with this id does not exist.");
        } else {
            testingProcessOutput.setQuestionsAnswersList(listOfQuestions);
        }*/

        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        /*HttpSession session = request.getSession(true);*/

        //session.setAttribute("test_id", testingProcessInput);
        /*for (int index = 0; index < listOfQuestions.size(); index++){
            Pair<Integer, List<Integer>> pairQuestionAnswers = listOfQuestions.get(index);
            int question = pairQuestionAnswers.getKey();
            List<Integer> answers = pairQuestionAnswers.getValue();
            String stringIndex = Integer.toString(index+1);
            session.setAttribute("question" + stringIndex, DAOFactory.getInstance(DBType.ORACLE).getModifiedTest().getQuestionText(question));
            session.setAttribute("question" + stringIndex + "ID", question);
            for (int jndex = 0; jndex < answers.size(); jndex++){
                String answerIndex = Integer.toString(jndex + 1);
                session.setAttribute("answer" + stringIndex + answerIndex, DAOFactory.getInstance(DBType.ORACLE).getModifiedTest().getAnswerText(answers.get(jndex)));
                session.setAttribute("answer" + stringIndex + answerIndex + "ID", answers.get(jndex));
            }
        }*/
     }

    @Override
    public String getRedirect() {
        return null /*MessageManager.getProperty("redirect.forward")*/;
    }


}
