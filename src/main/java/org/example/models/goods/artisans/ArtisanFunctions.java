package org.example.models.goods.artisans;

import com.mongodb.client.model.QuantileMethod;
import org.example.models.App;
import org.example.models.Result;
import org.example.models.game_structure.Coordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.craftings.Crafting;
import org.example.models.goods.craftings.CraftingType;
import org.example.models.goods.products.ProductType;
import org.example.models.interactions.game_buildings.Quadruple;

import java.util.ArrayList;
import java.util.Arrays;

public class ArtisanFunctions {
    public static Result artisanFunction(String artisanName, ArrayList<String> ourIngredients) {
        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(artisanName);
        Crafting crafting = null;
        if(goods != null && !goods.isEmpty()) {
            crafting = (Crafting) goods.getLast();
        }


        switch ((CraftingType) crafting.getType()) {
            case CraftingType.CHERRY_BOMB -> {
                goods.removeLast();
                return useCherryBomb(crafting);
            }
            case CraftingType.BOMB -> {
                goods.removeLast();
                return useBomb(crafting);
            }
            case CraftingType.MEGA_BOMB -> {
                goods.removeLast();
                return useMegaBomb(crafting);
            }
            case CraftingType.SPRINKLER -> {
                return useSprinkler(crafting);
            }
            case CraftingType.QUALITY_SPRINKLER -> {
                return useQualitySprinkler(crafting);
            }
            case CraftingType.IRIDIUM_SPRINKLER -> {
                return useIridiumSprinkler(crafting);
            }
            case CraftingType.CHARCOAL_KILN -> {
                return useCharcoalKiln(crafting, ourIngredients);
            }
            case CraftingType.FURNACE -> {
                return useFurnace(crafting, ourIngredients);
            }
            case CraftingType.SCARECROW -> {
                goods.removeLast();
                return useScarecrow(crafting);
            }
            case CraftingType.DELUXE_SCARECROW -> {
                goods.removeLast();
                return useDeluxeScarecrow(crafting);
            }
            case CraftingType.BEE_HOUSE -> {
                return useBeeHouse(crafting);
            }
            case CraftingType.CHEESE_PRESS -> {
                return useCheesePress(crafting, ourIngredients);
            }
            case CraftingType.KEG -> {
                return useKeg(crafting);
            }
            case CraftingType.LOOM -> {
                return useLoom(crafting);
            }
            case CraftingType.MAYONNAISE_MACHINE -> {
                return useMayonnaiseMachine(crafting);
            }
            case CraftingType.OIL_MAKER -> {
                return useOilMaker(crafting);
            }
            case CraftingType.PRESERVES_JAR -> {
                return usePreservesJar(crafting);
            }
            case CraftingType.DEHYDRATOR -> {
                return useDehydrator(crafting);
            }
            case CraftingType.FISH_SMOKER -> {
                return useFishSmoker(crafting);
            }
            case CraftingType.MYSTIC_TREE_SEED -> {
                return useMysticTreeSeed(crafting);
            }
            case CraftingType.CASK -> {
                return useCask(crafting);
            }
        }

        return new Result(false, "No Crafting found!");
    }

    private static void eliminateTileGoods(Coordinate coordinate, int i) {
        for (int j = 0; j < 8; j++) {
            Coordinate coordinate1 = new Coordinate(coordinate.getX() + i * Coordinate.coordinates.get(j).getX(),
                    coordinate.getY() + i * Coordinate.coordinates.get(j).getY());

            Tile tile = App.getCurrentGame().getMap().findTile(coordinate1);
            if(tile != null) {
                tile.getGoods().clear();
            }
        }
    }

    private static Result useCherryBomb(Crafting crafting) {
        Coordinate coordinate = App.getCurrentGame().getCurrentPlayer().getCoordinate();

        for (int i = 1; i <= 3; i++) {
            eliminateTileGoods(coordinate, i);
        }

        return new Result(true, crafting.getName() + " has been used");
    }

    private static Result useBomb(Crafting crafting) {
        Coordinate coordinate = App.getCurrentGame().getCurrentPlayer().getCoordinate();

        for (int i = 1; i <= 5; i++) {
            eliminateTileGoods(coordinate, i);
        }

        return new Result(true, crafting.getName() + " has been used");    }

    private static Result useMegaBomb(Crafting crafting) {
        Coordinate coordinate = App.getCurrentGame().getCurrentPlayer().getCoordinate();

        for (int i = 1; i <= 7; i++) {
            eliminateTileGoods(coordinate, i);
        }

        return new Result(true, crafting.getName() + " has been used");
    }


    private static Result useSprinkler(Crafting crafting) {
        Tile tile = App.getCurrentGame().getMap().findTile(App.getCurrentGame().getCurrentPlayer().getCoordinate());
        tile.getGoods().add(crafting);

        return new Result(true, crafting.getName() + " has been added to tile " + tile.getCordinate());
    }

    private static Result useQualitySprinkler(Crafting crafting) {
        Tile tile = App.getCurrentGame().getMap().findTile(App.getCurrentGame().getCurrentPlayer().getCoordinate());
        tile.getGoods().add(crafting);

        return new Result(true, crafting.getName() + " has been added to tile " + tile.getCordinate());
    }

    private static Result useIridiumSprinkler(Crafting crafting) {
        Tile tile = App.getCurrentGame().getMap().findTile(App.getCurrentGame().getCurrentPlayer().getCoordinate());
        tile.getGoods().add(crafting);

        return new Result(true, crafting.getName() + " has been added to tile " + tile.getCordinate());
    }

