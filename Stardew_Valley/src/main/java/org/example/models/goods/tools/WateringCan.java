package org.example.models.goods.tools;

import org.example.models.App;
import org.example.models.Result;
import org.example.models.enums.TileType;
import org.example.models.game_structure.Cordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.GoodType;

public class WateringCan extends Tool {
    private int capacity;

    public WateringCan() {
        super(ToolType.WATERING_CAN);
        this.capacity = 40;
    }

    @Override
    public Result useTool(Cordinate cordinate) {
        Tile tile = App.getCurrentGame().getMap().findTile(cordinate);
        if(tile.getTileType() == TileType.WATER) {
            switch (toolType.getLevel()) {
                case ToolLevel.ORDINARY ->
                    this.capacity = 40;
                case ToolLevel.COPPER ->
                    this.capacity = 55;
                case ToolLevel.IRON ->
                    this.capacity = 70;
                case ToolLevel.GOLD ->
                    this.capacity = 85;
                case ToolLevel.IRIDIUM ->
                    this.capacity = 100;
            }
            return new Result(true, toolType.getName() + "'s capacity gets full");
        }

        if(App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(cordinate) == null)
            return new Result(false, "You are not in your farm");

        if(capacity == 0)
            return new Result(false, "Your " + toolType.getName() + " is empty");

        this.capacity--;
        tile.setWatered(true);
        return new Result(true, toolType.getName() + " used");
    }

    @Override
    public GoodType getType() {
        return null;
    }

    public int getCapacity() {
        return capacity;
    }
}
