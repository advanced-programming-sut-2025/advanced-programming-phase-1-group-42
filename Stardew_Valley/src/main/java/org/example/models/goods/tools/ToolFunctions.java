package org.example.models.goods.tools;

import org.example.models.App;
import org.example.models.Result;
import org.example.models.enums.Season;
import org.example.models.enums.TileType;
import org.example.models.game_structure.Cordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.Good;
import org.example.models.goods.farmings.FarmingTree;
import org.example.models.goods.farmings.FarmingTreeSapling;
import org.example.models.goods.fishs.FishType;
import org.example.models.goods.foragings.ForagingSeed;
import org.example.models.goods.foragings.ForagingTree;

import java.util.ArrayList;

public class ToolFunctions {
    public static Result tooluse(Tool tool, Cordinate cordinate) {
        switch ((ToolType) tool.getType()){
            case ToolType.HOE -> {
                return useHoe(tool, cordinate);
            }
            case ToolType.PICKAXE -> {
                return usePickaxe(tool, cordinate);
            }
            case ToolType.AXE -> {
                return useAxe(tool, cordinate);
            }
            case ToolType.WATERING_CAN -> {
                return useWateringCan(tool, cordinate);
            }
            case ToolType.TRAINING_FISHING_POLE -> {
                return useTrainingFishingPole(tool, cordinate);
            }
            case ToolType.BAMBOO_FISHING_POLE -> {
                return useBambooFishingPole(tool, cordinate);
            }
            case ToolType.FIBERGLASS_FISHING_POLE -> {
                return useFiberglassFishingPole(tool, cordinate);
            }
            case ToolType.IRIDIUM_FISHING_POLE -> {
                return useIridiumFishingPole(tool, cordinate);
            }
            case ToolType.SCYTHE -> {
                return useScythe(tool, cordinate);
            }
            case ToolType.MILK_PAIL -> {
                return useMilkPail(tool, cordinate);
            }
            case ToolType.SHEAR -> {
                return useShear(tool, cordinate);
            }
            case ToolType.TRASH_CAN -> {
                return useTrashCan(tool, cordinate);
            }
            default -> {
                return new Result(false, "ToolType is Invalid!");
            }
        }
    }

    private static Result useHoe(Tool tool, Cordinate cordinate) {
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(cordinate);
        if(tile == null)
            return new Result(false, "Selected Tile should be in your farm");

        if(tile.getTileType() != TileType.FARM)
            return new Result(false, "You can only use your tool in farm tiles!");

        tile.setTileType(TileType.PLOWED_FARM);
        return new Result(true, ((ToolType) tool.getType()).getName() + " used!");
    }


    private static Result usePickaxe(Tool tool, Cordinate cordinate){
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(cordinate);
        if(tile == null)
            return new Result(false, "Selected Tile should be in your farm");

        if(tile.getTileType() == TileType.PLOWED_FARM)
            tile.setTileType(TileType.FARM);
        ArrayList<Good> newGoods = new ArrayList<>();
        for (Good good : tile.getGoods()) {
            //TODO
        }

        tile.setGoods(newGoods);
        return new Result(true, ((ToolType) tool.getType()).getName() + " used!");
    }

    private static Result useAxe(Tool tool, Cordinate cordinate) {
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(cordinate);
        if(tile == null)
            return new Result(false, "Selected Tile should be in your farm");

        return null;
        //TODO
    }

    private static Result useWateringCan(Tool tool, Cordinate cordinate) {
        Tile tile = App.getCurrentGame().getMap().findTile(cordinate);
        if(tile == null)
            return new Result(false, "Tile not found!");

        if(tile.getTileType() == TileType.WATER) {
            switch (((ToolType) tool.getType()).getLevel()) {
                case ToolLevel.ORDINARY ->
                        tool.capacity = 40;
                case ToolLevel.COPPER ->
                        tool.capacity = 55;
                case ToolLevel.IRON ->
                        tool.capacity = 70;
                case ToolLevel.GOLD ->
                        tool.capacity = 85;
                case ToolLevel.IRIDIUM ->
                        tool.capacity = 100;
            }
            return new Result(true, ((ToolType) tool.getType()).getName() + "'s capacity gets full");
        }

        if(App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(cordinate) == null)
            return new Result(false, "Selected Tile should be in your farm");

        if(tool.capacity == 0)
            return new Result(false, "Your " + ((ToolType) tool.getType()).getName() + " is empty");

        tool.capacity--;
        for (Good good : tile.getGoods()) {
            if(good instanceof FarmingTreeSapling || good instanceof FarmingTree ||
                    good instanceof ForagingSeed || good instanceof ForagingTree)
            //TODO
        }
        return new Result(true, ((ToolType) tool.getType()).getName() + " used");
    }

    private static Result useTrainingFishingPole(Tool tool, Cordinate cordinate) {
        Tile tile = App.getCurrentGame().getMap().findTile(cordinate);
        if(tile == null)
            return new Result(false, "Tile not found!");
        if(tile.getTileType() != TileType.WATER)
            return new Result(false, "You should use " + ((ToolType) tool.getType()).getName() + " in Water Tiles!");

        Good good = null;
        switch (App.getCurrentGame().getDateTime().getSeasonOfYear()) {
            case Season.SPRING ->
                    good = tile.findGood(FishType.HERRING);
            case Season.SUMMER ->
                    good = tile.findGood(FishType.SUNFISH);
            case Season.FALL ->
                    good = tile.findGood(FishType.SARDINE);
            case Season.WINTER ->
                    good = tile.findGood(FishType.PERCH);
        }

        //TODO
        return null;
    }

    private static Result useBambooFishingPole(Tool tool, Cordinate cordinate) {
        //TODO
        return null;
    }

    private static Result useFiberglassFishingPole(Tool tool, Cordinate cordinate) {
        //TODO
        return null;
    }

    private static Result useIridiumFishingPole(Tool tool, Cordinate cordinate) {
        //TODO
        return null;
    }

    private static Result useScythe(Tool tool, Cordinate cordinate) {
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(cordinate);
        if(tile == null)
            return new Result(false, "Selected Tile should be in your farm");

        return null;
    }

    private static Result useMilkPail(Tool tool, Cordinate cordinate) {
        return null;
    }

    private static Result useShear(Tool tool, Cordinate cordinate) {
        return null;
    }

    private static Result useTrashCan(Tool tool, Cordinate cordinate) {
        return null;
    }





}
