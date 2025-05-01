package org.example.models.interactions.game_buildings;

public class Quadruple<A , B , C , D>  {
    private final A a;
    private final B b;
    private final C c;
    private final D d;

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
