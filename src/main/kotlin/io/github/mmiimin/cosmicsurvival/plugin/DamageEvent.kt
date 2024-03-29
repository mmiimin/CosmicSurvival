package io.github.mmiimin.cosmicsurvival.plugin

import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage
import org.bukkit.Color
import org.bukkit.Particle
import org.bukkit.Particle.DustOptions
import org.bukkit.attribute.Attribute
import org.bukkit.entity.*
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import kotlin.math.min
import kotlin.math.pow


class DamageEvent {

    fun damageCalculate(victim: Entity, attacker: Entity, oDamage: Double): Double {
        var damage=oDamage
        var distance = 0.0
        var health = 1.0
        var maxHealth = 1.0
        if (victim is LivingEntity) {
            maxHealth = victim.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
            health =victim.health
        }
        if (attacker is Player) {
            damage *= (1 + (PlayerDataStorage.map[attacker.name + "statsSTR"]!!.toDouble() * 0.002))
            for(i: Int in 1..3) {
                val accLv = PlayerDataStorage.accessory[attacker.name+(PlayerDataStorage.map[attacker.name + "accessory" + i])]!!
                when (PlayerDataStorage.map[attacker.name + "accessory" + i]) {
                    4->{
                        if (health==maxHealth) {
                            damage *= (1 + 0.35 * accLv)
                            for (j: Int in -10..10) {
                                victim.world.spawnParticle(Particle.VILLAGER_HAPPY, victim.location.x+j*0.1,victim.location.y+1+j*0.1,victim.location.z+j*0.1, 0, 0.2, 0.5, 1.0, 0.0)
                            }
                        }
                    }
                    9->{
                        if (victim.fireTicks > 0) {
                            damage *= (1.05 + 0.05 * accLv)
                            victim.world.spawnParticle(Particle.FLAME,victim.location.add(0.0,1.0,0.0), 10, 0.5, 0.5, 0.5, 0.7)
                        }
                    }
                }
            }
        }
        if (attacker is Arrow) {
            distance = (attacker.shooter as Entity).location.distance(victim.location)
            if (attacker.shooter is Player) {
                damage *= (1 + (PlayerDataStorage.map[(attacker.shooter as Player).name + "statsSTR"]!!.toDouble() * 0.002))
                for(i: Int in 1..3) {
                    val accLv = PlayerDataStorage.accessory[(attacker.shooter as Player).name+(PlayerDataStorage.map[(attacker.shooter as Player).name + "accessory" + i])]!!
                    when (PlayerDataStorage.map[(attacker.shooter as Player).name + "accessory" + i]) {
                        8->{
                            damage *= (1 + 0.1 * accLv)
                            if (distance>=25) {
                                damage *= (1 + 0.2 * accLv)
                            }
                        }
                        13->{

                            val entities = victim.getNearbyEntities(1.5, 1.5, 1.5)
                            victim.world.spawnParticle(Particle.EXPLOSION_HUGE,victim.location,1,0.0,0.0,0.0,0.0)
                            for (ett in entities) {
                                if (ett is LivingEntity) {
                                    if (ett !is Villager) { ett.damage(4.0*accLv, attacker.shooter as Player); }
                                }
                            }
                        }
                        14->{
                            if (victim is LivingEntity){
                                victim.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 40 + 20 * accLv, 2))
                            }
                        }
                    }
                }
            }
        }

        return damage
    }
    fun victimDamageCalculate(victim: Entity, oDamage: Double): Double {
        var damage=oDamage
        if (victim is Player) {
            damage *= (0.9985.pow(PlayerDataStorage.map[victim.name + "statsDEF"]!!.toDouble()))
            for(i: Int in 1..3) {
                val accLv = PlayerDataStorage.accessory[victim.name+PlayerDataStorage.map[victim.name + "accessory" + i]]!!
                when (PlayerDataStorage.map[victim.name + "accessory" + i]) {
                    3->{
                        if (victim.health <= 8) {
                            damage *= (0.85-accLv*0.1)
                        }
                    }
                    5->{
                        if (victim.isSneaking) {
                            damage *= (0.92-accLv*0.08)
                        }
                    }


                }
            }
        }
        return damage
    }

    fun execution(victim: Entity, attacker: Entity, fDamage: Double): Double {
        var damage = fDamage
        if (victim is LivingEntity) {
            if (attacker is Player){
                for(i: Int in 1..3) {
                    val accLv = PlayerDataStorage.accessory[attacker.name+PlayerDataStorage.map[attacker.name + "accessory" + i]]!!
                    when (PlayerDataStorage.map[attacker.name + "accessory" + i]) {
                        6->{
                            if (victim.health <= accLv*2+1) {
                                damage=200.0
                                victim.world.spawnParticle(Particle.REDSTONE,victim.location.add(0.0,1.0,0.0),50,0.3,0.3,0.3,
                                    DustOptions(Color.fromRGB(255, 0, 0), 1.0F))
                            }
                        }
                        12->{
                            val damageDrain = min(attacker.health+1.5,attacker.health + damage*(accLv*0.02))
                            attacker.health=min(attacker.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value,damageDrain)
                        }
                    }
                }
            }
        }
        return damage
    }
}