package example;

import org.example.models.App;
import org.example.views.AppView;

public class Main {
    public static void main(String[] args) {
        // For Starting the game
        App.startGame();
        (new AppView()).run();

    }
}
