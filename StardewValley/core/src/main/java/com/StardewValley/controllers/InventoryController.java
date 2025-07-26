package com.StardewValley.controllers;

import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Pair;
import com.StardewValley.models.goods.Good;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class InventoryController {
    private final ArrayList<Pair<ImageButton, Image>> inventoryElements;
    private final ArrayList<Pair<Pair<ImageButton, Image>, Integer>> toolsElements;
    private final ArrayList<ImageButton> mainInventoryElements;
    private final ProgressBar progressBar;

    public InventoryController() {
        inventoryElements = new ArrayList<>();
        TextureRegionDrawable drawableSlot = Assets.getInstance().getDrawableSlot();
        TextureRegionDrawable drawableHighlight = Assets.getInstance().getDrawableHighlight();

        for (ArrayList<Good> goods : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            ImageButton imageButtonBackground = new ImageButton(drawableSlot, drawableSlot, drawableHighlight);
            Image image = new Image(new TextureRegion(new Texture("GameAssets/null.png")));
            if (!goods.isEmpty())
                image = new Image(new TextureRegion(new Texture(goods.getFirst().getType().imagePath())));

            Image finalImage = image;
            image.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    for (int i = 0; i < inventoryElements.size(); i++) {
                        Pair<ImageButton, Image> pair = inventoryElements.get(i);
                        pair.first().setChecked(false);
                        if (pair.second() == finalImage) {
                            pair.first().setChecked(true);
                            App.getCurrentGame().getCurrentPlayer().setInHandGood(
                                    App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i)
                            );
                        }
                    }
                }
            });

            inventoryElements.add(new Pair<>(imageButtonBackground, image));
        }

        progressBar = new ProgressBar(0, 200, 1, true, Assets.getInstance().getSkin());
        progressBar.setValue(App.getCurrentGame().getCurrentPlayer().getEnergy().getDayEnergyLeft());

        toolsElements = new ArrayList<>();
        mainInventoryElements = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            TextureRegionDrawable tabDrawable = new TextureRegionDrawable(new Texture("GameAssets/Main_Inventory/MainTable" + (i + 1) + ".png"));
            TextureRegionDrawable tabDrawableClicked = new TextureRegionDrawable(new Texture("GameAssets/Main_Inventory/MainTable" + (i + 1) + "Clicked.png"));
            ImageButton tabButton = new ImageButton(tabDrawable, tabDrawableClicked, tabDrawableClicked);
            tabButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    for (int i = 0; i < mainInventoryElements.size(); i++) {
                        ImageButton imageButton = mainInventoryElements.get(i);
                        imageButton.setChecked(false);
                        if (imageButton == tabButton) {
                            imageButton.setChecked(true);
                            //TODO
                        }
                    }
                }
            });

            mainInventoryElements.add(tabButton);
        }
    }

    public void updateInventory() {
        for (int i = 0; i < 12; i++) {
            Pair<ImageButton, Image> pair = inventoryElements.get(i);
            if (!App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i).isEmpty()) {
                pair.second().setDrawable(new TextureRegionDrawable(new Texture(
                        App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i).getFirst().getType().imagePath()
                )));
            }
            else {
                pair.second().setDrawable(new TextureRegionDrawable(new Texture("GameAssets/null.png")));
            }

            if (App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i) ==
                    App.getCurrentGame().getCurrentPlayer().getInHandGood())
                pair.first().setChecked(true);
            else
                pair.first().setChecked(false);
        }

        progressBar.setValue(App.getCurrentGame().getCurrentPlayer().getEnergy().getDayEnergyLeft());
    }

    public void playerChangedInventory() {
        for (int i = 0; i < inventoryElements.size(); i++) {
            Pair<ImageButton, Image> pair = inventoryElements.get(i);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i) ==
                    App.getCurrentGame().getCurrentPlayer().getInHandGood())
                pair.first().setChecked(true);
            else
                pair.first().setChecked(false);
        }

    }

    public ArrayList<Pair<ImageButton, Image>> getInventoryElements() {
        return inventoryElements;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public ArrayList<Pair<Pair<ImageButton, Image>, Integer>> getToolsElements() {
        return toolsElements;
    }

    public ArrayList<ImageButton> getMainInventoryElements() {
        return mainInventoryElements;
    }
}
