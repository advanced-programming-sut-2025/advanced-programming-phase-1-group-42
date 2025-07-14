package com.StardewValley.models.game_structure.weathers;

public class Sunny extends Weather {
    double weatherEffectingEnergy;
    double fishChance ;


    public Sunny(double weatherEffectingEnergy, double fishChance) {
        this.weatherEffectingEnergy = weatherEffectingEnergy;
        this.fishChance = fishChance;
    }

    public String getName() {
        return "Sunny";
    }
    public double getWeatherEffectingEnergy() {
        return weatherEffectingEnergy;
    }
    public double getFishChance() {
        return fishChance;
    }

    public String getCurrentWeather() { return "Sunny"; }
}
