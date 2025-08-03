package com.StardewValley.server.controllers;

import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Pair;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.recipes.CraftingRecipe;
import com.StardewValley.models.goods.recipes.CraftingRecipeType;
import com.StardewValley.client.views.GameView;
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
    private final GameView gameView;
    private final ArrayList<Pair<Pair<ImageButton, Image>, Integer>> craftingRecipes;

    private final TextureRegionDrawable drawableSlot;
    private final ButtonGroup<ImageButton> buttonGroup;

    public CraftingController(GameView gameView) {
        this.gameView = gameView;
        craftingRecipes = new ArrayList<>();

        drawableSlot = Assets.getInstance().getDrawableSlot();

        buttonGroup = new ButtonGroup<>();
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(0);
        buttonGroup.setUncheckLast(true);

        refreshRecipes();
    }

    public void refreshRecipes() {
        craftingRecipes.clear();
        buttonGroup.clear();

        CraftingRecipeType[] recipeTypes = CraftingRecipeType.values();

        ArrayList<CraftingRecipeType> playerRecipes = new ArrayList<>();
        for (CraftingRecipe recipe : App.getCurrentGame().getCurrentPlayer().getCraftingRecipes()) {
            playerRecipes.add((CraftingRecipeType) recipe.getType());
        }

        for (int i = 0; i < recipeTypes.length; i++) {
            CraftingRecipeType type = recipeTypes[i];
            GoodType goodType = type.getCraftingType();

            ImageButton slotButton = new ImageButton(drawableSlot);
            slotButton.setProgrammaticChangeEvents(false);

            Image itemImage;
            if (goodType != null) {
                itemImage = new Image(new TextureRegion(new Texture(goodType.imagePath())));
                if (!playerRecipes.contains(type)) {
                    itemImage.setColor(0.5f, 0.5f, 0.5f, 1f);

                } else {
                    final int index = i;

                    slotButton.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            if (!slotButton.isChecked()) {
                                slotButton.setChecked(true);
                            }

                            CraftingRecipeType clickedType = recipeTypes[index];
                            if (clickedType != null) {
                                gameView.showCraftingRecipeDetails(clickedType);
                            }
                        }
                    });


                    itemImage.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            slotButton.setChecked(true);
                            slotButton.fire(new InputEvent() {{
                                setType(Type.touchDown);
                            }});
                            slotButton.fire(new InputEvent() {{
                                setType(Type.touchUp);
                            }});
                        }
                    });

                }
            } else {
                itemImage = new Image(new TextureRegion(new Texture("GameAssets/null.png")));
            }


            craftingRecipes.add(new Pair<>(new Pair<>(slotButton, itemImage), i));
            buttonGroup.add(slotButton);
        }
    }

//    public void updateRecipes() {
//    }

    public ArrayList<Pair<Pair<ImageButton, Image>, Integer>> getCraftingRecipes() {
        return craftingRecipes;
    }
}
