package org.example.models;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.models.game_structure.Farm;
import org.example.models.game_structure.Game;
import org.example.models.interactions.Gender;
import org.example.models.interactions.User;

import java.util.ArrayList;
import java.util.List;

public class DBInteractor {

    public static void saveUser(){

        String connectionString = "mongodb+srv://namoder123:passme@cluster01.unmuffl.mongodb.net/?retryWrites=true&w=majority&appName=Cluster01";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("Game");
            MongoCollection<Document> collection = database.getCollection("USERS");

            try {
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException e) {
                e.printStackTrace();
            }


            for (User user : App.getUsers() ) {
                Document userDoc = new Document()
                        .append("username", user.getUsername())
                        .append("password", user.getPassword())
                        .append("nickname", user.getNickname())
                        .append("email", user.getEmail())
                        .append("gender", user.getGender())
                        .append("questionNumber", user.getQuestionNumber())
                        .append("answer", user.getAnswer())
                        .append("setPlaying",user.getPlaying())
                        .append("maxPoints",user.getMaxPoints())
                        .append("gamePlay",user.getGamePlay())
                        .append("earnedPoints",user.getEarnedPoints())
                        .append("stayLogin",user.isStayLogin());
                collection.insertOne(userDoc);

            }

//            System.out.println("َUse inserted: " + userDoc.toJson());
//            System.out.println("\nAll Users in collection:");
//            for (Document doc : collection.find()) {
//                System.out.println(doc.toJson());
//
//            }

        } catch (MongoException e) {
            System.out.println("Oh-No !! Something went wrong!");
        }

    }

    public static void loadUsers() {
        String connectionString = "mongodb+srv://namoder123:passme@cluster01.unmuffl.mongodb.net/?retryWrites=true&w=majority&appName=Cluster01";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("Game");
            MongoCollection<Document> collection = database.getCollection("USERS");

            List<Document> documents = collection.find().into(new ArrayList<>());

            if (documents.isEmpty()) {
                System.out.println("No users found in the database.");
            } else {
                App.getUsers().clear();

                for (Document doc : documents) {
                    User newUser = new User(null, null, null, null, null, 0, null);
                    newUser.setUsername(doc.getString("username"));
                    newUser.setPassword(doc.getString("password"));
                    newUser.setNickname(doc.getString("nickname"));
                    newUser.setEmail(doc.getString("email"));
                    newUser.setGender(Gender.valueOf(doc.getString("gender")));
                    newUser.setQuestionNumber(doc.getInteger("questionNumber"));
                    newUser.setAnswer(doc.getString("answer"));
                    newUser.setPlaying(doc.getBoolean("setPlaying"));
                    newUser.setMaxPoints(doc.getInteger("maxPoints"));
                    newUser.setGamePlay(doc.getInteger("gamePlay"));
                    newUser.setEarnedPoints(doc.getInteger("earnedPoints"));
                    newUser.setStayLogin(doc.getBoolean("stayLogin"));

                    // بررسی stayLogin
                    if (newUser.isStayLogin()) {
                        System.out.println("You are logged in as " + newUser.getUsername());
                        App.setCurrentUser(newUser);
                    }

                    // اضافه کردن کاربر به لیست
                    App.getUsers().add(newUser);
                }
            }
        } catch (Exception e) {
            System.out.println("Error while loading users from the database.");
            e.printStackTrace();
        }
    }

}
