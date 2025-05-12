package org.example.models.goods.artisans;

import org.example.models.Pair;
import org.example.models.goods.GoodType;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.recipes.CraftingRecipeType;
import org.example.models.interactions.Animals.AnimalProductsType;

import java.util.ArrayList;
import java.util.Arrays;

public enum ArtisanType implements GoodType {

    HONEY("Honey", "It's a sweet syrup produced by bees.", new Pair<>(0, 75), 4 * 24, new ArrayList<>(
            Arrays.asList(new Pair<>(null, new Pair<>(0, 350)),
            CraftingRecipeType.BEE_HOUSE),

    CLOTH("Cloth", "A bolt of fine wool cloth.", new Pair<>(-1, -1), 4, new ArrayList<>(
                    Arrays.asList(new Pair<>(AnimalProductsType.RABBIT_WOOL, new Pair<>(0, 470)),
                            new Pair<>(AnimalProductsType.SHEEP_WOOL, new Pair<>(0, 470)))
            ), CraftingRecipeType.LOOM),

    CHEESE("Cheese", "It's your basic cheese.", new Pair<>(0.0, 100), 3, new ArrayList<>(
                            Arrays.asList(
                                    new Pair<>(FoodType.MILK, new Pair<>(1.0, 230.0)),
                                    new Pair<>(AnimalProductsType.LARGE_MILK, new Pair<>(1.0, 345.0))
                            ), CraftingRecipeType.CHEESE_PRESS),

    GOAT_CHEESE("Goat Cheese", "Soft cheese made from goat's milk.", new Pair<>(0.0, 100), 3, new ArrayList<>(
                                    Arrays.asList(
                                            new Pair<>(AnimalProductsType.GOAT_MILK, new Pair<>(1.0, 400.0)),
                                            new Pair<>(AnimalProductsType.LARGE_GOAT_MILK, new Pair<>(1.0, 600.0))
                                    ), CraftingRecipeType.CHEESE_PRESS),

    MAYONNAISE("Mayonnaise", "It looks spreadable.", new Pair<>(0.0, 50), 3, new ArrayList<>(
                                            Arrays.asList(
                                                    new Pair<>(FoodType.EGG, new Pair<>(1.0, 190.0)),
                                                    new Pair<>(GoodType.LARGE_EGG, new Pair<>(1.0, 237.0))
                                            ), CraftingRecipeType.MAYONNAISE_MACHINE),

    DUCK_MAYONNAISE("Duck Mayonnaise", "It's a rich, yellow mayonnaise.", new Pair<>(0.0, 75), 3, new ArrayList<>(
                                                    Arrays.asList(new Pair<>(AnimalProductsType.DUCK_EGG, new Pair<>(1.0, 375.0)))
                                                    , CraftingRecipeType.MAYONNAISE_MACHINE),

    DINOSAUR_MAYONNAISE("Dinosaur Mayonnaise", "It's thick and creamy, with a vivid green hue. It smells like grass and leather.", new Pair<>(0.0, 125), 3, new ArrayList<>(
                                                            Arrays.asList(new Pair<>(AnimalProductsType.DINOSAUR_EGG, new Pair<>(1.0, 800.0)))
                                                            , CraftingRecipeType.MAYONNAISE_MACHINE),

    TRUFFLE_OIL("Truffle Oil", "A gourmet cooking ingredient.", new Pair<>(0.0, 38), 6, new ArrayList<>(
                                                                    Arrays.asList(new Pair<>(AnimalProductsType.TRUFFLE, new Pair<>(1.0, 1065.0)))
                                                                    , CraftingRecipeType.OIL_MAKER),

    BEER("Beer", "Drink in moderation.", new Pair<>(0.0, 50), 24, new ArrayList<>(
                                                                    Arrays.asList(new Pair<>(AnimalProductsTyper.WHEAT, new Pair<>(1.0, 200.0)))
                                                            ), CraftingRecipeType.KEG),
    VINEGAR("Vinegar", "An aged fermented liquid used in many cooking recipes.", new Pair<>(0.0, 13), 10, new ArrayList<>(
                                                                    Arrays.asList(new Pair<>(GoodType.RICE, new Pair<>(1.0, 100.0)))
                                                            ), CraftingRecipeType.CASK),

    COFFEE("Coffee", "It smells delicious. This is sure to give you a boost.", new Pair<>(0.0, 75), 2, new ArrayList<>(
                                                                    Arrays.asList(new Pair<>(GoodType.COFFEE_BEAN, new Pair<>(5.0, 150.0)))
                                                            ), CraftingRecipeType.KEG),

    JUICE("Juice", "A sweet, nutritious beverage.", new Pair<>(2.0, 0), 4 * 24, new ArrayList<>(), CraftingRecipeType.KEG),

    MEAD("Mead", "A fermented beverage made from honey. Drink in moderation.", new Pair<>(0.0, 100), 10, new ArrayList<>(
                                                                            Arrays.asList(new Pair<>(GoodType.HONEY, new Pair<>(1.0, 300.0)))
                                                                            , CraftingRecipeType.KEG),

    PALE_ALE("Pale Ale", "Drink in moderation.", new Pair<>(0.0, 50), 3 * 24, new ArrayList<>(
                                                                            Arrays.asList(new Pair<>(GoodType.HOPS, new Pair<>(1.0, 300.0)))
                                                                    ), CraftingRecipeType.KEG),

    WINE("Wine", "Drink in moderation.", new Pair<>(1.75, 0), 7 * 24, new ArrayList<>(), CraftingRecipeType.KEG),

    PICKLES("Pickles", "A jar of your home-made pickles.", new Pair<>(1.75, 0), 6, new ArrayList<>(), CraftingRecipeType.PRESERVES_JAR),

    JELLY("Jelly", "Gooey.", new Pair<>(2.0, 0), 3 * 24, new ArrayList<>(), CraftingRecipeType.PRESERVES_JAR),

    DRIED_MUSHROOMS("Dried Mushrooms", "A package of gourmet mushrooms.", new Pair<>(0.0, 50), 24, new ArrayList<>(
                                                                            Arrays.asList(new Pair<>(null, new Pair<>(5.0, 7.5)))
                                                                    ), CraftingRecipeType.DEHYDRATOR),

    DRIED_FRUIT("Dried Fruit", "Chewy pieces of dried fruit.", new Pair<>(0.0, 75), 24, new ArrayList<>(
                                                                                    Arrays.asList(new Pair<>(null, new Pair<>(5.0, 7.5))
                                                                                    , CraftingRecipeType.DEHYDRATOR),

    RAISINS("Raisins", "It's said to be the Junimos' favorite food.", new Pair<>(0.0, 125), 24, new ArrayList<>(
                                                                                            Arrays.asList(new Pair<>(GoodType.GRAPES, new Pair<>(5.0, 600.0)))
                                                                                            , CraftingRecipeType.DEHYDRATOR),

    SMOKED_FISH("Smoked Fish", "A whole fish, smoked to perfection.", new Pair<>(1.5, 0), 1, new ArrayList<>(
                                                                                            Arrays.asList(new Pair<>(null, new Pair<>(1.0, 2.0)))
                                                                                    ), CraftingRecipeType.FISH_SMOKER),

    COAL("Coal", "Turns 10 pieces of wood into one piece of coal.", new Pair<>(0.0, 0), 1, new ArrayList<>(
                                                                                                    Arrays.asList(new Pair<>(GoodType.WOOD, new Pair<>(10.0, 50.0)))
                                                                                                    , CraftingRecipeType.CHARCOAL_KILN),

    METAL_BAR("Any metal bar", "Turns ore and coal into metal bars.", new Pair<>(0.0, 0), 4, new ArrayList<>(
                                                                                                            Arrays.asList(
                                                                                                                    new Pair<>(GoodType.ORE, new Pair<>(5.0, 0.0)),
                                                                                                                    new Pair<>(GoodType.COAL, new Pair<>(1.0, 0.0))
                                                                                                            ), CraftingRecipeType.FURNACE),

    OIL("Oil", "All purpose cooking oil.", new Pair<>(0.0, 13), 6, new ArrayList<>(Arrays.asList(new Pair<>(GoodType.CORN, new Pair<>(1.0, 100.0)),
                                                                                                                    new Pair<>(GoodType.SUNFLOWER_SEEDS, new Pair<>(1.0, 100.0)),
                                                                                                                    new Pair<>(GoodType.SUNFLOWER, new Pair<>(1.0, 100.0)))
                                                                                                    ), CraftingRecipeType.OIL_MAKER);

    private final String name;
    private final String description;
    private final Pair<Double, Integer> energyEquation;
    private final int processingHour;
    private final ArrayList<Pair<GoodType, Pair<Double, Double>>> ingredients;
    private final CraftingRecipeType craftingRecipeType;

    ArtisanType(String name, String description, Pair<Double, Integer> energyEquation, int processingHour,
                ArrayList<Pair<GoodType, Pair<Double, Double>>> ingredients, CraftingRecipeType craftingRecipeType) {
        this.name = name;
        this.description = description;
        this.energyEquation = energyEquation;
        this.processingHour = processingHour;
        this.ingredients = ingredients;
        this.craftingRecipeType = craftingRecipeType;
    }
}


