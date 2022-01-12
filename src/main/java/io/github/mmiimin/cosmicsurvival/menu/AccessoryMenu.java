package io.github.mmiimin.cosmicsurvival.menu;

import io.github.mmiimin.cosmicsurvival.AccessoryItem;
import io.github.mmiimin.cosmicsurvival.AccessoryUpgrade;
import io.github.mmiimin.cosmicsurvival.util.ItemManager;
import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage;
import io.github.mmiimin.cosmicsurvival.util.WandItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class AccessoryMenu {

    ItemManager item = new ItemManager();
    AccessoryItem ai = new AccessoryItem();
    AccessoryUpgrade au = new AccessoryUpgrade();
    public void openAccessoryMenu(Player player) {
        WandItem wi = new WandItem();
        int level = PlayerDataStorage.map.get(player.getName()+"miningLevel")+PlayerDataStorage.map.get(player.getName()+"foragingLevel")+PlayerDataStorage.map.get(player.getName()+"farmingLevel")+PlayerDataStorage.map.get(player.getName()+"combatLevel")+
                PlayerDataStorage.map.get(player.getName()+"fishingLevel");
        Inventory inv = Bukkit.createInventory(null, 45, "§0장신구");
        for (int i = 0; i < 45; i++) {
            inv.setItem(i, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }

        inv.setItem(11, ai.createAccessory(player,PlayerDataStorage.map.get(player.getName()+"accessory1"),1));
        inv.setItem(20, wi.getWand(PlayerDataStorage.map.get(player.getName()+"wand1")));
        inv.setItem(22, wi.getWand(PlayerDataStorage.map.get(player.getName()+"wand2")));
        inv.setItem(24, wi.getWand(PlayerDataStorage.map.get(player.getName()+"wand3")));
        if (level>=50) {
            inv.setItem(13, ai.createAccessory(player,PlayerDataStorage.map.get(player.getName()+"accessory2"),1));
        }
        else {
            inv.setItem(13, item.createItem(Material.BARRIER, "§c슬롯 잠김",
                    "§72번째 슬롯은 §f50§7레벨부터 사용할 수 있습니다."));
        }
        if (level>=100) {
            inv.setItem(15, ai.createAccessory(player,PlayerDataStorage.map.get(player.getName()+"accessory3"),1));
        }
        else {
            inv.setItem(15, item.createItem(Material.BARRIER, "§c슬롯 잠김",
                    "§73번째 슬롯은 §f100§7레벨부터 사용할 수 있습니다."));
        }
        inv.setItem(40,item.createItem(Material.ARROW,"§a뒤로 가기"));
        inv.setItem(43, item.createItem(Material.LIGHT, "§e[!] 장신구 등급",
                "§7장신구는 §f✦ 일반 §7< §b✦ 빛나는 §7< §e✦ 완벽한 §7순서로 강화가 가능합니다",
                "§7장신구를 제작하거나 강화할 때 자원이 소모됩니다."));
        inv.setItem(44, item.createItem(Material.LIGHT, "§e[!] 완드 사용법",
                "§7완드는 염료만을 사용할 수 있으며",
                "§c⚡ 사용 효과§7가 있는 장신구를 장착 시",
                "§7해당 슬롯에 해당하는 완드를 우클릭하여 효과를 발동할 수 있습니다.",
                "§c⚡ 사용 효과§7가 없는 장신구는 완드를 사용해도 효과가 발동하지 않습니다.",
                "§7서버에 접속 시 완드 대기시간 60초가 기본으로 적용됩니다.",
                "§7완드의 재사용 대기시간이 모두 끝나기 전까지 장신구나 완드를 교체할 수 없습니다."));
        player.openInventory(inv);
    }

    public void openAccessoryList(Player player,String slot,int page) {
        Inventory inv = Bukkit.createInventory(null, 54, "§0장신구 "+slot +" - "+page);
        for (int i = 45; i < 54; i++) {
            inv.setItem(i, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }
        for (int i = (page-1)*45; i < 10; i++) {
            inv.setItem(i, ai.createAccessory(player,i,2));
        }

        inv.setItem(49,item.createItem(Material.ARROW,"§a뒤로 가기"));

        player.openInventory(inv);
    }

    public void openAccessoryUpgrade(Player player,int slot, int mode) {
        String title;
        if (mode==0) {
            title = "§0Accessory > 제작" + " §f" + slot;
        }
        else{
            title = "§0Accessory > 강화" + " §f" + slot;
        }
        Inventory inv = Bukkit.createInventory(null, 54, title);
        for (int i = 0; i < 54; i++) {
            inv.setItem(i, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }
        if (mode==0) {
            inv.setItem(24, item.createItem(Material.ANVIL, "§a제작하기", au.getUpgradeList(slot,PlayerDataStorage.accessory.get(player.getName()+slot))));
            inv.setItem(20, ai.createAccessory(player,slot,0));
        }
        else {
            inv.setItem(24, item.createItem(Material.ANVIL, "§a강화하기", au.getUpgradeList(slot,PlayerDataStorage.accessory.get(player.getName()+slot))));
            inv.setItem(11, ai.createAccessory(player,slot,0));
            inv.setItem(29, ai.createAccessory(player,slot,10));
            inv.setItem(20, item.createItem(Material.WHITE_STAINED_GLASS_PANE, "§b↓"));
        }

        inv.setItem(49,item.createItem(Material.ARROW,"§a뒤로 가기"));

        player.openInventory(inv);
    }
}
