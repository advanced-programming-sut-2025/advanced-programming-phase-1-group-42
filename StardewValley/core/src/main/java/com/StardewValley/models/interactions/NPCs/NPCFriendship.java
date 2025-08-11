package com.StardewValley.models.interactions.NPCs;

import com.StardewValley.client.AppClient;
import com.StardewValley.models.game_structure.Game;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.interactions.Player;

import java.util.ArrayList;

public class NPCFriendship {
    private String playerUsername;
    private NPCTypes npcTypes;
    private int friendshipPoints;
    private int friendshipLevel;
    private ArrayList<Good> gifts;
    private ArrayList<Integer> availableQuests = new ArrayList<>();
    private int firstMeetSeason;
    private boolean firstMeetToday = true;
    private boolean gotGiftToday = false;

    public NPCFriendship(String playerUsername , NPCTypes npcTypes) {
        friendshipPoints = 0;
        friendshipLevel = 0;
        gifts = new ArrayList<>();
        availableQuests.add(1);
        this.playerUsername = playerUsername;
        this.npcTypes = npcTypes;
        firstMeetSeason = 1;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public int getFriendshipPoints() {
        return friendshipPoints;
    }

    public void setFriendshipPoints(int friendshipPoints, Game game) {
        this.friendshipPoints += friendshipPoints;
        if (friendshipPoints >= 799) {
            this.friendshipPoints = 799;
        }
        friendshipLevel = (int) Math.floor((double) friendshipPoints /200);
        if (friendshipLevel == 1){
            getAvailableQuests().add(2);
        }
        if (game.getDateTime().getSeasonOfYearInt() == firstMeetSeason + 1){
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

    public NPCTypes getNpcTypes() {
        return npcTypes;
    }

    public void setFriendshipToday(){
        firstMeetToday = true;
        gotGiftToday = false;
    }



}
