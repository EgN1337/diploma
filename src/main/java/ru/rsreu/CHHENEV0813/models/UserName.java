package ru.rsreu.CHHENEV0813.models;

/**
 * UserName class contains first name, surname and third name to simplify obtaining this user information.
 */
public class UserName {
    private String firstName;
    private String surName;
    private String thirdName;

    public UserName(String firstName, String surName, String thirdName) {
        this.firstName = firstName;
        this.surName = surName;
        this.thirdName = thirdName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public String getSurName() {
        return surName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }
}
