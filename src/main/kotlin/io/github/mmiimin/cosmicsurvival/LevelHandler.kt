package io.github.mmiimin.cosmicsurvival

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerInteractEvent

class LevelHandler {

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        event.player.sendMessage("Interaction")
    }

    fun addExp(player: Player, code: Int, amount: Int) {
        val message: String = when (code) {
            1 -> "§c[\uD83D\uDDE1]"
            2 -> "§6[⛏]"

            else -> "§4ERROR - " + code + "code"
        }
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent(message));
    }
}