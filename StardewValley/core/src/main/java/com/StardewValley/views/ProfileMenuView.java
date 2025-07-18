package com.StardewValley.views;

import com.StardewValley.Main;
import com.StardewValley.controllers.ProfileMenuController;
import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ProfileMenuView implements Screen {
    private Skin skin;
    private ProfileMenuController controller;
    private Stage stage;
    private Window window;
    private Table table;
    private Label usernameLabel;
    private TextField usernameField;
    private TextButton usernameChangeButton;
    private Label passwordLabel;
    private TextField passwordField;
    private TextButton passwordChangeButton;
    private Label nicknameLabel;
    private TextField nicknameField;
    private TextButton nicknameChangeButton;
    private Label emailLabel;
    private TextField emailField;
    private TextButton emailChangeButton;
    private Label genderLabel;
    private TextField genderField;  // should be disabled
    private Label earnedPointsLabel;
    private TextField earnedPointsField;
    private Label maxPointsLabel;
    private TextField maxPointsField;
    private Label gamePlayLabel;
    private TextField gamePlayField;
    private TextButton backButton;
    private Label errorLabel;

    public ProfileMenuView(ProfileMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;

        this.usernameLabel = new Label("Username", skin);
        this.usernameField = new TextField(App.getCurrentUser().getUsername(), skin);
        this.usernameChangeButton = new TextButton("Change Username", skin);

        this.passwordLabel = new Label("Password", skin);
        this.passwordField = new TextField(App.getCurrentUser().getPassword(), skin);
        this.passwordChangeButton = new TextButton("Change Password", skin);

        this.nicknameLabel = new Label("Nickname", skin);
        this.nicknameField = new TextField(App.getCurrentUser().getNickname(), skin);
        this.nicknameChangeButton = new TextButton("Change Nickname", skin);

        this.emailLabel = new Label("Email", skin);
        this.emailField = new TextField(App.getCurrentUser().getEmail(), skin);
        this.emailChangeButton = new TextButton("Change Email", skin);



        this.genderLabel = new Label("Gender", skin);
        this.genderField = new TextField(App.getCurrentUser().getGender().getName(), skin);
        this.genderField.setDisabled(true);

        this.earnedPointsLabel = new Label("Earned Points", skin);
        this.earnedPointsField = new TextField(String.valueOf(App.getCurrentUser().getEarnedPoints()), skin);
        this.earnedPointsField.setDisabled(true);

        this.maxPointsLabel = new Label("Max Points", skin);
        this.maxPointsField = new TextField(String.valueOf(App.getCurrentUser().getMaxPoints()), skin);
        this.maxPointsField.setDisabled(true);

        this.gamePlayLabel = new Label("Game Play", skin);
        this.gamePlayField = new TextField(String.valueOf(App.getCurrentUser().getGamePlay()), skin);
        this.gamePlayField.setDisabled(true);

        this.backButton = new TextButton("Back", skin);
        this.errorLabel = new Label("", skin);

        this.controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table(skin);
        table.pad(50).defaults().expandX().padBottom(15);

// Row 1
        table.add(usernameLabel).left().padRight(30);
        table.add(usernameField).padRight(30).width(200);
        table.add(usernameChangeButton).expandX().fillX().padRight(30);
        table.add(genderLabel).left().padRight(30);
        table.add(genderField).width(200);
        table.row();

// Row 2
        table.add(passwordLabel).left().padRight(30);
        table.add(passwordField).padRight(30).width(200);
        table.add(passwordChangeButton).expandX().fillX().padRight(30);
        table.add(earnedPointsLabel).left().padRight(30);
        table.add(earnedPointsField).width(200);
        table.row();

// Row 3
        table.add(nicknameLabel).left().padRight(30);
        table.add(nicknameField).padRight(30).width(200);
        table.add(nicknameChangeButton).expandX().fillX().padRight(30);
        table.add(maxPointsLabel).left().padRight(30);
        table.add(maxPointsField).width(200);
        table.row();

// Row 4
        table.add(emailLabel).left().padRight(30);
        table.add(emailField).padRight(30).width(200);
        table.add(emailChangeButton).expandX().fillX().padRight(30);
        table.add(gamePlayLabel).left().padRight(30);
        table.add(gamePlayField).width(200);
        table.row();

// Row 5 – Back Button (centered across all columns)
        table.add(backButton).colspan(5).center().fillX().expandX().padTop(20);
        table.row();

// Row 6 – Error Label
        table.add(errorLabel).colspan(5).center()   .fillX().expandX().padTop(10);
        table.row();

        window = new Window("Pr ofile Menu", skin);
        window.setModal(true);
        window.setMovable(true);
        window.setResizable(false);

        window.add(table).grow().pad(10);
        window.pack();
        window.setPosition(
            Gdx.graphics.getWidth() / 2f - window.getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f - window.getHeight() / 2f
        );

        stage.addActor(window);
    }

    @Override
    public void render(float v) {
        Main.getBatch().begin();
        Assets.getInstance().getMenuBackground2().setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Assets.getInstance().getMenuBackground2().setPosition(0, 0);
        Assets.getInstance().getMenuBackground2().draw(Main.getBatch());
        Main.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        controller.handleProfile();
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

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextButton getUsernameChangeButton() {
        return usernameChangeButton;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextButton getPasswordChangeButton() {
        return passwordChangeButton;
    }

    public TextField getNicknameField() {
        return nicknameField;
    }

    public TextButton getNicknameChangeButton() {
        return nicknameChangeButton;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public TextButton getEmailChangeButton() {
        return emailChangeButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }
}
