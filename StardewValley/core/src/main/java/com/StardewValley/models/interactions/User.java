package com.StardewValley.models.interactions;

import com.StardewValley.models.game_structure.Game;


public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Gender gender;
    private int questionNumber;
    private String answer;
    private boolean isPlaying;
    private int earnedPoints;
    private int maxPoints;
    private int gamePlay;
    private boolean stayLogin;

    public User(String username, String password, String nickname, String email, Gender gender, int questionNumber, String answer) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.questionNumber = questionNumber;
        this.answer = answer;
        this.earnedPoints = 0;
        this.isPlaying = false;
        this.maxPoints = 0;
        this.gamePlay = 0;
        this.stayLogin = false;
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

    public Gender getGender() {
        return gender;
    }

    public Boolean getPlaying() {
        return isPlaying;
    }
    public void setPlaying(Boolean playing) {
        isPlaying = playing;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEarnedPoints(int earnedPoints) {
        this.earnedPoints = earnedPoints;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public int getGamePlay() {
        return gamePlay;
    }

    public void setGamePlay(int gamePlay) {
        this.gamePlay = gamePlay;
    }

    public String showInfo() {
        String info = "";
        info += "Username: " + this.username + "\n";
        info += "Nickname: " + this.nickname + "\n";
        info += "MaxPoints: " + this.maxPoints + "\n";
        info += "GamePlay: " + this.gamePlay + "\n";
        return info;
    }

    public void increaseEarnedPoints(int points) {
        this.earnedPoints += points;
    }

    public void increaseGamePlay(int time) {
        this.gamePlay += time;
    }

    public void maxMaxPoints(int points) {
        this.maxPoints = Math.max(this.maxPoints, points);
    }

    public boolean isStayLogin() {
        return stayLogin;
    }

    public void setStayLogin(boolean stayLogin) {
        this.stayLogin = stayLogin;
    }
}
