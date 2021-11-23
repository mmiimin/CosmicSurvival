package io.github.mmiimin.cosmicsurvival.menu;

import io.github.mmiimin.cosmicsurvival.util.ItemManager;
import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class StatsMenu {

    ItemManager item = new ItemManager();

    public void openStatsMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 36, "§0보유 스텟 포인트: "+ PlayerDataStorage.map.get(player.getName()+"statsPoint"));
        for (int i = 0; i < 36; i++) {
            inv.setItem(i, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }
        inv.setItem(10,item.createItem(Material.IRON_SWORD,"§a힘 §7" + PlayerDataStorage.map.get(player.getName()+"statsSTR"),
                "§f공격력이 §a" + String.format("%.2f",PlayerDataStorage.map.get(player.getName()+"statsSTR")*0.1) +"%§7➜"+ String.format("%.2f",(PlayerDataStorage.map.get(player.getName()+"statsSTR")+1)*0.1)+"% §f증가합니다",
                "",
                "§e클릭해서 1포인트 투자하기"));
        inv.setItem(12,item.createItem(Material.IRON_CHESTPLATE,"§a방어 §7" + PlayerDataStorage.map.get(player.getName()+"statsDEF"),
                "§f받은 피해의 §a" + String.format("%.2f", 100-(Math.pow(0.9993,PlayerDataStorage.map.get(player.getName()+"statsDEF"))*100 )) +"%§7➜"+String.format("%.2f", 100-(Math.pow(0.9993,PlayerDataStorage.map.get(player.getName()+"statsDEF")+1)*100 ))+"% §f를 방어합니다",
                "",
                "§e클릭해서 1포인트 투자하기"));
        inv.setItem(14,item.createItem(Material.FEATHER,"§a민첩 §7" + PlayerDataStorage.map.get(player.getName()+"statsDEX"),
                "§f이동 속도가 §a" + String.format("%.2f",PlayerDataStorage.map.get(player.getName()+"statsDEX")*0.06) +"%§7➜"+String.format("%.2f",(PlayerDataStorage.map.get(player.getName()+"statsDEX")+1)*0.06)+"% §f증가합니다",
                "",
                "§e클릭해서 1포인트 투자하기"));
        inv.setItem(16,item.createItem(Material.COOKED_BEEF,"§a생존 §7" + PlayerDataStorage.map.get(player.getName()+"statsSRV"),
                "§f배고픔이 §a" + String.format("%.2f", 100-(Math.pow(0.9975,PlayerDataStorage.map.get(player.getName()+"statsSRV"))*100 )) +"%§7➜"+String.format("%.2f", 100-(Math.pow(0.9975,PlayerDataStorage.map.get(player.getName()+"statsSRV")+1)*100 ))+"% §f확률로 닳지 않습니다",
                "",
                "§e클릭해서 1포인트 투자하기"));
        //inv.setItem(27,item.createItem(Material.REDSTONE_BLOCK,"§c스텟 초기화"));
        inv.setItem(31,item.createItem(Material.ARROW,"§a뒤로 가기"));
        player.openInventory(inv);
    }

}