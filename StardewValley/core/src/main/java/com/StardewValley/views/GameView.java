package com.StardewValley.views;

import com.StardewValley.controllers.GameMenuController;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameView implements Screen {
    private Skin skin;
    private GameMenuController controller;

    public GameView(GameMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
