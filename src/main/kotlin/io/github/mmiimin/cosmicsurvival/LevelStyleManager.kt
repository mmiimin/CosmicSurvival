package io.github.mmiimin.cosmicsurvival

import io.github.mmiimin.cosmicsurvival.util.PlayerDataStorage
import net.md_5.bungee.api.ChatColor
import org.bukkit.entity.Player

class LevelStyleManager {

    fun getLevelStyle(code: Int,player: Player):String {
        val p = player.name
        val level = (PlayerDataStorage.map[p + "miningLevel"]!! + PlayerDataStorage.map[p + "farmingLevel"]!! +
                PlayerDataStorage.map[p + "foragingLevel"]!! + PlayerDataStorage.map[p + "combatLevel"]!! + PlayerDataStorage.map[p + "fishingLevel"]!!)
        return when (code) {
            0 -> ""+ChatColor.of("#7F7F7F") + "[" + level + "★]"
            1 -> ""+ChatColor.of("#E77B57") + "[" + level + "★]"
            2 -> ""+ChatColor.of("#D1D1D1") + "[" + level + "★]"
            3 -> ""+ChatColor.of("#F8D547") + "[" + level + "★]"
            4 -> ""+ChatColor.of("#63E989") + "[" + level + "★]"
            5 -> ""+ChatColor.of("#71E0DA") + "[" + level + "★]"
            6 -> ""+ChatColor.of("#F24241") + "[" + level + "★]"
            7 -> ""+ChatColor.of("#CFB76E") + "[" + ChatColor.of("#E2E2E2") + level + "★" + ChatColor.of("#CFB76E") + "]" //WhiteGold
            8 -> ""+ChatColor.of("#DBC8B2") + "[" + ChatColor.of("#AE8A66") + level + "❋" + ChatColor.of("#DBC8B2") + "]" //CaffeLatte
            9 -> ""+ChatColor.of("#4D484D") + "[" + ChatColor.of("#59575A") + level + "❋" + ChatColor.of("#4D484D") + "]" //Netherite
            10 -> ""+ChatColor.of("#1C7E6D") + "[" + ChatColor.of("#30CAAE") + level + "★" + ChatColor.of("#1C7E6D") + "]" //Pearl
            11 -> ""+ChatColor.of("#FEF7E1") + "[" + level + ChatColor.of("#EB4540") + "❋" + ChatColor.of("#FEF7E1") + "]" //StrawberryCake
            12 -> {
                val stringBuilder = StringBuilder()
                val rainbowList = "§c§6§e§a§b§9§c§6§e§a§b§9".chunked(2)
                val everySplit = "[$level✵]".chunked(1)
                for (i in everySplit.indices){
                    stringBuilder.append(rainbowList[i])
                    stringBuilder.append(everySplit[i])
                }
                stringBuilder.toString()
            } //Rainbow
            13 -> ""+ChatColor.of("#86DDFF") + "[" + ChatColor.of("#2DA1FE") + level + ChatColor.of("#4F78FE") + "☽" + ChatColor.of("#374EEE") + "]" //Zenith
            14 -> ""+ChatColor.of("#FDCE4C") + "[" + ChatColor.of("#EBF32A") + level + ChatColor.of("#56F1F2") + "✵" + ChatColor.of("#FDCE4C") + "]" //Luxury
            15 -> {//１２３４５６７８９０
                val Str = level.toString().replace("1", "１")
                    .replace("2", "２")
                    .replace("3", "３")
                    .replace("4", "４")
                    .replace("5", "５")
                    .replace("6", "６")
                    .replace("7", "７")
                    .replace("8", "８")
                    .replace("9", "９")
                    .replace("0", "０")
                "" + ChatColor.of("#98B2BF") + "[" + ChatColor.of("#F1EFD6") + Str + "★" + ChatColor.of("#98B2BF") + "]"
            }//Modern


            16 -> ""+ChatColor.of("#EEEEEE") + "[" + ChatColor.of("#AE8A66") + level + "❋" + ChatColor.of("#EEEEEE") + "]" //TDL
            17 -> ""+ChatColor.of("#EEEEEE") + "[" + ChatColor.of("#AE8A66") + level + "❋" + ChatColor.of("#EEEEEE") + "]" //TDL


            else -> "§4ERROR (code:$code)"
        }

    }
}