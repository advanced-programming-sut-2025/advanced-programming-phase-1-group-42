package org.example;

import org.example.models.App;
import org.example.views.AppView;

public class Main {
    public static void main(String[] args) {
        (new AppView()).run();
        App app = new App();

    }
}