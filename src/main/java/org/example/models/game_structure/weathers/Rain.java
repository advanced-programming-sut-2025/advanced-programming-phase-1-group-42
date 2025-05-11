package org.example.models.game_structure.weathers;

public class Rain extends Weather {
    double weatherEffectingEnergy;
    double fishChance ;

    public Rain(double effectingEnergy, double fishChance) {
        this.weatherEffectingEnergy = effectingEnergy;
        this.fishChance = fishChance;
    }
    public double getWeatherEffectingEnergy() {
        return weatherEffectingEnergy;
    }
    public double getFishChance() {
        return fishChance;
    }


    public void thunder() {
        int[][] x = new int[3][4];
        int[][] y = new int[3][4];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                if(i == 0) {
                    x[i][j] = (int) (Math.random() * 70);
                    y[i][j] = (int) (Math.random() * 50);
                } else if (i == 1) {
                    x[i][j] = (int) (Math.random() * 70) + 70;
                    y[i][j] = (int) (Math.random() * 50);
                } else if (i == 2) {
                    x[i][j] = (int) (Math.random() * 70);
                    y[i][j] = (int) (Math.random() * 50) + 110;
                } else if (i == 3) {
                    x[i][j] = (int) (Math.random() * 70) + 70;
                    y[i][j] = (int) (Math.random() * 50) + 110;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                Weather.thunder(x[i][j] , y[i][j]);
            }
        }
    }
    public String getName(){
        return "Rain";
    }
    public String getCurrentWeather() { return "Rain"; }
}
