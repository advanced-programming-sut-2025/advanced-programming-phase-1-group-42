package com.StardewValley.models.goods.artisans;

import com.StardewValley.models.Pair;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.craftings.CraftingType;
import com.StardewValley.models.goods.farmings.FarmingCropType;
import com.StardewValley.models.goods.fishs.FishType;
import com.StardewValley.models.goods.foods.FoodType;
import com.StardewValley.models.goods.foragings.ForagingCropType;
import com.StardewValley.models.goods.foragings.ForagingMineralType;
import com.StardewValley.models.goods.foragings.ForagingSeedType;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.interactions.Animals.AnimalProductsType;
import com.StardewValley.models.interactions.game_buildings.Quadruple;

import java.util.ArrayList;
import java.util.Arrays;

public enum ArtisanType implements GoodType {

    HONEY("Honey", "It's a sweet syrup produced by bees.", new Pair<>(0.0, 75), 4 * 24, new ArrayList<>(
            Arrays.asList(new Quadruple<>(null, 1, 0.0, 350.0))),
            CraftingType.BEE_HOUSE , "GameAssets/Artisan_good/Honey.png"),

    CLOTH("Cloth", "A bolt of fine wool cloth.", new Pair<>(0.0, 0), 4, new ArrayList<>(
            Arrays.asList(new Quadruple<>(AnimalProductsType.RABBIT_WOOL, 1, 0.0, 470.0),
                    new Quadruple<>(AnimalProductsType.SHEEP_WOOL, 1, 0.0, 470.0)))
            , CraftingType.LOOM, "GameAssets/Artisan_good/Cloth.png"),

    CHEESE("Cheese", "It's your basic cheese.", new Pair<>(0.0, 100), 3, new ArrayList<>(
            Arrays.asList(
                    new Quadruple<>(AnimalProductsType.COW_MILK, 1, 0.0, 230.0),
                    new Quadruple<>(AnimalProductsType.BIG_COW_MILK, 1, 0.0, 345.0)
            )), CraftingType.CHEESE_PRESS, "GameAssets/Artisan_good/Cheese.png"),

    GOAT_CHEESE("Goat_Cheese", "Soft cheese made from goat's milk.", new Pair<>(0.0, 100), 3, new ArrayList<>(
            Arrays.asList(
                    new Quadruple<>(AnimalProductsType.GOAT_MILK, 1, 0.0, 400.0),
                    new Quadruple<>(AnimalProductsType.BIG_GOAT_MILK, 1, 0.0, 600.0)
            )), CraftingType.CHEESE_PRESS, "GameAssets/Artisan_good/Goat_Cheese.png"),

    MAYONNAISE("Mayonnaise", "It looks spreadable.", new Pair<>(0.0, 50), 3, new ArrayList<>(
            Arrays.asList(
                    new Quadruple<>(AnimalProductsType.CHICKEN_EGG, 1, 0.0, 190.0),
                    new Quadruple<>(AnimalProductsType.BIG_CHICKEN_EGG, 1, 0.0, 237.0)
            )), CraftingType.MAYONNAISE_MACHINE,"GameAssets/Artisan_good/Mayonnaise.png"),

    DUCK_MAYONNAISE("Duck_Mayonnaise", "It's a rich, yellow mayonnaise.", new Pair<>(0.0, 75), 3, new ArrayList<>(
            Arrays.asList(new Quadruple<>(AnimalProductsType.DUCK_EGG, 1, 0.0, 190.0),
                    new Quadruple<>(AnimalProductsType.DINOSAUR_EGG, 1, 0.0, 237.0)))
            , CraftingType.MAYONNAISE_MACHINE,"GameAssets/Artisan_good/Duck_Mayonnaise.png"),

    DINOSAUR_MAYONNAISE("Dinosaur_Mayonnaise", "It's thick and creamy, with a vivid green hue. It smells like grass and leather.", new Pair<>(0.0, 125), 3, new ArrayList<>(
            Arrays.asList(new Quadruple<>(AnimalProductsType.DINOSAUR_EGG, 1, 0.0, 800.0)))
            , CraftingType.MAYONNAISE_MACHINE,"GameAssets/Artisan_good/Dinosaur_Mayonnaise.png"),

    TRUFFLE_OIL("Truffle_Oil", "A gourmet cooking ingredient.", new Pair<>(0.0, 38), 6, new ArrayList<>(
            Arrays.asList(new Quadruple<>(AnimalProductsType.TRUFFLE, 1, 0.0, 1065.0)))
            , CraftingType.OIL_MAKER,"GameAssets/Artisan_good/Truffle_Oil.png"),

