package io.github.mmiimin.cosmicsurvival.util;

import org.bukkit.entity.Player;
import io.github.mmiimin.cosmicsurvival.database.Database;
import java.util.HashMap;

public class PlayerDataStorage {

    private Database db;

    public HashMap<String, Integer> map = new HashMap<>();

    public void addValue(String Data,int value) {
        map.replace(Data, map.get(Data)+value);
    }
    public void setValue(String Data,int value) {
        map.replace(Data, value);
    }
    public void subtractValue(String Data,int value) {
        map.replace(Data, map.get(Data)-value);
    }
    public int checkValue(String Data) {
        return map.get(Data);
    }

    public void saveAll(Player player) {
        String p=player.getName();
        map.replace(p+"combatExp",db.getTokens(String.valueOf(player.getUniqueId()),"combatExp"));
        map.replace(p+"combatLevel",db.getTokens(String.valueOf(player.getUniqueId()),"combatLevel"));
        map.replace(p+"miningExp",db.getTokens(String.valueOf(player.getUniqueId()),"miningExp"));
        map.replace(p+"miningLevel",db.getTokens(String.valueOf(player.getUniqueId()),"miningLevel"));
        map.replace(p+"foragingExp",db.getTokens(String.valueOf(player.getUniqueId()),"foragingExp"));
        map.replace(p+"foragingLevel",db.getTokens(String.valueOf(player.getUniqueId()),"foragingLevel"));
        map.replace(p+"fishingExp",db.getTokens(String.valueOf(player.getUniqueId()),"fishingExp"));
        map.replace(p+"fishingLevel",db.getTokens(String.valueOf(player.getUniqueId()),"fishingLevel"));
        map.replace(p+"farmingExp",db.getTokens(String.valueOf(player.getUniqueId()),"farmingExp"));
        map.replace(p+"farmingLevel",db.getTokens(String.valueOf(player.getUniqueId()),"farmingLevel"));
        map.replace(p+"achievementExp",db.getTokens(String.valueOf(player.getUniqueId()),"achievementExp"));
        map.replace(p+"achievementLevel",db.getTokens(String.valueOf(player.getUniqueId()),"achievementLevel"));
        map.replace(p+"statsSTR",db.getTokens(String.valueOf(player.getUniqueId()),"statsSTR"));
        map.replace(p+"statsDEF",db.getTokens(String.valueOf(player.getUniqueId()),"statsDEF"));
        map.replace(p+"statsDEX",db.getTokens(String.valueOf(player.getUniqueId()),"statsDEX"));
        map.replace(p+"statsINT",db.getTokens(String.valueOf(player.getUniqueId()),"statsINT"));
        map.replace(p+"statsPoint",db.getTokens(String.valueOf(player.getUniqueId()),"statsPoint"));

    }

    public void loadAll(Player player) {
        String p=player.getName();
    }
}
