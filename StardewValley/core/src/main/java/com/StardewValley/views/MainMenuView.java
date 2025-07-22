package com.StardewValley.views;

import com.StardewValley.Main;
import com.StardewValley.controllers.MainMenuController;
import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuView implements Screen {
    private Skin skin;
    private MainMenuController controller;
    private Stage stage;
    private Table table;
    private Label titleLabel;
    private TextButton logoutButton;
    private TextButton profileButton;
    private TextButton gameButton;
    private Label usernameLabel;
    private Label earnedPointsLabel;
    private Label isPlayingLabel;

    public MainMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.titleLabel = new Label("Main Menu", skin);
        this.titleLabel.setFontScale(1.2f);

        this.logoutButton = new TextButton("Logout", skin);
        this.profileButton = new TextButton("Profile Menu", skin);
        this.gameButton = new TextButton("Game Menu", skin);
        this.usernameLabel = new Label("Username: " + App.getCurrentUser().getUsername(), skin);
        this.usernameLabel.setFontScale(0.7f);
        this.earnedPointsLabel = new Label("Earned Points: " + App.getCurrentUser().getEarnedPoints(), skin);
        this.earnedPointsLabel.setFontScale(0.7f);
        this.isPlayingLabel = new Label("isPlaying: " + App.getCurrentUser().getPlaying(), skin);
        this.isPlayingLabel.setFontScale(0.7f);


        this.controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);

        Table table = new Table();
        table.setFillParent(true);
        table.pad(200).defaults().expandX().padBottom(15);

// Title (centered across 2 columns)
        table.add(titleLabel).colspan(2).center().padBottom(50).padTop(150);
        table.row();

// Username
        table.add(gameButton).fillX().uniform().width(300).padRight(50).right().height(70);
        table.add(usernameLabel).left();
        table.row();

// Earned Points
        table.add(profileButton).fillX().uniform().width(300).padRight(50).right().height(70);
        table.add(earnedPointsLabel).left();
        table.row();

// Is Playing
        table.add(logoutButton).fillX().uniform().width(300).padRight(50).right().height(70);
        table.add(isPlayingLabel).left();
        table.row();

        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        Main.getBatch().begin();
        Assets.getInstance().getMenuBackground().setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Assets.getInstance().getMenuBackground().setPosition(0, 0);
        Assets.getInstance().getMenuBackground().draw(Main.getBatch());
        Main.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        controller.handleMainMenu();
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
        stage.dispose();
    }

    public TextButton getLogoutButton() {
        return logoutButton;
    }

    public TextButton getProfileButton() {
        return profileButton;
    }

    public TextButton getGameButton() {
        return gameButton;
    }
}
