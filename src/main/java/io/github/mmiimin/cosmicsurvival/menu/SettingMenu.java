package io.github.mmiimin.cosmicsurvival.menu;

import io.github.mmiimin.cosmicsurvival.util.ItemManager;
import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage;
import io.github.mmiimin.cosmicsurvival.LevelStyleManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SettingMenu {

    ItemManager item = new ItemManager();


    public void openSettingMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 45, "§0설정");
        for (int i = 0; i < 45; i++) {
            inv.setItem(i, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }
        inv.setItem(10,item.createItem(Material.GOLDEN_SWORD,"§a피해량 표시",
                "§7피해를 주거나 받을 시",
                        "§7채팅창에 피해에 대한 정보를 전송합니다"));
        if (PlayerDataStorage.map.get(player.getName()+"settingDI") == 0) {
            inv.setItem(19, item.createItem(Material.GRAY_DYE, "§f현재 옵션: §7꺼짐",
                    "§7피해에 대한 정보를 제공하지 않습니다.",
                    "",
                    "§e클릭해서 변경하기"));
        }
        else if (PlayerDataStorage.map.get(player.getName()+"settingDI") == 1) {
            inv.setItem(19, item.createItem(Material.LIME_DYE, "§f현재 옵션: §a켜짐",
                    "§7피해에 대한 정보를 전부 제공합니다.",
                    "",
                    "§e클릭해서 변경하기"));
        }




        inv.setItem(40,item.createItem(Material.ARROW,"§a뒤로 가기"));
        player.openInventory(inv);
    }


}
