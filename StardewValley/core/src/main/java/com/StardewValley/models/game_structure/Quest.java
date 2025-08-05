package com.StardewValley.models.game_structure;

import com.StardewValley.models.Pair;
import com.StardewValley.models.interactions.Player;

import java.util.ArrayList;

public class Quest {
    private QuestType questType;
    private int collectedNum = 0;
    private int dayLeft;
    private boolean isActive = false;
    private boolean isCompleted = false;
    private ArrayList<Pair<Player,Integer>> members;

    public Quest(QuestType questType) {
        this.questType = questType;
        dayLeft = questType.getTime();
        members = new ArrayList<>();
    }

    public QuestType getQuestType() {
        return questType;
    }

    public void questDailyUpdate() {
        if (isActive) {
            dayLeft--;
            if (dayLeft == 0) {
                isActive = false;
            }
        }
    }

    public int getDayLeft() {
        return dayLeft;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public boolean addMembers(Player player) {
        if (members.size() < questType.getTargetNum()) {
            members.add(new Pair<>(player,0));
            if (members.size() == questType.getCapacity()) {
                isActive = true;
            }
            return true;
        } else {
            return false;
        }

    }

    public int getCollectedNum() {
        return collectedNum;
    }

    public void setCollectedNum(int collectedNum) {
        this.collectedNum += collectedNum;
        if (collectedNum == questType.getTargetNum()) {
            isActive = false;
            isCompleted = true;
        }
    }

    public ArrayList<Pair<Player,Integer>> getMembers() {
        return members;
    }


}
