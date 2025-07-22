package com.StardewValley.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

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
}
