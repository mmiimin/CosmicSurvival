package io.github.mmiimin.cosmicsurvival.menu;

import io.github.mmiimin.cosmicsurvival.plugin.CosmicSurvival;
import io.github.mmiimin.cosmicsurvival.util.ItemManager;
import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class MainProfile{

    ItemManager item = new ItemManager();

    private String getLeaderboard(String mastery, int rank) {
        ConfigurationSection cf = CosmicSurvival.plugin.getConfig().getConfigurationSection("Leaderboard.");
        assert cf != null;
        String ranks;
        String sInfo = cf.getValues(false)
                .entrySet()
                .stream()
                .sorted((a1, a2) -> {
                    int points1 = ((MemorySection) a1.getValue()).getInt(mastery);
                    int points2 = ((MemorySection) a2.getValue()).getInt(mastery);
                    return points2 - points1;
                })
                .skip(rank)
                .map(e -> Bukkit.getOfflinePlayer(UUID.fromString(e.getKey())).getName())
                .findFirst()
                .orElse("사람이 없죠?");
        int iInfo = cf.getValues(false)
                .entrySet()
                .stream()
                .sorted((a1, a2) -> {
                    int points1 = ((MemorySection) a1.getValue()).getInt(mastery);
                    int points2 = ((MemorySection) a2.getValue()).getInt(mastery);
                    return points2 - points1;
                })
                .skip(rank)
                .map(e -> ((MemorySection) e.getValue()).getInt(mastery))
                .findFirst()
                .orElse(0);
        /*
        Pair<String, Integer> info = cf.getValues(false)
                .entrySet()
                .stream()
                .sorted((a1, a2) -> {
                    int points1 = ((MemorySection) a1.getValue()).getInt(mastery);
                    int points2 = ((MemorySection) a2.getValue()).getInt(mastery);
                    return points2 - points1;
                })
                .skip(rank)
                .map(e -> Pair.of(Bukkit.getOfflinePlayer(UUID.fromString(e.getKey())).getName(), ((MemorySection) e.getValue()).getInt(mastery)))
                .findFirst()
                .orElse(Pair.of("사람이 없죠?",1));
        */
        switch (rank) {
            case 0 -> ranks = "§e#1";
            case 1 -> ranks = "§6#2";
            case 2 -> ranks = "§c#3";
            default -> ranks = "§7" + (rank-1);
        }
        if (iInfo/10000000 == 50) {
            return ranks + " "+ sInfo + " §7Lv" + iInfo/10000000 + " §b§lMAX LEVEL";
        }
        return ranks + " "+ sInfo + " §7Lv" + iInfo/10000000 + " §8" + String.format("%.2f", ((iInfo%10000000) / ((float) (iInfo/10000000)*10000+10000))*100)+"%";
        //return ranks + " "+ info.getKey() + " §7Lv" + info.getValue()/10000000 + " §8" + String.format("%.2f", ((info.getValue()%10000000) / ((float) (info.getValue()/10000000)*10000+10000))*100)+"%";
    }

    public void openMainProfile(Player player){
        String p = player.getName();

        Inventory inv = Bukkit.createInventory(null, 45, "§0프로필");
        for (int i=0;i<45;i++){
            inv.setItem(i, item.createItem(Material.BLACK_STAINED_GLASS_PANE," "));
        }
        List<String> lore = new ArrayList<>();
        lore.add("§e★§f 레벨: §e"+ (PlayerDataStorage.map.get(p+"combatLevel")+PlayerDataStorage.map.get(p+"miningLevel")+PlayerDataStorage.map.get(p+"foragingLevel")+PlayerDataStorage.map.get(p+"farmingLevel")+
                PlayerDataStorage.map.get(p+"fishingLevel")));
        lore.add("§b⌚§f 접속 시간: §b"+player.getStatistic(Statistic.PLAY_ONE_MINUTE)/72000+"시간 "+(player.getStatistic(Statistic.PLAY_ONE_MINUTE)%72000)/1200+"분");
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
        Objects.requireNonNull(meta).setOwningPlayer(player);
        meta.setLore(lore);
        meta.setDisplayName("§6⬛ " + player.getName() + "님의 정보");
        playerHead.setItemMeta(meta);
        inv.setItem(4,playerHead);
        
        inv.setItem(20,item.createItem(Material.NETHERITE_SWORD,"§c\uD83D\uDDE1 전투 §7Lv" + PlayerDataStorage.map.get(p+"combatLevel"),
                "§7몬스터를 사냥하여",
                "§7전투 경험치를 얻을 수 있습니다",
                "",
                "§f다음 레벨까지: §b"+String.format("%.2f",(PlayerDataStorage.map.get(p+"combatExp")/((float) PlayerDataStorage.map.get(p+"combatLevel")*10000+10000))*100)+"% §7("+PlayerDataStorage.map.get(p+"combatExp")+"/"+(PlayerDataStorage.map.get(p+"combatLevel")*10000+10000)+")",
                "§6-".repeat(Math.round((PlayerDataStorage.map.get(p+"combatExp")/((float) PlayerDataStorage.map.get(p+"combatLevel")*10000+10000))*20))+"§7-".repeat(20 - Math.round((PlayerDataStorage.map.get(p+"combatExp")/((float) PlayerDataStorage.map.get(p+"combatLevel")*10000+10000))*20)),
                "",
                "§b★ 순위표",
                getLeaderboard("co",0),
                getLeaderboard("co",1),
                getLeaderboard("co",2)));
        inv.setItem(21,item.createItem(Material.IRON_PICKAXE,"§e⛏ 채광 §7Lv" + PlayerDataStorage.map.get(p+"miningLevel"),
                "§7돌과 광물을 채굴하여",
                "§7채광 경험치를 얻을 수 있습니다",
                "",
                "§f다음 레벨까지: §b"+String.format("%.2f",(PlayerDataStorage.map.get(p+"miningExp")/((float) PlayerDataStorage.map.get(p+"miningLevel")*10000+10000))*100)+"% §7("+PlayerDataStorage.map.get(p+"miningExp")+"/"+(PlayerDataStorage.map.get(p+"miningLevel")*10000+10000)+")",
                "§6-".repeat(Math.round((PlayerDataStorage.map.get(p+"miningExp")/((float) PlayerDataStorage.map.get(p+"miningLevel")*10000+10000))*20))+"§7-".repeat(20 - Math.round((PlayerDataStorage.map.get(p+"miningExp")/((float) PlayerDataStorage.map.get(p+"miningLevel")*10000+10000))*20)),
                "",
                "§b★ 순위표",
                getLeaderboard("mi",0),
                getLeaderboard("mi",1),
                getLeaderboard("mi",2)));
        inv.setItem(22,item.createItem(Material.GOLDEN_AXE,"§6\uD83E\uDE93 벌목 §7Lv" + PlayerDataStorage.map.get(p+"foragingLevel"),
                "§7나무를 캐서",
                "§7벌목 경험치를 얻을 수 있습니다",
                "",
                "§f다음 레벨까지: §b"+String.format("%.2f",(PlayerDataStorage.map.get(p+"foragingExp")/((float) PlayerDataStorage.map.get(p+"foragingLevel")*10000+10000))*100)+"% §7("+PlayerDataStorage.map.get(p+"foragingExp")+"/"+(PlayerDataStorage.map.get(p+"foragingLevel")*10000+10000)+")",
                "§6-".repeat(Math.round((PlayerDataStorage.map.get(p+"foragingExp")/((float) PlayerDataStorage.map.get(p+"foragingLevel")*10000+10000))*20))+"§7-".repeat(20 - Math.round((PlayerDataStorage.map.get(p+"foragingExp")/((float) PlayerDataStorage.map.get(p+"foragingLevel")*10000+10000))*20)),
                "",
                "§b★ 순위표",
                getLeaderboard("fo",0),
                getLeaderboard("fo",1),
                getLeaderboard("fo",2)));
        inv.setItem(23,item.createItem(Material.STONE_HOE,"§a☘ 농사 §7Lv" + PlayerDataStorage.map.get(p+"farmingLevel"),
                "§7농작물을 수확하여",
                "§7농사 경험치를 얻을 수 있습니다",
                "",
                "§f다음 레벨까지: §b"+String.format("%.2f",(PlayerDataStorage.map.get(p+"farmingExp")/((float) PlayerDataStorage.map.get(p+"farmingLevel")*10000+10000))*100)+"% §7("+PlayerDataStorage.map.get(p+"farmingExp")+"/"+(PlayerDataStorage.map.get(p+"farmingLevel")*10000+10000)+")",
                "§6-".repeat(Math.round((PlayerDataStorage.map.get(p+"farmingExp")/((float) PlayerDataStorage.map.get(p+"farmingLevel")*10000+10000))*20))+"§7-".repeat(20 - Math.round((PlayerDataStorage.map.get(p+"farmingExp")/((float) PlayerDataStorage.map.get(p+"farmingLevel")*10000+10000))*20)),
                "",
                "§b★ 순위표",
                getLeaderboard("fa",0),
                getLeaderboard("fa",1),
                getLeaderboard("fa",2)));
        inv.setItem(24,item.createItem(Material.FISHING_ROD,"§3\uD83C\uDFA3 낚시 §7Lv" + PlayerDataStorage.map.get(p+"fishingLevel"),
                "§7물고기를 잡거나 보물을 낚아",
                "§7낚시 경험치를 얻을 수 있습니다",
                "",
                "§f다음 레벨까지: §b"+String.format("%.2f",(PlayerDataStorage.map.get(p+"fishingExp")/((float) PlayerDataStorage.map.get(p+"fishingLevel")*10000+10000))*100)+"% §7("+PlayerDataStorage.map.get(p+"fishingExp")+"/"+(PlayerDataStorage.map.get(p+"fishingLevel")*10000+10000)+")",
                "§6-".repeat(Math.round((PlayerDataStorage.map.get(p+"fishingExp")/((float) PlayerDataStorage.map.get(p+"fishingLevel")*10000+10000))*20))+"§7-".repeat(20 - Math.round((PlayerDataStorage.map.get(p+"fishingExp")/((float) PlayerDataStorage.map.get(p+"fishingLevel")*10000+10000))*20)),
                "",
                "§b★ 순위표",
                getLeaderboard("fi",0),
                getLeaderboard("fi",1),
                getLeaderboard("fi",2)));

        inv.setItem(40,item.createItem(Material.BARRIER,"§c✖ 닫기"));
        inv.setItem(44,item.createItem(Material.COMPARATOR,"§a⚙ 설정","","§e클릭해서 열기"));
        if (PlayerDataStorage.map.get(p+"statsPoint") == 0) {
            inv.setItem(36, item.createItem(Material.IRON_INGOT, "§f❖ 스텟", "", "§e클릭해서 열기"));
        }
        else {
            inv.setItem(36, item.createItem(Material.GOLD_INGOT, "§e❖ 스텟", "§f사용하지 않은 스텟 포인트가 §6"+PlayerDataStorage.map.get(p+"statsPoint")+"§f만큼 있습니다!","", "§e클릭해서 열기"));
        }
        int level = PlayerDataStorage.map.get(player.getName()+"miningLevel")+PlayerDataStorage.map.get(player.getName()+"foragingLevel")+PlayerDataStorage.map.get(player.getName()+"farmingLevel")+PlayerDataStorage.map.get(player.getName()+"combatLevel")+
                PlayerDataStorage.map.get(player.getName()+"fishingLevel");
        if (level>=5) {
            inv.setItem(0, item.createItem(Material.EMERALD, "§a✦ 장신구 메뉴", "§7장신구를 제작 및 강화하고", "§7착용할 수 있습니다", "", "§e클릭해서 열기"));
        }
        else{
            inv.setItem(0, item.createItem(Material.EMERALD, "§a✦ 장신구 메뉴", "§7장신구를 제작 및 강화하고", "§7착용할 수 있습니다", "", "§c5레벨부터 사용 가능"));
        }
        inv.setItem(8,item.createItem(Material.CHEST,"§e★ 치장품","§7레벨 칭호, 파티클 등","§7치장 아이템을 변경할 수 있습니다","","§e클릭해서 열기"));

        player.openInventory(inv);
    }

}
