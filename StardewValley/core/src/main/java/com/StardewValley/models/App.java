package com.StardewValley.models;

import com.StardewValley.models.enums.Menu;
import com.StardewValley.models.game_structure.Game;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.models.interactions.User;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;

import java.util.ArrayList;
import java.util.Arrays;

public class App {
    private final static ArrayList<User> users = new ArrayList<>();

    private static Menu currentMenu = null;

    private static User currentUser = null;

    private static Game currentGame = null;

//    Game Graphics Fields
    private static Music backgroundMusic;

    private static Cursor cursor;

    private static ArrayList<String> securityQuestions = new ArrayList<>(Arrays.asList(
            "What was the make and model of your first car?",
            "What city were you born in?",
            "What is your favorite movie of all time?"
    ));

    private final static ArrayList<Game> games = new ArrayList<>();

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }


    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }

    public static void setCurrentGame(Game currentGame) {
        App.currentGame = currentGame;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static ArrayList<String> getSecurityQuestions() {
        return securityQuestions;
    }

    public static void startGame() {
//        MusicPlayer player = new MusicPlayer("src/main/java/org/example/stardew.wav");
//
//        // اجرای پخش در یک Thread جداگانه
//        Thread musicThread = new Thread(player);
//        musicThread.setDaemon(true); // اگر برنامه تموم شه، این Thread هم بسته میشه
//        musicThread.start();


        try {
//            DBInteractor.loadUsers();
        } catch (Exception e) {
            System.out.println("Oh-No! Something went wrong while loading users!");
        }
        if (App.getCurrentUser() != null) {
//            App.setCurrentMenu(Menu.MainMenu);
        }
    }

    public static ArrayList<Game> getGames() {
        return games;
    }

    public static Game findGame(User user) {
        for (Game game : games) {
            for (Player player : game.getPlayers()) {
                if(player.getUser().getUsername().equals(user.getUsername())) {
                    return game;
                }
            }
        }
        return null;
    }

    public static Music getBackgroundMusic() {
        return backgroundMusic;
    }

    public static void setBackgroundMusic(Music backgroundMusic) {
        App.backgroundMusic = backgroundMusic;
        App.getBackgroundMusic().setLooping(true);
        App.getBackgroundMusic().setVolume(0.5f);
        App.getBackgroundMusic().play();
    }

    public static void setCursor() {
        Pixmap pixmap = new Pixmap(Gdx.files.internal("game_cursor_32.png"));
        int xHotspot = 0;
        int yHotspot = 0;

        cursor = Gdx.graphics.newCursor(pixmap, xHotspot, yHotspot);
        Gdx.graphics.setCursor(cursor);
        pixmap.dispose();
    }

    public static void setCursorFromImage(String imagePath) {
        Pixmap original = new Pixmap(Gdx.files.internal(imagePath));

        int width = original.getWidth();
        int height = original.getHeight();

        // Find next power of two for width and height
        int pow2Width = Integer.highestOneBit(width);
        if (pow2Width < width) pow2Width <<= 1;

        int pow2Height = Integer.highestOneBit(height);
        if (pow2Height < height) pow2Height <<= 1;

        Pixmap pixmap;

        if (width == pow2Width && height == pow2Height) {
            // Already power-of-two size, use as is
            pixmap = original;
        } else {
            // Resize to power-of-two pixmap
            pixmap = new Pixmap(pow2Width, pow2Height, original.getFormat());
            pixmap.drawPixmap(original, 0, 0);
            original.dispose();
        }

        int xHotspot = pixmap.getWidth() / 2;  // Center hotspot
        int yHotspot = pixmap.getHeight() / 2;

        if (cursor != null) {
            cursor.dispose();
        }

        cursor = Gdx.graphics.newCursor(pixmap, xHotspot, yHotspot);
        Gdx.graphics.setCursor(cursor);

        pixmap.dispose();
    }

}
