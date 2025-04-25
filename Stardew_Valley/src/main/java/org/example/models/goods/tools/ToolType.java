package org.example.models.goods.tools;

import org.example.models.goods.GoodType;

public enum ToolType implements GoodType {
    HOE(),
    PICKAXE(),
    AXE(),
    WATERING_CAN(),
    SCYTHE(),
    MILK_PAIL(),
    SHEAR(),
    TRASH_CAN(),
    TRAINING_FISHING_POLE(),
    BAMBOO_FISHING_POLE(),
    FIBERGLASS_FISHING_POLE(),
    IRIDIUM_FISHING_POLE();

    @Override
    public int getSellPrice() {
        return 0;
    }

    @Override
    public int getEnergy() {
        return 0;
    }


}
