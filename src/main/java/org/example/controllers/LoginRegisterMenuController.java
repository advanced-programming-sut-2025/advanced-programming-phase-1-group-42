package org.example.controllers;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.models.App;
import org.example.models.Result;
import org.example.models.enums.LoginRegisterCommands;
import org.example.models.enums.Menu;
import org.example.models.game_structure.Game;
import org.example.models.goods.products.Product;
import org.example.models.interactions.Gender;
import org.example.models.interactions.User;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginRegisterMenuController extends Controller {

    public void saveUsers() {
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

            User user = new User("MarmotLover", "passme", "mamad", "Email@a", null, 2, "np");
            user.setPlaying(true);

            Document userDoc = new Document()
                    .append("username", user.getUsername())
                    .append("password", user.getPassword())
                    .append("nickname", user.getNickname())
                    .append("email", user.getEmail())
                    .append("gender", user.getGender())
                    .append("questionNumber", user.getQuestionNumber())
                    .append("answer", user.getAnswer());


//            // 4. درج سند در MongoDB
            collection.insertOne(userDoc);
            System.out.println("َUse inserted: " + userDoc.toJson());
//
            System.out.println("\nAll Users in collection:");
            for (Document doc : collection.find()) {
                System.out.println(doc.toJson());

                // تبدیل Document به Person (اختیاری)
//                User retrievedPerson = new Person();
//                retrievedPerson.setName(doc.getString("name"));
//                retrievedPerson.setAge(doc.getInteger("age"));
//                retrievedPerson.setEmail(doc.getString("email"));
//                System.out.println("As Person object: " + retrievedPerson);
            }

        } catch (MongoException e) {
            System.out.println("chi migi");
        }
    }

    public Result exit() {
        App.setCurrentMenu(Menu.ExitMenu);
        return new Result(true, "Goodbye!");
    }

    public Result showCurrentMenu() {
        return new Result(true, "Current Menu : Login/Register Menu");
    }

    public Result register(String username, String password, String nickname, String email,
                           String gender, Scanner scanner) {

        User user = findAppUser(username);

        // Check Username Existence
        if (user != null) {
            System.out.println("User already exists");
            username += "-";
            Random random = new Random();
            for (int i = 0; i < 3; i++) {
                username += Integer.toString(random.nextInt(10));
            }

            System.out.println("Do you confirm this username to continue? (y/n)");
            String confirm = scanner.nextLine();
            if (!confirm.equals("n")) {
                return new Result(false, "Ok, Try again later!");
            }
        }

        // Checking Username format
        if (!username.matches("[A-Za-z0-9\\-]")) {
            return new Result(false, "Invalid username format!");
        }

        // Checking Email Format
        if (!checkEmailFormat(email)) {
            return new Result(false, "Invalid email format!");
        }

        // Checking Password Format
        if (password.matches("RANDOM_PASSWORD")) {
            while (true) {
                String rPassword = generateRandomPassword();
                System.out.println("Use this Random Password for your Password? (y/n/quit)");
                String input = scanner.nextLine();
                if (input.equals("y")) {
                    password = rPassword;
                    break;
                } else if (input.equals("quit")) {
                    return new Result(false, "Redirecting to Login/Register Menu...");
                }
            }
        } else {
            Matcher matcher = Pattern.compile("(?<password>\\S+)\\s+(?<confirmPassword>\\S+)").matcher(password);
            password = matcher.group("password");
            String confirmPassword = matcher.group("confirmPassword");

            if (!password.matches("[a-zA-Z0-9?><,\"'\\\\;:/|\\]\\[}{+=)(*&^%$#!]+"))
                return new Result(false, "Invalid password format!");
            if (!checkPasswordStrength(password).success())
                return new Result(false, checkPasswordStrength(password).toString());

            if (!password.equals(confirmPassword)) {
                String input = "";
                while (true) {
                    System.out.println("Re-Enter your password : (Enter 'quit' to back to Login/Register Menu)");
                    input = scanner.nextLine();
                    if (input.equals(password)) {
                        confirmPassword = input;
                        break;
                    }
                    if (input.equals("quit")) {
                        return new Result(false, "Redirecting to Login/Register Menu...");
                    }
                }
            }
        }


        System.out.println("Select the number of security_question you want to be asked :");
        for (int i = 0; i < App.getSecurityQuestions().size(); i++) {
            System.out.println((i + 1) + ". " + App.getSecurityQuestions().get(i));
        }

        Matcher matcher = LoginRegisterCommands.PickQuestion.matcher(scanner.nextLine());
        if (matcher == null)
            return new Result(false, "Invalid pick question format!");

        String number = matcher.group("questionNumber").trim();
        int num = 0;
        try {
            num = Integer.parseInt(number);
            if (num > 3 || num < 1)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            return new Result(false, "Security question number is invalid!");
        }

        String answer = matcher.group("answer").trim();
        App.getUsers().add(new User(username, password, nickname, email, Gender.findGender(gender), num, answer));
        return new Result(true, "Your account has been successfully registered!");
    }

    public Result login(String username, String password, boolean stayLoggedIn) {
        User user = findAppUser(username);
        if (user == null) {
            return new Result(false, "User not found!");
        }

        if (!user.getPassword().equals(password)) {
            return new Result(false, "Wrong password!");
        }


        if (stayLoggedIn) {
            //TODO
        }

        App.setCurrentUser(user);
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "You logged in successfully! Redirecting to Main Menu...");
    }

    public Result forgetPassword(String username, Scanner scanner) {
        User user = findAppUser(username);
        if (user == null) {
            return new Result(false, "User not found!");
        }

        System.out.println(App.getSecurityQuestions().get(user.getQuestionNumber()));
        String answer = scanner.nextLine();
        Matcher matcher = LoginRegisterCommands.AnswerQuestion.matcher(answer);
        if (matcher == null || !matcher.group("answer").trim().equals(user.getAnswer()))
            return new Result(false, "Wrong answer to security question!");

        System.out.println("Choose how to you want to reset your password? (1/2)\n1. Random Password\n2. Entering Password");
        int number = 2;
        try {
            number = Integer.parseInt(scanner.nextLine());
            if (number >= 3 || number < 1)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            return new Result(false, "Wrong number format!");
        }

        String newPassword = "";
        if (number == 1) {
            while (true) {
                String rPassword = generateRandomPassword();
                System.out.println("Use this Random Password for your Password? (y/n)");
                String input = scanner.nextLine();
                if (input.equals("y")) {
                    newPassword = rPassword;
                    break;
                }
            }
        } else {
            System.out.println("Enter your new password:");
            newPassword = scanner.nextLine();
        }

        user.setPassword(newPassword);
        return new Result(true, "Your Password has been successfully changed!");
    }
}
