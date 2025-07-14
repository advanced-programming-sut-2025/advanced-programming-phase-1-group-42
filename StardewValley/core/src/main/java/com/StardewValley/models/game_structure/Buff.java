package com.StardewValley.models.game_structure;

public class Buff {
    BuffType type;
    private int EffectTime;
    private int remainEffectTime;
    private int effect;

    public Buff(BuffType type, int remainEffectTime, int effectAmount) {
        this.type = type;
        this.EffectTime = remainEffectTime;
        this.remainEffectTime = remainEffectTime;
        this.effect = effectAmount;
    }

    public BuffType getType() {
        return type;
    }

    public int getEffectTime() {
        return EffectTime;
    }

    public int getEffect() { return effect;}

    public void setRemainEffectTime() {
        this.remainEffectTime -= 1;
    }
    public int getRemainEffectTime() {return remainEffectTime;}

}
