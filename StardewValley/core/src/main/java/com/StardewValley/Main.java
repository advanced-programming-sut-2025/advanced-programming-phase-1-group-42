package com.StardewValley;

import com.StardewValley.controllers.LoginRegisterMenuController;
import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.views.RegisterMenuView;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;


    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();

        App.setBackgroundMusic(Assets.getInstance().getStardewMusic());
        App.setCursor();

        main.setScreen(new RegisterMenuView(new LoginRegisterMenuController(), Assets.getInstance().getSkin()));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        if(App.getBackgroundMusic() != null)
            App.getBackgroundMusic().dispose();

        batch.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static void setMain(Main main) {
        Main.main = main;
    }

    public static Batch getBatch() {
        return batch;
    }


}
