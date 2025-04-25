package org.example.models.goods;

public enum GoodLevel {
    Ordinary(0),
    Steel(1),
    Gold(2),
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
                return Steel;
            case 1:
                return Gold;
            case 2:
                return IRIDIUM;
        }

        return null;
    }
}
