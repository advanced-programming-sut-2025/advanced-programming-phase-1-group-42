package com.StardewValley.controllers;

import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Pair;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.views.GameView;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class InventoryController {
    private GameView gameView;
    private final ArrayList<Pair<ImageButton, Image>> inventoryElements;
    private final ArrayList<Pair<Pair<ImageButton, Image>, Integer>> toolsElements;
    private final ArrayList<ImageButton> mainInventoryElements;
    private final ProgressBar progressBar;
    private ArrayList<Window> inventoryWindows = new ArrayList<>();

    public InventoryController(GameView gameView) {

        this.gameView = gameView;
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
            if (i == 7) {
                tabButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        for (int i = 0; i < mainInventoryElements.size(); i++) {
                            ImageButton imageButton = mainInventoryElements.get(i);
                            imageButton.setChecked(false);
                            if (imageButton == tabButton) {
//                                imageButton.setChecked(true);
                                gameView.closeMainTable();
                            }
                        }
                    }
                });
            }
            else {
                tabButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        for (int i = 0; i < mainInventoryElements.size(); i++) {
                            ImageButton imageButton = mainInventoryElements.get(i);
                            imageButton.setChecked(false);
                            if (imageButton == tabButton) {
                                imageButton.setChecked(true);
                                switch (i){
                                    case 0:
                                        gameView.switchWindow(inventoryWindows.get(0));
                                        break;
                                    case 1:
                                        gameView.switchWindow(inventoryWindows.get(1));
                                        break;
                                    case 2:
                                        gameView.switchWindow(inventoryWindows.get(2));
                                        break;
                                    case 3:
                                        gameView.switchWindow(inventoryWindows.get(3));
                                        break;
                                    default:
                                        gameView.switchWindow(inventoryWindows.get(4));
                                        break;

                                }
                            }
                        }
                    }
                });
            }

            mainInventoryElements.add(tabButton);
        }

    }

    public ArrayList<Window> getInventoryWindows() {
        return inventoryWindows;
    }

    public void setInventoryWindows(ArrayList<Window> inventoryWindows) {
        this.inventoryWindows = inventoryWindows;
    }

    public void updateInventory() {
        try {
            for (int i = 0; i < 12; i++) {
                Pair<ImageButton, Image> pair = inventoryElements.get(i);
                if (!App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i).isEmpty()) {
                    pair.second().setDrawable(new TextureRegionDrawable(new Texture(
                        App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i).getLast().getType().imagePath()
                    )));
                } else {
                    pair.second().setDrawable(new TextureRegionDrawable(new Texture("GameAssets/null.png")));
                }

                if (App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i) ==
                    App.getCurrentGame().getCurrentPlayer().getInHandGood())
                    pair.first().setChecked(true);
                else
                    pair.first().setChecked(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
