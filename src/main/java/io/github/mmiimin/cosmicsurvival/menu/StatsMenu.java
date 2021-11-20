package io.github.mmiimin.cosmicsurvival.menu;

import io.github.mmiimin.cosmicsurvival.util.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class StatsMenu {

    ItemManager item = new ItemManager();

    public void openMainProfile(Player player) {
        Inventory inv = Bukkit.createInventory(null, 36, "§0보유 스탯 포인트: ");
        for (int i = 0; i < 36; i++) {
            inv.setItem(i, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }
        inv.setItem(10,item.createItem(Material.DIAMOND,"§a힘 (STR)",
                "§f공격력이 %§7➜% 증가합니다",
                "",
                "§e클릭해서 1포인트 투자하기"));

        inv.setItem(27,item.createItem(Material.REDSTONE_BLOCK,"§c스텟 초기화"));
        inv.setItem(31,item.createItem(Material.ARROW,"§a뒤로 가기"));

    }

}
