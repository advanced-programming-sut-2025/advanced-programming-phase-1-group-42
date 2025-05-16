package org.example.models.goods.artisans;

import org.example.models.App;
import org.example.models.Result;
import org.example.models.game_structure.Coordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.craftings.Crafting;
import org.example.models.goods.craftings.CraftingType;
import org.example.models.goods.farmings.FarmingTreeSaplingType;
import org.example.models.goods.products.ProductType;
import org.example.models.interactions.game_buildings.Quadruple;

import java.util.ArrayList;
import java.util.Arrays;

public class ArtisanFunctions {
    public static Result useArtisan(String artisanName, ArrayList<String> ourIngredients) {
        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(artisanName);
        Crafting crafting = null;
        if(goods != null && !goods.isEmpty()) {
            crafting = (Crafting) goods.getLast();
        }
        else {
            return new Result(false, "You don't have " + artisanName + " in your inventory");
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
                return useKeg(crafting, ourIngredients);
            }
            case CraftingType.LOOM -> {
                return useLoom(crafting, ourIngredients);
            }
            case CraftingType.MAYONNAISE_MACHINE -> {
                return useMayonnaiseMachine(crafting, ourIngredients);
            }
            case CraftingType.OIL_MAKER -> {
                return useOilMaker(crafting, ourIngredients);
            }
            case CraftingType.PRESERVES_JAR -> {
                return usePreservesJar(crafting, ourIngredients);
            }
            case CraftingType.DEHYDRATOR -> {
                return useDehydrator(crafting, ourIngredients);
            }
            case CraftingType.FISH_SMOKER -> {
                return useFishSmoker(crafting, ourIngredients);
            }
            case CraftingType.MYSTIC_TREE_SEED -> {
                return useMysticTreeSeed(crafting);
            }
            case CraftingType.CASK -> {
                return useCask(crafting, ourIngredients);
            }
            case CraftingType.GRASS_STARTER -> {
                goods.removeLast();
                return useGrassStarter(crafting);
            }
        }

