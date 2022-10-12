package ru.rsreu.CHHENEV0813.command.content.output;

import javafx.util.Pair;

import java.util.List;

public class TestingProcessOutput {
    private List<Pair<Integer, List<Integer>>> questionsAnswersList;

    public List<Pair<Integer, List<Integer>>> getQuestionsAnswersList() {
        return questionsAnswersList;
    }

    public void setQuestionsAnswersList(List<Pair<Integer, List<Integer>>> questionsAnswersList) {
        this.questionsAnswersList = questionsAnswersList;
    }
}
