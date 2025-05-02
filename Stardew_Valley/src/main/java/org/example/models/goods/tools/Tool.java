package org.example.models.goods.tools;

import org.example.models.goods.Good;
import org.example.models.goods.GoodType;


public class Tool extends Good {
    private ToolType toolType;
    private ToolLevel toolLevel;
    public int capacity;

    public Tool(ToolType toolType) {
        this.toolType = toolType;
        if(toolType == ToolType.WATERING_CAN)
            this.capacity = 40;
        else
            this.capacity = -1;
    }

    @Override
    public String getName() {
        return toolType.getName();
    }

    @Override
    public int getSellPrice() {
        return toolType.getSellPrice();
    }

    @Override
    public GoodType getType() {
        return toolType;
    }

    public ToolLevel getToolLevel() {
        return toolLevel;
    }
}
