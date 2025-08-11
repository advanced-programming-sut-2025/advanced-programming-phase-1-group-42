package com.StardewValley.models.game_structure;

import com.StardewValley.models.App;
import com.StardewValley.models.Pair;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.interactions.Player;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {
    private ArrayList<ArrayList<Good>> list;
    private int size = 12;


    public ArrayList<ArrayList<Good>> getList() {
        return list;
    }

    public void setList(ArrayList<ArrayList<Good>> list) {
        this.list = list;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Inventory() {
        list = new ArrayList<>();
        for (int i = 0; i < 12; i++)
            list.add(new ArrayList<>());
    }

    public static boolean decreaseGoods(ArrayList<Good> goods, int number) {
        if (goods.size() < number)
            return false;

        for (int i = 0; i < number; i++) {
            goods.removeLast();
        }
        return true;
    }

    public ArrayList<Good> isInInventory(Good good) {
        for (int i = 0; i < size; i++) {
            if (!list.get(i).isEmpty() && list.get(i).getFirst().getName().equals(good.getName())) {
                return list.get(i);
            }
        }
        return null;
    }

    public ArrayList<Good> isInInventory(String goodName) {
        for (int i = 0; i < size; i++) {
            if (!list.get(i).isEmpty() && list.get(i).getFirst().getName().equals(goodName)) {
                return list.get(i);
            }
        }
        return null;
    }

    public boolean addGoodByObject(Good good) {
        for (ArrayList<Good> goods : list) {
            if (!goods.isEmpty() && goods.get(0).getName().equals(good.getName())) {
                goods.add(good);
                if (App.getCurrentGame() != null) {

                    for (Quest quest : App.getCurrentGame().getCurrentPlayer().getPlayerQuests()) {
                        if (quest.getQuestType().getProductType().getName().equals(good.getType().getName())) {
                            quest.setCollectedNum(1);
                            for (Pair<Player, Integer> pair : quest.getMembers()) {
                                if (pair.first().equals(App.getCurrentGame().getCurrentPlayer())) {
                                    pair.setSecond(pair.second() + 1);
                                    return true;
                                }
                            }

                        }
                    }
                }
                return true;
            }
        }

        for (ArrayList<Good> goods : list) {
            if (goods.isEmpty()) {
                goods.add(good);
                if (App.getCurrentGame() != null) {
                    for (Quest quest : App.getCurrentGame().getCurrentPlayer().getPlayerQuests()) {
                        if (quest.getQuestType().getProductType().getName().equals(good.getType().getName())) {
                            quest.setCollectedNum(1);
                            for (Pair<Player, Integer> pair : quest.getMembers()) {
                                if (pair.first().equals(App.getCurrentGame().getCurrentPlayer())) {
                                    pair.setSecond(pair.second() + 1);
                                    return true;
                                }
                            }

                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public ArrayList<Good> isInInventory(GoodType goodType) {
        for (int i = 0; i < size; i++) {
            if (!list.get(i).isEmpty() && list.get(i).getFirst().getType() == goodType) {
                return list.get(i);
            }
        }
        return null;
    }

    public boolean isInInventoryBoolean(GoodType goodName) {

        for (ArrayList<Good> goods : list) {
            if (!goods.isEmpty() && goods.getFirst().getType().equals(goodName)) {
                return true;
            }
        }
        return false;
    }


    public boolean addGood(ArrayList<Good> addingGood) {
        if (addingGood == null || addingGood.isEmpty()) {
            return false; // یا پرتاب استثنا: throw new IllegalArgumentException("addingGood cannot be null or empty");
        }

        Good firstAddingGood = addingGood.getFirst();
        if (firstAddingGood == null) {
            return false; // یا پرتاب استثنا
        }

        for (ArrayList<Good> goods : list) {
            if (!goods.isEmpty()) {
                Good firstGood = goods.getFirst();
                if (firstGood != null && firstGood.getName().equals(firstAddingGood.getName())) {
                    goods.addAll(addingGood);
                    if (App.getCurrentGame() != null) {
                        for (Quest quest : App.getCurrentGame().getCurrentPlayer().getPlayerQuests()) {
                            if (quest.getQuestType().getProductType().getName().equals(firstAddingGood.getName())) {
                                quest.setCollectedNum(list.size());
                                for (Pair<Player, Integer> pair : quest.getMembers()) {
                                    if (pair.first().equals(App.getCurrentGame().getCurrentPlayer())) {
                                        pair.setSecond(pair.second() + list.size());
                                        return true;
                                    }
                                }

                            }
                        }
                    }
                    return true;
                }
            }
        }

        for (ArrayList<Good> goods : list) {
            if (goods.isEmpty()) {
                goods.addAll(addingGood);
                if (App.getCurrentGame() != null) {

                    for (Quest quest : App.getCurrentGame().getCurrentPlayer().getPlayerQuests()) {
                        if (quest.getQuestType().getProductType().getName().equals(firstAddingGood.getName())) {
                            quest.setCollectedNum(addingGood.size());
                            for (Pair<Player, Integer> pair : quest.getMembers()) {
                                if (pair.first().equals(App.getCurrentGame().getCurrentPlayer())) {
                                    pair.setSecond(pair.second() + addingGood.size());
                                    return true;
                                }
                            }

                        }
                    }
                }
                return true;
            }
        }

        return false;
    }

    public boolean isFull() {
        for (ArrayList<Good> goods : list) {
            if (goods.isEmpty())
                return false;
        }
        return true;
    }

    public boolean addGood(Good good, int count) {
        for (ArrayList<Good> goods : list) {
            if (!goods.isEmpty()) {
                for (Good g : goods) {
                    if (g.getType() == good.getType()) {
                        for (int i = 0; i < count; i++) {
                            goods.add(Good.newGood(good.getType()));
                            if (App.getCurrentGame() != null) {

                                for (Quest quest : App.getCurrentGame().getCurrentPlayer().getPlayerQuests()) {
                                    if (quest.getQuestType().getProductType().getName().equals(good.getType().getName())) {
                                        quest.setCollectedNum(count);
                                        for (Pair<Player, Integer> pair : quest.getMembers()) {
                                            if (pair.first().equals(App.getCurrentGame().getCurrentPlayer())) {
                                                pair.setSecond(pair.second() + count);
                                                return true;
                                            }
                                        }
                                    }

                                }
                            }
                        }
                        return true;
                    }
                }
            }
        }

        for (ArrayList<Good> goods : list) {
            if (goods.isEmpty()) {
                for (int i = 0; i < count; i++) {
                    goods.add(Good.newGood(good.getType()));
                    if (App.getCurrentGame() != null) {

                        for (Quest quest : App.getCurrentGame().getCurrentPlayer().getPlayerQuests()) {
                            if (quest.getQuestType().getProductType().getName().equals(good.getName())) {
                                quest.setCollectedNum(count);
                                for (Pair<Player, Integer> pair : quest.getMembers()) {
                                    if (pair.first().equals(App.getCurrentGame().getCurrentPlayer())) {
                                        pair.setSecond(pair.second() + count);
                                        return true;
                                    }
                                }
                            }

                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public int howManyInInventory(Good good) {
        int count = 0;
        for (ArrayList<Good> goods : list) {
            if (!goods.isEmpty()) {
                for (Good g : goods) {
                    if (g.getType() == good.getType()) {
                        count++;
                    }
                }
                if (count > 0) {
                    return count;
                }
            }
        }
        return 0;
    }

    public int howManyInInventoryByType(GoodType type) {
        int count = 0;
        for (ArrayList<Good> goods : list) {
            if (!goods.isEmpty()) {
                for (Good g : goods) {
                    if (g.getType().equals(type)) {
                        count++;
                    }
                }
                if (count > 0) {
                    return count;
                }
            }
        }
        return 0;
    }

    public void removeItemsFromInventory(GoodType type, int count) {
        if (howManyInInventoryByType(type) < count) {
            return;
        }

        int remainingToRemove = count;

        for (ArrayList<Good> goods : list) {
            if (remainingToRemove <= 0) {
                break;
            }

            Iterator<Good> iterator = goods.iterator();
            while (iterator.hasNext() && remainingToRemove > 0) {
                Good good = iterator.next();
                if (good.getType().equals(type)) {
                    iterator.remove();
                    remainingToRemove--;
                }
            }
        }
    }

    public void increaseCapacity() {
        if (list.size() == 36)
            return;

        for (int i = 0; i < 12; i++)
            list.add(new ArrayList<>());
        size += 12;
    }

    public int getFirstElementSize() {
        for (ArrayList<Good> goods : list) {
            if (!goods.isEmpty())
                return goods.size();
        }
        return 0;
    }

}