    OIL("Oil", "All purpose cooking oil.", new Pair<>(0.0, 13), 6, new ArrayList<>(Arrays.asList(new Quadruple<>(FarmingCropType.CORN, 1, 1.0, 100.0),
            new Quadruple<>(ForagingSeedType.SUNFLOWER_SEEDS, 1, 0.0, 100.0),
            new Quadruple<>(FarmingCropType.SUNFLOWER, 1, 0.0, 100.0))), CraftingType.OIL_MAKER,
        "GameAssets/Artisan_good/Oil.png"),


    BEER("Beer", "Drink in moderation.", new Pair<>(0.0, 50), 24, new ArrayList<>(
            Arrays.asList(new Quadruple<>(FarmingCropType.WHEAT, 1, 0.0, 200.0))
    ), CraftingType.KEG,
        "GameAssets/Artisan_good/Beer.png"),

    VINEGAR("Vinegar", "An aged fermented liquid used in many cooking recipes.", new Pair<>(0.0, 13), 10, new ArrayList<>(
            Arrays.asList(new Quadruple<>(FoodType.RICE, 1, 0.0, 100.0))
    ), CraftingType.KEG, "GameAssets/Ingredient/Vinegar.png"),

    COFFEE("Coffee", "It smells delicious. This is sure to give you a boost.", new Pair<>(0.0, 75), 2, new ArrayList<>(
            Arrays.asList(new Quadruple<>(FarmingCropType.COFFEE_BEAN, 5, 0.0, 150.0))
    ), CraftingType.KEG,
        "GameAssets/Artisan_good/Coffee.png"),

    JUICE("Juice", "A sweet, nutritious beverage.", new Pair<>(2.0, 0), 4 * 24, new ArrayList<>(
            Arrays.asList(new Quadruple<>(FarmingCropType.ARTICHOKE, 1, 2.25, 0.0),
                    new Quadruple<>(FarmingCropType.BEET, 1, 2.25, 0.0),
                    new Quadruple<>(FarmingCropType.BLUEBERRY, 1, 2.25, 0.0), // Technically a fruit, but included if needed
                    new Quadruple<>(FarmingCropType.BOK_CHOY, 1, 2.25, 0.0),
                    new Quadruple<>(FarmingCropType.RED_CABBAGE, 1, 2.25, 0.0),
                    new Quadruple<>(FarmingCropType.CARROT, 1, 2.25, 0.0),
                    new Quadruple<>(FarmingCropType.CAULIFLOWER, 1, 2.25, 0.0),
                    new Quadruple<>(FarmingCropType.CORN, 1, 2.25, 0.0),
                    new Quadruple<>(FarmingCropType.EGGPLANT, 1, 2.25, 0.0),
                    new Quadruple<>(FarmingCropType.GARLIC, 1, 2.25, 0.0),
                    new Quadruple<>(FarmingCropType.GREEN_BEAN, 1, 2.25, 0.0),
                    new Quadruple<>(FarmingCropType.HOPS, 1, 2.25, 0.0), // Technically a flower, but brewed like a vegetable
                    new Quadruple<>(FarmingCropType.KALE, 1, 2.25, 0.0),
                    new Quadruple<>(FarmingCropType.MELON, 1, 2.25, 0.0), // Technically a fruit
                    new Quadruple<>(FarmingCropType.PARSNIP, 1, 2.25, 0.0),
                    new Quadruple<>(FarmingCropType.HOT_PEPPER, 1, 2.25, 0.0), // Technically a fruit
                    new Quadruple<>(FarmingCropType.POTATO, 1, 2.25, 0.0),
                    new Quadruple<>(FarmingCropType.PUMPKIN, 1, 2.25, 0.0),
                    new Quadruple<>(FarmingCropType.RADISH, 1, 2.25, 0.0),
                    new Quadruple<>(FarmingCropType.RED_CABBAGE, 1, 2.25, 0.0),
                    new Quadruple<>(FarmingCropType.RHUBARB, 1, 2.25, 0.0), // Technically a vegetable, despite being used like a fruit
                    new Quadruple<>(FarmingCropType.STARFRUIT, 1, 2.25, 0.0), // Technically a fruit
                    new Quadruple<>(FarmingCropType.SUMMER_SPANGLE, 1, 2.25, 0.0), // Flower, not a vegetable
                    new Quadruple<>(FarmingCropType.SWEET_GEM_BERRY, 1, 2.25, 0.0), // Technically a fruit
                    new Quadruple<>(FarmingCropType.TOMATO, 1, 2.25, 0.0), // Technically a fruit
                    new Quadruple<>(FarmingCropType.WHEAT, 1, 2.25, 0.0), // Grain, not a vegetable
                    new Quadruple<>(FarmingCropType.YAM, 1, 2.25, 0.0))),
            CraftingType.KEG,
        "GameAssets/Artisan_good/Juice.png"),

