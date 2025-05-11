package org.example.models.game_structure;
import java.util.concurrent.atomic.AtomicInteger;
import org.example.models.interactions.*;
import org.example.models.Pair;
import org.example.models.game_structure.*;


public class Trade {
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    private final int id;
    private final TradeType type;
    private final Player sender;
    private final Player receiver;
    private final String item;
    private final int amount;
    private final Integer price;
    private final String targetItem;
    private final Integer targetAmount;
    private boolean Shown = false;
    private boolean accepted = false;

    public Trade(TradeType type, Player sender, Player receiver, String item, int amount,
                 Integer price, String targetItem, Integer targetAmount) {
        this.id = idGenerator.getAndIncrement();
        this.type = type;
        this.sender = sender;
        this.receiver = receiver;
        this.item = item;
        this.amount = amount;
        this.price = price;
        this.targetItem = targetItem;
        this.targetAmount = targetAmount;
    }

    public int getId() { return id; }
    public Player getReceiver() { return receiver; }
    public Player getSender() { return sender; }
    public TradeType getType() {
        return type;
    }

    public String getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public Integer getTradePrice() {
        return price;
    }

    public String getTargetItem() {
        return targetItem;
    }

    public Integer getTargetAmount() {
        return targetAmount;
    }

    public boolean isAccepted() { return accepted; }
    public void setAccepted(boolean accepted) { this.accepted = accepted; }
    public boolean isShown() { return Shown; }
    public void setShown(boolean Shown) { this.Shown = Shown; }
    @Override
    public String toString() {
        return "Trade{id=" + id + ", type=" + type + ", sender=" + sender.getUser().getUsername() +
                ", item=" + item + ", amount=" + amount + ", price=" + price +
                ", targetItem=" + targetItem + ", targetAmount=" + targetAmount + "}";
    }
}