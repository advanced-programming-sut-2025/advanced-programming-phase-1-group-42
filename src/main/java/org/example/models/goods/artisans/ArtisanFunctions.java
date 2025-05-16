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
import org.example.models.goods.foragings.ForagingMineralType;
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

    private static Result multipleArtisan(Crafting crafting, ArrayList<String> ourIngredients,
                                          ArrayList<ArtisanType> ingredients) {
        if(ourIngredients.isEmpty())
            return new Result(false, "You should input your desired ingredients!");

        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.getFirst());
        ArtisanType artisanTypeOrginal = null;
        boolean flag = false;
        Quadruple<GoodType, Integer, Double, Double> ingredient = null;
        if(goods == null)
            return new Result(false, "You don't have " + ourIngredients.getFirst() + " in your inventory!");

        for (ArtisanType artisanType : ingredients) {
            ingredient = artisanType.hasIngredient(goods.getFirst().getType());
            artisanTypeOrginal = artisanType;
            if(ingredient != null) {
                flag = true;
                break;
            }
        }

        if(!flag)
            return new Result(false, "There isn't any " + ourIngredients.getFirst() + " in your " + crafting.getName() + "ingredients!");
        if(goods.size() < ingredient.b)
            return new Result(false, "You don't have enough ingredients in your inventory!");

        Good good = Good.newGood(artisanTypeOrginal);
        ((Artisan) good).setGoodType(goods.getFirst().getType());
        for (int i = 0; i < ingredient.b; i++) {
            goods.removeLast();
        }

        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(new ArrayList<>(Arrays.asList(good)));
        return new Result(true, "You have extracted " + good.getName() + " by " + crafting.getName());
    }

    private static Result doubleArtisan(Crafting crafting, ArrayList<String> ourIngredients, ArtisanType ingredient1) {
        if(ourIngredients.size() < 2)
            return new Result(false, "You should input your desired ingredients!");

        ArrayList<Good> goods1 = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.getFirst());
        ArrayList<Good> goods2 = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.get(1));
        if(goods1 == null || goods2 == null)
            return new Result(false, "You don't have enough ingredients in your inventory");

        Quadruple<GoodType, Integer, Double, Double> metalIngredient = ingredient1.hasIngredient(goods1.getFirst().getType());
        Quadruple<GoodType, Integer, Double, Double> coalIngredient = ingredient1.hasIngredient(goods1.get(1).getType());

        if(metalIngredient == null || coalIngredient == null)
            return new Result(false, "Your ingredients don't match the crafting necessary ingredients!");
        if(goods1.size() < metalIngredient.b || goods2.size() < coalIngredient.b) {
            return new Result(false, "You don't have enough ingredients in your inventory!");
        }

        Good good = Good.newGood(ingredient1);
        ((Artisan) good).setGoodType(goods1.getFirst().getType());
        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(new ArrayList<>(Arrays.asList(good)));

        goods2.removeLast();
        for (int i = 0; i < metalIngredient.b; i++)
            goods1.removeLast();

        return new Result(true, "A " + good.getName() + " has been added to your inventory");
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
        Quadruple<GoodType, Integer, Double, Double> ingredient = ArtisanType.COAL.getIngredients().getLast();

        if(ourIngredients.isEmpty())
            return new Result(false, "You should input your desired ingredients!");

        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.getFirst());
        if(goods == null || goods.getFirst().getType() != ingredient.a || goods.size() < ingredient.b) {
            return new Result(false, "You don't have " + ingredient.b + " number of " + ingredient.a.getName() + " in your inventory");
        }

        for (int i = 0; i < ingredient.b; i++)
            goods.removeLast();

        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(Good.newGoods(ArtisanType.COAL, 1));
        return new Result(true, crafting.getName() + " has been used & A " + ArtisanType.COAL.getName() +
                " has been added to your inventory");
    }

    private static Result useFurnace(Crafting crafting, ArrayList<String> ourIngredients) {
        return doubleArtisan(crafting, ourIngredients, ArtisanType.METAL_BAR);
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

        Good good = Good.newGood(ArtisanType.HONEY);
        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(new ArrayList<>(Arrays.asList(good)));
        return new Result(true, "You have extracted Honey by " + crafting.getName());
    }

    private static Result useCheesePress(Crafting crafting, ArrayList<String> ourIngredients) {
        if(ourIngredients.isEmpty())
            return new Result(false, "You should input your desired ingredients!");

        ArrayList<Good> milks = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.getFirst());
        ArtisanType artisanType = null;
        if(milks == null)
            return new Result(false, "You don't have " + ourIngredients.getFirst() + " in your inventory");

        Quadruple<GoodType, Integer, Double, Double> milkIngredient = ArtisanType.CHEESE.hasIngredient(milks.getFirst().getType());
        artisanType = ArtisanType.CHEESE;
        if(milkIngredient == null) {
            milkIngredient = ArtisanType.GOAT_CHEESE.hasIngredient(milks.getFirst().getType());
            artisanType = ArtisanType.GOAT_CHEESE;
        }

        if(milkIngredient == null)
            return new Result(false, "There isn't any " + ourIngredients.getFirst() + " in your " + crafting.getName() + "ingredients!");
        if(milks.size() < milkIngredient.b) {
            return new Result(false, "You don't have enough ingredients in your inventory!");
        }

        Good good = Good.newGood(artisanType);
        ((Artisan) good).setGoodType(milks.getFirst().getType());
        for (int i = 0; i < milkIngredient.b; i++)
            milks.removeLast();

        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(new ArrayList<>(Arrays.asList(good)));
        return new Result(true, "You have extracted " + good.getName() + " by " + crafting.getName());
    }

    private static Result useKeg(Crafting crafting, ArrayList<String> ourIngredients) {
        return multipleArtisan(crafting, ourIngredients, new ArrayList<>(
                Arrays.asList(
                        ArtisanType.BEER,
                        ArtisanType.COFFEE,
                        ArtisanType.JUICE,
                        ArtisanType.MEAD,
                        ArtisanType.PALE_ALE,
                        ArtisanType.WINE,
                        ArtisanType.VINEGAR
                )
        ));
    }

    private static Result useLoom(Crafting crafting, ArrayList<String> ourIngredients) {
        if(ourIngredients.isEmpty())
            return new Result(false, "You should input your desired ingredients!");

        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.getFirst());
        if(goods == null)
            return new Result(false, "You don't have " + ourIngredients.getFirst() + " in your inventory!");

        Quadruple<GoodType, Integer, Double, Double> loomIngredient = ArtisanType.CLOTH.hasIngredient(goods.getFirst().getType());

        if(loomIngredient == null)
            return new Result(false, "There isn't any " + ourIngredients.getFirst() + " in your " + crafting.getName() + "ingredients!");
        if(goods.size() < loomIngredient.b)
            return new Result(false, "You don't have enough ingredients in your inventory!");

        Good good = Good.newGood(ArtisanType.CLOTH);
        for (int i = 0; i < loomIngredient.b; i++) {
            goods.removeLast();
        }

        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(new ArrayList<>(Arrays.asList(good)));
        return new Result(true, "You have extracted " + good.getName() + " by " + crafting.getName());
    }

    private static Result useMayonnaiseMachine(Crafting crafting, ArrayList<String> ourIngredients) {
        return multipleArtisan(crafting, ourIngredients, new ArrayList<>(Arrays.asList(
                ArtisanType.MAYONNAISE,
                ArtisanType.DUCK_MAYONNAISE,
                ArtisanType.DINOSAUR_MAYONNAISE
        )));
    }

    private static Result useOilMaker(Crafting crafting, ArrayList<String> ourIngredients) {
        return multipleArtisan(crafting, ourIngredients, new ArrayList<>(Arrays.asList(
                ArtisanType.TRUFFLE_OIL,
                ArtisanType.OIL
        )));
    }

    private static Result usePreservesJar(Crafting crafting, ArrayList<String> ourIngredients) {
        return multipleArtisan(crafting, ourIngredients, new ArrayList<>(Arrays.asList(
                ArtisanType.PICKLES,
                ArtisanType.JELLY
        )));
    }

    private static Result useDehydrator(Crafting crafting, ArrayList<String> ourIngredients) {
        return multipleArtisan(crafting, ourIngredients,
                new ArrayList<>(Arrays.asList(
                        ArtisanType.DRIED_MUSHROOMS,
                        ArtisanType.DRIED_FRUIT,
                        ArtisanType.RAISINS
                )));
    }

    private static Result useFishSmoker(Crafting crafting, ArrayList<String> ourIngredients) {
        return doubleArtisan(crafting, ourIngredients, ArtisanType.SMOKED_FISH);
    }

    private static Result useMysticTreeSeed(Crafting crafting) {
        Tile tile = App.getCurrentGame().getMap().findTile(App.getCurrentGame().getCurrentPlayer().getCoordinate());
        tile.getGoods().add(Good.newGood(FarmingTreeSaplingType.MYSTIC_SAPLING));

        return new Result(true, "A Mystic_Sapling has been added to " +
                App.getCurrentGame().getCurrentPlayer().getCoordinate() + " with your " + crafting.getName() + "!");
    }

    private static Result useCask(Crafting crafting, ArrayList<String> ourIngredients) {
        if(ourIngredients.isEmpty())
            return new Result(false, "You should input your desired ingredients!");

        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ourIngredients.getFirst());
        if(goods == null)
            return new Result(false, "You don't have " + ourIngredients.getFirst() + " in your inventory!");

        Quadruple<GoodType, Integer, Double, Double> loomIngredient = ArtisanType.VINEGAR.hasIngredient(goods.getFirst().getType());

        if(loomIngredient == null)
            return new Result(false, "There isn't any " + ourIngredients.getFirst() + " in your " + crafting.getName() + "ingredients!");
        if(goods.size() < loomIngredient.b)
            return new Result(false, "You don't have enough ingredients in your inventory!");

        Good good = Good.newGood(ArtisanType.VINEGAR);
        for (int i = 0; i < loomIngredient.b; i++) {
            goods.removeLast();
        }

        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(new ArrayList<>(Arrays.asList(good)));
        return new Result(true, "You have extracted " + ArtisanType.VINEGAR.getName() + " by " + crafting.getName());
    }

    private static Result useGrassStarter(Crafting crafting) {
        Tile tile = App.getCurrentGame().getMap().findTile(App.getCurrentGame().getCurrentPlayer().getCoordinate());
        tile.getGoods().add(Good.newGood(ProductType.GRASS));

        return new Result(true, "A Grass has been added to " +
                App.getCurrentGame().getCurrentPlayer().getCoordinate() + " with your " + crafting.getName() + "!");
    }
}


