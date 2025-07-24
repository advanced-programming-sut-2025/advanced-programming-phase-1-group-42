package com.StardewValley.models.goods.tools;

import com.StardewValley.models.App;
import com.StardewValley.models.goods.GoodType;

public enum ToolType implements GoodType {
    HOE("Hoe", -1, 5,"GameAssets/Hoe/Hoe.png"),
    PICKAXE("Pickaxe", -1,5,"GameAssets/Tools/Pickaxe/Pickaxe.png"),
    AXE("Axe", -1, 5,"GameAssets/Tools/Axe/Axe.png"),
    WATERING_CAN("Watering_Can", -1, 5,"GameAssets/Watering_Can/Watering_Can.png"), //?
    SCYTHE("Scythe", -1, 2,"GameAssets/Tools/Scythe.png"),
    MILK_PAIL("Milk_Pail", 1000, 4,"GameAssets/Tools/Milk_Pail.png"),
    SHEAR("Shear", 1000, 4,"GameAssets/Tools/Shears.png"),
    TRASH_CAN("Trash_Can", 0,0,"GameAssets/Tools/Trash_Can.png"), //??
    TRAINING_FISHING_POLE("Training_Fishing_Pole", 25, 8,"GameAssets/Tools/Training_Rod.png"),
    BAMBOO_FISHING_POLE("Bamboo_Fishing_Pole", 500, 8,"GameAssets/Fishing_Pole/Bamboo_Pole.png"),
    FIBERGLASS_FISHING_POLE("Fiberglass_Fishing_Pole", 1800, 6,"GameAssets/Fishing_Pole/Fiberglass_Rod.png"),
    IRIDIUM_FISHING_POLE("Iridium_Fishing_Pole", 7500, 4 , "GameAssets/Fishing_Pole/Iridium_Rod.png");

    @Override
    public int getSellPrice() {
        return price;
    }

    @Override
    public int getEnergy() {
        int finalEnergy = this.energy;
        finalEnergy -= this.getLevel().getLevelNumber();
        if((this == HOE || this == WATERING_CAN) && App.getCurrentGame().getCurrentPlayer().getSkill().getFarmingLevel() == 4)
            finalEnergy--;
        if((this == PICKAXE) && App.getCurrentGame().getCurrentPlayer().getSkill().getMiningLevel() == 4)
            finalEnergy--;
        if((this == AXE) && App.getCurrentGame().getCurrentPlayer().getSkill().getForagingLevel() == 4)
            finalEnergy--;
        if((this == TRAINING_FISHING_POLE || this == BAMBOO_FISHING_POLE || this == FIBERGLASS_FISHING_POLE ||
                this == IRIDIUM_FISHING_POLE) && App.getCurrentGame().getCurrentPlayer().getSkill().getFishingLevel() == 4)
            finalEnergy--;

        return Math.max(finalEnergy, 0);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String imagePath() {
        return imagePath;
    }


    private final String name;
    private final int price;
    private final int energy;
    private ToolLevel level;
    private String imagePath;

    ToolType(String name, int price, int energy ,String imagePath) {
        this.name = name;
        this.price = price;
        this.energy = energy;
        this.level = ToolLevel.ORDINARY;
        this.imagePath = imagePath;
    }

    public ToolLevel getLevel() {
        return level;
    }

    public void setLevel(ToolLevel level) {
        this.level = level;
    }

    public static ToolType getTool(String toolName) {
        return switch (toolName) {
            case "Training_Fishing_Pole" -> ToolType.TRAINING_FISHING_POLE;
            case "Bamboo_Fishing_Pole" -> ToolType.BAMBOO_FISHING_POLE;
            case "Fiberglass_Fishing_Pole" -> ToolType.FIBERGLASS_FISHING_POLE;
            case "Iridium_Fishing_Pole" -> ToolType.IRIDIUM_FISHING_POLE;
            default -> null;
        };
    }


}
