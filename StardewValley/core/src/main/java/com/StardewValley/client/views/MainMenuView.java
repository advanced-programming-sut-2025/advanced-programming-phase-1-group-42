package com.StardewValley.client.views;

import com.StardewValley.client.Main;
import com.StardewValley.client.AppClient;
import com.StardewValley.models.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuView implements Screen {
    private Skin skin;
    private Stage stage;
    private Table table;
    private Label titleLabel;
    private TextButton logoutButton;
    private TextButton profileButton;
    private TextButton gameButton;
    private Label usernameLabel;
    private Label earnedPointsLabel;
    private Label isPlayingLabel;

    public MainMenuView(Skin skin) {
        this.skin = skin;
        this.titleLabel = new Label("Main Menu", skin, "Bold");

        this.logoutButton = new TextButton("Logout", skin, "Earth");
        this.profileButton = new TextButton("Profile Menu", skin, "Earth");
        this.gameButton = new TextButton("Game Menu", skin, "Earth");
        this.usernameLabel = new Label("Username: " + AppClient.getCurrentUser().getUsername(), skin);
        this.usernameLabel.setFontScale(0.7f);
        this.earnedPointsLabel = new Label("Earned Points: " + AppClient.getCurrentUser().getEarnedPoints(), skin);
        this.earnedPointsLabel.setFontScale(0.7f);
        this.isPlayingLabel = new Label("isPlaying: " + AppClient.getCurrentUser().getPlaying(), skin);
        this.isPlayingLabel.setFontScale(0.7f);


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
        table.pad(220).defaults().expandX().padBottom(15);

// Title (centered across 2 columns)
        table.add(titleLabel).colspan(3).center().padBottom(50).padTop(200);
        table.row();

// Username
        table.add(usernameLabel).center();
        table.add(earnedPointsLabel).center();
        table.add(isPlayingLabel).center();
        table.row();

// Earned Points
        table.add(gameButton).fillX().uniform().padRight(50).padTop(30).right();
        table.add(profileButton).fillX().uniform().padRight(50).padTop(30).right();
        table.add(logoutButton).fillX().uniform().padRight(50).padTop(30).right();
        table.row();


        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        OrthographicCamera defaultCamera = new OrthographicCamera();
        defaultCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        defaultCamera.update();
        Main.getBatch().setProjectionMatrix(defaultCamera.combined);

        Main.getBatch().begin();
        Assets.getInstance().getMenuBackground().setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Assets.getInstance().getMenuBackground().setPosition(0, 0);
        Assets.getInstance().getMenuBackground().draw(Main.getBatch());
        Main.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        handleMainMenu();
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

    private void handleMainMenu() {

    }
}
