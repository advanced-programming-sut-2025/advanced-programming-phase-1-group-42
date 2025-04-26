package org.example.models.goods;

public abstract class Good {

    public abstract String getName();
    public abstract int getSellPrice();
    public abstract GoodType getType();
    protected int price;
    protected String name;

}
