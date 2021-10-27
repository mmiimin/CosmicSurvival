package io.github.mmiimin.cosmicsurvival.plugin;

import io.github.mmiimin.cosmicsurvival.database.Database;
import io.github.mmiimin.cosmicsurvival.database.SQLite;
import io.github.mmiimin.cosmicsurvival.plugin.EventListener;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class CosmicSurvival extends JavaPlugin implements Listener {

    private Database db;
    private Listener EventListener;

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(EventListener, this);
        this.db = new SQLite(this);
        this.db.load();
    }

    @Override
    public void onDisable(){

    }

    public Database getRDatabase() {
        return this.db;
    }


}

