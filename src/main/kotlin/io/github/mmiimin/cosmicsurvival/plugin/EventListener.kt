package io.github.mmiimin.cosmicsurvival.plugin

import io.github.mmiimin.cosmicsurvival.AccessoryActive
import io.github.mmiimin.cosmicsurvival.AccessoryRefresh
import io.github.mmiimin.cosmicsurvival.LevelHandler
import io.github.mmiimin.cosmicsurvival.LevelStyleManager
import io.github.mmiimin.cosmicsurvival.util.ItemManager
import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage
import io.github.mmiimin.cosmicsurvival.util.WandItem
import net.coreprotect.CoreProtect
import net.coreprotect.CoreProtectAPI
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.*
import org.bukkit.Bukkit.getServer
import org.bukkit.attribute.Attribute
import org.bukkit.block.data.Ageable
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.*
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow


class EventListener: Listener{
    private var lh = LevelHandler()
    private var lsm = LevelStyleManager()
    private var im = ItemManager()
    private var de = DamageEvent()
    private var wi = WandItem()
    private var aa = AccessoryActive()
    private var ar = AccessoryRefresh()
    private var blockPlacer: ArrayList<Location> = ArrayList()

    private fun getCoreProtect(): CoreProtectAPI? {
        val plugin: Plugin? = getServer().pluginManager.getPlugin("CoreProtect")

        // Check that CoreProtect is loaded
        if (plugin == null || plugin !is CoreProtect) {
            return null
        }

        // Check that the API is enabled
        val coreProtect = plugin.api
        if (!coreProtect.isEnabled) {
            return null
        }

        // Check that a compatible version of the API is loaded
        return if (coreProtect.APIVersion() < 7) {
            null
        } else coreProtect
    }

    @EventHandler
    fun onKill(event: EntityDeathEvent) {
        val attacker = event.entity.killer
        val victim = event.entity
        if (attacker is Player) {
            if (attacker.world.name == "world" || attacker.world.name == "world_nether" || attacker.world.name == "world_the_end" ){
                when (victim) {
                    is PigZombie -> {lh.addExp(attacker,1,5)}
                    is Zombie -> {lh.addExp(attacker,1,150)}
                    is Skeleton -> {lh.addExp(attacker,1,175)}
                    is Creeper -> {lh.addExp(attacker,1,250)}
                    is Spider -> {lh.addExp(attacker,1,210)}
                    is Enderman -> { if (attacker.world.name != "world_the_end") {lh.addExp(attacker,1,350)}
                                     else {lh.addExp(attacker,1,1)}}
                    is CaveSpider -> {lh.addExp(attacker,1,225)}
                    is WitherSkeleton -> {lh.addExp(attacker,1,610)}
                    is Blaze -> {lh.addExp(attacker,1,590)}
                    is ZombieVillager -> {lh.addExp(attacker,1,150)}
                    is Zoglin -> {lh.addExp(attacker,1,660)}
                    is Phantom -> {lh.addExp(attacker,1,1450)}
                    is Hoglin -> {lh.addExp(attacker,1,700)}
                    is Piglin -> {lh.addExp(attacker,1,455)}
                    is PiglinBrute -> {lh.addExp(attacker,1,650)}
                    is Evoker -> {lh.addExp(attacker,1,600)}
                    is Vex -> {lh.addExp(attacker,1,405)}
                    is Drowned -> {lh.addExp(attacker,1,150)}
                    is Ghast -> {lh.addExp(attacker,1,1500)}
                    is Husk -> {lh.addExp(attacker,1,90)}
                    is Slime -> {lh.addExp(attacker,1,75)}
                    is MagmaCube -> {lh.addExp(attacker,1,75)}
                    is Ravager -> {lh.addExp(attacker,1,3300)}
                    is Wither -> {lh.addExp(attacker,1,9000)}
                    is EnderDragon -> {lh.addExp(attacker,1,10000)}
                    is Illusioner -> {lh.addExp(attacker,1,620)}
                    is Stray -> {lh.addExp(attacker,1,100)}
                    is Vindicator -> {lh.addExp(attacker,1,580)}
                    is Bat -> {lh.addExp(attacker,1,100)}
                    is Witch -> {lh.addExp(attacker,1,300)}
                    is Shulker -> {lh.addExp(attacker,1,577)}
                    is Silverfish -> {lh.addExp(attacker,1,152)}
                    is Guardian -> {lh.addExp(attacker,1,800)}
                    is ElderGuardian -> {lh.addExp(attacker,1,2700)}
                    is Pillager -> {lh.addExp(attacker,1,550)}

                    is Salmon -> {lh.addExp(attacker,5,80)}
                    is Cod -> {lh.addExp(attacker,5,70)}
                    is PufferFish -> {lh.addExp(attacker,5,120)}
                    is Squid -> {lh.addExp(attacker,5,100)}
                    is TropicalFish -> {lh.addExp(attacker,5,180)}
                }
            }
        }
    }

    @EventHandler
    fun onPlace(event: BlockPlaceEvent) {
        blockPlacer.add(event.block.location)
        if (blockPlacer.size > 100) {
            blockPlacer.removeAt(0)
        }
    }

