<<<<<<< Updated upstream
package org.example.models.game_structure.weathers;

public class Snow extends Weather {
}
=======
package org.example.models.game_structure.weathers;

public class Snow extends Weather {
    double weatherEffectingEnergy = 2;

    public void CheatThunder() {
        // TODO
    }
    public String getName() {
        return "Snow";
    }

    public String getCurrentWeather() { return "Snow"; }
}
>>>>>>> Stashed changes
