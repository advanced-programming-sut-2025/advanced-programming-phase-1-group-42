package com.StardewValley.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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

    private Image like_img = new Image(like);
    private Image dislike_img = new Image(dislike);
    private Image heart_img = new Image(heart);
    private Image smile_img = new Image(smile);
    private Image uwu_img = new Image(uwu);
    private Image question_img = new Image(question);
    private Image angry_img = new Image(angry);
    private Image speechless_img = new Image(speechless);
    private Image surprised_img = new Image(surprised);
    private Image sleepy_img = new Image(sleepy);
    private Image notSure_img = new Image(notSure);
//
//    // Like animation frames
//    private Texture like_1Frame = new Texture("assets/GameAssets/Popup/like1.png");
//    private Texture like_2Frame = new Texture("assets/GameAssets/Popup/like2.png");
//    private Texture like_3Frame = new Texture("assets/GameAssets/Popup/like3.png");
//    private Texture like_4Frame = new Texture("assets/GameAssets/Popup/like4.png");
//    private final Animation<Texture> like_frames = new Animation<>(0.1f, like_1Frame, like_2Frame, like_3Frame, like_4Frame);
//
//    // Dislike animation frames
//    private Texture dislike_1Frame = new Texture("assets/GameAssets/Popup/dislike1.png");
//    private Texture dislike_2Frame = new Texture("assets/GameAssets/Popup/dislike2.png");
//    private Texture dislike_3Frame = new Texture("assets/GameAssets/Popup/dislike3.png");
//    private Texture dislike_4Frame = new Texture("assets/GameAssets/Popup/dislike4.png");
//    private final Animation<Texture> dislike_frames = new Animation<>(0.1f, dislike_1Frame, dislike_2Frame, dislike_3Frame, dislike_4Frame);
//
//    // Heart animation frames
//    private Texture heart_1Frame = new Texture("assets/GameAssets/Popup/heart1.png");
//    private Texture heart_2Frame = new Texture("assets/GameAssets/Popup/heart2.png");
//    private Texture heart_3Frame = new Texture("assets/GameAssets/Popup/heart3.png");
//    private Texture heart_4Frame = new Texture("assets/GameAssets/Popup/heart4.png");
//    private final Animation<Texture> heart_frames = new Animation<>(0.1f, heart_1Frame, heart_2Frame, heart_3Frame, heart_4Frame);
//
//    // Smile animation frames
//    private Texture smile_1Frame = new Texture("assets/GameAssets/Popup/smile1.png");
//    private Texture smile_2Frame = new Texture("assets/GameAssets/Popup/smile2.png");
//    private Texture smile_3Frame = new Texture("assets/GameAssets/Popup/smile3.png");
//    private Texture smile_4Frame = new Texture("assets/GameAssets/Popup/smile4.png");
//    private final Animation<Texture> smile_frames = new Animation<>(0.1f, smile_1Frame, smile_2Frame, smile_3Frame, smile_4Frame);
//
//    // UwU animation frames
    private Texture uwu_1Frame = new Texture("assets/GameAssets/Popup/uwu1.png");
    private Texture uwu_2Frame = new Texture("assets/GameAssets/Popup/uwu2.png");
    private Texture uwu_3Frame = new Texture("assets/GameAssets/Popup/uwu3.png");
    private Texture uwu_4Frame = new Texture("assets/GameAssets/Popup/uwu4.png");
    private final Animation<Texture> uwu_frames = new Animation<>(0.1f, uwu_1Frame, uwu_2Frame, uwu_3Frame, uwu_4Frame);
