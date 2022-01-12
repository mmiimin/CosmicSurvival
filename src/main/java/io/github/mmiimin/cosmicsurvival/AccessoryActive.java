package io.github.mmiimin.cosmicsurvival;

import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage;
import io.github.mmiimin.cosmicsurvival.util.WandItem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AccessoryActive {
    WandItem wandItem = new WandItem();
    public void aaActive(Player player, int slot) {
        if (player.getCooldown(wandItem.getWandMaterial(PlayerDataStorage.map.get(player.getName() + "wand" + slot))) == 0) {

            int cooldown = 0;
            int accLevel = PlayerDataStorage.accessory.get(player.getName()+PlayerDataStorage.map.get(player.getName() + "accessory" + slot));
            if (accLevel > 0) {
                switch (PlayerDataStorage.map.get(player.getName() + "accessory" + slot)) {
                    case 1 -> {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 10 + ((accLevel - 1) * 50), accLevel + 1));
                        player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK,player.getLocation().add(0,1,0),50,0.5,0.5,0.5,0.5);
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE,1F,2F);
                        cooldown = 70 - accLevel * 10;
                    }
                    case 2 -> {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20 * 2, accLevel - 1));
                        player.getWorld().spawnParticle(Particle.ITEM_CRACK, player.getLocation().add(0,1,0), 10, 0.3, 0.5, 0.3, 0.2, new ItemStack(Material.GOLD_BLOCK));
                        player.playSound(player.getLocation(), Sound.BLOCK_BELL_USE,1F,1F);
                        cooldown = 23 - accLevel * 3;
                    }
                    case 7 -> {
                        player.setVelocity(player.getLocation().getDirection().multiply(1.5D).setY(0.9D));
                        player.getWorld().spawnParticle(Particle.SWEEP_ATTACK,player.getLocation().add(0,1,0),5,0.2,0.2,0.2,0.0);
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP,1F,1F);
                        cooldown = 20 - accLevel * 5;
                    }
                    case 9 -> {
                        player.getWorld().spawnParticle(Particle.FLAME,player.getLocation().add(0,1,0),5,0.2,0.2,0.2,0.0);
                        player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT,1F,1F);
                        cooldown = 56 - accLevel * 2;
                    }
                }
                player.setCooldown(wandItem.getWandMaterial(PlayerDataStorage.map.get(player.getName() + "wand" + slot)), 20 * cooldown);
            }
        }
        else{
            int cooldown=player.getCooldown(wandItem.getWandMaterial(PlayerDataStorage.map.get(player.getName() + "wand" + slot)));
            String message = "§e재사용 가능까지 §b"+cooldown/20.0+"초§e 남았습니다";
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
        }
    }
}
