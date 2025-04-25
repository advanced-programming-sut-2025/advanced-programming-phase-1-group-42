package org.example.models.goods;

public abstract class Good {

    public abstract String getName();
    public abstract int getSellPrice();
    public GoodLevel getGoodLevel() {
        return this.level;
    }
    public void setGoodLevel(GoodLevel goodLevel) {
        this.level = goodLevel;
    }
    protected int price;
    protected String name;
    protected GoodLevel level;


}
