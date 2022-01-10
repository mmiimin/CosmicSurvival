package io.github.mmiimin.cosmicsurvival.util;

import io.github.mmiimin.cosmicsurvival.plugin.CosmicSurvival;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerDataStorage {

    public static HashMap<String, Integer> map = new HashMap<>();
    public static HashMap<String, Integer> accessory = new HashMap<>();

    public void saveAccData(Player player){
        for (int i = 0; i < 100; i++) {
            CosmicSurvival.plugin.getConfig().set("Filter."+player.getUniqueId() +"."+i,PlayerDataStorage.accessory.get(player.getName()+i));
        }
        CosmicSurvival.plugin.saveConfig();
    }
    public void loadAccData(Player player) {
        for (int i = 0; i < 100; i++) {
            try {
                accessory.put(player.getName()+i, CosmicSurvival.plugin.getConfig().getInt("Filter." + player.getUniqueId() + "." + i));
            }
            catch (NullPointerException e) {
                accessory.put(player.getName()+i, 0);
            }
        }
    }
}
