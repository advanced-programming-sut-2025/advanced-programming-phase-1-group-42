package com.StardewValley.models.interactions.NPCs;

import com.StardewValley.client.AppClient;
import com.StardewValley.models.enums.Season;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.goods.Good;

import java.util.ArrayList;
import java.util.Random;


public class NPC {
    private final NPCTypes type;
    private final ArrayList<NPCFriendship> friendships = new ArrayList<>();
    Coordinate coordinate;
    private boolean isFirstMeet = true;

    public NPC(NPCTypes type) {
        this.type = type;
    }

    public NPCTypes getType() {
        return type;
    }

    public ArrayList<NPCFriendship> getFriendships() {
        return friendships;
    }

    public boolean isBirthdayToday() {

        return AppClient.getCurrentGame().getDateTime().getSeasonOfYear().equals(type.getBirthday().first())
                && AppClient.getCurrentGame().getDateTime().getDayOfSeason()==type.getBirthday().second();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }


    public String  npcDialogs() {
        for (NPCFriendship friendship : friendships) {
            if (friendship.getPlayer().equals(AppClient.getCurrentGame().getCurrentPlayer())) {
                if (friendship.getFirstMeetToday()){
                    getFriendship().setFriendshipPoints(20);
                    friendship.setFirstMeetToday();
                }
            }
        }

        if (isBirthdayToday()) {
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
        if (AppClient.getCurrentGame().getDateTime().getSeasonOfYear().equals(Season.SPRING)) {
            seasonIndex = 2;
        } else if (AppClient.getCurrentGame().getDateTime().getSeasonOfYear().equals(Season.SUMMER)) {
            seasonIndex = 3;
        } else if (AppClient.getCurrentGame().getDateTime().getSeasonOfYear().equals(Season.FALL)) {
            seasonIndex = 4;
        } else if (AppClient.getCurrentGame().getDateTime().getSeasonOfYear().equals(Season.WINTER)) {
            seasonIndex = 5;
        }

        if (AppClient.getCurrentGame().getWeather().getName().equals("Sunny")) {
            weatherIndex = 6;
        } else if (AppClient.getCurrentGame().getWeather().getName().equals("Rainy")) {
            weatherIndex = 7;
        } else if (AppClient.getCurrentGame().getWeather().getName().equals("Storm")) {
            weatherIndex = 8;
        } else if (AppClient.getCurrentGame().getWeather().getName().equals("Snowy")) {
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


    public NPCFriendship getFriendship() {
        for (NPCFriendship friendship : friendships) {
            if (friendship.getPlayer().equals(AppClient.getCurrentGame().getCurrentPlayer())) {
                return friendship;
            }
        }
        friendships.add(new NPCFriendship(AppClient.getCurrentGame().getCurrentPlayer(),this));
        for (NPCFriendship friendship : friendships) {
            if (friendship.getPlayer().equals(AppClient.getCurrentGame().getCurrentPlayer())) {
                return friendship;
            }
        }
        return null;
    }

    public void finishQuest(int index) {
        if (type.getName().equals("Sebastian")){
            NPCRewardsFunctions.sebastianRewards(index,getFriendship());
        } else if (type.getName().equals("Abigail")){
            NPCRewardsFunctions.abigailRewards(index,getFriendship());
        } else if (type.getName().equals("Leah")){
            NPCRewardsFunctions.leahRewards(index,getFriendship());
        } else if (type.getName().equals("Harvey")){
            NPCRewardsFunctions.harveyRewards(index,getFriendship());
        } else if (type.getName().equals("Robin")){
            NPCRewardsFunctions.robinRewards(index,getFriendship());
        }
    }

    public void getGift(Good good) {
        getFriendship().getGifts().add(good);
        if (!getFriendship().getGotGiftToday()){
            if (type.getFavorites().contains(good.getType())){
                getFriendship().setFriendshipPoints(200);
            } else {
                getFriendship().setFriendshipPoints(50);
            }
            getFriendship().setGotGiftToday();
        }

    }
}
