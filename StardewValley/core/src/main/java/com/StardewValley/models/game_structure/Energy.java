package com.StardewValley.models.game_structure;

import com.StardewValley.models.App;

import static java.lang.Math.min;

public class Energy {
    private double dayEnergyLeft = 200;
    private double maxDayEnergy = 200;
    private double maxTurnEnergy = 50;
    private double turnValueLeft = 50;
    private boolean isAwake = true;


    // Functions
    public void setMaxDayEnergy(int value) {
        this.maxDayEnergy = value;
    }
    public void setMaxTurnEnergy(int maxTurnEnergy) {
        this.maxTurnEnergy = maxTurnEnergy;
    }

    public void setTurnValueLeft(int turnValueLeft) {
        this.turnValueLeft = turnValueLeft;
    }
    public void setDayEnergyLeft(int dayEnergyLeft) { this.dayEnergyLeft = dayEnergyLeft; }

    public int getTurnValueLeft() {
        return (int) turnValueLeft;
    }

    public void decreaseTurnEnergyLeft(double value) {
        this.dayEnergyLeft -= (App.getCurrentGame().getWeather().getWeatherEffectingEnergy() * value);
        this.turnValueLeft -= (App.getCurrentGame().getWeather().getWeatherEffectingEnergy() * value);
        if(this.dayEnergyLeft <= 0){
            this.isAwake = false;
        }
        if(this.turnValueLeft <= 0){
            App.getCurrentGame().nextPlayer();
            this.turnValueLeft = min(50, dayEnergyLeft);
        }
    }

    public void setAwake(boolean awake) {
        isAwake = awake;
    }
    public boolean isAwake() {
        return isAwake;
    }

    public void increaseTurnEnergyLeft(int value) {
        this.dayEnergyLeft += value;
        this.turnValueLeft += value;
        if(dayEnergyLeft >= maxDayEnergy){
            dayEnergyLeft = maxDayEnergy;
        }
        if(turnValueLeft >= maxTurnEnergy){
            turnValueLeft = maxTurnEnergy;
        }
    }


    public int getDayEnergyLeft() {
        return (int) dayEnergyLeft;
    }

    public int getMaxDayEnergy() {
        return (int) maxDayEnergy;
    }

    public int getMaxTurnEnergy() {
        return (int) maxTurnEnergy;
    }
}
