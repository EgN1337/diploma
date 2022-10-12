package ru.rsreu.CHHENEV0813.command.content.output;

import ru.rsreu.CHHENEV0813.models.UncheckedTest;

import java.util.List;

public class LoadStudentUnsolvedTestsOutput {
    List<UncheckedTest> uncheckedTestList;

    public List<UncheckedTest> getUncheckedTestList() {
        return uncheckedTestList;
    }

    public void setUncheckedTestList(List<UncheckedTest> uncheckedTestList) {
        this.uncheckedTestList = uncheckedTestList;
    }
}
