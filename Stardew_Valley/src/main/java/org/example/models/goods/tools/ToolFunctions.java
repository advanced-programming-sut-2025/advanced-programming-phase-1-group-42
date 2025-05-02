package org.example.models.goods.tools;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.Result;
import org.example.models.enums.Season;
import org.example.models.enums.TileType;
import org.example.models.game_structure.Coordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.craftings.Crafting;
import org.example.models.goods.farmings.Farming;
import org.example.models.goods.farmings.FarmingTree;
import org.example.models.goods.farmings.FarmingTreeSapling;
import org.example.models.goods.farmings.FarmingTreeType;
import org.example.models.goods.fishs.Fish;
import org.example.models.goods.fishs.FishType;
import org.example.models.goods.foods.Food;
import org.example.models.goods.foods.artisans.Artisan;
import org.example.models.goods.foragings.*;
import org.example.models.goods.products.ProductType;

import java.util.ArrayList;
import java.util.Arrays;

public class ToolFunctions {
    private static boolean checkCanBreak(Tool tool, ForagingMineral foragingMineral) {
        switch (tool.getToolLevel()) {
            case ToolLevel.ORDINARY -> {
                return foragingMineral.getType() != ForagingMineralType.COPPER &&
                        foragingMineral.getType() != ForagingMineralType.IRON &&
                        foragingMineral.getType() != ForagingMineralType.GOLD &&
                        foragingMineral.getType() != ForagingMineralType.IRIDIUM;
            }
            case ToolLevel.COPPER -> {
                return foragingMineral.getType() != ForagingMineralType.IRON &&
                        foragingMineral.getType() != ForagingMineralType.GOLD &&
                        foragingMineral.getType() != ForagingMineralType.IRIDIUM;
            }
            case ToolLevel.IRON -> {
                return foragingMineral.getType() != ForagingMineralType.GOLD &&
                        foragingMineral.getType() != ForagingMineralType.IRIDIUM;
            }
            case ToolLevel.GOLD, ToolLevel.IRIDIUM -> {
                return true;
            }
        }
        return false;
    }


    public static Result tooluse(Tool tool, Coordinate coordinate) {
        switch ((ToolType) tool.getType()){
            case ToolType.HOE -> {
                return useHoe(tool, coordinate);
            }
            case ToolType.PICKAXE -> {
                return usePickaxe(tool, coordinate);
            }
            case ToolType.AXE -> {
                return useAxe(tool, coordinate);
            }
            case ToolType.WATERING_CAN -> {
                return useWateringCan(tool, coordinate);
            }
            case ToolType.SCYTHE -> {
                return useScythe(tool, coordinate);
            }
            case ToolType.MILK_PAIL -> {
                return useMilkPail(tool, coordinate);
            }
            case ToolType.SHEAR -> {
                return useShear(tool, coordinate);
            }
            case ToolType.TRASH_CAN, ToolType.TRAINING_FISHING_POLE, ToolType.BAMBOO_FISHING_POLE,
                 ToolType.FIBERGLASS_FISHING_POLE, ToolType.IRIDIUM_FISHING_POLE -> {
                return new Result(false, "You should use other command to use " + tool.getType().getName() + "!");
            }
            default -> {
                return new Result(false, "ToolType is Invalid!");
            }
        }
    }

    public static Result fishingPoleUse(Tool tool, Coordinate coordinate) {
        switch ((ToolType) tool.getType()) {
            case ToolType.TRAINING_FISHING_POLE -> {
                return useTrainingFishingPole(tool, coordinate);
            }
            case ToolType.BAMBOO_FISHING_POLE -> {
                return useBambooFishingPole(tool, coordinate);
            }
            case ToolType.FIBERGLASS_FISHING_POLE -> {
                return useFiberglassFishingPole(tool, coordinate);
            }
            case ToolType.IRIDIUM_FISHING_POLE -> {
                return useIridiumFishingPole(tool, coordinate);
            }
            default -> {
                return new Result(false, "ToolType is Invalid!");
            }
        }
    }


    private static Result useHoe(Tool tool, Coordinate coordinate) {
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(coordinate, App.getCurrentGame().getCurrentPlayer());
        if(tile == null)
            return new Result(false, "Selected Tile should be in your farm");

        if(tile.getTileType() != TileType.FARM)
            return new Result(false, "You can only use your tool in farm tiles!");

        tile.setTileType(TileType.PLOWED_FARM);
        return new Result(true, ((ToolType) tool.getType()).getName() + " used!");
    }


