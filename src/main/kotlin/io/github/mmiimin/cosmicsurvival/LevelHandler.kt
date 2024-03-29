package io.github.mmiimin.cosmicsurvival

import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.entity.Player

class LevelHandler {

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
        val hi: String
        val fi: String
        val message: String
        when (code) {
            1 -> {  hi = "§c[\uD83D\uDDE1]"
                    fi = "§c|"}
            2 -> {  hi = "§e[⛏]"
                    fi = "§e|"}
            3 -> {  hi = "§6[\uD83E\uDE93]"
                    fi = "§6|"}
            4 -> {  hi = "§a[☘]"
                    fi = "§a|"}
            5 -> {  hi = "§3[\uD83C\uDFA3]"
                    fi = "§3|"}
            6 -> {  hi = "§d[⦾]"
                    fi = "§d|"}


            else -> {  hi = "§4[X]"
                       fi = "§4|"}
        }

        if (PlayerDataStorage.map[player.name + codex + "Level"]!! < 50) {
            PlayerDataStorage.map[player.name + codex + "Exp"] = amount + PlayerDataStorage.map[player.name + codex + "Exp"]!!
            if (PlayerDataStorage.map[player.name + codex + "Exp"]!! >= (PlayerDataStorage.map[player.name + codex + "Level"]!!)*10000+10000) {
                message = "§6§lLEVEL UP!"
                player.playSound(player.location,Sound.ENTITY_PLAYER_LEVELUP,1.0F,1.0F)
                PlayerDataStorage.map[player.name + codex + "Exp"] = PlayerDataStorage.map[player.name + codex + "Exp"]!! - ((PlayerDataStorage.map[player.name + codex + "Level"]!!)*10000+10000)
                PlayerDataStorage.map[player.name + codex + "Level"] = PlayerDataStorage.map[player.name + codex + "Level"]!! + 1
                PlayerDataStorage.map[player.name + "statsPoint"] = PlayerDataStorage.map[player.name + "statsPoint"]!! + 1
                val icon: String = when (code) {
                    1 -> "§c\uD83D\uDDE1 전투"
                    2 -> "§e⛏ 채광"
                    3 -> "§6\uD83E\uDE93 벌목"
                    4 -> "§a☘ 농사"
                    5 -> "§3\uD83C\uDFA3 낚시"
                    6 -> "§d⦾ 제작"

                    else -> "X"
                }
                Bukkit.broadcastMessage("§e↑ " + player.name+"§f님이 "+ icon + " §e" + PlayerDataStorage.map[player.name + codex + "Level"]+"레벨§f을 달성했습니다!")
                player.sendMessage("§e❖ 스텟 포인트 +1")
                addExp(player,code,0)
                if (PlayerDataStorage.map[player.name + codex + "Level"] == 50){
                    player.playSound(player.location,Sound.UI_TOAST_CHALLENGE_COMPLETE,1.0F,1.0F)
                    player.sendTitle("§e축하합니다! " + icon + " §b" + PlayerDataStorage.map[player.name + codex + "Level"]+"레벨","§f최고 레벨 달성",20,120,120)
                    player.sendMessage("§e❖ 추가 스텟 포인트 +10")
                    PlayerDataStorage.map[player.name + "statsPoint"] = PlayerDataStorage.map[player.name + "statsPoint"]!! + 10
                }
            }
            else{
            message = hi + " §b(+" + amount + ") " + fi.repeat(((PlayerDataStorage.map[player.name + codex + "Exp"]!! /((PlayerDataStorage.map[player.name + codex + "Level"]!!).toFloat()*10000+10000))*50).toInt())+
                    "§7|".repeat(49-(((PlayerDataStorage.map[player.name + codex + "Exp"]!! /((PlayerDataStorage.map[player.name + codex + "Level"]!!).toFloat()*10000+10000))*50).toInt())) +
                    " §7(" +String.format("%.1f",((PlayerDataStorage.map[player.name + codex + "Exp"]!! /((PlayerDataStorage.map[player.name + codex + "Level"]!!).toFloat()*10000+10000)))*100)+ "%)"}
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent(message))
        }
        else{
            PlayerDataStorage.map[player.name + codex + "Exp"] = 0
            message = hi + " §b(+" + amount + ") " + "§b|".repeat(50)+
                    " §b(MAX)"
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,TextComponent(message))
        }
    }
}