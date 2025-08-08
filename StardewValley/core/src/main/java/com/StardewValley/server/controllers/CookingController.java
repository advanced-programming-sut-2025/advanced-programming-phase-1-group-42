package com.StardewValley.server.controllers;

import com.StardewValley.models.Assets;
import com.StardewValley.models.Pair;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.recipes.CookingRecipe;
import com.StardewValley.models.goods.recipes.CookingRecipeType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class CookingController {
    public CookingController() {
    }

    public ArrayList<CookingRecipeType> getUnlockedRecipes() {
        ArrayList<CookingRecipeType> unlocked = new ArrayList<>();
        for (CookingRecipe recipe : App.getCurrentGame().getCurrentPlayer().getCookingRecipes()) {
            unlocked.add(recipe.getType());
        }
        return unlocked;
    }

    public CookingRecipeType[] getAllRecipes() {
        return CookingRecipeType.values();
    }
}