        return new Result(false, "No Crafting found!");
    }

    private static void eliminateTileGoods(Coordinate coordinate, int i) {
        for (int j = coordinate.getX() + (i * Coordinate.coordinates.get(7).getX());
        j < coordinate.getX() + (i * Coordinate.coordinates.get(3).getX()); j++) {
            for (int k = coordinate.getY() + (i * Coordinate.coordinates.get(7).getY());
            k < coordinate.getY() + (i * Coordinate.coordinates.get(3).getY()); k++) {
                Coordinate coordinate1 = new Coordinate(j, k);
                
                Tile tile = App.getCurrentGame().getMap().findTile(coordinate1);
                if(tile != null) {
                    tile.getGoods().clear();
                }
            }
        }
    }

    private static Result multipleArtisan(Crafting crafting, ArrayList<String> ourIngredients) {
        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.getFirst());
        boolean flag = false;
        Quadruple<GoodType, Integer, Double, Double> ingredients = null;
        if(goods == null)
            return new Result(false, "You don't have " + ourIngredients.getFirst() + " in your inventory!");

        for (ArtisanType artisanType : ((CraftingType) crafting.getType()).getArtisanTypes()) {
            ingredients = artisanType.hasIngredient(goods.getFirst().getType());
            if(ingredients != null) {
                flag = true;
                break;
            }
        }

        if(!flag)
            return new Result(false, "There isn't any " + ourIngredients.getFirst() + " in your " + crafting.getName() + "ingredients!");
        if(goods.size() < ingredients.b)
            return new Result(false, "You don't have enough ingredients in your inventory!");

        Good good = Good.newGood(ingredients.a);
        ((Artisan) good).setGoodType(goods.getFirst().getType());
        for (int i = 0; i < ingredients.b; i++) {
            goods.removeLast();
        }

        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(new ArrayList<>(Arrays.asList(good)));
        return new Result(true, "You have extracted " + good.getName() + " by " + crafting.getName());
    }

    private static Result doubleArtisan(Crafting crafting, ArrayList<String> ourIngredients) {
        ArrayList<Good> fishGoods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.getFirst());
        ArrayList<Good> coalGoods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.get(1));
        if(fishGoods == null || coalGoods == null)
            return new Result(false, "You don't have enough ingredients in your inventory");

        Quadruple<GoodType, Integer, Double, Double> metalIngredient = ((CraftingType) crafting.getType()).getArtisanTypes().getFirst().hasIngredient(fishGoods.getFirst().getType());
        Quadruple<GoodType, Integer, Double, Double> coalIngredient = ((CraftingType) crafting.getType()).getArtisanTypes().getFirst().hasIngredient(fishGoods.get(1).getType());

        if(metalIngredient == null || coalIngredient == null )
            return new Result(false, "Your ingredients don't match the crafting necessary ingredients!");
        if(fishGoods.size() < metalIngredient.b || coalGoods.size() < coalIngredient.b) {
            return new Result(false, "You don't have enough ingredients in your inventory!");
        }

        Good good = Good.newGood(metalIngredient.a);
        ((Artisan) good).setGoodType(fishGoods.getFirst().getType());
        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(new ArrayList<>(Arrays.asList(good)));

        coalGoods.removeLast();
        for (int i = 0; i < metalIngredient.b; i++)
            fishGoods.removeLast();

        return new Result(true, "A " + metalIngredient.a.getName() + " has been added to your inventory");
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

        return new Result(true, crafting.getName() + " has been used");
    }

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
        return doubleArtisan(crafting, ourIngredients);
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
        return new Result(true, "You have extracted honey by " + crafting.getName());
    }

    private static Result useCheesePress(Crafting crafting, ArrayList<String> ourIngredients) {
        ArrayList<Good> milks = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.getFirst());
        if(milks == null)
            return new Result(false, "You don't have " + ourIngredients.getFirst() + " in your inventory");

        Quadruple<GoodType, Integer, Double, Double> milkIngredient = ((CraftingType) crafting.getType()).getArtisanTypes().getFirst().hasIngredient(milks.getFirst().getType());
        if(milkIngredient == null)
            milkIngredient = ((CraftingType) crafting.getType()).getArtisanTypes().get(1).hasIngredient(milks.getFirst().getType());

        if(milkIngredient == null)
            return new Result(false, "There isn't any " + ourIngredients.getFirst() + " in your " + crafting.getName() + "ingredients!");
        if(milks.size() < milkIngredient.b) {
            return new Result(false, "You don't have enough ingredients in your inventory!");
        }

        Good good = Good.newGood(milkIngredient.a);
        ((Artisan) good).setGoodType(milks.getFirst().getType());
        for (int i = 0; i < milkIngredient.b; i++)
            milks.removeLast();

        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(new ArrayList<>(Arrays.asList(good)));
        return new Result(true, "You have extracted " + good.getName() + " by " + crafting.getName());
    }

    private static Result useKeg(Crafting crafting, ArrayList<String> ourIngredients) {
        return multipleArtisan(crafting, ourIngredients);
    }

    private static Result useLoom(Crafting crafting, ArrayList<String> ourIngredients) {
        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.getFirst());
        if(goods == null)
            return new Result(false, "You don't have " + ourIngredients.getFirst() + " in your inventory!");

        Quadruple<GoodType, Integer, Double, Double> loomIngredient = ((CraftingType) crafting.getType()).
                getArtisanTypes().getFirst().hasIngredient(goods.getFirst().getType());

        if(loomIngredient == null)
            return new Result(false, "There isn't any " + ourIngredients.getFirst() + " in your " + crafting.getName() + "ingredients!");
        if(goods.size() < loomIngredient.b)
            return new Result(false, "You don't have enough ingredients in your inventory!");

        Good good = Good.newGood(loomIngredient.a);
        for (int i = 0; i < loomIngredient.b; i++) {
            goods.removeLast();
        }

        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(new ArrayList<>(Arrays.asList(good)));
        return new Result(true, "You have extracted " + good.getName() + " by " + crafting.getName());
    }

    private static Result useMayonnaiseMachine(Crafting crafting, ArrayList<String> ourIngredients) {
        return multipleArtisan(crafting, ourIngredients);
    }

    private static Result useOilMaker(Crafting crafting, ArrayList<String> ourIngredients) {
        return multipleArtisan(crafting, ourIngredients);
    }

    private static Result usePreservesJar(Crafting crafting, ArrayList<String> ourIngredients) {
        return multipleArtisan(crafting, ourIngredients);
    }

    private static Result useDehydrator(Crafting crafting, ArrayList<String> ourIngredients) {
        return multipleArtisan(crafting, ourIngredients);
    }

    private static Result useFishSmoker(Crafting crafting, ArrayList<String> ourIngredients) {
        return doubleArtisan(crafting, ourIngredients);
    }

    private static Result useMysticTreeSeed(Crafting crafting) {
        Tile tile = App.getCurrentGame().getMap().findTile(App.getCurrentGame().getCurrentPlayer().getCoordinate());
        tile.getGoods().add(Good.newGood(FarmingTreeSaplingType.MYSTIC_SAPLING));

        return new Result(true, "A Mystic_Sapling has been added to " +
                App.getCurrentGame().getCurrentPlayer().getCoordinate() + " with your " + crafting.getName() + "!");
    }

    private static Result useCask(Crafting crafting, ArrayList<String> ourIngredients) {
        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.getFirst());
        if(goods == null)
            return new Result(false, "You don't have " + ourIngredients.getFirst() + " in your inventory!");

        Quadruple<GoodType, Integer, Double, Double> loomIngredient = ((CraftingType) crafting.getType()).
                getArtisanTypes().getFirst().hasIngredient(goods.getFirst().getType());

        if(loomIngredient == null)
            return new Result(false, "There isn't any " + ourIngredients.getFirst() + " in your " + crafting.getName() + "ingredients!");
        if(goods.size() < loomIngredient.b)
            return new Result(false, "You don't have enough ingredients in your inventory!");

        Good good = Good.newGood(loomIngredient.a);
        for (int i = 0; i < loomIngredient.b; i++) {
            goods.removeLast();
        }

        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(new ArrayList<>(Arrays.asList(good)));
        return new Result(true, "You have extracted " + good.getName() + " by " + crafting.getName());
    }

    private static Result useGrassStarter(Crafting crafting) {
        Tile tile = App.getCurrentGame().getMap().findTile(App.getCurrentGame().getCurrentPlayer().getCoordinate());
        tile.getGoods().add(Good.newGood(ProductType.GRASS));

        return new Result(true, "A Grass has been added to " +
                App.getCurrentGame().getCurrentPlayer().getCoordinate() + " with your " + crafting.getName() + "!");
    }
}


