package com.StardewValley.views;

import com.StardewValley.Main;
import com.StardewValley.controllers.LoginRegisterMenuController;
import com.StardewValley.models.Assets;
import com.StardewValley.models.DBInteractor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LoginMenuView implements Screen {
    private Stage stage;
    private Skin skin;
    private LoginRegisterMenuController controller;
    private Table table;
    private Label titleLabel;
    private Label usernameLabel;
    private TextField usernameField;
    private Label passwordLabel;
    private TextField passwordField;
    private CheckBox stayOnLoginCheckBox;
    private TextButton loginButton;
    private TextButton forgetPasswordButton;
    private TextButton backButton;
    private Label errorLabel;

    private Window forgetPasswordWindow;
    private Table forgetPasswordTable;
    private Label usernameForgetLabel;
    private TextField usernameForgetField;
    private TextButton usernameFindButton;
    private Label securityQuestionLabel;
    private TextField securityQuestionField;
    private TextButton securityQuestionSubmitButton;
    private Label newPasswordLabel;
    private TextField newPasswordField;
    private TextButton randomNewPasswordButton;
    private TextField confirmNewPasswordField;
    private TextButton newPasswordConfirmButton;
    private TextButton forgetBackButton;
    private Label forgetErrorLabel;


    public LoginMenuView(LoginRegisterMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.stage = new Stage();
        this.titleLabel = new Label("Login Menu", skin, "Bold");
        this.usernameLabel = new Label("Username", skin);
        this.usernameLabel.setFontScale(0.7f);
        this.usernameField = new TextField("Parsa-374", skin);
        this.passwordLabel = new Label("Password", skin);
        this.passwordLabel.setFontScale(0.7f);
        this.passwordField = new TextField("Passw0rd##", skin);
        this.stayOnLoginCheckBox = new CheckBox("Stay Logged in", skin);
        this.loginButton = new TextButton("Login", skin);
        this.forgetPasswordButton = new TextButton("Forget Password", skin);
        this.backButton = new TextButton("Back", skin);
        this.errorLabel = new Label("", skin);

        this.usernameForgetLabel = new Label("Username", skin);
        this.usernameForgetLabel.setFontScale(0.7f);
        this.usernameForgetField = new TextField("Parsa-374", skin);
        this.usernameFindButton = new TextButton("Find Username", skin);
        this.securityQuestionLabel = new Label("Question: ", skin);
        this.securityQuestionLabel.setFontScale(0.7f);
        this.securityQuestionField = new TextField("", skin);
        this.securityQuestionSubmitButton = new TextButton("Submit Answer", skin);
        this.newPasswordLabel = new Label("New Password", skin);
        this.newPasswordLabel.setFontScale(0.7f);
        this.newPasswordField = new TextField("Passw0rd##", skin);
        this.confirmNewPasswordField = new TextField("Passw0rd##", skin);
        this.randomNewPasswordButton = new TextButton("Generating Random Password", skin);
        this.newPasswordConfirmButton = new TextButton("Confirm New Password", skin);
        this.forgetBackButton = new TextButton("Back", skin);
        this.forgetErrorLabel = new Label("", skin);
        this.forgetErrorLabel.setFontScale(0.7f);

        this.controller.setView(this);

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table(skin);
        table.setFillParent(true);
        table.pad(320).defaults().expandX().padBottom(15);
        table.row().padTop(200);

// Title label (centered across 3 columns)
        table.add(titleLabel).colspan(3).center();
        table.row();

// Username
        table.add(usernameLabel).left();
        table.add(usernameField).fillX().height(50);
        table.row();

// Password
        table.add(passwordLabel).left();
        table.add(passwordField).fillX().height(50);
        table.add(stayOnLoginCheckBox).center().height(50);
        table.row();

// Buttons: Login | Forgot Password | Back
        table.add(loginButton).padTop(20).expandX().fillX().size(350, 70);
        table.add(forgetPasswordButton).padTop(20).expandX().fillX().size(350, 70);
        table.add(backButton).padTop(20).expandX().fillX().size(350, 70);
        table.row();

        table.add(errorLabel).colspan(3).center();
        table.row();

        stage.addActor(table);

        forgetPasswordTable = new Table(skin);
        forgetPasswordTable.pad(40).defaults().expandX().padBottom(15);

// Username field with Find button
        forgetPasswordTable.add(usernameForgetLabel).left();
        forgetPasswordTable.add(usernameForgetField).fillX().height(50);
        forgetPasswordTable.add(usernameFindButton).expandX().fillX().height(70);
        forgetPasswordTable.row();

// Security question field with Submit button
        forgetPasswordTable.add(securityQuestionLabel).colspan(3).center();
        forgetPasswordTable.row();

        forgetPasswordTable.add(securityQuestionField).fillX().height(50);
        forgetPasswordTable.add(securityQuestionSubmitButton).fillX().expandX().height(70);
        forgetPasswordTable.row();

// New password field
        forgetPasswordTable.add(newPasswordLabel).left();
        forgetPasswordTable.add(newPasswordField).fillX().height(50);
        forgetPasswordTable.add(confirmNewPasswordField).fillX().expandX().height(50);
        forgetPasswordTable.row();

// Confirm new password field + Confirm button
        forgetPasswordTable.add(randomNewPasswordButton).fillX().height(70);
        forgetPasswordTable.add(newPasswordConfirmButton).fillX().height(70);
        forgetPasswordTable.add(forgetBackButton).right().fillX().height(70);
        forgetPasswordTable.row();

// Error label (centered)
        forgetPasswordTable.add(forgetErrorLabel).colspan(3).center().padTop(10);
        forgetPasswordTable.row();

        forgetPasswordWindow = new Window("Forgot Password", skin);
        forgetPasswordWindow.getTitleLabel().setFontScale(0.8f);
        GameMenuView.setWindowForTable(forgetPasswordWindow, forgetPasswordTable, stage);
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

        controller.handleLogin();
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

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getForgetPasswordButton() {
        return forgetPasswordButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public TextButton getUsernameFindButton() {
        return usernameFindButton;
    }

    public TextButton getSecurityQuestionSubmitButton() {
        return securityQuestionSubmitButton;
    }

    public TextButton getNewPasswordConfirmButton() {
        return newPasswordConfirmButton;
    }

    public TextButton getForgetBackButton() {
        return forgetBackButton;
    }

    public void initForgetPasswordWindow() {
        this.usernameForgetField.setText("Parsa-374");
        this.securityQuestionLabel.setText("Question: ");
        this.securityQuestionField.setText("");
        this.newPasswordField.setText("Passw0rd##");
        this.confirmNewPasswordField.setText("Passw0rd##");
        this.forgetErrorLabel.setText("");

        forgetPasswordWindow.setVisible(true);
    }

    public TextField getUsernameForgetField() {
        return usernameForgetField;
    }

    public Label getForgetErrorLabel() {
        return forgetErrorLabel;
    }

    public Label getSecurityQuestionLabel() {
        return securityQuestionLabel;
    }

    public TextField getSecurityQuestionField() {
        return securityQuestionField;
    }

    public TextButton getRandomNewPasswordButton() {
        return randomNewPasswordButton;
    }

    public TextField getNewPasswordField() {
        return newPasswordField;
    }

    public TextField getConfirmNewPasswordField() {
        return confirmNewPasswordField;
    }

    public Window getForgetPasswordWindow() {
        return forgetPasswordWindow;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public CheckBox getStayOnLoginCheckBox() {
        return stayOnLoginCheckBox;
    }
}
