package org.example.models.goods.tools;

import org.example.models.App;
import org.example.models.Result;
import org.example.models.enums.TileType;
import org.example.models.game_structure.Cordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.GoodType;

public class Hoe extends Tool {
    public Hoe() {
        super(ToolType.HOE);
    }

    @Override
    public Result useTool(Cordinate cordinate) {
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(cordinate);
        if(tile == null)
            return new Result(false, "You are not in your farm");

        if(tile.getTileType() != TileType.FARM)
            return new Result(false, "You can only use your tool in farm tiles!");

        tile.setTileType(TileType.PREPARED_FARM);
        return new Result(true, toolType.getName() + " used!");
    }

    @Override
    public GoodType getType() {
        return null;
    }
}
