package com.StardewValley.models;

import com.StardewValley.models.interactions.Gender;
import com.StardewValley.models.interactions.User;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class DBInteractor {

    public static ArrayList<User> databaseUsers = new ArrayList<>();

//    public static void saveUsers() {
//
//        String connectionString = "mongodb+srv://namoder123:passme@cluster01.unmuffl.mongodb.net/?retryWrites=true&w=majority&appName=Cluster01";
//
//        ServerApi serverApi = ServerApi.builder()
//                .version(ServerApiVersion.V1)
//                .build();
//
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(new ConnectionString(connectionString))
//                .serverApi(serverApi)
//                .build();
//
//        try (MongoClient mongoClient = MongoClients.create(settings)) {
//            MongoDatabase database = mongoClient.getDatabase("Game");
//            MongoCollection<Document> collection = database.getCollection("USERS");
//
//            try {
//                database.runCommand(new Document("ping", 1));
//                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
//            } catch (MongoException e) {
//                e.printStackTrace();
//            }
//
//
//            for (User user : App.getUsers()) {
//                if (!databaseUsers.contains(user)) {
//                    Document userDoc = new Document()
//                            .append("username", user.getUsername())
//                            .append("password", user.getPassword())
//                            .append("nickname", user.getNickname())
//                            .append("email", user.getEmail())
//                            .append("gender", user.getGender())
//                            .append("questionNumber", user.getQuestionNumber())
//                            .append("answer", user.getAnswer())
//                            .append("setPlaying", user.getPlaying())
//                            .append("maxPoints", user.getMaxPoints())
//                            .append("gamePlay", user.getGamePlay())
//                            .append("earnedPoints", user.getEarnedPoints())
//                            .append("stayLogin", user.isStayLogin());
//                    collection.insertOne(userDoc);
//                }
//            }
//
//        } catch (MongoException e) {
//            System.out.println("Oh-No !! Something went wrong!");
//        }
//
//    }
//
//    public static void loadUsers() {
//        String connectionString = "mongodb+srv://namoder123:passme@cluster01.unmuffl.mongodb.net/?retryWrites=true&w=majority&appName=Cluster01";
//
//        ServerApi serverApi = ServerApi.builder()
//                .version(ServerApiVersion.V1)
//                .build();
//
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(new ConnectionString(connectionString))
//                .serverApi(serverApi)
//                .build();
//
//        try (MongoClient mongoClient = MongoClients.create(settings)) {
//            MongoDatabase database = mongoClient.getDatabase("Game");
//            MongoCollection<Document> collection = database.getCollection("USERS");
//
//            List<Document> documents = collection.find().into(new ArrayList<>());
//
//            if (documents.isEmpty()) {
//                System.out.println("No users found in the database.");
//            } else {
//                App.getUsers().clear();
//
//                for (Document doc : documents) {
//                    User newUser = new User(null, null, null, null, null, 0, null);
//                    newUser.setUsername(doc.getString("username"));
//                    newUser.setPassword(doc.getString("password"));
//                    newUser.setNickname(doc.getString("nickname"));
//                    newUser.setEmail(doc.getString("email"));
//                    newUser.setGender(Gender.valueOf(doc.getString("gender")));
//                    newUser.setQuestionNumber(doc.getInteger("questionNumber"));
//                    newUser.setAnswer(doc.getString("answer"));
//                    newUser.setPlaying(doc.getBoolean("setPlaying"));
//                    newUser.setMaxPoints(doc.getInteger("maxPoints"));
//                    newUser.setGamePlay(doc.getInteger("gamePlay"));
//                    newUser.setEarnedPoints(doc.getInteger("earnedPoints"));
//                    newUser.setStayLogin(doc.getBoolean("stayLogin"));
//
//                    if (newUser.isStayLogin()) {
//                        System.out.println("You are logged in as " + newUser.getUsername());
//                        App.setCurrentUser(newUser);
//                    }
//                    databaseUsers.add(newUser);
//                    App.getUsers().add(newUser);
//                }
//                System.out.println("Users loaded successfully.");
//            }
//        } catch (Exception e) {
//            System.out.println("Error while loading users from the database.");
//        }
//    }
//
//    public static void resetStayLogin() {
//
//        String connectionString = "mongodb+srv://namoder123:passme@cluster01.unmuffl.mongodb.net/?retryWrites=true&w=majority&appName=Cluster01";
//
//        ServerApi serverApi = ServerApi.builder()
//                .version(ServerApiVersion.V1)
//                .build();
//
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(new ConnectionString(connectionString))
//                .serverApi(serverApi)
//                .build();
//
//        try (MongoClient mongoClient = MongoClients.create(settings)) {
//            MongoDatabase database = mongoClient.getDatabase("Game");
//            MongoCollection<Document> collection = database.getCollection("USERS");
//
//            Bson filter = Filters.eq("username", App.getCurrentUser().getUsername());
//
//            Bson update = Updates.set("stayLogin", false);
//
//            UpdateResult result = collection.updateOne(filter, update);
//
//            if (result.getModifiedCount() <= 0) {
//                System.out.println("User not found or no changes made.");
//            }
//        } catch (Exception e) {
//            System.out.println("Error while updating user.");
//        }
//    }
//
//    public static void changeUserInDatabase(String phrase , String whichChange) {
//
//        String connectionString = "mongodb+srv://namoder123:passme@cluster01.unmuffl.mongodb.net/?retryWrites=true&w=majority&appName=Cluster01";
//
//        ServerApi serverApi = ServerApi.builder()
//                .version(ServerApiVersion.V1)
//                .build();
//
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(new ConnectionString(connectionString))
//                .serverApi(serverApi)
//                .build();
//
//        try (MongoClient mongoClient = MongoClients.create(settings)) {
//            MongoDatabase database = mongoClient.getDatabase("Game");
//            MongoCollection<Document> collection = database.getCollection("USERS");
//
//            Bson filter ;
//            if (whichChange.equals("username")) {
//                filter = Filters.eq("nickname", App.getCurrentUser().getNickname());
//            } else {
//                filter = Filters.eq("username", App.getCurrentUser().getUsername());
//            }
//
//            Bson update = Updates.set(whichChange, phrase);
//
//            UpdateResult result = collection.updateOne(filter, update);
//
//            if (result.getModifiedCount() <= 0) {
//                System.out.println("User not found or no changes made.");
//            }
//        } catch (Exception e) {
//            System.out.println("Error while updating user.");
//        }
//    }
//
//    public static void setStayLogin() {
//
//        String connectionString = "mongodb+srv://namoder123:passme@cluster01.unmuffl.mongodb.net/?retryWrites=true&w=majority&appName=Cluster01";
//
//        ServerApi serverApi = ServerApi.builder()
//                .version(ServerApiVersion.V1)
//                .build();
//
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(new ConnectionString(connectionString))
//                .serverApi(serverApi)
//                .build();
//
//        try (MongoClient mongoClient = MongoClients.create(settings)) {
//            MongoDatabase database = mongoClient.getDatabase("Game");
//            MongoCollection<Document> collection = database.getCollection("USERS");
//
//            Bson filter = Filters.eq("username", App.getCurrentUser().getUsername());
//
//            Bson update = Updates.set("stayLogin", true);
//
//            UpdateResult result = collection.updateOne(filter, update);
//
//            if (result.getModifiedCount() <= 0) {
//                System.out.println("User not found or no changes made.");
//            }
//        } catch (Exception e) {
//            System.out.println("Error while updating user.");
//        }
//    }
}
