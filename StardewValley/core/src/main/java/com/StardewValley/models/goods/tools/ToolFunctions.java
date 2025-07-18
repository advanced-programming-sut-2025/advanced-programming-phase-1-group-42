package com.StardewValley.models.goods.tools;

import com.StardewValley.models.App;
import com.StardewValley.models.Pair;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.Season;
import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.game_structure.BuffType;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Skill;
import com.StardewValley.models.game_structure.Tile;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodLevel;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.farmings.FarmingCropType;
import com.StardewValley.models.goods.farmings.FarmingTree;
import com.StardewValley.models.goods.farmings.FarmingTreeSapling;
import com.StardewValley.models.goods.farmings.FarmingTreeType;
import com.StardewValley.models.goods.fishs.FishType;
import com.StardewValley.models.goods.foragings.*;
import com.StardewValley.models.goods.products.ProductType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ToolFunctions {
    private static boolean checkCanBreak(Tool tool, ForagingMineral foragingMineral) {
        switch (tool.getToolLevel()) {
            case ToolLevel.ORDINARY -> {
                return foragingMineral.getType() != ForagingMineralType.COPPER_ORE &&
                        foragingMineral.getType() != ForagingMineralType.IRON_ORE &&
                        foragingMineral.getType() != ForagingMineralType.GOLD_ORE &&
                        foragingMineral.getType() != ForagingMineralType.IRIDIUM_ORE;
            }
            case ToolLevel.COPPER -> {
                return foragingMineral.getType() != ForagingMineralType.IRON_ORE &&
                        foragingMineral.getType() != ForagingMineralType.GOLD_ORE &&
                        foragingMineral.getType() != ForagingMineralType.IRIDIUM_ORE;
            }
            case ToolLevel.IRON -> {
                return foragingMineral.getType() != ForagingMineralType.GOLD_ORE &&
                        foragingMineral.getType() != ForagingMineralType.IRIDIUM_ORE;
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
                if(App.getCurrentGame().getCurrentPlayer().getBuff() != null) {
                    if (App.getCurrentGame().getCurrentPlayer().getBuff().getType().equals(BuffType.FARMING_BUFF)) {
                        App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(1);
                    }
                }
                return useHoe(tool, coordinate);
            }
            case ToolType.PICKAXE -> {
                if(App.getCurrentGame().getCurrentPlayer().getBuff() != null) {
                    if (App.getCurrentGame().getCurrentPlayer().getBuff().getType().equals(BuffType.MINING_BUFF)) {
                        App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(1);
                    }
                }
                return usePickaxe(tool, coordinate);
            }
            case ToolType.AXE -> {
                if(App.getCurrentGame().getCurrentPlayer().getBuff() != null) {
                    if (App.getCurrentGame().getCurrentPlayer().getBuff().getType().equals(BuffType.FORAGING_BUFF)) {
                        App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(1);
                    }
                }
                return useAxe(tool, coordinate);
            }
            case ToolType.WATERING_CAN -> {
                if(App.getCurrentGame().getCurrentPlayer().getBuff() != null) {
                    if (App.getCurrentGame().getCurrentPlayer().getBuff().getType().equals(BuffType.FARMING_BUFF)) {
                        App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(1);
                    }
                }
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



    private static Result useHoe(Tool tool, Coordinate coordinate) {
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(coordinate, App.getCurrentGame().getCurrentPlayer());
        if(tile == null)
            return new Result(false, "Selected Tile should be in your farm");

        if(tile.getTileType() != TileType.FARM)
            return new Result(false, "You can only use your tool in farm tiles!");

        tile.setTileType(TileType.PLOWED_FARM);

        Iterator<Good> iterator = tile.getGoods().iterator();
        while (iterator.hasNext()) {
            Good good = iterator.next();
            if (good.getType() instanceof FarmingCropType || good.getType() instanceof ForagingSeedType) {
                App.getCurrentGame().getCurrentPlayer().getInventory().addGoodByObject(good);
                App.getCurrentGame().getCurrentPlayer().getSkill().increaseFarmingPoints(5);
                iterator.remove(); // Safe removal
            }
        }

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
                    //? i deleted something?
                    if(App.getCurrentGame().getCurrentPlayer().getSkill().getMiningLevel() >= 2){
                        ArrayList<Good> gooods = Good.newGoods(good.getType(), 2);
                        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(gooods);
                    }
                    else{
                        ArrayList<Good> gooods = Good.newGoods(good.getType(), 1);
                        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(gooods);
                    }
                }
                else
                    newGoods.add(good);
            } else if(good instanceof FarmingTree farmingTree) {
                FarmingTreeType type = (FarmingTreeType) farmingTree.getType();
                for (Pair<GoodType, Integer> product : type.getProducts()) {
                    if (farmingTree.isFruitAvailable()){
                        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(
                                Good.newGoods(product.first(), product.second())
                        );
                    }
                }
            }
            else if(good instanceof ForagingTree foragingTree) {
                ForagingTreeType type = (ForagingTreeType) foragingTree.getType();
                for (Pair<GoodType, Integer> product : type.getProducts()) {
                    App.getCurrentGame().getCurrentPlayer().getInventory().addGood(
                            Good.newGoods(product.first(), product.second())
                    );
                }
            }
            else {
                App.getCurrentGame().getCurrentPlayer().getInventory().addGood(new ArrayList<>(Arrays.asList(good)));
            }
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
            else if(good instanceof FarmingTree farmingTree && farmingTree.isFruitAvailable()) {
                for (Pair<GoodType, Integer> product : ((FarmingTreeType) farmingTree.getType()).getProducts()) {
                    ArrayList<Good> newGoods = Good.newGoods(product.first(), product.second());
                    App.getCurrentGame().getCurrentPlayer().getInventory().addGood(newGoods);
                }
            } else if(good instanceof FarmingTreeSapling) {
                ArrayList<Good> newGoods = Good.newGoods(ProductType.WOOD, 2);
                App.getCurrentGame().getCurrentPlayer().getInventory().addGood(newGoods);
            }
            else
                tileGoods.add(good);

        }

        tile.setGoods(tileGoods);


        Iterator<Good> iterator = tile.getGoods().iterator();
        while (iterator.hasNext()) {
            Good good = iterator.next();
            if (good.getType() instanceof ForagingSeedType || good.getType() instanceof ForagingCropType) {
                App.getCurrentGame().getCurrentPlayer().getInventory().addGoodByObject(good);
                App.getCurrentGame().getCurrentPlayer().getSkill().increaseForagingPoints(10);
                iterator.remove(); // Safe removal
            }
        }
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

        if(App.getCurrentGame().getCurrentPlayer().getFarm().findTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()) == null)
            return new Result(false, "Selected Tile should be in your farm");

        if(tool.capacity == 0)
            return new Result(false, "Your " + ((ToolType) tool.getType()).getName() + " is empty");

        tool.capacity--;
        tile.setWatered(true);
        return new Result(true, ((ToolType) tool.getType()).getName() + " used");
    }

    public static Result fish(ToolType fishingPole, double numberOfFishes, double rarityChance) {

        if(App.getCurrentGame().getCurrentPlayer().getBuff() != null) {
            if (App.getCurrentGame().getCurrentPlayer().getBuff().getType().equals(BuffType.FISHING_BUFF)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(1);
            }
        }

        App.getCurrentGame().getCurrentPlayer().getSkill().increaseFishingPoints(5);

        int numberOfFishesInt = (int)Math.floor(numberOfFishes);
        int fishQuality = (int) Math.floor(rarityChance);


        if(numberOfFishesInt == 0){
            return new Result(true , "Better luck next time!");
        }

        GoodLevel fishLevel;
        switch (fishQuality){
            case 0:
            fishLevel = GoodLevel.ORDINARY;
            break;
            case 1:
            fishLevel = GoodLevel.STEEL;
            break;
            case 2:
            fishLevel = GoodLevel.GOLD;
            break;
            case 3:
            fishLevel = GoodLevel.IRIDIUM;
            break;
            default:
                fishLevel = GoodLevel.ORDINARY;
        }

        Skill skill = App.getCurrentGame().getCurrentPlayer().getSkill();
        if(App.getCurrentGame().getCurrentPlayer().getInventory().isFull()){
            return new Result(true, "Your inventory is full!");
        }

        Season season = App.getCurrentGame().getDateTime().getSeasonOfYear();
        ArrayList<Good> good = null;

        int chance = (int) Math.floor(Math.random()*5);

        if(fishingPole.equals(ToolType.TRAINING_FISHING_POLE)){
            switch (season) {
                case SPRING:
                    good = Good.newFishs(FishType.HERRING,numberOfFishesInt , fishLevel);
                    break;
                case SUMMER:
                    good = Good.newFishs(FishType.SUNFISH,numberOfFishesInt, fishLevel);
                    break;
                case FALL:
                    good = Good.newFishs(FishType.SARDINE,numberOfFishesInt, fishLevel);
                    break;
                case WINTER:
                    good = Good.newFishs(FishType.PERCH , numberOfFishesInt, fishLevel);
                    break;
                default:
                    break;
            }
        }
        else {
            switch (season) {
                case SPRING:
                    switch (chance) {
                        case 0:
                            good = Good.newFishs(FishType.HERRING, numberOfFishesInt, fishLevel);
                            break;
                        case 2:
                            good = Good.newFishs(FishType.LIONFISH, numberOfFishesInt, fishLevel);
                            break;
                        case 3:
                            good = Good.newFishs(FishType.GHOSTFISH, numberOfFishesInt, fishLevel);
                            break;
                        case 4:
                            if (skill.getFishingLevel() == 5) {
                                good = Good.newFishs(FishType.LEGEND, numberOfFishesInt, fishLevel);
                            }
                            else {
                                good = Good.newFishs(FishType.HERRING, numberOfFishesInt, fishLevel);
                            }
                            break;
                        default:
                            good = Good.newFishs(FishType.FLOUNDER, numberOfFishesInt, fishLevel);
                            break;
                    }
                    break;
                case SUMMER:
                    switch (chance) {
                        case 0:
                            good = Good.newFishs(FishType.SUNFISH, numberOfFishesInt, fishLevel);
                            break;
                        case 2:
                            good = Good.newFishs(FishType.TILAPIA, numberOfFishesInt, fishLevel);
                            break;
                        case 3:
                            good = Good.newFishs(FishType.RAINBOW_TROUT, numberOfFishesInt, fishLevel);
                            break;
                        case 4:
                            if (skill.getFishingLevel() == 5) {
                                good = Good.newFishs(FishType.CRIMSONFISH, numberOfFishesInt, fishLevel);
                            } else {
                                good = Good.newFishs(FishType.DORADO, numberOfFishesInt, fishLevel);
                            }
                            break;
                        default:
                            good = Good.newFishs(FishType.DORADO, numberOfFishesInt, fishLevel);
                            break;
                    }
                    break;
                case FALL:
                    switch (chance) {
                        case 0:
                            good = Good.newFishs(FishType.SARDINE, numberOfFishesInt, fishLevel);
                            break;
                        case 1:
                            good = Good.newFishs(FishType.SALMON, numberOfFishesInt, fishLevel);
                            break;
                        case 2:
                            good = Good.newFishs(FishType.SHAD, numberOfFishesInt, fishLevel);
                            break;
                        case 4:
                            if (skill.getFishingLevel() == 5) {
                                good = Good.newFishs(FishType.ANGLER, numberOfFishesInt, fishLevel);
                            } else {
                                good = Good.newFishs(FishType.BLUE_DISCUS, numberOfFishesInt, fishLevel);
                            }
                            break;
                        default:
                            good = Good.newFishs(FishType.BLUE_DISCUS, numberOfFishesInt, fishLevel);
                            break;
                    }
                    break;
                case WINTER:
                    switch (chance) {
                        case 0:
                            good = Good.newFishs(FishType.PERCH, numberOfFishesInt, fishLevel);
                            break;
                        case 1:
                            good = Good.newFishs(FishType.SQUID, numberOfFishesInt, fishLevel);
                            break;
                        case 2:
                            good = Good.newFishs(FishType.MIDNIGHT_CARP, numberOfFishesInt, fishLevel);
                            break;
                        case 4:
                            if (skill.getFishingLevel() == 5) {
                                good = Good.newFishs(FishType.GLACIERFISH, numberOfFishesInt, fishLevel);
                            } else {
                                good = Good.newFishs(FishType.TUNA, numberOfFishesInt, fishLevel);
                            }
                            break;
                        default:
                            good = Good.newFishs(FishType.TUNA, numberOfFishesInt, fishLevel);
                            break;
                    }
                default:
                    break;
            }
        }

        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(good);
        System.out.println("You've got " + good.size() + " " + good.getFirst().getName() + " !!!");
        return new Result(true,"You've got " + good.lastIndexOf(good.getFirst()) + " " + good.getFirst().getName() + " !!!");
    }


    private static Result useScythe(Tool tool, Coordinate coordinate) {
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(coordinate, App.getCurrentGame().getCurrentPlayer());
        if(tile == null)
            return new Result(false, "Selected Tile should be in your farm");

        ArrayList<Good> goods = new ArrayList<>();
        for (Good good : tile.getGoods()) {
            if(good.getType() != ProductType.GRASS) {
                App.getCurrentGame().getCurrentPlayer().getInventory().addGood(
                        new ArrayList<>(Arrays.asList(good))
                );
            }
            else if(good instanceof ForagingTree foragingTree) {
                for (Pair<GoodType, Integer> product : ((ForagingTreeType) foragingTree.getType()).getProducts()) {
                    ArrayList<Good> newGoods = Good.newGoods(product.first(), product.second());
                    App.getCurrentGame().getCurrentPlayer().getInventory().addGood(newGoods);
                }
            }
            else if(good instanceof FarmingTree farmingTree && farmingTree.isFruitAvailable()) {
                for (Pair<GoodType, Integer> product : ((FarmingTreeType) farmingTree.getType()).getProducts()) {
                    ArrayList<Good> newGoods = Good.newGoods(product.first(), product.second());
                    App.getCurrentGame().getCurrentPlayer().getInventory().addGood(newGoods);
                }
            } else if(good instanceof FarmingTreeSapling) {
                ArrayList<Good> newGoods = Good.newGoods(ProductType.WOOD, 2);
                App.getCurrentGame().getCurrentPlayer().getInventory().addGood(newGoods);
            }
            else
                goods.add(good);
        }


        tile.setGoods(goods);
        return new Result(true, ((ToolType) tool.getType()).getName() + " used");
    }

    private static Result useMilkPail(Tool tool, Coordinate coordinate) {
        return null;
    }

    private static Result useShear(Tool tool, Coordinate coordinate) {
        return null;
    }

    public static int useTrashCan(Tool tool, int totalPrice) {
        int finalPrice = totalPrice;

        System.out.println(tool.getToolLevel().getName());
        switch (tool.getToolLevel()) {
            case ToolLevel.ORDINARY ->
                finalPrice = 0;
            case ToolLevel.COPPER ->
                finalPrice = (totalPrice * 15) / 100;
            case ToolLevel.IRON ->
                finalPrice = (totalPrice * 30) / 100;
            case ToolLevel.GOLD ->
                finalPrice = (totalPrice * 45) / 100;
            case ToolLevel.IRIDIUM ->
                finalPrice = (totalPrice * 60) / 100;
        }

        return finalPrice;
    }
}
