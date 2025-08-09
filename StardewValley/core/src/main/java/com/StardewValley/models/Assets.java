package com.StardewValley.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.Arrays;

public class Assets {
    private final Skin skin = new Skin(Gdx.files.internal("Theme2/LibGdx-Skin-main/NzSkin.json"));
    private final Music stardewMusic = Gdx.audio.newMusic(Gdx.files.internal("Audio/stardewMusic.wav"));
    private final Sprite menuBackground = new Sprite(new Texture("menu_background.png"));
    private final Sprite menuBackground2 = new Sprite(new Texture("menu_background_2.png"));
    private final ArrayList<ArrayList<String>> playerTextureStrings = new ArrayList<>(Arrays.asList(
        new ArrayList<>(Arrays.asList(
            "GameAssets/Player_Movement/Male/Male_walk (10).png",
            "GameAssets/Player_Movement/Male/Male_walk (11).png",
            "GameAssets/Player_Movement/Male/Male_walk (12).png",
            "GameAssets/Player_Movement/Male/Male_walk (9).png"
        )),
        new ArrayList<>(Arrays.asList(
            "GameAssets/Player_Movement/Male/Male_walk (14).png",
            "GameAssets/Player_Movement/Male/Male_walk (15).png",
            "GameAssets/Player_Movement/Male/Male_walk (16).png",
            "GameAssets/Player_Movement/Male/Male_walk (13).png"
        )),
        new ArrayList<>(Arrays.asList(
            "GameAssets/Player_Movement/Male/Male_walk (2).png",
            "GameAssets/Player_Movement/Male/Male_walk (3).png",
            "GameAssets/Player_Movement/Male/Male_walk (4).png",
            "GameAssets/Player_Movement/Male/Male_walk (1).png"
        )),
        new ArrayList<>(Arrays.asList(
            "GameAssets/Player_Movement/Male/Male_walk (6).png",
            "GameAssets/Player_Movement/Male/Male_walk (7).png",
            "GameAssets/Player_Movement/Male/Male_walk (8).png",
            "GameAssets/Player_Movement/Male/Male_walk (5).png"
        ))
    ));
    private final ArrayList<ArrayList<String>> femalePlayerTextureStrings = new ArrayList<>(Arrays.asList(
        new ArrayList<>(Arrays.asList(
            "GameAssets/Player_Movement/Female/Untitled-7.png",
            "GameAssets/Player_Movement/Female/Untitled-8.png",
            "GameAssets/Player_Movement/Female/Untitled-7.png",
            "GameAssets/Player_Movement/Female/Untitled-9.png"
        )),
        new ArrayList<>(Arrays.asList(
            "GameAssets/Player_Movement/Female/Untitled-4.png",
            "GameAssets/Player_Movement/Female/Untitled-5.png",
            "GameAssets/Player_Movement/Female/Untitled-4.png",
            "GameAssets/Player_Movement/Female/Untitled-6.png"
        )),
        new ArrayList<>(Arrays.asList(
            "GameAssets/Player_Movement/Female/Untitled-1.png",
            "GameAssets/Player_Movement/Female/Untitled-2.png",
            "GameAssets/Player_Movement/Female/Untitled-1.png",
            "GameAssets/Player_Movement/Female/Untitled-3.png"
        )),
        new ArrayList<>(Arrays.asList(
            "GameAssets/Player_Movement/Female/Untitled-10.png",
            "GameAssets/Player_Movement/Female/Untitled-11.png",
            "GameAssets/Player_Movement/Female/Untitled-10.png",
            "GameAssets/Player_Movement/Female/Untitled-12.png"
        ))
    ));

