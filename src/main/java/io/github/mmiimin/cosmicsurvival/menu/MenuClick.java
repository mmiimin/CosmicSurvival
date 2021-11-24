package io.github.mmiimin.cosmicsurvival.menu;

import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

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

        if (e.getView().getTitle().equals("§0스텟 초기화")){
            switch (slot) {
                case 13 -> {
                    String p = player.getName();
                    PlayerDataStorage.map.put(p+"statsPoint",PlayerDataStorage.map.get(p+"statsPoint")+PlayerDataStorage.map.get(p+"statsSTR")+PlayerDataStorage.map.get(p+"statsDEX")+PlayerDataStorage.map.get(p+"statsDEF")+PlayerDataStorage.map.get(p+"statsSRV"));
                    player.sendMessage("§c※ §f스텟 초기화로 §e"+(PlayerDataStorage.map.get(p+"statsSTR")+PlayerDataStorage.map.get(p+"statsDEX")+PlayerDataStorage.map.get(p+"statsDEF")+PlayerDataStorage.map.get(p+"statsSRV"))+"§f포인트가 환불되었습니다");
                    PlayerDataStorage.map.put(p+"statsSTR",0);
                    PlayerDataStorage.map.put(p+"statsDEX",0);
                    PlayerDataStorage.map.put(p+"statsDEF",0);
                    PlayerDataStorage.map.put(p+"statsSRV",0);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 0.5F);
                    player.closeInventory();
                }
                case 31 -> stats.openStatsMenu(player);
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
                    setting.openSettingMenu(player);
                }
                case 21 -> setting.levelStyle(player,1);
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
                        stats.openStatsMenu(player);
                    }
                    else {
                        player.sendMessage("§c[!] 스텟 포인트가 부족합니다");
                        player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.0F);
                    }
                }
                case 27 -> stats.resetConfirm(player);
                case 31 -> profile.openMainProfile(player);
            }
        }

        if (e.getView().getTitle().contains("§0레벨 스타일")){
            switch (slot) {
                case 10 -> {if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")){PlayerDataStorage.map.put(player.getName()+"settingLS",0);player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");}}
                case 11 -> {if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")){PlayerDataStorage.map.put(player.getName()+"settingLS",1);player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");}}
                case 12 -> {if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")){PlayerDataStorage.map.put(player.getName()+"settingLS",2);player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");}}
                case 13 -> {if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")){PlayerDataStorage.map.put(player.getName()+"settingLS",3);player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");}}
                case 14 -> {if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")){PlayerDataStorage.map.put(player.getName()+"settingLS",4);player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");}}
                case 15 -> {if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")){PlayerDataStorage.map.put(player.getName()+"settingLS",5);player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");}}
                case 16 -> {if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")){PlayerDataStorage.map.put(player.getName()+"settingLS",6);player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");}}
                case 19 -> {if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")){PlayerDataStorage.map.put(player.getName()+"settingLS",7);player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");}}
                case 20 -> {if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")){PlayerDataStorage.map.put(player.getName()+"settingLS",8);player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");}}
                case 21 -> {if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")){PlayerDataStorage.map.put(player.getName()+"settingLS",9);player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");}}
                case 22 -> {if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")){PlayerDataStorage.map.put(player.getName()+"settingLS",10);player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");}}
                case 23 -> {if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")){PlayerDataStorage.map.put(player.getName()+"settingLS",11);player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");}}
                case 24 -> {if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")){PlayerDataStorage.map.put(player.getName()+"settingLS",12);player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");}}
                case 25 -> {if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")){PlayerDataStorage.map.put(player.getName()+"settingLS",13);player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");}}
                case 28 -> {if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")){PlayerDataStorage.map.put(player.getName()+"settingLS",14);player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");}}
                case 29 -> {if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")){PlayerDataStorage.map.put(player.getName()+"settingLS",15);player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");}}
                case 49 -> setting.openSettingMenu(player);
            }
        }


    }
}
