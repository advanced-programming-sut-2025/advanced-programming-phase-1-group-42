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
            player.getSprite().setPosition(player.getCoordinate().getX() * 40,
                    player.getCoordinate().getY() * 40);
            player.getSprite().draw(Main.getBatch());

            if (player.getPlayerDirection() != -1) {
                if(player.getUser().getGender().getName().equals("Male")) {
                    animation(player, player.getPlayerDirection());
                } else {
                    femaleAnimation(player, player.getPlayerDirection());
                }
            }
            player.getInHandGoodSprite().setSize(40, 40);
            Main.getBatch().draw(player.getInHandGoodSprite(),
                player.getInHandGoodSprite().getX(), player.getInHandGoodSprite().getY());
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

    private void femaleAnimation(Player player, int i) {
        Array<Texture> regions = new Array<>(Assets.getInstance().getFemalePlayerTextures().getFirst().size());
        Assets.getInstance().getFemalePlayerTextures().get(i).forEach(regions::add);
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
