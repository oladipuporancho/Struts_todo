package com.struts.todo.action;

import com.opensymphony.xwork2.ActionSupport;
import com.struts.todo.util.MongoUtil;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.model.Filters;
import org.apache.struts2.json.annotations.JSON;

public class SignupAction extends ActionSupport {
    private String username;
    private String email;
    private String password;
    private String message;

    @Override
    public String execute() {
        try {
            MongoCollection<Document> collection = MongoUtil.getDatabase().getCollection("users");

            Document existing = collection.find(Filters.or(
                    Filters.eq("username", username),
                    Filters.eq("email", email)
            )).first();

            if (existing != null) {
                message = "Username or email already exists!";
                return ERROR;
            }

            Document user = new Document("username", username)
                                .append("email", email)
                                .append("password", password);
            collection.insertOne(user);

            message = "Signup successful for user: " + username;
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            message = "Error occurred during signup!";
            return ERROR;
        }
    }

    @JSON(serialize = false)
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    @JSON(serialize = false)
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @JSON(serialize = false)
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getMessage() { return message; }
}
