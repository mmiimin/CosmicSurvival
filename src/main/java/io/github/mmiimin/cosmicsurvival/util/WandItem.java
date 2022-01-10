package io.github.mmiimin.cosmicsurvival.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import io.github.mmiimin.cosmicsurvival.util.ItemManager;

public class WandItem {
    ItemManager im = new ItemManager();
    public Material getWandMaterial(int wand){
        Material material;
        switch (wand) {
            case 0 -> material = Material.RED_DYE;
            case 1 -> material = Material.ORANGE_DYE;
            case 2 -> material = Material.YELLOW_DYE;
            case 3 -> material = Material.LIME_DYE;
            case 4 -> material = Material.GREEN_DYE;
            case 5 -> material = Material.LIGHT_BLUE_DYE;
            case 6 -> material = Material.BLUE_DYE;
            case 7 -> material = Material.CYAN_DYE;
            case 8 -> material = Material.PINK_DYE;
            case 9 -> material = Material.MAGENTA_DYE;
            case 10 -> material = Material.PURPLE_DYE;
            case 11 -> material = Material.BROWN_DYE;
            case 12 -> material = Material.BLACK_DYE;
            case 13 -> material = Material.GRAY_DYE;
            case 14 -> material = Material.LIGHT_GRAY_DYE;
            case 15 -> material = Material.WHITE_DYE;
            default -> material = Material.BARRIER;
        }
        return material;
    }
    public ItemStack getWand(int wand){
        Material material;
        String name;
        String lore;
        switch (wand) {
            case 0 -> {
                material = Material.RED_DYE;
                name = "§c빨간색 염료";
            }
            case 1 -> {
                material = Material.ORANGE_DYE;
                name = "§6주황색 염료";
            }
            case 2 -> {
                material = Material.YELLOW_DYE;
                name = "§e노란색 염료";
            }
            case 3 -> {
                material = Material.LIME_DYE;
                name = "§a연두색 염료";
            }
            case 4 -> {
                material = Material.GREEN_DYE;
                name = "§2초록색 염료";
            }
            case 5 -> {
                material = Material.LIGHT_BLUE_DYE;
                name = "§b하늘색 염료";
            }
            case 6 -> {
                material = Material.BLUE_DYE;
                name = "§1파란색 염료";
            }
            case 7 -> {
                material = Material.CYAN_DYE;
                name = "§3청록색 염료";
            }
            case 8 -> {
                material = Material.PINK_DYE;
                name = "§d분홍색 염료";
            }
            case 9 -> {
                material = Material.MAGENTA_DYE;
                name = ChatColor.of("#C566BF")+"자홍색 염료";
            }
            case 10 -> {
                material = Material.PURPLE_DYE;
                name = "§5보라색 염료";
            }
            case 11 -> {
                material = Material.BROWN_DYE;
                name = ChatColor.of("#945A31")+"갈색 염료";
            }
            case 12 -> {
                material = Material.BLACK_DYE;
                name = "§8검은색 염료";
            }
            case 13 -> {
                material = Material.GRAY_DYE;
                name = "§7회색 염료";
            }
            case 14 -> {
                material = Material.LIGHT_GRAY_DYE;
                name = ChatColor.of("#D2D2D2")+"밝은 회색 염료";
            }
            case 15 -> {
                material = Material.WHITE_DYE;
                name = "§f하얀색 염료";
            }
            default -> {
                material = Material.BARRIER;
                name = "§4ERROR";
            }
        }
        lore="§e클릭해서 변경하기";
        return im.createItem(material,name,"",lore);
    }
}
