package org.example.models.game_structure;

public class Energy {
    private int value;
    private int maxValue;

    // Functions
    public void energySet(int value) {
        this.value -= value;
    }

    public void energyUnlimited() {
        //TODO
    }
}
