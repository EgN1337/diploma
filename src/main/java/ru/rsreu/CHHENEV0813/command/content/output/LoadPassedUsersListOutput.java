package ru.rsreu.CHHENEV0813.command.content.output;

import ru.rsreu.CHHENEV0813.models.PassedStudent;

import java.util.List;

public class LoadPassedUsersListOutput {
    private List<PassedStudent> passedStudent;

    public List<PassedStudent> getPassedStudent() {
        return passedStudent;
    }

    public void setPassedStudent(List<PassedStudent> passedStudent) {
        this.passedStudent = passedStudent;
    }
}