    private static Result useCharcoalKiln(Crafting crafting, ArrayList<String> ourIngredients) {
        Quadruple<GoodType, Integer, Double, Double> ingredient = ((CraftingType) crafting.getType()).getArtisanTypes().getFirst().getIngredients().getFirst();

        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.getFirst());
        if(goods == null || goods.getFirst().getType() != ingredient.a || goods.size() < ingredient.b) {
            return new Result(false, "You don't have " + ingredient.b + " number of " + ingredient.a.getName() + " in your inventory");
        }

        for (int i = 0; i < ingredient.b; i++)
            goods.removeLast();

        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(Good.newGoods(ingredient.a, 1));
        return new Result(true, crafting.getName() + " has been used & A " + ingredient.a.getName() +
                " has been added to your inventory");
    }

    private static Result useFurnace(Crafting crafting, ArrayList<String> ourIngredients) {
        ArrayList<Good> metalGoods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.getFirst());
        ArrayList<Good> coalGoods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.get(1));
        Quadruple<GoodType, Integer, Double, Double> metalIngredient = ((CraftingType) crafting.getType()).getArtisanTypes().getFirst().hasIngredient(metalGoods.getFirst().getType());
        Quadruple<GoodType, Integer, Double, Double> coalIngredient = ((CraftingType) crafting.getType()).getArtisanTypes().getFirst().hasIngredient(metalGoods.get(1).getType());

        if(metalGoods == null || coalGoods == null
                || metalIngredient == null ||
                coalIngredient == null ||
                metalGoods.size() < metalIngredient.b || coalGoods.size() < coalIngredient.b) {
            return new Result(false, "You don't have enough ingredients in your inventory!");
        }

        Good good = Good.newGood(metalIngredient.a);
        ((Artisan) good).setGoodType(metalGoods.getFirst().getType());
        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(new ArrayList<>(Arrays.asList(good)));

        coalGoods.removeLast();
        for (int i = 0; i < metalIngredient.b; i++)
            metalGoods.removeLast();

        return new Result(true, "A " + metalIngredient.a.getName() + " has been added to your inventory");
    }

    private static Result useScarecrow(Crafting crafting) {
        Tile tile = App.getCurrentGame().getMap().findTile(App.getCurrentGame().getCurrentPlayer().getCoordinate());
        tile.getGoods().add(crafting);

        return new Result(true, crafting.getName() + " has been added to " + App.getCurrentGame().getCurrentPlayer().getCoordinate());
    }

    private static Result useDeluxeScarecrow(Crafting crafting) {
        Tile tile = App.getCurrentGame().getMap().findTile(App.getCurrentGame().getCurrentPlayer().getCoordinate());
        tile.getGoods().add(crafting);

        return new Result(true, crafting.getName() + " has been added to " + App.getCurrentGame().getCurrentPlayer().getCoordinate());
    }

    private static Result useBeeHouse(Crafting crafting) {
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().findTile(App.getCurrentGame().getCurrentPlayer().getCoordinate());
        if(tile == null)
            return new Result(false, "You should be in your farm to use " + crafting.getName() + "!");

        Good good = Good.newGood(((CraftingType) crafting.getType()).getArtisanTypes().getFirst());
        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(new ArrayList<>(Arrays.asList(good)));
        return new Result(true, "You have extracted honey by " + good.getName());
    }

    private static Result useCheesePress(Crafting crafting, ArrayList<String> ourIngredients) {
        ArrayList<Good> milks = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.getFirst());
        Quadruple<GoodType, Integer, Double, Double> milkIngredient = ((CraftingType) crafting.getType()).getArtisanTypes().getFirst().hasIngredient(milks.getFirst().getType());
        if(milkIngredient == null)
            milkIngredient = ((CraftingType) crafting.getType()).getArtisanTypes().get(1).hasIngredient(milks.getFirst().getType())

        if(milks == null
                || milkIngredient == null ||
                milks.size() < milkIngredient.b) {
            return new Result(false, "You don't have enough ingredients in your inventory!");
        }

        Good good = Good.newGood(milkIngredient.a);
        ((Artisan) good).setGoodType(milks.getFirst().getType());
        milks.removeLast();

        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(new ArrayList<>(Arrays.asList(good)));
        return new Result(true, "You have extracted " + good.getName() + " by " + good.getName());
    }

    private static Result useKeg(Crafting crafting, ArrayList<String> ourIngredients) {
        ArrayList<Good> ingredients = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.getFirst());


    }

    private static Result useLoom(Crafting crafting) {
        // Implementation for Loom
    }

    private static Result useMayonnaiseMachine(Crafting crafting) {
        // Implementation for Mayonnaise Machine
    }

    private static Result useOilMaker(Crafting crafting) {
        // Implementation for Oil Maker
    }

    private static Result usePreservesJar(Crafting crafting) {
        // Implementation for Preserves Jar
    }

    private static Result useDehydrator(Crafting crafting) {
        // Implementation for Dehydrator
    }

    private static Result useFishSmoker(Crafting crafting) {
        // Implementation for Fish Smoker
    }

    private static Result useMysticTreeSeed(Crafting crafting) {
        // Implementation for Mystic Tree Seed
    }

    private static Result useCask(Crafting crafting) {
        // Implementation for Cask
    }
}


