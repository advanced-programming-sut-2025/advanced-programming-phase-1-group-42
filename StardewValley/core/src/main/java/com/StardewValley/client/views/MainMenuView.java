package com.StardewValley.client.views;

import com.StardewValley.client.Main;
import com.StardewValley.client.AppClient;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Message;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.HashMap;

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

    private Window onlineUsersWindow;
    private ScrollPane onlineUsersScrollPane;
    private Table onlineUsersTable;

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
        table.add(gameButton).fillX().uniform().padRight(50).right();
        table.add(profileButton).fillX().uniform().padRight(50).right();
        table.add(logoutButton).fillX().uniform().padRight(50).right();
        table.row();


        // Setup online users table and scroll pane
        onlineUsersTable = new Table(skin);
        onlineUsersScrollPane = new ScrollPane(onlineUsersTable, skin);
        onlineUsersScrollPane.setScrollingDisabled(false, true);
        onlineUsersScrollPane.setFadeScrollBars(false);
        loadOnlineUsers();

        // Add scrollpane row
        table.add(onlineUsersScrollPane).colspan(3).fillX().height(150).padTop(40);
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
        loadOnlineUsers();

        if (getGameButton().isChecked()) {
            getGameButton().setChecked(false);

            Message message2 = new Message(new HashMap<>() {{
                put("field", "controller");
                put("change", "GameMenuController");
            }}, Message.Type.change);
            Message responseMessage2 = AppClient.getServerHandler().sendAndWaitForResponse(message2);
            if (!checkMessageValidity(responseMessage2, Message.Type.response)) {
//                getErrorLabel().setText("Network error!");
                return;
            }

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameMenuView(Assets.getInstance().getSkin()));
        }
        else if (getProfileButton().isChecked()) {
            getProfileButton().setChecked(false);

            Message message2 = new Message(new HashMap<>() {{
                put("field", "controller");
                put("change", "ProfileMenuController");
            }}, Message.Type.change);
            Message responseMessage2 = AppClient.getServerHandler().sendAndWaitForResponse(message2);
            if (!checkMessageValidity(responseMessage2, Message.Type.response)) {
//                getErrorLabel().setText("Network error!");
                return;
            }

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ProfileMenuView(Assets.getInstance().getSkin()));
        }
        else if (getLogoutButton().isChecked()) {
            getLogoutButton().setChecked(false);

            if (AppClient.getCurrentUser().isStayLogin()) {
////             DBInteractor.resetStayLogin();
            }

////          DBInteractor.saveUsers();

            Message message2 = new Message(new HashMap<>() {{
                put("field", "controller");
                put("change", "LoginMenuController");
            }}, Message.Type.change);
            Message responseMessage2 = AppClient.getServerHandler().sendAndWaitForResponse(message2);
            if (!checkMessageValidity(responseMessage2, Message.Type.response)) {
//                getErrorLabel().setText("Network error!");
                return;
            }

            AppClient.getCurrentUser().setStayLogin(false);
            AppClient.setCurrentUser(null);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new LoginMenuView(Assets.getInstance().getSkin()));
        }
    }

    private void loadOnlineUsers() {
        onlineUsersTable.clear();  // Clear previous labels

        Message message = new Message(new HashMap<>() {{
            put("function", "updateOnlineUsers");
            put("arguments", "");
        }}, Message.Type.command);

        Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
        ArrayList<String> onlineUsers = responseMessage.getFromBody("message");

        for (String user : onlineUsers) {
            Label label = new Label(user, skin, "Bold");
            label.setFontScale(0.8f);
            onlineUsersTable.add(label).fillX().expandX().center().row();
        }
    }

    public boolean checkMessageValidity(Message message, Message.Type type) {
        return message.getType() == type;
    }

}
