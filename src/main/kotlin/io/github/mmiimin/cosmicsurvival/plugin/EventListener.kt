package io.github.mmiimin.cosmicsurvival.plugin

import io.github.mmiimin.cosmicsurvival.LevelHandler
import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage
import net.coreprotect.CoreProtect
import net.coreprotect.CoreProtectAPI
import org.bukkit.Bukkit
import org.bukkit.Bukkit.getServer
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.attribute.Attribute
import org.bukkit.block.data.Ageable
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.*
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.Plugin
import kotlin.math.pow


class EventListener: Listener{
    private var lh = LevelHandler()
    private var pds = PlayerDataStorage()

    private fun getCoreProtect(): CoreProtectAPI? {
        val plugin: Plugin? = getServer().pluginManager.getPlugin("CoreProtect")

        // Check that CoreProtect is loaded
        if (plugin == null || plugin !is CoreProtect) {
            return null
        }

        // Check that the API is enabled
        val CoreProtect = plugin.api
        if (!CoreProtect.isEnabled) {
            return null
        }

        // Check that a compatible version of the API is loaded
        return if (CoreProtect.APIVersion() < 7) {
            null
        } else CoreProtect
    }

    @EventHandler
    fun onKill(event: EntityDeathEvent) {
        val attacker = event.entity.killer
        val victim = event.entity
        if (attacker is Player) {
            when (victim) {
                is Zombie -> {lh.addExp(attacker,1,75)}
                is Skeleton -> {lh.addExp(attacker,1,90)}
                is Creeper -> {lh.addExp(attacker,1,128)}
                is Spider -> {lh.addExp(attacker,1,110)}
                is Enderman -> { if (attacker.world.name != "world_the_end") {lh.addExp(attacker,1,225)}}
                is CaveSpider -> {lh.addExp(attacker,1,143)}
                is WitherSkeleton -> {lh.addExp(attacker,1,200)}
                is Blaze -> {lh.addExp(attacker,1,240)}
                is ZombieVillager -> {lh.addExp(attacker,1,75)}
                is Zoglin -> {lh.addExp(attacker,1,340)}
                is Phantom -> {lh.addExp(attacker,1,455)}
                is Hoglin -> {lh.addExp(attacker,1,320)}
                is PigZombie -> {lh.addExp(attacker,1,80)}
                is Piglin -> {lh.addExp(attacker,1,185)}
                is PiglinBrute -> {lh.addExp(attacker,1,245)}
                is Evoker -> {lh.addExp(attacker,1,300)}
                is Vex -> {lh.addExp(attacker,1,195)}
                is Drowned -> {lh.addExp(attacker,1,70)}
                is Ghast -> {lh.addExp(attacker,1,285)}
                is Husk -> {lh.addExp(attacker,1,85)}
                is Slime -> {lh.addExp(attacker,1,50)}
                is MagmaCube -> {lh.addExp(attacker,1,52)}
                is Ravager -> {lh.addExp(attacker,1,500)}
                is Wither -> {lh.addExp(attacker,1,999)}
                is Illusioner -> {lh.addExp(attacker,1,307)}
                is Stray -> {lh.addExp(attacker,1,100)}
                is Vindicator -> {lh.addExp(attacker,1,298)}
                is Bat -> {lh.addExp(attacker,1,50)}
                is Witch -> {lh.addExp(attacker,1,180)}
                is Shulker -> {lh.addExp(attacker,1,288)}
                is Silverfish -> {lh.addExp(attacker,1,102)}

                is Salmon -> {lh.addExp(attacker,5,80)}
                is Cod -> {lh.addExp(attacker,5,70)}
                is PufferFish -> {lh.addExp(attacker,5,120)}
                is Squid -> {lh.addExp(attacker,5,100)}
                is TropicalFish -> {lh.addExp(attacker,5,180)}
            }
        }
    }

