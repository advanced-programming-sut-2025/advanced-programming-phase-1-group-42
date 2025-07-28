package com.StardewValley.models;

public record Result(boolean success, String message) {
    @Override
    public String toString() {
        return this.message;
    }
}