    MEAD("Mead", "A fermented beverage made from honey. Drink in moderation.", new Pair<>(0.0, 100), 10, new ArrayList<>(
            Arrays.asList(new Quadruple<>(ArtisanType.HONEY, 1, 0.0, 300.0)))
            , CraftingType.KEG,
        "GameAssets/Artisan_good/Mead.png"),

    PALE_ALE("Pale_Ale", "Drink in moderation.", new Pair<>(0.0, 50), 3 * 24, new ArrayList<>(
            Arrays.asList(new Quadruple<>(FarmingCropType.HOPS, 1, 0.0, 300.0))
    ), CraftingType.KEG,
        "GameAssets/Artisan_good/Pale_Ale.png"),

    WINE("Wine", "Drink in moderation.", new Pair<>(1.75, 0), 7 * 24,
            new ArrayList<>(// Tree Fruits (Year-round after maturity)
                    Arrays.asList(
                            new Quadruple<>(FoodType.APRICOT, 1, 3.0, 0.0),
                            new Quadruple<>(FoodType.CHERRY, 1, 3.0, 0.0),
                            new Quadruple<>(FoodType.ORANGE, 1, 3.0, 0.0),
                            new Quadruple<>(FoodType.PEACH, 1, 3.0, 0.0),
                            new Quadruple<>(FoodType.APPLE, 1, 3.0, 0.0),
                            new Quadruple<>(FoodType.POMEGRANATE, 1, 3.0, 0.0),

// Seasonal Fruits (Grown from seeds)
                            new Quadruple<>(FarmingCropType.STRAWBERRY, 1, 3.0, 0.0), // Spring
                            new Quadruple<>(FarmingCropType.BLUEBERRY, 1, 3.0, 0.0),  // Summer
                            new Quadruple<>(FarmingCropType.MELON, 1, 3.0, 0.0),      // Summer
                            new Quadruple<>(FarmingCropType.RED_CABBAGE, 1, 3.0, 0.0),// Summer (technically a veg, but used in kegs)
                            new Quadruple<>(FarmingCropType.CRANBERRY, 1, 3.0, 0.0),  // Fall
                            new Quadruple<>(FarmingCropType.GRAPE, 1, 3.0, 0.0),      // Summer/Fall (trellis)
                            new Quadruple<>(FarmingCropType.HOPS, 1, 3.0, 0.0),       // Summer (trellis, used for Pale Ale)

// Special Fruits (Foraging/Other)
                            new Quadruple<>(ForagingCropType.BLACKBERRY, 1, 3.0, 0.0), // Foraged (Fall)
                            new Quadruple<>(ForagingCropType.SALMONBERRY, 1, 3.0, 0.0),// Foraged (Spring)
                            new Quadruple<>(ForagingCropType.SPICE_BERRY, 1, 3.0, 0.0), // Foraged (Summer)
                            new Quadruple<>(ForagingCropType.WILD_HORSERADISH, 1, 3.0, 0.0) // Foraged (Spring, technically veg)

// Giant Crops (If included)
//                    new Quadruple<>(FarmingCropType.GIANT_MELON, 1, 3.0, 0.0),
//                    new Quadruple<>(FarmingCropType.GIANT_PUMPKIN, 1, 3.0, 0.0),
//                    new Quadruple<>(FarmingCropType.GIANT_CAULIFLOWER, 1, 3.0, 0.0))
                    ))
            , CraftingType.KEG,
        "GameAssets/Artisan_good/Wine.png"),

