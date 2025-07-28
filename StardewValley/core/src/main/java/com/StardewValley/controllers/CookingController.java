package com.StardewValley.controllers;

import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Pair;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.recipes.CookingRecipe;
import com.StardewValley.models.goods.recipes.CookingRecipeType;
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

public class CookingController {
    private final GameView gameView;
    private final ArrayList<Pair<Pair<ImageButton, Image>, Integer>> cookingRecipe;

    private final TextureRegionDrawable drawableSlot;
    private final ButtonGroup<ImageButton> buttonGroup;

    public CookingController(GameView gameView) {
        this.gameView = gameView;
        cookingRecipe = new ArrayList<>();

        drawableSlot = Assets.getInstance().getDrawableSlot();

        buttonGroup = new ButtonGroup<>();
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(0);
        buttonGroup.setUncheckLast(true);

        refreshRecipes();
    }

    public void refreshRecipes() {
        cookingRecipe.clear();
        buttonGroup.clear();

        CookingRecipeType[] recipeTypes = CookingRecipeType.values();

        ArrayList<CookingRecipeType> playerRecipes = new ArrayList<>();
        for (CookingRecipe recipe : App.getCurrentGame().getCurrentPlayer().getCookingRecipes()) {
            playerRecipes.add(recipe.getType());
        }

        for (int i = 0; i < recipeTypes.length; i++) {
            CookingRecipeType type = recipeTypes[i];
            GoodType goodType = type.getGoodType();

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

                            CookingRecipeType clickedType = recipeTypes[index];
                            if (clickedType != null) {
                                gameView.showRecipeDetails(clickedType);
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


            cookingRecipe.add(new Pair<>(new Pair<>(slotButton, itemImage), i));
            buttonGroup.add(slotButton);
        }
    }

    public void updateRecipes() {
    }

    public ArrayList<Pair<Pair<ImageButton, Image>, Integer>> getCookingRecipe() {
        return cookingRecipe;
    }
}
