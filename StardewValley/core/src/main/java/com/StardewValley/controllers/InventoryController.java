package com.StardewValley.controllers;

import com.StardewValley.Main;
import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Pair;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.interactions.game_buildings.Quadruple;
import com.StardewValley.views.GameView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class InventoryController {
    private GameView gameView;
    private Skin skin;
    private BitmapFont smallFont;
    private final ArrayList<Quadruple<ImageButton, Image, Label, Label>> inventoryElements;
    private final ArrayList<Pair<Pair<ImageButton, Image>, Integer>> toolsElements;
    private final ArrayList<ImageButton> mainInventoryElements;
    private final ProgressBar progressBar;
    private ArrayList<Window> inventoryWindows = new ArrayList<>();

    public InventoryController(GameView gameView) {

        this.gameView = gameView;
        inventoryElements = new ArrayList<>();
        skin = Assets.getInstance().getSkin();
        TextureRegionDrawable drawableSlot = Assets.getInstance().getDrawableSlot();
        TextureRegionDrawable drawableHighlight = Assets.getInstance().getDrawableHighlight();

        int ctr = 1;
        for (ArrayList<Good> goods : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            ImageButton imageButtonBackground = new ImageButton(drawableSlot, drawableSlot, drawableHighlight);
            Image image = new Image(new TextureRegion(new Texture("GameAssets/null.png")));
            if (!goods.isEmpty())
                image = new Image(new TextureRegion(new Texture(goods.getFirst().getType().imagePath())));

            Image finalImage = image;
            Label counterLabel = new Label(String.valueOf(ctr++), skin);
            counterLabel.setFontScale(0.4f);
            Label quantityLabel = new Label((goods.isEmpty()) ? "" : String.valueOf(goods.size()), skin, "Bold");
            quantityLabel.setFontScale(0.4f);
            image.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    for (int i = 0; i < inventoryElements.size(); i++) {
                        Quadruple<ImageButton, Image, Label, Label> quadruple = inventoryElements.get(i);
                        quadruple.a.setChecked(false);
                        if (quadruple.b == finalImage) {
                            quadruple.a.setChecked(true);
                            if (gameView.getFridgeOpen()) {
                                if(!App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i).getFirst().getName().isEmpty()) {
                                    App.getCurrentGame().getCurrentPlayer().getFridge().addItemToFridge(App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i).getFirst());
                                    App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory
                                        (App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i).getFirst().getType(), 1);
                                    gameView.initFridgeWindow();
                                }
                            } else {
                                App.getCurrentGame().getCurrentPlayer().setInHandGood(
                                    App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i));
                            }

                        }
                    }
                }
            });

            inventoryElements.add(new Quadruple<>(imageButtonBackground, image, counterLabel, quantityLabel));
        }

        progressBar = new ProgressBar(0, 200, 1, true, Assets.getInstance().getSkin());
        progressBar.setValue(App.getCurrentGame().getCurrentPlayer().getEnergy().getDayEnergyLeft());

        toolsElements = new ArrayList<>();
        mainInventoryElements = new ArrayList<>();



        for (int i = 0; i < 7; i++) {
            TextureRegionDrawable tabDrawable = new TextureRegionDrawable(new Texture("GameAssets/Main_Inventory/MainTable" + (i + 1) + ".png"));
            TextureRegionDrawable tabDrawableClicked = new TextureRegionDrawable(new Texture("GameAssets/Main_Inventory/MainTable" + (i + 1) + "Clicked.png"));
            ImageButton tabButton = new ImageButton(tabDrawable, tabDrawableClicked, tabDrawableClicked);
            if (i == 6) {
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
                                        gameView.switchWindow(inventoryWindows.get(0),0);
                                        break;
                                    case 1:
                                        gameView.switchWindow(inventoryWindows.get(1),1);
                                        break;
                                    case 2:
                                        gameView.switchWindow(inventoryWindows.get(2),2);
                                        break;
                                    case 3:
                                        gameView.switchWindow(inventoryWindows.get(3),3);
                                        break;
                                    case 4:
                                        gameView.switchWindow(inventoryWindows.get(4),4);
                                        break;
                                    case 5:
                                        gameView.switchWindow(inventoryWindows.get(5),5);
                                        break;
                                    default:
                                        gameView.switchWindow(inventoryWindows.get(3),3);
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
                ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i);
                Quadruple<ImageButton, Image, Label, Label> quadruple = inventoryElements.get(i);
                if (!App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i).isEmpty()) {
                    quadruple.b.setDrawable(new TextureRegionDrawable(new Texture(
                        App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i).getLast().getType().imagePath()
                    )));
                } else {
                    quadruple.b.setDrawable(new TextureRegionDrawable(new Texture("GameAssets/null.png")));
                }


                if (!gameView.isTabClicked()) {
                    if (App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i) ==
                        App.getCurrentGame().getCurrentPlayer().getInHandGood())
                        quadruple.a.setChecked(true);
                    else
                        quadruple.a.setChecked(false);
                }

                quadruple.d.setText((goods.isEmpty()) ? "" : String.valueOf(goods.size()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        progressBar.setValue(App.getCurrentGame().getCurrentPlayer().getEnergy().getDayEnergyLeft());
    }

    public void playerChangedInventory() {
        for (int i = 0; i < inventoryElements.size(); i++) {
            Quadruple<ImageButton, Image, Label, Label> quadruple = inventoryElements.get(i);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i) ==
                    App.getCurrentGame().getCurrentPlayer().getInHandGood())
                quadruple.a.setChecked(true);
            else
                quadruple.a.setChecked(false);
        }

    }

    public ArrayList<Quadruple<ImageButton, Image, Label, Label>> getInventoryElements() {
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
