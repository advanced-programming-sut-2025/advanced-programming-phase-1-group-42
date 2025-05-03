package org.example.models.goods;

public enum GoodLevel {
    ORDINARY(0),
    STEEL(1),
    GOLD(2),
    IRIDIUM(3);

    private int levelNumber;

    GoodLevel(int levelNumber) {
        this.levelNumber = levelNumber;
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
}
