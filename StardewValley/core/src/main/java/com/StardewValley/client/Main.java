package com.StardewValley.client;

import com.StardewValley.models.Assets;
import com.StardewValley.client.views.RegisterMenuView;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.net.Socket;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;


    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();

        try (Socket socket = new Socket("localhost", 1111)) {
            AppClient.setServerHandler(new ServerHandler(socket));
            System.out.println("Connected to server with port: " + socket.getPort());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        AppClient.setBackgroundMusic(Assets.getInstance().getStardewMusic());
        AppClient.setCursor();

        main.setScreen(new RegisterMenuView(Assets.getInstance().getSkin()));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        if(AppClient.getBackgroundMusic() != null)
            AppClient.getBackgroundMusic().dispose();

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
