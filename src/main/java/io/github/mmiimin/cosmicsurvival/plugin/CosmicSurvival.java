package io.github.mmiimin.cosmicsurvival.plugin;

import io.github.mmiimin.cosmicsurvival.database.Database;
import io.github.mmiimin.cosmicsurvival.database.SQLite;
import io.github.mmiimin.cosmicsurvival.menu.MainProfile;
import io.github.mmiimin.cosmicsurvival.menu.MenuClick;
import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage;
import io.github.mmiimin.cosmicsurvival.util.WandItem;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class CosmicSurvival extends JavaPlugin implements CommandExecutor, Listener {

    private Database db;
    MainProfile menu = new MainProfile();
    PlayerDataStorage pds = new PlayerDataStorage();
    WandItem wandItem = new WandItem();
    public static CosmicSurvival plugin;

    @Override
    public void onEnable(){
        plugin = this;
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getServer().getPluginManager().registerEvents(new MenuClick(), this);
        getServer().getPluginManager().registerEvents(this, this);
        this.db = new SQLite(this);
        this.db.load();
        Objects.requireNonNull(this.getCommand("profile")).setExecutor(this);
        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            loadAll(player);
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for(Player player : Bukkit.getServer().getOnlinePlayers()) {
                saveAll(player);
            }
        }, 0, 20 * 60);
    }

    @Override
    public void onDisable(){
        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            saveAll(player);
        }

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            menu.openMainProfile(player);
            saveAll(player);
        }
        else{
            sender.sendMessage("Cannot open GUI menu");
        }
        return true;
    }

    public Database getRDatabase() {
        return this.db;
    }

    @EventHandler
    public void onEntityExplode(final EntityExplodeEvent event) {
        if (event.getEntityType() == EntityType.CREEPER) {
            event.setCancelled(true);
            Objects.requireNonNull(Bukkit.getWorld("world")).playSound(event.getEntity().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
        }
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage("§a⏻ "+event.getJoinMessage());
        loadFirstTime(event.getPlayer());
        loadAll(event.getPlayer());
        event.getPlayer().setCooldown(wandItem.getWandMaterial(PlayerDataStorage.map.get(event.getPlayer().getName() + "wand1")),20 * 60);
        event.getPlayer().setCooldown(wandItem.getWandMaterial(PlayerDataStorage.map.get(event.getPlayer().getName() + "wand2")),20 * 60);
        event.getPlayer().setCooldown(wandItem.getWandMaterial(PlayerDataStorage.map.get(event.getPlayer().getName() + "wand3")),20 * 60);
    }

    @EventHandler
    private void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage("§c⏻ "+event.getQuitMessage());
        saveAll(event.getPlayer());
    }

    public void saveAll(Player player) {
        pds.saveAccData(player);
        String p=player.getName();
        this.getConfig().set("Leaderboard."+player.getUniqueId() +".co",PlayerDataStorage.map.get(p+"combatLevel")*10000000+PlayerDataStorage.map.get(p+"combatExp"));
        this.getConfig().set("Leaderboard."+player.getUniqueId() +".mi",PlayerDataStorage.map.get(p+"miningLevel")*10000000+PlayerDataStorage.map.get(p+"miningExp"));
        this.getConfig().set("Leaderboard."+player.getUniqueId() +".fo",PlayerDataStorage.map.get(p+"foragingLevel")*10000000+PlayerDataStorage.map.get(p+"foragingExp"));
        this.getConfig().set("Leaderboard."+player.getUniqueId() +".fi",PlayerDataStorage.map.get(p+"fishingLevel")*10000000+PlayerDataStorage.map.get(p+"fishingExp"));
        this.getConfig().set("Leaderboard."+player.getUniqueId() +".fa",PlayerDataStorage.map.get(p+"farmingLevel")*10000000+PlayerDataStorage.map.get(p+"farmingExp"));
        this.saveConfig();
        db.setTokens(player,
                PlayerDataStorage.map.get(p+"combatExp"),
                PlayerDataStorage.map.get(p+"combatLevel"),
                PlayerDataStorage.map.get(p+"miningExp"),
                PlayerDataStorage.map.get(p+"miningLevel"),
                PlayerDataStorage.map.get(p+"foragingExp"),
                PlayerDataStorage.map.get(p+"foragingLevel"),
                PlayerDataStorage.map.get(p+"farmingExp"),
                PlayerDataStorage.map.get(p+"farmingLevel"),
                PlayerDataStorage.map.get(p+"fishingExp"),
                PlayerDataStorage.map.get(p+"fishingLevel"),
                PlayerDataStorage.map.get(p+"craftingExp"),
                PlayerDataStorage.map.get(p+"craftingLevel"),
                PlayerDataStorage.map.get(p+"achievementExp"),
                PlayerDataStorage.map.get(p+"achievementLevel"),
                PlayerDataStorage.map.get(p+"accessory1"),
                PlayerDataStorage.map.get(p+"wand1"),
                PlayerDataStorage.map.get(p+"accessory2"),
                PlayerDataStorage.map.get(p+"wand2"),
                PlayerDataStorage.map.get(p+"accessory3"),
                PlayerDataStorage.map.get(p+"wand3"),
                PlayerDataStorage.map.get(p+"statResetLevel"),
                PlayerDataStorage.map.get(p+"accessorySetTime"),
                0,
                PlayerDataStorage.map.get(p+"settingAT")*100000+PlayerDataStorage.map.get(p+"settingHE")*1000+PlayerDataStorage.map.get(p+"settingLS")*10+PlayerDataStorage.map.get(p+"settingDI"),
                0,
                0,
                0,
                PlayerDataStorage.map.get(p+"statsSTR"),
                PlayerDataStorage.map.get(p+"statsDEF"),
                PlayerDataStorage.map.get(p+"statsDEX"),
                PlayerDataStorage.map.get(p+"statsSRV"),
                PlayerDataStorage.map.get(p+"statsPoint"));

    }

    public void loadAll(Player player) {
        String p=player.getName();
        pds.loadAccData(player);
        PlayerDataStorage.map.put(p+"statResetLevel",db.getTokens(String.valueOf(player.getUniqueId()),"runeSlot1"));
        PlayerDataStorage.map.put(p+"accessorySetTime",db.getTokens(String.valueOf(player.getUniqueId()),"runeSlot2"));
        PlayerDataStorage.map.put(p+"combatExp",db.getTokens(String.valueOf(player.getUniqueId()),"combatExp"));
        PlayerDataStorage.map.put(p+"combatLevel",db.getTokens(String.valueOf(player.getUniqueId()),"combatLevel"));
        PlayerDataStorage.map.put(p+"miningExp",db.getTokens(String.valueOf(player.getUniqueId()),"miningExp"));
        PlayerDataStorage.map.put(p+"miningLevel",db.getTokens(String.valueOf(player.getUniqueId()),"miningLevel"));
        PlayerDataStorage.map.put(p+"foragingExp",db.getTokens(String.valueOf(player.getUniqueId()),"foragingExp"));
        PlayerDataStorage.map.put(p+"foragingLevel",db.getTokens(String.valueOf(player.getUniqueId()),"foragingLevel"));
        PlayerDataStorage.map.put(p+"fishingExp",db.getTokens(String.valueOf(player.getUniqueId()),"fishingExp"));
        PlayerDataStorage.map.put(p+"fishingLevel",db.getTokens(String.valueOf(player.getUniqueId()),"fishingLevel"));
        PlayerDataStorage.map.put(p+"farmingExp",db.getTokens(String.valueOf(player.getUniqueId()),"farmingExp"));
        PlayerDataStorage.map.put(p+"farmingLevel",db.getTokens(String.valueOf(player.getUniqueId()),"farmingLevel"));
        PlayerDataStorage.map.put(p+"achievementExp",db.getTokens(String.valueOf(player.getUniqueId()),"achievementExp"));
        PlayerDataStorage.map.put(p+"achievementLevel",db.getTokens(String.valueOf(player.getUniqueId()),"achievementLevel"));
        PlayerDataStorage.map.put(p+"wand1",db.getTokens(String.valueOf(player.getUniqueId()),"accessorySlot11"));
        PlayerDataStorage.map.put(p+"accessory1",db.getTokens(String.valueOf(player.getUniqueId()),"accessorySlot10"));
        PlayerDataStorage.map.put(p+"wand2",db.getTokens(String.valueOf(player.getUniqueId()),"accessorySlot21"));
        PlayerDataStorage.map.put(p+"accessory2",db.getTokens(String.valueOf(player.getUniqueId()),"accessorySlot20"));
        PlayerDataStorage.map.put(p+"wand3",db.getTokens(String.valueOf(player.getUniqueId()),"accessorySlot31"));
        PlayerDataStorage.map.put(p+"accessory3",db.getTokens(String.valueOf(player.getUniqueId()),"accessorySlot30"));
        PlayerDataStorage.map.put(p+"statsSTR",db.getTokens(String.valueOf(player.getUniqueId()),"statsSTR"));
        PlayerDataStorage.map.put(p+"statsDEF",db.getTokens(String.valueOf(player.getUniqueId()),"statsDEF"));
        PlayerDataStorage.map.put(p+"statsDEX",db.getTokens(String.valueOf(player.getUniqueId()),"statsDEX"));
        PlayerDataStorage.map.put(p+"statsSRV",db.getTokens(String.valueOf(player.getUniqueId()),"statsSRV"));
        PlayerDataStorage.map.put(p+"statsPoint",db.getTokens(String.valueOf(player.getUniqueId()),"statsPoint"));
        PlayerDataStorage.map.put(p+"settingDI",db.getTokens(String.valueOf(player.getUniqueId()),"setting")%10);
        PlayerDataStorage.map.put(p+"settingLS",(db.getTokens(String.valueOf(player.getUniqueId()),"setting")%1000)/10);
        PlayerDataStorage.map.put(p+"settingHE",(db.getTokens(String.valueOf(player.getUniqueId()),"setting")%100000)/1000);
        PlayerDataStorage.map.put(p+"settingAT",(db.getTokens(String.valueOf(player.getUniqueId()),"setting")%10000000)/100000);
    }

    public void loadFirstTime(Player player) {
        String p=player.getName();
        PlayerDataStorage.map.put(p+"statResetLevel",0);
        PlayerDataStorage.map.put(p+"accessorySetTime",0);
        PlayerDataStorage.map.put(p+"combatExp",0);
        PlayerDataStorage.map.put(p+"combatLevel",0);
        PlayerDataStorage.map.put(p+"miningExp",0);
        PlayerDataStorage.map.put(p+"miningLevel",0);
        PlayerDataStorage.map.put(p+"foragingExp",0);
        PlayerDataStorage.map.put(p+"foragingLevel",0);
        PlayerDataStorage.map.put(p+"fishingExp",0);
        PlayerDataStorage.map.put(p+"fishingLevel",0);
        PlayerDataStorage.map.put(p+"farmingExp",0);
        PlayerDataStorage.map.put(p+"farmingLevel",0);
        PlayerDataStorage.map.put(p+"achievementExp",0);
        PlayerDataStorage.map.put(p+"achievementLevel",0);
        PlayerDataStorage.map.put(p+"craftingExp",0);
        PlayerDataStorage.map.put(p+"craftingLevel",0);
        PlayerDataStorage.map.put(p+"statsSTR",0);
        PlayerDataStorage.map.put(p+"statsDEF",0);
        PlayerDataStorage.map.put(p+"statsDEX",0);
        PlayerDataStorage.map.put(p+"statsSRV",0);
        PlayerDataStorage.map.put(p+"statsPoint",0);
        PlayerDataStorage.map.put(p+"settingDI",0);
        PlayerDataStorage.map.put(p+"settingLS",0);
        PlayerDataStorage.map.put(p+"settingHE",0);
        PlayerDataStorage.map.put(p+"settingAT",0);
        PlayerDataStorage.map.put(p+"wand1",0);
        PlayerDataStorage.map.put(p+"wand2",1);
        PlayerDataStorage.map.put(p+"wand3",2);
        PlayerDataStorage.map.put(p+"accessory1",0);
        PlayerDataStorage.map.put(p+"accessory2",0);
        PlayerDataStorage.map.put(p+"accessory3",0);
    }
}

