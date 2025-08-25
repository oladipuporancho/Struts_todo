package com.struts.todo.action;

import com.opensymphony.xwork2.ActionSupport;
import com.struts.todo.dao.UserDao;

public class DeleteUserAction extends ActionSupport {
    private String email;
    private String message;
    private final UserDao userDao = new UserDao();

    @Override
    public String execute() {
        if (email == null || email.trim().isEmpty()) {
            message = "Email is required.";
            return ERROR;
        }

        email = email.trim();
        if (userDao.deleteUserByEmail(email)) {
            message = "User with email '" + email + "' deleted successfully.";
            return SUCCESS;
        } else {
            message = "User with email '" + email + "' not found!";
            return ERROR;
        }
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMessage() { return message; }
}
