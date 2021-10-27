package io.github.mmiimin.cosmicsurvival.menu;

import io.github.mmiimin.cosmicsurvival.util.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class MainProfile {

    ItemManager item = new ItemManager();

    public void openMainProfile(Player player){
        Inventory inv = Bukkit.createInventory(null, 45, Component.text("프로필"));
        for (int i=0;i<45;i++){
            inv.setItem(i, item.createItem(Material.BLACK_STAINED_GLASS_PANE," "));
        }
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("§f레벨: "+"§e0"));
        lore.add(Component.text("§f플레이 시간: §b"+player.getStatistic(Statistic.PLAY_ONE_MINUTE)/72000+"시간 §7"+(player.getStatistic(Statistic.PLAY_ONE_MINUTE)%72000)/1200+"분"));
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
        meta.setOwningPlayer(player);
        meta.lore(lore);
        meta.displayName(Component.text("§e" + player.displayName() + "의 정보"));
        playerHead.setItemMeta(meta);
        inv.setItem(4,playerHead);
        inv.setItem(19,item.createItem(Material.NETHERITE_SWORD,"전투",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명"));
        inv.setItem(20,item.createItem(Material.IRON_PICKAXE,"채광",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명"));
        inv.setItem(21,item.createItem(Material.GOLDEN_AXE,"벌목",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명"));
        inv.setItem(22,item.createItem(Material.STONE_HOE,"농사",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명"));
        inv.setItem(23,item.createItem(Material.FISHING_ROD,"낚시",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명"));
        inv.setItem(24,item.createItem(Material.ANVIL,"제작",
                "§7장신구 또는 룬을 제작하거나",
                "§7희귀 재료를 발견하여 제작 경험치를 얻을 수 있습니다.",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명"));
        inv.setItem(25,item.createItem(Material.DIAMOND,"업적",
                "§7도전과제를 클리어하여",
                "§7업적 경험치를 얻을 수 있습니다",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명",
                "설명"));
    }



}