    @EventHandler
    fun onBreak(event: BlockBreakEvent) {
        val CoreProtect: CoreProtectAPI = getCoreProtect()!!

        val blo = event.block.type
        if (CoreProtect.blockLookup(event.block,2147000000).size == 0) {
            when (blo) {

                Material.STONE -> { lh.addExp(event.player,2,5) }
                Material.DIORITE -> { lh.addExp(event.player,2,5) }
                Material.ANDESITE -> { lh.addExp(event.player,2,5) }
                Material.GRANITE -> { lh.addExp(event.player,2,5) }
                Material.DEEPSLATE -> { lh.addExp(event.player,2,6) }
                Material.CALCITE -> { lh.addExp(event.player,2,8) }
                Material.TUFF -> { lh.addExp(event.player,2,7) }
                Material.OBSIDIAN -> { lh.addExp(event.player,2,10) }
                Material.COBBLED_DEEPSLATE -> { lh.addExp(event.player,2,6) }
                Material.IRON_ORE -> { lh.addExp(event.player,2,65) }
                Material.DEEPSLATE_IRON_ORE -> { lh.addExp(event.player,2,72) }
                Material.COAL_ORE -> { lh.addExp(event.player,2,45) }
                Material.DEEPSLATE_COAL_ORE -> { lh.addExp(event.player,2,50) }
                Material.GOLD_ORE -> { lh.addExp(event.player,2,450) }
                Material.DEEPSLATE_GOLD_ORE -> { lh.addExp(event.player,2,495) }
                Material.LAPIS_ORE -> { lh.addExp(event.player,2,340) }
                Material.DEEPSLATE_LAPIS_ORE -> { lh.addExp(event.player,2,374) }
                Material.REDSTONE_ORE -> { lh.addExp(event.player,2,350) }
                Material.DEEPSLATE_REDSTONE_ORE -> { lh.addExp(event.player,2,385) }
                Material.COPPER_ORE -> { lh.addExp(event.player,2,15) }
                Material.DEEPSLATE_COPPER_ORE -> { lh.addExp(event.player,2,18) }
                Material.DIAMOND_ORE -> { lh.addExp(event.player,2,2000) }
                Material.DEEPSLATE_DIAMOND_ORE -> { lh.addExp(event.player,2,2200) }
                Material.EMERALD_ORE -> { lh.addExp(event.player,2,5000) }
                Material.DEEPSLATE_EMERALD_ORE -> { lh.addExp(event.player,2,5000) }
                Material.ANCIENT_DEBRIS -> { lh.addExp(event.player,2,5000) }
                Material.NETHER_QUARTZ_ORE -> { lh.addExp(event.player,2,60) }
                Material.NETHER_GOLD_ORE -> { lh.addExp(event.player,2,37) }
                Material.AMETHYST_BLOCK -> { lh.addExp(event.player,2,13) }
                Material.AMETHYST_SHARD -> { lh.addExp(event.player,2,333) }

                Material.SPAWNER -> { lh.addExp(event.player,1,2000) }

                Material.ACACIA_LOG -> { lh.addExp(event.player,3,32) }
                Material.JUNGLE_LOG -> { lh.addExp(event.player,3,32) }
                Material.OAK_LOG -> { lh.addExp(event.player,3,32) }
                Material.DARK_OAK_LOG -> { lh.addExp(event.player,3,32) }
                Material.SPRUCE_LOG -> { lh.addExp(event.player,3,32) }
                Material.BIRCH_LOG -> { lh.addExp(event.player,3,32) }
                Material.STRIPPED_SPRUCE_LOG -> { lh.addExp(event.player,3,32) }
                Material.STRIPPED_ACACIA_LOG -> { lh.addExp(event.player,3,32) }
                Material.STRIPPED_BIRCH_LOG -> { lh.addExp(event.player,3,32) }
                Material.STRIPPED_DARK_OAK_LOG -> { lh.addExp(event.player,3,32) }
                Material.STRIPPED_OAK_LOG -> { lh.addExp(event.player,3,32) }
                Material.STRIPPED_JUNGLE_LOG -> { lh.addExp(event.player,3,32) }
                Material.CRIMSON_STEM -> { lh.addExp(event.player,3,32) }
                Material.STRIPPED_CRIMSON_STEM -> { lh.addExp(event.player,3,32) }
                Material.WARPED_STEM -> { lh.addExp(event.player,3,32) }
                Material.STRIPPED_WARPED_STEM -> { lh.addExp(event.player,3,32) }

                Material.PUMPKIN -> { lh.addExp(event.player,4,35) }
                Material.MELON -> { lh.addExp(event.player,4,35) }
                Material.WHEAT -> {
                    val ageable: Ageable = event.block.blockData as Ageable
                    if (ageable.age == ageable.maximumAge) {
                        lh.addExp(event.player,4,75)
                    }
                }
                Material.CARROTS -> {
                    val ageable: Ageable = event.block.blockData as Ageable
                    if (ageable.age == ageable.maximumAge) {
                        lh.addExp(event.player, 4, 50)
                    }
                }
                Material.POTATOES -> {
                    val ageable: Ageable = event.block.blockData as Ageable
                    if (ageable.age == ageable.maximumAge) {
                        lh.addExp(event.player, 4, 50)
                    }
                }
                Material.NETHER_WART -> {
                    val ageable: Ageable = event.block.blockData as Ageable
                    if (ageable.age == ageable.maximumAge) {
                        lh.addExp(event.player, 4, 55)
                    }
                }
                Material.SUGAR_CANE -> {
                    var baseheight = event.block.location.blockY.toDouble()
                    var i = 1
                    while (baseheight < 255) {
                        baseheight++
                        val loc = Location(
                            event.block.world,
                            event.block.location.blockX.toDouble(),
                            baseheight,
                            event.block.location.blockZ.toDouble()
                        )
                        if (loc.block.type == Material.SUGAR_CANE) {
                            if (CoreProtect.blockLookup(loc.block, 150).size == 0) { i++ }
                        }
                        else{ break }
                    }
                    lh.addExp(event.player, 4, 30*i)
                }

                else -> {}
            }
        }
        else if (CoreProtect.blockLookup(event.block,120).size == 0) {
            when (blo) {
                Material.ACACIA_LOG -> { lh.addExp(event.player,3,32) }
                Material.JUNGLE_LOG -> { lh.addExp(event.player,3,32) }
                Material.OAK_LOG -> { lh.addExp(event.player,3,32) }
                Material.DARK_OAK_LOG -> { lh.addExp(event.player,3,32) }
                Material.SPRUCE_LOG -> { lh.addExp(event.player,3,32) }
                Material.BIRCH_LOG -> { lh.addExp(event.player,3,32) }
                Material.STRIPPED_SPRUCE_LOG -> { lh.addExp(event.player,3,32) }
                Material.STRIPPED_ACACIA_LOG -> { lh.addExp(event.player,3,32) }
                Material.STRIPPED_BIRCH_LOG -> { lh.addExp(event.player,3,32) }
                Material.STRIPPED_DARK_OAK_LOG -> { lh.addExp(event.player,3,32) }
                Material.STRIPPED_OAK_LOG -> { lh.addExp(event.player,3,32) }
                Material.STRIPPED_JUNGLE_LOG -> { lh.addExp(event.player,3,32) }
                Material.CRIMSON_STEM -> { lh.addExp(event.player,3,32) }
                Material.STRIPPED_CRIMSON_STEM -> { lh.addExp(event.player,3,32) }
                Material.WARPED_STEM -> { lh.addExp(event.player,3,32) }
                Material.STRIPPED_WARPED_STEM -> { lh.addExp(event.player,3,32) }

                Material.PUMPKIN -> { lh.addExp(event.player,4,35) }
                Material.MELON -> { lh.addExp(event.player,4,35) }
                Material.WHEAT -> {
                    val ageable: Ageable = event.block.blockData as Ageable
                    if (ageable.age == ageable.maximumAge) {
                        lh.addExp(event.player,4,75)
                    }
                }
                Material.CARROTS -> {
                    val ageable: Ageable = event.block.blockData as Ageable
                    if (ageable.age == ageable.maximumAge) {
                        lh.addExp(event.player, 4, 50)
                    }
                }
                Material.POTATOES -> {
                    val ageable: Ageable = event.block.blockData as Ageable
                    if (ageable.age == ageable.maximumAge) {
                        lh.addExp(event.player, 4, 50)
                    }
                }
                Material.NETHER_WART -> {
                    val ageable: Ageable = event.block.blockData as Ageable
                    if (ageable.age == ageable.maximumAge) {
                        lh.addExp(event.player, 4, 55)
                    }
                }
                Material.SUGAR_CANE -> {
                    var baseheight = event.block.location.blockY.toDouble()
                    var i = 1
                    while (baseheight < 255) {
                        baseheight++
                        val loc = Location(
                            event.block.world,
                            event.block.location.blockX.toDouble(),
                            baseheight,
                            event.block.location.blockZ.toDouble()
                        )
                        if (loc.block.type == Material.SUGAR_CANE) {
                            if (CoreProtect.blockLookup(loc.block, 150).size == 0) { i++ }
                        }
                        else{ break }
                    }
                    lh.addExp(event.player, 4, 30*i)
                }


                else -> {}
            }

        }

    }

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {

        var message = event.message

        message = message.replace(":yellow_square:", "§e⬛§f")
        message = message.replace(":red_square:", "§c⬛§f")
        for(player in Bukkit.getOnlinePlayers()) {
            if (message.contains("@"+player.name)) {
                player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F)
                message = message.replace(
                    "@" + player.name.toRegex(),
                    "§6@" + player.name.toRegex()+"§f")

            }
        }
        event.message = message

