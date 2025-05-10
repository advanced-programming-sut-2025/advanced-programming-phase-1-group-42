package org.example.models.game_structure.weathers;

import org.example.models.App;

public class Storm extends Weather {
    double weatherEffectingEnergy = 1.5;


    public void thunder() {
        int x1 = (int) Math.floor(Math.random()*60);
        int y1 = (int) Math.floor(Math.random()*50);
        int x2 = (int) Math.floor(Math.random()*60) + 60;
        int y2 = (int) Math.floor(Math.random()*50);
        int x3 = (int) Math.floor(Math.random()*60);
        int y3 = (int) Math.floor(Math.random()*60);

        App.getCurrentGame().getWeather().Thunder(x1, y1);
        App.getCurrentGame().getWeather().Thunder(x2, y2);
        App.getCurrentGame().getWeather().Thunder(x3, y3);

    }

    public String getName() {
        return "Storm";
    }

    public String getCurrentWeather() { return "Storm"; }
}
