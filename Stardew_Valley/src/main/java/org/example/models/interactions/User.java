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
    private Boolean isPlaying;

    public User(String username, String password, String nickname, String email, String gender, int questionNumber, String answer) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.questionNumber = questionNumber;
        this.answer = answer;
        this.earnedPoints = 0;
        this.game = null;
        this.isPlaying = false;
    }

    public void changeUsername(String username) {
        this.username = username;
    }

    public void changeGender(String gender) {
        this.gender = gender;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public Boolean getPlaying() {
        return isPlaying;
    }
    public void setPlaying(Boolean playing) {
        isPlaying = playing;
    }

    public Game getGame() {
        return game;
    }

    public int getEarnedPoints() {
        return earnedPoints;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getAnswer() {
        return answer;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
