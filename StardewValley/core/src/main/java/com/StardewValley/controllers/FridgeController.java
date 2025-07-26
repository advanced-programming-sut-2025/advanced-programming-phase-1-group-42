package com.StardewValley.controllers;

import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Pair;
import com.StardewValley.models.goods.Good;
import com.StardewValley.views.GameView;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class FridgeController {
    private GameView gameView;
    private final ArrayList<Pair<Pair<ImageButton, Image>, Integer>> fridgeElements;

    private final TextureRegionDrawable drawableSlot;
    private final TextureRegionDrawable drawableHighlight;

    private final ButtonGroup<ImageButton> buttonGroup;

    public FridgeController(GameView gameView) {
        this.gameView = gameView;
        fridgeElements = new ArrayList<>();

        drawableSlot = Assets.getInstance().getDrawableSlot();
        drawableHighlight = Assets.getInstance().getDrawableHighlight();

        buttonGroup = new ButtonGroup<>();
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(0);
        buttonGroup.setUncheckLast(true);

        refreshFridgeElements();
    }

    public void refreshFridgeElements() {
        fridgeElements.clear();
        buttonGroup.clear();

        ArrayList<ArrayList<Good>> fridgeItems = App.getCurrentGame().getCurrentPlayer().getFridge().getInFridgeItems();

        for (int i = 0; i < fridgeItems.size(); i++) {
            ArrayList<Good> goods = fridgeItems.get(i);

            ImageButton slotButton = new ImageButton(drawableSlot);
            slotButton.setProgrammaticChangeEvents(false);

            Image itemImage;
            if (!goods.isEmpty()) {
                itemImage = new Image(new TextureRegion(new Texture(goods.get(0).getType().imagePath())));
            } else {
                itemImage = new Image(new TextureRegion(new Texture("GameAssets/null.png")));
            }

            final int index = i;

            slotButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!slotButton.isChecked()) {
                        slotButton.setChecked(true);
                    }
                    ArrayList<Good> itemsInSlot = App.getCurrentGame().getCurrentPlayer().getFridge().getInFridgeItems().get(index);
                    if (!itemsInSlot.isEmpty()) {
                        Good good = itemsInSlot.getFirst();
                        App.getCurrentGame().getCurrentPlayer().getFridge().removeItemsFromFridge(good.getType(), 1);
                        App.getCurrentGame().getCurrentPlayer().getInventory().addGoodByObject(Good.newGood(good.getType()));
                        refreshFridgeElements();
                        updateFridge();
                        gameView.initFridgeWindow();
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

            fridgeElements.add(new Pair<>(new Pair<>(slotButton, itemImage), i));
            buttonGroup.add(slotButton);
        }
    }

    public void updateFridge() {
        try {
            for (Pair<Pair<ImageButton, Image>, Integer> pair : fridgeElements) {
                ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getFridge().getInFridgeItems().get(pair.second());

                if (!goods.isEmpty()) {
                    pair.first().second().setDrawable(new TextureRegionDrawable(new Texture(goods.get(0).getType().imagePath())));
                } else {
                    pair.first().second().setDrawable(new TextureRegionDrawable(new Texture("GameAssets/null.png")));
                }

                if (!goods.isEmpty() && App.getCurrentGame().getCurrentPlayer().getInHandGood() != null
                    && App.getCurrentGame().getCurrentPlayer().getInHandGood().getFirst() == goods.get(0)) {
                    pair.first().first().setChecked(true);
                } else {
                    pair.first().first().setChecked(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pair<Pair<ImageButton, Image>, Integer>> getFridgeElements() {
        return fridgeElements;
    }
}