    private final ArrayList<ArrayList<Texture>> playerTextures = new ArrayList<>(Arrays.asList(
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Male/Male_walk (10).png"),
            new Texture("GameAssets/Player_Movement/Male/Male_walk (11).png"),
            new Texture("GameAssets/Player_Movement/Male/Male_walk (12).png"),
            new Texture("GameAssets/Player_Movement/Male/Male_walk (9).png")
        )),
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Male/Male_walk (14).png"),
            new Texture("GameAssets/Player_Movement/Male/Male_walk (15).png"),
            new Texture("GameAssets/Player_Movement/Male/Male_walk (16).png"),
            new Texture("GameAssets/Player_Movement/Male/Male_walk (13).png")
        )),
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Male/Male_walk (2).png"),
            new Texture("GameAssets/Player_Movement/Male/Male_walk (3).png"),
            new Texture("GameAssets/Player_Movement/Male/Male_walk (4).png"),
            new Texture("GameAssets/Player_Movement/Male/Male_walk (1).png")
        )),
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Male/Male_walk (6).png"),
            new Texture("GameAssets/Player_Movement/Male/Male_walk (7).png"),
            new Texture("GameAssets/Player_Movement/Male/Male_walk (8).png"),
            new Texture("GameAssets/Player_Movement/Male/Male_walk (5).png")
        ))
    ));
    private final ArrayList<ArrayList<Texture>> femalePlayerTextures = new ArrayList<>(Arrays.asList(
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Female/Untitled-7.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-8.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-7.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-9.png")
        )),
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Female/Untitled-4.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-5.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-4.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-6.png")
        )),
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Female/Untitled-1.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-2.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-1.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-3.png")
        )),
        new ArrayList<>(Arrays.asList(
            new Texture("GameAssets/Player_Movement/Female/Untitled-10.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-11.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-10.png"),
            new Texture("GameAssets/Player_Movement/Female/Untitled-12.png")
        ))
    ));

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final TextureRegionDrawable drawableSlot = new TextureRegionDrawable(new Texture("GameAssets/Inventory_Table/slot.png"));
    private final TextureRegionDrawable drawableSlotHover = new TextureRegionDrawable(new Texture("GameAssets/Inventory_Table/slotHover.png"));
    private final TextureRegionDrawable drawableHighlight = new TextureRegionDrawable(new Texture("GameAssets/Inventory_Table/highlight.png"));
    private final Texture night_background = new Texture("night.png");

    private final String nullPNGPath = "GameAssets/null.png";


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

    public ArrayList<ArrayList<String>> getPlayerTextureStrings() {
        return playerTextureStrings;
    }

    public ArrayList<ArrayList<String>> getFemalePlayerTextureStrings() {
        return femalePlayerTextureStrings;
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


    private Texture like = new Texture("GameAssets/Popup/like.png");
    private Texture dislike = new Texture("GameAssets/Popup/dislike.png");
    private Texture heart = new Texture("GameAssets/Popup/heart.png");
    private Texture smile = new Texture("GameAssets/Popup/music.png");
    private Texture uwu = new Texture("GameAssets/Popup/uwu.png");
    private Texture question = new Texture("GameAssets/Popup/question.png");
    private Texture angry = new Texture("GameAssets/Popup/angry.png");
    private Texture speechless = new Texture("GameAssets/Popup/speechless.png");
    private Texture surprised = new Texture("GameAssets/Popup/surprised.png");
    private Texture sleepy = new Texture("GameAssets/Popup/sleepy.png");
    private Texture notSure = new Texture("GameAssets/Popup/notsure.png");
    private Texture edit = new Texture("GameAssets/Popup/edit.png");

    public Texture getLike() {
        return like;
    }

    public Texture getUwu() {
        return uwu;
    }

    public Texture getDislike() {
        return dislike;
    }

    public Texture getHeart() {
        return heart;
    }

    public Texture getSmile() {
        return smile;
    }

    public Texture getQuestion() {
        return question;
    }

    public Texture getAngry() {
        return angry;
    }

    public Texture getSpeechless() {
        return speechless;
    }

    public Texture getSurprised() {
        return surprised;
    }

    public Texture getSleepy() {
        return sleepy;
    }

    public Texture getNotSure() {
        return notSure;
    }

    public Texture getEdit() {
        return edit;
    }

    public ArrayList<ArrayList<Texture>> getPlayerTextures() {
        return playerTextures;
    }

    public ArrayList<ArrayList<Texture>> getFemalePlayerTextures() {
        return femalePlayerTextures;
    }
}
