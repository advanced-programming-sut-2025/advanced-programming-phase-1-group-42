package org.example.models.goods.tools;

import org.example.models.App;
import org.example.models.goods.GoodLevel;
import org.example.models.goods.GoodType;

public enum ToolType implements GoodType {
    HOE("Hoe", -1, 5),
    PICKAXE("Pickaxe", -1,5),
    AXE("Axe", -1, 5),
    WATERING_CAN("WateringCan", -1, 5),
    SCYTHE("Scythe", -1, 2),
    MILK_PAIL("Milk_Pail", 1000, 4),
    SHEAR("Shear", 1000, 4),
    TRASH_CAN("Trash_Can", 0,0),
    TRAINING_FISHING_POLE("Training_Fishing_Pole", 25, 8),
    BAMBOO_FISHING_POLE("Bamboo_Fishing_Pole", 500, 8),
    FIBERGLASS_FISHING_POLE("Fiberglass_Fishing_Pole", 1800, 6),
    IRIDIUM_FISHING_POLE("Iridium_Fishing_Pole", 7500, 4);

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

    private final String name;
    private final int price;
    private final int energy;
    private ToolLevel level;

    ToolType(String name, int price, int energy) {
        this.name = name;
        this.price = price;
        this.energy = energy;
        this.level = ToolLevel.ORDINARY;
    }

    public ToolLevel getLevel() {
        return level;
    }

    public void setLevel(ToolLevel level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

}
