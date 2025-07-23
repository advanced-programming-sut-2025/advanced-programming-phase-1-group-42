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
            new Texture("GameAssets/Player_Movement/Untitled-7.png"),
            new Texture("GameAssets/Player_Movement/Untitled-8.png"),
            new Texture("GameAssets/Player_Movement/Untitled-9.png")
        )),
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Untitled-4.png"),
            new Texture("GameAssets/Player_Movement/Untitled-5.png"),
            new Texture("GameAssets/Player_Movement/Untitled-6.png")
        )),
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Untitled-1.png"),
            new Texture("GameAssets/Player_Movement/Untitled-2.png"),
            new Texture("GameAssets/Player_Movement/Untitled-3.png")
        )),
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Untitled-10.png"),
            new Texture("GameAssets/Player_Movement/Untitled-11.png"),
            new Texture("GameAssets/Player_Movement/Untitled-12.png")
        ))
    ));

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final TextureRegionDrawable drawableSlot = new TextureRegionDrawable(new Texture("GameAssets/Inventory_Table/slot.png"));
    private final TextureRegionDrawable drawableHighlight = new TextureRegionDrawable(new Texture("GameAssets/Inventory_Table/highlight.png"));
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

    public void setColorFunction() {
        int time = App.getCurrentGame().getDateTime().getTime();
        if(time >= 19) {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0, 0, 0.5f);
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }
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
}
