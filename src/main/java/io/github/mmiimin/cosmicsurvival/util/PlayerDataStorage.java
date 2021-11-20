package io.github.mmiimin.cosmicsurvival.util;

import io.github.mmiimin.cosmicsurvival.database.Database;
import io.github.mmiimin.cosmicsurvival.plugin.CosmicSurvival;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerDataStorage {

    public HashMap<String, Integer> map = new HashMap<>();

    private Database db;
    private CosmicSurvival cs;

    public void saveAll(Player player) {
        cs.getRDatabase();
        String p=player.getName();
        db.setTokens(player,
                map.get(p+"combatExp"),
                map.get(p+"combatLevel"),
                map.get(p+"miningExp"),
                map.get(p+"miningLevel"),
                map.get(p+"foragingExp"),
                map.get(p+"foragingLevel"),
                map.get(p+"farmingExp"),
                map.get(p+"farmingLevel"));

    }

    public void loadFirstTime(Player player) {
        String p=player.getName();
        map.put(p+"combatExp",0);
        map.put(p+"combatLevel",0);
        map.put(p+"miningExp",0);
        map.put(p+"miningLevel",0);
        map.put(p+"foragingExp",0);
        map.put(p+"foragingLevel",0);
        map.put(p+"fishingExp",0);
        map.put(p+"fishingLevel",0);
        map.put(p+"farmingExp",0);
        map.put(p+"farmingLevel",0);
        map.put(p+"achievementExp",0);
        map.put(p+"achievementLevel",0);
        map.put(p+"statsSTR",0);
        map.put(p+"statsDEF",0);
        map.put(p+"statsDEX",0);
        map.put(p+"statsINT",0);
        map.put(p+"statsPoint",0);
    }

    public void loadAll(Player player) {
        cs.getRDatabase();
        String p=player.getName();
        map.put(p+"combatExp",db.getTokens(String.valueOf(player.getUniqueId()),"combatExp"));
        map.put(p+"combatLevel",db.getTokens(String.valueOf(player.getUniqueId()),"combatLevel"));
        map.put(p+"miningExp",db.getTokens(String.valueOf(player.getUniqueId()),"miningExp"));
        map.put(p+"miningLevel",db.getTokens(String.valueOf(player.getUniqueId()),"miningLevel"));
        map.put(p+"foragingExp",db.getTokens(String.valueOf(player.getUniqueId()),"foragingExp"));
        map.put(p+"foragingLevel",db.getTokens(String.valueOf(player.getUniqueId()),"foragingLevel"));
        map.put(p+"fishingExp",db.getTokens(String.valueOf(player.getUniqueId()),"fishingExp"));
        map.put(p+"fishingLevel",db.getTokens(String.valueOf(player.getUniqueId()),"fishingLevel"));
        map.put(p+"farmingExp",db.getTokens(String.valueOf(player.getUniqueId()),"farmingExp"));
        map.put(p+"farmingLevel",db.getTokens(String.valueOf(player.getUniqueId()),"farmingLevel"));
        map.put(p+"achievementExp",db.getTokens(String.valueOf(player.getUniqueId()),"achievementExp"));
        map.put(p+"achievementLevel",db.getTokens(String.valueOf(player.getUniqueId()),"achievementLevel"));
        map.put(p+"statsSTR",db.getTokens(String.valueOf(player.getUniqueId()),"statsSTR"));
        map.put(p+"statsDEF",db.getTokens(String.valueOf(player.getUniqueId()),"statsDEF"));
        map.put(p+"statsDEX",db.getTokens(String.valueOf(player.getUniqueId()),"statsDEX"));
        map.put(p+"statsINT",db.getTokens(String.valueOf(player.getUniqueId()),"statsINT"));
        map.put(p+"statsPoint",db.getTokens(String.valueOf(player.getUniqueId()),"statsPoint"));
    }


}
