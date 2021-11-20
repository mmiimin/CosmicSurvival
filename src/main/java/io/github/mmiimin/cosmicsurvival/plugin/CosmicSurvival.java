package io.github.mmiimin.cosmicsurvival.plugin;

import io.github.mmiimin.cosmicsurvival.database.Database;
import io.github.mmiimin.cosmicsurvival.database.SQLite;
import io.github.mmiimin.cosmicsurvival.menu.MainProfile;
import io.github.mmiimin.cosmicsurvival.menu.MenuClick;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class CosmicSurvival extends JavaPlugin implements CommandExecutor {

    private Database db;
    MainProfile menu = new MainProfile();

    @Override
    public void onEnable(){
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getServer().getPluginManager().registerEvents(new MenuClick(), this);
        this.db = new SQLite(this);
        this.db.load();
        Objects.requireNonNull(this.getCommand("profile")).setExecutor(this);
    }

    @Override
    public void onDisable(){

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            menu.openMainProfile(player);
        }
        else{
            sender.sendMessage("Cannot open GUI menu");
        }
        return true;
    }

    public Database getRDatabase() {
        return this.db;
    }


}

