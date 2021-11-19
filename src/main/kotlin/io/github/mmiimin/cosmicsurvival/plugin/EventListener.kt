package io.github.mmiimin.cosmicsurvival.plugin

import io.github.mmiimin.cosmicsurvival.LevelHandler
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import java.util.*


class EventListener: Listener{
    private var lh = LevelHandler()

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
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

        val msg = event.message.uppercase()
        var message = event.message
        for (player in Bukkit.getOnlinePlayers()) {
            if (msg.contains("@"+player.name.uppercase(Locale.getDefault()))) {
                player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F)
                message = message.replace(
                    "@" + player.name.toRegex(),
                    "§6@" + player.name.toRegex()+"§f")

            }
        }
        event.message = message

        event.format = "%s: %s"
    }

}