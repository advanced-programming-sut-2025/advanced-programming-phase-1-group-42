package com.StardewValley.models;

import com.StardewValley.models.interactions.User;

import java.util.HashMap;

public class Message {
	private Type type;
	private HashMap<String, Object> body;

	/*
	 * Empty constructor needed for JSON Serialization/Deserialization
	 */
	public Message() {}

	public Message(HashMap<String, Object> body, Type type) {
		this.body = body;
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public <T> T getFromBody(String fieldName) {
		return (T) body.get(fieldName);
	}

	public int getIntFromBody(String fieldName) {
		return (int) ((double) ((Double) body.get(fieldName)));
	}

    public boolean getBooleanFromBody(String fieldName) {
        return (boolean) body.get(fieldName);
    }

    public User getUserFromBody(String fieldName) {
        return (User) body.get(fieldName);
    }

	public enum Type {
		command,
		response,
		request,
        update,
        change
	}
}