    PICKLES("Pickles", "A jar of your home-made pickles.", new Pair<>(1.75, 0), 6,
            new ArrayList<>(Arrays.asList(
                    new Quadruple<>(FarmingCropType.ARTICHOKE, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.BEET, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.BLUEBERRY, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.BOK_CHOY, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.RED_CABBAGE, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.CARROT, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.CAULIFLOWER, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.CORN, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.EGGPLANT, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.GARLIC, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.GREEN_BEAN, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.HOPS, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.KALE, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.MELON, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.PARSNIP, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.HOT_PEPPER, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.POTATO, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.PUMPKIN, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.RADISH, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.RED_CABBAGE, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.RHUBARB, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.STARFRUIT, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.SUMMER_SPANGLE, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.SWEET_GEM_BERRY, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.TOMATO, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.WHEAT, 1, 2.0, 50.0),
                    new Quadruple<>(FarmingCropType.YAM, 1, 2.0, 50.0)
            )), CraftingType.PRESERVES_JAR,
        "GameAssets/Artisan_good/Pickles.png"),

    JELLY("Jelly", "Gooey.", new Pair<>(2.0, 0), 3 * 24,
            new ArrayList<>(// Tree Fruits (FoodType)
                    Arrays.asList(new Quadruple<>(FoodType.APRICOT, 1, 2.0, 50.0),
                            new Quadruple<>(FoodType.CHERRY, 1, 2.0, 50.0),
                            new Quadruple<>(FoodType.ORANGE, 1, 2.0, 50.0),
                            new Quadruple<>(FoodType.PEACH, 1, 2.0, 50.0),
                            new Quadruple<>(FoodType.APPLE, 1, 2.0, 50.0),
                            new Quadruple<>(FoodType.POMEGRANATE, 1, 2.0, 50.0),

// Seasonal Fruits (FarmingCropType)
                            new Quadruple<>(FarmingCropType.STRAWBERRY, 1, 2.0, 50.0),    // Spring
                            new Quadruple<>(FarmingCropType.BLUEBERRY, 1, 2.0, 50.0),     // Summer
                            new Quadruple<>(FarmingCropType.MELON, 1, 2.0, 50.0),         // Summer
                            new Quadruple<>(FarmingCropType.RED_CABBAGE, 1, 2.0, 50.0),   // Summer (veg, but keg-compatible)
                            new Quadruple<>(FarmingCropType.CRANBERRY, 1, 2.0, 50.0),     // Fall
                            new Quadruple<>(FarmingCropType.GRAPE, 1, 2.0, 50.0),         // Summer/Fall (trellis)
                            new Quadruple<>(FarmingCropType.HOPS, 1, 2.0, 50.0),          // Summer (trellis, Pale Ale)

// Foraged Fruits (ForagingCropType)
                            new Quadruple<>(ForagingCropType.BLACKBERRY, 1, 2.0, 50.0),   // Fall
                            new Quadruple<>(ForagingCropType.SALMONBERRY, 1, 2.0, 50.0),  // Spring
                            new Quadruple<>(ForagingCropType.SPICE_BERRY, 1, 2.0, 50.0),  // Summer
                            new Quadruple<>(ForagingCropType.WILD_HORSERADISH, 1, 2.0, 50.0) // Spring (veg)

// Giant Crops (FarmingCropType)
//                    new Quadruple<>(FarmingCropType.GIANT_MELON, 1, 2.0, 50.0),
//                    new Quadruple<>(FarmingCropType.GIANT_PUMPKIN, 1, 2.0, 50.0),
//                    new Quadruple<>(FarmingCropType.GIANT_CAULIFLOWER, 1, 2.0, 50.0))
                    )), CraftingType.PRESERVES_JAR,
        "GameAssets/Artisan_good/Jelly.png"),

    DRIED_MUSHROOMS("Dried_Mushrooms", "A package of gourmet mushrooms.", new Pair<>(0.0, 50), 24, new ArrayList<>(
            Arrays.asList(new Quadruple<>(FoodType.COMMON_MUSHROOM, 5, 7.5, 25.0),
                    new Quadruple<>(ForagingCropType.RED_MUSHROOM, 5, 7.5, 25.0),
                    new Quadruple<>(ForagingCropType.PURPLE_MUSHROOM, 5, 7.5, 25.0),
                    new Quadruple<>(ForagingCropType.MOREL, 5, 7.5, 25.0),
                    new Quadruple<>(ForagingCropType.CHANTERELLE, 5, 7.5, 25.0))
    ), CraftingType.DEHYDRATOR,
        "GameAssets/Artisan_good/Dried_Mushrooms.png"),

