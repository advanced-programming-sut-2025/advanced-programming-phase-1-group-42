package com.StardewValley.models.interactions.NPCs;

import com.StardewValley.client.AppClient;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.interactions.Player;

import java.util.ArrayList;

public class NPCFriendship {
    private Player player;
    private NPC npc;
    private int friendshipPoints;
    private int friendshipLevel;
    private ArrayList<Good> gifts;
    private ArrayList<Integer> availableQuests = new ArrayList<>();
    private int firstMeetSeason;
    private boolean firstMeetToday = true;
    private boolean gotGiftToday = false;

    public NPCFriendship(Player player , NPC npc) {
        friendshipPoints = 0;
        friendshipLevel = 0;
        gifts = new ArrayList<>();
        availableQuests.add(1);
        this.player = player;
        this.npc = npc;
        firstMeetSeason = AppClient.getCurrentGame().getDateTime().getSeasonOfYearInt();
    }

    public Player getPlayer() {
        return player;
    }

    public int getFriendshipPoints() {
        return friendshipPoints;
    }

    public void setFriendshipPoints(int friendshipPoints) {
        this.friendshipPoints += friendshipPoints;
        if (friendshipPoints >= 799) {
            this.friendshipPoints = 799;
        }
        friendshipLevel = (int) Math.floor((double) friendshipPoints /200);
        if (friendshipLevel == 1){
            getAvailableQuests().add(2);
        }
        if (AppClient.getCurrentGame().getDateTime().getSeasonOfYearInt() == firstMeetSeason + 1){
            getAvailableQuests().add(3);
        }
    }

    public int getFriendshipLevel() {
        return friendshipLevel;
    }

    public void setFriendshipLevel() {
        friendshipLevel++;
        if (friendshipLevel >= 3) {
            friendshipLevel = 3;
        }
        friendshipPoints += 200;
        if (friendshipPoints >= 799) {
            this.friendshipPoints = 799;
        }

        if (friendshipLevel == 1){
            getAvailableQuests().add(2);
        }

        if (AppClient.getCurrentGame().getDateTime().getSeasonOfYearInt() == firstMeetSeason + 1){
            getAvailableQuests().add(3);
        }

    }

    public ArrayList<Good> getGifts() {
        return gifts;
    }

    public ArrayList<Integer> getAvailableQuests() {
        return availableQuests;
    }
    public void setGotGiftToday() {
        gotGiftToday = true;
    }

    public boolean getGotGiftToday() {
        return gotGiftToday;
    }

    public void setFirstMeetToday() {
        firstMeetToday = false;
    }

    public boolean getFirstMeetToday() {
        return firstMeetToday;
    }

    public NPC getNpc() {
        return npc;
    }

    public void setFriendshipToday(){
        firstMeetToday = true;
        gotGiftToday = false;
    }



}
