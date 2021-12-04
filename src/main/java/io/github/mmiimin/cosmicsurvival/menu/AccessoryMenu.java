package io.github.mmiimin.cosmicsurvival.menu;

import io.github.mmiimin.cosmicsurvival.util.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class AccessoryMenu {

    ItemManager item = new ItemManager();

    public void openAccessoryMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 36, "§0장신구");
        for (int i = 0; i < 36; i++) {
            inv.setItem(i, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }

        inv.setItem(10, item.createItem(Material.NETHER_STAR, "§a",
                "§7닉네임 옆에 표시되는",
                "§7레벨 칭호의 스타일을 설정합니다",
                "",
                "§e클릭해서 변경하기"));
    }


}
