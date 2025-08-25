package com.struts.todo.dao;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.struts.todo.model.User;
import com.struts.todo.util.MongoUtil;
import org.bson.Document;
import com.mongodb.client.result.DeleteResult;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final MongoCollection<Document> collection;

    public UserDao() {
        collection = MongoUtil.getDatabase().getCollection("users");
    }

    public void saveUser(User user) {
        if (user == null) return;
        Document doc = new Document("username", user.getUsername())
                .append("email", user.getEmail())
                .append("password", user.getPassword());
        collection.insertOne(doc);
    }

    public User findUserByEmail(String email) {
        if (email == null || email.trim().isEmpty()) return null;
        Document doc = collection.find(Filters.eq("email", email.trim())).first();
        if (doc != null) {
            return new User(
                    doc.getString("username"),
                    doc.getString("email"),
                    null // hide password
            );
        }
        return null;
    }

    public boolean deleteUserByEmail(String email) {
        if (email == null || email.trim().isEmpty()) return false;
        DeleteResult result = collection.deleteOne(Filters.eq("email", email.trim()));
        return result.getDeletedCount() > 0;
    }

    public List<User> findAllUsers() {
        List<User> list = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                list.add(new User(
                        doc.getString("username"),
                        doc.getString("email"),
                        null // hide password
                ));
            }
        }
        return list;
    }
}
