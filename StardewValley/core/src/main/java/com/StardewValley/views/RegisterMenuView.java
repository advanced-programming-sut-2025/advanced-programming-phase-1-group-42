package com.StardewValley.views;

import com.StardewValley.Main;
import com.StardewValley.controllers.LoginRegisterMenuController;
import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.w3c.dom.Text;

public class RegisterMenuView implements Screen {
    private Stage stage;
    private final LoginRegisterMenuController controller;
    private Table table;
    private Skin skin;
    private Label titleLabel;
    private Label usernameLabel;
    private TextField usernameField;
    private Label passwordLabel;
    private TextField passwordField;
    private Label confirmPasswordLabel;
    private TextField confirmPasswordField;
    private TextButton randomPasswordButton;
    private Label nicknameLabel;
    private TextField nicknameField;
    private Label emailLabel;
    private TextField emailField;
    private Label genderLabel;
    private SelectBox<String> genderBox;
    private TextButton registerButton;
    private TextButton exitButton;
    private TextButton loginButton;
    private Label messageLabel;

    private Window securityQuestionWindow;
    private Table securityQuestionTable;
    private Label securityQuestionLabel;
    private SelectBox<String> securityQuestionBox;
    private TextField securityQuestionField;
    private TextButton submitAnswerButton;
    private TextButton backButton;


    public RegisterMenuView(LoginRegisterMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.titleLabel = new Label("Register Menu", skin);
        this.titleLabel.setFontScale(2.0f);
        this.usernameLabel = new Label("Username", skin);
        this.usernameField = new TextField("Parsa-374", skin);
        this.passwordLabel = new Label("Password", skin);
        this.passwordField = new TextField("Passw0rd##", skin);
        this.confirmPasswordLabel = new Label("Confirm Password", skin);
        this.confirmPasswordField = new TextField("Passw0rd##", skin);
        this.randomPasswordButton = new TextButton("Generating Random Password", skin);
        this.nicknameLabel = new Label("Nickname", skin);
        this.nicknameField = new TextField("Parsa", skin);
        this.emailLabel = new Label("Email", skin);
        this.emailField = new TextField("Em.ail8@org.eu", skin);
        this.genderLabel = new Label("Gender", skin);
        this.genderBox = new SelectBox<>(skin);
        this.genderBox.setItems("Male", "Female");
        this.genderBox.setSelectedIndex(0);
        this.registerButton = new TextButton("Register", skin);
        this.exitButton = new TextButton("Exit", skin);
        this.loginButton = new TextButton("Login", skin);
        this.messageLabel = new Label("", skin);

        this.securityQuestionWindow = new Window("", skin);
        this.securityQuestionTable = new Table(skin);
        this.securityQuestionLabel = new Label("Security Question Window", skin);
        this.securityQuestionBox = new SelectBox<>(skin);
        this.securityQuestionBox.setItems(
            "What was the make and model of your first car?",
            "What city were you born in?",
            "What is your favorite movie of all time?"
        );
        this.securityQuestionBox.setSelectedIndex(0);
        this.securityQuestionField = new TextField("yes i was.", skin);
        this.submitAnswerButton = new TextButton("Submit", skin);
        this.backButton = new TextButton("Back", skin);

        this.controller.setView(this);
    }


    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);

        table.pad(200).defaults().expandX().center();
        table.row().padTop(250);
// Title
        table.add().center().colspan(3).fillX().padRight(200);
        table.add(titleLabel).colspan(3).padBottom(30).center();

        table.row();

// Username
        table.add(usernameLabel).left().colspan(3).padRight(30);
        table.add(usernameField).colspan(3).fillX();
        table.row();

// Password
        table.add(passwordLabel).left().colspan(3).padRight(30);
        table.add(passwordField).colspan(3).fillX();
        table.row();

// Confirm Password
        table.add(confirmPasswordLabel).left().colspan(3).padRight(30);
        table.add(confirmPasswordField).colspan(3).fillX();
        table.add(randomPasswordButton).colspan(3).fillX();
        table.row();

// Nickname
        table.add(nicknameLabel).left().colspan(3).padRight(30);
        table.add(nicknameField).colspan(3).fillX();
        table.row();

// Email
        table.add(emailLabel).left().colspan(3).padRight(30);
        table.add(emailField).colspan(3).fillX();
        table.row();

// Gender
        table.add(genderLabel).left().colspan(3).padRight(30);
        table.add(genderBox).colspan(3).fillX();
        table.row();

// Buttons: Register | Login | Exit
        table.add(registerButton).padTop(20).expandX().colspan(3).fillX();
        table.add(loginButton).padTop(20).expandX().colspan(3).fillX();
        table.add(exitButton).padTop(20).expandX().colspan(3).fillX();
        table.add();
        table.row();

// Message Label
        table.add().left().colspan(3);
        table.add(messageLabel).colspan(3).center().padTop(20);
        table.add();
        table.center();

        stage.addActor(table);

        // Initialize the table
        securityQuestionTable = new Table();
        securityQuestionTable.pad(20).defaults().expandX().left().padBottom(15);

// Add Security Question Label + SelectBox
        securityQuestionTable.add(securityQuestionLabel).left().padRight(30);
        securityQuestionTable.add(securityQuestionBox).fillX().colspan(2);
        securityQuestionTable.row();

// Add TextField for Answer
        securityQuestionTable.add(new Label("Your Answer:", skin)).left();
        securityQuestionTable.add(securityQuestionField).fillX().colspan(2);
        securityQuestionTable.row();

// Buttons: Submit + Back
        securityQuestionTable.add(backButton).colspan(1).center().padTop(20).fillX();
        securityQuestionTable.add(submitAnswerButton).colspan(1).padTop(20).center().fillX();
        securityQuestionTable.row();

        securityQuestionWindow = new Window("Security Question", skin);
        securityQuestionWindow.setModal(true);
        securityQuestionWindow.setMovable(true);
        securityQuestionWindow.setResizable(false);

        securityQuestionWindow.add(securityQuestionTable).grow().pad(10);
        securityQuestionWindow.pack();
        securityQuestionWindow.setPosition(
            Gdx.graphics.getWidth() / 2f - securityQuestionWindow.getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f - securityQuestionWindow.getHeight() / 2f
        );

// Add to stage when needed
        stage.addActor(securityQuestionWindow);
        securityQuestionWindow.setVisible(false);

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

        controller.handleRegister();
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

    public TextButton getRandomPasswordButton() {
        return randomPasswordButton;
    }

    public TextButton getRegisterButton() {
        return registerButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getNicknameField() {
        return nicknameField;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public SelectBox<String> getGenderBox() {
        return genderBox;
    }

    public Label getMessageLabel() {
        return messageLabel;
    }

    public TextButton getSubmitAnswerButton() {
        return submitAnswerButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public Stage getStage() {
        return stage;
    }

    public Window getSecurityQuestionWindow() {
        return securityQuestionWindow;
    }

    public SelectBox<String> getSecurityQuestionBox() {
        return securityQuestionBox;
    }

    public TextField getSecurityQuestionField() {
        return securityQuestionField;
    }


}
