package org.example.models.game_structure;

public class Buff {
    BuffType type;
    private int remainEffectTime;
    private int effect;

    public Buff(BuffType type, int remainEffectTime, int effectAmount) {
        this.type = type;
        this.remainEffectTime = remainEffectTime;
        this.effect = effectAmount;
    }

    public BuffType getType() {
        return type;
    }
    public int getRemainEffectTime() {
        return remainEffectTime;
    }

    public void setRemainEffectTime() {
        this.remainEffectTime -= 1;
    }

    public int getEffect() {
        return effect;
    }





}
