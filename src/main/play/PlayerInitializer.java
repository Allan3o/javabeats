package src.main.play;

import javafx.application.Platform;

public class PlayerInitializer {

    private static boolean initialized = false;

    public static synchronized void init() {
        if (initialized) return;

        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
           
        }

        initialized = true;
    }
}
