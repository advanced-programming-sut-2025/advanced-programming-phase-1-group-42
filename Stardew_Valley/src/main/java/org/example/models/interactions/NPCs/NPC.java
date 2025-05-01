package org.example.models.interactions.NPCs;

import org.example.models.App;
import org.example.models.enums.Season;
import org.example.models.enums.WeatherType;
import org.example.models.game_structure.Cordinate;
import org.example.models.game_structure.weathers.Weather;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import java.util.Random;
import java.util.ArrayList;
import java.util.Map;


public class NPC {
    private final NPCTypes type;
    private final ArrayList<NPCFriendship> friendships = new ArrayList<>();
    Cordinate cordinate;

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

        return App.getCurrentGame().getDateTime().getSeasonOfYear().equals(type.getBirthday().getFirst())
                && App.getCurrentGame().getDateTime().getDayOfSeason()==type.getBirthday().getSecond();
    }

    public Cordinate getCordinate() {
        return cordinate;
    }


    public void npcDialogs() {
        getFriendship().setFriendshipPoints(20);

        if (isBirthdayToday()) {
            System.out.print(type.getDialogs().getFirst());
            return;
        }

        Random random = new Random();

        //random talk about favorites
        int randomInterest = random.nextInt(3);
        if (randomInterest == 0) {
            int randomInterestIndex = random.nextInt(2);
            System.out.print(type.getDialogs().get(randomInterestIndex+10));
            return;
        }


        //talk about weather or season
        int seasonIndex = -1;
        int weatherIndex = -1;
        if (App.getCurrentGame().getDateTime().getSeasonOfYear().equals(Season.SPRING)) {
            seasonIndex = 2;
        } else if (App.getCurrentGame().getDateTime().getSeasonOfYear().equals(Season.SUMMER)) {
            seasonIndex = 3;
        } else if (App.getCurrentGame().getDateTime().getSeasonOfYear().equals(Season.FALL)) {
            seasonIndex = 4;
        } else if (App.getCurrentGame().getDateTime().getSeasonOfYear().equals(Season.WINTER)) {
            seasonIndex = 5;
        }

        if (App.getCurrentGame().getWeather().getName().equals("Sunny")) {
            weatherIndex = 6;
        } else if (App.getCurrentGame().getWeather().getName().equals("Rainy")) {
            weatherIndex = 7;
        } else if (App.getCurrentGame().getWeather().getName().equals("Storm")) {
            weatherIndex = 8;
        } else if (App.getCurrentGame().getWeather().getName().equals("Snowy")) {
            weatherIndex = 9;
        }

        if (seasonIndex != -1 && weatherIndex != -1) {
            int dialogIndex = random.nextBoolean() ? seasonIndex : weatherIndex;
            System.out.print(type.getDialogs().get(dialogIndex));
        } else if (seasonIndex != -1) {
            System.out.print(type.getDialogs().get(seasonIndex));
        } else if (weatherIndex != -1) {
            System.out.print(type.getDialogs().get(weatherIndex));
        }

    }


    public NPCFriendship getFriendship() {
        for (NPCFriendship friendship : friendships) {
<<<<<<< Updated upstream
            if (friendship.getPlayer().equals(App.getCurrentGame().getCurrentPlayingPlayer())) {
                return friendship;
            }
        }
        friendships.add(new NPCFriendship(App.getCurrentGame().getCurrentPlayingPlayer(),this));
=======
            if (friendship.getPlayer().equals(App.getCurrentGame().getCurrentPlayer())) {
                return friendship;
            }
        }
        friendships.add(new NPCFriendship(App.getCurrentGame().getCurrentPlayer(),this));
>>>>>>> Stashed changes
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
        if (getFriendship().getGotGiftToday()){
            if (type.getFavorites().contains(good.getType())){
                getFriendship().setFriendshipPoints(200);
            } else {
                getFriendship().setFriendshipPoints(50);
            }
            getFriendship().setGotGiftToday();
        }

    }
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
