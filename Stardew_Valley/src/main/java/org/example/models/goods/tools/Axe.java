package org.example.models.goods.tools;

import org.example.models.App;
import org.example.models.Result;
import org.example.models.game_structure.Cordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.GoodType;

public class Axe extends Tool {
    public Axe() {
        super(ToolType.AXE );
    }

    @Override
    public Result useTool(Cordinate cordinate) {
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(cordinate);
        if(tile == null)
            return new Result(false, "You are not in your farm");

        //TODO
    }

    @Override
    public GoodType getType() {
        return null;
    }
}
