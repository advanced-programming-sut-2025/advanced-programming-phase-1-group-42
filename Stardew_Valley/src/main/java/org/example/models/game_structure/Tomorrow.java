<<<<<<< Updated upstream
package org.example.models.game_structure;

import org.example.models.game_structure.weathers.Weather;

public class Tomorrow {
    private Weather weather;

    // Function for forecasting the weather of tomorrow
    public Weather weatherForecast() {
        //TODO
    }
}
=======
package org.example.models.game_structure;

import org.example.models.App;
import org.example.models.enums.WeatherType;
import org.example.models.game_structure.weathers.Weather;

public class Tomorrow {
    private Weather weather;

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

    public void setTomorrowWeather() {
        double probability = Math.random()*10;

        if (App.getCurrentGame().getDateTime().getSeasonOfYear().equals("Spring")){

            if(probability < 7){
                weather = WeatherType.Sunny.getWeather();
            } else if( probability < 9){
                weather = WeatherType.Rain.getWeather();
            } else {
                weather = WeatherType.Storm.getWeather();
            }
        } else if (App.getCurrentGame().getDateTime().getSeasonOfYear().equals("Summer")){
            if(probability < 9){
                weather = WeatherType.Sunny.getWeather();
            } else {
                weather = WeatherType.Rain.getWeather();
            }
        } else if (App.getCurrentGame().getDateTime().getSeasonOfYear().equals("Fall")){
            if (probability < 2){
                weather = WeatherType.Sunny.getWeather();
            } else if( probability < 6){
                weather = WeatherType.Rain.getWeather();
            } else if(probability < 9){
                weather = WeatherType.Storm.getWeather();
            } else {
                weather = WeatherType.Snow.getWeather();
            }
        } else if (App.getCurrentGame().getDateTime().getSeasonOfYear().equals("Winter")){
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
>>>>>>> Stashed changes