    @EventHandler
    fun onBreak(event: BlockBreakEvent) {
        val CoreProtect: CoreProtectAPI = getCoreProtect()!!

        val blo = event.block.type
        if (CoreProtect.blockLookup(event.block,2147000000).size == 0 && !(blockPlacer.contains(event.block.location))) {
            if (event.player.world.name == "world" || event.player.world.name == "world_nether" || event.player.world.name == "world_the_end" ){
                when (blo) {
                    Material.SAND -> { lh.addExp(event.player,2,3) }
                    Material.NETHERRACK -> { lh.addExp(event.player,2,3) }
                    Material.GRAVEL -> { lh.addExp(event.player,2,3) }
                    Material.STONE -> { lh.addExp(event.player,2,5) }
                    Material.COBBLESTONE -> { lh.addExp(event.player,2,5) }
                    Material.DIORITE -> { lh.addExp(event.player,2,5) }
                    Material.ANDESITE -> { lh.addExp(event.player,2,5) }
                    Material.GRANITE -> { lh.addExp(event.player,2,5) }
                    Material.DEEPSLATE -> { lh.addExp(event.player,2,6) }
                    Material.CALCITE -> { lh.addExp(event.player,2,8) }
                    Material.TUFF -> { lh.addExp(event.player,2,7) }
                    Material.OBSIDIAN -> { lh.addExp(event.player,2,10) }
                    Material.COBBLED_DEEPSLATE -> { lh.addExp(event.player,2,6) }
                    Material.IRON_ORE -> { lh.addExp(event.player,2,68) }
                    Material.DEEPSLATE_IRON_ORE -> { lh.addExp(event.player,2,68) }
                    Material.COAL_ORE -> { lh.addExp(event.player,2,47) }
                    Material.DEEPSLATE_COAL_ORE -> { lh.addExp(event.player,2,47) }
                    Material.GOLD_ORE -> { lh.addExp(event.player,2,475) }
                    Material.DEEPSLATE_GOLD_ORE -> { lh.addExp(event.player,2,475) }
                    Material.LAPIS_ORE -> { lh.addExp(event.player,2,350) }
                    Material.DEEPSLATE_LAPIS_ORE -> { lh.addExp(event.player,2,350) }
                    Material.REDSTONE_ORE -> { lh.addExp(event.player,2,360) }
                    Material.DEEPSLATE_REDSTONE_ORE -> { lh.addExp(event.player,2,360) }
                    Material.COPPER_ORE -> { lh.addExp(event.player,2,20) }
                    Material.DEEPSLATE_COPPER_ORE -> { lh.addExp(event.player,2,20) }
                    Material.DIAMOND_ORE -> { lh.addExp(event.player,2,2100) }
                    Material.DEEPSLATE_DIAMOND_ORE -> { lh.addExp(event.player,2,2100) }
                    Material.EMERALD_ORE -> { lh.addExp(event.player,2,5000) }
                    Material.DEEPSLATE_EMERALD_ORE -> { lh.addExp(event.player,2,5000) }
                    Material.ANCIENT_DEBRIS -> { lh.addExp(event.player,2,3000) }
                    Material.NETHER_QUARTZ_ORE -> { lh.addExp(event.player,2,70) }
                    Material.NETHER_GOLD_ORE -> { lh.addExp(event.player,2,37) }
                    Material.GLOWSTONE -> { lh.addExp(event.player,2,77) }
                    Material.AMETHYST_BLOCK -> { lh.addExp(event.player,2,13) }
                    Material.AMETHYST_SHARD -> { lh.addExp(event.player,2,333) }

                    Material.SPAWNER -> { lh.addExp(event.player,1,5000) }

                    Material.ACACIA_LEAVES -> { lh.addExp(event.player,3,4) }
                    Material.BIRCH_LEAVES -> { lh.addExp(event.player,3,4) }
                    Material.DARK_OAK_LEAVES -> { lh.addExp(event.player,3,4) }
                    Material.OAK_LEAVES -> { lh.addExp(event.player,3,4) }
                    Material.SPRUCE_LEAVES -> { lh.addExp(event.player,3,4) }
                    Material.JUNGLE_LEAVES -> { lh.addExp(event.player,3,4) }
                    Material.AZALEA_LEAVES -> { lh.addExp(event.player,3,4) }
                    Material.FLOWERING_AZALEA_LEAVES -> { lh.addExp(event.player,3,4) }
                    Material.ACACIA_LOG -> { lh.addExp(event.player,3,128) }
                    Material.JUNGLE_LOG -> { lh.addExp(event.player,3,128) }
                    Material.OAK_LOG -> { lh.addExp(event.player,3,128) }
                    Material.DARK_OAK_LOG -> { lh.addExp(event.player,3,128) }
                    Material.SPRUCE_LOG -> { lh.addExp(event.player,3,128) }
                    Material.BIRCH_LOG -> { lh.addExp(event.player,3,128) }
                    Material.CRIMSON_STEM -> { lh.addExp(event.player,3,128) }
                    Material.WARPED_STEM -> { lh.addExp(event.player,3,128) }

                    Material.PUMPKIN -> { lh.addExp(event.player,4,35) }
                    Material.MELON -> { lh.addExp(event.player,4,35) }
                    Material.WHEAT -> {
                        val ageable: Ageable = event.block.blockData as Ageable
                        if (ageable.age == ageable.maximumAge) {
                            lh.addExp(event.player,4,110)
                        }
                    }
                    Material.CARROTS -> {
                        val ageable: Ageable = event.block.blockData as Ageable
                        if (ageable.age == ageable.maximumAge) {
                            lh.addExp(event.player, 4, 78)
                        }
                    }
                    Material.POTATOES -> {
                        val ageable: Ageable = event.block.blockData as Ageable
                        if (ageable.age == ageable.maximumAge) {
                            lh.addExp(event.player, 4, 78)
                        }
                    }
                    Material.NETHER_WART -> {
                        val ageable: Ageable = event.block.blockData as Ageable
                        if (ageable.age == ageable.maximumAge) {
                            lh.addExp(event.player, 4, 85)
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
                        lh.addExp(event.player, 4, 30*min(i,3))
                    }
                    Material.BAMBOO -> {
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
                            if (loc.block.type == Material.BAMBOO) {
                                if (CoreProtect.blockLookup(loc.block, 150).size == 0) { i++ }
                            }
                            else{ break }
                        }
                        lh.addExp(event.player, 3, 3*min(i,16))
                    }

                    else -> {}
                }
            }
        }
        else if (CoreProtect.blockLookup(event.block,120).size == 0 && !(blockPlacer.contains(event.block.location))) {
            if (event.player.world.name == "world" || event.player.world.name == "world_nether" || event.player.world.name == "world_the_end" ){
                when (blo) {
                    Material.PUMPKIN -> { lh.addExp(event.player,4,35) }
                    Material.MELON -> { lh.addExp(event.player,4,35) }
                    Material.WHEAT -> {
                        val ageable: Ageable = event.block.blockData as Ageable
                        if (ageable.age == ageable.maximumAge) {
                            lh.addExp(event.player,4,110)
                        }
                    }
                    Material.CARROTS -> {
                        val ageable: Ageable = event.block.blockData as Ageable
                        if (ageable.age == ageable.maximumAge) {
                            lh.addExp(event.player, 4, 78)
                        }
                    }
                    Material.POTATOES -> {
                        val ageable: Ageable = event.block.blockData as Ageable
                        if (ageable.age == ageable.maximumAge) {
                            lh.addExp(event.player, 4, 78)
                        }
                    }
                    Material.NETHER_WART -> {
                        val ageable: Ageable = event.block.blockData as Ageable
                        if (ageable.age == ageable.maximumAge) {
                            lh.addExp(event.player, 4, 85)
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
                        lh.addExp(event.player, 4, 30* min(i,3))
                    }
                    Material.BAMBOO -> {
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
                            if (loc.block.type == Material.BAMBOO) {
                                if (CoreProtect.blockLookup(loc.block, 150).size == 0) { i++ }
                            }
                            else{ break }
                        }
                        lh.addExp(event.player, 3, 3*min(i,16))
                    }

                    else -> {}
                }
            }

        }

    }

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {

        val p = event.player.name
        var message = event.message
        val levelDisplay = lsm.getLevelStyle(PlayerDataStorage.map[p + "settingLS"]!!,event.player)
        message = message.replace(":yellow_square:", "§e⬛§f")
        message = message.replace(":red_square:", "§c⬛§f")
        message = message.replace(":green_square:", "§a⬛§f")
        message = message.replace(":open_mouth:", "§eö§f")
        message = message.replace(":heart:", "§c❤§f")
        message = message.replace(":accept:", "§2✔§f")
        message = message.replace(":deny:", "§4❌§f")
        message = message.replace(":x:", "§4❌§f")
        message = message.replace(":o:", "§4○§f")
        message = message.replace(":v:", "§2✔§f")
        message = message.replace(":star:", "§6⭐§f")
        message = message.replace(":boj:", "§9/<>§f")
        message = message.replace(":question:", "§c§l?§f")
        message = message.replace(":exclamation:", "§c§l!§f")
        message = message.replace(":interrobang:", "§c§l!?§f")
        message = message.replace(":bangbang:", "§c§l!!§f")
        message = message.replace(":python:", "§1┛§e┏§f")
        for(player in Bukkit.getOnlinePlayers()) {
            if (message.contains(player.name)) {
                player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F)
                message = message.replace(
                    player.name.toRegex(),
                    "§6" + player.name.toRegex()+"§f")

            }
        }
        event.message = message

