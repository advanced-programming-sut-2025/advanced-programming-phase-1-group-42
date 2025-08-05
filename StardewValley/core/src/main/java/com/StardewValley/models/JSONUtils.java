package com.StardewValley.models;

import com.StardewValley.models.interactions.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONUtils {
	private static final GsonBuilder gsonBuilder = new GsonBuilder();
	private static final Gson gson;

	static {
		gsonBuilder.setPrettyPrinting();
		gson = gsonBuilder.create();
	}

	public synchronized static String toJson(Message message) {
		return gson.toJson(message);
	}

	public synchronized static Message fromJson(String json) {
		return gson.fromJson(json, Message.class);
	}

    public synchronized static User fromJsonUser(String json) {
        return gson.fromJson(json, User.class);
    }

    public synchronized static String toJsonUser(User user) {
        return gson.toJson(user, User.class);
    }


}
