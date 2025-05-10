package org.example.models.game_structure.weathers;

public class Rain extends Weather {
    double weatherEffectingEnergy = 1.5;


    public void cheatThunder(int x, int y) {

    }
     public void thunder() {

     }
    public String getName(){
        return "Rain";
    }
    public String getCurrentWeather() { return "Rain"; }
}
