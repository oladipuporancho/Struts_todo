package com.struts.todo.action;

import com.opensymphony.xwork2.ActionSupport;
import com.struts.todo.util.MongoUtil;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class LoginAction extends ActionSupport {
    private String username;
    private String password;
    private String message;

    @Override
    public String execute() {
        try {
            MongoCollection<Document> collection = MongoUtil.getDatabase().getCollection("users");

            Document user = collection.find(
                new Document("username", username).append("password", password)
            ).first();

            if (user != null) {
                this.username = user.getString("username");
                message = "You are logged in successfully, " + this.username;
                return SUCCESS;
            } else {
                message = "Invalid username or password";
                return ERROR;
            }

        } catch (Exception e) {
            e.printStackTrace();
            message = "Login failed: " + e.getMessage();
            return ERROR;
        }
    }


    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }

    public String getMessage() { return message; }
    public String getUsername() { return username; }
}