    DRIED_FRUIT("Dried_Fruit", "Chewy pieces of dried fruit.", new Pair<>(0.0, 75), 24, new ArrayList<>(
            Arrays.asList(// Tree Fruits (FoodType → Adjusted to FarmingCropType if needed)
                    new Quadruple<>(FoodType.APRICOT, 5, 7.5, 25.0),
                    new Quadruple<>(FoodType.CHERRY, 5, 7.5, 25.0),
                    new Quadruple<>(FoodType.ORANGE, 5, 7.5, 25.0),
                    new Quadruple<>(FoodType.PEACH, 5, 7.5, 25.0),
                    new Quadruple<>(FoodType.APPLE, 5, 7.5, 25.0),
                    new Quadruple<>(FoodType.POMEGRANATE, 5, 7.5, 25.0),

// Seasonal Fruits (Grown from seeds)
                    new Quadruple<>(FarmingCropType.STRAWBERRY, 5, 7.5, 25.0), // Spring
                    new Quadruple<>(FarmingCropType.BLUEBERRY, 5, 7.5, 25.0),  // Summer
                    new Quadruple<>(FarmingCropType.MELON, 5, 7.5, 25.0),      // Summer
                    new Quadruple<>(FarmingCropType.RED_CABBAGE, 5, 7.5, 25.0),// Summer (technically veg, but used in kegs)
                    new Quadruple<>(FarmingCropType.CRANBERRY, 5, 7.5, 25.0),  // Fall
                    new Quadruple<>(FarmingCropType.GRAPE, 5, 7.5, 25.0),      // Summer/Fall (trellis)
                    new Quadruple<>(FarmingCropType.HOPS, 5, 7.5, 25.0),       // Summer (trellis, Pale Ale)

// Foraged Fruits (ForagingCropType → Adjusted if needed)
                    new Quadruple<>(ForagingCropType.BLACKBERRY, 5, 7.5, 25.0), // Fall
                    new Quadruple<>(ForagingCropType.SALMONBERRY, 5, 7.5, 25.0),// Spring
                    new Quadruple<>(ForagingCropType.SPICE_BERRY, 5, 7.5, 25.0), // Summer
                    new Quadruple<>(ForagingCropType.WILD_HORSERADISH, 5, 7.5, 25.0) // Spring (veg)

// Giant Crops
//                    new Quadruple<>(FarmingCropType.GIANT_MELON, 5, 7.5, 25.0),
//                    new Quadruple<>(FarmingCropType.GIANT_PUMPKIN, 5, 7.5, 25.0),
//                    new Quadruple<>(FarmingCropType.GIANT_CAULIFLOWER, 5, 7.5, 25.0))
            )), CraftingType.DEHYDRATOR,
        "GameAssets/Artisan_good/Red_Dried_Fruit.png"),

    RAISINS("Raisins", "It's said to be the Junimos' favorite food.", new Pair<>(0.0, 125), 24, new ArrayList<>(
            Arrays.asList(new Quadruple<>(FarmingCropType.GRAPE, 5, 0.0, 600.0)))
            , CraftingType.DEHYDRATOR,
        "GameAssets/Artisan_good/Raisins.png"),

    COAL("Coal", "Turns 10 pieces of wood into one piece of coal.", new Pair<>(0.0, 0), 1, new ArrayList<>(
            Arrays.asList(new Quadruple<>(ProductType.WOOD, 10, 0.0, 50.0)))
            , CraftingType.CHARCOAL_KILN,
        "GameAssets/Resource/Coal.png"),

