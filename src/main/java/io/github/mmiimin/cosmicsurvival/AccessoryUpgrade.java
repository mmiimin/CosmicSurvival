package io.github.mmiimin.cosmicsurvival;

import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccessoryUpgrade {

    AccessoryItem ai = new AccessoryItem();

    public String[] getUpgradeList(int code, int upgrade) {
        List<String> upgradeList = new ArrayList<>();
        switch (code) {
            case 1 -> {
                upgradeList.add("§b○§f 네더의 별 x"+(2));
                upgradeList.add("§b○§f 전달체 x"+(2));
                upgradeList.add("§b○§f 석탄 블록 x"+(128+upgrade*64));
            }
            case 2 -> {
                upgradeList.add("§b○§f 황금 사과 x"+(64+upgrade*32));
                upgradeList.add("§b○§f 꿀 블록 x"+(32+upgrade*16));
            }
            case 3 -> {
                upgradeList.add("§b○§f 네더라이트 주괴 x"+(20+10*upgrade));
                upgradeList.add("§b○§f 금 블록 x"+(80+upgrade*40));
            }
            case 4 -> {
                upgradeList.add("§b○§f 바다 랜턴 x"+(64+40*upgrade));
                upgradeList.add("§b○§f 다이아몬드 x"+(100+upgrade*50));
                upgradeList.add("§b○§f 푸른 얼음 x"+(96+upgrade*64));
            }
            case 5 -> {
                upgradeList.add("§b○§f 리스폰 정박기 x"+(32+32*upgrade));
                upgradeList.add("§b○§f 네더라이트 블록 x"+(4+upgrade));
            }
            case 6 -> {
                upgradeList.add("§b○§f 자수정 블록 x"+(256+256*upgrade));
                upgradeList.add("§b○§f 네더라이트 블록 x"+(3+ upgrade));
            }
            case 7 -> {
                upgradeList.add("§b○§f 에메랄드 광석 x"+(21+11*upgrade));
                upgradeList.add("§b○§f 다이아몬드 블록 x"+(30+20*upgrade));
                upgradeList.add("§b○§f 빛나는 먹물 주머니 x"+(64+32*upgrade));
            }
            case 8 -> {
                upgradeList.add("§b○§f 엔드 막대기 x"+(64+64*upgrade));
                upgradeList.add("§b○§f 금 블록 x"+(80+40*upgrade));
            }
            case 9 -> {
                upgradeList.add("§b○§f 마그마 크림 x"+(100+50*upgrade));
                upgradeList.add("§b○§f PIGSTEP 음반 x"+(1));
            }
            case 10 -> {
                upgradeList.add("§b○§f 빨간색 염료 x"+(20));
                upgradeList.add("§b○§f 용의 숨결 x"+(64));
                upgradeList.add("§b○§f 안산암 x"+(20));
            }
            case 11 -> {
                upgradeList.add("§b○§f 네더의 별 x"+(2));
                upgradeList.add("§b○§f 금 블록 x"+(120+60*upgrade));
                upgradeList.add("§b○§f 다이아몬드 x"+(350+130*upgrade));
                upgradeList.add("§b○§f 가스트의 눈물 x"+(48+16*upgrade));
            }
            case 12 -> {
                upgradeList.add("§b○§f 경험치 병 x"+(4+upgrade));
                upgradeList.add("§b○§f 네더라이트 주괴 x"+(12+upgrade*4));
                upgradeList.add("§b○§f 다이아몬드 x"+(150+upgrade*50));
                upgradeList.add("§b○§f 금 원석 x"+(385+64*upgrade));
            }
            case 13 -> {
                upgradeList.add("§b○§f 팬텀 막 x"+(32));
                upgradeList.add("§b○§f 뼈 블록 x"+(640+120*upgrade));
                upgradeList.add("§b○§f 심층암 에메랄드 광석 x"+(1));
            }
        }
        upgradeList.add("§e");
        if (upgrade==0) {
            upgradeList.add("§e클릭해서 제작하기");
        }
        else{
            upgradeList.add("§e클릭해서 강화하기");
        }

        return upgradeList.toArray(new String[upgradeList.size()]);
    }

    public void upgradeAccessory(int code, int upgrade, Player player) {
        List<ItemStack> checkListItem = new ArrayList<>();
        List<Integer> checkListAmount = new ArrayList<>();
        int count=0;
        switch (code) {
            case 1 -> {
                checkListItem.add(new ItemStack(Material.NETHER_STAR));
                checkListItem.add(new ItemStack(Material.CONDUIT));
                checkListItem.add(new ItemStack(Material.COAL_BLOCK));
                checkListAmount.add(2);
                checkListAmount.add(2);
                checkListAmount.add(128+upgrade*64);
            }
            case 2 -> {
                checkListItem.add(new ItemStack(Material.GOLDEN_APPLE));
                checkListItem.add(new ItemStack(Material.HONEY_BLOCK));
                checkListAmount.add(64+upgrade*32);
                checkListAmount.add(32+upgrade*16);
            }
            case 3 -> {
                checkListItem.add(new ItemStack(Material.NETHERITE_INGOT));
                checkListItem.add(new ItemStack(Material.GOLD_BLOCK));
                checkListAmount.add(20+upgrade*10);
                checkListAmount.add(80+upgrade*40);
            }
            case 4 -> {
                checkListItem.add(new ItemStack(Material.SEA_LANTERN));
                checkListItem.add(new ItemStack(Material.DIAMOND));
                checkListItem.add(new ItemStack(Material.BLUE_ICE));
                checkListAmount.add(64+upgrade*40);
                checkListAmount.add(100+upgrade*50);
                checkListAmount.add(96+upgrade*64);
            }
            case 5 -> {
                checkListItem.add(new ItemStack(Material.RESPAWN_ANCHOR));
                checkListItem.add(new ItemStack(Material.NETHERITE_BLOCK));
                checkListAmount.add(32+upgrade*32);
                checkListAmount.add(4+upgrade);
            }
            case 6 -> {
                checkListItem.add(new ItemStack(Material.NETHERITE_BLOCK));
                checkListItem.add(new ItemStack(Material.AMETHYST_BLOCK));
                checkListAmount.add(3+upgrade);
                checkListAmount.add(256+upgrade*256);
            }
            case 7 -> {
                checkListItem.add(new ItemStack(Material.EMERALD_ORE));
                checkListItem.add(new ItemStack(Material.DIAMOND_BLOCK));
                checkListItem.add(new ItemStack(Material.GLOW_INK_SAC));
                checkListAmount.add(21+upgrade*11);
                checkListAmount.add(30+upgrade*20);
                checkListAmount.add(64+upgrade*32);
            }
            case 8 -> {
                checkListItem.add(new ItemStack(Material.END_ROD));
                checkListItem.add(new ItemStack(Material.GOLD_BLOCK));
                checkListAmount.add(64+upgrade*64);
                checkListAmount.add(80+upgrade*40);
            }
            case 9 -> {
                checkListItem.add(new ItemStack(Material.MAGMA_CREAM));
                checkListItem.add(new ItemStack(Material.MUSIC_DISC_PIGSTEP));
                checkListAmount.add(100+upgrade*50);
                checkListAmount.add(1);
            }
            case 10 -> {
                checkListItem.add(new ItemStack(Material.RED_DYE));
                checkListItem.add(new ItemStack(Material.ANDESITE));
                checkListItem.add(new ItemStack(Material.DRAGON_BREATH));
                checkListAmount.add(20);
                checkListAmount.add(20);
                checkListAmount.add(64);
            }
            case 11 -> {
                checkListItem.add(new ItemStack(Material.NETHER_STAR));
                checkListItem.add(new ItemStack(Material.GOLD_BLOCK));
                checkListItem.add(new ItemStack(Material.DIAMOND));
                checkListItem.add(new ItemStack(Material.GHAST_TEAR));
                checkListAmount.add(2);
                checkListAmount.add(120+upgrade*60);
                checkListAmount.add(350+upgrade*130);
                checkListAmount.add(48+upgrade*16);
            }
            case 12 -> {
                checkListItem.add(new ItemStack(Material.EXPERIENCE_BOTTLE));
                checkListItem.add(new ItemStack(Material.NETHERITE_INGOT));
                checkListItem.add(new ItemStack(Material.DIAMOND));
                checkListItem.add(new ItemStack(Material.RAW_GOLD));
                checkListAmount.add(4+upgrade);
                checkListAmount.add(12+upgrade*4);
                checkListAmount.add(150+upgrade*50);
                checkListAmount.add(384+upgrade*64);
            }
            case 13 -> {
                checkListItem.add(new ItemStack(Material.PHANTOM_MEMBRANE));
                checkListItem.add(new ItemStack(Material.BONE_BLOCK));
                checkListItem.add(new ItemStack(Material.DEEPSLATE_EMERALD_ORE));
                checkListAmount.add(32);
                checkListAmount.add(640+upgrade*120);
                checkListAmount.add(1);
            }
        }
        for (int i=0;i<checkListItem.size();i++){
            if (!(player.getInventory().containsAtLeast(checkListItem.get(i),checkListAmount.get(i)))){
                player.sendMessage("§c[!] 재료가 부족합니다: §7"+checkListItem.get(i).getType());
            }
            else{
                count++;
            }
        }
        if (count==checkListItem.size()){
            for (int i=0;i<checkListItem.size();i++){
                player.getInventory().removeItem(new ItemStack(checkListItem.get(i).getType(),checkListAmount.get(i)));
            }
            player.closeInventory();
            PlayerDataStorage.accessory.put(player.getName()+code,upgrade+1);
            Bukkit.broadcastMessage("§a✦ "+player.getName()+"§f 님이 "+Objects.requireNonNull(ai.createAccessory(player,code, upgrade + 1).getItemMeta()).getDisplayName()+"§f을(를) 제작하였습니다!");
            player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST,1,1);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING,1,2);
        }
    }
}
