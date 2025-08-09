package com.StardewValley.client.views;

import com.StardewValley.client.Main;
import com.StardewValley.client.AppClient;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Message;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RegisterMenuView implements Screen {
    private Stage stage;
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
    private Label securityQuestionAnswerLabel;
    private TextField securityQuestionField;
    private TextButton submitAnswerButton;
    private TextButton backButton;

    /*
    	"default": {
		"font": "Text"
	},
	"WhiteText": {
		"font": "WhiteText"
	},
	"Impact": {
		"font": "Impact"
	},
	"BoldImpact": {
		"font": "BoldImpact"
	},
	"Bold": {
		"font": "Bold"
	}
     */

    public RegisterMenuView(Skin skin) {
        this.skin = skin;
        this.titleLabel = new Label("Register Menu", skin, "Bold");
        this.usernameLabel = new Label("Username", skin);
        this.usernameLabel.setFontScale(0.7f);
        this.usernameField = new TextField("Parsa-374", skin);
        this.passwordLabel = new Label("Password", skin);
        this.passwordLabel.setFontScale(0.7f);
        this.passwordField = new TextField("Passw0rd##", skin);
        this.confirmPasswordLabel = new Label("Confirm Password", skin);
        this.confirmPasswordLabel.setFontScale(0.7f);
        this.confirmPasswordField = new TextField("Passw0rd##", skin);
        this.randomPasswordButton = new TextButton("Generating Random Password", skin);
        this.nicknameLabel = new Label("Nickname", skin);
        this.nicknameLabel.setFontScale(0.7f);
        this.nicknameField = new TextField("Parsa", skin);
        this.emailLabel = new Label("Email", skin);
        this.emailLabel.setFontScale(0.7f);
        this.emailField = new TextField("Em.ail8@org.eu", skin);
        this.genderLabel = new Label("Gender", skin);
        this.genderLabel.setFontScale(0.7f);
        this.genderBox = new SelectBox<>(skin);
        this.genderBox.setItems("Male", "Female");
        this.genderBox.setSelectedIndex(0);
        this.registerButton = new TextButton("Register", skin);
        this.exitButton = new TextButton("Exit", skin);
        this.loginButton = new TextButton("Login", skin);
        this.messageLabel = new Label("", skin);
        this.messageLabel.setFontScale(0.7f);

        this.securityQuestionWindow = new Window("", skin);
        this.securityQuestionTable = new Table(skin);
        this.securityQuestionLabel = new Label("Security Question Window", skin);
        this.securityQuestionLabel.setFontScale(0.7f);
        this.securityQuestionBox = new SelectBox<>(skin);
        this.securityQuestionBox.setItems(
            "What was the make and model of your first car?",
            "What city were you born in?",
            "What is your favorite movie of all time?"
        );
        this.securityQuestionBox.setSelectedIndex(0);
        this.securityQuestionAnswerLabel = new Label("Your Answer:", skin);
        this.securityQuestionAnswerLabel.setFontScale(0.7f);
        this.securityQuestionField = new TextField("yes i was.", skin);
        this.submitAnswerButton = new TextButton("Submit", skin);
        this.backButton = new TextButton("Back", skin);
    }


    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);

        table.pad(200).defaults().expandX().center();
        table.row().padTop(320);
// Title
        table.add(titleLabel).colspan(3).padBottom(20).center();

        table.row();

// Username
        table.add(usernameLabel).left().padRight(30);
        table.add(usernameField).fillX().height(50);
        table.row();

// Password
        table.add(passwordLabel).left().padRight(30);
        table.add(passwordField).fillX().height(50);
        table.row();

// Confirm Password
        table.add(confirmPasswordLabel).left().padRight(30);
        table.add(confirmPasswordField).fillX().height(50);
        table.add(randomPasswordButton).fillX().size(500, 70);
        table.row();

// Nickname
        table.add(nicknameLabel).left().padRight(30);
        table.add(nicknameField).fillX().height(50);
        table.row();

// Email
        table.add(emailLabel).left().padRight(30);
        table.add(emailField).fillX().height(50);
        table.row();

// Gender
        table.add(genderLabel).left().padRight(30);
        table.add(genderBox).fillX().height(50);
        table.row();

