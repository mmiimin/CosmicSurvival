package io.github.mmiimin.cosmicsurvival;

import io.github.mmiimin.cosmicsurvival.plugin.DamageEvent;
import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage;
import io.github.mmiimin.cosmicsurvival.util.WandItem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Objects;

import static io.github.mmiimin.cosmicsurvival.plugin.CosmicSurvival.plugin;

public class AccessoryActive {
    WandItem wandItem = new WandItem();
    DamageEvent damageEvent = new DamageEvent();
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
                        cooldown = 20 * (70 - accLevel * 10);
                    }
                    case 2 -> {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, accLevel*6, 3));
                        player.getWorld().spawnParticle(Particle.ITEM_CRACK, player.getLocation().add(0,1,0), 10, 0.3, 0.5, 0.3, 0.2, new ItemStack(Material.GOLD_BLOCK));
                        player.playSound(player.getLocation(), Sound.BLOCK_BELL_USE,1F,1F);
                        cooldown = 20 * (23 - accLevel * 3);
                    }
                    case 7 -> {
                        player.setVelocity(player.getLocation().getDirection().multiply(1.7D).setY(0.75D));
                        player.getWorld().spawnParticle(Particle.SWEEP_ATTACK,player.getLocation().add(0,1,0),5,0.2,0.2,0.2,0.0);
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP,1F,1F);
                        cooldown = 20 * (20 - accLevel * 5);
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
                                            ((LivingEntity) ett).addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 2, 128));
                                            ((LivingEntity) ett).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 2, 2));
                                        }, 20);
                                    }
                                }
                            }
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 5+accLevel, 0));
                            player.playSound(player.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE,1.5F,0.7F);
                        }, 20);


                        cooldown = 20 * ( 34 - accLevel * 4);
                    }
                    case 10 -> {
                        ItemStack potion = new ItemStack(Material.LINGERING_POTION);
                        PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();
                        assert potionMeta != null;
                        potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0), false);
                        potion.setItemMeta(potionMeta);

                        ThrownPotion thrownPotion = player.launchProjectile(ThrownPotion.class);
                        thrownPotion.setItem(potion);

                        cooldown = 20 * (31 - accLevel*5);
                    }
                    case 11 -> {
                        player.playSound(player.getLocation(),Sound.BLOCK_NOTE_BLOCK_BELL,1.0F,1.98F);

                        Location start = player.getEyeLocation();
                        Vector direction = start.getDirection();
                        start = start.clone().add(direction.clone().multiply(2));
                        int distance = 50;
                        RayTraceResult hitResult = player.getWorld().rayTrace(start, direction, distance, FluidCollisionMode.NEVER, true,0.15,null);
                        Location end = start.clone().add(direction.clone().multiply(distance));
                        if (hitResult != null) {
                            end = hitResult.getHitPosition().toLocation(player.getWorld());

                            Entity target = hitResult.getHitEntity();
                            if (target instanceof LivingEntity) {
                                double damage = accLevel * 4.0 + 4;
                                ((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20 * 2, 0));
                                ((LivingEntity) target).damage(damage,player);
                                target.getWorld().spawnParticle(Particle.END_ROD, target.getLocation(), 20, 0.5, 0.5, 0.5, 0.05);
                                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.5F, 1.5F);
                            }
                        }
                        Vector vector = new Vector(end.getX() - start.getX(), end.getY() - start.getY(), end.getZ() - start.getZ());
                        int count = 125;
                        for (int i=0;i<count;i++){
                            player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, start.getX()+vector.getX()*0.04*i,start.getY()+vector.getY()*0.04*i,start.getZ()+vector.getZ()*0.04*i,1,0.0,0.0,0.0,0.0);
                        }

                        cooldown = 10;
                    }
                }
                player.setCooldown(wandItem.getWandMaterial(PlayerDataStorage.map.get(player.getName() + "wand" + slot)), cooldown);
            }
        }
        else{
            int cooldown=player.getCooldown(wandItem.getWandMaterial(PlayerDataStorage.map.get(player.getName() + "wand" + slot)));
            String message = "§e재사용 가능까지 §b"+cooldown/20.0+"초§e 남았습니다";
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
        }
    }
}
