package org.example.models.game_structure;

public class Energy {
    private int dayEnergyLeft;
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
    public void increaseDayEnergyLeft(int value) {
        this.dayEnergyLeft += value;
    }
    public void decreaseDayEnergyLeft(int value) {
        this.dayEnergyLeft -= value;
    }

    public int getDayEnergyLeft() {
        return dayEnergyLeft;
    }

    public void energyUnlimited() {
        //TODO
    }
}