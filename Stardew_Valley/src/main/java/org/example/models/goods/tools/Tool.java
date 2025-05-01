package org.example.models.goods.tools;

import org.example.models.Result;
import org.example.models.game_structure.Cordinate;
import org.example.models.goods.Good;


public class Tool extends Good {
    protected ToolType toolType;
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



    public abstract Result useTool(Cordinate cordinate);
}
