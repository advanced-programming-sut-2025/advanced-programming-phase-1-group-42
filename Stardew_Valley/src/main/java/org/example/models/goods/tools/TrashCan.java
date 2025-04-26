package org.example.models.goods.tools;

import org.example.models.Result;
import org.example.models.game_structure.Cordinate;
import org.example.models.goods.GoodType;

public class TrashCan extends Tool {
    public TrashCan() {
        super(ToolType.TRASH_CAN);
    }

    @Override
    public Result useTool(Cordinate cordinate) {
        return null;
    }

    @Override
    public GoodType getType() {
        return null;
    }
}
