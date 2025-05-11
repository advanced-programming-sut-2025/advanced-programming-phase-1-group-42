package org.example.models.game_structure.weathers;

public class Snow extends Weather {
    double weatherEffectingEnergy ;
    double fishChance ;

    public double getWeatherEffectingEnergy() {
        return weatherEffectingEnergy;
    }
    public Snow(double effectingEnergy, double fishChance) {
        this.weatherEffectingEnergy = effectingEnergy;
        this.fishChance = fishChance;
    }

    public double getFishChance() {
        return fishChance;
    }

    public String getName() {
        return "Snow";
    }

    public String getCurrentWeather() { return "Snow"; }
}
