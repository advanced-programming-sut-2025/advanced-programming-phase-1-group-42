package org.example.models.enums;

import org.example.models.game_structure.*;

public enum WeatherType {
    Sunny(new Sunny()),
    Rain(new Rain()),
    Storm(new Storm()),
    Snow(new Snow());

    private final Weather weather;

    WeatherType(Weather weather) {
        this.weather = weather;
    }
}

