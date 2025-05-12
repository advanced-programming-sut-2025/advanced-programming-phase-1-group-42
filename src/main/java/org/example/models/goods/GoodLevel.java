package org.example.models.goods;

public enum GoodLevel {
    ORDINARY(0, 1),
    STEEL(1,1.25),
    GOLD(2,1.5),
    IRIDIUM(3,2);

    private int levelNumber;
    private final double ratio;


    GoodLevel(int levelNumber , double ratio) {
        this.levelNumber = levelNumber;
        this.ratio = ratio;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public GoodLevel increaseGoodLevel() {
        switch (levelNumber) {
            case 0:
                return STEEL;
            case 1:
                return GOLD;
            case 2:
                return IRIDIUM;
        }

        return null;
    }

    public double getRatio() {
        return ratio;
    }

}
