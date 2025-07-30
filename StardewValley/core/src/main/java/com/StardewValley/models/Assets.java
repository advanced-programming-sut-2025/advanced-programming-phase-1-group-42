package com.StardewValley.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.Arrays;

public class Assets {
    private static Assets instance = new Assets();
    private final Skin skin = new Skin(Gdx.files.internal("Theme2/LibGdx-Skin-main/NzSkin.json"));
    private final Music stardewMusic = Gdx.audio.newMusic(Gdx.files.internal("Audio/stardewMusic.wav"));
    private final Sprite menuBackground = new Sprite(new Texture("menu_background.png"));
    private final Sprite menuBackground2 = new Sprite(new Texture("menu_background_2.png"));
    private final ArrayList<ArrayList<Texture>> playerTextures = new ArrayList<>(Arrays.asList(
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Male/Untitled-7.png"),
            new Texture("GameAssets/Player_Movement/Male/Untitled-8.png"),
            new Texture("GameAssets/Player_Movement/Male/Untitled-9.png")
        )),
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Male/Untitled-4.png"),
            new Texture("GameAssets/Player_Movement/Male/Untitled-5.png"),
            new Texture("GameAssets/Player_Movement/Male/Untitled-6.png")
        )),
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Male/Untitled-1.png"),
            new Texture("GameAssets/Player_Movement/Male/Untitled-2.png"),
            new Texture("GameAssets/Player_Movement/Male/Untitled-3.png")
        )),
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Male/Untitled-10.png"),
            new Texture("GameAssets/Player_Movement/Male/Untitled-11.png"),
            new Texture("GameAssets/Player_Movement/Male/Untitled-12.png")
        ))
    ));
    private final ArrayList<ArrayList<Texture>> femalePlayerTextures = new ArrayList<>(Arrays.asList(
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Female/Untitled-7.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-8.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-9.png")
        )),
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Female/Untitled-4.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-5.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-6.png")
        )),
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Female/Untitled-1.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-2.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-3.png")
        )),
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Female/Untitled-10.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-11.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-12.png")
        ))
    ));

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final TextureRegionDrawable drawableSlot = new TextureRegionDrawable(new Texture("GameAssets/Inventory_Table/slot.png"));
    private final TextureRegionDrawable drawableSlotHover = new TextureRegionDrawable(new Texture("GameAssets/Inventory_Table/slotHover.png"));
    private final TextureRegionDrawable drawableHighlight = new TextureRegionDrawable(new Texture("GameAssets/Inventory_Table/highlight.png"));
    private final Texture night_background = new Texture("night.png");

    private final String nullPNGPath = "GameAssets/null.png";


    public static Assets getInstance() {
        return instance;
    }
    public Skin getSkin() {
        return skin;
    }

    public Music getStardewMusic() {
        return stardewMusic;
    }

    public Sprite getMenuBackground() {
        return menuBackground;
    }

    public Sprite getMenuBackground2() {
        return menuBackground2;
    }

    public ArrayList<ArrayList<Texture>> getPlayerTextures() {
        return playerTextures;
    }

    public ArrayList<ArrayList<Texture>> getFemalePlayerTextures() {
        return femalePlayerTextures;
    }

    public TextureRegionDrawable getDrawableSlot() {
        return drawableSlot;
    }

    public TextureRegionDrawable getDrawableHighlight() {
        return drawableHighlight;
    }

    public String getNullPNGPath() {
        return nullPNGPath;
    }

    public TextureRegionDrawable getDrawableSlotHover() {
        return drawableSlotHover;
    }

    public Texture getNight_background() {
        return night_background;
    }
}
