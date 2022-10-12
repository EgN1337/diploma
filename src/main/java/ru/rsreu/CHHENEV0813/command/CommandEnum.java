package ru.rsreu.CHHENEV0813.command;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    GUEST_PAGE{
        {
            this.command = new GuestPageCommand();
        }
    },
    GUEST_REDIRECT{
        {
            this.command = new RedirectCommand();
        }
    },
    ADMIN_PAGE_LOAD_USERS{
        {
            this.command = new LoadUserListForAdminCommand();
        }
    },
    ADMIN_PAGE_ADD_USER{
        {
            this.command = new AddUserFromAdminCommand();
        }
    },
    ADMIN_PAGE_EDIT_USER{
        {
            this.command = new EditUserFromAdminCommand();
        }
    },
    ADMIN_PAGE_DELETE_USER{
        {
            this.command = new DeleteUserFromAdminCommand();
        }
    },
    MODER_PAGE_LOAD_USERS{
        {
            this.command = new LoadUserListFromModerCommand();
        }
    },
    MODER_PAGE_EDIT_USER{
        {
            this.command = new EditUserStatusCommand();
        }
    },
    TEACHER_PAGE_LOAD_TESTS{
        {
            this.command = new LoadAllTestsCommand();
        }
    },
    TEACHER_PAGE_LOAD_PASSED{
        {
            this.command = new LoadPassedUsersListCommand();
        }
    },
    TEACHER_PAGE_LOAD_UNPASSED{
        {
            this.command = new LoadUnpassedUsersListCommand();
        }
    },
    TEACHER_PAGE_LOAD_INVALUABLE{
        {
            this.command = new LoadInvaluableTestsListCommand();
        }
    },
    TEACHER_PAGE_LOAD_UNSTARTED{
        {
            this.command = new LoadUnstartedStudentsListCommand();
        }
    },
    TEACHER_PAGE_ADD_TEST{
        {
            this.command = new CreateTestCommand();
        }
    },
    TEACHER_PAGE_CHECK_TEST{
        {
            this.command = new CheckTestCommand();
        }
    },
    TEACHER_PAGE_ASSESS_TEST{
        {
            this.command = new AssessTestCommand();
        }
    },
    STUDENT_PAGE_LOAD_PASSED{
        {
            this.command = new LoadStudentPassedTestsCommand();
        }
    },
    STUDENT_PAGE_LOAD_UNPASSED{
        {
            this.command = new LoadStudentUnpassedTestsCommand();
        }
    },
    STUDENT_PAGE_LOAD_UNCHECKED{
        {
            this.command = new LoadStudentUncheckedTestsCommand();
        }
    },
    STUDENT_PAGE_LOAD_UNSOLVED{
        {
            this.command = new LoadStudentUnsolvedTestsCommand();
        }
    },
    STUDENT_PAGE_LOAD_TASK{
        {
            this.command = new LoadTestTaskCommand();
        }
    },
    STUDENT_PAGE_ADD_SOLUTION{
        {
            this.command = new AddSolutionToTestCommand();
        }
    },
    SHOW_BLOCKED_PAGE{
        {
            this.command = new ShowBlockedPageCommand();
        }
    },
    SHOW_TEST_ADDING_PAGE{
        {
            this.command = new ShowTestAddingPage();
        }
    },
    TEACHER_PAGE_CREATE_QUESTION{
        {
            this.command = new CreateQuestionCommand();
        }
    },
    STUDENT_PAGE_SOLVE_TEST{
        {
            this.command = new ShowTestSolvingPage();
        }
    },
    STUDENT_LOAD_TEST_PAGE{
        {
            this.command = new TestingProcessCommand();
        }
    },
    STUDENT_SOLVE_TEST{
        {
            this.command = new SolveTestCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