// Buttons: Register | Login | Exit
        table.add(registerButton).padTop(10).expandX().fillX().size(450, 70);
        table.add(loginButton).padTop(10).expandX().fillX().size(450, 70);
        table.add(exitButton).padTop(10).expandX().fillX().size(450, 70);
        table.add();
        table.row();

// Message Label
        table.add(messageLabel).colspan(3).center().padTop(5);
        table.center();

        stage.addActor(table);

        // Initialize the table
        securityQuestionTable = new Table();
        securityQuestionTable.pad(20).defaults().expandX().left().padBottom(15);

// Add Security Question Label + SelectBox
        securityQuestionTable.add(securityQuestionLabel).left().padRight(30);
        securityQuestionTable.add(securityQuestionBox).fillX().colspan(2).height(50);
        securityQuestionTable.row();

// Add TextField for Answer
        securityQuestionTable.add(securityQuestionAnswerLabel).left();
        securityQuestionTable.add(securityQuestionField).fillX().colspan(2).height(50);
        securityQuestionTable.row();

// Buttons: Submit + Back
        securityQuestionTable.add(backButton).colspan(1).center().padTop(20).fillX().height(70);
        securityQuestionTable.add(submitAnswerButton).colspan(1).padTop(20).center().fillX().height(70);
        securityQuestionTable.row();

        securityQuestionWindow = new Window("Security Question", skin);
        securityQuestionWindow.getTitleLabel().setFontScale(0.8f);
        GameMenuView.setWindowForTable(securityQuestionWindow, securityQuestionTable, stage);

