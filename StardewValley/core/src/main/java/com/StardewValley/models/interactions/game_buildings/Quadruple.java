package com.StardewValley.models.interactions.game_buildings;

public class Quadruple<A, B, C, D> {
    public final A a;
    public final B b;
    public final C c;
    public final D d;
    public Quadruple(A a, B b, C c, D d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    public A getFirst() {
        return a;
    }
    public B getSecond() {
        return b;
    }
    public C getThird() {
        return c;
    }
    public D getFourth() {
        return d;
    }
}
