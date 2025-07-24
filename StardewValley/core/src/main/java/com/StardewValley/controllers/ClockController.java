package com.StardewValley.controllers;

import com.StardewValley.models.App;
import com.StardewValley.models.enums.Season;
import com.StardewValley.models.game_structure.DateTime;
import com.StardewValley.models.game_structure.weathers.Weather;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ClockController extends Controller {
    private Stage stage;
    private Texture clockTexture;
    private Texture handTexture;
    private Image clockFaceImage;
    private Image clockHandImage;
    private DateTime currentTime;
    private Weather currentWeather;
    private float rotation = 0;
    public ClockController() {
        currentTime = App.getCurrentGame().getDateTime();
        currentWeather = App.getCurrentGame().getWeather();

        stage = new Stage(new ScreenViewport());

        clockTexture = new Texture("assets\\GameAssets\\Clock/Clock.png");
        handTexture = new Texture("assets\\GameAssets\\Clock/Handle.png");

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

    public void update() {

        clockHandImage.setRotation((currentTime.getTime() - 9)*14);


        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public Stage getStage() {
        return stage;
    }
}
