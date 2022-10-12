package ru.rsreu.CHHENEV0813.command.content.output;

import ru.rsreu.CHHENEV0813.models.PassedStudent;

import java.util.List;

public class LoadUnpassedUsersListOutput {
    private List<PassedStudent> unPassedStudent;

    public List<PassedStudent> getPassedStudent() {
        return unPassedStudent;
    }

    public void setPassedStudent(List<PassedStudent> passedStudent) {
        this.unPassedStudent = passedStudent;
    }

}
