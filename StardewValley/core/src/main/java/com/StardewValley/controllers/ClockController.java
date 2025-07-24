package com.StardewValley.controllers;

import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.enums.Season;
import com.StardewValley.models.game_structure.DateTime;
import com.StardewValley.models.game_structure.Wallet;
import com.StardewValley.models.game_structure.weathers.Weather;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ClockController extends Controller {
    private Stage stage;

    private Texture clockTexture;
    private Texture handTexture;
    private Image clockFaceImage;
    private Image clockHandImage;
    private Texture seasonTexture;
    private Texture weatherTexture;
    private Image seasonImage;
    private Image weatherImage;
    private String lastWeatherName;
    private String lastSeasonName;
    private String lastDayOfWeekName;
    private int lastDayOfMonthName;
    private int lastTimeOfDayName;
    private int lastNetWorth;

    private DateTime currentTime;
    private Wallet currentWallet;
    private Weather currentWeather;

    private Label dayOfWeekLabel;
    private Label dayOfMonthLabel;
    private Label timeOfDayLabel;
    private Skin skin;


    private Texture a;
    private Texture b;
    private Texture c;
    private Texture d;
    private Texture e;
    private Texture f;
    private Texture g;
    private Texture h;
    private Image A;
    private Image B;
    private Image C;
    private Image D;
    private Image E;
    private Image F;
    private Image G;
    private Image H;


    public ClockController() {
        currentTime = App.getCurrentGame().getDateTime();
        currentWeather = App.getCurrentGame().getWeather();
        currentWallet = App.getCurrentGame().getCurrentPlayer().getWallet();
        skin = Assets.getInstance().getSkin();
        stage = new Stage(new ScreenViewport());


        lastNetWorth = currentWallet.getBalance();
        a = new Texture(Gdx.files.internal("GameAssets/Clock/Nan.png"));
        b = new Texture(Gdx.files.internal("GameAssets/Clock/Nan.png"));
        c = new Texture(Gdx.files.internal("GameAssets/Clock/Nan.png"));
        d = new Texture(Gdx.files.internal("GameAssets/Clock/Nan.png"));
        e = new Texture(Gdx.files.internal("GameAssets/Clock/Nan.png"));
        f = new Texture(Gdx.files.internal("GameAssets/Clock/Nan.png"));
        g = new Texture(Gdx.files.internal("GameAssets/Clock/Nan.png"));
        h = new Texture(Gdx.files.internal("GameAssets/Clock/0.png"));
        A = new Image(new TextureRegionDrawable(a));
        B = new Image(new TextureRegionDrawable(b));
        C = new Image(new TextureRegionDrawable(c));
        D = new Image(new TextureRegionDrawable(d));
        E = new Image(new TextureRegionDrawable(e));
        F = new Image(new TextureRegionDrawable(f));
        G = new Image(new TextureRegionDrawable(g));
        H = new Image(new TextureRegionDrawable(h));

        A.setSize(A.getWidth() * 3, A.getHeight() * 3);
        B.setSize(B.getWidth() * 3, B.getHeight() * 3);
        C.setSize(C.getWidth() * 3, C.getHeight() * 3);
        D.setSize(D.getWidth() * 3, D.getHeight() * 3);
        E.setSize(E.getWidth() * 3, E.getHeight() * 3);
        F.setSize(F.getWidth() * 3, F.getHeight() * 3);
        G.setSize(G.getWidth() * 3, G.getHeight() * 3);
        H.setSize(H.getWidth() * 3, H.getHeight() * 3);

        A.setPosition(17*3, Gdx.graphics.getHeight() - 56*3);
        B.setPosition(A.getX() + 3 + B.getWidth(), Gdx.graphics.getHeight() - 56*3);
        C.setPosition(B.getX() + 3 + C.getWidth(), Gdx.graphics.getHeight() - 56*3);
        D.setPosition(C.getX() + 3 + D.getWidth(), Gdx.graphics.getHeight() - 56*3);
        E.setPosition(D.getX() + 3 + E.getWidth(), Gdx.graphics.getHeight() - 56*3);
        F.setPosition(E.getX() + 3 + F.getWidth(), Gdx.graphics.getHeight() - 56*3);
        G.setPosition(F.getX() + 3 + G.getWidth(), Gdx.graphics.getHeight() - 56*3);
        H.setPosition(G.getX() + 3 + H.getWidth(), Gdx.graphics.getHeight() - 56*3);
        setClockFace();
        setWeatherAndSeason();
        setDays();

        stage.addActor(A);
        stage.addActor(B);
        stage.addActor(C);
        stage.addActor(D);
        stage.addActor(E);
        stage.addActor(F);
        stage.addActor(G);
        stage.addActor(H);
    }

    public void update() {

        clockHandImage.setRotation((currentTime.getTime() - 9) * 14);

        String currentWeatherName = App.getCurrentGame().getWeather().getName();
        String currentSeasonName = App.getCurrentGame().getDateTime().getSeason().getName();
        String currentDayOfWeek = App.getCurrentGame().getDateTime().getDayOfWeek();
        int currentDayOfMonth = App.getCurrentGame().getDateTime().getDayOfSeason();
        int currentTimeOfDay = App.getCurrentGame().getDateTime().getTime();
        int currentNetWorth = App.getCurrentGame().getCurrentPlayer().getWallet().getBalance();


        if(currentNetWorth != lastNetWorth) {
            int netWorth = currentNetWorth;
            int num;
            int count = 0;
            lastNetWorth = currentNetWorth;
            while (netWorth > 0) {
                num = netWorth % 10;
                netWorth = netWorth / 10;
                switch (count) {
                    case 0:
                        h.dispose();
                        h = new Texture("GameAssets/Clock/" + num + ".png");
                        H.setDrawable(new TextureRegionDrawable(h));
                        break;
                    case 1:
                        g.dispose();
                        g = new Texture("GameAssets/Clock/" + num + ".png");
                        G.setDrawable(new TextureRegionDrawable(g));
                        break;
                    case 2:
                        f.dispose();
                        f = new Texture("GameAssets/Clock/" + num + ".png");
                        F.setDrawable(new TextureRegionDrawable(f));
                        break;
                    case 3:
                        e.dispose();
                        e = new Texture("GameAssets/Clock/" + num + ".png");
                        E.setDrawable(new TextureRegionDrawable(e));
                        break;
                    case 4:
                        d.dispose();
                        d = new Texture("GameAssets/Clock/" + num + ".png");
                        D.setDrawable(new TextureRegionDrawable(d));
                        break;
                    case 5:
                        c.dispose();
                        c = new Texture("GameAssets/Clock/" + num + ".png");
                        C.setDrawable(new TextureRegionDrawable(c));
                        break;
                    case 6:
                        b.dispose();
                        b = new Texture("GameAssets/Clock/" + num + ".png");
                        B.setDrawable(new TextureRegionDrawable(b));
                        break;
                    case 7:
                        a.dispose();
                        a = new Texture("GameAssets/Clock/" + num + ".png");
                        A.setDrawable(new TextureRegionDrawable(a));
                        break;
                    default:
                        a.dispose();
                        a = new Texture("GameAssets/Clock/" + num + ".png");
                        A.setDrawable(new TextureRegionDrawable(a));
                        break;
                }
                count++;
            }
            for(; count < 8; count++) {
                switch (count) {
                    case 0:
                        h.dispose();
                        weatherTexture.dispose();
                        h = new Texture("GameAssets/Clock/" + "Nan" + ".png");
                        H.setDrawable(new TextureRegionDrawable(h));
                        break;
                    case 1:
                        g.dispose();
                        g = new Texture("GameAssets/Clock/" + "Nan" + ".png");
                        G.setDrawable(new TextureRegionDrawable(g));
                        break;
                    case 2:
                        f.dispose();
                        f = new Texture("GameAssets/Clock/" + "Nan" + ".png");
                        F.setDrawable(new TextureRegionDrawable(f));
                        break;
                    case 3:
                        e.dispose();
                        e = new Texture("GameAssets/Clock/" + "Nan" + ".png");
                        E.setDrawable(new TextureRegionDrawable(e));
                        break;
                    case 4:
                        d.dispose();
                        d = new Texture("GameAssets/Clock/" + "Nan" + ".png");
                        D.setDrawable(new TextureRegionDrawable(d));
                        break;
                    case 5:
                        c.dispose();
                        c = new Texture("GameAssets/Clock/" + "Nan" + ".png");
                        C.setDrawable(new TextureRegionDrawable(c));
                        break;
                    case 6:
                        b.dispose();
                        b = new Texture("GameAssets/Clock/" + "Nan" + ".png");
                        B.setDrawable(new TextureRegionDrawable(b));
                        break;
                    case 7:
                        a.dispose();
                        a = new Texture("GameAssets/Clock/" + "Nan" + ".png");
                        A.setDrawable(new TextureRegionDrawable(a));
                        break;
                    default:
                        a.dispose();
                        a = new Texture("GameAssets/Clock/" + "Nan" + ".png");
                        A.setDrawable(new TextureRegionDrawable(a));
                        break;
                }
            }
        }

        else if (!currentWeatherName.equals(lastWeatherName)) {
            lastWeatherName = currentWeatherName;
            weatherTexture.dispose();
            weatherTexture = new Texture("GameAssets/Clock/" + currentWeatherName + ".png");
            weatherImage.setDrawable(new TextureRegionDrawable(new TextureRegion(weatherTexture)));
        }
        else if (!currentSeasonName.equals(lastSeasonName)) {
            lastSeasonName = currentSeasonName;
            seasonTexture.dispose();
            seasonTexture = new Texture("GameAssets/Clock/" + currentSeasonName + ".png");
            seasonImage.setDrawable(new TextureRegionDrawable(new TextureRegion(seasonTexture)));
        }
        else if (!currentDayOfWeek.equals(lastDayOfWeekName)) {
            lastDayOfWeekName = currentDayOfWeek;
            switch (currentDayOfWeek) {
                case "Saturday":
                dayOfWeekLabel.setText("Sat.");
                break;
                case "Sunday":
                dayOfWeekLabel.setText("Sun.");
                break;
                case "Monday":
                dayOfWeekLabel.setText("Mon.");
                break;
                case "Tuesday":
                dayOfWeekLabel.setText("Tue.");
                break;
                case "Wednesday":
                dayOfWeekLabel.setText("Wed.");
                break;
                case "Thursday":
                dayOfWeekLabel.setText("Thu.");
                break;
                case "Friday":
                dayOfWeekLabel.setText("Fri.");
                break;
                default:
                dayOfWeekLabel.setText("Sun.");
                break;
            }
        }
        else if (currentDayOfMonth != lastDayOfMonthName) {
            lastDayOfMonthName = currentDayOfMonth;
            dayOfMonthLabel.setText(currentDayOfMonth);
        }
        else if(currentTimeOfDay != lastTimeOfDayName) {
            lastTimeOfDayName = currentTimeOfDay;
            timeOfDayLabel.setText(currentTimeOfDay + ":00");
        }

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }


    public void setWeatherAndSeason(){
        seasonTexture = new Texture("GameAssets/Clock/" + currentTime.getSeason().getName() + ".png");
        weatherTexture = new Texture("GameAssets/Clock/" + currentWeather.getName() + ".png");
        lastWeatherName = currentWeather.getName();
        lastSeasonName = currentTime.getSeason().getName();
        seasonImage = new Image(new TextureRegionDrawable(new TextureRegion(seasonTexture)));
        weatherImage = new Image(new TextureRegionDrawable(new TextureRegion(weatherTexture)));
        seasonImage.setSize(seasonImage.getWidth() * 3, seasonImage.getHeight() * 3);
        seasonImage.setPosition(29*3, Gdx.graphics.getHeight() - 24*3);
        weatherImage.setSize(weatherImage.getWidth() * 3, weatherImage.getHeight() * 3);
        weatherImage.setPosition(53*3, Gdx.graphics.getHeight() - 24*3);
        stage.addActor(weatherImage);
        stage.addActor(seasonImage);
    }
    public void setClockFace(){
        clockTexture = new Texture("GameAssets/Clock/Clock.png");
        handTexture = new Texture("GameAssets/Clock/Handle.png");
        clockFaceImage = new Image(new TextureRegionDrawable(new TextureRegion(clockTexture)));
        clockHandImage = new Image(new TextureRegionDrawable(new TextureRegion(handTexture)));
        clockFaceImage.setSize(clockFaceImage.getWidth() * 3, clockFaceImage.getHeight() * 3);
        clockHandImage.setSize(clockHandImage.getWidth() * 3, clockHandImage.getHeight() * 3);
        clockFaceImage.setPosition(0, Gdx.graphics.getHeight()-clockFaceImage.getHeight()); // base position (or center it if needed)
        clockHandImage.setPosition(19 * 3, 39 * 3 + Gdx.graphics.getHeight()-clockFaceImage.getHeight()); // adjust these if needed
        clockHandImage.setOrigin(clockHandImage.getWidth() / 2f, 0f);
        stage.addActor(clockFaceImage);
        stage.addActor(clockHandImage);
    }
    public void setDays(){
        lastDayOfWeekName = currentTime.getDayOfWeek();
        lastDayOfMonthName = currentTime.getDayOfSeason();
        lastTimeOfDayName = currentTime.getTime();

        dayOfWeekLabel = new Label("Sun.", skin);
        dayOfWeekLabel.setPosition(84 , Gdx.graphics.getHeight()- dayOfWeekLabel.getHeight() - 6);
        dayOfMonthLabel = new Label("1", skin);
        dayOfMonthLabel.setPosition(84 + dayOfWeekLabel.getWidth() + 8  , Gdx.graphics.getHeight()- dayOfWeekLabel.getHeight() - 6);
        timeOfDayLabel = new Label("9:00", skin);
        timeOfDayLabel.setPosition(45*3 - timeOfDayLabel.getWidth()/2 , Gdx.graphics.getHeight()- timeOfDayLabel.getHeight() - 74);

        stage.addActor(dayOfWeekLabel);
        stage.addActor(dayOfMonthLabel);
        stage.addActor(timeOfDayLabel);
    }

    public Stage getStage() {
        return stage;
    }
}
