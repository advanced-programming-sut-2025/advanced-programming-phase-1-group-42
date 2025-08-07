package com.StardewValley.controllers;

import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Pair;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.recipes.CraftingRecipe;
import com.StardewValley.models.goods.recipes.CraftingRecipeType;
import com.StardewValley.views.GameView;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class CraftingController {

    public CraftingController() {
    }

    public ArrayList<CraftingRecipeType> getUnlockedRecipes() {
        ArrayList<CraftingRecipeType> unlocked = new ArrayList<>();
        for (CraftingRecipe recipe : App.getCurrentGame().getCurrentPlayer().getCraftingRecipes()) {
            unlocked.add((CraftingRecipeType) recipe.getType());
        }
        return unlocked;
    }

    public CraftingRecipeType[] getAllRecipes() {
        return CraftingRecipeType.values();
    }
}
