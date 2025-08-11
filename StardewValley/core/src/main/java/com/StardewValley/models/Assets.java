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
    private Texture hi = new Texture("GameAssets/Popup/Hi.png");
    private Texture bye = new Texture("GameAssets/Popup/Bye.png");
    public Texture getLike() {
        return like;
    }

    public Texture getBye() {
        return bye;
    }

    public Texture getHi() {return hi;}

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

    public final Texture farm_Background = new Texture("GameAssets/Map/Farm.png");
    public final Texture empty = new Texture("GameAssets/Map/Border.png");
    public final Texture null_txr = new Texture("GameAssets/Map/Null.png");
    public final Texture wall = new Texture("GameAssets/Map/Wall.png");
    public final Texture water = new Texture("GameAssets/Map/Water.png");
    public final Texture greenhouse = new Texture("GameAssets/Map/Greenhouse.png");
    public final Texture playerBuilding = new Texture("GameAssets/Map/House.png");
    public final Texture quarry = new Texture("GameAssets/Map/Quarry.png");
    public final Texture road = new Texture("GameAssets/Map/Road.png");
    public final Texture beach = new Texture("GameAssets/Map/Beach.png");
    public final Texture square = new Texture("GameAssets/Map/Square.png");
    public final Texture wateredFarm = new Texture("GameAssets/Map/WateredFarm.png");
    public final Texture tree = new Texture("GameAssets/Map/Tree.png");
    public final Texture seed = new Texture("GameAssets/Map/Seed.png");
    public final Texture crop = new Texture("GameAssets/Map/Crop.png");
    public final Texture playerTexture = new Texture("GameAssets/Map/Player.png");
    public final Texture mushroomTree = new Texture("GameAssets/Map/MushroomTree.png");
    public final Texture plain = new Texture("GameAssets/Map/Plain.png");
    public final Texture abigail = new Texture("GameAssets/Map/Abigail.png");
    public final Texture clint = new Texture("GameAssets/Map/Clint.png");
    public final Texture gus = new Texture("GameAssets/Map/Gus.png");
    public final Texture harvey = new Texture("GameAssets/Map/Harvey.png");
    public final Texture leah = new Texture("GameAssets/Map/Leah.png");
    public final Texture marnie = new Texture("GameAssets/Map/Marnie.png");
    public final Texture morris = new Texture("GameAssets/Map/Morris.png");
    public final Texture pierre = new Texture("GameAssets/Map/Pierre.png");
    public final Texture robin = new Texture("GameAssets/Map/Robin.png");
    public final Texture sebastian = new Texture("GameAssets/Map/Sebastian.png");
    public final Texture willy = new Texture("GameAssets/Map/Willy.png");

    private final Image farm_Background_img = new Image(new TextureRegionDrawable(farm_Background));
    private final Image empty_img = new Image(new TextureRegionDrawable(empty));
    private final Image null_img = new Image(new TextureRegionDrawable(null_txr));
    private final Image wall_img = new Image(new TextureRegionDrawable(wall));
    private final Image water_img = new Image(new TextureRegionDrawable(water));
    private final Image greenhouse_img = new Image(new TextureRegionDrawable(greenhouse));
    private final Image playerBuilding_img = new Image(new TextureRegionDrawable(playerBuilding));
    private final Image quarry_img = new Image(new TextureRegionDrawable(quarry));
    private final Image road_img = new Image(new TextureRegionDrawable(road));
    private final Image beach_img = new Image(new TextureRegionDrawable(beach));
    private final Image square_img = new Image(new TextureRegionDrawable(square));
    private final Image wateredFarm_img = new Image(new TextureRegionDrawable(wateredFarm));
    private final Image tree_img = new Image(new TextureRegionDrawable(tree));
    private final Image seed_img = new Image(new TextureRegionDrawable(seed));
    private final Image crop_img = new Image(new TextureRegionDrawable(crop));
    private final Image player_img = new Image(new TextureRegionDrawable(playerTexture));
    private final Image mushroomTree_img = new Image(new TextureRegionDrawable(mushroomTree));
    private final Image plain_img = new Image(new TextureRegionDrawable(plain));
    private final Image abigail_img = new Image(new TextureRegionDrawable(abigail));
    private final Image clint_img = new Image(new TextureRegionDrawable(clint));
    private final Image gus_img = new Image(new TextureRegionDrawable(gus));
    private final Image harvey_img = new Image(new TextureRegionDrawable(harvey));
    private final Image leah_img = new Image(new TextureRegionDrawable(leah));
    private final Image marnie_img = new Image(new TextureRegionDrawable(marnie));
    private final Image morris_img = new Image(new TextureRegionDrawable(morris));
    private final Image pierre_img = new Image(new TextureRegionDrawable(pierre));
    private final Image robin_img = new Image(new TextureRegionDrawable(robin));
    private final Image sebastian_img = new Image(new TextureRegionDrawable(sebastian));
    private final Image willy_img = new Image(new TextureRegionDrawable(willy));

    // Size constant
    private static final int IMAGE_SIZE = 8;

    // Getter methods for images with size setting
    public Image getFarmBackgroundImg() {
        farm_Background_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return farm_Background_img;
    }

    public Image getEmptyImg() {
        empty_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return empty_img;
    }

    public Image getNullImg() {
        null_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return null_img;
    }

    public Image getWallImg() {
        wall_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return wall_img;
    }

    public Image getWaterImg() {
        water_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return water_img;
    }

    public Image getGreenhouseImg() {
        greenhouse_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return greenhouse_img;
    }

    public Image getPlayerBuildingImg() {
        playerBuilding_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return playerBuilding_img;
    }

    public Image getQuarryImg() {
        quarry_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return quarry_img;
    }

    public Image getRoadImg() {
        road_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return road_img;
    }

    public Image getBeachImg() {
        beach_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return beach_img;
    }

    public Image getSquareImg() {
        square_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return square_img;
    }

    public Image getWateredFarmImg() {
        wateredFarm_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return wateredFarm_img;
    }

    public Image getTreeImg() {
        tree_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return tree_img;
    }

    public Image getSeedImg() {
        seed_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return seed_img;
    }

    public Image getCropImg() {
        crop_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return crop_img;
    }

    public Image getPlayerImg() {
        player_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return player_img;
    }

    public Image getMushroomTreeImg() {
        mushroomTree_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return mushroomTree_img;
    }

    public Image getPlainImg() {
        plain_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return plain_img;
    }

    public Image getAbigailImg() {
        abigail_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return abigail_img;
    }

    public Image getClintImg() {
        clint_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return clint_img;
    }

    public Image getGusImg() {
        gus_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return gus_img;
    }

    public Image getHarveyImg() {
        harvey_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return harvey_img;
    }

    public Image getLeahImg() {
        leah_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return leah_img;
    }

    public Image getMarnieImg() {
        marnie_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return marnie_img;
    }

    public Image getMorrisImg() {
        morris_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return morris_img;
    }

    public Image getPierreImg() {
        pierre_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return pierre_img;
    }

    public Image getRobinImg() {
        robin_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return robin_img;
    }

    public Image getSebastianImg() {
        sebastian_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return sebastian_img;
    }

    public Image getWillyImg() {
        willy_img.setSize(IMAGE_SIZE, IMAGE_SIZE);
        return willy_img;
    }

}
