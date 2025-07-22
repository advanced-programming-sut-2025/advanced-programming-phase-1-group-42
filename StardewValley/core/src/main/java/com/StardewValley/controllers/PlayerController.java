package com.StardewValley.controllers;

import com.StardewValley.Main;
import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.interactions.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

public class PlayerController {
    public void updatePlayer() {
        for (Player player : App.getCurrentGame().getPlayers()) {
            if (player.getPlayerDirection() != -1)
                animation(player, player.getPlayerDirection());
        }
    }

    private void animation(Player player, int i) {
        Array<Texture> regions = new Array<>(Assets.getInstance().getPlayerTextures().getFirst().size());
        Assets.getInstance().getPlayerTextures().get(i).forEach(regions::add);
        Animation<Texture> animation = new Animation<>(0.1f, regions);

        player.getSprite().setRegion(animation.getKeyFrame(player.getTime()));

        if(!animation.isAnimationFinished(player.getTime())) {
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            player.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }
}
