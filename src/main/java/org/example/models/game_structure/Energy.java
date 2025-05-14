package org.example.models.game_structure;

import org.example.models.App;

public class Energy {
    private int dayEnergyLeft = 200;
    private int maxDayEnergy = 200;
    private int maxTurnEnergy = 50;
    private int turnValueLeft = 50;
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
        return turnValueLeft;
    }

    public void decreaseTurnEnergyLeft(int value) {

        this.dayEnergyLeft -= (int) (App.getCurrentGame().getWeather().getWeatherEffectingEnergy() * value);
        this.turnValueLeft -= (int) (App.getCurrentGame().getWeather().getWeatherEffectingEnergy() * value);
        if(this.dayEnergyLeft <= 0){
            this.isAwake = false;
        }
        if(this.turnValueLeft <= 0){
            App.getCurrentGame().nextPlayer();
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
    }


    public int getDayEnergyLeft() {
        return dayEnergyLeft;
    }

}