package com.StardewValley.models.interactions.Animals;

public enum AnimalProductQuality {
    NORMAL(1),
    SILVER(1.25),
    GOLD(1.5),
    IRIDIUM(2);

    private final double ratio;
    AnimalProductQuality(double ratio) {
        this.ratio = ratio;
    }
    public double getRatio() {
        return ratio;
    }


}
