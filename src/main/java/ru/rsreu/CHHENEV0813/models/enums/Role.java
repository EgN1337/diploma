package ru.rsreu.CHHENEV0813.models.enums;

import ru.rsreu.CHHENEV0813.command.CommandEnum;
import ru.rsreu.CHHENEV0813.resource.MessageManager;

import java.util.EnumSet;

public enum Role {
    ADMIN(0, MessageManager.getProperty("show.admin"), "Администратор"),
    MODERATOR(1, MessageManager.getProperty("show.moder"), "Модератор"),
    TEACHER(2, MessageManager.getProperty("show.teacher"), "Преподаватель"),
    /*TEACHER_EDITOR(2,MessageManager.getProperty("show.addTest"), "Преподаватель_изменятель"),*/
    STUDENT(3, MessageManager.getProperty("show.student"),"Студент"),
    GUEST(4, MessageManager.getProperty("show.login"),"Гость");

    private final int role;
    private final String mainPage;
    private final String description;
    private static EnumSet<CommandEnum> roleCommands;



    Role(int role, String mainPage, String description) {
        this.role = role;
        this.mainPage = mainPage;
        this.description = description;
    }

    public static Role getRoleFromInt(int roleId) {
        for (Role role : Role.values()) {
            if (roleId == role.getRoleId()) {
                return role;
            }
        }
        return Role.GUEST;
    }

    public static Role getIntFromString(int roleId) {
        for (Role role : Role.values()) {
            if (roleId == role.getRoleId()) {
                return role;
            }
        }
        return Role.GUEST;
    }

    public static EnumSet<CommandEnum> adminCommands(){
        EnumSet<CommandEnum> adminCommands;
        adminCommands = EnumSet.of(
                CommandEnum.LOGOUT,
                CommandEnum.ADMIN_PAGE_LOAD_USERS,
                CommandEnum.ADMIN_PAGE_ADD_USER,
                CommandEnum.ADMIN_PAGE_EDIT_USER,
                CommandEnum.ADMIN_PAGE_DELETE_USER,
                CommandEnum.SHOW_BLOCKED_PAGE
        );

        return adminCommands;
    }

    public static EnumSet<CommandEnum> moderCommands(){
        EnumSet<CommandEnum> moderCommands;
        moderCommands = EnumSet.of(
                CommandEnum.LOGOUT,
                CommandEnum.MODER_PAGE_LOAD_USERS,
                CommandEnum.MODER_PAGE_EDIT_USER,
                CommandEnum.SHOW_BLOCKED_PAGE
        );

        return moderCommands;
    }

    public static EnumSet<CommandEnum> teacherCommands(){
        EnumSet<CommandEnum> teacherCommand;
        teacherCommand = EnumSet.of(
                CommandEnum.LOGOUT,
                CommandEnum.TEACHER_PAGE_LOAD_TESTS,
                CommandEnum.TEACHER_PAGE_LOAD_PASSED,
                CommandEnum.TEACHER_PAGE_LOAD_UNPASSED,
                CommandEnum.TEACHER_PAGE_LOAD_INVALUABLE,
                CommandEnum.TEACHER_PAGE_LOAD_UNSTARTED,
                CommandEnum.TEACHER_PAGE_ADD_TEST,
                CommandEnum.TEACHER_PAGE_CHECK_TEST,
                CommandEnum.TEACHER_PAGE_ASSESS_TEST,
                CommandEnum.SHOW_BLOCKED_PAGE,
                CommandEnum.SHOW_TEST_ADDING_PAGE,
                CommandEnum.TEACHER_PAGE_CREATE_QUESTION
        );

        return teacherCommand;
    }

    public static EnumSet<CommandEnum> teacherEditorCommands(){
        EnumSet<CommandEnum> teacherEditorCommand;
        teacherEditorCommand = EnumSet.of(
                CommandEnum.LOGOUT,
                CommandEnum.TEACHER_PAGE_ADD_TEST,
                CommandEnum.SHOW_TEST_ADDING_PAGE,
                CommandEnum.TEACHER_PAGE_CREATE_QUESTION
        );

        return teacherEditorCommand;
    }

    public static EnumSet<CommandEnum> studentCommands(){
        EnumSet<CommandEnum> studentCommand;
        studentCommand = EnumSet.of(
                CommandEnum.LOGOUT,
                CommandEnum.STUDENT_PAGE_LOAD_PASSED,
                CommandEnum.STUDENT_PAGE_LOAD_UNPASSED,
                CommandEnum.STUDENT_PAGE_LOAD_UNCHECKED,
                CommandEnum.STUDENT_PAGE_LOAD_UNSOLVED,
                CommandEnum.STUDENT_PAGE_LOAD_TASK,
                CommandEnum.STUDENT_PAGE_ADD_SOLUTION,
                CommandEnum.SHOW_BLOCKED_PAGE,
                CommandEnum.STUDENT_PAGE_SOLVE_TEST,
                CommandEnum.STUDENT_LOAD_TEST_PAGE,
                CommandEnum.STUDENT_SOLVE_TEST
        );
        return studentCommand;
    }

    public static EnumSet<CommandEnum> guestCommands(){
        EnumSet<CommandEnum> guestCommand;
        guestCommand = EnumSet.of(
                CommandEnum.GUEST_PAGE,
                CommandEnum.LOGIN,
                CommandEnum.LOGOUT,
                CommandEnum.GUEST_REDIRECT
        );
        return guestCommand;
    }

    public int getRoleId() {
        return this.role;
    }

    public String getMainPage() { return this.mainPage; }
    public String getDescription() { return this.description; }

    public static EnumSet<CommandEnum> getRoleCommands(int roleId) {
        if (getRoleFromInt(roleId) == ADMIN) {
            roleCommands = adminCommands();
        } else if (getRoleFromInt(roleId) == MODERATOR){
            roleCommands =  moderCommands();
        } else if (getRoleFromInt(roleId) == TEACHER){
            roleCommands =  teacherCommands();
        /*} else if (getRoleFromInt(roleId) == TEACHER_EDITOR){
            roleCommands =  teacherEditorCommands();*/
        } else if (getRoleFromInt(roleId) == STUDENT){
            roleCommands =  studentCommands();
        } else if (getRoleFromInt(roleId) == GUEST){
            roleCommands =  guestCommands();
        }
        return roleCommands;
    }
}
