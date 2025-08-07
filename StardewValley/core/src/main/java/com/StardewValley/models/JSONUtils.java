package com.StardewValley.models;

import com.StardewValley.models.game_structure.Game;
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

    public synchronized static Labi fromJsonLabi(String json) {
        return gson.fromJson(json, Labi.class);
    }

    public synchronized static String toJsonLabi(Labi labi) {
        return gson.toJson(labi, Labi.class);
    }

    public synchronized static Game fromJsonGame(String json) {
        return gson.fromJson(json, Game.class);
    }

    public synchronized static String toJsonLabi(Game game) {
        return gson.toJson(game, Game.class);
    }

    public synchronized static Gson getGson () {
        return gson;
    }
}
