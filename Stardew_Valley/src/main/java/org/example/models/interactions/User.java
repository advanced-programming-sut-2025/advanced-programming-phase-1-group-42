package org.example.models.interactions;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String gender;
    private Boolean isPlaying;

    public User(String username , String password, String nickname, String email, String gender) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
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
}
