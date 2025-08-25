package com.struts.todo.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoUtil {
    private static final String CONNECTION_STRING = "mongodb+srv://Strutproject:ola123@cluster0.eylzobk.mongodb.net/?retryWrites=true&w=majority";
    private static final String DB_NAME = "strutsTodoDB";
    private static MongoClient mongoClient;

    static {
        try {
            mongoClient = MongoClients.create(CONNECTION_STRING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MongoDatabase getDatabase() {
        return mongoClient.getDatabase(DB_NAME);
    }
}
