package io.github.mmiimin.cosmicsurvival.menu;

import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MenuClick implements Listener
{
    MainProfile profile = new MainProfile();
    StatsMenu stats = new StatsMenu();
    SettingMenu setting = new SettingMenu();

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {

        if (!(e.getView().getTitle().contains("§0"))) return;
        e.setCancelled(true);
        final ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType().isAir()) return;
        final Player player = (Player) e.getWhoClicked();
        final int slot = e.getRawSlot();
        if (e.getView().getTitle().equals("§0프로필")){
            switch (slot) {
                case 36 -> stats.openStatsMenu(player);
                case 40 -> player.closeInventory();
                case 44 -> setting.openSettingMenu(player);
            }
        }

        if (e.getView().getTitle().equals("§0설정")){
            switch (slot) {
                case 19 -> {
                    if (PlayerDataStorage.map.get(player.getName()+"settingDI") == 0) {
                        PlayerDataStorage.map.put(player.getName()+"settingDI",1);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
                    }
                    else if (PlayerDataStorage.map.get(player.getName()+"settingDI") == 1) {
                        PlayerDataStorage.map.put(player.getName()+"settingDI",0);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
                    }
                    player.closeInventory();
                    setting.openSettingMenu(player);
                }
                case 40 -> profile.openMainProfile(player);
            }
        }

        else if (e.getView().getTitle().contains("§0보유 스텟 포인트")){
            switch (slot) {
                case 10 -> {
                    if (PlayerDataStorage.map.get(player.getName()+"statsPoint") > 0) {
                        PlayerDataStorage.map.put(player.getName()+"statsPoint",PlayerDataStorage.map.get(player.getName()+"statsPoint")-1);
                        PlayerDataStorage.map.put(player.getName()+"statsSTR",PlayerDataStorage.map.get(player.getName()+"statsSTR")+1);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 2.0F);
                        player.closeInventory();
                        stats.openStatsMenu(player);
                    }
                    else {
                        player.sendMessage("§c[!] 스텟 포인트가 부족합니다");
                        player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.0F);
                    }
                }
                case 12 -> {
                    if (PlayerDataStorage.map.get(player.getName()+"statsPoint") > 0) {
                        PlayerDataStorage.map.put(player.getName()+"statsPoint",PlayerDataStorage.map.get(player.getName()+"statsPoint")-1);
                        PlayerDataStorage.map.put(player.getName()+"statsDEF",PlayerDataStorage.map.get(player.getName()+"statsDEF")+1);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 2.0F);
                        player.closeInventory();
                        stats.openStatsMenu(player);
                    }
                    else {
                        player.sendMessage("§c[!] 스텟 포인트가 부족합니다");
                        player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.0F);
                    }
                }
                case 14 -> {
                    if (PlayerDataStorage.map.get(player.getName()+"statsPoint") > 0) {
                        PlayerDataStorage.map.put(player.getName()+"statsPoint",PlayerDataStorage.map.get(player.getName()+"statsPoint")-1);
                        PlayerDataStorage.map.put(player.getName()+"statsDEX",PlayerDataStorage.map.get(player.getName()+"statsDEX")+1);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 2.0F);
                        player.closeInventory();
                        stats.openStatsMenu(player);
                    }
                    else {
                        player.sendMessage("§c[!] 스텟 포인트가 부족합니다");
                        player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.0F);
                    }
                }
                case 16 -> {
                    if (PlayerDataStorage.map.get(player.getName()+"statsPoint") > 0) {
                        PlayerDataStorage.map.put(player.getName()+"statsPoint",PlayerDataStorage.map.get(player.getName()+"statsPoint")-1);
                        PlayerDataStorage.map.put(player.getName()+"statsSRV",PlayerDataStorage.map.get(player.getName()+"statsSRV")+1);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 2.0F);
                        player.closeInventory();
                        stats.openStatsMenu(player);
                    }
                    else {
                        player.sendMessage("§c[!] 스텟 포인트가 부족합니다");
                        player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.0F);
                    }
                }
                case 27 -> {
                    player.sendMessage("스텟 초기화 기능이 곧 추가됩니다");
                }
                case 31 -> profile.openMainProfile(player);
            }
        }




    }
}