//
//    // Question animation frames
//    private Texture question_1Frame = new Texture("assets/GameAssets/Popup/question1.png");
//    private Texture question_2Frame = new Texture("assets/GameAssets/Popup/question2.png");
//    private Texture question_3Frame = new Texture("assets/GameAssets/Popup/question3.png");
//    private Texture question_4Frame = new Texture("assets/GameAssets/Popup/question4.png");
//    private final Animation<Texture> question_frames = new Animation<>(0.1f, question_1Frame, question_2Frame, question_3Frame, question_4Frame);
//
//    // Angry animation frames
//    private Texture angry_1Frame = new Texture("assets/GameAssets/Popup/angry1.png");
//    private Texture angry_2Frame = new Texture("assets/GameAssets/Popup/angry2.png");
//    private Texture angry_3Frame = new Texture("assets/GameAssets/Popup/angry3.png");
//    private Texture angry_4Frame = new Texture("assets/GameAssets/Popup/angry4.png");
//    private final Animation<Texture> angry_frames = new Animation<>(0.1f, angry_1Frame, angry_2Frame, angry_3Frame, angry_4Frame);
//
//    // Speechless animation frames
//    private Texture speechless_1Frame = new Texture("assets/GameAssets/Popup/speechless1.png");
//    private Texture speechless_2Frame = new Texture("assets/GameAssets/Popup/speechless2.png");
//    private Texture speechless_3Frame = new Texture("assets/GameAssets/Popup/speechless3.png");
//    private Texture speechless_4Frame = new Texture("assets/GameAssets/Popup/speechless4.png");
//    private final Animation<Texture> speechless_frames = new Animation<>(0.1f, speechless_1Frame, speechless_2Frame, speechless_3Frame, speechless_4Frame);
//
//    // Surprised animation frames
//    private Texture surprised_1Frame = new Texture("assets/GameAssets/Popup/surprised1.png");
//    private Texture surprised_2Frame = new Texture("assets/GameAssets/Popup/surprised2.png");
//    private Texture surprised_3Frame = new Texture("assets/GameAssets/Popup/surprised3.png");
//    private Texture surprised_4Frame = new Texture("assets/GameAssets/Popup/surprised4.png");
//    private final Animation<Texture> surprised_frames = new Animation<>(0.1f, surprised_1Frame, surprised_2Frame, surprised_3Frame, surprised_4Frame);
//
//    // Sleepy animation frames
//    private Texture sleepy_1Frame = new Texture("assets/GameAssets/Popup/sleepy1.png");
//    private Texture sleepy_2Frame = new Texture("assets/GameAssets/Popup/sleepy2.png");
//    private Texture sleepy_3Frame = new Texture("assets/GameAssets/Popup/sleepy3.png");
//    private Texture sleepy_4Frame = new Texture("assets/GameAssets/Popup/sleepy4.png");
//    private final Animation<Texture> sleepy_frames = new Animation<>(0.1f, sleepy_1Frame, sleepy_2Frame, sleepy_3Frame, sleepy_4Frame);
//
//    // NotSure animation frames
//    private Texture notSure_1Frame = new Texture("assets/GameAssets/Popup/notSure1.png");
//    private Texture notSure_2Frame = new Texture("assets/GameAssets/Popup/notSure2.png");
//    private Texture notSure_3Frame = new Texture("assets/GameAssets/Popup/notSure3.png");
//    private Texture notSure_4Frame = new Texture("assets/GameAssets/Popup/notSure4.png");
//    private final Animation<Texture> notSure_frames = new Animation<>(0.1f, notSure_1Frame, notSure_2Frame, notSure_3Frame, notSure_4Frame);

    private Texture popup_1Frame = new Texture("assets/GameAssets/Popup/popup1.png");
    private Texture popup_2Frame = new Texture("assets/GameAssets/Popup/popup2.png");
    private Texture popup_3Frame = new Texture("assets/GameAssets/Popup/popup3.png");
    private Texture popup_4Frame = new Texture("assets/GameAssets/Popup/popup4.png");
    private final Animation<Texture> popup_frames = new Animation<>(0.1f, popup_1Frame,popup_1Frame,popup_2Frame, popup_2Frame,
                                                                                    popup_3Frame, popup_3Frame, popup_4Frame, popup_4Frame);

    public Texture getPopup_1Frame() {
        return popup_1Frame;
    }

    public Texture getPopup_2Frame() {
        return popup_2Frame;
    }

    public Texture getPopup_3Frame() {
        return popup_3Frame;
    }

    public Texture getPopup_4Frame() {
        return popup_4Frame;
    }

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
}
