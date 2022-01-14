package io.github.mmiimin.cosmicsurvival;

import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage;
import io.github.mmiimin.cosmicsurvival.util.WandItem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Objects;

import static io.github.mmiimin.cosmicsurvival.plugin.CosmicSurvival.plugin;

public class AccessoryActive {
    WandItem wandItem = new WandItem();
    public void aaActive(Player player, int slot) {
        if (player.getCooldown(wandItem.getWandMaterial(PlayerDataStorage.map.get(player.getName() + "wand" + slot))) == 0) {

            int cooldown = 0;
            int accLevel = PlayerDataStorage.accessory.get(player.getName()+PlayerDataStorage.map.get(player.getName() + "accessory" + slot));
            if (accLevel > 0) {
                switch (PlayerDataStorage.map.get(player.getName() + "accessory" + slot)) {
                    case 1 -> {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 10 + ((accLevel - 1) * 50), accLevel*2 + 5));
                        player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK,player.getLocation().add(0,1,0),50,0.5,0.5,0.5,0.5);
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE,1F,2F);
                        cooldown = 70 - accLevel * 10;
                    }
                    case 2 -> {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Math.min(accLevel*6,10), Math.max(accLevel - 1,1)));
                        player.getWorld().spawnParticle(Particle.ITEM_CRACK, player.getLocation().add(0,1,0), 10, 0.3, 0.5, 0.3, 0.2, new ItemStack(Material.GOLD_BLOCK));
                        player.playSound(player.getLocation(), Sound.BLOCK_BELL_USE,1F,1F);
                        cooldown = 23 - accLevel * 3;
                    }
                    case 7 -> {
                        player.setVelocity(player.getLocation().getDirection().multiply(1.7D).setY(0.5D));
                        player.getWorld().spawnParticle(Particle.SWEEP_ATTACK,player.getLocation().add(0,1,0),5,0.2,0.2,0.2,0.0);
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP,1F,1F);
                        cooldown = 20 - accLevel * 5;
                    }
                    case 9 -> {
                        int radius=accLevel+3;
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 19, 4));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 19, 253));
                        player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT,1.5F,1F);
                        player.getWorld().spawnParticle(Particle.FLAME, player.getLocation().add(0, 1, 0), 150, 3.0, 3.0, 3.0, 0.5);

                        for(int i=0;i<360;i++) { //  2*pi / 360 = 57.324
                            double x = (radius * Math.sin(i/57.324));
                            double z = (radius * Math.cos(i/57.324));
                            player.getWorld().spawnParticle(Particle.FLAME, player.getLocation().add(x, 1, z), 1, 0.0, 0.0, 0.0, 0.0);
                        }
                        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                            List<Entity> entities = (List<Entity>) Objects.requireNonNull(player.getNearbyEntities(radius,9,radius));
                            for(int i=0;i<360;i++) { //  2*pi / 360 = 57.324
                                double x = (radius * Math.sin(i/57.324));
                                double z = (radius * Math.cos(i/57.324));
                                player.getWorld().spawnParticle(Particle.FLAME, player.getLocation().add(x, 1, z), 5, 0.0, 0.0, 0.0, 0.0);
                            }
                            for (int i = 0; i < 1800; i++) {
                                double x = (i/1800.0*radius * Math.sin(i / 6.28));
                                double z = (i/1800.0*radius * Math.cos(i / 6.28));
                                player.getWorld().spawnParticle(Particle.FLAME, player.getLocation().add(x, i/200.0, z), 1, 0.0, 0.0, 0.0, 0.0);
                            }

                            for (Entity ett : entities) {
                                if(ett instanceof LivingEntity) {
                                    if (!(ett instanceof Villager)) {
                                        ett.setFireTicks(20*10);
                                        ett.setVelocity(ett.getVelocity().setY(2.1D));

                                        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                                            ((LivingEntity) ett).addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 5, 128));
                                            ((LivingEntity) ett).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 2, 2));
                                        }, 20);
                                    }
                                }
                            }
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 5+accLevel, 0));
                            player.playSound(player.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE,1.5F,0.7F);
                        }, 20);


                        cooldown = 34 - accLevel * 4;
                    }
                    case 10 -> {
                        ItemStack itemStack = new ItemStack(Material.SPLASH_POTION);
                        PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();

                        potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 20 * 5, 0), true);

                        itemStack.setItemMeta(potionMeta);

                        ThrownPotion thrownPotion = (ThrownPotion) player.getWorld().spawnEntity(player.getLocation().add(player.getLocation().getDirection().multiply(5)), EntityType.SPLASH_POTION);
                        thrownPotion.setItem(itemStack);

                        cooldown = 11 - accLevel;
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
