package org.example.models.goods.tools;

import org.example.models.goods.GoodLevel;

public enum ToolLevel {
    ORDINARY(0),
    COPPER(1),
    IRON(2),
    GOLD(3),
    IRIDIUM(4);

    private int levelNumber;

    ToolLevel(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public ToolLevel increaseGoodLevel() {
        switch (levelNumber) {
            case 0:
                return COPPER;
            case 1:
                return IRON;
            case 2:
                return GOLD;
            case 3:
                return IRIDIUM;
        }
        return null;
    }
}