        event.format = "$levelDisplay§f %s: %s"
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
    fun onInventoryClose(event: InventoryCloseEvent) {
        ar.aRefresh(event.player as Player?)
    }

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_BLOCK || event.action == Action.RIGHT_CLICK_AIR) {
            when (event.player.inventory.itemInMainHand.type) {
                wi.getWandMaterial(PlayerDataStorage.map[event.player.name + "wand1"]!!) -> {
                    aa.aaActive(event.player, 1)
                }
                wi.getWandMaterial(PlayerDataStorage.map[event.player.name + "wand2"]!!) -> {
                    aa.aaActive(event.player, 2)
                }
                wi.getWandMaterial(PlayerDataStorage.map[event.player.name + "wand3"]!!) -> {
                    aa.aaActive(event.player, 3)
                }
                else -> {
                }
            }
        }
    }

    @EventHandler
    fun onDamagedByEnvironment(event: EntityDamageEvent) {
        val victim = event.entity
        var health = 1.0
        var maxHealth = 1.0
        if (event.cause != EntityDamageEvent.DamageCause.ENTITY_ATTACK && event.cause != EntityDamageEvent.DamageCause.PROJECTILE &&
            event.cause != EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK &&
            event.cause != EntityDamageEvent.DamageCause.THORNS && event.cause != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            if (victim is Player) {
                maxHealth = victim.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
                health =victim.health

                event.damage = de.victimDamageCalculate(victim,event.damage)
                var damage = event.damage
                for(i: Int in 1..3) {
                    val accLv = PlayerDataStorage.accessory[victim.name+(PlayerDataStorage.map[victim.name + "accessory" + i])]!!
                    when (PlayerDataStorage.map[victim.name + "accessory" + i]) {
                        9->{
                            if (event.cause == EntityDamageEvent.DamageCause.FIRE || event.cause == EntityDamageEvent.DamageCause.FIRE_TICK || event.cause == EntityDamageEvent.DamageCause.LAVA) {
                                damage *= 1-(0.2 + 0.1 * accLv)
                                victim.fireTicks = max(0,victim.fireTicks-10*accLv)
                            }
                        }
                        14->{
                            if (event.cause == EntityDamageEvent.DamageCause.FLY_INTO_WALL || event.cause == EntityDamageEvent.DamageCause.FALL) {
                                damage *= 1-(0.3 + 0.2 * accLv)
                            }
                        }
                    }
                }
                event.damage=damage
                if (PlayerDataStorage.map[victim.name + "settingDI"] == 1) {
                    val indicate: String = when (event.cause) {
                        EntityDamageEvent.DamageCause.FIRE -> "불"
                        EntityDamageEvent.DamageCause.FIRE_TICK -> "불"
                        EntityDamageEvent.DamageCause.BLOCK_EXPLOSION -> "폭발"
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
        if (victim is LivingEntity) {
            if (victim.absorptionAmount == 0.0) {
                event.damage = de.damageCalculate(victim, attacker, event.damage)
                event.damage = de.victimDamageCalculate(victim, event.damage)
                event.damage = de.execution(victim, attacker, event.finalDamage)
            }
        }
        if (victim is Player) {
            if (attacker is Arrow) {
                val distance= String.format("%.2f",(attacker.shooter as Entity).location.distance(victim.location))
                if (PlayerDataStorage.map[victim.name + "settingDI"] == 1) {
                    victim.sendMessage("§c➜ §7" + (attacker.shooter as Entity).name + "에게 §6"+distance+" 거리에서 §c" + String.format("%.2f",event.finalDamage) + "§7 피해를 받았다.")
                }
                if (attacker.shooter is Player) {
                    spawnHitParticle(attacker.shooter as Player,victim)
                    if (PlayerDataStorage.map[(attacker.shooter as Player).name + "settingDI"] == 1) {
                        (attacker.shooter as Player).sendMessage("§a➜ §7" + victim.name + "에게 §6"+distance+" 거리에서 §a" + String.format("%.2f",event.finalDamage)  + "§7 피해를 주었다.")
                    }
                }
            }
            else{
                if (attacker is Player) {
                    spawnHitParticle(attacker,victim)
                }
                if (PlayerDataStorage.map[victim.name + "settingDI"] == 1) {
                    victim.sendMessage("§c➜ §7" + attacker.name + "에게 §c" + String.format("%.2f",event.finalDamage) + "§7 피해를 받았다.")
                }
                if (PlayerDataStorage.map[attacker.name + "settingDI"] == 1) {
                    attacker.sendMessage("§a➜ §7" + victim.name + "에게 §a" + String.format("%.2f",event.finalDamage)  + "§7 피해를 주었다.")
                }
            }
        }
        else if (attacker is Player) { //Victim = Entity
            spawnHitParticle(attacker,victim)
            if (PlayerDataStorage.map[attacker.name + "settingDI"] == 1) {
                attacker.sendMessage("§a➜ §7" + victim.name + "에게 §a" + String.format("%.2f",event.finalDamage)  + "§7 피해를 주었다.")
            }
        }
        else if (attacker is Arrow) {
            val distance= String.format("%.2f",(attacker.shooter as Entity).location.distance(victim.location))
            if (attacker.shooter is Player) {
                spawnHitParticle(attacker.shooter as Player,victim)
                if (PlayerDataStorage.map[(attacker.shooter as Player).name + "settingDI"] == 1) {
                    (attacker.shooter as Player).sendMessage("§a➜ §7" + victim.name + "에게 §6"+distance+" 거리에서 §a" + String.format("%.2f",event.finalDamage)  + "§7 피해를 주었다.")
                }
            }
        }
    }

    private fun spawnHitParticle(attacker: Player, victim: Entity) {
        when (PlayerDataStorage.map[attacker.name + "settingHE"]){
            1 -> attacker.world.spawnParticle( Particle.ASH,victim.location.x,victim.location.y+1,victim.location.z,150,0.3,1.0,0.3 )
            2 -> attacker.world.spawnParticle( Particle.DRAGON_BREATH,victim.location.x,victim.location.y+1,victim.location.z,8,0.3,0.3,0.3, 0.05)
            3 -> attacker.world.spawnParticle( Particle.HEART,victim.location.x,victim.location.y+2,victim.location.z,2,0.1,0.1,0.1)
            4 -> attacker.world.spawnParticle( Particle.FLAME,victim.location.x,victim.location.y+1,victim.location.z,12,0.4,0.4,0.4,0.01)
            5 -> {
                attacker.world.spawnParticle( Particle.PORTAL,victim.location.x,victim.location.y+1,victim.location.z,60,0.1,0.1,0.1,0.33)
                attacker.world.spawnParticle( Particle.PORTAL,victim.location.x,victim.location.y+1,victim.location.z,60,0.5,0.5,0.5,0.0)
            }
            6 -> attacker.world.spawnParticle( Particle.FIREWORKS_SPARK,victim.location.x,victim.location.y+1,victim.location.z,20,0.3,0.3,0.3,0.0)
            7 -> attacker.world.spawnParticle( Particle.FLASH,victim.location.x,victim.location.y+1,victim.location.z,1,0.2,0.5,0.2,0.0)
            8 -> attacker.world.spawnParticle( Particle.SOUL,victim.location.x,victim.location.y+1,victim.location.z,5,0.3,0.5,0.3,0.0)
            9 -> {
                attacker.world.spawnParticle( Particle.BUBBLE_POP,victim.location.x,victim.location.y+1,victim.location.z,20,0.5,0.5,0.5,0.0)
                attacker.world.playSound(victim.location,Sound.ENTITY_PLAYER_SPLASH,1.5F,1F)
            }
            10 -> {
                attacker.world.spawnParticle( Particle.NOTE,victim.location.x,victim.location.y+1,victim.location.z,7,0.5,0.5,0.5,1.0)
                attacker.world.playSound(victim.location,Sound.BLOCK_NOTE_BLOCK_BELL,1.5F,(Math.random()+1).toFloat())
            }
            11 -> {
                attacker.world.spawnParticle( Particle.ELECTRIC_SPARK,victim.location.x,victim.location.y+2,victim.location.z,500,0.0,20.0,0.0,0.0)
                attacker.world.playSound(victim.location,Sound.ENTITY_LIGHTNING_BOLT_IMPACT,1.5F,2F)
            }
            12 -> {
                attacker.world.spawnParticle( Particle.VILLAGER_HAPPY,victim.location.x,victim.location.y+1,victim.location.z,50,1.0,0.0,0.0,0.0)
                attacker.world.spawnParticle( Particle.VILLAGER_HAPPY,victim.location.x,victim.location.y+1,victim.location.z,50,0.0,0.0,1.0,0.0)
                attacker.world.playSound(victim.location,Sound.ENTITY_ENDER_DRAGON_SHOOT,1.5F,1F)
            }
            13 -> {
                attacker.world.spawnParticle( Particle.FALLING_DRIPSTONE_WATER,victim.location.x,victim.location.y+1,victim.location.z,50,1.0,0.0,0.0,0.0)
                attacker.world.spawnParticle( Particle.VILLAGER_HAPPY,victim.location.x,victim.location.y+1,victim.location.z,50,0.0,0.0,1.0,0.0)
                attacker.world.playSound(victim.location,Sound.ENTITY_ENDER_DRAGON_SHOOT,1.5F,1F)
            }
        }
    }

    @EventHandler
    fun onArrowDamaged(event: EntityShootBowEvent) {

    }

    @EventHandler
    fun onHungerChanged(event: FoodLevelChangeEvent) {
        val player = event.entity as Player
        if (player.foodLevel > event.foodLevel){
            val srvRate = Math.random()
            for(i: Int in 1..3) {
                val accLv = PlayerDataStorage.accessory[player.name+(PlayerDataStorage.map[player.name + "accessory" + i])]!!
                when (PlayerDataStorage.map[player.name + "accessory" + i]) {
                    14->{
                        if (event.foodLevel <= 7) {
                            event.isCancelled=true;
                        }
                    }
                }
            }
            if (srvRate > (0.9965.pow(PlayerDataStorage.map[player.name + "statsSRV"]!!.toDouble()))){
                event.isCancelled = true
                player.health = min(player.health + 1, player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value)
            }
        }
    }

    @EventHandler
    fun onFishing(event: PlayerFishEvent) {
        if (event.state == PlayerFishEvent.State.CAUGHT_FISH) {
            var waterCount=0
            for(x: Int in -2..2) {
                for(y: Int in 1..2) {
                    for(z: Int in -2..2) {
                        if (event.player.world.getBlockAt(event.hook.location.blockX+x,event.hook.location.blockY-y,event.hook.location.blockZ+z).type == Material.WATER){
                            waterCount += 1
                        }
                    }
                }
            }
            if (waterCount<50){
                if (event.caught is Item){
                    val rate = "§7§lCOMMON"
                    val caught: String
                    val weight = Math.random()*100
                    val stack = event.caught as Item?
                    if (weight < 25) {
                        stack!!.itemStack = ItemStack(Material.STICK)
                        caught = "막대기"
                    }
                    else if (weight < 50) {
                        stack!!.itemStack = ItemStack(Material.BOWL)
                        caught = "그릇"
                    }
                    else if (weight < 75) {
                        stack!!.itemStack = ItemStack(Material.ROTTEN_FLESH)
                        caught = "썩은 살점"
                    }
                    else {
                        stack!!.itemStack = ItemStack(Material.GLASS_BOTTLE)
                        caught = "유리병"
                    }
                    event.player.sendMessage("$rate §f$caught 을(를) 낚았다.")
                    event.player.sendMessage("§c[!] 낚시 위치가 좋지 않습니다. 물이 많은 곳으로 이동하세요 §7(위치 점수: "+(waterCount*2)+"/100)")
                    event.player.playSound(event.player.location,Sound.BLOCK_NOTE_BLOCK_BASS,1.0F,0.5F)
                    event.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent("§a[!] 위치 점수가 100점일 때만 낚시가 가능합니다"))
                }
            }
            else{
                if(event.caught != null) {
                    val bait = event.player.inventory.itemInOffHand.itemMeta?.displayName
                    var los = event.player.inventory.itemInMainHand.itemMeta?.getEnchantLevel(Enchantment.LUCK)
                    if (los == null) {
                        los = event.player.inventory.itemInOffHand.itemMeta?.getEnchantLevel(Enchantment.LUCK)
                        if (los == null) { los=0 }
                    }
                    val fishingRate = Math.random()*100
                    val weight = Math.random()*100
                    val rate: String
                    val caught: String
                    var amount = 0
                    if (event.caught is Item){
                        val stack = event.caught as Item?
                        if (fishingRate < 80) {
                            rate = "§3§lFISH"
                            event.player.playSound(event.player.location,Sound.ITEM_BUCKET_FILL_FISH,1.0F,0.5F)
                            if (weight < 60) {
                                stack!!.itemStack = ItemStack(Material.COD)
                                caught = "대구"
                                lh.addExp(event.player,5,300)
                            }
                            else if (weight < 85) {
                                stack!!.itemStack = ItemStack(Material.SALMON)
                                caught = "연어"
                                lh.addExp(event.player,5,420)
                            }
                            else if (weight < 98) {
                                stack!!.itemStack = ItemStack(Material.PUFFERFISH)
                                caught = "복어"
                                lh.addExp(event.player,5,900)
                            }
                            else {
                                stack!!.itemStack = ItemStack(Material.TROPICAL_FISH)
                                caught = "열대어"
                                lh.addExp(event.player,5,2160)
                            }
                            event.player.sendMessage("$rate §f$caught 을(를) 낚았다.")
                        }
                        else if (fishingRate <= 90-(los.minus(3))) {
                            event.player.playSound(event.player.location,Sound.BLOCK_NOTE_BLOCK_BASS,1.0F,0.5F)
                            rate = "§7§lCOMMON"
                            if (weight < 5) {
                                stack!!.itemStack = ItemStack(Material.STICK)
                                caught = "막대기"
                                lh.addExp(event.player,5,145)
                            }
                            else if (weight < 22) {
                                stack!!.itemStack = ItemStack(Material.LILY_PAD)
                                caught = "수련잎"
                                lh.addExp(event.player,5,70)
                            }
                            else if (weight < 32) {
                                stack!!.itemStack = ItemStack(Material.BOWL)
                                caught = "그릇"
                                lh.addExp(event.player,5,120)
                            }
                            else if (weight < 42) {
                                stack!!.itemStack = ItemStack(Material.LEATHER)
                                caught = "가죽"
                                lh.addExp(event.player,5,100)
                            }
                            else if (weight < 52) {
                                stack!!.itemStack = ItemStack(Material.ROTTEN_FLESH)
                                caught = "썩은 살점"
                                lh.addExp(event.player,5,30)
                            }
                            else if (weight < 62) {
                                stack!!.itemStack = ItemStack(Material.BONE)
                                caught = "뼈"
                                lh.addExp(event.player,5,40)
                            }
                            else if (weight < 72) {
                                stack!!.itemStack = ItemStack(Material.GLASS_BOTTLE)
                                caught = "유리병"
                                lh.addExp(event.player,5,90)
                            }
                            else if (weight < 77) {
                                stack!!.itemStack = ItemStack(Material.STRING)
                                caught = "실"
                                lh.addExp(event.player,5,20)
                            }
                            else if (weight < 78) {
                                stack!!.itemStack = ItemStack(Material.INK_SAC)
                                caught = "먹물"
                                lh.addExp(event.player,5,350)
                            }
                            else if (weight < 88) {
                                stack!!.itemStack = ItemStack(Material.TRIPWIRE_HOOK)
                                caught = "철사 덫 갈고리"
                                lh.addExp(event.player,5,130)
                            }
                            else if (weight < 98) {
                                stack!!.itemStack = ItemStack(Material.LEATHER_BOOTS)
                                caught = "가죽 장화"
                                lh.addExp(event.player,5,85)
                            }
                            else {
                                stack!!.itemStack = ItemStack(Material.FISHING_ROD)
                                caught = "낚싯대"
                                lh.addExp(event.player,5,105)
                            }
                            event.player.sendMessage("$rate §f$caught 을(를) 낚았다.")
                        }
                        else if (fishingRate <= 99.5-(los.minus(3))) {
                            rate = "§b§lRARE!"
                            event.player.playSound(event.player.location,Sound.BLOCK_NOTE_BLOCK_BELL,1.0F,2.0F)
                            if (weight < 12) {
                                stack!!.itemStack = ItemStack(Material.NAME_TAG)
                                caught = "이름표"
                                lh.addExp(event.player,5,3400)
                            }
                            else if (weight < 24) {
                                stack!!.itemStack = ItemStack(Material.NAUTILUS_SHELL)
                                caught = "앵무조개 껍데기"
                                lh.addExp(event.player,5,3070)
                            }
                            else if (weight < 36) {
                                stack!!.itemStack = ItemStack(Material.SADDLE)
                                caught = "안장"
                                lh.addExp(event.player,5,2600)
                            }
                            else if (weight < 48) {
                                stack!!.itemStack = ItemStack(Material.EXPERIENCE_BOTTLE)
                                caught = "경험치 병"
                                lh.addExp(event.player,5,2777)
                            }
                            else if (weight < 60) {
                                stack!!.itemStack = ItemStack(Material.DIAMOND)
                                caught = "다이아몬드"
                                lh.addExp(event.player,5,4000)
                            }
                            else if (weight < 72) {
                                stack!!.itemStack = ItemStack(Material.FISHING_ROD)
                                stack.itemStack = im.randomEnchantment(stack.itemStack)
                                caught = "마법이 부여된 낚싯대"
                                lh.addExp(event.player,5,2800)
                            }
                            else if (weight < 84) {
                                stack!!.itemStack = ItemStack(Material.GOLDEN_APPLE)
                                caught = "황금 사과"
                                lh.addExp(event.player,5,2840)
                            }
                            else if (weight < 96) {
                                stack!!.itemStack = ItemStack(Material.BOW)
                                stack.itemStack = im.randomEnchantment(stack.itemStack)
                                caught = "마법이 부여된 활"
                                lh.addExp(event.player,5,2920)
                            }
                            else {
                                stack!!.itemStack = ItemStack(Material.SHULKER_SHELL)
                                caught = "셜커 껍데기"
                                lh.addExp(event.player,5,4800)
                            }
                            event.player.sendMessage("$rate §f$caught 을(를) 낚았다.")
                        }
                        else if (fishingRate <= 99.95-(los.minus(3))) {
                            rate = "§d§lEPIC!"
                            event.player.playSound(event.player.location,Sound.ENTITY_PLAYER_LEVELUP,1.0F,0.5F)
                            lh.addExp(event.player,5,15000)

                            if (weight < 20) {
                                stack!!.itemStack = ItemStack(Material.ANCIENT_DEBRIS)
                                caught = "고대 잔해"
                            }
                            else if (weight < 40) {
                                stack!!.itemStack = ItemStack(Material.TOTEM_OF_UNDYING)
                                caught = "불사의 토템"
                            }
                            else if (weight < 50) {
                                stack!!.itemStack = ItemStack(Material.EXPERIENCE_BOTTLE)
                                event.expToDrop = 1500
                                caught = "1500 인첸트 경험치"
                            }
                            else if (weight < 60) {
                                stack!!.itemStack = im.createItem(im.createSkull("http://textures.minecraft.net/texture/7594ca5dc5c85db3b4a90d485932bede5fbdf4023fc4fbff6fe14be409c1f97")
                                    ,"§e빛나는 미끼","§7낚는 물고기/보물의 개수가 2배로 증가합니다","","§7미끼 사용법:","§7왼손에 들고 낚시를 하면 자동으로 1개가 소모됩니다","§7주의: 미끼를 설치 시 사용할 수 없게 됩니다!")
                                stack.itemStack.amount = 5
                                caught = "빛나는 미끼"
                                amount = 5
                            }
                            else if (weight < 70) {
                                stack!!.itemStack = im.createItem(im.createSkull("http://textures.minecraft.net/texture/e679918e52f3f8f2cabbbeac6a97681f2f8aa10c0b2e818592885a4a0e9d227")
                                    ,"§e경험치 미끼","§7낚시 경험치가 1~10000중 랜덤으로 추가 지급됩니다","","§7미끼 사용법:","§7왼손에 들고 낚시를 하면 자동으로 1개가 소모됩니다","§7주의: 미끼를 설치 시 사용할 수 없게 됩니다!")
                                stack.itemStack.amount = 5
                                caught = "경험치 미끼"
                                amount = 5
                            }

                            else if (weight < 80) {
                                stack!!.itemStack = ItemStack(Material.WITHER_SKELETON_SKULL)
                                caught = "위더 스켈레톤 해골"
                            }
                            else if (weight < 81) {
                                stack!!.itemStack = ItemStack(Material.MUSIC_DISC_13)
                                caught = "뮤직 디스크 - 13"
                            }
                            else if (weight < 82) {
                                stack!!.itemStack = ItemStack(Material.MUSIC_DISC_BLOCKS)
                                caught = "뮤직 디스크 - Blocks"
                            }
                            else if (weight < 83) {
                                stack!!.itemStack = ItemStack(Material.MUSIC_DISC_CAT)
                                caught = "뮤직 디스크 - Cat"
                            }
                            else if (weight < 84) {
                                stack!!.itemStack = ItemStack(Material.MUSIC_DISC_CHIRP)
                                caught = "뮤직 디스크 - Chirp"
                            }
                            else if (weight < 85) {
                                stack!!.itemStack = ItemStack(Material.MUSIC_DISC_MALL)
                                caught = "뮤직 디스크 - Mall"
                            }
                            else if (weight < 86) {
                                stack!!.itemStack = ItemStack(Material.MUSIC_DISC_MELLOHI)
                                caught = "뮤직 디스크 - Mellohi"
                            }
                            else if (weight < 87) {
                                stack!!.itemStack = ItemStack(Material.MUSIC_DISC_PIGSTEP)
                                caught = "뮤직 디스크 - Pigstep"
                            }
                            else if (weight < 88) {
                                stack!!.itemStack = ItemStack(Material.MUSIC_DISC_FAR)
                                caught = "뮤직 디스크 - Far"
                            }
                            else if (weight < 89) {
                                stack!!.itemStack = ItemStack(Material.MUSIC_DISC_WAIT)
                                caught = "뮤직 디스크 - Wait"
                            }
                            else if (weight < 90) {
                                stack!!.itemStack = ItemStack(Material.MUSIC_DISC_WARD)
                                caught = "뮤직 디스크 - Ward"
                            }
                            else if (weight < 91) {
                                stack!!.itemStack = ItemStack(Material.MUSIC_DISC_STAL)
                                caught = "뮤직 디스크 - Stal"
                            }
                            else if (weight < 92) {
                                stack!!.itemStack = ItemStack(Material.MUSIC_DISC_STRAD)
                                caught = "뮤직 디스크 - Strad"
                            }
                            else {
                                stack!!.itemStack = ItemStack(Material.ENCHANTED_GOLDEN_APPLE)
                                caught = "마법이 부여된 황금 사과"
                            }
                            if (amount > 0){ Bukkit.broadcastMessage("$rate §f${event.player.name}님이 $caught§7x$amount §f을(를) 낚았습니다!") }
                            else { Bukkit.broadcastMessage("$rate §f${event.player.name}님이 $caught §f을(를) 낚았습니다!") }
                        }
                        else {
                            rate = "§e§lLEGENDARY!"
                            event.player.playSound(event.player.location,Sound.UI_TOAST_CHALLENGE_COMPLETE,1.0F,1.0F)
                            lh.addExp(event.player,5,75000)
                            if (weight < 30) {
                                stack!!.itemStack = ItemStack(Material.NETHERITE_INGOT)
                                caught = "네더라이트 주괴"
                            }
                            else if (weight < 40) {
                                stack!!.itemStack = ItemStack(Material.NETHERITE_PICKAXE)
                                caught = "마법이 부여된 네더라이트 곡괭이"
                                stack.itemStack = im.randomEnchantment(stack.itemStack)
                            }
                            else if (weight < 50) {
                                stack!!.itemStack = ItemStack(Material.HEART_OF_THE_SEA)
                                caught = "바다의 심장"
                            }
                            else if (weight < 60) {
                                stack!!.itemStack = ItemStack(Material.BOW)
                                caught = "무한 수선 활"
                                stack.itemStack = im.infMendEnchantment(stack.itemStack)
                            }
                            else if (weight < 70) {
                                stack!!.itemStack = ItemStack(Material.NETHER_STAR)
                                caught = "네더의 별"
                            }
                            else if (weight < 80) {
                                stack!!.itemStack = im.createItem(im.createSkull("http://textures.minecraft.net/texture/884e92487c6749995b79737b8a9eb4c43954797a6dd6cd9b4efce17cf475846")
                                    ,"§e체력 증가 미끼","§7체력 증가 III 버프를 1시간동안 얻습니다 ","","§7미끼 사용법:","§7왼손에 들고 낚시를 하면 자동으로 1개가 소모됩니다","§7주의: 미끼를 설치 시 사용할 수 없게 됩니다!")
                                stack.itemStack.amount = 3
                                caught = "체력 증가 미끼"
                                amount = 3
                            }
                            else if (weight < 90) {
                                stack!!.itemStack = im.createItem(im.createSkull("http://textures.minecraft.net/texture/5e48615df6b7ddf3ad495041876d9169bdc983a3fa69a2aca107e8f251f7687")
                                    ,"§e포만한 미끼","§7포화 I 버프를 1시간동안 얻습니다","","§7미끼 사용법:","§7왼손에 들고 낚시를 하면 자동으로 1개가 소모됩니다","§7주의: 미끼를 설치 시 사용할 수 없게 됩니다!")
                                stack.itemStack.amount = 3
                                caught = "포만한 미끼"
                                amount = 3
                            }
                            else {
                                stack!!.itemStack = ItemStack(Material.NETHERITE_HELMET)
                                caught = "마법이 부여된 네더라이트 투구"
                                stack.itemStack = im.randomEnchantment(stack.itemStack)
                            }
                            if (amount > 0){ Bukkit.broadcastMessage("$rate §f${event.player.name}님이 $caught§7x$amount §f을(를) 낚았습니다!") }
                            else { Bukkit.broadcastMessage("$rate §f${event.player.name}님이 $caught §f을(를) 낚았습니다!") }
                        }
                        when (bait) {
                            "§e빛나는 미끼" -> {
                                stack.itemStack.amount *= 2
                                event.player.sendMessage("§e빛나는 미끼§f가 사용되었습니다 §7(아이템 개수 2배)")
                                if (event.player.inventory.itemInOffHand.amount == 1){ event.player.inventory.setItemInOffHand(ItemStack(Material.AIR)) }
                                else { event.player.inventory.itemInOffHand.amount = event.player.inventory.itemInOffHand.amount-1 }
                            }
                            "§e경험치 미끼" -> {
                                val expBait = (Math.random()*10000).toInt()
                                lh.addExp(event.player,5,expBait)
                                event.player.sendMessage("§e경험치 미끼§f가 사용되었습니다 §7(+$expBait)")
                                if (event.player.inventory.itemInOffHand.amount == 1){ event.player.inventory.setItemInOffHand(ItemStack(Material.AIR)) }
                                else { event.player.inventory.itemInOffHand.amount = event.player.inventory.itemInOffHand.amount-1 }
                            }
                            "§e체력 증가 미끼" -> {
                                event.player.addPotionEffect(PotionEffect(PotionEffectType.HEALTH_BOOST,20*3599,2))
                                event.player.sendMessage("§e체력 증가 미끼§f가 사용되었습니다 §7(체력 버프 1시간)")
                                if (event.player.inventory.itemInOffHand.amount == 1){ event.player.inventory.setItemInOffHand(ItemStack(Material.AIR)) }
                                else { event.player.inventory.itemInOffHand.amount = event.player.inventory.itemInOffHand.amount-1 }
                            }
                            "§e포만한 미끼" -> {
                                event.player.addPotionEffect(PotionEffect(PotionEffectType.SATURATION,20*3599,0))
                                event.player.sendMessage("§e포만한 미끼§f가 사용되었습니다 §7(포화 버프 1시간)")
                                if (event.player.inventory.itemInOffHand.amount == 1){ event.player.inventory.setItemInOffHand(ItemStack(Material.AIR)) }
                                else { event.player.inventory.itemInOffHand.amount = event.player.inventory.itemInOffHand.amount-1 }
                            }
                        }
                    }
                }
            }
        }
    }
}