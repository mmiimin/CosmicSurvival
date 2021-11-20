package io.github.mmiimin.cosmicsurvival

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerInteractEvent
import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage

class LevelHandler {
    private var pds = PlayerDataStorage()

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        event.player.sendMessage("Interaction")
    }

    fun addExp(player: Player, code: Int, amount: Int) {
        val codex: String = when (code) {
            1 -> "combat"
            2 -> "mining"
            3 -> "foraging"
            4 -> "farming"
            5 -> "fishing"
            6 -> "crafting"
            else -> "X"
        }
        val message: String = when (code) {
            1 -> "§c[\uD83D\uDDE1]"
            2 -> "§6[⛏]"

            else -> "§4ERROR - " + code + "code"
        }
        pds.map[player.name + codex + "exp"] = amount
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent(message))
    }
}