    private static Result usePickaxe(Tool tool, Coordinate coordinate){
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(coordinate, App.getCurrentGame().getCurrentPlayer());
        if(tile == null)
            return new Result(false, "Selected Tile should be in your farm");

        if(tile.getTileType() == TileType.PLOWED_FARM)
            tile.setTileType(TileType.FARM);
        tile.setWatered(false);

        ArrayList<Good> newGoods = new ArrayList<>();
        for (Good good : tile.getGoods()) {
            if (good instanceof ForagingMineral foragingMineral) {
                if(checkCanBreak(tool, foragingMineral)) {
                    App.getCurrentGame().getCurrentPlayer().getInventory().addGood(
                            new ArrayList<>(Arrays.asList(good))
                    );
                }
                else
                    newGoods.add(good);
            }
            //TODO : باید درخت به بار رسیده باشد که بتوان محصولات آن را برداشت کرد
        }

        tile.setGoods(newGoods);
        return new Result(true, ((ToolType) tool.getType()).getName() + " used!");
    }

    private static Result useAxe(Tool tool, Coordinate coordinate) {
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(coordinate, App.getCurrentGame().getCurrentPlayer());
        if(tile == null)
            return new Result(false, "Selected Tile should be in your farm");

        ArrayList<Good> tileGoods = new ArrayList<>();
        for (Good good : tile.getGoods()) {
            if(good instanceof ForagingTree foragingTree) {
                for (Pair<GoodType, Integer> product : ((ForagingTreeType) foragingTree.getType()).getProducts()) {
                    ArrayList<Good> newGoods = Good.newGoods(product.first(), product.second());

                    App.getCurrentGame().getCurrentPlayer().getInventory().addGood(newGoods);
                }
            }
            else if(good instanceof FarmingTree farmingTree) {
                for (Pair<GoodType, Integer> product : ((FarmingTreeType) farmingTree.getType()).getProducts()) {
                    ArrayList<Good> newGoods = Good.newGoods(product.first(), product.second());

                    App.getCurrentGame().getCurrentPlayer().getInventory().addGood(newGoods);
                }
            }
            else
                tileGoods.add(good);

            //TODO باید درخت به بار رسیده باشد که بتوان محصولات آن را برداشت کرد
        }

        tile.setGoods(tileGoods);
        return new Result(true, ((ToolType) tool.getType()).getName() + " used!");
    }

    private static Result useWateringCan(Tool tool, Coordinate coordinate) {
        Tile tile = App.getCurrentGame().getMap().findTile(coordinate);
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

        if(App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(coordinate, App.getCurrentGame().getCurrentPlayer()) == null)
            return new Result(false, "Selected Tile should be in your farm");

        if(tool.capacity == 0)
            return new Result(false, "Your " + ((ToolType) tool.getType()).getName() + " is empty");

        tool.capacity--;
        tile.setWatered(true);
        return new Result(true, ((ToolType) tool.getType()).getName() + " used");
    }

    private static Result useTrainingFishingPole(Tool tool, Coordinate coordinate) {
        Tile tile = App.getCurrentGame().getMap().findTile(coordinate);
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


        return null;
    }

    private static Result useBambooFishingPole(Tool tool, Coordinate coordinate) {
        //TODO
        return null;
    }

    private static Result useFiberglassFishingPole(Tool tool, Coordinate coordinate) {
        //TODO
        return null;
    }

    private static Result useIridiumFishingPole(Tool tool, Coordinate coordinate) {
        //TODO
        return null;
    }

    private static Result useScythe(Tool tool, Coordinate coordinate) {
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(coordinate, App.getCurrentGame().getCurrentPlayer());
        if(tile == null)
            return new Result(false, "Selected Tile should be in your farm");

        for (Good good : tile.getGoods()) {
            if(good.getType() != ProductType.GRASS) {
                App.getCurrentGame().getCurrentPlayer().getInventory().addGood(
                        new ArrayList<>(Arrays.asList(good))
                );
            }
        }
        //TODO باید درخت به بار رسیده باشد که بتوان محصولات آن را برداشت کرد

        tile.setGoods(new ArrayList<>());
        return null;
    }

    private static Result useMilkPail(Tool tool, Coordinate coordinate) {
        return null;
    }

    private static Result useShear(Tool tool, Coordinate coordinate) {
        return null;
    }

    public static int useTrashCan(Tool tool, int totalPrice) {
        int finalPrice = totalPrice;

        switch (tool.getToolLevel()) {
            case ToolLevel.ORDINARY ->
                finalPrice = 0;
            case ToolLevel.COPPER ->
                finalPrice = totalPrice * 15 / 100;
            case ToolLevel.IRON ->
                finalPrice = totalPrice * 30 / 100;
            case ToolLevel.GOLD ->
                finalPrice = totalPrice * 45 / 100;
            case ToolLevel.IRIDIUM ->
                finalPrice = totalPrice * 60 / 100;
        }

        return finalPrice;
    }





}
