package org.example.models.game_structure;

import org.example.models.goods.Good;
import org.example.models.interactions.Player;

import java.util.ArrayList;

public class Trade {

    private int id;
    private TradeType type;
    private Player sender;
    private Player receiver;
    private String item;
    private int amount;
    private int price; // optional
    private String targetItem; // for barter trades
    private int targetAmount;  // for barter trades
    private boolean isAccepted;
    private boolean isShown;
    private ArrayList<Good> list;

    public Trade(ArrayList<Good> list) {
        this.list = list;
    }
    public ArrayList<Good> getList() {
        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TradeType getType() {
        return type;
    }

    public void setType(TradeType type) {
        this.type = type;
    }

    public Player getSender() {
        return sender;
    }

    public void setSender(Player sender) {
        this.sender = sender;
    }

    public Player getReceiver() {
        return receiver;
    }

    public void setReceiver(Player receiver) {
        this.receiver = receiver;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTradePrice() {
        return price;
    }

    public void setTradePrice(int price) {
        this.price = price;
    }

    public String getTargetItem() {
        return targetItem;
    }

    public void setTargetItem(String targetItem) {
        this.targetItem = targetItem;
    }

    public int getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(int targetAmount) {
        this.targetAmount = targetAmount;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isShown() {
        return isShown;
    }

    public void setShown(boolean shown) {
        isShown = shown;
    }
}
