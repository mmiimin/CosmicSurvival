package io.github.mmiimin.cosmicsurvival.menu;

import io.github.mmiimin.cosmicsurvival.util.ItemManager;
import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainProfile{

    ItemManager item = new ItemManager();

    public void openMainProfile(Player player){
        String p = player.getName();

        Inventory inv = Bukkit.createInventory(null, 45, "§0프로필");
        for (int i=0;i<45;i++){
            inv.setItem(i, item.createItem(Material.BLACK_STAINED_GLASS_PANE," "));
        }
        List<String> lore = new ArrayList<>();
        lore.add("§e★§f 레벨: §e"+ (PlayerDataStorage.map.get(p+"combatLevel")+PlayerDataStorage.map.get(p+"miningLevel")+PlayerDataStorage.map.get(p+"foragingLevel")+PlayerDataStorage.map.get(p+"farmingLevel")+
                PlayerDataStorage.map.get(p+"fishingLevel")+PlayerDataStorage.map.get(p+"craftingLevel")+PlayerDataStorage.map.get(p+"achievementLevel")));
        lore.add("§b⌚§f 접속 시간: §b"+player.getStatistic(Statistic.PLAY_ONE_MINUTE)/72000+"시간 "+(player.getStatistic(Statistic.PLAY_ONE_MINUTE)%72000)/1200+"분");
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
        Objects.requireNonNull(meta).setOwningPlayer(player);
        meta.setLore(lore);
        meta.setDisplayName("§6⬛ " + player.getName() + "님의 정보");
        playerHead.setItemMeta(meta);
        inv.setItem(4,playerHead);
        inv.setItem(24,item.createItem(Material.NETHERITE_SWORD,"§c\uD83D\uDDE1 전투 §7Lv" + PlayerDataStorage.map.get(p+"combatLevel"), //19
                "§7몬스터를 사냥하여",
                "§7전투 경험치를 얻을 수 있습니다",
                "",
                "§f다음 레벨까지: §b"+String.format("%.2f",(PlayerDataStorage.map.get(p+"combatExp")/((float) PlayerDataStorage.map.get(p+"combatLevel")*10000+10000))*100)+"% §7("+PlayerDataStorage.map.get(p+"combatExp")+"/"+(PlayerDataStorage.map.get(p+"combatLevel")*10000+10000)+")",
                "§6-".repeat((int) Math.ceil((PlayerDataStorage.map.get(p+"combatExp")/((float) PlayerDataStorage.map.get(p+"combatLevel")*10000+10000))*20))+"§7-".repeat(20 - (int) Math.ceil((PlayerDataStorage.map.get(p+"combatExp")/((float) PlayerDataStorage.map.get(p+"combatLevel")*10000+10000))*20))));
        inv.setItem(20,item.createItem(Material.IRON_PICKAXE,"§e⛏ 채광 §7Lv" + PlayerDataStorage.map.get(p+"miningLevel"),
                "§7돌과 광물을 채굴하여",
                "§7채광 경험치를 얻을 수 있습니다",
                "",
                "§f다음 레벨까지: §b"+String.format("%.2f",(PlayerDataStorage.map.get(p+"miningExp")/((float) PlayerDataStorage.map.get(p+"miningLevel")*10000+10000))*100)+"% §7("+PlayerDataStorage.map.get(p+"miningExp")+"/"+(PlayerDataStorage.map.get(p+"miningLevel")*10000+10000)+")",
                "§6-".repeat((int) Math.ceil((PlayerDataStorage.map.get(p+"miningExp")/((float) PlayerDataStorage.map.get(p+"miningLevel")*10000+10000))*20))+"§7-".repeat(20 - (int) Math.ceil((PlayerDataStorage.map.get(p+"miningExp")/((float) PlayerDataStorage.map.get(p+"miningLevel")*10000+10000))*20))));
        inv.setItem(21,item.createItem(Material.GOLDEN_AXE,"§6\uD83E\uDE93 벌목 §7Lv" + PlayerDataStorage.map.get(p+"foragingLevel"),
                "§7나무를 캐서",
                "§7벌목 경험치를 얻을 수 있습니다",
                "",
                "§f다음 레벨까지: §b"+String.format("%.2f",(PlayerDataStorage.map.get(p+"foragingExp")/((float) PlayerDataStorage.map.get(p+"foragingLevel")*10000+10000))*100)+"% §7("+PlayerDataStorage.map.get(p+"foragingExp")+"/"+(PlayerDataStorage.map.get(p+"foragingLevel")*10000+10000)+")",
                "§6-".repeat((int) Math.ceil((PlayerDataStorage.map.get(p+"foragingExp")/((float) PlayerDataStorage.map.get(p+"foragingLevel")*10000+10000))*20))+"§7-".repeat(20 - (int) Math.ceil((PlayerDataStorage.map.get(p+"foragingExp")/((float) PlayerDataStorage.map.get(p+"foragingLevel")*10000+10000))*20))));
        inv.setItem(22,item.createItem(Material.STONE_HOE,"§a☘ 농사 §7Lv" + PlayerDataStorage.map.get(p+"farmingLevel"),
                "§7농작물을 수확하여",
                "§7농사 경험치를 얻을 수 있습니다",
                "",
                "§f다음 레벨까지: §b"+String.format("%.2f",(PlayerDataStorage.map.get(p+"farmingExp")/((float) PlayerDataStorage.map.get(p+"farmingLevel")*10000+10000))*100)+"% §7("+PlayerDataStorage.map.get(p+"farmingExp")+"/"+(PlayerDataStorage.map.get(p+"farmingLevel")*10000+10000)+")",
                "§6-".repeat((int) Math.ceil((PlayerDataStorage.map.get(p+"farmingExp")/((float) PlayerDataStorage.map.get(p+"farmingLevel")*10000+10000))*20))+"§7-".repeat(20 - (int) Math.ceil((PlayerDataStorage.map.get(p+"farmingExp")/((float) PlayerDataStorage.map.get(p+"farmingLevel")*10000+10000))*20))));
        inv.setItem(23,item.createItem(Material.FISHING_ROD,"§3\uD83C\uDFA3 낚시 §7Lv" + PlayerDataStorage.map.get(p+"fishingLevel"),
                "§7물고기를 잡거나 보물을 낚아",
                "§7낚시 경험치를 얻을 수 있습니다",
                "",
                "§f다음 레벨까지: §b"+String.format("%.2f",(PlayerDataStorage.map.get(p+"fishingExp")/((float) PlayerDataStorage.map.get(p+"fishingLevel")*10000+10000))*100)+"% §7("+PlayerDataStorage.map.get(p+"fishingExp")+"/"+(PlayerDataStorage.map.get(p+"fishingLevel")*10000+10000)+")",
                "§6-".repeat((int) Math.ceil((PlayerDataStorage.map.get(p+"fishingExp")/((float) PlayerDataStorage.map.get(p+"fishingLevel")*10000+10000))*20))+"§7-".repeat(20 - (int) Math.ceil((PlayerDataStorage.map.get(p+"fishingExp")/((float) PlayerDataStorage.map.get(p+"fishingLevel")*10000+10000))*20))));
        /*inv.setItem(24,item.createItem(Material.ANVIL,"§d⦾ 제작",
                "§7장신구 또는 룬을 제작하거나",
                "§7희귀 재료를 발견하여 제작 경험치를 얻을 수 있습니다.",
                "",
                "§f다음 레벨까지: §b"+String.format("%.2f",(pds.getValue(p+"fishingExp")/(float) pds.getValue(p+"fishingLevel")*10000+10000))+"% §7("+pds.getValue(p+"fishingExp")+"/"+pds.getValue(p+"fishingLevel")*10000+10000+")",
                "§6-".repeat((int) Math.ceil((pds.getValue(p+"fishingExp")/(float) pds.getValue(p+"fishingLevel")*10000+10000)/5))+"§7-".repeat(20 - (int) Math.ceil((pds.getValue(p+"fishingExp")/(float) pds.getValue(p+"fishingLevel")*10000+10000)/5))));

        inv.setItem(25,item.createItem(Material.DIAMOND,"§b✧ 업적",
                "§7도전과제를 클리어하여",
                "§7업적 경험치를 얻을 수 있습니다",
                "",
                "§c 시즌 7 추가 예정"));
        */
        inv.setItem(40,item.createItem(Material.BARRIER,"§c✖ 닫기"));
        inv.setItem(44,item.createItem(Material.COMPARATOR,"§a⚙ 설정","","§e클릭해서 열기"));
        if (PlayerDataStorage.map.get(p+"statsPoint") == 0) {
            inv.setItem(36, item.createItem(Material.IRON_INGOT, "§f❖ 스텟", "", "§e클릭해서 열기"));
        }
        else {
            inv.setItem(36, item.createItem(Material.GOLD_INGOT, "§e❖ 스텟", "§f사용하지 않은 스텟 포인트가 §6"+PlayerDataStorage.map.get(p+"statsPoint")+"§f만큼 있습니다!","", "§e클릭해서 열기"));
        }
        //inv.setItem(0,item.createItem(Material.EMERALD,"§a❈ 장신구 메뉴","§7장신구를 착용하거나","§7추가 능력을 감정할 수 있습니다","","§e클릭해서 열기"));
        //inv.setItem(8,item.createItem(Material.END_CRYSTAL,"§5❋ 룬 메뉴","§7룬을 착용하거나","§7강화할 수 있습니다","","§e클릭해서 열기"));

        player.openInventory(inv);
    }

}
