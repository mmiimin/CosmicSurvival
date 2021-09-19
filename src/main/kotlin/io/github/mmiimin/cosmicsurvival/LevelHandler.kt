package io.github.mmiimin.cosmicsurvival

import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerInteractEvent

class LevelHandler {

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        event.player.sendMessage("Interaction")
    }
}