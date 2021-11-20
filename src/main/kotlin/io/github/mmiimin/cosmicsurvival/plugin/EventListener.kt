package io.github.mmiimin.cosmicsurvival.plugin

import io.github.mmiimin.cosmicsurvival.LevelHandler
import io.github.mmiimin.cosmicsurvival.database.Database
import io.github.mmiimin.cosmicsurvival.database.SQLite
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent


class EventListener: Listener{
    private var lh = LevelHandler()

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        event.joinMessage = "§a⏻ §e"+event.joinMessage

    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        event.quitMessage = "§c⏻ §e"+event.quitMessage
    }

    @EventHandler
    fun onKill(event: EntityDeathEvent) {
        val attacker = event.entity.killer as Player

        if (attacker.world.name != "world_the_end") {

        }
    }

    @EventHandler
    fun onBreak(event: BlockBreakEvent) {
        val blo = event.block.type
        if (blo == Material.STONE) { lh.addExp(event.player,2,5) }
        else if (blo == Material.DIORITE) { lh.addExp(event.player,2,5) }
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
        if (event.entity.killer is Player) {
            event.deathMessage = "§c⚔ §7" + event.deathMessage
        }
        else {
            event.deathMessage = "§c☠ §7" + event.deathMessage
        }
    }

    @EventHandler
    fun onDamagedByEnvironment(event: EntityDamageEvent) {
        val victim = event.entity
        if (event.cause != EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            if (victim is Player) {

            }
        }
    }

    @EventHandler
    fun onDamaged(event: EntityDamageByEntityEvent) {
        val victim = event.entity
        val attacker = event.damager

    }

}