package com.StardewValley.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
    private static Assets instance;
    private static final Skin skin = new Skin(Gdx.files.internal("/Users/mparsaarani/Sharif_Github/Term2Project/advanced-programming-phase-1-group-42/StardewValley/assets/Theme/skin/craftacular-ui.json"));

    public static Assets getInstance() {
        return instance;
    }
    public static Skin getSkin() {
        return skin;
    }
}
