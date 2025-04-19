package org.example.models.interactions;

import org.example.models.game_structure.Game;

import java.util.ArrayList;

public class User {
    private Game game;
    private int earnedPoints;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String gender;
    private int questionNumber;
    private String answer;

    private static ArrayList<String> securityQuestions = new ArrayList<>();

}
