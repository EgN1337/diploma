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

public class ShowTestSolvingPage implements ActionCommand{

    private LoadTestTaskInput inputContent;
    private LoadTestTaskOutput outputContent;
    private TestingProcessInput testingProcessInput;
    private TestingProcessOutput testingProcessOutput;
    List<Pair<Integer, List<Integer>>> listOfQuestions;

    @Override
    public void getAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        inputContent = new LoadTestTaskInput();
        inputContent.setTestId(request.getParameter("test_id"));

        testingProcessInput = new TestingProcessInput();
        //testingProcessInput.setTestID(session.getAttribute("test_id2"));
        testingProcessInput.setTestID(request.getParameter("test_id"));
    }

    @Override
    public String execute() throws Exception {
        String page = MessageManager.getProperty("jsp.solve.test");
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
        }

        testingProcessOutput = new TestingProcessOutput();
        Integer testID = this.testingProcessInput.getTestID();
        listOfQuestions = DAOFactory.getInstance(DBType.ORACLE).getModifiedTest().getQuestionAnswersList(testID);
        if (listOfQuestions == null) {
            throw new TestException("wrongTest", "Test with this id does not exist.");
        } else {
            testingProcessOutput.setQuestionsAnswersList(listOfQuestions);
        }

        return page;
    }

    @Override
    public void setAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("test_task2", outputContent.getTest().getTask());
        session.setAttribute("test_description2", outputContent.getTest().getDescription());
        session.setAttribute("test_id2", inputContent.getTestId());

        for (int index = 0; index < listOfQuestions.size(); index++){
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
        }

        session.setAttribute("question_amount",listOfQuestions.size());
        session.setAttribute("currentValue",1);
        session.setAttribute("currentQuestion",session.getAttribute("question1"));
        session.setAttribute("currentQuestionID",session.getAttribute("question1ID"));
        session.setAttribute("currentAnswer1",session.getAttribute("answer11"));
        session.setAttribute("currentAnswer2",session.getAttribute("answer12"));
        session.setAttribute("currentAnswer3",session.getAttribute("answer13"));
        session.setAttribute("currentAnswer4",session.getAttribute("answer14"));
        session.setAttribute("currentAnswer1ID",session.getAttribute("answer11ID"));
        session.setAttribute("currentAnswer2ID",session.getAttribute("answer12ID"));
        session.setAttribute("currentAnswer3ID",session.getAttribute("answer13ID"));
        session.setAttribute("currentAnswer4ID",session.getAttribute("answer14ID"));

        session.setAttribute("mouthValue", 50);
        session.setAttribute("eyeValue", 50);
        session.setAttribute("eyeBrowsValue", 50);
        session.setAttribute("noseValue", 50);
        session.setAttribute("hairValue", 50);
        session.setAttribute("headValue", 50);


    }

    @Override
    public String getRedirect() {
        return MessageManager.getProperty("redirect.forward");
    }

    private boolean validateInputData (LoadTestTaskInput inputContent){
        return !(inputContent.getTestId() == 0);
    }
}