    SMOKED_FISH("Smoked_Fish", "A whole fish, smoked to perfection.", new Pair<>(1.5, 0), 1, new ArrayList<>(
            Arrays.asList(
                    // Spring Fish
                    new Quadruple<>(FishType.FLOUNDER, 1, 2.0, 0.0),
                    new Quadruple<>(FishType.LIONFISH, 1, 2.0, 0.0),
                    new Quadruple<>(FishType.HERRING, 1, 2.0, 0.0),
                    new Quadruple<>(FishType.GHOSTFISH, 1, 2.0, 0.0),

                    // Summer Fish
                    new Quadruple<>(FishType.TILAPIA, 1, 2.0, 0.0),
                    new Quadruple<>(FishType.DORADO, 1, 2.0, 0.0),
                    new Quadruple<>(FishType.SUNFISH, 1, 2.0, 0.0),
                    new Quadruple<>(FishType.RAINBOW_TROUT, 1, 2.0, 0.0),

                    // Fall Fish
                    new Quadruple<>(FishType.SALMON, 1, 2.0, 0.0),
                    new Quadruple<>(FishType.SARDINE, 1, 2.0, 0.0),
                    new Quadruple<>(FishType.SHAD, 1, 2.0, 0.0),
                    new Quadruple<>(FishType.BLUE_DISCUS, 1, 2.0, 0.0),

                    // Winter Fish
                    new Quadruple<>(FishType.MIDNIGHT_CARP, 1, 2.0, 0.0),
                    new Quadruple<>(FishType.SQUID, 1, 2.0, 0.0),
                    new Quadruple<>(FishType.TUNA, 1, 2.0, 0.0),
                    new Quadruple<>(FishType.PERCH, 1, 2.0, 0.0),

                    // Legendary Fish
                    new Quadruple<>(FishType.LEGEND, 1, 2.0, 0.0),
                    new Quadruple<>(FishType.CRIMSONFISH, 1, 2.0, 0.0),
                    new Quadruple<>(FishType.ANGLER, 1, 2.0, 0.0),
                    new Quadruple<>(FishType.GLACIERFISH, 1, 2.0, 0.0),

                    // Coals
                    new Quadruple<>(ForagingMineralType.COAL, 1, 0.0, 0.0),
                    new Quadruple<>(ProductType.COAL, 1, 0.0, 0.0),
                    new Quadruple<>(ArtisanType.COAL, 1, 0.0, 0.0)
            )), CraftingType.FISH_SMOKER,
        "GameAssets/Fish/Smoked_Fish.png"),

    METAL_BAR("Metal_Bar", "Turns ore and coal into metal bars.", new Pair<>(0.0, 0), 4, new ArrayList<>(
            Arrays.asList(
                    new Quadruple<>(ForagingMineralType.COPPER_ORE, 5, 10.0, 0.0),
                    new Quadruple<>(ForagingMineralType.IRON_ORE, 5, 10.0, 0.0),
                    new Quadruple<>(ForagingMineralType.GOLD_ORE, 5, 10.0, 0.0),
                    new Quadruple<>(ForagingMineralType.IRIDIUM_ORE, 5, 10.0, 0.0),

                    //Coals
                    new Quadruple<>(ForagingMineralType.COAL, 1, 0.0, 0.0),
                    new Quadruple<>(ProductType.COAL, 1, 0.0, 0.0),
                    new Quadruple<>(ArtisanType.COAL, 1, 0.0, 0.0)
            )), CraftingType.FURNACE, "GameAssets/Resource/Iron_Bar.png"),

    DEHYDRATOR_RECIPE("", "", new Pair<>(0.0, 0), 0, new ArrayList<>(), null,
        "GameAssets/Crafting/Dehydrator.png");

    private final String name;
    private final String description;
    private final Pair<Double, Integer> energyEquation;
    private final int processingHour;
    private final ArrayList<Quadruple<GoodType, Integer, Double, Double>> ingredients;
    private final CraftingType craftingType;
    private final String imagePath;
    ArtisanType(String name, String description, Pair<Double, Integer> energyEquation, int processingHour,
                ArrayList<Quadruple<GoodType, Integer, Double, Double>> ingredients, CraftingType craftingType , String imagePath) {
        this.name = name;
        this.description = description;
        this.energyEquation = energyEquation;
        this.processingHour = processingHour;
        this.ingredients = ingredients;
        this.craftingType = craftingType;
        this.imagePath = imagePath;
    }

    @Override
    public int getSellPrice() {
        return -1;
    }

    @Override
    public int getEnergy() {
        return -1;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String imagePath() {
        return imagePath;
    }

    public int getEnergy(GoodType goodType) {
        if(energyEquation.first() == 0)
            return energyEquation.second();
        else {
            return (int) (goodType.getEnergy() * energyEquation.first()) + energyEquation.second();
        }
        //TODO
    }

    public int getSellPrice(GoodType goodType) {
        for (Quadruple<GoodType, Integer, Double, Double> ingredient : ingredients) {
            if(ingredient.a == goodType) {
                return (int) (ingredient.c * goodType.getSellPrice() + ingredient.d);
            }
        }
        return -1;
    }

    public ArrayList<Quadruple<GoodType, Integer, Double, Double>> getIngredients() {
        return ingredients;
    }

    public Quadruple<GoodType, Integer, Double, Double> hasIngredient(GoodType goodType) {
        for (Quadruple<GoodType, Integer, Double, Double> ingredient : ingredients) {
            if(ingredient.a == goodType) {
                return ingredient;
            }
        }
        return null;
    }

    public int getProcessingHour() {
        return processingHour;
    }

    public String getDescription() {
        return description;
    }
}


