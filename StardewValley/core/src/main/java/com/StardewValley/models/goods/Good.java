package com.StardewValley.models.goods;

import com.StardewValley.models.goods.artisans.Artisan;
import com.StardewValley.models.goods.artisans.ArtisanType;
import com.StardewValley.models.goods.craftings.Crafting;
import com.StardewValley.models.goods.craftings.CraftingType;
import com.StardewValley.models.goods.farmings.*;
import com.StardewValley.models.goods.fishs.Fish;
import com.StardewValley.models.goods.fishs.FishType;
import com.StardewValley.models.goods.foods.Food;
import com.StardewValley.models.goods.foods.FoodType;
import com.StardewValley.models.goods.foragings.*;
import com.StardewValley.models.goods.products.Product;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.goods.recipes.CookingRecipe;
import com.StardewValley.models.goods.recipes.CookingRecipeType;
import com.StardewValley.models.goods.recipes.CraftingRecipe;
import com.StardewValley.models.goods.recipes.CraftingRecipeType;
import com.StardewValley.models.goods.tools.Tool;
import com.StardewValley.models.goods.tools.ToolType;
import com.StardewValley.models.interactions.Animals.AnimalProduct;
import com.StardewValley.models.interactions.Animals.AnimalProductsType;

import java.util.ArrayList;

public abstract class Good {

    public abstract String getName();
    public abstract int getSellPrice();
    public abstract GoodType getType();

    protected int price;
    protected String name;


    public static Good newGood(GoodType type) {
        if(type instanceof CraftingType)
            return new Crafting((CraftingType)type);

        if(type instanceof FarmingTreeType)
            return new FarmingTree((FarmingTreeType) type);
        if(type instanceof FarmingTreeSaplingType)
            return new FarmingTreeSapling((FarmingTreeSaplingType) type);
        if(type instanceof FarmingCropType)
            return new FarmingCrop((FarmingCropType) type);

        if(type instanceof FishType)
            return new Fish((FishType) type , GoodLevel.ORDINARY);

        if(type instanceof FoodType)
            return new Food((FoodType) type);

        if(type instanceof ArtisanType)
            return new Artisan((ArtisanType) type);

        if(type instanceof ForagingTreeType)
            return new ForagingTree((ForagingTreeType) type);
        if(type instanceof ForagingMixedSeedType)
            return new ForagingMixedSeed((ForagingMixedSeedType) type);
        if(type instanceof ForagingMineralType)
            return new ForagingMineral((ForagingMineralType) type);
        if(type instanceof ForagingSeedType)
            return new ForagingSeed((ForagingSeedType) type);
        if(type instanceof ForagingCropType)
            return new ForagingCrop((ForagingCropType) type);

        if(type instanceof ProductType)
            return new Product((ProductType) type);

        if(type instanceof CookingRecipeType)
            return new CookingRecipe((CookingRecipeType) type);
        if(type instanceof CraftingRecipeType)
            return new CraftingRecipe((CraftingRecipeType) type);

        if(type instanceof ToolType)
            return new Tool((ToolType) type);
        if (type instanceof AnimalProductsType)
            return new AnimalProduct((AnimalProductsType) type , GoodLevel.ORDINARY);

        return null;
    }

    public static ArrayList<Good> newGoods(GoodType type, int number) {
        ArrayList<Good> goods = new ArrayList<>();
        for(int i = 0; i < number; i++)
            goods.add(newGood(type));
        return goods;
    }

    public static Good newFish(GoodType type , GoodLevel fishQuality) {
        if(type instanceof FishType)
            return new Fish((FishType) type , fishQuality);
        return null;
    }
    public static ArrayList<Good> newFishs(GoodType type, int number, GoodLevel fishQuality) {
        ArrayList<Good> goods = new ArrayList<>();
        for(int i = 0; i < number; i++)
            goods.add(newFish(type , fishQuality));
        return goods;
    }

    public static GoodType newGoodType(String typeName) {

        typeName = typeName.trim();

        for (CraftingType value : CraftingType.values()) {
            if(value.getName().equals(typeName))
                return value;
        }

        for (FarmingTreeSaplingType value : FarmingTreeSaplingType.values()) {
            if(value.getName().equals(typeName))
                return value;
        }

        for (FarmingTreeType value : FarmingTreeType.values()) {
            if(value.getName().equals(typeName))
                return value;
        }

        for (FarmingCropType value : FarmingCropType.values()) {
            if(value.getName().equals(typeName))
                return value;
        }

        for (FishType value : FishType.values()) {
            if(value.getName().equals(typeName))
                return value;
        }

        for (FoodType value : FoodType.values()) {
            if(value.getName().equals(typeName))
                return value;
        }

        for (ArtisanType value : ArtisanType.values()) {
            if(value.getName().equals(typeName))
                return value;
        }

        for (ForagingTreeType value : ForagingTreeType.values()) {
            if(value.getName().equals(typeName))
                return value;
        }

        for (ForagingMixedSeedType value : ForagingMixedSeedType.values()) {
            if(value.getName().equals(typeName))
                return value;
        }

        for (ForagingMineralType value : ForagingMineralType.values()) {
            if(value.getName().equals(typeName))
                return value;
        }

        for (ForagingSeedType value : ForagingSeedType.values()) {
            if(value.getName().equals(typeName)) {
                return value;
            }
        }

        for (ForagingCropType value : ForagingCropType.values()) {
            if(value.getName().equals(typeName))
                return value;
        }

        for (ProductType value : ProductType.values()) {
            if(value.getName().equals(typeName))
                return value;
        }

        for (CookingRecipeType value : CookingRecipeType.values()) {
            if(value.getName().equals(typeName))
                return value;
        }

        for (CraftingRecipeType value : CraftingRecipeType.values()) {
            if(value.getName().equals(typeName))
                return value;
        }

        for (ToolType value : ToolType.values()) {
            if(value.getName().equals(typeName))
                return value;
        }

        for (AnimalProductsType value : AnimalProductsType.values()) {
            if(value.getName().equals(typeName))
                return value;
        }

        return null;
    }
}


