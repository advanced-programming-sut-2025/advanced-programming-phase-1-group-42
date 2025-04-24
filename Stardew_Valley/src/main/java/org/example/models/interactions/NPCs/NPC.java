package org.example.models.interactions.NPCs;

import org.example.models.App;
import org.example.models.enums.Season;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;

import java.util.ArrayList;
import java.util.Map;

public class NPC {
    private final NPCTypes type;
    private final ArrayList<NPCFriendship> friendships = new ArrayList<>();

    public NPC(NPCTypes type) {
        this.type = type;
    }

    public boolean isBirthdayToday() {
        if (type.getBirthday().isEmpty()) {
            return false;
        }

        Map.Entry<Season, Integer> entry = type.getBirthday().entrySet().iterator().next();
        return App.getCurrentGame().getDateTime().getSeasonOfYear().equals(entry.getKey())
                && App.getCurrentGame().getDateTime().getDayOfSeason() == entry.getValue();
    }

    public void npcDialogs() {
        getFriendship().setFriendshipPoints(20);
        if (isBirthdayToday()) {
            System.out.println(type.getDialogs().getFirst());
        }
    }

    public NPCFriendship getFriendship() {
        for (NPCFriendship friendship : friendships) {
            if (friendship.getPlayer().equals(App.getCurrentGame().getCurrentPlayingPlayer())) {
                return friendship;
            }
        }
        return null;
    }

    public void finishQuest(Good good) {
        if (type.getName().equals("Sebastian")){
            NPCRewardsFunctions.sebastianRewards(good,getFriendship());
        } else if (type.getName().equals("Abigail")){
            NPCRewardsFunctions.abigailRewards(good,getFriendship());
        } else if (type.getName().equals("Leah")){
            NPCRewardsFunctions.leahRewards(good,getFriendship());
        } else if (type.getName().equals("Harvey")){
            NPCRewardsFunctions.harveyRewards(good,getFriendship());
        } else if (type.getName().equals("Robin")){
            NPCRewardsFunctions.robinRewards(good,getFriendship());
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
}
