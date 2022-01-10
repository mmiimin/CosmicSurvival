package io.github.mmiimin.cosmicsurvival.menu;

import io.github.mmiimin.cosmicsurvival.AccessoryUpgrade;
import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage;
import io.github.mmiimin.cosmicsurvival.util.WandItem;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static java.lang.Integer.parseInt;

public class MenuClick implements Listener
{
    MainProfile profile = new MainProfile();
    StatsMenu stats = new StatsMenu();
    SettingMenu setting = new SettingMenu();
    CosmeticMenu cosmeticMenu = new CosmeticMenu();
    AccessoryMenu accessoryMenu = new AccessoryMenu();
    WandSelectMenu wandSelectMenu = new WandSelectMenu();
    WandItem wandItem = new WandItem();
    AccessoryUpgrade accessoryUpgrade = new AccessoryUpgrade();

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
                case 8 -> cosmeticMenu.openCosmeticMenu(player);
                case 36 -> stats.openStatsMenu(player);
                case 40 -> player.closeInventory();
                case 44 -> setting.openSettingMenu(player);
                case 0 -> {
                    if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 열기")) {
                        accessoryMenu.openAccessoryMenu(player);
                    }
                }
            }
        }

        else if (e.getView().getTitle().equals("§0스텟 초기화")){
            switch (slot) {
                case 13 -> {
                    String p = player.getName();
                    PlayerDataStorage.map.put(p+"statsPoint",PlayerDataStorage.map.get(p+"statsPoint")+PlayerDataStorage.map.get(p+"statsSTR")+PlayerDataStorage.map.get(p+"statsDEX")+PlayerDataStorage.map.get(p+"statsDEF")+PlayerDataStorage.map.get(p+"statsSRV"));
                    player.sendMessage("§c※ §f스텟 초기화로 §e"+(PlayerDataStorage.map.get(p+"statsSTR")+PlayerDataStorage.map.get(p+"statsDEX")+PlayerDataStorage.map.get(p+"statsDEF")+PlayerDataStorage.map.get(p+"statsSRV"))+"§f포인트가 환불되었습니다");
                    PlayerDataStorage.map.put(p+"statsSTR",0);
                    PlayerDataStorage.map.put(p+"statsDEX",0);
                    PlayerDataStorage.map.put(p+"statsDEF",0);
                    PlayerDataStorage.map.put(p+"statsSRV",0);
                    PlayerDataStorage.map.put(player.getName()+"statResetLevel",PlayerDataStorage.map.get(player.getName()+"miningLevel")+PlayerDataStorage.map.get(player.getName()+"foragingLevel")+PlayerDataStorage.map.get(player.getName()+"fishingLevel")+PlayerDataStorage.map.get(player.getName()+"combatLevel")+PlayerDataStorage.map.get(player.getName()+"farmingLevel")+20);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 0.5F);
                    player.closeInventory();
                }
                case 31 -> stats.openStatsMenu(player);
            }
        }

        else if (e.getView().getTitle().equals("§0치장품")){
            switch (slot) {
                case 10 -> cosmeticMenu.levelStyle(player,1);
                case 12 -> cosmeticMenu.hitEffect(player,1);
                case 14 -> cosmeticMenu.arrowTrail(player,1);
                case 31 -> profile.openMainProfile(player);
            }
        }

        else if (e.getView().getTitle().equals("§0설정")){
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
                case 40 -> profile.openMainProfile(player);
            }
        }

        else if (e.getView().getTitle().equals("§0장신구")){
            if (slot==40){
                profile.openMainProfile(player);
            }
            else if (player.getCooldown(wandItem.getWandMaterial(PlayerDataStorage.map.get(player.getName() + "wand1")))==0 &&
                    player.getCooldown(wandItem.getWandMaterial(PlayerDataStorage.map.get(player.getName() + "wand2")))==0 &&
                    player.getCooldown(wandItem.getWandMaterial(PlayerDataStorage.map.get(player.getName() + "wand3")))==0) {
                switch (slot) {
                    case 11 -> {
                        if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")) {
                            accessoryMenu.openAccessoryList(player, "첫번째 슬롯", 1);
                        }
                    }
                    case 13 -> {
                        if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")) {
                            accessoryMenu.openAccessoryList(player, "두번째 슬롯", 1);
                        }
                    }
                    case 15 -> {
                        if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")) {
                            accessoryMenu.openAccessoryList(player, "세번째 슬롯", 1);
                        }
                    }
                    case 20 -> wandSelectMenu.openWandMenu(player, "첫번째 슬롯");
                    case 22 -> wandSelectMenu.openWandMenu(player, "두번째 슬롯");
                    case 24 -> wandSelectMenu.openWandMenu(player, "세번째 슬롯");

                }
            }
            else{
                player.sendMessage("§c[!] 완드의 재사용 대기시간 중에는 장신구 또는 완드를 변경할 수 없습니다");
            }
        }

        else if (e.getView().getTitle().contains("§0장신구")){
            int page = parseInt(e.getView().getTitle().substring(e.getView().getTitle().length() - 1));
            int z=45*(page-1)+slot;
            if (slot == 49){accessoryMenu.openAccessoryMenu(player);}
            if (e.getClick() == ClickType.RIGHT) {
                if (!(Objects.requireNonNull(clickedItem.getItemMeta()).getLore() == null)) {
                    if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e우클릭해서 제작하기") || Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e우클릭해서 강화하기")) {
                        accessoryMenu.openAccessoryUpgrade(player, z, PlayerDataStorage.accessory.get(player.getName()+z));
                    }
                }
            }
            if (e.getView().getTitle().contains("첫번째 슬롯")) {
                if (!(Objects.requireNonNull(clickedItem.getItemMeta()).getLore() == null)) {
                    if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e좌클릭해서 장착하기") && e.getClick() == ClickType.LEFT) {
                        if (z!=0 && z == PlayerDataStorage.map.get(player.getName() + "accessory2")) {
                            PlayerDataStorage.map.put(player.getName() + "accessory2", PlayerDataStorage.map.get(player.getName() + "accessory1"));
                        } else if (z!=0 && z == PlayerDataStorage.map.get(player.getName() + "accessory3")) {
                            PlayerDataStorage.map.put(player.getName() + "accessory3", PlayerDataStorage.map.get(player.getName() + "accessory1"));
                        }
                        PlayerDataStorage.map.put(player.getName() + "accessory1", z);
                        player.sendMessage("§a✦§f 첫번째 장신구가 " + Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName() + " §f으로 변경되었습니다.");
                    }
                }
            } else if (e.getView().getTitle().contains("두번째 슬롯")) {
                if (!(Objects.requireNonNull(clickedItem.getItemMeta()).getLore() == null)) {
                    if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e좌클릭해서 장착하기") && e.getClick() == ClickType.LEFT) {
                        if (z!=0 && z == PlayerDataStorage.map.get(player.getName() + "accessory1")) {
                            PlayerDataStorage.map.put(player.getName() + "accessory1", PlayerDataStorage.map.get(player.getName() + "accessory2"));
                        } else if (z!=0 && z == PlayerDataStorage.map.get(player.getName() + "accessory3")) {
                            PlayerDataStorage.map.put(player.getName() + "accessory3", PlayerDataStorage.map.get(player.getName() + "accessory2"));
                        }
                        PlayerDataStorage.map.put(player.getName() + "accessory2", z);
                        player.sendMessage("§a✦§f 두번째 장신구가 " + Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName() + " §f으로 변경되었습니다.");
                    }
                }
            } else if (e.getView().getTitle().contains("세번째 슬롯")) {
                if (!(Objects.requireNonNull(clickedItem.getItemMeta()).getLore() == null)) {
                    if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e좌클릭해서 장착하기") && e.getClick() == ClickType.LEFT) {
                        if (z!=0 && z == PlayerDataStorage.map.get(player.getName() + "accessory2")) {
                            PlayerDataStorage.map.put(player.getName() + "accessory2", PlayerDataStorage.map.get(player.getName() + "accessory3"));
                        } else if (z!=0 && z == PlayerDataStorage.map.get(player.getName() + "accessory1")) {
                            PlayerDataStorage.map.put(player.getName() + "accessory1", PlayerDataStorage.map.get(player.getName() + "accessory3"));
                        }
                        PlayerDataStorage.map.put(player.getName() + "accessory3", z);
                        player.sendMessage("§a✦§f 세번째 장신구가 " + Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName() + " §f으로 변경되었습니다.");
                    }
                }
            }
        }

        else if (e.getView().getTitle().contains("§0완드 선택")){
            int z=getCosmeticNumber(slot, 1);
            if (slot == 49){accessoryMenu.openAccessoryMenu(player);}
            else if (e.getView().getTitle().contains("첫번째 슬롯")) {
                if (!(Objects.requireNonNull(clickedItem.getItemMeta()).getLore() == null)) {
                    if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")) {
                        if (z == PlayerDataStorage.map.get(player.getName() + "wand2")) {
                            PlayerDataStorage.map.put(player.getName() + "wand2", PlayerDataStorage.map.get(player.getName() + "wand1"));
                        } else if (z == PlayerDataStorage.map.get(player.getName() + "wand3")) {
                            PlayerDataStorage.map.put(player.getName() + "wand3", PlayerDataStorage.map.get(player.getName() + "wand1"));
                        }
                        PlayerDataStorage.map.put(player.getName() + "wand1", z);
                        player.sendMessage("§a✦§f 첫번째 완드가 " + Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName() + " §f로 변경되었습니다.");
                    }
                }
                } else if (e.getView().getTitle().contains("두번째 슬롯")) {
                if (!(Objects.requireNonNull(clickedItem.getItemMeta()).getLore() == null)) {
                    if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")) {
                        if (z == PlayerDataStorage.map.get(player.getName() + "wand1")) {
                            PlayerDataStorage.map.put(player.getName() + "wand1", PlayerDataStorage.map.get(player.getName() + "wand2"));
                        } else if (z == PlayerDataStorage.map.get(player.getName() + "wand3")) {
                            PlayerDataStorage.map.put(player.getName() + "wand3", PlayerDataStorage.map.get(player.getName() + "wand2"));
                        }
                        PlayerDataStorage.map.put(player.getName() + "wand2", z);
                        player.sendMessage("§a✦§f 두번째 완드가 " + Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName() + " §f로 변경되었습니다.");
                    }
                }
                } else if (e.getView().getTitle().contains("세번째 슬롯")) {
                if (!(Objects.requireNonNull(clickedItem.getItemMeta()).getLore() == null)) {
                    if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")) {
                        if (z == PlayerDataStorage.map.get(player.getName() + "wand2")) {
                            PlayerDataStorage.map.put(player.getName() + "wand2", PlayerDataStorage.map.get(player.getName() + "wand3"));
                        } else if (z == PlayerDataStorage.map.get(player.getName() + "wand1")) {
                            PlayerDataStorage.map.put(player.getName() + "wand1", PlayerDataStorage.map.get(player.getName() + "wand3"));
                        }
                        PlayerDataStorage.map.put(player.getName() + "wand3", z);
                        player.sendMessage("§a✦§f 세번째 완드가 " + Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName() + " §f로 변경되었습니다.");
                    }
                }
            }
        }

        else if (e.getView().getTitle().contains("§0Accessory >")){
            int acc = parseInt(e.getView().getTitle().split("f")[1]);
            int accLv = PlayerDataStorage.accessory.get(player.getName()+acc);
            if (slot == 49){accessoryMenu.openAccessoryMenu(player);}
            if (slot==24) {
                accessoryUpgrade.upgradeAccessory(acc,accLv,player);
            }
        }

        if (e.getView().getTitle().contains("§0보유 스텟 포인트")){
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
                case 27 -> {
                    if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 스텟 초기화")) {
                        stats.resetConfirm(player);
                    }
                }
                case 31 -> profile.openMainProfile(player);
            }
        }

        else if (e.getView().getTitle().contains("§0레벨 스타일")){
            if (slot == 49){cosmeticMenu.openCosmeticMenu(player);}

            else if (e.getView().getTitle().contains("1")) {
                if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")) {
                    PlayerDataStorage.map.put(player.getName()+"settingLS",getCosmeticNumber(slot,1));
                    player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");
                }
            }
            else if (e.getView().getTitle().contains("2")) {
                if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")) {
                    PlayerDataStorage.map.put(player.getName()+"settingLS",getCosmeticNumber(slot,2));
                    player.sendMessage("§e★ §f레벨 스타일이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");
                }
            }
        }

        else if (e.getView().getTitle().contains("§0타격 파티클")){
            if (slot == 49){cosmeticMenu.openCosmeticMenu(player);}

            else if (e.getView().getTitle().contains("1")) {
                if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")) {
                    PlayerDataStorage.map.put(player.getName()+"settingHE",getCosmeticNumber(slot,1));
                    player.sendMessage("§e★ §f타격 파티클이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");
                }
            }
            else if (e.getView().getTitle().contains("2")) {
                if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")) {
                    PlayerDataStorage.map.put(player.getName()+"settingHE",getCosmeticNumber(slot,2));
                    player.sendMessage("§e★ §f타격 파티클이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");
                }
            }
        }

        else if (e.getView().getTitle().contains("§0화살 파티클")){
            if (slot == 49){cosmeticMenu.openCosmeticMenu(player);}

            else if (e.getView().getTitle().contains("1")) {
                if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")) {
                    PlayerDataStorage.map.put(player.getName()+"settingAT",getCosmeticNumber(slot,1));
                    player.sendMessage("§e★ §f화살 파티클이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");
                }
            }
            else if (e.getView().getTitle().contains("2")) {
                if (Objects.requireNonNull(Objects.requireNonNull(clickedItem.getItemMeta()).getLore()).contains("§e클릭해서 변경하기")) {
                    PlayerDataStorage.map.put(player.getName()+"settingAT",getCosmeticNumber(slot,2));
                    player.sendMessage("§e★ §f화살 파티클이 "+ Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName()+" §f으로 변경되었습니다.");
                }
            }
        }

    }

    private int getCosmeticNumber(int inv, int page) {
        int result = inv;
        if (result>=37) { result-=6; }
        else if (result>=28) { result-=4; }
        else if (result>=19) { result-=2; }
        result-=10;
        result+=(28*(page-1));
        return result;
    }
}
