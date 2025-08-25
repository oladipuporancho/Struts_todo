package com.struts.todo.action;

import com.opensymphony.xwork2.ActionSupport;
import com.struts.todo.dao.UserDao;
import com.struts.todo.model.User;

import java.util.ArrayList;
import java.util.List;

public class GetUsersAction extends ActionSupport {
    private List<User> users;
    private String email;
    private String message;

    private final UserDao userDao = new UserDao();

    @Override
    public String execute() {
        try {
            users = new ArrayList<>();

            if (email != null && !email.trim().isEmpty()) {
                User user = userDao.findUserByEmail(email.trim());
                if (user != null) {
                    users.add(user);
                    message = "User fetched successfully";
                    return SUCCESS;
                } else {
                    message = "User with email '" + email + "' not found";
                    return ERROR;
                }
            } else {
                users = userDao.findAllUsers();
                message = "All users fetched successfully";
                return SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Error fetching users: " + e.getMessage();
            return ERROR;
        }
    }

    public List<User> getUsers() { return users; }
    public String getMessage() { return message; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
