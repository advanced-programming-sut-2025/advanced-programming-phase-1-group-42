package org.example.models.game_structure;

public class Skill {

    private int farmingPoints;
    private int cookingPoints;
    private int fishingPoints;
    private int miningPoints;
    private int foragingPoints;
    private int farmingLevel = 0;
    private int cookingLevel = 0;
    private int fishingLevel = 0;
    private int miningLevel = 0;
    private int foragingLevel = 0;

    public int getCookingLevel() {
        return cookingLevel;
    }

    public void increaseCookingLevel(int cookingLevel) {
        this.cookingLevel++;
    }

    public int getMiningLevel() {
        return miningLevel;
    }

    public void increaseMiningLevel(int miningLevel) {
        this.miningLevel++;
    }

    public int getFarmingLevel() {
        return farmingLevel;
    }

    public void increaseFarmingLevel(int farmingLevel) {
        this.farmingLevel++;
    }

    public int getForagingLevel() {
        return foragingLevel;
    }

    public void increaseForagingLevel(int foragingLevel) {
        this.foragingLevel++;
    }

    public int getFishingLevel() {
        return fishingLevel;
    }

    public void increaseFishingLevel(int fishingLevel) {
        this.fishingLevel++;
    }
}
