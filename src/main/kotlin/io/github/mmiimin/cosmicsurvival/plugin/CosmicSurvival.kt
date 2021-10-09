package io.github.mmiimin.cosmicsurvival.plugin

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.java.JavaPlugin

class CosmicSurvival : JavaPlugin() , Listener {

    override fun onEnable() {
        server.pluginManager.registerEvents(this, this@CosmicSurvival)
    }

    override fun onDisable() {
    }

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        event.player.sendMessage("Interaction")
        event.player.setCooldown(Material.ACACIA_LEAVES,2)
    }
/*
    @EventHandler
    fun breakExperience(event: BlockBreakEvent) {
        if (event.block.blockData) {

        }
    }
*/ //
}
