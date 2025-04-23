<<<<<<< Updated upstream
package org.example.models.game_structure.weathers;

public class Sunny extends Weather {
}
=======
package org.example.models.game_structure.weathers;

public class Sunny extends Weather {
    double weatherEffectingEnergy = 1;

    public void CheatThunder() {
        // TODO

    }

    public String getName() {
        return "Sunny";
    }

    public String getCurrentWeather() { return "Sunny"; }
}
>>>>>>> Stashed changes
