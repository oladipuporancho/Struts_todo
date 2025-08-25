# Struts_todo



 Struts Todo Application

[![Java](https://img.shields.io/badge/Java-8+-blue.svg)](https://www.java.com/)
[![Struts 2](https://img.shields.io/badge/Struts-2.5-orange.svg)](https://struts.apache.org/)
[![MongoDB](https://img.shields.io/badge/MongoDB-Atlas-green.svg)](https://www.mongodb.com/)

A simple Todo/User management application built with Java Struts 2 and MongoDB.
It allows user creation, retrieval, and deletion with JSON API responses.


Features

User Management: Create, fetch, delete users.
Secure: Passwords are never returned in API responses.
Struts 2 Actions: Clean MVC structure for backend logic.
MongoDB Integration: Cloud or local database support.


Requirements

Java 8+
Maven
MongoDB ( Atlas cluster)

 Setup

1. creating the repository:

```echo "# Struts_todo" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/oladipuporancho/Struts_todo.git
git push -u origin main
cd struts-todo
```

2. Update MongoDB URI in `MongoUtil.java`:

```java
mongodb+srv://Strutproject:ola123@cluster0.eylzobk.mongodb.net/?retryWrites=true&w=majority
```

3. Build the project:

bash
mvn clean package


4. Run locally:

bash
java -jar target/struts-todo.war


 API Endpoints

Get all users
GET /getUsers.action

Get a user by email
GET /getUsers.action?email=aaron@gmail.com`

 Delete a user by email
  POST /deleteUser.action
  Body (JSON):

  json
  {
      "email": "aaron@gmail.com
  }
