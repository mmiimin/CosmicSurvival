package io.github.mmiimin.cosmicsurvival.menu;

import io.github.mmiimin.cosmicsurvival.util.ItemManager;
import io.github.mmiimin.cosmicsurvival.util.WandItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class WandSelectMenu {
    ItemManager item = new ItemManager();
    WandItem wi = new WandItem();
    public void openWandMenu(Player player,String page){
        Inventory inv = Bukkit.createInventory(null, 54, "§0완드 선택 - "+page);
        for (int i = 0; i < 9; i++) {
            inv.setItem(i, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
            inv.setItem(i + 45, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }
        for (int i = 0; i < 6; i++) {
            inv.setItem(i * 9, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
            inv.setItem(i * 9 + 8, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }
        for (int i = 0; i < 16; i++) {
            inv.setItem(indexMatch(i), wi.getWand(i));
        }
        inv.setItem(49,item.createItem(Material.ARROW,"§a뒤로 가기"));
        player.openInventory(inv);
    }

    private int indexMatch(int i) {
        int index = i+10;
        if (index>23) {index+=2;}
        if (index>16) {index+=2;}
        return index;
    }
}
