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



    public void increaseFarmingPoints(int value) {
        farmingPoints += value;
        if(farmingPoints == 150 || farmingPoints == 250 || farmingPoints == 350 || farmingPoints == 450) {
            increaseCookingLevel();
        }
    }
    public void increaseCookingPoints(int value) {
        cookingPoints += value;
        if(cookingPoints == 150 || cookingPoints == 250 || cookingPoints == 350 || cookingPoints == 450) {
            increaseCookingLevel();
        }
    }
    public void increaseFishingPoints(int value) {
        fishingPoints += value;
        if(fishingPoints == 150 || fishingPoints == 250 || fishingPoints == 350 || fishingPoints == 450) {
            increaseCookingLevel();
        }
    }
    public void increaseMiningPoints(int value) {
        miningPoints += value;
        if(miningPoints == 150 || miningPoints == 250 || miningPoints == 350 || miningPoints == 450) {
            increaseCookingLevel();
        }
    }
    public void increaseForagingPoints(int value) {
        foragingPoints += value;
        if(foragingPoints == 150 || foragingPoints == 250 || foragingPoints == 350 || foragingPoints == 450) {
            increaseCookingLevel();
        }
    }



    public int getCookingLevel() {
        return cookingLevel;
    }

    public void increaseCookingLevel() {
        this.cookingLevel++;
    }

    public int getMiningLevel() {
        return miningLevel;
    }

    public void increaseMiningLevel() {
        this.miningLevel++;
    }

    public int getFarmingLevel() {
        return farmingLevel;
    }

    public void increaseFarmingLevel() {
        this.farmingLevel++;
    }

    public int getForagingLevel() {
        return foragingLevel;
    }

    public void increaseForagingLevel() {
        this.foragingLevel++;
    }

    public int getFishingLevel() {
        return fishingLevel;
    }

    public void increaseFishingLevel() {
        this.fishingLevel++;
    }
}
