package org.example.controllers;

import org.example.models.App;
import org.example.models.Result;
import org.example.models.enums.Menu;
import org.example.models.interactions.Gender;
import org.example.models.interactions.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class LoginRegisterMenuControllerTest {

    private LoginRegisterMenuController controller;
    private Scanner scanner;

    @BeforeEach
    void setUp() {
        controller = new LoginRegisterMenuController();
        scanner = new Scanner(System.in);
        App.getUsers().clear();
    }

    @Test
    void exitCommand_LoginRegisterMenu_ExitsGame() {
        Result result = controller.exit();
        assertTrue(result.success());
        assertEquals(Menu.ExitMenu, App.getCurrentMenu());
        assertEquals("Goodbye!", result.toString());
    }

    @Test
    void showCurrentMenu_ReturnsCorrectMenuName() {
        Result result = controller.showCurrentMenu();
        assertTrue(result.success());
        assertEquals("Current Menu : Login/Register Menu", result.toString());
    }

    @Test
    void register_ValidInput_CreatesNewUser() {
        String username = "testUser";
        String password = "Pass123! Pass123!";
        String nickname = "Test";
        String email = "test@example.com";
        String gender = "Male";

        // Mock scanner input for security question
        String testInput = "1\nTestAnswer\nTestAnswer";
        scanner = new Scanner(testInput);

        Result result = controller.register(username, password, nickname, email, gender, scanner);
        assertTrue(result.success());
        assertEquals("Your account has been successfully registered!", result.toString());

        User registeredUser = (new Controller()).findAppUser(username);
        assertNotNull(registeredUser);
        assertEquals(nickname, registeredUser.getNickname());
        assertEquals(email, registeredUser.getEmail());
    }

    @Test
    void register_DuplicateUsername_SuggestsAlternative() {
        String username = "existingUser";
        String password = "Pass123! Pass123!";
        String nickname = "Existing";
        String email = "existing@example.com";
        String gender = "Female";

        // Add existing user
        App.getUsers().add(new User(username, "hashedPassword", nickname, email, Gender.FEMALE, 1, "answer"));

        // Mock scanner input for username confirmation and security question
        String testInput = "y\n1\nTestAnswer\nTestAnswer";
        scanner = new Scanner(testInput);

        Result result = controller.register(username, password, nickname, email, gender, scanner);
        assertTrue(result.success());
    }

    @Test
    void login_ValidCredentials_LogsInUser() {
        String username = "testUser";
        String password = "Pass123!";
        String hashedPassword = (new Controller()).getSHA256(password);

        App.getUsers().add(new User(username, hashedPassword, "Test", "test@example.com", Gender.MALE, 1, "answer"));

        Result result = controller.login(username, password, null);
        assertTrue(result.success());
        assertEquals("You logged in successfully! Redirecting to Main Menu...", result.toString());
        assertEquals(Menu.MainMenu, App.getCurrentMenu());
        assertEquals(username, App.getCurrentUser().getUsername());
    }

    @Test
    void login_InvalidCredentials_ReturnsError() {
        String username = "nonExistingUser";
        String password = "WrongPass!";

        Result result = controller.login(username, password, null);
        assertFalse(result.success());
        assertEquals("User not found!", result.toString());
    }

    @Test
    void forgetPassword_CorrectSecurityAnswer_ResetsPassword() {
        String username = "testUser";
        String questionAnswer = "TestAnswer";
        User user = new User(username, "oldHash", "Test", "test@example.com", Gender.MALE, 1, questionAnswer);
        App.getUsers().add(user);

        // Mock scanner input for security answer and password reset option
        String testInput = questionAnswer + "\n2\nNewPass123!";
        scanner = new Scanner(testInput);

        Result result = controller.forgetPassword(username, scanner);
        assertTrue(result.success());
        assertEquals("Your Password has been successfully changed!", result.toString());
        assertNotEquals("oldHash", user.getPassword());
    }
}