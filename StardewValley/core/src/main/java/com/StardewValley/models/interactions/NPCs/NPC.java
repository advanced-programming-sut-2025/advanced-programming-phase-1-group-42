package com.StardewValley.models.interactions.NPCs;

import com.StardewValley.client.AppClient;
import com.StardewValley.models.Message;
import com.StardewValley.models.enums.Season;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Game;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.server.ClientHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class NPC {
    private final NPCTypes type;
    private final ArrayList<NPCFriendship> friendships = new ArrayList<>();
    Coordinate coordinate;
    private boolean isFirstMeet = true;

    public NPC(NPCTypes type, ArrayList<Player> players) {
        this.type = type;

        for (Player player : players) {
            friendships.add(new NPCFriendship(player.getUsername(), this.getType()));
        }
    }

    public NPCTypes getType() {
        return type;
    }

    public ArrayList<NPCFriendship> getFriendships() {
        return friendships;
    }

    public boolean isBirthdayToday(ClientHandler clientHandler) {

        return clientHandler.getClientGame().getDateTime().getSeasonOfYear().equals(type.getBirthday().first())
                && clientHandler.getClientGame().getDateTime().getDayOfSeason()==type.getBirthday().second();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }


    public String npcDialogs(ClientHandler clientHandler) {
        for (NPCFriendship friendship : friendships) {
            if (friendship.getPlayerUsername().equals(clientHandler.getClientPlayer().getUsername())) {
                if (friendship.getFirstMeetToday()){
                    getFriendship(clientHandler.getClientPlayer()).setFriendshipPoints(20,
                        clientHandler.getClientGame());
                    friendship.setFirstMeetToday();
                }
            }
        }

        if (isBirthdayToday(clientHandler)) {
            return type.getDialogs().getFirst();
        }

        Random random = new Random();

        //random talk about favorites
        int randomInterest = random.nextInt(3);
        if (randomInterest == 0) {
            int randomInterestIndex = random.nextInt(2);
            return type.getDialogs().get(randomInterestIndex+10);
        }


        //talk about weather or season
        int seasonIndex = -1;
        int weatherIndex = -1;
        if (clientHandler.getClientGame().getDateTime().getSeasonOfYear().equals(Season.SPRING)) {
            seasonIndex = 2;
        } else if (clientHandler.getClientGame().getDateTime().getSeasonOfYear().equals(Season.SUMMER)) {
            seasonIndex = 3;
        } else if (clientHandler.getClientGame().getDateTime().getSeasonOfYear().equals(Season.FALL)) {
            seasonIndex = 4;
        } else if (clientHandler.getClientGame().getDateTime().getSeasonOfYear().equals(Season.WINTER)) {
            seasonIndex = 5;
        }

        if (clientHandler.getClientGame().getWeather().getName().equals("Sunny")) {
            weatherIndex = 6;
        } else if (clientHandler.getClientGame().getWeather().getName().equals("Rainy")) {
            weatherIndex = 7;
        } else if (clientHandler.getClientGame().getWeather().getName().equals("Storm")) {
            weatherIndex = 8;
        } else if (clientHandler.getClientGame().getWeather().getName().equals("Snowy")) {
            weatherIndex = 9;
        }

        if (seasonIndex != -1 && weatherIndex != -1) {
            int dialogIndex = random.nextBoolean() ? seasonIndex : weatherIndex;
            return type.getDialogs().get(dialogIndex);
        } else if (seasonIndex != -1) {
            return type.getDialogs().get(seasonIndex);
        } else if (weatherIndex != -1) {
            return type.getDialogs().get(weatherIndex);
        }

        return null;

    }


    public NPCFriendship getFriendship(Player player) {
        for (NPCFriendship friendship : friendships) {
            if (friendship.getPlayerUsername().equals(player.getPlayerUsername())) {
                return friendship;
            }
        }

        //TODO Nader
//        friendships.add(new NPCFriendship(player,this));
//        for (NPCFriendship friendship : friendships) {
//            if (friendship.getPlayer().equals(player)) {
//                return friendship;
//            }
//        }
        return null;
    }

    public void finishQuest(int index, Player player, ClientHandler clientHandler) {
        if (type.getName().equals("Sebastian")){
            NPCRewardsFunctions.sebastianRewards(index,getFriendship(player), clientHandler);
        } else if (type.getName().equals("Abigail")){
            NPCRewardsFunctions.abigailRewards(index,getFriendship(player), clientHandler);
        } else if (type.getName().equals("Leah")){
            NPCRewardsFunctions.leahRewards(index,getFriendship(player), clientHandler);
        } else if (type.getName().equals("Harvey")){
            NPCRewardsFunctions.harveyRewards(index,getFriendship(player), clientHandler);
        } else if (type.getName().equals("Robin")){
            NPCRewardsFunctions.robinRewards(index,getFriendship(player), clientHandler);
        }
    }

    public void getGift(Good good, ClientHandler clientHandler) {
        Player player = clientHandler.getClientPlayer();
        getFriendship(player).getGifts().add(good);
        if (!getFriendship(player).getGotGiftToday()){
            if (type.getFavorites().contains(good.getType())){
                getFriendship(player).setFriendshipPoints(200, clientHandler.getClientGame() );
            } else {
                getFriendship(player).setFriendshipPoints(50, clientHandler.getClientGame() );
            }
            getFriendship(player).setGotGiftToday();
        }

    }
}
