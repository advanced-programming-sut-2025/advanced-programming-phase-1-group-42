package com.StardewValley.models.game_structure;

import com.StardewValley.models.enums.Season;
import com.StardewValley.models.enums.WeatherType;
import com.StardewValley.models.game_structure.weathers.Sunny;
import com.StardewValley.models.game_structure.weathers.Weather;

import java.util.Random;

public class Tomorrow {
    private Weather weather = new Sunny(1,1);

    // Function for forecasting the weather of tomorrow
    public Weather weatherForecast() {
        return this.weather;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public void setTomorrowWeather(Game game) {
        double probability = (new Random()).nextInt(11);

        if (game.getDateTime().getSeasonOfYear().equals(Season.SPRING)) {

            if(probability < 7){
                weather = WeatherType.Sunny.getWeather();
            } else if( probability < 9){
                weather = WeatherType.Rain.getWeather();
            } else {
                weather = WeatherType.Storm.getWeather();
            }
        } else if (game.getDateTime().getSeasonOfYear().equals(Season.SUMMER)){
            if(probability < 9){
                weather = WeatherType.Sunny.getWeather();
            } else {
                weather = WeatherType.Rain.getWeather();
            }
        } else if (game.getDateTime().getSeasonOfYear().equals(Season.FALL)){
            if (probability < 2){
                weather = WeatherType.Sunny.getWeather();
            } else if( probability < 6){
                weather = WeatherType.Rain.getWeather();
            } else if(probability < 9){
                weather = WeatherType.Storm.getWeather();
            } else {
                weather = WeatherType.Snow.getWeather();
            }
        } else if (game.getDateTime().getSeasonOfYear().equals(Season.WINTER)){
            if( probability < 2){
                weather = WeatherType.Rain.getWeather();
            } else if(probability < 5){
                weather = WeatherType.Storm.getWeather();
            } else {
                weather = WeatherType.Snow.getWeather();
            }
        }
    }


}
