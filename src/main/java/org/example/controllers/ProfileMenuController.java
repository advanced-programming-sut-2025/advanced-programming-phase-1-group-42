package org.example.controllers;

import org.example.models.App;
import org.example.models.DBInteractor;
import org.example.models.Result;
import org.example.models.enums.Menu;
import org.example.models.interactions.User;

import java.util.Random;
import java.util.Scanner;

public class ProfileMenuController extends Controller {

    public Result changeUsername(String username, Scanner scanner) {
        // Check Username is new
        if(App.getCurrentUser().getUsername().equals(username)) {
            return new Result(false, "Your new username should be different from the current one.");
        }
        // Check username format
        if(!username.matches("[A-Za-z0-9\\-]+")) {
            return new Result(false, "Invalid username format!");
        }

        User user = findAppUser(username);

        // Check Username Existence
        if(user != null) {
            System.out.println("Username already exists");
            username += "-";
            Random random = new Random();
            for (int i = 0; i < 3; i++) {
                username += Integer.toString(random.nextInt(10));
            }

            System.out.println("Do you confirm this username to continue? (y/n)");
            String confirm = scanner.nextLine();
            if(!confirm.equals("n")) {
                return new Result(true, "Ok, Try again later!");
            }
        }

        App.getCurrentUser().setUsername(username);
//        DBInteractor.changeUserInDatabase(username,"username");
        return new Result(true, "Your username was successfully changed to " + username + ".");
    }

    public Result changeNickname(String nickname) {
        // Check nickname is new
        if(App.getCurrentUser().getNickname().equals(nickname)) {
            return new Result(false, "Your nickname should be different from the current one.");
        }

        App.getCurrentUser().setNickname(nickname);
//        DBInteractor.changeUserInDatabase(nickname,"nickname");
        return new Result(true, "Your nickname was successfully changed to " + nickname + ".");
    }

    public Result changeEmail(String email) {
        // Check email is new
        if(App.getCurrentUser().getEmail().equals(email)) {
            return new Result(false, "Your email should be different from the current one.");
        }

        // Checking Email Format
        if(!checkEmailFormat(email)) {
            return new Result(false, "Invalid email format!");
        }

        App.getCurrentUser().setEmail(email);
//        DBInteractor.changeUserInDatabase(email,"email");
        return new Result(true, "Your email was successfully changed to " + email + ".");
    }

    public Result changePassword(String newPassword, String oldPassword) {
        // Check old Password equals user password
        if(!App.getCurrentUser().getPassword().equals(oldPassword)) {
            return new Result(false, "Your old password does not match!");
        }

        // Check Password is new
        if(newPassword.equals(oldPassword)) {
            return new Result(false, "Your password should be different from the current one.");
        }

        // Check Password format
        if(!newPassword.matches("[a-zA-Z0-9?><,\"'\\\\;:/|\\]\\[}{+=)(*&^%$#!]+"))
            return new Result(false, "Invalid newPassword format!");
        // Check Password strength
        if(!checkPasswordStrength(newPassword).success())
            return new Result(false, checkPasswordStrength(newPassword).toString());


        App.getCurrentUser().setPassword(newPassword);
//        DBInteractor.changeUserInDatabase(newPassword,"password");
        return new Result(true, "Your password was successfully changed to " + newPassword + ".");
    }

    public Result userInfo() {
        return new Result(true, App.getCurrentUser().showInfo());
    }

    public Result exit() {
        App.setCurrentMenu(Menu.MainMenu);

        return new Result(true, "Redirecting to main menu!");
    }

    public Result showCurrentMenu() {
        return new Result(true, "Current Menu : Profile Menu");
    }
}
