package io.github.mmiimin.cosmicsurvival.database;

import io.github.mmiimin.cosmicsurvival.plugin.CosmicSurvival;

import java.util.logging.Level;

public class Error {
    public static void execute(CosmicSurvival plugin, Exception ex){
        plugin.getLogger().log(Level.SEVERE, "Couldn't execute MySQL statement: ", ex);
    }
    public static void close(CosmicSurvival plugin, Exception ex){
        plugin.getLogger().log(Level.SEVERE, "Failed to close MySQL connection: ", ex);
    }
}
