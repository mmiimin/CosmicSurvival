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
                "§f공격력이 §a" + String.format("%.2f",PlayerDataStorage.map.get(player.getName()+"statsSTR")*0.2) +"%§7➜"+ String.format("%.2f",(PlayerDataStorage.map.get(player.getName()+"statsSTR")+1)*0.2)+"% §f증가합니다",
                "",
                "§e클릭해서 1포인트 투자하기"));
        inv.setItem(12,item.createItem(Material.IRON_CHESTPLATE,"§a방어 §7" + PlayerDataStorage.map.get(player.getName()+"statsDEF"),
                "§f받은 피해의 §a" + String.format("%.2f", 100-(Math.pow(0.9985,PlayerDataStorage.map.get(player.getName()+"statsDEF"))*100 )) +"%§7➜"+String.format("%.2f", 100-(Math.pow(0.9985,PlayerDataStorage.map.get(player.getName()+"statsDEF")+1)*100 ))+"% §f를 방어합니다",
                "",
                "§e클릭해서 1포인트 투자하기"));
        inv.setItem(14,item.createItem(Material.FEATHER,"§a민첩 §7" + PlayerDataStorage.map.get(player.getName()+"statsDEX"),
                "§f이동 속도가 §a" + String.format("%.2f",PlayerDataStorage.map.get(player.getName()+"statsDEX")*0.1) +"%§7➜"+String.format("%.2f",(PlayerDataStorage.map.get(player.getName()+"statsDEX")+1)*0.1)+"% §f증가합니다",
                "",
                "§e클릭해서 1포인트 투자하기"));
        inv.setItem(16,item.createItem(Material.COOKED_BEEF,"§a생존 §7" + PlayerDataStorage.map.get(player.getName()+"statsSRV"),
                "§f배고픔이 §a" + String.format("%.2f", 100-(Math.pow(0.9965,PlayerDataStorage.map.get(player.getName()+"statsSRV"))*100 )) +"%§7➜"+String.format("%.2f", 100-(Math.pow(0.9965,PlayerDataStorage.map.get(player.getName()+"statsSRV")+1)*100 ))+"% §f확률로 닳지 않습니다",
                "§f이 효과로 배고픔이 감소하지 않을 시 체력이 2만큼 회복됩니다.",
                "",
                "§e클릭해서 1포인트 투자하기"));
        if (PlayerDataStorage.map.get(player.getName()+"miningLevel")+PlayerDataStorage.map.get(player.getName()+"foragingLevel")+PlayerDataStorage.map.get(player.getName()+"fishingLevel")+PlayerDataStorage.map.get(player.getName()+"combatLevel")+PlayerDataStorage.map.get(player.getName()+"farmingLevel") >= PlayerDataStorage.map.get(player.getName()+"statResetLevel")) {
            inv.setItem(27,item.createItem(Material.REDSTONE_BLOCK,"§c스텟 초기화",
                    "§c투자한 모든 스텟 포인트가 환불됩니다",
                    "",
                    "§e클릭해서 스텟 초기화"));
        }
        else {
            inv.setItem(27,item.createItem(Material.COAL_BLOCK,"§c스텟 초기화",
                    "§c투자한 모든 스텟 포인트가 환불됩니다",
                    "",
                    "§c"+PlayerDataStorage.map.get(player.getName()+"statResetLevel")+"레벨 요구"));
        }

        inv.setItem(31,item.createItem(Material.ARROW,"§a뒤로 가기"));
        player.openInventory(inv);
    }

    public void resetConfirm(Player player) {
        Inventory inv = Bukkit.createInventory(null, 36, "§0스텟 초기화");
        for (int i = 0; i < 36; i++) {
            inv.setItem(i, item.createItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }
        inv.setItem(13,item.createItem(Material.REDSTONE_BLOCK,"§c스텟 초기화",
                "§4⚠ 스텟 초기화는 20레벨마다 한번 가능합니다",
                "§c지금 초기화 하면 "+(PlayerDataStorage.map.get(player.getName()+"miningLevel")+PlayerDataStorage.map.get(player.getName()+"foragingLevel")+PlayerDataStorage.map.get(player.getName()+"fishingLevel")+PlayerDataStorage.map.get(player.getName()+"combatLevel")+PlayerDataStorage.map.get(player.getName()+"farmingLevel")+20)+"레벨 이후에 초기화가 가능합니다.",
                "",
                "§c정말 초기화하시겠습니까?",
                "§c투자한 모든 스텟 포인트가 환불됩니다",
                "",
                "§e클릭해서 스텟 초기화"));
        inv.setItem(31,item.createItem(Material.ARROW,"§a뒤로 가기",
                "§7스텟창으로 돌아갑니다"));
        player.openInventory(inv);
    }
}