        event.format = "%s: %s"
    }

    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        event.deathMessage = "§c☠ §7" + event.deathMessage
        if (event.entity.killer != null) {
            if (event.entity.killer is Player) {
                event.deathMessage = "§c⚔ §7" + event.deathMessage
            }
        }
    }

    @EventHandler
    fun onDamagedByEnvironment(event: EntityDamageEvent) {
        val victim = event.entity
        if (event.cause != EntityDamageEvent.DamageCause.ENTITY_ATTACK && event.cause != EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK) {
            if (victim is Player) {
                event.damage = event.damage* (0.9993.pow(PlayerDataStorage.map[victim.name + "statsDEF"]!!.toDouble()))
                if (PlayerDataStorage.map[victim.name + "settingDI"] == 1) {
                    val indicate: String = when (event.cause) {
                        EntityDamageEvent.DamageCause.FIRE -> "불"
                        EntityDamageEvent.DamageCause.FIRE_TICK -> "불"
                        EntityDamageEvent.DamageCause.BLOCK_EXPLOSION -> "폭발"
                        EntityDamageEvent.DamageCause.ENTITY_EXPLOSION -> "폭발"
                        EntityDamageEvent.DamageCause.CONTACT -> "찔림"
                        EntityDamageEvent.DamageCause.CRAMMING -> "끼임 (엔티티 과다)"
                        EntityDamageEvent.DamageCause.FREEZE -> "동상"
                        EntityDamageEvent.DamageCause.FALLING_BLOCK -> "낙하물"
                        EntityDamageEvent.DamageCause.FALL -> "낙하"
                        EntityDamageEvent.DamageCause.FLY_INTO_WALL -> "운동 에너지"
                        EntityDamageEvent.DamageCause.LAVA -> "용암"
                        EntityDamageEvent.DamageCause.LIGHTNING -> "번개"
                        EntityDamageEvent.DamageCause.MAGIC -> "마법"
                        EntityDamageEvent.DamageCause.POISON -> "독"
                        EntityDamageEvent.DamageCause.STARVATION -> "굶주림"
                        EntityDamageEvent.DamageCause.SUFFOCATION -> "끼임"
                        EntityDamageEvent.DamageCause.WITHER -> "시듦"
                        EntityDamageEvent.DamageCause.HOT_FLOOR -> "뜨거운 바닥"
                        EntityDamageEvent.DamageCause.DROWNING -> "산소 부족"
                        EntityDamageEvent.DamageCause.VOID -> "공허"

                        else -> event.cause.toString()
                    }
                    victim.sendMessage("§c➜ §7"+indicate+"에 의해 §c"+String.format("%.2f",event.finalDamage) +"§7 피해를 받았다.")
                }
            }
        }
    }

    @EventHandler
    fun onDamaged(event: EntityDamageByEntityEvent) {
        val victim = event.entity
        val attacker = event.damager
        if (victim is Player) {
            event.damage = event.damage* (0.9993.pow(PlayerDataStorage.map[victim.name + "statsDEF"]!!.toDouble()))
            if (attacker is Player) {
                event.damage = event.damage* (1+(PlayerDataStorage.map[attacker.name + "statsSTR"]!!.toDouble()*0.001) )
            }
            if (PlayerDataStorage.map[victim.name + "settingDI"] == 1) {
                victim.sendMessage("§c➜ §7" + attacker.name + "에게 §c" + String.format("%.2f",event.finalDamage) + "§7 피해를 받았다.")
            }
            if (PlayerDataStorage.map[attacker.name + "settingDI"] == 1) {
                attacker.sendMessage("§a➜ §7" + victim.name + "에게 §a" + String.format("%.2f",event.finalDamage)  + "§7 피해를 주었다.")
            }
        }
        else if (attacker is Player) {
            event.damage = event.damage* (1+(PlayerDataStorage.map[attacker.name + "statsSTR"]!!.toDouble()*0.001) )
            if (PlayerDataStorage.map[attacker.name + "settingDI"] == 1) {
                attacker.sendMessage("§a➜ §7" + victim.name + "에게 §a" + String.format("%.2f",event.finalDamage)  + "§7 피해를 주었다.")
            }
        }

    }

    @EventHandler
    fun onHungerChanged(event: FoodLevelChangeEvent) {
        val player = event.entity as Player
        if (player.foodLevel > event.foodLevel){
            val srvRate = Math.random()
            if (srvRate > (0.9975.pow(PlayerDataStorage.map[player.name + "statsSRV"]!!.toDouble()))){
                event.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onFishing(event: PlayerFishEvent) {
        val fishingRate = Math.random()
        val weight = Math.random()

        if (fishingRate > 0.8) {
            if (weight < 0.2) {

            }
            else if (weight < 0.4) {

            }
        }
    }

}