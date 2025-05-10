package org.example.models.goods.tools;

import org.example.models.goods.*;

import java.util.ArrayList;
import java.util.Arrays;

public enum ToolLevel {
    ORDINARY(0, "Ordinary"),
    COPPER(1, "Copper"),
    IRON(2, "Iron"),
    GOLD(3, "Gold"),
    IRIDIUM(4, "Iridium");

    private int levelNumber;
    private String name;
    public final static ArrayList<ToolLevel> toolLevels = new ArrayList<>(Arrays.asList(values()));

    ToolLevel(int levelNumber, String name) {
        this.levelNumber = levelNumber;
        this.name = name;
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

    public String getName() {
        return name;
    }
}
