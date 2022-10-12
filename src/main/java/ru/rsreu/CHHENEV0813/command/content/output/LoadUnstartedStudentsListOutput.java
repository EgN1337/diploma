package ru.rsreu.CHHENEV0813.command.content.output;

import ru.rsreu.CHHENEV0813.models.InvaluableSolutions;

import java.util.List;

public class LoadUnstartedStudentsListOutput {
    private List<InvaluableSolutions> unstartedSolutions;

    public List<InvaluableSolutions> getUnstartedSolutions() {
        return unstartedSolutions;
    }

    public void setUnstartedSolutions(List<InvaluableSolutions> unstartedSolutions) {
        this.unstartedSolutions = unstartedSolutions;
    }
}
