package io.github.mmiimin.cosmicsurvival.menu;

import io.github.mmiimin.cosmicsurvival.util.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class StatsMenu {

    ItemManager item = new ItemManager();

    public void openMainProfile(Player player) {
        Inventory inv = Bukkit.createInventory(null, 36, "보유 스탯 포인트: ");
        for (int i = 0; i < 36; i++) {
            inv.setItem(i, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }
    }

}
