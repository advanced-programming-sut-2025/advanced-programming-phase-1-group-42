package com.StardewValley.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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



    private Texture a = new Texture(Gdx.files.internal("GameAssets/Clock/Nan.png"));
    private Texture b = new Texture(Gdx.files.internal("GameAssets/Clock/Nan.png"));
    private Texture c = new Texture(Gdx.files.internal("GameAssets/Clock/Nan.png"));
    private Texture d = new Texture(Gdx.files.internal("GameAssets/Clock/Nan.png"));
    private Texture e = new Texture(Gdx.files.internal("GameAssets/Clock/Nan.png"));
    private Texture f = new Texture(Gdx.files.internal("GameAssets/Clock/Nan.png"));
    private Texture g = new Texture(Gdx.files.internal("GameAssets/Clock/Nan.png"));
    private Texture h = new Texture(Gdx.files.internal("GameAssets/Clock/0.png"));

    private Image A = new Image(new TextureRegionDrawable(a));
    private Image B = new Image(new TextureRegionDrawable(b));
    private Image C = new Image(new TextureRegionDrawable(c));
    private Image D = new Image(new TextureRegionDrawable(d));
    private Image E = new Image(new TextureRegionDrawable(e));
    private Image F = new Image(new TextureRegionDrawable(f));
    private Image G = new Image(new TextureRegionDrawable(g));
    private Image H = new Image(new TextureRegionDrawable(h));

    public Image getA() {
        return A;
    }

    public Image getB() {
        return B;
    }

    public Image getC() {
        return C;
    }

    public Image getD() {
        return D;
    }

    public Image getE() {
        return E;
    }

    public Image getF() {
        return F;
    }

    public Image getG() {
        return G;
    }

    public Image getH() {
        return H;
    }

    private TextureRegionDrawable TR0 = new TextureRegionDrawable(new Texture("GameAssets/Clock/0.png"));
    private TextureRegionDrawable TR1 = new TextureRegionDrawable(new Texture("GameAssets/Clock/1.png"));
    private TextureRegionDrawable TR2 = new TextureRegionDrawable(new Texture("GameAssets/Clock/2.png"));
    private TextureRegionDrawable TR3 = new TextureRegionDrawable(new Texture("GameAssets/Clock/3.png"));
    private TextureRegionDrawable TR4 = new TextureRegionDrawable(new Texture("GameAssets/Clock/4.png"));
    private TextureRegionDrawable TR5 = new TextureRegionDrawable(new Texture("GameAssets/Clock/5.png"));
    private TextureRegionDrawable TR6 = new TextureRegionDrawable(new Texture("GameAssets/Clock/6.png"));
    private TextureRegionDrawable TR7 = new TextureRegionDrawable(new Texture("GameAssets/Clock/7.png"));
    private TextureRegionDrawable TR8 = new TextureRegionDrawable(new Texture("GameAssets/Clock/8.png"));
    private TextureRegionDrawable TR9 = new TextureRegionDrawable(new Texture("GameAssets/Clock/9.png"));
    private TextureRegionDrawable TRNan = new TextureRegionDrawable(new Texture("GameAssets/Clock/Nan.png"));

    public TextureRegionDrawable getTRNan() {
        return TRNan;
    }

    public TextureRegionDrawable getClockNumber(int num) {
        switch (num) {
            case 0:
                return TR0;
                case 1:
                    return TR1;
                    case 2:
                        return TR2;
                        case 3:
                            return TR3;
                            case 4:
                                return TR4;
                                case 5:
                                    return TR5;
                                    case 6:
                                        return TR6;
                                        case 7:
                                            return TR7;
                                            case 8:
                                                return TR8;
                                                case 9:
                                                    return TR9;
                                                    default:
                                                        return TR0;
        }
    }

    private final TextureRegionDrawable spring = new TextureRegionDrawable(new Texture("GameAssets/Clock/Spring.png"));
    private final TextureRegionDrawable fall = new TextureRegionDrawable(new Texture("GameAssets/Clock/Fall.png"));
    private final TextureRegionDrawable summer = new TextureRegionDrawable(new Texture("GameAssets/Clock/Summer.png"));
    private final TextureRegionDrawable winter = new TextureRegionDrawable(new Texture("GameAssets/Clock/Winter.png"));

    private final TextureRegionDrawable snow = new TextureRegionDrawable(new Texture("GameAssets/Clock/Snow.png"));
    private final TextureRegionDrawable sunny = new TextureRegionDrawable(new Texture("GameAssets/Clock/Sunny.png"));
    private final TextureRegionDrawable rain = new TextureRegionDrawable(new Texture("GameAssets/Clock/Rain.png"));
    private final TextureRegionDrawable storm = new TextureRegionDrawable(new Texture("GameAssets/Clock/Storm.png"));

    public TextureRegionDrawable getClockSeason(String season) {
        switch (season) {
            case "Spring":
                return spring;
            case "Fall":
                return fall;
            case "Summer":
                return summer;
            case "Winter":
                return winter;
            default:
                return spring;
        }
    }

    public TextureRegionDrawable getClockWeather(String weather) {
        switch (weather) {
            case "Snow":
                return snow;
            case "Sunny":
                return sunny;
            case "Rain":
                return rain;
            case "Storm":
                return storm;
            default:
                return sunny;
        }
    }
}
