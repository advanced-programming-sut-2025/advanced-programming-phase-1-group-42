package org.example.models;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.models.game_structure.Farm;
import org.example.models.game_structure.Game;
import org.example.models.interactions.User;

import java.util.ArrayList;

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


    public static void loadUsers(){
        String uri = "mongodb+srv://namoder123:passme@cluster01.unmuffl.mongodb.net/?retryWrites=true&w=majority&appName=Cluster01";

        try {
            MongoDatabase database = MongoClients.create(uri).getDatabase("Game");
            MongoCollection<Document> collection = database.getCollection("USERS");

            Document doc = collection.find().first();
            if (doc != null) {
                User newUser = new User(null,null,null,null,null,0,null);
                newUser.setUsername(doc.getString("username"));
                newUser.setPassword(doc.getString("nickname"));
                newUser.setEmail(doc.getString("email"));
                newUser.setPassword(doc.getString("password"));
                newUser.setPlaying(doc.getBoolean("playing"));
                newUser.setEarnedPoints(doc.getInteger("earnedPoints"));
                newUser.setGamePlay(doc.getInteger(("gamePlay")));
                newUser.setQuestionNumber(doc.getInteger("questionNumber"));
                newUser.setAnswer(doc.getString("answer"));
                newUser.setMaxPoints(doc.getInteger("maxPoints"));
                newUser.setStayLogin(doc.getBoolean("stayLogin"));

                //stay Login
                if (newUser.isStayLogin()) {
                    App.setCurrentUser(newUser);
                }

                App.getUsers().add(newUser);
            } else {
                System.out.println("No documents found");
            }
        } catch (Exception e) {
            System.out.println("Error connecting to the database");
        }
    }
}
