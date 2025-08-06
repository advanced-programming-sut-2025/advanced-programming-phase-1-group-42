package com.StardewValley.server.controllers;

import com.StardewValley.models.Message;
import com.StardewValley.models.Result;
import com.StardewValley.models.interactions.User;
import com.StardewValley.server.AppServer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Controller {
    protected User findAppUser(String username) {
        for (User user : AppServer.getUsers()) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    protected User findAppOnlineUser(String username) {
        for (User user : AppServer.getOnlineUsers()) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    protected boolean checkEmailFormat(String email) {
        // Check email format
        if(email.matches("\\s*([^@]*@){2,}.*") || email.matches("[^@]+"))
            return false;

        Matcher matcher = Pattern.compile("(?<username>\\S+)@(?<domain>\\S+)\\.(?<tld>\\S+)").matcher(email);
        if(!matcher.matches()) {
            return false;
        }

        String username = matcher.group("username");
        String domain = matcher.group("domain");
        String tld = matcher.group("tld");
        // Check email username format
        if(!username.matches("^[a-zA-Z0-9][a-zA-Z0-9.\\-_]*[a-zA-Z0-9]$") || username.matches(".*\\.\\..*")) {
            return false;
        }

        // Check email domain format
        if(!domain.matches("[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9]")) {
            return false;
        }

        // Check email tld format
        if(tld.length() < 2 || tld.matches("[^a-zA-Z0-9\\-]+"))
            return false;

        return true;
    }

    protected Result checkPasswordStrength(String password) {
        if(password.length() < 8) {
            return new Result(false, "Password must be at least 8 characters");
        }

        if(!password.matches(".*\\d.*"))
            return new Result(false, "Password must contain at least one digit");
        if(!password.matches(".*[a-zA-Z].*"))
            return new Result(false, "Password must contain at least one letter");
        if(!password.matches(".*[?><,\"';:/|\\]\\[}{+=)(*&^%$#!].*"))
            return new Result(false, "Password must contain at least one special character");

        return new Result(true, "Password is valid");
    }

    protected String generateRandomPassword() {
        String chars = "a-zA-Z0-9";
        Random random = new Random();
        int rSize = random.nextInt(8) + 8;
        StringBuilder rPassword = new StringBuilder();
        for (int i = 0; i < rSize; i++) {
            rPassword.append(chars.charAt(random.nextInt(chars.length())));
        }
        int[] rIndexes = new int[4];
        HashSet<Integer> rIndexSet = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            int counter = 10;
            while(counter-- > 0) {
                rIndexes[i] = random.nextInt(rSize);
                if(rIndexSet.contains(rIndexes[i]))
                    continue;
                rIndexSet.add(rIndexes[i]);
                break;
            }
        }

        rPassword.setCharAt(rIndexes[0], (char) ('0' + random.nextInt(10)));
        rPassword.setCharAt(rIndexes[1], (char) ('a' + random.nextInt(26)));
        rPassword.setCharAt(rIndexes[2], (char) ('A' + random.nextInt(26)));
        rPassword.setCharAt(rIndexes[3], "?><,\"\\';:/|][}{+=)(*&^%$#!".charAt(random.nextInt(26)));
        return rPassword.toString();
    }

    protected String getSHA256(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hashBytes = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    public abstract Message handleMessage(Message message);
}
