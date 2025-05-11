package org.example.models.enums;

import org.example.models.game_structure.weathers.*;

public enum WeatherType {
    Sunny(new Sunny(1 ,1.5)),
    Rain(new Rain(1.5,1.2)),
    Storm(new Storm(1.5,0.5)),
    Snow(new Snow(2,1));

    private final Weather weather;

    WeatherType(Weather weather) {
        this.weather = weather;
    }
    public Weather getWeather(){
        return weather;
    }
}

