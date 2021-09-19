package io.github.mmiimin.cosmicsurvival.plugin

import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent

class EventListener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player

    }

}