//        randomPasswordButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                Message message = new Message(new HashMap<>() {{
//                    put("function", "generateRandomPassword");
//                    put("arguments", null);
//                }},
//                    Message.Type.command);
//
//                Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message, 500);
//                if (!checkMessageValidity(responseMessage, Message.Type.response)) {
//                    getMessageLabel().setText("Network error!");
//                }
//
//                String randomPassword = responseMessage.getFromBody("message");
//                getPasswordField().setText(randomPassword);
//                getConfirmPasswordField().setText(randomPassword);
//            }
//        });
//
//        exitButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                AppClient.getServerHandler().end();
//                Main.getBatch().dispose();
//            }
//        });
//
//        loginButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                Message message = new Message(new HashMap<>() {{
//                    put("field", "controller");
//                    put("change", "LoginMenuController");
//                }}, Message.Type.change);
//                Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message, 500);
//                if (!checkMessageValidity(responseMessage, Message.Type.response)) {
//                    getMessageLabel().setText("Network error!");
//                    return;
//                }
//
//                Main.getMain().getScreen().dispose();
//                Main.getMain().setScreen(new LoginMenuView(Assets.getInstance().getSkin()));
//            }
//        });
//
//
//        registerButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                Message message = new Message(new HashMap<>() {{
//                    put("function", "register");
//                    put("arguments", new ArrayList<String>(Arrays.asList(
//                        getUsernameField().getText(),
//                        getPasswordField().getText(),
//                        getConfirmPasswordField().getText(),
//                        getNicknameField().getText(),
//                        getEmailField().getText(),
//                        getGenderBox().getSelected()
//                    )));
//                }}, Message.Type.command);
//
//                Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message, 500);
//                if (!checkMessageValidity(responseMessage, Message.Type.response)) {
//                    getMessageLabel().setText("Network error!");
//                    return;
//                }
//
//                if(!responseMessage.getBooleanFromBody("success")) {
//                    getMessageLabel().setText(responseMessage.getFromBody("message"));
//                    return;
//                }
//
//                getSecurityQuestionWindow().setVisible(true);
//                getSecurityQuestionBox().setSelectedIndex(0);
//                getSecurityQuestionField().setText("yes i was");
//            }
//        });
//
//        backButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                getSecurityQuestionWindow().setVisible(false);
//            }
//        });
//
//        submitAnswerButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                Message message = new Message(new HashMap<>() {{
//                    put("field", "users");
//                    put("change", new ArrayList<>(Arrays.asList(
//                        getUsernameField().getText(),
//                        getPasswordField().getText(), //getSHA256
//                        getNicknameField().getText(),
//                        getEmailField().getText(),
//                        getGenderBox().getSelected(), // Gender.findGender
//                        String.valueOf(getSecurityQuestionBox().getSelectedIndex()),
//                        getSecurityQuestionField().getText())
//                    ));
//                }}, Message.Type.change);
//
//                Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message, 500);
//                if (!checkMessageValidity(responseMessage, Message.Type.response)) {
//                    getMessageLabel().setText("Network error!");
//                    return;
//                }
//
//
//                getMessageLabel().setText("Your account has been successfully registered!");
//                getSecurityQuestionWindow().setVisible(false);
//            }
//        });

    }

    @Override
    public void render(float v) {
        Main.getBatch().begin();
        AppClient.getAssets().getMenuBackground().setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        AppClient.getAssets().getMenuBackground().setPosition(0, 0);
        AppClient.getAssets().getMenuBackground().draw(Main.getBatch());
        Main.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        handleRegister();
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

    private void handleRegister() {

//        System.out.println(AppClient.getServerHandler().isAlive());
        if (getRandomPasswordButton().isChecked()) {
            getRandomPasswordButton().setChecked(false);

            Message message = new Message(new HashMap<>() {{
                put("function", "generateRandomPassword");
                put("arguments", null);
            }},
                Message.Type.command);

            Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
            if (!checkMessageValidity(responseMessage, Message.Type.response)) {
                getMessageLabel().setText("Network error!");
            }

            String randomPassword = responseMessage.getFromBody("message");
            getPasswordField().setText(randomPassword);
            getConfirmPasswordField().setText(randomPassword);
        }
        else if (getExitButton().isChecked()) {
            getExitButton().setChecked(false);
            try {
                AppClient.getServerHandler().getSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.getBatch().dispose();
        }
        else if (getLoginButton().isChecked()) {
            getLoginButton().setChecked(false);

            Message message = new Message(new HashMap<>() {{
                put("field", "controller");
                put("change", "LoginMenuController");
            }}, Message.Type.change);
            Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
            if (!checkMessageValidity(responseMessage, Message.Type.response)) {
                getMessageLabel().setText("Network error!");
                return;
            }

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new LoginMenuView(AppClient.getAssets().getSkin()));
        }
        else if (getRegisterButton().isChecked()) {
            getRegisterButton().setChecked(false);

            Message message = new Message(new HashMap<>() {{
                put("function", "register");
                put("arguments", new ArrayList<String>(Arrays.asList(
                    getUsernameField().getText(),
                    getPasswordField().getText(),
                    getConfirmPasswordField().getText(),
                    getNicknameField().getText(),
                    getEmailField().getText(),
                    getGenderBox().getSelected()
                )));
            }}, Message.Type.command);

            Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
            if (!checkMessageValidity(responseMessage, Message.Type.response)) {
                getMessageLabel().setText("Network error!");
                return;
            }

            if(!responseMessage.getBooleanFromBody("success")) {
                getMessageLabel().setText(responseMessage.getFromBody("message"));
                return;
            }

            getSecurityQuestionWindow().setVisible(true);
            getSecurityQuestionBox().setSelectedIndex(0);
            getSecurityQuestionField().setText("yes i was");
        }
        else if (getBackButton().isChecked()) {
            getBackButton().setChecked(false);
            getSecurityQuestionWindow().setVisible(false);
        }
        else if (getSubmitAnswerButton().isChecked()) {
            getSubmitAnswerButton().setChecked(false);

            Message message = new Message(new HashMap<>() {{
                put("field", "users");
                put("change", new ArrayList<>(Arrays.asList(
                     getUsernameField().getText(),
                    getPasswordField().getText(), //getSHA256
                    getNicknameField().getText(),
                    getEmailField().getText(),
                    getGenderBox().getSelected(), // Gender.findGender
                    String.valueOf(getSecurityQuestionBox().getSelectedIndex()),
                    getSecurityQuestionField().getText())
                ));
            }}, Message.Type.change);

            Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
            if (!checkMessageValidity(responseMessage, Message.Type.response)) {
                getMessageLabel().setText("Network error!");
                return;
            }


            getMessageLabel().setText("Your account has been successfully registered!");
            getSecurityQuestionWindow().setVisible(false);
        }
    }

    public boolean checkMessageValidity(Message message, Message.Type type) {
        return message.getType() == type;
    }

}
