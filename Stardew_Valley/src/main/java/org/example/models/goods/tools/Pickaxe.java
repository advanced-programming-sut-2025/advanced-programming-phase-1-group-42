package org.example.models.goods.tools;

import org.example.models.App;
import org.example.models.Result;
import org.example.models.enums.TileType;
import org.example.models.game_structure.Cordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;

import java.util.ArrayList;

public class Pickaxe extends Tool {
    public Pickaxe() {
        super(ToolType.PICKAXE);
    }

    @Override
    public Result useTool(Cordinate cordinate) {
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(cordinate);
        if(tile == null)
            return new Result(false, "You are not in your farm");

        if(tile.getTileType() == TileType.PREPARED_FARM)
            tile.setTileType(TileType.FARM);
        ArrayList<Good> newGoods = new ArrayList<>();
        for (Good good : tile.getGoods()) {
            //TODO
        }

        tile.setGoods(newGoods);
        return new Result(true, toolType.getName() + " used!");
    }

    @Override
    public GoodType getType() {
        return null;
    }
}
